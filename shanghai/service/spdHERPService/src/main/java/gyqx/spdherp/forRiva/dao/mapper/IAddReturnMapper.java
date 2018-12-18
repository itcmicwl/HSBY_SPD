package gyqx.spdherp.forRiva.dao.mapper;

import gyqx.spdherp.stockout.vo.SickerUserVo;
import org.apache.ibatis.annotations.Param;

public interface IAddReturnMapper {
	SickerUserVo getSickerUseById(@Param("id")String id);

	int stockpilebatch(SickerUserVo sickerUserVo);
}

