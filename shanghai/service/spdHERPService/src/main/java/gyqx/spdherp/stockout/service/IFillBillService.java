package gyqx.spdherp.stockout.service;

import gyqx.spdherp.stockout.vo.BigBatch4FillVo;
import gyqx.spdherp.stockout.vo.SickerUserListVo;
import gyqx.spdherp.stockout.vo.SickerUserVo;

import java.util.List;

/**
 * Created by moonb on 2018/5/14.
 */
public interface IFillBillService {
    void createFillBillBySickerUse(SickerUserVo sickerUserVo) throws Exception;
    void createFillBillByUniqueCode(List<String> uniqueCode,Integer flag) throws Exception;
    void createFillBillByUniqueCode(List<String> uniqueCode, List<BigBatch4FillVo> bigBatchInfos, Integer flag, String execDeptIt) throws Exception;
}
