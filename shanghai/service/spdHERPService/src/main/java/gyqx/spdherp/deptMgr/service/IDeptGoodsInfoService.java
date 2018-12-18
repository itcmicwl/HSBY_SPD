package gyqx.spdherp.deptMgr.service;

import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdherp.deptMgr.vo.*;
import gyqx.spdherp.po.DeptGoodsInfo;

public interface IDeptGoodsInfoService {

	QueryResult<DeptGoodsInfoVo> deptGoodsInfoList(QueryInfo<DeptGoodsInfoVo> queryInfo) throws Exception;

	DeptGoodsInfo deptGoodsInfoEdit(DeptGoodsInfo deptGoodsInfo) throws Exception;

	DeptGoodsInfo deptGoodsInfoDelete(DeptGoodsInfo deptGoodsInfo) throws Exception;

	List<DeptGoodsInfo> deptGoodsInfoMulDelete(List<DeptGoodsInfo> deptGoodsInfos) throws Exception;

	QueryResult<HosGoodsInfo> deptGoodsInfoImportQuery(QueryInfo<DeptGoodsInfoVo> queryInfo) throws Exception;

	List<DeptGoodsInfo> deptGoodsInfoImport(DeptGoodsInfoImportVo deptGoodsInfoImportVo) throws Exception;
	QueryResult<DeptGoodsList4InStockVo> getDeptGoodsList4InStock(QueryInfo<DeptGoodsList4InStockVo> queryInfo) throws Exception;

	/**
	 * 查询业务员
	 * @param queryInfo
	 * @return
	 */
	QueryResult<SaleManVo> getSaleManList(QueryInfo<SaleManVo> queryInfo);

	DeptGoodsReplaceVo getGoodsByErpCode(String hosId, String erpCode);
	

}
