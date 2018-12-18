package gyqx.spdherp.deptMgr.dao.mapper;

import java.util.List;

import gyqx.spdherp.deptMgr.vo.DeptGoodsReplaceVo;
import gyqx.spdherp.po.DeptGoodsInfo;
import org.apache.ibatis.annotations.Param;

import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdherp.deptMgr.vo.DeptGoodsInfoVo;
import gyqx.spdherp.deptMgr.vo.DeptGoodsList4InStockVo;
import gyqx.spdherp.deptMgr.vo.SaleManVo;
import org.springframework.web.bind.annotation.PathVariable;

public interface IdeptGoodsInfoMapper {

	List<DeptGoodsInfoVo> deptGoodsInfoList(DeptGoodsInfoVo deptGoodsInfoVo);

	List<DeptGoodsList4InStockVo> getDeptGoodsList4InStock(DeptGoodsList4InStockVo vo);

	List<HosGoodsInfo> deptGoodsInfoImportQuery(DeptGoodsInfoVo queryObject);

	/**
	 * 查询业务员
	 * 
	 * @param queryObject
	 * @return
	 */
	List<SaleManVo> getSaleManList(@Param("vo") SaleManVo queryObject);

	DeptGoodsReplaceVo getGoodsByErpCode(@Param("hosId")String hosId,
										 @Param("erpCode")String erpCode);
	Integer insertByBatch(List<DeptGoodsInfo> lst);
}
