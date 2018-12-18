package gyqx.spdherp.provManager.service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.myInfos.vo.CompanyInfoVo;
import gyqx.spdhdi.myInfos.vo.ProvRegistInfoPushVo;
import gyqx.spdherp.provManager.vo.ProvHosInfoVo;

public interface INewProvInfoVoService {

	QueryResult<ProvHosInfoVo> getNewProvInfoVoList(QueryInfo<ProvHosInfoVo> queryInfo) throws Exception;

	CompanyInfoVo getProvCompanyInfo(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception;

}