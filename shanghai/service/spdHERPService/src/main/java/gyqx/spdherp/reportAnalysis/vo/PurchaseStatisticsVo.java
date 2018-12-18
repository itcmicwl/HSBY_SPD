package gyqx.spdherp.reportAnalysis.vo;

import java.io.Serializable;

/**
 * 某科室的采购金额统计、清沟金额统计查询实体
 * 
 * @author CHENJIANGHUA
 *
 */
public class PurchaseStatisticsVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 医疗机构id
	 */
	private String hosId;

	/**
	 * 科室id
	 */
	private String deptId;

	/**
	 * 月份
	 */
	private String month;

	/**
	 * 科室月请购金额
	 */
	private String reqPurchaseQuantity;

	/**
	 * 科室月采购金额
	 */
	private String purchaseQuantity;

	/**
	 * 同比
	 */
	private String similarRatio;

	/**
	 * 环比
	 */
	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	private String ringRatio;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getReqPurchaseQuantity() {
		return reqPurchaseQuantity;
	}

	public void setReqPurchaseQuantity(String reqPurchaseQuantity) {
		this.reqPurchaseQuantity = reqPurchaseQuantity;
	}

	public String getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(String purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public String getSimilarRatio() {
		return similarRatio;
	}

	public void setSimilarRatio(String similarRatio) {
		this.similarRatio = similarRatio;
	}

	public String getRingRatio() {
		return ringRatio;
	}

	public void setRingRatio(String ringRatio) {
		this.ringRatio = ringRatio;
	}

	@Override
	public String toString() {
		return "PurchaseStatisticsVo [hosId=" + hosId + ", deptId=" + deptId + ", month=" + month
				+ ", reqPurchaseQuantity=" + reqPurchaseQuantity + ", purchaseQuantity=" + purchaseQuantity
				+ ", similarRatio=" + similarRatio + ", ringRatio=" + ringRatio + "]";
	}

}
