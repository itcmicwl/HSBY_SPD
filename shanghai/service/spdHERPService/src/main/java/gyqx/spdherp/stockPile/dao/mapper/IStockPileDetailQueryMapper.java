package gyqx.spdherp.stockPile.dao.mapper;

import java.util.List;
import gyqx.spdherp.stockPile.vo.StockPileDetailQueryVo;
 
public interface IStockPileDetailQueryMapper {

	List<StockPileDetailQueryVo> list(StockPileDetailQueryVo queryInfo) ;
}

