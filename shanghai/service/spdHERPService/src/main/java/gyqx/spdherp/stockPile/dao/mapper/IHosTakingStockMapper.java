package gyqx.spdherp.stockPile.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.HosTakingStock;
 
public interface IHosTakingStockMapper {
	int update(HosTakingStock bean);
	int insert(HosTakingStock bean);
	List<HosTakingStock> list(HosTakingStock queryInfo) ;
}

