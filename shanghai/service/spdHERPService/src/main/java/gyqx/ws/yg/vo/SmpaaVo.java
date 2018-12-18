package gyqx.ws.yg.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by moonb on 2017/11/21.
 */
@Component
@PropertySource(value = "classpath:properties/yg.properties", ignoreResourceNotFound = true)
public class SmpaaVo implements Cloneable{
    @Value("${yg.yyUser}")
    private String user;
    private String pwd;
    private String jgbm;
    private String version ="1.0.0.0";
    private String xxlx;
    private String sign;
    private String xmlData;
    private String ygUrl;
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    @Value("${yg.yyPwd}")
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getJgbm() {
        return jgbm;
    }

    @Value("${yg.yyJgbm}")
    public void setJgbm(String jgbm) {
        this.jgbm = jgbm;
    }

    public String getVersion() {
        return version;
    }

    @Value("${yg.yyVersion}")
    public void setVersion(String version) {
        this.version = version;
    }

    public String getXxlx() {
        return xxlx;
    }

    public void setXxlx(String xxlx) {
        this.xxlx = xxlx;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getXmlData() {
        return xmlData;
    }

    public void setXmlData(String xmlData) {
        this.xmlData = xmlData;
    }

    public String getYgUrl() {
        return ygUrl;
    }
    @Value("${yg.yyWsdlUrl}")
    public void setYgUrl(String ygUrl) {
        this.ygUrl = ygUrl;
    }

}
