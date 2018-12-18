package gyqx.spdherp.sms.service.impl;

import gyqx.spdherp.applyMgr.Constance;
import gyqx.spdherp.applyMgr.vo.DeptBuyBillVo;
import gyqx.spdherp.sms.service.SmsDestUtils;
import gyqx.spdherp.sms.vo.SmsInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 18-1-24 上午9:16
 */
@Component("sms_qg1120")
public class QG1120SmsDestUtilsImpl implements SmsDestUtils {
    private static final Logger logger = LoggerFactory.getLogger(QG1120SmsDestUtilsImpl.class);
    @Resource
    private ApplicationContext applicationContext;

    @Override
    public List<SmsInfoVo> getSmsInfo(String userId, String userDept, Object bill, Object... params) {
        if (params.length > 0 && params[0] instanceof Integer) {
            Integer state = (Integer) params[0];
            if(state.equals(Constance.DEPTAPPLAY_MAIN_STATUS_AUDIT)){
                SmsDestUtils smsDestUtils = (SmsDestUtils) applicationContext.getBean("sms_qg11");
                return getSmsInfoVos(userId, userDept, bill, smsDestUtils, params);
            } else if (state.equals(Constance.DEPTAPPLAY_MAIN_STATUS_COMMITED)){
                SmsDestUtils smsDestUtils = (SmsDestUtils) applicationContext.getBean("sms_qg20");
                return getSmsInfoVos(userId, userDept, bill, smsDestUtils, params);
            }
        }
        logger.error("state参数错误");
        return null;
    }

    private List<SmsInfoVo> getSmsInfoVos(String userId, String userDept, Object bill, SmsDestUtils smsDestUtils, Object[] params) {
        if (bill == null && params.length >1 && params[1] instanceof List ) {
            List<SmsInfoVo> result = new ArrayList<>();
            ((List<DeptBuyBillVo>) params[1]).forEach(item -> result.addAll(smsDestUtils.getSmsInfo(userId, userDept, item, params)));
            return result;
        } else {
            return smsDestUtils.getSmsInfo(userId, userDept, bill, params);
        }
    }
}
