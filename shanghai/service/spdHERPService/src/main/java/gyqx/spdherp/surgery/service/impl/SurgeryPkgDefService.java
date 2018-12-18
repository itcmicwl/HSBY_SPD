package gyqx.spdherp.surgery.service.impl;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.transform.Tx;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdherp.po.SurgeryPkgDef;
import gyqx.spdherp.surgery.constant.Constants;
import gyqx.spdherp.surgery.dao.SurgeryPkgDefDao;
import gyqx.spdherp.surgery.service.ISurgeryPkgDefService;
import gyqx.spdherp.surgery.service.ISurgeryPkgKindService;
import gyqx.spdherp.surgery.vo.ElTreeVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgDefVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgKindVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SurgeryPkgDefService implements ISurgeryPkgDefService {
	
	@Resource
	private SurgeryPkgDefDao  dao;

	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private SimpleDao simpleDao;

	@Resource
	UserOnlineStateUtils userOnline;

	@Resource
	private ISurgeryPkgKindService surgeryPkgKindService;
	
	public SurgeryPkgDef add(SurgeryPkgDefVo surgeryPkgDefVo) throws Exception
	{
		String id = surgeryPkgDefVo.getHosId()+":"+ atomUtil.newValue("surgery_def_id");
		surgeryPkgDefVo.setId(id);
		surgeryPkgDefVo.setLastUpdateDatetime(new java.sql.Date(new Date().getTime()));
		surgeryPkgDefVo.setFlag(Constants.SUR_DEF_FLAG_NORMAL);
		surgeryPkgDefVo.setVersion(0);

		// 查重处理
		long num = 0;
		if(!StringUtils.isEmpty(surgeryPkgDefVo.getCname())) {
			num = simpleDao.queryForObject("select count(*) from surgery_pkg_def where cname= ?  and flag = 1 and hos_id=? ", Long.class, surgeryPkgDefVo.getCname(), surgeryPkgDefVo.getHosId());
			if (num > 0) {
				ValidateException.Throw("cname", '"' + surgeryPkgDefVo.getCname() + '"' + "已存在！", surgeryPkgDefVo.getCname());
			}
		}
		if(!StringUtils.isEmpty(surgeryPkgDefVo.getEname())){
			num = simpleDao.queryForObject("select count(*) from surgery_pkg_def where ename= ?  and flag = 1 and hos_id=? ", Long.class, surgeryPkgDefVo.getEname(), surgeryPkgDefVo.getHosId());
			if(num>0){
				ValidateException.Throw("ename", '"' + surgeryPkgDefVo.getEname() + '"' +  "已存在！", surgeryPkgDefVo.getEname());
			}
		}

		// 判断手术包类型是否为叶子节点
		long sonNum = simpleDao.queryForObject("select count(*) from surgery_pkg_kind where level_code like ?", Long.class, surgeryPkgDefVo.getKindCode() + "." + '%');
		if(sonNum > 0) {
			ValidateException.Throw("kindId", "所属类型必须为手术包分类的叶子节点",surgeryPkgDefVo.getCname());
		}

		// 根据手术包类型levelCode获取类型id
		String kindId = simpleDao.queryForObject("select id from surgery_pkg_kind where level_code = ? ", String.class, surgeryPkgDefVo.getKindCode());
		surgeryPkgDefVo.setKindId(kindId);

		SurgeryPkgDef surgeryPkgDef = new SurgeryPkgDef();
		Tx.transform(surgeryPkgDefVo).to(surgeryPkgDef);
		return dao.insertAndGet(surgeryPkgDef);
	}
		
	public int update(SurgeryPkgDefVo surgeryPkgDefVo) throws Exception {
		// 查重处理
		long num = 0;
		if(!StringUtils.isEmpty(surgeryPkgDefVo.getCname())) {
			num = simpleDao.queryForObject("select count(*) from surgery_pkg_def where cname= ?  and hos_id=? and flag = 1 and id != ?", Long.class, surgeryPkgDefVo.getCname(), surgeryPkgDefVo.getHosId(), surgeryPkgDefVo.getId());
			if (num > 0) {
				ValidateException.Throw("cname", '"' + surgeryPkgDefVo.getCname() + '"' + "已存在！", surgeryPkgDefVo.getCname());
			}
		}
		if(!StringUtils.isEmpty(surgeryPkgDefVo.getEname())) {
			num = simpleDao.queryForObject("select count(*) from surgery_pkg_def where ename= ?  and hos_id=?  and flag = 1 and id != ?", Long.class, surgeryPkgDefVo.getEname(), surgeryPkgDefVo.getHosId(), surgeryPkgDefVo.getId());
			if (num > 0) {
				ValidateException.Throw("ename", '"' + surgeryPkgDefVo.getEname() + '"' + "已存在！", surgeryPkgDefVo.getEname());
			}
		}

		// 判断手术包类型是否为叶子节点
		long sonNum = simpleDao.queryForObject("select count(*) from surgery_pkg_kind where level_code like ?", Long.class, surgeryPkgDefVo.getKindCode() + "." + '%');
		if(sonNum > 0) {
			ValidateException.Throw("kindId", "所属类型必须为手术包分类的叶子节点",null);
		}

		// 根据手术包类型levelCode获取类型id
		String kindId = simpleDao.queryForObject("select id from surgery_pkg_kind where level_code = ?", String.class, surgeryPkgDefVo.getKindCode());
		surgeryPkgDefVo.setKindId(kindId);
/*
		SurgeryPkgKind surgeryPkgKind = (SurgeryPkgKind) simpleDao.getByFieldName(SurgeryPkgKind.class, "levelCode", surgeryPkgDefVo.getKindCode());
		surgeryPkgDefVo.setKindId(surgeryPkgKind.getId());
*/

		SurgeryPkgDef surgeryPkgDef = new SurgeryPkgDef();
		Tx.transform(surgeryPkgDefVo).to(surgeryPkgDef);

		return dao.update(surgeryPkgDef);
	}

	public List<SurgeryPkgDefVo> list(SurgeryPkgDefVo bean) throws Exception
	{
		return (List<SurgeryPkgDefVo>) dao.list(bean);
	}	
	
	public QueryResult<SurgeryPkgDefVo> listByPage(QueryInfo<SurgeryPkgDefVo> queryInfo) throws Exception{
		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

	public ElTreeVo getSurgeryKindDefTreeByHos(Map params) throws Exception {

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
			SurgeryPkgKindVo par = new SurgeryPkgKindVo();
			par.setHosId(userOnline.getCurrent().getCorpId());
			par.setPid("/");
			List<SurgeryPkgKindVo> data = surgeryPkgKindService.list(par);

			if (data != null && data.size() > 0) {
				for (SurgeryPkgKindVo pkgKind : data) {
					ElTreeVo childNode = new ElTreeVo();
					childNode.setId(pkgKind.getId());
					childNode.setLabel(pkgKind.getKindName());
					childNode.setPid(pkgKind.getPid());
					childNode.setCode(pkgKind.getLevelCode());
					childNode.setNodeType(ElTreeVo.NODE_TYPE_KIND);
					childNode.setOb(pkgKind);
					if (checkIfLeaf(childNode.getId())) {
						childNode.setIsLeaf(1);
						childNode.setChildren(setSurKindDefNode(pkgKind));
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
			//result.setData(root);
			//System.out.println(result.getData().toString());
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return root;
	}

	private List<ElTreeVo> setSurKindDefNode(SurgeryPkgKindVo pkgKind) throws Exception{
		SurgeryPkgDefVo surgeryPkgDefVo = new SurgeryPkgDefVo();
		surgeryPkgDefVo.setKindId(pkgKind.getId());
		surgeryPkgDefVo.setHosId(pkgKind.getHosId());
		surgeryPkgDefVo.setFlag(Constants.SUR_DEF_FLAG_NORMAL);
		List<SurgeryPkgDefVo> surgeryPkgDefList = list(surgeryPkgDefVo);

		List<ElTreeVo> nodeList = new ArrayList();

		if (surgeryPkgDefList.size() > 0) {
			for (SurgeryPkgDefVo surgeryPkgDef : surgeryPkgDefList) {
				ElTreeVo childNode = new ElTreeVo();
				childNode.setId(surgeryPkgDef.getId());
				childNode.setLabel(surgeryPkgDef.getCname());
				childNode.setPid(surgeryPkgDef.getKindId());
				//childNode.setCode(pkgKind.getLevelCode());
				childNode.setNodeType(ElTreeVo.NODE_TYPE_DEF);
				childNode.setIsLeaf(1);
				childNode.setOb(surgeryPkgDef);

				nodeList.add(childNode);
			}
		}
		return nodeList;


	}

	private ElTreeVo appColumnNode(ElTreeVo parentNode) throws Exception {
		SurgeryPkgKindVo surgeryPkgKind = new SurgeryPkgKindVo();
		surgeryPkgKind.setPid(parentNode.getId());
		surgeryPkgKind.setHosId(userOnline.getCurrent().getCorpId());

		List<ElTreeVo> tsonList=new ArrayList();

		List<SurgeryPkgKindVo> data=   surgeryPkgKindService.list(surgeryPkgKind);
		if(data.size()>0){
			for (SurgeryPkgKindVo pkgKind : data){
				ElTreeVo childNode  = new ElTreeVo();
				childNode.setId(pkgKind.getId());
				childNode.setLabel(pkgKind.getKindName());
				childNode.setCode(pkgKind.getLevelCode());
				childNode.setPid(pkgKind.getPid());
				childNode.setPcode(parentNode.getCode());
				childNode.setOb(pkgKind);
				childNode.setNodeType(ElTreeVo.NODE_TYPE_KIND);
				if(checkIfLeaf(childNode.getId())){
					childNode.setIsLeaf(1);
					childNode.setChildren(setSurKindDefNode(pkgKind));
				}
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
	protected boolean checkIfLeaf(String  id) throws Exception{
		boolean  b = true;

		SurgeryPkgKindVo surgeryPkgKind = new SurgeryPkgKindVo();
		surgeryPkgKind.setPid(id);
		surgeryPkgKind.setHosId(userOnline.getCurrent().getCorpId());

		List<SurgeryPkgKindVo> data=   surgeryPkgKindService.list(surgeryPkgKind);
		if(data.size()>0){
			b =false;
		}

		return b;
	}

}


