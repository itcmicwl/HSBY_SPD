package gyqx.spdhdi.myInfos.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import common.utils.UserOnlineStateUtils;

import java.util.Date;
import java.util.List;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.po.ProvHosInfoCode;
import gyqx.spdhdi.myInfos.dao.ProvHosInfoCodeDao;
import gyqx.spdhdi.myInfos.service.IProvHosInfoCodeService;
import gyqx.spdhdi.myInfos.vo.ProvHosInfoCodeVo;

@Service
public class ProvHosInfoCodeService implements IProvHosInfoCodeService {
	
	@Resource
	private ProvHosInfoCodeDao  dao;
	@Resource
	private SimpleDao simpleDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private SysConfigUtil configUtil;
	@Resource
	private UserOnlineStateUtils userOnline;
	public ProvHosInfoCode get(String id)
	{
		return dao.get( id);	
	}	

	public ProvHosInfoCode add(ProvHosInfoCode provHosInfoCode) throws Exception 
	{
		String id = atomUtil.newValue("prov_hos_code");
		provHosInfoCode.setProvId(userOnline.getCurrent().getCorpId());
		provHosInfoCode.setVersion(0);
		provHosInfoCode.setFillDate(new Date());
		provHosInfoCode.setId(id);
		return dao.insertAndGet(provHosInfoCode);
	}
		
	public ProvHosInfoCode update(ProvHosInfoCode provHosInfoCode) throws Exception {
		provHosInfoCode.setProvId(userOnline.getCurrent().getCorpId());
	
		provHosInfoCode.setLastUpdateDatetime(new Date());
		return dao.updateAndGet(provHosInfoCode);
	}
	public int updateFields(ProvHosInfoCode provHosInfoCode,String fieldNames) throws Exception
	{
		return dao.updateFields(provHosInfoCode, fieldNames);
	}

	public void delete(ProvHosInfoCode provHosInfoCode) throws Exception {
		dao.delete(provHosInfoCode);
	}
	
	public ProvHosInfoCode getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ProvHosInfoCode) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<ProvHosInfoCode> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ProvHosInfoCode>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<ProvHosInfoCode> list(ProvHosInfoCode bean) throws Exception
	{
		return (List<ProvHosInfoCode>) dao.list(bean);
	}	
	
	public QueryResult<ProvHosInfoCode> listByPage(QueryInfo<ProvHosInfoCode> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}
	public QueryResult<ProvHosInfoCodeVo> list_PH_code_voByPage(QueryInfo<ProvHosInfoCodeVo> queryInfo) throws Exception{
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list_PH_code_vo(queryInfo.getQueryObject()));
	}

}


