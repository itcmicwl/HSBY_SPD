package gyqx.spdherp.spdOutPtl.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.spdherp.spdOutPtl.vo.DeptBuyForSpdPtl;
import gyqx.spdherp.spdOutPtl.vo.OutStockForSpdPtl;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.spdOutPtl.dao.mapper.ISpdOutPtlMapper;
import gyqx.spdherp.po.SpdOutPtl;

@Repository
public class SpdOutPtlDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private ISpdOutPtlMapper mapper;

	public SpdOutPtl get(String id)
	{
		return dao.get(SpdOutPtl.class, id);	
	}	

	public SpdOutPtl insertAndGet(SpdOutPtl spdOutPtl) throws Exception 
	{
		return dao.insertAndGet(spdOutPtl);
	}

	public SpdOutPtl updateAndGet(SpdOutPtl spdOutPtl) throws Exception {
		return dao.updateAndGet(spdOutPtl);
	}
	public int updateFields(SpdOutPtl spdOutPtl,String fieldNames) throws Exception
	{
		return dao.updateFields(spdOutPtl, fieldNames);
	}
	public int updateNotNullFields(SpdOutPtl spdOutPtl) throws Exception
	{
		return dao.updateNotNullFields(spdOutPtl);
	}

	public void delete(SpdOutPtl spdOutPtl) throws Exception {
		dao.delete(spdOutPtl);
	}
	
	public SpdOutPtl getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (SpdOutPtl) dao.getByFieldName(SpdOutPtl.class, fieldName, fieldValue);
	}
	
	public List<SpdOutPtl> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<SpdOutPtl>) dao.queryBeanList(SpdOutPtl.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(SpdOutPtl.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<SpdOutPtl> list(SpdOutPtl qbean) throws Exception{		
		
		return mapper.list(qbean);
	}

	public int insertSpdOutPtlLists(List<SpdOutPtl> list){
		return mapper.insertSpdOutPtlLists(list);
	}

	public List<OutStockForSpdPtl> getOutListAddErpCode(String pid){
		return mapper.getOutListAddErpCode(pid);
	}

	public List<DeptBuyForSpdPtl> getPurListInfo(String billId,String provId){
		return mapper.getPurListInfo(billId);
	}

	public List<SpdOutPtl> getSpdOutPtlList(String hosId){
		return mapper.getSpdOutPtl(hosId);
	}
}


