package gyqx.spdherp.provManager.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Size;
import java.util.Date;

@JsonIgnoreProperties
public class ProvSalesRepVo {
    /**
     * ID
     */
    @Size(max = 36)
    private String id;
    /**
     * 供应商ID
     */
    @Size(max = 36)
    private String provId;
    /**
     * 医院ID
     */
    @Size(max = 36)
    private String hosId;
    private String hosName;
    /**
     * 委托人
     */
    @Size(max = 36)
    private String salesName;
    private String phoneNum;
    /**
     * 附件路径
     */
    @Size(max = 256)
    private String filePath;
    /**
     * 有效开始日期
     */
    private Date expdtBeginDate;
    /**
     * 有效结束日期
     */
    private Date expdtEndDate;

    /**
     * 数据版本
     */
    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getHosId() {
        return hosId;
    }

    public void setHosId(String hosId) {
        this.hosId = hosId;
    }

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public Date getExpdtBeginDate() {
        return expdtBeginDate;
    }

    public void setExpdtBeginDate(Date expdtBeginDate) {
        this.expdtBeginDate = expdtBeginDate;
    }

    public Date getExpdtEndDate() {
        return expdtEndDate;
    }

    public void setExpdtEndDate(Date expdtEndDate) {
        this.expdtEndDate = expdtEndDate;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
