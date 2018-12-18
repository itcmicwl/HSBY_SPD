package gyqx.spdherp.stockPile.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import gyqx.spdherp.stockPile.dao.mapper.IStockPileDetailQueryMapper;
import gyqx.spdherp.stockPile.vo.StockPileDetailQueryVo;

@Repository
public class StockPileDetailQueryDao 
{	
	@Resource
	private IStockPileDetailQueryMapper mapper;
	
	public List<StockPileDetailQueryVo> list(StockPileDetailQueryVo qbean) throws Exception{
		return mapper.list(qbean);
	}
}


