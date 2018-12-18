package gyqx.spdherp.quartz;

import common.utils.UtilsContext;
import gyqx.spdhdi.orderMgr.Constance;
import gyqx.spdhdi.orderMgr.service.IPurchaseService;
import gyqx.spdherp.stockPile.service.IStockPileService;
import gyqx.spdherp.stockPile.vo.DeptGoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AutoCreateBill {
    @Autowired
    private IPurchaseService purSer;
    @Autowired
    private IStockPileService stocPileSer;
    @Autowired
    private UtilsContext utilsContext;
    private Logger logger = LoggerFactory.getLogger(smpaaQuartz.class);
    public void autoCreatPurBill() {
        logger.info("####################开始库存不足补货任务（补采购单） start##############################");
        try {
            DeptGoodsVo query = new DeptGoodsVo();
            query.setHosId(utilsContext.getSysConfigUtil().getValue(Constance.SYS_CUR_HOS));//当前医院
            query.setDeptId(utilsContext.getSysConfigUtil().getValue(Constance.SYS_HOS_SBK));//设备自动补货
            List<DeptGoodsVo> lstGoodsStoc = stocPileSer.getGoodsInfo4AutoPur(query);
            purSer.creatPurBillByStoc(lstGoodsStoc);
        } catch (Exception e) {
            logger.error("执行定时任务出错", e);
        }
        logger.info("#####################结束库存不足补货任务（补采购单） end###############################");
    }
}
