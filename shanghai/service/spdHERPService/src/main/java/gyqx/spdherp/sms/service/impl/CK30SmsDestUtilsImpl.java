package gyqx.spdherp.sms.service.impl;

import common.db.SimpleDao;
import common.utils.SysConfigUtil;
import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.sms.dao.mapper.SmsDestMapper;
import gyqx.spdherp.sms.service.SmsDestUtils;
import gyqx.spdherp.sms.vo.SmsInfoVo;
import gyqx.spdherp.sms.vo.UserInfo;
import gyqx.spdherp.stockout.Constance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 18-1-24 上午9:18
 */
@Component("sms_ck30")
public final class CK30SmsDestUtilsImpl extends SmsDestTemplate {
    private static final Logger logger = LoggerFactory.getLogger(CK30SmsDestUtilsImpl.class);
    private SimpleDao simpleDao;

    @Autowired
    public CK30SmsDestUtilsImpl(SysConfigUtil sysConfigUtil, SmsDestMapper smsDestMapper, SimpleDao simpleDao) {
        super(sysConfigUtil, smsDestMapper);
        this.simpleDao = simpleDao;
    }

    @PostConstruct
    protected void getConfiguration() {
        signName = sysConfigUtil.getValue("sms.signName");
        templateCode = sysConfigUtil.getValue("sms.template.ck30");
    }

    @Override
    protected void setMsgInfo(String userId, String userDept, Object bill, Object[] params) {
        msgTitle = "您有1笔订单正在配送，请及时查看";
        destUrl = "/storeMgr/inByDeptApplyList";
        msg = "您有1笔配送订单需要处理。";
    }

    @Override
    protected List<UserInfo> getUserInfos(String userId, String userDept, Object bill, Object[] params) {
        // 获取入参出库单
        OutStock outStock;
        if (bill instanceof OutStock) {
            outStock = (OutStock) bill;
            if (outStock.getStatus() != Constance.OUTSTOCKBILL_STATUS_ACCOUNT) {
                return Collections.emptyList();
            }
        } else {
            logger.error("单据错误");
            return Collections.emptyList();
        }

        List<UserInfo> userInfoList = new ArrayList<>();
        if (outStock.getOutStockKind().equals(Constance.OUTSTOCK_KIND_REQUEST.toString())) {
            // 请购出库
            String buyMainFillter =  simpleDao.queryForObject("SELECT dbm.fillter FROM dept_buy_main dbm LEFT JOIN out_stock os ON ( os.source_bill_id = dbm.id ) WHERE os.id = ?", String.class, outStock.getId());
            userInfoList = smsDestMapper.findPhoneNumsCK30Req(buyMainFillter);
        } else if (outStock.getOutStockKind().equals(Constance.OUTSTOCK_KIND_CONSUME.toString())) {
            // 调拔出库
            String dstDept = outStock.getInDeptId();
            userInfoList = smsDestMapper.findPhoneNumsCK30Trans("护士长", dstDept);
        }

        return userInfoList;
    }
}
