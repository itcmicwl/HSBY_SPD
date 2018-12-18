package gyqx.spdhdi.orderMgr.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
public class PurchaseVo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 7733254237560544503L;
	/**
	 *
	 */

	private String id;
	private String billId;
	/**
	 * 请购机构
	 */
	private String purchaseComId;
	private String purchaseComName;
	public String getPurDeptId() {
		return purDeptId;
	}
	public void setPurDeptId(String purDeptId) {
		this.purDeptId = purDeptId;
	}
	/**
	 * 请购科室
	 */
	private String purDeptId;
	/**
	 * 收货科室
	 */
	private String recOrgId;
	private String recOrgCode;
	private String recOrgName;
	/**
	 * 配送机构
	 */
	private String provId;
	private String provName;

	/**
	 * 二级供应商
	 */
	private String subProvId;
	private String subProvName;
	/**
	 * 配送关系类型
	 */
	private String distrRelation;
	/**
	 * 制单人
	 */
	private String fillter;
	private String fillterName;
	private String remark;
	/**
	 * 当前状态
	 */
	private Integer status = 0;
	/**
	 * 当前审核人
	 */
	private String autitorId;
	private String autitorName;
	/**
	 * 最终审批通过笔数
	 */
	private Integer autitorSumRow;
	private Integer sumRow;
	private BigDecimal sumAmount;
	private BigDecimal sumTaxAmount;
	private Integer purKind;
	private Integer purType = 10;		//采购模式默认为10
	private Date sDate;//开始时间
	private Date eDate;//结束日期

	public Integer getPurKind() {
		return purKind;
	}
	public void setPurKind(Integer purKind) {
		this.purKind = purKind;
	}
	public BigDecimal getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}
	public BigDecimal getSumTaxAmount() {
		return sumTaxAmount;
	}
	public void setSumTaxAmount(BigDecimal sumTaxAmount) {
		this.sumTaxAmount = sumTaxAmount;
	}
	public Date getsDate() {
		return sDate;
	}
	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}
	public Date geteDate() {
		return eDate;
	}
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	private Date fillDate;
	private Date lastUpdateDt;
	private Integer version;
	private Integer flag;
	private String purComCode;
	private String purComErpCode;
	private String purOrgCode;
	private String purOrgCname;
	private String purOrgErpCode;
	private String purOrgScmCode;
	private String recOrgErpCode;
	private String recOrgScmCode;
	private String provCode;
	private String provErpCode;
	private String subProvCode;
	private String subProvErpCode;
	private int[] arrStatus;
	private String closingReason;
	public String getClosingReason() {
		return closingReason;
	}
	public void setClosingReason(String closingReason) {
		this.closingReason = closingReason;
	}
	/**
	 * 收货地址ID
	 */
	private String recAddressId ;
	/**
	 * 联系人
	 */
	private String recLinkman ;
	/**
	 * 联系电话
	 */
	private String recLinkmanPhone ;
	/**
	 * 收货地址
	 */
	private String recAddress ;
	/**
	 * 最后收货时间
	 */
	private java.util.Date lastArrivalDate ;
	private List<PurchaseListVo> purchaseList;
	public java.util.Date getLastArrivalDate() {
		return lastArrivalDate;
	}
	public void setLastArrivalDate(java.util.Date lastArrivalDate) {
		this.lastArrivalDate = lastArrivalDate;
	}
	public String getId() {
		return id;
	}
	public String getRecAddressId() {
		return recAddressId;
	}
	public void setRecAddressId(String recAddressId) {
		this.recAddressId = recAddressId;
	}
	public String getRecLinkman() {
		return recLinkman;
	}
	public void setRecLinkman(String recLinkman) {
		this.recLinkman = recLinkman;
	}
	public String getRecLinkmanPhone() {
		return recLinkmanPhone;
	}
	public void setRecLinkmanPhone(String recLinkmanPhone) {
		this.recLinkmanPhone = recLinkmanPhone;
	}
	public String getRecAddress() {
		return recAddress;
	}
	public void setRecAddress(String recAddress) {
		this.recAddress = recAddress;
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
	public String getPurchaseComId() {
		return purchaseComId;
	}
	public void setPurchaseComId(String purchaseComId) {
		this.purchaseComId = purchaseComId;
	}
	public String getPurchaseComName() {
		return purchaseComName;
	}
	public void setPurchaseComName(String purchaseComName) {
		this.purchaseComName = purchaseComName;
	}
	public String getRecOrgId() {
		return recOrgId;
	}
	public void setRecOrgId(String recOrgId) {
		this.recOrgId = recOrgId;
	}
	public String getRecOrgName() {
		return recOrgName;
	}
	public void setRecOrgName(String recOrgName) {
		this.recOrgName = recOrgName;
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
	public String getDistrRelation() {
		return distrRelation;
	}
	public void setDistrRelation(String distrRelation) {
		this.distrRelation = distrRelation;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAutitorId() {
		return autitorId;
	}
	public void setAutitorId(String autitorId) {
		this.autitorId = autitorId;
	}
	public String getAutitorName() {
		return autitorName;
	}
	public void setAutitorName(String autitorName) {
		this.autitorName = autitorName;
	}
	public Integer getAutitorSumRow() {
		return autitorSumRow;
	}
	public void setAutitorSumRow(Integer autitorSumRow) {
		this.autitorSumRow = autitorSumRow;
	}
	public Integer getSumRow() {
		return sumRow;
	}
	public void setSumRow(Integer sumRow) {
		this.sumRow = sumRow;
	}
	public Date getFillDate() {
		return fillDate;
	}
	public void setFillDate(Date fillDate) {
		this.fillDate = fillDate;
	}
	public Date getLastUpdateDt() {
		return lastUpdateDt;
	}
	public void setLastUpdateDt(Date lastUpdateDt) {
		this.lastUpdateDt = lastUpdateDt;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public List<PurchaseListVo> getPurchaseList() {
		return purchaseList;
	}
	public void setPurchaseList(List<PurchaseListVo> purchaseList) {
		this.purchaseList = purchaseList;
	}
	public String getRecOrgCode() {
		return recOrgCode;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public void setRecOrgCode(String recOrgCode) {
		this.recOrgCode = recOrgCode;
	}
	public String getPurComCode() {
		return purComCode;
	}
	public void setPurComCode(String purComCode) {
		this.purComCode = purComCode;
	}
	public String getPurComErpCode() {
		return purComErpCode;
	}
	public void setPurComErpCode(String purComErpCode) {
		this.purComErpCode = purComErpCode;
	}
	public String getPurOrgCode() {
		return purOrgCode;
	}
	public void setPurOrgCode(String purOrgCode) {
		this.purOrgCode = purOrgCode;
	}
	public String getPurOrgCname() {
		return purOrgCname;
	}
	public void setPurOrgCname(String purOrgCname) {
		this.purOrgCname = purOrgCname;
	}
	public String getPurOrgErpCode() {
		return purOrgErpCode;
	}
	public void setPurOrgErpCode(String purOrgErpCode) {
		this.purOrgErpCode = purOrgErpCode;
	}
	public String getPurOrgScmCode() {
		return purOrgScmCode;
	}
	public void setPurOrgScmCode(String purOrgScmCode) {
		this.purOrgScmCode = purOrgScmCode;
	}
	public String getRecOrgErpCode() {
		return recOrgErpCode;
	}
	public void setRecOrgErpCode(String recOrgErpCode) {
		this.recOrgErpCode = recOrgErpCode;
	}
	public String getRecOrgScmCode() {
		return recOrgScmCode;
	}
	public void setRecOrgScmCode(String recOrgScmCode) {
		this.recOrgScmCode = recOrgScmCode;
	}
	public String getProvCode() {
		return provCode;
	}
	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}
	public String getProvErpCode() {
		return provErpCode;
	}
	public void setProvErpCode(String provErpCode) {
		this.provErpCode = provErpCode;
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

	public String getSubProvId() {
		return subProvId;
	}

	public void setSubProvId(String subProvId) {
		this.subProvId = subProvId;
	}

	public String getSubProvName() {
		return subProvName;
	}

	public void setSubProvName(String subProvName) {
		this.subProvName = subProvName;
	}

	public String getSubProvCode() {
		return subProvCode;
	}

	public void setSubProvCode(String subProvCode) {
		this.subProvCode = subProvCode;
	}

	public String getSubProvErpCode() {
		return subProvErpCode;
	}

	public void setSubProvErpCode(String subProvErpCode) {
		this.subProvErpCode = subProvErpCode;
	}

	public void setPurType(Integer purType) {
		this.purType = purType;
	}
}
