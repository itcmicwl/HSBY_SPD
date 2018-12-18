package gyqx.spdherp.deptMgr.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import gyqx.spdherp.deptMgr.dao.HosDeptApplyUpperDao;
import gyqx.spdherp.deptMgr.service.IHosDeptApplyUpperService;
import gyqx.spdherp.deptMgr.vo.HosDeptApplyUperQueryVo;
import gyqx.spdherp.po.HosDeptApplyUpper;

@Service
public class HosDeptApplyUpperService implements IHosDeptApplyUpperService {
	
	@Resource
	private HosDeptApplyUpperDao  dao;
	@Resource
	private SimpleDao simpleDao;
	@Resource
	private SysAtomUtil atomUtil;
	
	
	public HosDeptApplyUpper get(String id)
	{
		return dao.get( id);	
	}	

	public HosDeptApplyUpper add(HosDeptApplyUpper hosDeptApplyUpper) throws Exception 
	{
		String id = atomUtil.newValue("hos_dept_apply_upper_id");
		hosDeptApplyUpper.setId(id);
		return dao.insertAndGet(hosDeptApplyUpper);
	}
		
	public HosDeptApplyUpper update(HosDeptApplyUpper hosDeptApplyUpper) throws Exception {
		return dao.updateAndGet(hosDeptApplyUpper);
	}
	public int updateFields(HosDeptApplyUpper hosDeptApplyUpper,String fieldNames) throws Exception
	{
		return dao.updateFields(hosDeptApplyUpper, fieldNames);
	}

	public void delete(HosDeptApplyUpper hosDeptApplyUpper) throws Exception {
		dao.delete(hosDeptApplyUpper);
	}
	
	public HosDeptApplyUpper getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (HosDeptApplyUpper) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<HosDeptApplyUpper> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<HosDeptApplyUpper>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<HosDeptApplyUpper> list(HosDeptApplyUpper bean) throws Exception
	{
		return (List<HosDeptApplyUpper>) dao.list(bean);
	}	
	
	public QueryResult<HosDeptApplyUpper> listByPage(QueryInfo<HosDeptApplyUpper> queryInfo) throws Exception{		
		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

	@Override
	public QueryResult<HosDeptApplyUperQueryVo> queryHosDeptApplyUpperList(QueryInfo<HosDeptApplyUpper> queryInfo) {
		StringBuffer sqlSb = new StringBuffer()
		.append(" SELECT ")
		.append("    goodsInfo.*, ")
		.append("    hosDeptApplyUpper.id AS hosDeptApplyUpperId, ")
		.append("    hosDeptApplyUpper.m1_qty, ")
		.append("    hosDeptApplyUpper.m2_qty, ")
		.append("    hosDeptApplyUpper.m3_qty, ")
		.append("    hosDeptApplyUpper.m4_qty, ")
		.append("    hosDeptApplyUpper.m5_qty, ")
		.append("    hosDeptApplyUpper.m6_qty, ")
		.append("    hosDeptApplyUpper.m7_qty, ")
		.append("    hosDeptApplyUpper.m8_qty, ")
		.append("    hosDeptApplyUpper.m9_qty, ")
		.append("    hosDeptApplyUpper.m10_qty, ")
		.append("    hosDeptApplyUpper.m11_qty, ")
		.append("    hosDeptApplyUpper.m12_qty, ")
		.append("    hosDeptApplyUpper.version as hosDeptApplyUpperVersion ")
		.append(" FROM dept_goods_info goodsInfo ")
		.append("  LEFT JOIN hos_dept_apply_upper hosDeptApplyUpper ON goodsInfo.goods_id = hosDeptApplyUpper.hos_goods_id ")
		.append("   AND goodsInfo.dept_id = hosDeptApplyUpper.dept_id AND  goodsInfo.hos_id = hosDeptApplyUpper.hos_id ")
		.append(" WHERE goodsInfo.goods_id = ? AND goodsInfo.id = ? AND goodsInfo.dept_id = ? AND goodsInfo.hos_id = ? ");

		HosDeptApplyUpper obj = queryInfo.getQueryObject();
		List<HosDeptApplyUperQueryVo> queryResult = null;
		queryResult = simpleDao.queryForList(sqlSb.toString(), HosDeptApplyUperQueryVo.class, 
									obj.getHosGoodsId(),obj.getId(),obj.getDeptId(),obj.getHosId());
		PageUtils.startPage(queryInfo);
		return  PageUtils.endPage(queryResult);
	}

}


