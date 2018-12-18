package gyqx.spdherp.applyMgr.dao.mapper;

import java.util.List;

import gyqx.spdherp.applyMgr.vo.DeptBuyBillVo;
import gyqx.spdherp.applyMgr.vo.DeptBuySubVo;
import gyqx.spdherp.applyMgr.vo.ProvGoodsKindsVo;
import gyqx.spdherp.po.DeptBuySub;
import org.apache.ibatis.annotations.Param;

public interface IDeptBuySubMapper {
	int update(DeptBuySub bean);
	List<DeptBuySub> list(DeptBuySub queryInfo) ;
	List<DeptBuySubVo> getVoList(DeptBuySubVo queryInfo) ;
	void deleteByBillId(String billId);
	List<DeptBuySubVo> getDetailsVo4Pur(DeptBuySubVo queryInfo) ;
	List<DeptBuySubVo> getDetailsVo4Merger(DeptBuySubVo queryInfo) ;

	List<ProvGoodsKindsVo> getProvGoodsKinds(DeptBuySubVo queryInfo);
	List<DeptBuySubVo> getVoListByOut(String id);
	List<DeptBuySubVo> getVoList4SurPack(@Param("billId")String billId,@Param("stockId")String stockId);
}

