package gyqx.spdherp.deptMgr.dao;

import java.util.List;

import javax.annotation.Resource;

import gyqx.spdherp.deptMgr.vo.DeptGoodsReplaceVo;
import gyqx.spdherp.po.DeptGoodsInfo;
import org.springframework.stereotype.Repository;

import common.db.query.QueryInfo;
import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdherp.deptMgr.dao.mapper.IdeptGoodsInfoMapper;
import gyqx.spdherp.deptMgr.vo.DeptGoodsInfoVo;
import gyqx.spdherp.deptMgr.vo.DeptGoodsList4InStockVo;
import gyqx.spdherp.deptMgr.vo.SaleManVo;

@Repository
public class DeptGoodsInfoMgrDao {
	@Resource
	private IdeptGoodsInfoMapper mapper;

	public List<DeptGoodsInfoVo> deptGoodsInfoList(DeptGoodsInfoVo deptGoodsInfoVo) {
		return mapper.deptGoodsInfoList(deptGoodsInfoVo);
	}
	public List<DeptGoodsList4InStockVo> getDeptGoodsList4InStock(DeptGoodsList4InStockVo vo){
		return mapper.getDeptGoodsList4InStock(vo);
	}
	
	public List<HosGoodsInfo> deptGoodsInfoImportQuery(DeptGoodsInfoVo queryObject) {
		return mapper.deptGoodsInfoImportQuery(queryObject);
	}
	public List<SaleManVo> getSaleManList(QueryInfo<SaleManVo> queryInfo) {
		return mapper.getSaleManList(queryInfo.getQueryObject());
	}
	public DeptGoodsReplaceVo getGoodsByErpCode(String hosId,String erpCode){
		return mapper.getGoodsByErpCode(hosId,erpCode);
	}
	public Integer insertByBatch(List<DeptGoodsInfo> lst){
		return mapper.insertByBatch(lst);
	}
}
