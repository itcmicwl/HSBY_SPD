package gyqx.spdhdi.myInfos.dao.mapper;

import java.util.List;

import gyqx.spdhdi.myInfos.vo.ProvRegistGoodsPushQ;
import gyqx.spdhdi.myInfos.vo.ProvRegistGoodsPushQR;
import gyqx.spdhdi.po.ProvRegistGoodsPush;

public interface IProvRegistGoodsPushMapper {
	
	int update(ProvRegistGoodsPush bean);

	List<ProvRegistGoodsPush> list(ProvRegistGoodsPush queryInfo);

	List<ProvRegistGoodsPushQR> pushedRegCertsList(ProvRegistGoodsPushQ queryInfo);
}
