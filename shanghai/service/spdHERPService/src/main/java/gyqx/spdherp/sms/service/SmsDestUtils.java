package gyqx.spdherp.sms.service;

import gyqx.spdherp.sms.vo.SmsInfoVo;

import java.util.List;

/**
 * @Auther Liangwu
 * @Date 18-1-24 上午9:15
 */
public interface SmsDestUtils {
    List<SmsInfoVo> getSmsInfo(String userId, String userDept, Object bill, Object... params);
}
