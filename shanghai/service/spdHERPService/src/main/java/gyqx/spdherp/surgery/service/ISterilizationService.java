package gyqx.spdherp.surgery.service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.surgery.vo.SurgeryPkgListVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;

import java.util.List;

public interface ISterilizationService {

    QueryResult<SurgeryPkgVo> listFirstPkgByPage(QueryInfo<SurgeryPkgVo> queryInfo) throws Exception;

    QueryResult<SurgeryPkgVo> listFirstExpireByPage(QueryInfo<SurgeryPkgVo> queryInfo) throws Exception;

    QueryResult<SurgeryPkgVo> listSecPkgByPage(QueryInfo<SurgeryPkgVo> queryInfo) throws Exception;

    QueryResult<SurgeryPkgVo> listSecExpire(QueryInfo<SurgeryPkgVo> queryInfo);

    QueryResult<SurgeryPkgListVo> getNotFirstPkgListBySurCode(QueryInfo<SurgeryPkgVo> queryInfo);

    QueryResult<SurgeryPkgListVo> getNotSecPkgListBySurCode(QueryInfo<SurgeryPkgVo> queryInfo);

    int updateFirstSterialization(SurgeryPkgVo surgeryPkgVo);

    int updateSecSterialization(SurgeryPkgVo surgeryPkgVo);
}
