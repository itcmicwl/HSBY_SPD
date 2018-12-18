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
 * @Date 18-1-24 上午9:17
 */
@Component("sms_qg20")
public final class QG20SmsDestUtilsImpl extends SmsDestTemplate {
    private static final Logger logger = LoggerFactory.getLogger(QG20SmsDestUtilsImpl.class);

    @Autowired
    public QG20SmsDestUtilsImpl(SysConfigUtil sysConfigUtil, SmsDestMapper smsDestMapper) {
        super(sysConfigUtil, smsDestMapper);
    }

    @PostConstruct
    protected void getConfiguration() {
        signName = sysConfigUtil.getValue("sms.signName");
        templateCode = sysConfigUtil.getValue("sms.template.qg20");
    }

    @Override
    protected void setMsgInfo(String userId, String userDept, Object bill, Object[] params) {
        msgTitle = "您有1笔请购单需要进行采购，请及时查看";
        destUrl = "/applyMgr/purByDeptBuy";
        msg = "您有1笔请购订单需要处理。";
    }

    @Override
    protected List<UserInfo> getUserInfos(String userId, String userDept, Object bill, Object[] params) {
        // 只有低值情况下，SPD驻点人员接收短信，其余情况不发送短信
        int purMode;
        if (bill instanceof DeptBuyBillVo) {
            purMode = ((DeptBuyBillVo) bill).getPurMode();
            if (purMode != 10) {
                logger.info("非低值，不发送短信");
                return Collections.emptyList();
            }
        } else {
            logger.error("单据错误");
            return Collections.emptyList();
        }

        // 设备科ID
        String equipDepartmentOrgId = sysConfigUtil.getValue("equipDepartmentOrgId");
        return smsDestMapper.findPhoneNumsQG20("SPD", equipDepartmentOrgId);
    }
}
