package gyqx.spdherp.reportAnalysis.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 医院入库单信息统计vo
 * @author Administrator
 *
 */
public class HosMonthInStockBillQueryVo {

	/**
	 * 入库单号
	 */
	private String instockBillId ;
	
	/**
	 * 会计期，如:201710，201712
	 */
	private String accountingPeriod;
	
	/**
	 * 审核日期
	 */
	private Date auditDate;
	/**
	 * 入库类型
	 */
	private String instockType;
	
	/**
	 * 审核人员Id
	 */
	private String auditUser;
	
	/**
	 * 审核人员
	 */
	private String auditUserName;
	
	/**
	 * 入库部门Id
	 */
	private String instockDeptId;
	
	/**
	 * 入库部门
	 */
	private String instockDeptName;
	
	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 商品规格
	 */
	private String goodsGg;

	/**
	 * 商品单价
	 */
	private String inPrice;

	/**
	 * 入库商品金额
	 */
	private BigDecimal goodsAmount ;

	/**
	 * 商品数量
	 */
	private String qty;
	
	/**
	 * 供应商
	 */
	private String  provider;
	
	/**
	 * 入库总金额
	 */
	private BigDecimal amount ;
	
	/**
	 * 物资分类
	 */
	private String goodsClassification;
	
	/**
	 * 入库方式显示名称
	 * 低值或高值耗材，根据hos_goods_info 字段pur_mode 采购模式来确定
	 */
	private String highOrLower;
	
	/**
	 * 资金来源
	 */
	private String fundType;
	
	/**
	 * 医院id
	 */
	private String hosId;

	/**
	 * 销售单号
	 */
	private String saleBillId;

	/**
	 * JDE编码
	 */
	private String erpCode;

	/**
	 * 批号
	 */
	private String batchCode;

	/**
	 * 生产日期
	 */
	private String productDate;

	/**
	 * 有效期
	 */
	private String expdtEndDate;


	public String getInstockBillId() {
		return instockBillId;
	}

	public void setInstockBillId(String instockBillId) {
		this.instockBillId = instockBillId;
	}

	public String getAccountingPeriod() {
		return accountingPeriod;
	}

	public void setAccountingPeriod(String accountingPeriod) {
		this.accountingPeriod = accountingPeriod;
	}

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getInstockDeptId() {
		return instockDeptId;
	}

	public void setInstockDeptId(String instockDeptId) {
		this.instockDeptId = instockDeptId;
	}

	public String getInstockDeptName() {
		return instockDeptName;
	}

	public void setInstockDeptName(String instockDeptName) {
		this.instockDeptName = instockDeptName;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getGoodsClassification() {
		return goodsClassification;
	}

	public void setGoodsClassification(String goodsClassification) {
		this.goodsClassification = goodsClassification;
	}

	public String getHighOrLower() {
		return highOrLower;
	}

	public void setHighOrLower(String highOrLower) {
		this.highOrLower = highOrLower;
	}

	public String getInstockType() {
		return instockType;
	}

	public void setInstockType(String instockType) {
		this.instockType = instockType;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getSaleBillId() {
		return saleBillId;
	}

	public void setSaleBillId(String saleBillId) {
		this.saleBillId = saleBillId;
	}

	public String getErpCode() {
		return erpCode;
	}

	public void setErpCode(String erpCode) {
		this.erpCode = erpCode;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	public String getExpdtEndDate() {
		return expdtEndDate;
	}

	public void setExpdtEndDate(String expdtEndDate) {
		this.expdtEndDate = expdtEndDate;
	}

	public String getInPrice() {
		return inPrice;
	}

	public void setInPrice(String inPrice) {
		this.inPrice = inPrice;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public String getGoodsGg() {
		return goodsGg;
	}

	public void setGoodsGg(String goodsGg) {
		this.goodsGg = goodsGg;
	}

	@Override
	public String toString() {
		return "HosMonthInStockBillQueryVo{" +
						"instockBillId='" + instockBillId + '\'' +
						", accountingPeriod='" + accountingPeriod + '\'' +
						", auditDate=" + auditDate +
						", instockType='" + instockType + '\'' +
						", auditUser='" + auditUser + '\'' +
						", auditUserName='" + auditUserName + '\'' +
						", instockDeptId='" + instockDeptId + '\'' +
						", instockDeptName='" + instockDeptName + '\'' +
						", goodsName='" + goodsName + '\'' +
						", goodsGg='" + goodsGg + '\'' +
						", inPrice='" + inPrice + '\'' +
						", goodsAmount=" + goodsAmount +
						", qty='" + qty + '\'' +
						", provider='" + provider + '\'' +
						", amount=" + amount +
						", goodsClassification='" + goodsClassification + '\'' +
						", highOrLower='" + highOrLower + '\'' +
						", fundType='" + fundType + '\'' +
						", hosId='" + hosId + '\'' +
						", saleBillId='" + saleBillId + '\'' +
						", erpCode='" + erpCode + '\'' +
						", batchCode='" + batchCode + '\'' +
						", productDate='" + productDate + '\'' +
						", expdtEndDate='" + expdtEndDate + '\'' +
						'}';
	}
}
