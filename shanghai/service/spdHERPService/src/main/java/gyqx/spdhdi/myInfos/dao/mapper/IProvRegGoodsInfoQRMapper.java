package gyqx.spdhdi.myInfos.dao.mapper;

import java.util.List;

import gyqx.spdhdi.myInfos.vo.ProvRegGoodsInfoQ;
import gyqx.spdhdi.myInfos.vo.ProvRegGoodsInfoQR;

public interface IProvRegGoodsInfoQRMapper {

	List<ProvRegGoodsInfoQR> list(ProvRegGoodsInfoQ queryInfo) ;
}

