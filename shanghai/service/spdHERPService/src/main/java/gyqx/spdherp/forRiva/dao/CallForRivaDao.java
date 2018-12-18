package gyqx.spdherp.forRiva.dao;
import gyqx.spdherp.forRiva.dao.mapper.ICallForRivaMapper;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;

@Repository
public class CallForRivaDao
{
	@Resource
	private ICallForRivaMapper iCallForRivaMapper;

	public String getStocIdByEname(String hosId,String ename){
		return iCallForRivaMapper.getStocIdByEname(hosId,ename);
	}
}


