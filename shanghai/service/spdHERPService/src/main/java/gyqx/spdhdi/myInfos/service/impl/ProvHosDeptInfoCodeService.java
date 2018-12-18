package gyqx.spdhdi.myInfos.service.impl;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdhdi.myInfos.dao.ProvHosDeptInfoCodeDao;
import gyqx.spdhdi.myInfos.service.IProvHosDeptInfoCodeService;
import gyqx.spdhdi.myInfos.vo.ProvHosDeptInfoCodeVo;
import gyqx.spdhdi.myInfos.vo.SaleCodeInfoVo;
import gyqx.spdhdi.po.ProvHosDeptInfoCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ProvHosDeptInfoCodeService implements IProvHosDeptInfoCodeService {
	
	@Resource
	private ProvHosDeptInfoCodeDao  dao;
	@Resource
	private SimpleDao simpleDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private SysConfigUtil configUtil;
	@Resource
	private UserOnlineStateUtils userOnline;
	public ProvHosDeptInfoCode get(String id)
	{
		return dao.get( id);	
	}	

	public ProvHosDeptInfoCode add(ProvHosDeptInfoCode provHosDeptInfoCode) throws Exception 
	{
		String id = atomUtil.newValue("prov_hos_dept_code");
		provHosDeptInfoCode.setProvId(userOnline.getCurrent().getCorpId());
		provHosDeptInfoCode.setVersion(0);
		provHosDeptInfoCode.setFillDate(new Date());
		provHosDeptInfoCode.setId(id);
		return dao.insertAndGet(provHosDeptInfoCode);
	}
		
	public ProvHosDeptInfoCode update(ProvHosDeptInfoCode provHosDeptInfoCode) throws Exception {
		provHosDeptInfoCode.setProvId(userOnline.getCurrent().getCorpId());
	
		provHosDeptInfoCode.setLastUpdateDatetime(new Date());
		return dao.updateAndGet(provHosDeptInfoCode);
	}
	public int updateFields(ProvHosDeptInfoCode provHosDeptInfoCode,String fieldNames) throws Exception
	{
		return dao.updateFields(provHosDeptInfoCode, fieldNames);
	}

	public void delete(ProvHosDeptInfoCode provHosDeptInfoCode) throws Exception {
		dao.delete(provHosDeptInfoCode);
	}
	
	public ProvHosDeptInfoCode getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ProvHosDeptInfoCode) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<ProvHosDeptInfoCode> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ProvHosDeptInfoCode>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<ProvHosDeptInfoCode> list(ProvHosDeptInfoCode bean) throws Exception
	{
		return (List<ProvHosDeptInfoCode>) dao.list(bean);
	}	
	
	public QueryResult<ProvHosDeptInfoCode> listByPage(QueryInfo<ProvHosDeptInfoCode> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}
	public QueryResult<ProvHosDeptInfoCodeVo> list_PH_dept_code_voByPage(QueryInfo<ProvHosDeptInfoCodeVo> queryInfo) throws Exception{
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list_PH_dept_code_vo(queryInfo.getQueryObject()));
	}
	public ProvHosDeptInfoCodeVo getThePHDeptCodeVoByProvIdAndOrgId(String provId ,String orgId )  throws  Exception  
	{ 
		ProvHosDeptInfoCodeVo result = new ProvHosDeptInfoCodeVo();
		QueryInfo<ProvHosDeptInfoCodeVo> queryInfo = new QueryInfo<ProvHosDeptInfoCodeVo>();
		ProvHosDeptInfoCodeVo qv= new ProvHosDeptInfoCodeVo();
		qv.setProvId(provId);
		qv.setHosDeptId(orgId);
		queryInfo.setQueryObject(qv);
		QueryResult<ProvHosDeptInfoCodeVo> ret = list_PH_dept_code_voByPage(queryInfo);
		if(ret.getData().size()>0)
			result = ret.getData().get(0);
		return result;
	}

	@Override
	public SaleCodeInfoVo getSaleCodeInfo(SaleCodeInfoVo vo) {
		return dao.getSaleCodeInfo(vo);
	}
}


