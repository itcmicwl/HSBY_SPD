package gyqx.spdherp.surgery.service.impl;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import common.utils.UserOnlineStateUtils;
import common.web.AjaxResult;
import gyqx.spdherp.po.SurgeryPkgKind;
import gyqx.spdherp.surgery.dao.SurgeryPkgKindDao;
import gyqx.spdherp.surgery.service.ISurgeryPkgKindService;
import gyqx.spdherp.surgery.vo.ElTreeVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgKindVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class SurgeryPkgKindService implements ISurgeryPkgKindService {
	
	@Resource
	private SurgeryPkgKindDao  dao;

	@Resource
	private SysConfigUtil configUtil;

	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private SimpleDao simpleDao;

	@Resource
	UserOnlineStateUtils userOnline;

	public SurgeryPkgKind add(SurgeryPkgKind surgeryPkgKind) throws Exception
	{
		String id = surgeryPkgKind.getHosId()+":"+atomUtil.newValue("surgery_kind_id");
		surgeryPkgKind.setId(id);
		surgeryPkgKind.setLastUpdateDatetime(new java.sql.Date(new Date().getTime()));
		surgeryPkgKind.setVersion(0);

		if(StringUtils.isEmpty(surgeryPkgKind.getPid())){
			surgeryPkgKind.setPid("/");
		} else if(surgeryPkgKind.getPid().indexOf(":") > 0){
            //前端pid 存的是 pcode  需要查询找到真实的pid
            SurgeryPkgKind parentKind =(SurgeryPkgKind)	simpleDao.getByFieldName(SurgeryPkgKind.class, "levelCode", surgeryPkgKind.getPid());
            surgeryPkgKind.setPid(parentKind.getId());
		}
		// 查重处理
		long num = 0;
		if(!StringUtils.isEmpty(surgeryPkgKind.getKindName())) {
			num = simpleDao.queryForObject("select count(*) from surgery_pkg_kind where kind_name= ?  and hos_id=? ", Long.class, surgeryPkgKind.getKindName(), surgeryPkgKind.getHosId());
		}
		if(num>0){
			ValidateException.Throw("kindName", surgeryPkgKind.getKindName() +  "已存在！",surgeryPkgKind.getKindName());
		}
		return dao.insertAndGet(surgeryPkgKind);
	}
		
	public int update(SurgeryPkgKind surgeryPkgKind) throws Exception {
        surgeryPkgKind.setLastUpdateDatetime(new java.sql.Date(new Date().getTime()));
        surgeryPkgKind.setVersion(surgeryPkgKind.getVersion() + 1);

		long num = 0;
		if(!StringUtils.isEmpty(surgeryPkgKind.getKindName())) {
			num = simpleDao.queryForObject("select count(*) from surgery_pkg_kind where kind_name= ?  and hos_id=? and id != ?", Long.class, surgeryPkgKind.getKindName(), surgeryPkgKind.getHosId(), surgeryPkgKind.getId());
		}
		if(num>0){
			ValidateException.Throw("kindName", surgeryPkgKind.getKindName() +  "已存在！",surgeryPkgKind.getKindName());
		} else {
            String oldLevelCode = surgeryPkgKind.getLevelCode();// 暂存原有code
			int idx = oldLevelCode.lastIndexOf(".");
			// 原类型的上级分类
			String oldParentLevelCode = "";
			if(idx > 0) {
				oldParentLevelCode = oldLevelCode.substring(0, idx);
			}
			// 新类型的上级分类code
			String newPcode = surgeryPkgKind.getPid();	// pid为前端传过来的code

			// 判断是否修改了上级分类
            if(!oldParentLevelCode.equals(newPcode)){

				if(oldLevelCode.equals(newPcode)) {
					ValidateException.Throw("levelCode", "上级分类不能为自己",null );
				} else {
					if (newPcode.indexOf(oldLevelCode + ".") == 0) {
						ValidateException.Throw("levelCode", "上级分类不能为自己的子分类", null);
					}
				}


				SurgeryPkgKind parentKind = new SurgeryPkgKind();
				if(!StringUtils.isEmpty(surgeryPkgKind.getPid()) && !"/".equals(surgeryPkgKind.getPid())) {
					parentKind = (SurgeryPkgKind) simpleDao.getByFieldName(SurgeryPkgKind.class, "levelCode", newPcode);
					surgeryPkgKind.setPid(parentKind.getId());
				} else {
					surgeryPkgKind.setPid("/");
				}

                // 修改类型层级，需要重新生成code
                surgeryPkgKind = dao.codeMgr(surgeryPkgKind, parentKind);
                // 是否有子类型 ，有则所有子类型的code 都要调整
                long sonNum = simpleDao.queryForObject("select count(*) from surgery_pkg_kind where level_code like ?", Long.class, oldLevelCode + "." + '%');
                if (sonNum > 0) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("oldCode", oldLevelCode);
                    map.put("newCode", surgeryPkgKind.getLevelCode());
                    dao.updateAllSonCode(map);
                }
            } else {
                // 层级未改动，pid存储的为上级分类的层级代码，该字段不更新
                surgeryPkgKind.setPid(null);
                surgeryPkgKind.setLevelCode(null);
            }

        }
		return dao.updateAndGet(surgeryPkgKind);
	}

    public int delete(SurgeryPkgKind surgeryPkgKind) throws Exception {
        //判断是否有下级分类，有则不允许删除
        long num=simpleDao.queryForObject("select count(*) from surgery_pkg_kind where pid = ? ", Long.class, surgeryPkgKind.getId());
        if(num>0){
            ValidateException.Throw("kindName", "该手术包类型存在子类型，不能删除！",null );
        }
		num=simpleDao.queryForObject("select count(*) from surgery_pkg_def where kind_id = ? and flag = 1", Long.class, surgeryPkgKind.getId());
		if(num>0){
			ValidateException.Throw("kindName", "该手术包类型下存在正在使用的手术包，不能删除！",null );
		}
        return simpleDao.deleteByFieldNames(surgeryPkgKind,"id");
    }

	
	public List<SurgeryPkgKindVo> list(SurgeryPkgKindVo bean) throws Exception
	{
		return (List<SurgeryPkgKindVo>) dao.list(bean);
	}	

	public ElTreeVo getSurgeryKindTreeByHos(SurgeryPkgKindVo params) throws Exception {
		AjaxResult result = new AjaxResult();

		ElTreeVo root = new ElTreeVo();
		root.setId("-1");
		root.setLabel("root");

/*		ElTreeVo treeNode = new ElTreeVo();
		treeNode.setId("-1");
		treeNode.setLabel("手术包分类");
		treeNode.setCode("-1");
		treeNode.setNodeType(ElTreeVo.NODE_TYPE_KIND);
		treeNode.setOb(null);*/
		List<ElTreeVo> tsonList = new ArrayList();

		try {
//			SurgeryPkgKindVo par = new SurgeryPkgKindVo();
			params.setHosId(userOnline.getCurrent().getCorpId());
			params.setPid("/");
			List<SurgeryPkgKindVo> data = list(params);

			if (data.size() > 0) {
				for (SurgeryPkgKindVo pkgKind : data) {
					ElTreeVo childNode = new ElTreeVo();
					childNode.setId(pkgKind.getId());
					childNode.setLabel(pkgKind.getKindName());
					childNode.setPid(pkgKind.getPid());
					childNode.setCode(pkgKind.getLevelCode());
					childNode.setOb(pkgKind);
					if (checkIfLeaf(childNode.getId())) {
						childNode.setIsLeaf(1);
					} else {
						childNode.setIsLeaf(0);
						childNode = appColumnNode(childNode);
					}
					tsonList.add(childNode);
				}
				root.setChildren(tsonList);
			}
			//List<ElTreeVo> rootSonList = new ArrayList();
			//rootSonList.add(treeNode);
			//root.setChildren(rootSonList);
			result.setData(root);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return root;
	}

	private ElTreeVo appColumnNode(ElTreeVo parentNode) throws Exception {
		SurgeryPkgKindVo surgeryPkgKind = new SurgeryPkgKindVo();
		surgeryPkgKind.setPid(parentNode.getId());
		surgeryPkgKind.setHosId(userOnline.getCurrent().getCorpId());

		List<ElTreeVo> tsonList=new ArrayList();

		List<SurgeryPkgKindVo> data=  list(surgeryPkgKind);
		if(data.size()>0){
			for (SurgeryPkgKindVo pkgKind : data){
				ElTreeVo childNode  = new ElTreeVo();
				childNode.setId(pkgKind.getId());
				childNode.setLabel(pkgKind.getKindName());
				childNode.setCode(pkgKind.getLevelCode());
				childNode.setPid(pkgKind.getPid());
				childNode.setPcode(parentNode.getCode());
				childNode.setOb(pkgKind);
				if(checkIfLeaf(childNode.getId())){
					childNode.setIsLeaf(1);				}
				else {
					childNode.setIsLeaf(0);
					childNode = appColumnNode(childNode);
				}
				tsonList.add(childNode);
			}
			parentNode.setChildren(tsonList);
		}


		return parentNode;
	}

	/**
	 * 根据id判断是否为叶子节点
	 * @param id
	 * @return
	 * @throws Exception
	 */
	protected boolean checkIfLeaf(String  id) throws Exception{
		boolean  b = true;

		SurgeryPkgKindVo surgeryPkgKind = new SurgeryPkgKindVo();
		surgeryPkgKind.setPid(id);
		surgeryPkgKind.setHosId(userOnline.getCurrent().getCorpId());

		List<SurgeryPkgKindVo> data= list(surgeryPkgKind);
		if(data.size()>0){
			b =false;
		}

		return b;
	}

}


