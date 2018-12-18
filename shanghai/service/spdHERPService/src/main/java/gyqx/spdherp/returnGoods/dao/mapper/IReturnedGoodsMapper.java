package gyqx.spdherp.returnGoods.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.ReturnedGoods;
 
public interface IReturnedGoodsMapper {
	int update(ReturnedGoods bean);
	int insert(ReturnedGoods bean);
	List<ReturnedGoods> list(ReturnedGoods queryInfo) ;
}

