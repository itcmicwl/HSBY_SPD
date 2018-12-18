package gyqx.spdherp.sms.service.impl;

import common.utils.SysConfigUtil;
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
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 18-1-24 上午9:16
 */
@Component("sms_qg10")
public final class QG10SmsDestUtilsImpl extends SmsDestTemplate {
    private static final Logger logger = LoggerFactory.getLogger(QG10SmsDestUtilsImpl.class);

    @Autowired
    public QG10SmsDestUtilsImpl(SysConfigUtil sysConfigUtil, SmsDestMapper smsDestMapper) {
        super(sysConfigUtil, smsDestMapper);
    }

    @PostConstruct
    protected void getConfiguration() {
        signName = sysConfigUtil.getValue("sms.signName");
        templateCode = sysConfigUtil.getValue("sms.template.qg10");
    }

    @Override
    protected void setMsgInfo(String userId, String userDept, Object bill, Object[] params) {
        msgTitle = "您有1笔请购单需要审核，请及时查看";
        destUrl = "/applyMgr/purchaseAudit";
        msg = "您有1笔请购订单需要处理。";
    }

    @Override
    protected List<UserInfo> getUserInfos(String userId, String userDept, Object bill, Object[] params) {
        return smsDestMapper.findPhoneNumsQG10("护士长", userDept);
    }
}
