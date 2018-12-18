package gyqx.spdhdi.myInfos.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdhdi.myInfos.dao.mapper.IProvRegGoodsInfoQRMapper;
import gyqx.spdhdi.myInfos.vo.ProvRegGoodsInfoQ;
import gyqx.spdhdi.myInfos.vo.ProvRegGoodsInfoQR;

@Repository
public class ProvRegGoodsInfoQRDao 
{	
	@Resource
	private IProvRegGoodsInfoQRMapper mapper;
	
	public List<ProvRegGoodsInfoQR> list(ProvRegGoodsInfoQ qbean) throws Exception{		

		return mapper.list(qbean);
	}
}


