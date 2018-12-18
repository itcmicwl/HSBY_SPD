package gyqx.spdherp.surgery.dao;


import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdherp.po.SurgeryPkgKind;
import gyqx.spdherp.surgery.dao.mapper.ISurgeryPkgKindMapper;
import gyqx.spdherp.surgery.vo.SurgeryPkgKindVo;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class SurgeryPkgKindDao 
{
	@Resource
	private SimpleDao dao;	

	@Resource
	private ISurgeryPkgKindMapper mapper;

	public SurgeryPkgKind get(String id)
	{
		return dao.get(SurgeryPkgKind.class, id);	
	}	

	public SurgeryPkgKind insertAndGet(SurgeryPkgKind surgeryPkgKind) throws Exception {
		String pid = surgeryPkgKind.getPid();
		SurgeryPkgKind parent = get(pid);
		surgeryPkgKind = codeMgr(surgeryPkgKind, parent);
		dao.insert(surgeryPkgKind);

		return surgeryPkgKind;

	}

	public SurgeryPkgKind codeMgr(SurgeryPkgKind surgeryPkgKind, SurgeryPkgKind parent)throws Exception{
		if(parent != null && StringUtils.hasText(surgeryPkgKind.getPid()) && surgeryPkgKind.getPid() !="/"){
			//lock this
			dao.updateVersion(SurgeryPkgKind.class, surgeryPkgKind.getPid());

            List<String> maxCodes = dao.queryValues("select max(level_code) from surgery_pkg_kind where pid = ? ",String.class, surgeryPkgKind.getPid());

			String code = StringUtils.hasText(parent.getLevelCode())? parent.getLevelCode() +"." + "00001" : "00001";
			//String code = "";
			if(maxCodes.size()> 0 && StringUtils.hasText(maxCodes.get(0))){
				String maxCode = maxCodes.get(0);
				int lastDotIndex = maxCode.lastIndexOf(".");
				String lastSeg = maxCode.substring( lastDotIndex +1,maxCode.length());
				code = maxCode.substring(0,lastDotIndex+1) + String.format("%05d", Integer.parseInt(lastSeg)+1);
			}
			surgeryPkgKind.setLevelCode(code);
		}
		else{
			//List<String> maxCodes = dao.queryValues("select max(level_code) from surgery_pkg_kind where hos_id = ? and (p_id=''or p_id is null or p_id ='/')",String.class,surgeryPkgKind.getHosId());
            List<String> maxCodes = dao.queryValues("select max(level_code) from surgery_pkg_kind where hos_id = ? and (pid=''or pid is null or pid ='/')",String.class, surgeryPkgKind.getHosId());
			String code = surgeryPkgKind.getHosId()+":00001";
			if(maxCodes.size()> 0 && StringUtils.hasText(maxCodes.get(0)))
			{
				String maxCode = maxCodes.get(0);
				int lastDotIndex = maxCode.lastIndexOf(":");
				String lastSeg = maxCode.substring( lastDotIndex +1,maxCode.length());
				code = maxCode.substring(0,lastDotIndex+1) + String.format("%05d", Integer.parseInt(lastSeg)+1);
			}
			surgeryPkgKind.setLevelCode(code);
		}
		return surgeryPkgKind;
	}

	public int updateAndGet(SurgeryPkgKind surgeryPkgKind) throws Exception {
/*		String pid = surgeryPkgKind.getPid();
		surgeryPkgKind.setIsLeaf(0);
		SurgeryPkgKind parent = get(pid);
		surgeryPkgKind = codeMgr(surgeryPkgKind, parent);*/
		return mapper.update(surgeryPkgKind);
	}

	public void delete(SurgeryPkgKind surgeryPkgKind) throws Exception {
		dao.delete(surgeryPkgKind);
	}

	public List<SurgeryPkgKindVo> list(SurgeryPkgKindVo qbean) throws Exception{
		
		return mapper.list(qbean);
	}

    public int updateAllSonCode(Map map){
	    return mapper.updateAllSonCode(map);
    }
}


