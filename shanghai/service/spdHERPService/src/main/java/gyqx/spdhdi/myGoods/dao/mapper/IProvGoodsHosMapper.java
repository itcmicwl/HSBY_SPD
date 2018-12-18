package gyqx.spdhdi.myGoods.dao.mapper;

import java.util.List;

import gyqx.spdhdi.myGoods.vo.ProHosGoodsInfoVo;
import gyqx.spdhdi.myGoods.vo.ProvGoods4HosVo;

public interface IProvGoodsHosMapper {
	
	List<ProHosGoodsInfoVo> getProHosGoodsList(ProHosGoodsInfoVo proHosGoodsInfoVo);
	List<ProHosGoodsInfoVo> getProHosDeptGoodsList(ProHosGoodsInfoVo proHosGoodsInfoVo);
	List<ProvGoods4HosVo> getProGoods4HosList(ProvGoods4HosVo v);

	
}
