package gyqx.spdherp.quartz;

import gyqx.spdhdi.orderMgr.service.IPurconfirmService;
import gyqx.ws.yg.common.ImportSendBill;
import gyqx.ws.yg.service.IYgRecConfirmService;
import gyqx.ws.yg.service.IYgSendBillService;
import gyqx.ws.yg.vo.repVo.YY154_REP_DETAIL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 阳光采购平台相关定时任务
 * Created by moonb on 2017/12/26.
 */
public class smpaaQuartz {
    @Autowired
    private IPurconfirmService purconfirmService;
    @Autowired
    private ImportSendBill importSendBill;
    @Autowired
    private IYgRecConfirmService ygRecConSendBillSer;
    private Logger logger = LoggerFactory.getLogger(smpaaQuartz.class);
    public void sendPurToYgJob() {
        logger.info("####################开始上传采购单到阳光采购平台任务 start##############################");
        try {
            purconfirmService.sendPurToYg();
        } catch (Exception e) {
            logger.error("执行定时任务出错", e);
        }
        logger.info("#####################结束阳光平台上传任务 end###############################");
    }
    public void importSendBillFormYg() {
        logger.info("####################开始从阳光平台导入配送单数据任务 start##############################");
        try {
            importSendBill.autoImport();
        } catch (Exception e) {
            logger.error("执行定时任务出错", e);
        }
        logger.info("#####################结束从阳光平台导入配送单数据任务 end###############################");
    }
    public void autoRecSendBill() {
        logger.info("####################开始验收配送单任务 start##############################");
        try {
            ygRecConSendBillSer.autoRecSendBill();
        } catch (Exception e) {
            logger.error("执行定时任务出错", e);
        }
        logger.info("#####################结束验收配送单任务 end###############################");
    }
}
