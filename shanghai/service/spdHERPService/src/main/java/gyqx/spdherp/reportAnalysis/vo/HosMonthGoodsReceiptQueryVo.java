package gyqx.spdherp.reportAnalysis.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class HosMonthGoodsReceiptQueryVo implements Serializable{

	private static final long serialVersionUID = 4872829025037350230L;
	
	/**
	 * 
	 * 
	 * AA.goodsId,
	    AA.receiptCount,
		AA.totalAmount,
	    AA.inPrice,
	    BB.goods_name AS goodsName,
	    BB.goods_gg AS goodsGg,
	    BB.made,
	    BB.unit,
	    CC.cname AS provName**/
	/**
	 * 收货部门
	 */
	private String inDeptId ;
	public String getInDeptId() {
		return inDeptId;
	}
	
	/**
	 * 收货部门列表
	 */
	private List<String> deptIds;

	/**
	 * erpCode
	 */
	private String erpCode;
	
	public String getErpCode() {
		return erpCode;
	}

	public void setErpCode(String erpCode) {
		this.erpCode = erpCode;
	}

	public void setInDeptId(String inDeptId) {
		this.inDeptId = inDeptId;
	}
	/**
	 * 单价
	 */
	private BigDecimal  inPrice;
	/**
	 * 总金额
	 */
	private BigDecimal  totalAmount;
	/**
	 * 查询开始日期
	 */
	private Date sDate;
	
	/**
	 * 查询结束日期
	 */
	private Date eDate;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 商品Id
	 */
	private String goodsId;
	
	/**
	 * 商品规格
	 */
	private String goodsGg;
	
	/**
	 * 计量单位
	 */
	private String unit;
	
	/**
	 * 供应商
	 */
	private String provName;
	
	/**
	 * 收货数量
	 */
	private BigDecimal receiptCount;
	
	/**
	 * 产地
	 */
	private String made;
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
	@Override
	public String toString() {
		return "HosMonthGoodsReceiptQueryVo [inPrice=" + inPrice + ", totalAmount=" + totalAmount + ", sDate=" + sDate
				+ ", eDate=" + eDate + ", goodsName=" + goodsName + ", goodsId=" + goodsId + ", goodsGg=" + goodsGg
				+ ", unit=" + unit + ", provName=" + provName + ", receiptCount=" + receiptCount + ", made=" + made
				+ ", hosId=" + hosId + "]";
	}

	public BigDecimal getInPrice() {
		return inPrice;
	}

	public void setInPrice(BigDecimal inPrice) {
		this.inPrice = inPrice;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsGg() {
		return goodsGg;
	}

	public void setGoodsGg(String goodsGg) {
		this.goodsGg = goodsGg;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}



	public BigDecimal getReceiptCount() {
		return receiptCount;
	}

	public void setReceiptCount(BigDecimal receiptCount) {
		this.receiptCount = receiptCount;
	}

	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
	}

	public List<String> getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}

	
}
