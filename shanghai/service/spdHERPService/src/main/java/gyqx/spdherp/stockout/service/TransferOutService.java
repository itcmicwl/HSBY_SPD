package gyqx.spdherp.stockout.service;

import common.db.query.QueryResult;
import gyqx.spdherp.stockout.vo.IdNameVo;
import gyqx.spdherp.stockout.vo.StockpileHzVo;

import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-9-8 下午3:32
 */
public interface TransferOutService {
    List<IdNameVo> listOrgs();
}
