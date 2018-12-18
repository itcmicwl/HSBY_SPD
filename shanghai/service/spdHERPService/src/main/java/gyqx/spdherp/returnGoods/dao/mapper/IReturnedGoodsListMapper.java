package gyqx.spdherp.returnGoods.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.ReturnedGoodsList;
 
public interface IReturnedGoodsListMapper {
	int update(ReturnedGoodsList bean);
	int insert(ReturnedGoodsList bean);
	List<ReturnedGoodsList> list(ReturnedGoodsList queryInfo) ;
}

