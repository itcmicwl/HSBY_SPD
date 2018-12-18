
package gyqx.spdhdi.orderMgr.vo;

import com.mysql.jdbc.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 采购确认单明细
*/
public class PurconfirmListVo implements Serializable
{
	/**
	 * 
	 */

	private static final long serialVersionUID = -6489171328897887081L;
	/**
	 * ID
	*/
	private String id ;
	/**
	 * pid
	*/
	private String pid ;
	/**
	 * 单号
	*/
	private String billId ;
	/**
	 * 行号
	*/
	private Integer rowNumber ;
	/**
	 * 请购科室
	*/
	private String applyDeptId ;
	private String applyDeptCode;
	private String applyDeptName;
	/**
	 * 采购订单号
	*/
	private String purBillId ;
	/**
	 * 采购订单行号
	*/
	private Integer purBillRownum ;
	/**
	 * 采购订单日期
	*/
	private java.util.Date purBillDate ;
	/**
	 * 科室请购单号
	*/
	private String applyBillId ;
	/**
	 * 科室请购单行号
	*/
	private Integer applyRowNumber ;
	/**
	 * 配送商产品ID
	*/
	private String provGoodsId ;
	/**
	 * 规格
	*/
	private String goodsGg ;
	/**
	 * 生产厂商
	*/
	private String goodsMrfsId ;
	private String mfrsName;
	/**
	 * 产地
	*/
	private String made ;
	/**
	 * 医院产品ID
	*/
	private String hosGoodsId ;
	/**
	 * 医院内部产品编号
	*/
	private String hosGoodsCode ;
	/**
	 * 医院内部产品名称
	*/
	private String hosGoodsName ;
	/**
	 * 配送机构内部编号
	*/
	private String provGoodsErpCode ;
	/**
	 * 配送机构内部名称
	*/
	private String provGoodsErpName ;
	/**
	 * 配送单价
	*/
	private BigDecimal provGoodsErpPrice ;
	/**
	 * 请购单价
	*/
	private BigDecimal hosPrice ;
	/**
	 * 院内单位
	*/
	private String hosUnit ;
	private String unit;
	/**
	 * 转换比率
	*/
	private BigDecimal hosScale ;
	/**
	 * 院内单位请购数量
	*/
	private BigDecimal hosApplyQty ;
	/**
	 * 请购数量
	*/
	private BigDecimal applyQty ;
	/**
	 * 院内单位采购数量
	*/
	private BigDecimal hosPurQty ;
	/**
	 * 采购数量
	*/
	private BigDecimal purQty ;
	/**
	 * 院内单位数量
	*/
	private BigDecimal unitQty ;
	/**
	 * 数量
	*/
	private BigDecimal qty ;
	private BigDecimal packetCode ;
	/**
	 * 税率
	*/
	private BigDecimal qrTax ;
	/**
	 * 采购不含税单价
	*/
	private BigDecimal qrTaxPrice ;
	/**
	 * 采购不含税金额
	*/
	private BigDecimal qrTaxAmount ;
	/**
	 * 采购含税金额
	*/
	private BigDecimal qrAmount ;
	/**
	 * 税额
	*/
	private BigDecimal qrTaxMoney ;
	/**
	 * 科室请购日期
	*/
	private java.util.Date applyDate ;
	/**
	 * 预计到货日期
	*/
	private java.util.Date esArrivalDate ;
	/**
	 * 数据版本
	*/
	private int version ;
	private int purStatus;	//采购单对应明细的状态
	private BigDecimal sendQty;
	private int status;
	private Integer uniqueKind ;
	private String saleMan;
	private String miCode;//医保编码
	private String ygBillId;
	private String checkMark;//1可以确认 0不能确认
	public String getId ()
	{
		return id ;
	}

	public void setId (String id )
	{
		this.id = id;
	}
	public String getPid ()
	{
		return pid ;
	}

	public void setPid (String pid )
	{
		this.pid = pid;
	}
	public String getBillId ()
	{
		return billId ;
	}

	public void setBillId (String billId )
	{
		this.billId = billId;
	}
	public Integer getRowNumber ()
	{
		return rowNumber ;
	}

	public void setRowNumber (Integer rowNumber )
	{
		this.rowNumber = rowNumber;
	}
	public String getApplyDeptId ()
	{
		return applyDeptId ;
	}

	public void setApplyDeptId (String applyDeptId )
	{
		this.applyDeptId = applyDeptId;
	}
	public String getPurBillId ()
	{
		return purBillId ;
	}

	public void setPurBillId (String purBillId )
	{
		this.purBillId = purBillId;
	}
	public Integer getPurBillRownum ()
	{
		return purBillRownum ;
	}

	public void setPurBillRownum (Integer purBillRownum )
	{
		this.purBillRownum = purBillRownum;
	}
	public java.util.Date getPurBillDate ()
	{
		return purBillDate ;
	}

	public void setPurBillDate (java.util.Date purBillDate )
	{
		this.purBillDate = purBillDate;
	}
	public String getApplyBillId ()
	{
		return applyBillId ;
	}

	public void setApplyBillId (String applyBillId )
	{
		this.applyBillId = applyBillId;
	}
	public Integer getApplyRowNumber ()
	{
		return applyRowNumber ;
	}

	public void setApplyRowNumber (Integer applyRowNumber )
	{
		this.applyRowNumber = applyRowNumber;
	}
	public String getProvGoodsId ()
	{
		return provGoodsId ;
	}

	public void setProvGoodsId (String provGoodsId )
	{
		this.provGoodsId = provGoodsId;
	}
	public String getGoodsGg ()
	{
		return goodsGg ;
	}

	public void setGoodsGg (String goodsGg )
	{
		this.goodsGg = goodsGg;
	}
	public String getGoodsMrfsId ()
	{
		return goodsMrfsId ;
	}

	public void setGoodsMrfsId (String goodsMrfsId )
	{
		this.goodsMrfsId = goodsMrfsId;
	}
	public String getMade ()
	{
		return made ;
	}

	public void setMade (String made )
	{
		this.made = made;
	}
	public String getHosGoodsId ()
	{
		return hosGoodsId ;
	}

	public void setHosGoodsId (String hosGoodsId )
	{
		this.hosGoodsId = hosGoodsId;
	}
	public String getHosGoodsCode ()
	{
		return hosGoodsCode ;
	}

	public void setHosGoodsCode (String hosGoodsCode )
	{
		this.hosGoodsCode = hosGoodsCode;
	}
	public String getHosGoodsName ()
	{
		return hosGoodsName ;
	}

	public void setHosGoodsName (String hosGoodsName )
	{
		this.hosGoodsName = hosGoodsName;
	}
	public String getProvGoodsErpCode ()
	{
		return provGoodsErpCode ;
	}

	public void setProvGoodsErpCode (String provGoodsErpCode )
	{
		this.provGoodsErpCode = provGoodsErpCode;
	}
	public String getProvGoodsErpName ()
	{
		return provGoodsErpName ;
	}

	public void setProvGoodsErpName (String provGoodsErpName )
	{
		this.provGoodsErpName = provGoodsErpName;
	}
	public BigDecimal getProvGoodsErpPrice ()
	{
		return provGoodsErpPrice ;
	}

	public void setProvGoodsErpPrice (BigDecimal provGoodsErpPrice )
	{
		this.provGoodsErpPrice = provGoodsErpPrice;
	}
	public BigDecimal getHosPrice ()
	{
		return hosPrice ;
	}

	public void setHosPrice (BigDecimal hosPrice )
	{
		this.hosPrice = hosPrice;
	}
	public String getHosUnit ()
	{
		return hosUnit ;
	}

	public void setHosUnit (String hosUnit )
	{
		this.hosUnit = hosUnit;
	}
	public BigDecimal getHosScale ()
	{
		return hosScale ;
	}

	public void setHosScale (BigDecimal hosScale )
	{
		this.hosScale = hosScale;
	}
	public BigDecimal getHosApplyQty ()
	{
		return hosApplyQty ;
	}

	public void setHosApplyQty (BigDecimal hosApplyQty )
	{
		this.hosApplyQty = hosApplyQty;
	}
	public BigDecimal getApplyQty ()
	{
		return applyQty ;
	}

	public void setApplyQty (BigDecimal applyQty )
	{
		this.applyQty = applyQty;
	}
	public BigDecimal getHosPurQty ()
	{
		return hosPurQty ;
	}

	public void setHosPurQty (BigDecimal hosPurQty )
	{
		this.hosPurQty = hosPurQty;
	}
	public BigDecimal getPurQty ()
	{
		return purQty ;
	}

	public void setPurQty (BigDecimal purQty )
	{
		this.purQty = purQty;
	}
	public BigDecimal getUnitQty ()
	{
		return unitQty ;
	}

	public void setUnitQty (BigDecimal unitQty )
	{
		this.unitQty = unitQty;
	}
	public BigDecimal getQty ()
	{
		return qty ;
	}

	public void setQty (BigDecimal qty )
	{
		this.qty = qty;
	}
	public BigDecimal getQrTax ()
	{
		return qrTax ;
	}

	public void setQrTax (BigDecimal qrTax )
	{
		this.qrTax = qrTax;
	}
	public BigDecimal getQrTaxPrice ()
	{
		return qrTaxPrice ;
	}

	public void setQrTaxPrice (BigDecimal qrTaxPrice )
	{
		this.qrTaxPrice = qrTaxPrice;
	}
	public BigDecimal getQrTaxAmount ()
	{
		return qrTaxAmount ;
	}

	public void setQrTaxAmount (BigDecimal qrTaxAmount )
	{
		this.qrTaxAmount = qrTaxAmount;
	}
	public BigDecimal getQrAmount ()
	{
		return qrAmount ;
	}

	public void setQrAmount (BigDecimal qrAmount )
	{
		this.qrAmount = qrAmount;
	}
	public BigDecimal getQrTaxMoney ()
	{
		return qrTaxMoney ;
	}

	public void setQrTaxMoney (BigDecimal qrTaxMoney )
	{
		this.qrTaxMoney = qrTaxMoney;
	}
	public java.util.Date getApplyDate ()
	{
		return applyDate ;
	}

	public void setApplyDate (java.util.Date applyDate )
	{
		this.applyDate = applyDate;
	}
	public java.util.Date getEsArrivalDate ()
	{
		return esArrivalDate ;
	}

	public void setEsArrivalDate (java.util.Date esArrivalDate )
	{
		this.esArrivalDate = esArrivalDate;
	}
	public int getVersion ()
	{
		return version ;
	}

	public void setVersion (int version )
	{
		this.version = version;
	}
	public String getApplyDeptCode() {
		return applyDeptCode;
	}

	public void setApplyDeptCode(String applyDeptCode) {
		this.applyDeptCode = applyDeptCode;
	}

	public String getApplyDeptName() {
		return applyDeptName;
	}

	public void setApplyDeptName(String applyDeptName) {
		this.applyDeptName = applyDeptName;
	}


	public int getPurStatus() {
		return purStatus;
	}

	public void setPurStatus(int purStatus) {
		this.purStatus = purStatus;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	public BigDecimal getSendQty() {
		return sendQty;
	}

	public void setSendQty(BigDecimal sendQty) {
		this.sendQty = sendQty;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getUniqueKind() {
		return uniqueKind;
	}

	public void setUniqueKind(Integer uniqueKind) {
		this.uniqueKind = uniqueKind;
	}

	public String getMfrsName() {
		return mfrsName;
	}

	public void setMfrsName(String mfrsName) {
		this.mfrsName = mfrsName;
	}

	public BigDecimal getPacketCode() {
		return packetCode;
	}

	public void setPacketCode(BigDecimal packetCode) {
		this.packetCode = packetCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PurchaseListVo) {   
			PurchaseListVo pur = (PurchaseListVo) obj;
			if(pur.getRowNumber().equals(0) || StringUtils.isNullOrEmpty(pur.getPid())){
				return this.saleMan.equals(pur.getSaleMan());
			}
            return this.purBillId.equals(pur.getPid())   
                    && this.purBillRownum.equals(pur.getRowNumber());   
        } 
		if (obj instanceof DistrBillListVo) {   
			DistrBillListVo d = (DistrBillListVo) obj;   
            return this.purBillId.equals(d.getPurBillId())   
                    && this.purBillRownum.equals(d.getPurRowNumber());   
        } 
		return super.equals(obj);
	}

	public String getSaleMan() {
		return saleMan;
	}

	public void setSaleMan(String saleMan) {
		this.saleMan = saleMan;
	}

	public String getMiCode() {
		return miCode;
	}

	public void setMiCode(String miCode) {
		this.miCode = miCode;
	}

	public String getYgBillId() {
		return ygBillId;
	}

	public void setYgBillId(String ygBillId) {
		this.ygBillId = ygBillId;
	}

	public String getCheckMark() {
		return checkMark;
	}

	public void setCheckMark(String checkMark) {
		this.checkMark = checkMark;
	}

	@Override
	public String toString() {
		return "PurconfirmList [" +" id=" +  id   +", pid=" +  pid   +", billId=" +  billId   +", rowNumber=" +  rowNumber   +", applyDeptId=" +  applyDeptId   +", purBillId=" +  purBillId   +", purBillRownum=" +  purBillRownum   +", purBillDate=" +  purBillDate   +", applyBillId=" +  applyBillId   +", applyRowNumber=" +  applyRowNumber   +", provGoodsId=" +  provGoodsId   +", goodsGg=" +  goodsGg   +", goodsMrfsId=" +  goodsMrfsId   +", made=" +  made   +", hosGoodsId=" +  hosGoodsId   +", hosGoodsCode=" +  hosGoodsCode   +", hosGoodsName=" +  hosGoodsName   +", provGoodsErpCode=" +  provGoodsErpCode   +", provGoodsErpName=" +  provGoodsErpName   +", provGoodsErpPrice=" +  provGoodsErpPrice   +", hosPrice=" +  hosPrice   +", hosUnit=" +  hosUnit   +", hosScale=" +  hosScale   +", hosApplyQty=" +  hosApplyQty   +", applyQty=" +  applyQty   +", hosPurQty=" +  hosPurQty   +", purQty=" +  purQty   +", unitQty=" +  unitQty   +", qty=" +  qty   +", qrTax=" +  qrTax   +", qrTaxPrice=" +  qrTaxPrice   +", qrTaxAmount=" +  qrTaxAmount   +", qrAmount=" +  qrAmount   +", qrTaxMoney=" +  qrTaxMoney   +", applyDate=" +  applyDate   +", esArrivalDate=" +  esArrivalDate   +", version=" +  version   
			 + "]";
	}
}