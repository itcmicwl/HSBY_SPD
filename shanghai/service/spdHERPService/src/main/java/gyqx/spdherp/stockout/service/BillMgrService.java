package gyqx.spdherp.stockout.service;

import common.exception.ParameterException;
import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.po.OutStockUniqueCode;
import gyqx.spdherp.stockMgr.vo.InStockListVo;
import gyqx.spdherp.stockMgr.vo.InStockVo;
import gyqx.spdherp.stockout.vo.BigBatch4FillVo;
import gyqx.spdherp.stockout.vo.EntireOutStockListVo;
import gyqx.spdherp.stockout.vo.OutStockBillVo;
import gyqx.spdherp.stockout.vo.SysOrgVo;

import java.util.Date;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-8-29 上午11:16
 */
public interface BillMgrService {
    List<SysOrgVo> listSysOrg(String stockId, Boolean submitted);

    List<OutStockBillVo> listBill(String deptId, String stockId, Date beginDate, Date endDate, String status);

    OutStockBillVo getBillDetail(String id);

    Integer updateOutStock(OutStock outStock) throws Exception;

    /**
     * 补出库单，虚库出库补实库出库
     * @param uniqueCodeList 唯一码列表
     * @return 出库单唯一码子表列表
     * @throws ParameterException 传入唯一码列表与查询出库单唯一码子表数量不一致
     */
    List<OutStockUniqueCode> fillOutStockBill(List<String> uniqueCodeList) throws ParameterException;

    /**
     * 补出库单
     * @param uniqueCodeList 唯一码列表
     * @param fillType       1为虚库出库补实库出库，-1为补计费退货冲红单据
     * @param execDeptId     执行科室Id
     * @return 出库单唯一码子表列表
     * @throws ParameterException 传入唯一码列表与查询出库单唯一码子表数量不一致
     */
    List<OutStockUniqueCode> fillOutStockBill(List<String> uniqueCodeList, Integer fillType, String execDeptId) throws ParameterException;

    /**
     * <b>请在同一事务内回写出库单的入库数量后再调用此方法</b><br>
     * 根据入库单ID（类型：请购出库 40，状态：记帐 30/部分入库 69），统计出库单出库数量与入库数量比较，如全部出库，
     * 则修改出库单状态为“入库/60”，否则出库单状态改为“部分入库/69”
     * @param outStockId 出库单单号
     * @return 更新出库单状态是否成功，true, 成功；false, 失败
     */
    Boolean updateOutStockStatus(String outStockId) throws ParameterException;

    /**
     * 检查是否为手术包出库
     * @param id 出库单ID
     * @return 手术包出库返回true, 其它为false
     */
    boolean checkSurPkg(String id);

    /**
     * 补出库单
     * @param bigBatchInfos 大批次信息
     * @param fillType 1为虚库出库补实库出库，-1为补计费退货冲红单据
     * @param execDeptId 执行科室Id
     * @return 出库单子表列表
     */
    List<EntireOutStockListVo> fillOutStockBillByBigBatch(List<BigBatch4FillVo> bigBatchInfos, Integer fillType, String execDeptId) throws ParameterException;

    /**
     * 科室入库拒收商品时，出库相应商品数量改变，备注为原出库数量。
     * @param removeRowList 入库时传值，删除行信息
     * @return 修改成功为true, 否则为false
     */
    boolean updateOutStockByReturn(List<InStockListVo> removeRowList) throws Exception;

    boolean genReturnProvBillByInStock(InStockVo inStock);
}
