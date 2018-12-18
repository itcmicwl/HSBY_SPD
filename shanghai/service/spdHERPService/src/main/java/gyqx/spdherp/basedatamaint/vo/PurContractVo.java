
package gyqx.spdherp.basedatamaint.vo;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 合同信息
 */
@Table(name = "pur_contract")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurContractVo implements IBean {
    /**
     * ID
     */
    @Id
    @Size(max = 36)
    @Column(name = "id")
    @Title("ID")
    private String id;
    /**
     * 合同编号
     */
    @Size(max = 50)
    @Column(name = "contract_code")
    @Title("合同编号")
    private String contractCode;
    /**
     * 合同名称
     */
    @Size(max = 200)
    @Column(name = "contract_name")
    @Title("合同名称")
    private String contractName;
    /**
     * 供应商ID
     */
    @Size(max = 36)
    @Column(name = "prov_id")
    @Title("供应商ID")
    private String provId;
    private String provName;
    /**
     * 合同签订日期
     */
    @Column(name = "sign_date")
    @Title("合同签订日期")
    private java.util.Date signDate;
    /**
     * 合同生效日期
     */
    @Column(name = "begin_date")
    @Title("合同生效日期")
    private java.util.Date beginDate;
    /**
     * 截止日期
     */
    @Column(name = "end_date")
    @Title("截止日期")
    private java.util.Date endDate;
    /**
     * 附件
     */
    @Size(max = 500)
    @Column(name = "attachment_path")
    @Title("附件")
    private String attachmentPath;
    /**
     * 数据版本
     */
    @Column(name = "version")
    @Title("数据版本")
    private int version;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }
}