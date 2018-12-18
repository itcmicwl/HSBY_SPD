package gyqx.spdhdi.myInfos.dao.mapper;

import java.util.List;

import gyqx.spdhdi.myInfos.vo.ProvRegistGoodsPushQR;
import gyqx.spdhdi.myInfos.vo.ProvRegistInfoPushVo;
import gyqx.spdhdi.po.ProvRegistInfoPush;

public interface IProvRegistInfoPushMapper {
	
	int update(ProvRegistInfoPushVo provRegistInfoPushVo);

	List<ProvRegistInfoPush> list(ProvRegistInfoPush queryInfo);

	List<ProvRegistInfoPushVo> pushedRegCertsProvListByPage(ProvRegistInfoPushVo queryObject);

	List<ProvRegistInfoPushVo> pushedGoodsProvListByPage(ProvRegistInfoPushVo queryObject);

	List<ProvRegistGoodsPushQR> pushedGoodsList(ProvRegistInfoPushVo queryObject);

	void update(ProvRegistInfoPush bean);
}
