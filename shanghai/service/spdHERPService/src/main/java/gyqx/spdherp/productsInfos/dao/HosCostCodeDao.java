package gyqx.spdherp.productsInfos.dao;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdherp.po.HosCostCode;
import gyqx.spdherp.productsInfos.dao.mapper.IHosCostCodeMapper;
import gyqx.spdherp.productsInfos.vo.HosCostCodeVo;

@Repository
public class HosCostCodeDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IHosCostCodeMapper mapper;

	public HosCostCode get(String id)
	{
		return dao.get(HosCostCode.class, id);	
	}	

	public HosCostCode insertAndGet(HosCostCode hosCostCode) throws Exception 
	{
		String id = hosCostCode.getHosId()+atomUtil.newValue("hosCostCode");
		hosCostCode.setId(id);
		return dao.insertAndGet(hosCostCode);
	}

	public HosCostCode updateAndGet(HosCostCode hosCostCode) throws Exception {
		return dao.updateAndGet(hosCostCode);
	}
	public int updateFields(HosCostCode hosCostCode,String fieldNames) throws Exception
	{
		return dao.updateFields(hosCostCode, fieldNames);
	}
	public int updateNotNullFields(HosCostCode hosCostCode) throws Exception
	{
		return dao.updateNotNullFields(hosCostCode);
	}

	public void delete(HosCostCode hosCostCode) throws Exception {
		dao.delete(hosCostCode);
	}
	
	public HosCostCode getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (HosCostCode) dao.getByFieldName(HosCostCode.class, fieldName, fieldValue);
	}
	
	public List<HosCostCode> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<HosCostCode>) dao.queryBeanList(HosCostCode.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(HosCostCode.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<HosCostCode> list(HosCostCode qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
	public List<HosCostCodeVo> getHosCostCodeGoodsList(HosCostCodeVo qbean) throws Exception{		

		return mapper.getHosCostCodeGoodsList(qbean);
	}
}


