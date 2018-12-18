package gyqx.spdherp.sms.service.impl;

import common.utils.SysConfigUtil;
import gyqx.spdherp.sms.dao.mapper.SmsDestMapper;
import gyqx.spdherp.sms.service.SmsDestUtils;
import gyqx.spdherp.sms.vo.SmsInfoVo;
import gyqx.spdherp.sms.vo.UserInfo;
import gyqx.spdherp.stockout.vo.OutStockBillVo;
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
@Component("sms_ck10")
public final class CK10SmsDestUtilsImpl extends SmsDestTemplate {
    private static final Logger logger = LoggerFactory.getLogger(CK10SmsDestUtilsImpl.class);

    @Autowired
    public CK10SmsDestUtilsImpl(SysConfigUtil sysConfigUtil, SmsDestMapper smsDestMapper) {
        super(sysConfigUtil, smsDestMapper);
    }

    @PostConstruct
    protected void getConfiguration() {
        signName = sysConfigUtil.getValue("sms.signName");
        templateCode = sysConfigUtil.getValue("sms.template.ck10");
    }

    @Override
    protected void setMsgInfo(String userId, String userDept, Object bill, Object[] params) {
        msgTitle = "您有1笔出库单需要审核，请及时查看";
        destUrl = "/storeMgr/billMgrHos";
        msg = "您有1笔出库单需要处理。";
    }

    @Override
    protected List<UserInfo> getUserInfos(String userId, String userDept, Object bill, Object[] params) {
        Integer outStockType;
        if (bill instanceof OutStockBillVo) {
            outStockType = ((OutStockBillVo) bill).getOutStockType();
            if (outStockType == 0) {
                logger.error("出库类型错误");
                return Collections.emptyList();
            }
        } else {
            logger.error("单据错误");
            return Collections.emptyList();
        }

        // 设备科ID
        String equipDepartmentOrgId = sysConfigUtil.getValue("equipDepartmentOrgId");

        return smsDestMapper.findPhoneNumsCK10(outStockType, equipDepartmentOrgId);
    }

}
