package gyqx.spdherp.spdOutPtl.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.JdeGoodsStockInfo;
 
public interface IJdeGoodsStockInfoMapper {
	int update(JdeGoodsStockInfo bean);
	int insert(JdeGoodsStockInfo bean);
	List<JdeGoodsStockInfo> list(JdeGoodsStockInfo queryInfo) ;
}

