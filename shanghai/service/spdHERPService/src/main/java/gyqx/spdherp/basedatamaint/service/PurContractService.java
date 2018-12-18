package gyqx.spdherp.basedatamaint.service;

import common.db.query.QueryResult;
import gyqx.spdherp.basedatamaint.vo.PurContractVo;
import gyqx.spdherp.po.PurContract;

import java.util.Date;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-8-16 下午6:06
 */
public interface PurContractService {
    QueryResult<PurContractVo> listPurContract(String searchText, Date signDateBegin, Date signDateEnd, int pageNum, int pageSize);

    List<PurContractVo> listProv(String provName);

    String savePurContract(PurContract purContract) throws Exception;

    Integer updatePurContract(PurContract purContract) throws Exception;

    String deletePurContract(String id, int version) throws Exception;
}
