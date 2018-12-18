package gyqx.spdherp.provManager.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdherp.provManager.dao.mapper.IProvGoodsMapper;
import gyqx.spdherp.provManager.vo.ProvGoodsVo;

@Repository
public class ProvGoodsDao {

	@Resource
	IProvGoodsMapper mapper;

	public List<ProvGoodsVo> queryProvGoodsList(ProvGoodsVo provGoodsVo) {
		return mapper.queryProvGoodsList(provGoodsVo);
	}
	
}
