package gyqx.spdhdi.myGoods.dao.mapper;

import java.util.List;

import gyqx.spdhdi.myGoods.vo.ProvGoodsInfoVo;
import gyqx.spdhdi.po.ProvGoodsInfo;

public interface IProvGoodsInfoMapper {
	List<ProvGoodsInfoVo> getProvGoodsInfoList(ProvGoodsInfo provGoods);
	List<ProvGoodsInfoVo> getProvGoodsInfoListByProvAndHos(gyqx.spdhdi.po.ProvGoodsHos pgh);
}
