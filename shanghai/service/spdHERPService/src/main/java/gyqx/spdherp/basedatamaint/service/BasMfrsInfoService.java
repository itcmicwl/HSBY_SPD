package gyqx.spdherp.basedatamaint.service;

import common.db.query.QueryResult;
import common.utils.DictionaryUtils;
import gyqx.spdhdi.po.BasMfrsInfo;
import gyqx.spdherp.basedatamaint.vo.BasMfrsInfoVo;

import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-8-14 下午3:58
 */
public interface BasMfrsInfoService {
    List<DictionaryUtils.DictionaryValue> listCjlxDict();

    QueryResult<BasMfrsInfoVo> listBasMfrsInfo(String mfrsName, String mfrsKind, int pageNum, int pageSize);

    String saveBasMfrsInfo(BasMfrsInfo basMfrsInfo) throws Exception;

    Integer updateBasMfrsInfo(BasMfrsInfo basMfrsInfo) throws Exception;

    String removeBasMfrsInfo(String id, int version) throws Exception;
}
