package gyqx.spdhdi.myGoods.service;

import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.baseData.vo.BasGoodsInfoVo;
import gyqx.spdhdi.myGoods.vo.ProvGoodsInfoVo;
import gyqx.spdhdi.po.ProvGoodsHos;
import gyqx.spdhdi.po.ProvGoodsInfo;


public interface IProvGoodsInfoService 
{
	public ProvGoodsInfo addProvGood(ProvGoodsInfo good) throws Exception;
	
	public ProvGoodsInfo updateProvGood(ProvGoodsInfo good) throws Exception;
	
	public QueryResult<ProvGoodsInfoVo> listProvGoods(QueryInfo<ProvGoodsInfo> queryInfo) throws Exception;
	
	public void deleteProvGood(ProvGoodsInfo good) throws Exception;	
	public int multDelProvGoods(List<ProvGoodsInfo> goods) throws Exception;	
	
	public ProvGoodsInfo convertBasGood2ProvGood(BasGoodsInfoVo basGoodVo, ProvGoodsInfo provGood) throws Exception;	
 
	public void addProvGoodS(List<ProvGoodsInfo> goods) throws Exception;
	public List<ProvGoodsInfo> addProvGoodS4ImportExcel(List<ProvGoodsInfo> goods) throws Exception;
	public QueryResult<ProvGoodsInfoVo> getProvGoodsInfoListByProvAndHos_Pages(QueryInfo<ProvGoodsHos> queryInfo) throws Exception;	

}
