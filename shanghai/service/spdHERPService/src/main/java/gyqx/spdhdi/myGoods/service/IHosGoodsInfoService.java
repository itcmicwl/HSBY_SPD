package gyqx.spdhdi.myGoods.service;

import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.baseData.vo.BasGoodsInfoVo;
import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdhdi.myGoods.vo.ProvGoods4HosVo;
import gyqx.spdhdi.po.HosGoodsInfo;


public interface IHosGoodsInfoService 
{
	HosGoodsInfo addHosGood(HosGoodsInfo good) throws Exception;
	
	HosGoodsInfo updateHosGood(HosGoodsInfo good) throws Exception;
	
	QueryResult<HosGoodsInfoVo> listHosGoods(QueryInfo<HosGoodsInfo> queryInfo) throws Exception;
	QueryResult<HosGoodsInfoVo> listHosGoods4CodeMgr(QueryInfo<HosGoodsInfoVo> queryInfo) throws Exception;

	
	void deleteHosGood(HosGoodsInfo good) throws Exception;
	void multDelHosGoods(List<HosGoodsInfo> goods) throws Exception;
	
	
	void addHosGoodS(List<HosGoodsInfo> goods) throws Exception;

	HosGoodsInfo convertBasGood2HosGood(BasGoodsInfoVo basGood, HosGoodsInfo hosGood);
	
	HosGoodsInfo convertPHGood2HosGood(ProvGoods4HosVo phGoodVo, HosGoodsInfo hosGood);
	
	List<HosGoodsInfo> addHosGoodS4ImportExcel(List<HosGoodsInfo> goods) throws Exception;

	HosGoodsInfoVo getHosGoodsInfoById(HosGoodsInfoVo good);

    Integer listHosGoodsCount4CodeMgr(QueryInfo<HosGoodsInfoVo> queryInfo);
    Integer updateChangeHosGoods(String newGoodsId,String oldGoodsId,String newErpCode) throws Exception;
}
