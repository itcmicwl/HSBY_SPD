package gyqx.spdherp.basedatamaint.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasMfrsInfoVo {
    /**
     * ID
     */
    @Id
    @Size(max = 36)
    @Column(name = "id")
    @Title("ID")
    private String id;
    /**
     * 生产厂家类型
     * <p>
     * 生产厂家类型
     */
    @Size(max = 36)
    @Column(name = "mfrs_kind")
    @Title("生产厂家类型")
    private String mfrsKind;
    /**
     * 厂家名称
     */
    @Size(max = 300)
    @Column(name = "mfrs_name")
    @Title("厂家名称")
    private String mfrsName;
    /**
     * 全国总代理
     */
    @Size(max = 36)
    @Column(name = "agent_name")
    @Title("全国总代理")
    private String agentName;
    /**
     * 营业执照注册号
     */
    @Size(max = 36)
    @Column(name = "reg_code")
    @Title("营业执照注册号")
    private String regCode;
    /**
     * 经营许可证编号
     */
    @Size(max = 36)
    @Column(name = "jyxk_code")
    @Title("经营许可证编号")
    private String jyxkCode;
    /**
     * 纳税人识别号
     */
    @Size(max = 36)
    @Column(name = "nsrsb_code")
    @Title("纳税人识别号")
    private String nsrsbCode;
    /**
     * 生产许可证
     */
    @Size(max = 36)
    @Column(name = "product_code")
    @Title("生产许可证")
    private String productCode;
    /**
     * 备注信息
     */
    @Size(max = 128)
    @Column(name = "remark")
    @Title("备注信息")
    private String remark;
    /**
     * 是否三证合一
     */
    @Size(max = 1)
    @Column(name = "is_three_in_one")
    @Title("是否三证合一")
    private String isThreeInOne;
    /**
     * 统一码
     */
    @Size(max = 128)
    @Column(name = "master_code")
    @Title("统一码")
    private String masterCode;
    /**
     * 标志
     */
    @Size(max = 1)
    @Column(name = "flag")
    @Title("标志")
    private String flag;
    /**
     * 数据版本
     */
    @Column(name = "version")
    @Title("数据版本")
    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMfrsKind() {
        return mfrsKind;
    }

    public void setMfrsKind(String mfrsKind) {
        this.mfrsKind = mfrsKind == null ? null : mfrsKind.trim();
    }

    public String getMfrsName() {
        return mfrsName;
    }

    public void setMfrsName(String mfrsName) {
        this.mfrsName = mfrsName == null ? null : mfrsName.trim();
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode == null ? null : regCode.trim();
    }

    public String getJyxkCode() {
        return jyxkCode;
    }

    public void setJyxkCode(String jyxkCode) {
        this.jyxkCode = jyxkCode == null ? null : jyxkCode.trim();
    }

    public String getNsrsbCode() {
        return nsrsbCode;
    }

    public void setNsrsbCode(String nsrsbCode) {
        this.nsrsbCode = nsrsbCode == null ? null : nsrsbCode.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIsThreeInOne() {
        return isThreeInOne;
    }

    public void setIsThreeInOne(String isThreeInOne) {
        this.isThreeInOne = isThreeInOne == null ? null : isThreeInOne.trim();
    }

    public String getMasterCode() {
        return masterCode;
    }

    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode == null ? null : masterCode.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}