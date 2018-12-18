package gyqx.spdherp.sms.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.utils.SysConfigUtil;
import common.utils.message.MessageSender;
import gyqx.spdherp.sms.dao.mapper.SmsDestMapper;
import gyqx.spdherp.sms.service.SmsDestUtils;
import gyqx.spdherp.sms.vo.SmsInfoVo;
import gyqx.spdherp.sms.vo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class SmsDestTemplate implements SmsDestUtils {
    protected static final Logger logger = LoggerFactory.getLogger(SmsDestTemplate.class);
    protected static String signName = null;
    protected static String templateCode = null;
    protected static String msgTitle = null;
    protected static String destUrl = null;
    protected static String msg = null;

    protected SysConfigUtil sysConfigUtil;
    protected SmsDestMapper smsDestMapper;

    public SmsDestTemplate(SysConfigUtil sysConfigUtil, SmsDestMapper smsDestMapper) {
        this.sysConfigUtil = sysConfigUtil;
        this.smsDestMapper = smsDestMapper;
    }

    @Override
    public List<SmsInfoVo> getSmsInfo(String userId, String userDept, Object bill, Object... params) {
        if (signName == null || templateCode == null) {
            sysConfigUtil.refresh();
            getConfiguration();
            if (signName == null || templateCode == null) {
                logger.error("未找到短信签名或模板");
                return null;
            }
        }

        setMsgInfo(userId, userDept, bill, params);

        List<UserInfo> userInfoList = getUserInfos(userId, userDept, bill, params);

        return sendMsgAndGetSmsInfo(userId, msgTitle, destUrl, msg, signName, templateCode, userInfoList);
    }

    /**
     * 发送前端消息至指定人员，并返回短信Vo
     * @param userId 发送消息人员
     * @param msgTitle 消息标题
     * @param destUrl 消息中转Url
     * @param msg 消息体
     * @param signName 短信签名/大宇
     * @param templateCode 短信模板/大宇
     * @param userInfoList 消息及短信接收人员
     * @return 返回短信接收人员信息
     */
    protected List<SmsInfoVo> sendMsgAndGetSmsInfo(String userId,
                                                   String msgTitle,
                                                   String destUrl,
                                                   String msg,
                                                   String signName,
                                                   String templateCode,
                                                   List<UserInfo> userInfoList) {
        List<SmsInfoVo> smsInfoList = new ArrayList<>();
        for (UserInfo userInfo : userInfoList) {
            if (StringUtils.hasText(userInfo.getTel())) {
                smsInfoList.add(new SmsInfoVo(userInfo.getTel(), signName, templateCode));
            }
            try {
                MessageSender.send(msgTitle, msg, destUrl, userId, userInfo.getOrgId(), userInfo.getUserId());
            } catch (JsonProcessingException e) {
                logger.error("trans to JSON error");
                e.printStackTrace();
            }
        }
        return smsInfoList;
    }

    protected abstract void getConfiguration();

    protected abstract void setMsgInfo(String userId, String userDept, Object bill, Object[] params);

    protected abstract List<UserInfo> getUserInfos(String userId, String userDept, Object bill, Object[] params);
}

