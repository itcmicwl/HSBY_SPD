package gyqx.spdherp.sms.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.utils.jms.JMSSendUtils;
import common.utils.json.Json;
import gyqx.spdherp.sms.service.SmsDestUtils;
import gyqx.spdherp.sms.service.SmsUtils;
import gyqx.spdherp.sms.vo.SmsInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther Liangwu
 * @Date 18-1-24 上午8:57
 */
@Service
public class SimpleSmsUtilsImpl implements SmsUtils {
    private static final Logger logger = LoggerFactory.getLogger(SimpleSmsUtilsImpl.class);

    private final ApplicationContext applicationContext;
    private final JMSSendUtils jmsSendUtils;

    @Autowired
    public SimpleSmsUtilsImpl(ApplicationContext applicationContext, JMSSendUtils jmsSendUtils) {
        this.applicationContext = applicationContext;
        this.jmsSendUtils = jmsSendUtils;
    }

    @Override
    public Integer sendToMQ(String bizKind, String userId, String userDeptId, Object bill, Object... params) {
        SmsDestUtils smsDestUtils = (SmsDestUtils) applicationContext.getBean("sms_" + bizKind.toLowerCase());
        List<SmsInfoVo> smsInfoList = smsDestUtils.getSmsInfo(userId, userDeptId, bill, params);
        if (smsInfoList == null || smsInfoList.size() == 0) {
            logger.info("bizKind: " + bizKind + "，未找到相关联系人。");
            return 0;
        }

        smsInfoList.forEach(smsInfo -> {
            try {
                String smsInfoJson = Json.write(smsInfo);
                jmsSendUtils.send("sms", smsInfoJson, null);
            } catch (JsonProcessingException e) {
                logger.error("Can't transfer " + smsInfo.toString() + " to JsonString.");
            }
        });

        return smsInfoList.size();
    }
}
