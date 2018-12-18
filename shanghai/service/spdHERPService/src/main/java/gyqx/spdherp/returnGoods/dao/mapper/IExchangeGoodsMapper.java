package gyqx.spdherp.returnGoods.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.ExchangeGoods;
 
public interface IExchangeGoodsMapper {
	int update(ExchangeGoods bean);
	int insert(ExchangeGoods bean);
	List<ExchangeGoods> list(ExchangeGoods queryInfo) ;
}

