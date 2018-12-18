package gyqx.spdherp.reportAnalysis.vo;

import java.math.BigDecimal;
import java.util.Date;

public class HosMonthGoodsReceiptDetailVo {


	/**
	 * 医院Id
	 */
	private String hosId;
	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	private String goodsId;
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * 查询开始日期
	 */
	private Date sDate;
	
	/**
	 * 查询结束日期
	 */
	private Date eDate;
	
	private String inDeptId;
	
	private BigDecimal  deptReceiptCount;
	
	private String inDeptName;

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

	public String getInDeptId() {
		return inDeptId;
	}

	public void setInDeptId(String inDeptId) {
		this.inDeptId = inDeptId;
	}

	public BigDecimal getDeptReceiptCount() {
		return deptReceiptCount;
	}

	public void setDeptReceiptCount(BigDecimal deptReceiptCount) {
		this.deptReceiptCount = deptReceiptCount;
	}

	public String getInDeptName() {
		return inDeptName;
	}

	public void setInDeptName(String inDeptName) {
		this.inDeptName = inDeptName;
	}

	@Override
	public String toString() {
		return "HosMonthGoodsReceiptDetailVo [goodsId=" + goodsId + ", sDate=" + sDate + ", eDate=" + eDate
				+ ", inDeptId=" + inDeptId + ", deptReceiptCount=" + deptReceiptCount + ", inDeptName=" + inDeptName
				+ "]";
	}
	
	
}
