package gyqx.spdherp.applyMgr.service;


import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.applyMgr.vo.DeptCarGoodsStVo;
import gyqx.spdherp.applyMgr.vo.DeptCarSurGoodsStVo;
import gyqx.spdherp.po.DeptBuyCar;

public interface IDeptBuyCarService 
{
	DeptBuyCar get(String id) throws Exception ;
	DeptBuyCar add(DeptBuyCar deptBuyCar) throws Exception ;
	DeptBuyCar update(DeptBuyCar deptBuyCar) throws Exception;
	
	List<DeptBuyCar> query(String predicate,String orderBy,Object... fieldValue) throws Exception;
	
	List<DeptBuyCar> list(DeptBuyCar bean) throws Exception;
	
	QueryResult<DeptBuyCar> listByPage(QueryInfo<DeptBuyCar> queryInfo) throws Exception;	
	void addList(List<DeptBuyCar> cars) throws Exception ;

	QueryResult<DeptCarGoodsStVo> listByPage_deptCarGoodsStlist(QueryInfo<DeptCarGoodsStVo> queryInfo) throws Exception;
	
	List<DeptCarGoodsStVo> list_deptCarGoodsStlist(DeptCarGoodsStVo bean) throws Exception;
	void deleteCars(List<DeptBuyCar> cars) throws Exception ;

	 void addList4mixSur(List<DeptBuyCar> cars) throws Exception ;
	 QueryResult<DeptCarSurGoodsStVo> listByPage_deptCarGoodsStlist4sur(QueryInfo<DeptCarGoodsStVo> queryInfo) throws Exception;
		String updateSurQty(DeptCarSurGoodsStVo row) throws Exception;


}


