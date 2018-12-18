package gyqx.spdherp.provManager.dao.mapper;

import java.util.List;

import gyqx.spdherp.provManager.vo.ProvGoodsVo;

public interface IProvGoodsMapper {
	
	List<ProvGoodsVo> queryProvGoodsList(ProvGoodsVo provGoodsVo);

}
