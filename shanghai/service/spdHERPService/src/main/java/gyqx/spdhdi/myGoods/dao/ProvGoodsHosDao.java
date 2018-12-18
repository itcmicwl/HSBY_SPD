package gyqx.spdhdi.myGoods.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdhdi.myGoods.dao.mapper.IProvGoodsHosMapper;
import gyqx.spdhdi.myGoods.vo.ProHosGoodsInfoVo;
import gyqx.spdhdi.myGoods.vo.ProvGoods4HosVo;




@Repository
public class ProvGoodsHosDao {
	@Resource
	private IProvGoodsHosMapper mapper;
	/*public List<BasGoodsInfoVo> getBasGoodsInfoList(BasGoodsInfoVo good) throws Exception {
		return mapper.getBasGoodsInfoList(good);
	}*/
	@Resource
	IProvGoodsHosMapper proHosGoodsInfo;
	public List<ProHosGoodsInfoVo> getProHosGoodsList(ProHosGoodsInfoVo proHosGoodsInfoVo){
		return mapper.getProHosGoodsList(proHosGoodsInfoVo);
	}
	public List<ProHosGoodsInfoVo>getProHosDeptGoodsList(ProHosGoodsInfoVo proHosGoodsInfoVo){
		return  mapper.getProHosDeptGoodsList(proHosGoodsInfoVo);
	}
	public List<ProvGoods4HosVo> getProGoods4HosList(ProvGoods4HosVo v){
		return mapper.getProGoods4HosList(v);
	}
	
}
