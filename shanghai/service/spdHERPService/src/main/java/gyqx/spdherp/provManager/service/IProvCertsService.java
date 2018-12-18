package gyqx.spdherp.provManager.service;

import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.myInfos.vo.BasMfrsInfoVo;
import gyqx.spdhdi.myInfos.vo.ProvFrwtsVoR;
import gyqx.spdhdi.myInfos.vo.ProvJxsqsVo;
import gyqx.spdhdi.myInfos.vo.ProvRegistInfoPushVo;
import gyqx.spdhdi.po.ProvCns;
import gyqx.spdherp.provManager.vo.OutdateCertsImgVo;
import gyqx.spdherp.provManager.vo.OutdateCertsVo;
import gyqx.spdherp.provManager.vo.ProvIncorruptAgrmtVo;
import gyqx.spdherp.provManager.vo.ProvSalesRepVo;

public interface IProvCertsService {
	
	QueryResult<OutdateCertsVo> queryProvOutdateCerts(QueryInfo<OutdateCertsVo> queryInfo) throws Exception;

	List<OutdateCertsImgVo> queryOutdateCertsImg(OutdateCertsVo outdateCertsVo) throws Exception;

	QueryResult<ProvRegistInfoPushVo> getPushedRegCertsList(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception;

	QueryResult<BasMfrsInfoVo> getBasMfrsList(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception;

	QueryResult<ProvJxsqsVo> jxsqsList(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception;

	QueryResult<ProvFrwtsVoR> wtsList(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception;

	QueryResult<ProvCns> cnsList(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception;

	QueryResult<ProvSalesRepVo> salesRepList(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception;

	QueryResult<ProvIncorruptAgrmtVo> listIncorruptAgrmt(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception;
	
}
