package gyqx.spdherp.stockout.service;


import common.utils.DictionaryUtils;
import gyqx.spdherp.stockout.vo.*;

import java.util.Date;
import java.util.List;

/**
 * @Auther ganwei
 * @Date 18-10-17 上午10:31
 */
public interface SurReturnOutService {

    String saveSurReturnBill(OutStockBillVo outStockBillVo) throws Exception;

}
