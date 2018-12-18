package gyqx.spdhdi.myGoods.service;

import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.myGoods.vo.ProHosGoodsInfoVo;
import gyqx.spdhdi.myGoods.vo.ProvGoods4HosVo;
import gyqx.spdhdi.myGoods.vo.ProvGoodsInfoVo;
import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdhdi.po.ProvGoodsHos;


public interface IProvGoodsHosService 
{
	/*public BasGoodsInfo addBasGood(BasGoodsInfo good) throws Exception;
	
	
	
	public QueryResult<BasGoodsInfoVo> listBasGoods(QueryInfo<BasGoodsInfoVo> queryInfo) throws Exception;
	*/
	public ProvGoodsHos updateProvGoodsHos(ProvGoodsHos good) throws Exception;
	public void delProvGoodsHosLogical(ProvGoodsHos good) throws Exception;
	public void delProvGoodsHosPhysical(ProvGoodsHos good) throws Exception;
	public void multDelProvGoodsHos(List<ProvGoodsHos> goods) throws Exception;	
	

	QueryResult<ProHosGoodsInfoVo> getProHosGoodsList(QueryInfo<ProHosGoodsInfoVo> queryInfo)  throws Exception;
	QueryResult<ProHosGoodsInfoVo> getProHosDeptGoodsList(QueryInfo<ProHosGoodsInfoVo> queryInfo)  throws Exception;
	
	public void addProvGoodsHosList(List<ProvGoodsHos> goods) throws Exception;

	public ProvGoodsHos convertProvGood2PGH(ProvGoodsInfoVo basGood,ProvGoodsHos hosGood);
	public ProvGoodsHos getProvGoodsHosById(String id) throws Exception;
	QueryResult<ProvGoods4HosVo> getProGoods4HosList(QueryInfo<ProvGoods4HosVo> queryInfo )throws Exception;
}
