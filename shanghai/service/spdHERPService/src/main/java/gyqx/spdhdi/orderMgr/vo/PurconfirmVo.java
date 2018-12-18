
package gyqx.spdhdi.orderMgr.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 采购确认单
 */
public class PurconfirmVo implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3948961884332224589L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 单号
	 */
	private String billId;
	/**
	 * SPD系统标识
	 */
	private String spdCode;
	private String purBillId;
	/**
	 * 请购机构
	 */
	private String purchaseCompanyId;

	/**
	 * 请购机构编号
	 */
	private String purchaseCompanyCode;
	/**
	 * 请购机构名称
	 */
	private String purchaseCompanyName;
	/**
	 * 请购机构ERP编号
	 */
	private String purchaseCompanyErpCode;
	/**
	 * 请购科室
	 */
	private String applyOrgId;
	/**
	 * 请购科室编号
	 */
	private String applyOrgCode;
	/**
	 * 请购科室名称
	 */
	private String applyOrgName;
	/**
	 * 请购科室SCM编号
	 */
	private String applyOrgScmCode;
	/**
	 * 请购科室ERP编号
	 */
	private String applyOrgErpCode;
	/**
	 * 收货科室
	 */
	private String recieveOrgId;
	/**
	 * 收货科室编号
	 */
	private String recieveOrgCode;
	/**
	 * 收货科室名称
	 */
	private String recieveOrgName;
	/**
	 * 收货科室SCM编号
	 */
	private String recieveOrgScmCode;
	/**
	 * 收货科室ERP编号
	 */
	private String recieveOrgErpCode;
	/**
	 * 配送机构
	 */
	private String provId;
	/**
	 * 配送机构编号
	 */
	private String provCode;
	/**
	 * 配送机构名称
	 */
	private String provName;
	/**
	 * 配送机构ERP编号
	 */
	private String provErpCode;
	/**
	 * 配送机构ERP部门编号
	 */
	private String provOrgCode;
	/**
	 * 配送机构系统标识
	 */
	private String provSystemId;
	/**
	 * 配送关系类型
	 */
	private String deliveryRelation;
	/**
	 * 结算配送机构
	 */
	private String balanceOrgId;
	/**
	 * 采购总金额
	 */
	private java.math.BigDecimal sumAmount;
	/**
	 * 采购不含税总金额
	 */
	private java.math.BigDecimal sumTaxAmount;
	/**
	 * 当前审批级别
	 */
	private Integer status;
	/**
	 * 制单人
	 */
	private String fillter;
	private String fillterName;
	/**
	 * 备注信息
	 */
	private String remark;
	/**
	 * 笔数
	 */
	private Integer sumRow;
	/**
	 * 建档时间
	 */
	private java.util.Date fillDate;
	/**
	 * 最后更新时间
	 */
	private java.util.Date lastUpdateDatetime;
	private Integer isTrans;

	/**
	 * 数据版本
	 */
	private int version;
	private List<PurconfirmListVo> lstDetail;
	/**
	 * 查询用
	 */
	private int[] arrStatus;
	private Integer purType = 10;
	/**
	 * 查询用
	 */
	private String[] arrBillIds;
	private String saleMan;
	private String ygBillId;
	private int isConfirm;
	private String recAddress;
	private java.util.Date lastArrivalDate;
	private String provYgCode;
	private String hosYgCode;
	public java.util.Date getLastArrivalDate() {
		return lastArrivalDate;
	}

	public void setLastArrivalDate(java.util.Date lastArrivalDate) {
		this.lastArrivalDate = lastArrivalDate;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getPurBillId() {
		return purBillId;
	}

	public void setPurBillId(String purBillId) {
		this.purBillId = purBillId;
	}

	public String getSpdCode() {
		return spdCode;
	}

	public void setSpdCode(String spdCode) {
		this.spdCode = spdCode;
	}

	public String getPurchaseCompanyId() {
		return purchaseCompanyId;
	}

	public void setPurchaseCompanyId(String purchaseCompanyId) {
		this.purchaseCompanyId = purchaseCompanyId;
	}

	public String getPurchaseCompanyCode() {
		return purchaseCompanyCode;
	}

	public void setPurchaseCompanyCode(String purchaseCompanyCode) {
		this.purchaseCompanyCode = purchaseCompanyCode;
	}

	public String getPurchaseCompanyName() {
		return purchaseCompanyName;
	}

	public void setPurchaseCompanyName(String purchaseCompanyName) {
		this.purchaseCompanyName = purchaseCompanyName;
	}

	public String getPurchaseCompanyErpCode() {
		return purchaseCompanyErpCode;
	}

	public void setPurchaseCompanyErpCode(String purchaseCompanyErpCode) {
		this.purchaseCompanyErpCode = purchaseCompanyErpCode;
	}

	public String getApplyOrgId() {
		return applyOrgId;
	}

	public void setApplyOrgId(String applyOrgId) {
		this.applyOrgId = applyOrgId;
	}

	public String getApplyOrgCode() {
		return applyOrgCode;
	}

	public void setApplyOrgCode(String applyOrgCode) {
		this.applyOrgCode = applyOrgCode;
	}

	public String getApplyOrgName() {
		return applyOrgName;
	}

	public void setApplyOrgName(String applyOrgName) {
		this.applyOrgName = applyOrgName;
	}

	public String getApplyOrgScmCode() {
		return applyOrgScmCode;
	}

	public void setApplyOrgScmCode(String applyOrgScmCode) {
		this.applyOrgScmCode = applyOrgScmCode;
	}

	public String getApplyOrgErpCode() {
		return applyOrgErpCode;
	}

	public void setApplyOrgErpCode(String applyOrgErpCode) {
		this.applyOrgErpCode = applyOrgErpCode;
	}

	public String getRecieveOrgId() {
		return recieveOrgId;
	}

	public void setRecieveOrgId(String recieveOrgId) {
		this.recieveOrgId = recieveOrgId;
	}

	public String getRecieveOrgCode() {
		return recieveOrgCode;
	}

	public void setRecieveOrgCode(String recieveOrgCode) {
		this.recieveOrgCode = recieveOrgCode;
	}

	public String getRecieveOrgName() {
		return recieveOrgName;
	}

	public void setRecieveOrgName(String recieveOrgName) {
		this.recieveOrgName = recieveOrgName;
	}

	public String getRecieveOrgScmCode() {
		return recieveOrgScmCode;
	}

	public void setRecieveOrgScmCode(String recieveOrgScmCode) {
		this.recieveOrgScmCode = recieveOrgScmCode;
	}

	public String getRecieveOrgErpCode() {
		return recieveOrgErpCode;
	}

	public void setRecieveOrgErpCode(String recieveOrgErpCode) {
		this.recieveOrgErpCode = recieveOrgErpCode;
	}

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getProvCode() {
		return provCode;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}

	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	public String getProvErpCode() {
		return provErpCode;
	}

	public void setProvErpCode(String provErpCode) {
		this.provErpCode = provErpCode;
	}

	public String getProvOrgCode() {
		return provOrgCode;
	}

	public void setProvOrgCode(String provOrgCode) {
		this.provOrgCode = provOrgCode;
	}

	public String getProvSystemId() {
		return provSystemId;
	}

	public void setProvSystemId(String provSystemId) {
		this.provSystemId = provSystemId;
	}

	public String getDeliveryRelation() {
		return deliveryRelation;
	}

	public void setDeliveryRelation(String deliveryRelation) {
		this.deliveryRelation = deliveryRelation;
	}

	public String getBalanceOrgId() {
		return balanceOrgId;
	}

	public void setBalanceOrgId(String balanceOrgId) {
		this.balanceOrgId = balanceOrgId;
	}

	public java.math.BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(java.math.BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	public java.math.BigDecimal getSumTaxAmount() {
		return sumTaxAmount;
	}

	public void setSumTaxAmount(java.math.BigDecimal sumTaxAmount) {
		this.sumTaxAmount = sumTaxAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFillter() {
		return fillter;
	}

	public void setFillter(String fillter) {
		this.fillter = fillter;
	}

	public String getFillterName() {
		return fillterName;
	}

	public void setFillterName(String fillterName) {
		this.fillterName = fillterName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSumRow() {
		return sumRow;
	}

	public void setSumRow(Integer sumRow) {
		this.sumRow = sumRow;
	}

	public java.util.Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(java.util.Date fillDate) {
		this.fillDate = fillDate;
	}

	public java.util.Date getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}

	public void setLastUpdateDatetime(java.util.Date lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<PurconfirmListVo> getLstDetail() {
		return lstDetail;
	}

	public int[] getArrStatus() {
		return arrStatus;
	}

	public void setArrStatus(int[] arrStatus) {
		this.arrStatus = arrStatus;
	}

	public void setLstDetail(List<PurconfirmListVo> lstDetail) {
		this.lstDetail = lstDetail;
	}

	public String[] getArrBillIds() {
		return arrBillIds;
	}

	public void setArrBillIds(String[] arrBillIds) {
		this.arrBillIds = arrBillIds;
	}

	@Override
	public String toString() {
		return "Purconfirm [" + " id=" + id + ", billId=" + billId + ", spdCode=" + spdCode + ", purchaseCompanyId="
				+ purchaseCompanyId + ", purchaseCompanyCode=" + purchaseCompanyCode + ", purchaseCompanyName="
				+ purchaseCompanyName + ", purchaseCompanyErpCode=" + purchaseCompanyErpCode + ", applyOrgId="
				+ applyOrgId + ", applyOrgCode=" + applyOrgCode + ", applyOrgName=" + applyOrgName
				+ ", applyOrgScmCode=" + applyOrgScmCode + ", applyOrgErpCode=" + applyOrgErpCode + ", recieveOrgId="
				+ recieveOrgId + ", recieveOrgCode=" + recieveOrgCode + ", recieveOrgName=" + recieveOrgName
				+ ", recieveOrgScmCode=" + recieveOrgScmCode + ", recieveOrgErpCode=" + recieveOrgErpCode + ", provId="
				+ provId + ", provCode=" + provCode + ", provName=" + provName + ", provErpCode=" + provErpCode
				+ ", provOrgCode=" + provOrgCode + ", provSystemId=" + provSystemId + ", deliveryRelation="
				+ deliveryRelation + ", balanceOrgId=" + balanceOrgId + ", sumAmount=" + sumAmount + ", sumTaxAmount="
				+ sumTaxAmount + ", status=" + status + ", fillter=" + fillter + ", remark=" + remark + ", sumRow="
				+ sumRow + ", fillDate=" + fillDate + ", lastUpdateDatetime=" + lastUpdateDatetime + ", version="
				+ version + "]";
	}

    public Integer getPurType() {
        return purType;
    }

    public void setPurType(Integer purType) {
        this.purType = purType;
    }

	public Integer getIsTrans() {
		return isTrans;
	}

	public void setIsTrans(Integer isTrans) {
		this.isTrans = isTrans;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		PurconfirmVo o = null;
		o = (PurconfirmVo) super.clone();
		return o;
	}

	public String getSaleMan() {
		return saleMan;
	}

	public void setSaleMan(String saleMan) {
		this.saleMan = saleMan;
	}

	public String getYgBillId() {
		return ygBillId;
	}

	public void setYgBillId(String ygBillId) {
		this.ygBillId = ygBillId;
	}

	public int getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(int isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getRecAddress() {
		return recAddress;
	}

	public void setRecAddress(String recAddress) {
		this.recAddress = recAddress;
	}

	public String getProvYgCode() {
		return provYgCode;
	}

	public void setProvYgCode(String provYgCode) {
		this.provYgCode = provYgCode;
	}

	public String getHosYgCode() {
		return hosYgCode;
	}

	public void setHosYgCode(String hosYgCode) {
		this.hosYgCode = hosYgCode;
	}
}