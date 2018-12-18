package gyqx.spdhdi.orderMgr.vo;

import java.io.Serializable;
import java.util.List;

public class DistrBillVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8192440857605012235L;
	/**
	 * 请购配送单
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
	private Integer status;
	/**
	 * 建档时间
	 */
	private java.util.Date fillDate;
	/**
	 * 数据版本
	 */
	private int version;
	private List<DistrBillListVo> distrBillList;
	private int[] arrStatus;
	private Integer purType;
	
	private String userId;
	private String hosId;
	
	
	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getFillter() {
		return fillter;
	}

	public void setFillter(String fillter) {
		this.fillter = fillter;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public java.util.Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(java.util.Date fillDate) {
		this.fillDate = fillDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<DistrBillListVo> getDistrBillList() {
		return distrBillList;
	}

	public void setDistrBillList(List<DistrBillListVo> distrBillList) {
		this.distrBillList = distrBillList;
	}

	public String getFillterName() {
		return fillterName;
	}

	public void setFillterName(String fillterName) {
		this.fillterName = fillterName;
	}

	public int[] getArrStatus() {
		return arrStatus;
	}

	public void setArrStatus(int[] arrStatus) {
		this.arrStatus = arrStatus;
	}

	public Integer getPurType() {
		return purType;
	}

	public void setPurType(Integer purType) {
		this.purType = purType;
	}
	@Override
	public String toString() {
		return "DistrBill [" + " id=" + id + ", billId=" + billId + ", spdCode=" + spdCode + ", purchaseCompanyId="
				+ purchaseCompanyId + ", purchaseCompanyCode=" + purchaseCompanyCode + ", purchaseCompanyName="
				+ purchaseCompanyName + ", purchaseCompanyErpCode=" + purchaseCompanyErpCode + ", applyOrgId="
				+ applyOrgId + ", applyOrgCode=" + applyOrgCode + ", applyOrgName=" + applyOrgName
				+ ", applyOrgScmCode=" + applyOrgScmCode + ", applyOrgErpCode=" + applyOrgErpCode + ", recieveOrgId="
				+ recieveOrgId + ", recieveOrgCode=" + recieveOrgCode + ", recieveOrgName=" + recieveOrgName
				+ ", recieveOrgScmCode=" + recieveOrgScmCode + ", recieveOrgErpCode=" + recieveOrgErpCode + ", provId="
				+ provId + ", provCode=" + provCode + ", provName=" + provName + ", provErpCode=" + provErpCode
				+ ", fillter=" + fillter + ", remark=" + remark + ", sumRow=" + sumRow + ", fillDate=" + fillDate
				+ ", version=" + version + "]";
	}
}
