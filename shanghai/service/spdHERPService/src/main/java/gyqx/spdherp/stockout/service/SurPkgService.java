package gyqx.spdherp.stockout.service;

import common.db.query.QueryResult;
import common.exception.ParameterException;
import gyqx.spdherp.stockout.vo.SurPkgVo;

import java.util.List;

/**
 * @Author liangwu
 * @Date 18-10-8 下午2:16
 */
public interface SurPkgService {
    QueryResult<SurPkgVo> listSurPkg(String stockId, String deptId, int pageNum, int pageSize);

    SurPkgVo getSurPkg(String pkgCode);

    int saveOutStockBill(List<String> pkgCodeList) throws Exception;
}
