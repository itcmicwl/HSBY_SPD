package gyqx.spdherp.returnGoods.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.ExchangeGoodsList;
 
public interface IExchangeGoodsListMapper {
	int update(ExchangeGoodsList bean);
	int insert(ExchangeGoodsList bean);
	List<ExchangeGoodsList> list(ExchangeGoodsList queryInfo) ;
}

