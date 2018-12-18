package gyqx.spdhdi.myInfos.service;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.myInfos.vo.ProvRegGoodsInfoQ;
import gyqx.spdhdi.myInfos.vo.ProvRegGoodsInfoQR;

public interface IProvRegGoodsInfoQRService 
{
	QueryResult<ProvRegGoodsInfoQR> listByPage(QueryInfo<ProvRegGoodsInfoQ> queryInfo) throws Exception;	
}


