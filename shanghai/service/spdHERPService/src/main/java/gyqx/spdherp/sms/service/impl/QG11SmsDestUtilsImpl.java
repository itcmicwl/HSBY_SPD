package gyqx.spdherp.sms.service.impl;

import common.utils.SysConfigUtil;
import gyqx.spdherp.applyMgr.vo.DeptBuyBillVo;
import gyqx.spdherp.sms.dao.mapper.SmsDestMapper;
import gyqx.spdherp.sms.service.SmsDestUtils;
import gyqx.spdherp.sms.vo.SmsInfoVo;
import gyqx.spdherp.sms.vo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 18-1-24 上午9:16
 */
@Component("sms_qg11")
public final class QG11SmsDestUtilsImpl extends SmsDestTemplate {
    private static final Logger logger = LoggerFactory.getLogger(QG11SmsDestUtilsImpl.class);

    @Autowired
    public QG11SmsDestUtilsImpl(SysConfigUtil sysConfigUtil, SmsDestMapper smsDestMapper) {
        super(sysConfigUtil, smsDestMapper);
    }

    @PostConstruct
    protected void getConfiguration() {
        signName = sysConfigUtil.getValue("sms.signName");
        templateCode = sysConfigUtil.getValue("sms.template.qg11");
    }

    @Override
    protected void setMsgInfo(String userId, String userDept, Object bill, Object[] params) {
        msgTitle = "您有1笔请购单需要审核，请及时查看";
        destUrl = "/applyMgr/purchaseAuditBuy";
        msg = "您有1笔请购订单需要处理。";
    }

    @Override
    protected List<UserInfo> getUserInfos(String userId, String userDept, Object bill, Object[] params) {
        int purMode;
        if (bill instanceof DeptBuyBillVo) {
            purMode = ((DeptBuyBillVo) bill).getPurMode();
            if (purMode == 0) {
                logger.error("采购模式错误");
                return Collections.emptyList();
            }
        } else {
            logger.error("订单错误");
            return Collections.emptyList();
        }

        // 设备科ID
        String equipDepartmentOrgId = sysConfigUtil.getValue("equipDepartmentOrgId");
        return smsDestMapper.findPhoneNumsQG11(purMode, equipDepartmentOrgId);
    }
}
