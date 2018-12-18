package gyqx.spdherp.productsInfos.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.HosCostCode;
import gyqx.spdherp.productsInfos.dao.HosCostCodeDao;
import gyqx.spdherp.productsInfos.service.IHosCostCodeService;
import gyqx.spdherp.productsInfos.vo.HosCostCodeVo;

@Service
public class HosCostCodeService implements IHosCostCodeService {
	
	@Resource
	private HosCostCodeDao  dao;
	
	public HosCostCode get(String id)
	{
		return dao.get( id);	
	}	

	public HosCostCode add(HosCostCode hosCostCode) throws Exception 
	{

		return dao.insertAndGet(hosCostCode);
	}
		
	public HosCostCode update(HosCostCode hosCostCode) throws Exception {
		return dao.updateAndGet(hosCostCode);
	}
	public int updateFields(HosCostCode hosCostCode,String fieldNames) throws Exception
	{
		return dao.updateFields(hosCostCode, fieldNames);
	}

	public void delete(HosCostCode hosCostCode) throws Exception {
		dao.delete(hosCostCode);
	}
	
	public HosCostCode getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (HosCostCode) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<HosCostCode> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<HosCostCode>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<HosCostCode> list(HosCostCode bean) throws Exception
	{
		return (List<HosCostCode>) dao.list(bean);
	}	
	
	public QueryResult<HosCostCode> listByPage(QueryInfo<HosCostCode> queryInfo) throws Exception{		
		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}
 
	public QueryResult<HosCostCodeVo> listByPage_HosCostCodeGoods(QueryInfo<HosCostCodeVo> queryInfo) throws Exception{
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.getHosCostCodeGoodsList(queryInfo.getQueryObject()));
	}

}


