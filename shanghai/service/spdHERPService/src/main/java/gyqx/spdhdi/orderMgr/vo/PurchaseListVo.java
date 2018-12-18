/**
 * 
 */
package gyqx.spdhdi.orderMgr.vo;

import jdk.nashorn.internal.ir.ReturnNode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author moonb
 *
 */
public class PurchaseListVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7880345323517244778L;
	private String id;
	private String pid;
	private String billId;
	private Integer rowNumber;
	private String applyDeptId;
	private String applyDeptCode;
	private String applyDeptName;
	private String applyBillId;
	private Integer applyRowNum;
	private String hosGoodsId;
	private String hosGoodsCode;
	private String hosGoodsName;
	private BigDecimal hosPrice;
	private String hosUnit;
	private float hosScale;
	private BigDecimal packetCode ;
	private BigDecimal  hosApplyQty;
	private BigDecimal  applyQty;
	private BigDecimal  hosPurQty;
	private BigDecimal  purQty;
	private BigDecimal  unitQty;
	private BigDecimal  qty;
	private BigDecimal  purTax;
	private BigDecimal purTaxPrice;
	private BigDecimal  purTaxAmount;
	private BigDecimal  purAmount;
	private BigDecimal  purTaxMoney;
	private Integer status;
	private String provId;
	private Date applyDate;
	private Date receiveDate;
	private Integer version;
	private String provGoodsId;
	private String goodsGg;
	private String made;
	private String mfrsId;
	private String mfrsName;
	private BigDecimal salePrice;
	private String goodsName;
	private String erpCode;
	private  Integer uniqueKind;
	private String unit;
	private String saleMan;
	private String checkMark;//检查状态1:能关联到供货目录，0则不能
	private List<PurchaseExtVo> lstApply;
	public String getSaleMan() {
		return saleMan;
	}
	public void setSaleMan(String saleMan) {
		this.saleMan = saleMan;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public Integer getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}
	public String getApplyDeptId() {
		return applyDeptId;
	}
	public void setApplyDeptId(String applyDeptId) {
		this.applyDeptId = applyDeptId;
	}
	public String getApplyBillId() {
		return applyBillId;
	}
	public void setApplyBillId(String applyBillId) {
		this.applyBillId = applyBillId;
	}
	public Integer getApplyRowNum() {
		return applyRowNum;
	}
	public void setApplyRowNum(Integer applyRowNum) {
		this.applyRowNum = applyRowNum;
	}
	public String getHosGoodsId() {
		return hosGoodsId;
	}
	public void setHosGoodsId(String hosGoodsId) {
		this.hosGoodsId = hosGoodsId;
	}
	public String getHosGoodsCode() {
		return hosGoodsCode;
	}
	public void setHosGoodsCode(String hosGoodsCode) {
		this.hosGoodsCode = hosGoodsCode;
	}
	public String getHosGoodsName() {
		return hosGoodsName;
	}
	public void setHosGoodsName(String hosGoodsName) {
		this.hosGoodsName = hosGoodsName;
	}
	public BigDecimal getHosPrice() {
		return hosPrice;
	}
	public void setHosPrice(BigDecimal hosPrice) {
		this.hosPrice = hosPrice;
	}
	public String getHosUnit() {
		return hosUnit;
	}
	public void setHosUnit(String hosUnit) {
		this.hosUnit = hosUnit;
	}
	public float getHosScale() {
		return hosScale;
	}
	public void setHosScale(float hosScale) {
		this.hosScale = hosScale;
	}
	public BigDecimal  getHosApplyQty() {
		return hosApplyQty;
	}
	public void setHosApplyQty(BigDecimal  hosApplyQty) {
		this.hosApplyQty = hosApplyQty;
	}
	public BigDecimal  getApplyQty() {
		return applyQty;
	}
	public void setApplyQty(BigDecimal  applyQty) {
		this.applyQty = applyQty;
	}
	public BigDecimal  getHosPurQty() {
		return hosPurQty;
	}
	public void setHosPurQty(BigDecimal hosPurQty) {
		this.hosPurQty = hosPurQty;
	}
	public BigDecimal getPurQty() {
		return purQty;
	}
	public void setPurQty(BigDecimal purQty) {
		this.purQty = purQty;
	}
	public BigDecimal  getUnitQty() {
		return unitQty;
	}
	public void setUnitQty(BigDecimal  unitQty) {
		this.unitQty = unitQty;
	}
	public BigDecimal  getQty() {
		return qty;
	}
	public void setQty(BigDecimal  qty) {
		this.qty = qty;
	}
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getApplyDeptName() {
		return applyDeptName;
	}
	public void setApplyDeptName(String applyDeptName) {
		this.applyDeptName = applyDeptName;
	}
	public String getApplyDeptCode() {
		return applyDeptCode;
	}
	public void setApplyDeptCode(String applyDeptCode) {
		this.applyDeptCode = applyDeptCode;
	}	
	public BigDecimal getPurTax() {
		return purTax;
	}
	public void setPurTax(BigDecimal purTax) {
		this.purTax = purTax;
	}
	public BigDecimal getPurTaxPrice() {
		return purTaxPrice;
	}
	public void setPurTaxPrice(BigDecimal purTaxPrice) {
		this.purTaxPrice = purTaxPrice;
	}
	public BigDecimal getPurTaxAmount() {
		return purTaxAmount;
	}
	public void setPurTaxAmount(BigDecimal purTaxAmount) {
		this.purTaxAmount = purTaxAmount;
	}
	public BigDecimal getPurAmount() {
		return purAmount;
	}
	public void setPurAmount(BigDecimal purAmount) {
		this.purAmount = purAmount;
	}
	public BigDecimal getPurTaxMoney() {
		return this.purTaxMoney;
		//return this.purAmount.subtract(this.purTaxAmount);
	}
	public void setPurTaxMoney(BigDecimal purTaxMoney) {

		this.purTaxMoney = purTaxMoney;//this.purAmount.subtract(this.purTaxAmount);
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getProvGoodsId() {
		return provGoodsId;
	}
	public void setProvGoodsId(String provGoodsId) {
		this.provGoodsId = provGoodsId;
	}
	public String getGoodsGg() {
		return goodsGg;
	}
	public void setGoodsGg(String goodsGg) {
		this.goodsGg = goodsGg;
	}
	public String getMade() {
		return made;
	}
	public void setMade(String made) {
		this.made = made;
	}
	public String getMfrsId() {
		return mfrsId;
	}
	public void setMfrsId(String mfrsId) {
		this.mfrsId = mfrsId;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getErpCode() {
		return erpCode;
	}
	public void setErpCode(String erpCode) {
		this.erpCode = erpCode;
	}

	public BigDecimal getPacketCode() {
		return packetCode;
	}

	public void setPacketCode(BigDecimal packetCode) {
		this.packetCode = packetCode;
	}

	public String getMfrsName() {
		return mfrsName;
	}

	public void setMfrsName(String mfrsName) {
		this.mfrsName = mfrsName;
	}

	public Integer getUniqueKind() {
		return uniqueKind;
	}

	public void setUniqueKind(Integer uniqueKind) {
		this.uniqueKind = uniqueKind;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCheckMark() {
		return checkMark;
	}
	public void setCheckMark(String checkMark) {
		this.checkMark = checkMark;
	}

	public List<PurchaseExtVo> getLstApply() {
		return lstApply;
	}

	public void setLstApply(List<PurchaseExtVo> lstApply) {
		this.lstApply = lstApply;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() == obj.getClass()){
			PurchaseListVo purLstVo = (PurchaseListVo) obj;
			return (this.pid.equals(purLstVo.getPid()) || this.billId.equals(purLstVo.getBillId()))
					&& this.rowNumber.equals(purLstVo.getRowNumber());
		}
		return super.equals(obj);
	}
}
