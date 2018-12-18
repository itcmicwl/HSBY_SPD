package gyqx.spdherp.stockout.service;


import common.utils.DictionaryUtils;
import gyqx.spdherp.forRiva.vo.ForRivaH03;
import gyqx.spdherp.stockout.vo.*;

import java.util.Date;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-8-16 上午10:31
 */
public interface RequestOutService {
    List<SysOrgVo> listSysOrg(String stockId);

    List<IdNameVo> listOwnStock();

    List<DeptBuyMainVo> listDeptBuyRequest(Date beginDate, Date endDate, String billId, String deptId, String stockId);

    List<DictionaryUtils.DictionaryValue> listDictValue(String dict);

    String saveOutStockBill(OutStockBillVo outStockBillVo) throws Exception;

    List<GoodsNoIdUniqueVo> listUniqueCode(String stockId, String buyKind, List<String> goodsIdList);

    List<DeptBuySubVo> listDeptBuyRequestDetail(String billId, String deptId, String stockId);
}
