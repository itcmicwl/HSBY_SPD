package gyqx.spdherp.forRiva.service;

import gyqx.spdherp.stockout.vo.SickerUserVo;

public interface IAddReturnService {

	SickerUserVo getSickerUseResult(String id) throws Exception;

	int stockpilebatch(SickerUserVo sickerUserVo) throws Exception;

	int addSickerUse(String billId) throws Exception;
}
