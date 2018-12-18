package gyqx.spdherp.forRiva.dao;
import gyqx.spdhdi.orderMgr.vo.DistrBillListVo;
import gyqx.spdherp.forRiva.dao.mapper.IPrintMapper;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;

@Repository
public class PrintForRivaDao
{
	@Resource
	private IPrintMapper iPrintMapper;
	public String receiveJfCode(String goodsId,String hosId){
		return iPrintMapper.receiveJfCodeById(goodsId,hosId);
	}

	public DistrBillListVo getById(String id){
		return iPrintMapper.getById(id);
	}
}


