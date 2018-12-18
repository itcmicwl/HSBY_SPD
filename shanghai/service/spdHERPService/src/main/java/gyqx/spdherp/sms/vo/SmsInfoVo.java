package gyqx.spdherp.sms.vo;

/**
 * @Auther Liangwu
 * @Date 18-1-24 上午9:01
 */
public class SmsInfoVo {
    private String phoneNum;
    private String signName;
    private String templateCode;
    private String templateParam;

    public SmsInfoVo(String phoneNum, String signName, String templateCode) {
        this(phoneNum, signName, templateCode, null);
    }

    public SmsInfoVo(String phoneNum, String signName, String templateCode, String templateParam) {
        this.phoneNum = phoneNum;
        this.signName = signName;
        this.templateCode = templateCode;
        this.templateParam = templateParam;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }

    @Override
    public String toString() {
        return "SmsInfoVo{" +
                "phoneNum='" + phoneNum + '\'' +
                ", signName='" + signName + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", templateParam='" + templateParam + '\'' +
                '}';
    }
}
