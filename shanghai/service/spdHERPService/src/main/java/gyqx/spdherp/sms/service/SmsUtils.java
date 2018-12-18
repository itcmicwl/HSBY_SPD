package gyqx.spdherp.sms.service;

import gyqx.spdherp.sms.vo.SmsInfoVo;
import gyqx.spdherp.sms.vo.UserInfo;
import org.slf4j.Logger;

import java.util.List;

/**
 * @Auther Liangwu
 * @Date 18-1-24 上午8:57
 */
public interface SmsUtils {
    Integer sendToMQ(String bizKind, String userId, String userDeptId, Object bill, Object... params);
}
