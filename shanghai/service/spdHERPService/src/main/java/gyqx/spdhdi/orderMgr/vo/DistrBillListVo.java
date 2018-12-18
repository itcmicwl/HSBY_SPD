package gyqx.spdhdi.orderMgr.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import gyqx.spdherp.stockMgr.vo.InStockListVo;

/**
 * 请购配送单明细
 */
public class DistrBillListVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4498578128713736689L;
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
	 * 确认单号
	*/
	private String purconfirmBillId ;
	/**
	 * 确认单行号
	*/
	private Integer purconfirmRowNumber ;
	/**
	 * 采购订单号
	*/
	private String purBillId ;
	/**
	 * 采购订单行号
	*/
	private int purRowNumber ;
	/**
	 * 采购订单日期
	*/
	private java.util.Date purBillDate ;
	/**
	 * 科室请购单号
	*/
	private String applyBillId ;
	/**
	 * 销售单号
	*/
	private String saleBillId ;
	/**
	 * 发货日期
	*/
	private java.util.Date deliveryDate ;
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
	 * 产品批次
	*/
	private String batchNum ;
	/**
	 * 启用唯一码管理
	*/
	private String isUniqueCode ;
	/**
	 * 批号
	*/
	private String batchCode ;
	/**
	 * 灭菌批号
	*/
	private String sterilizationCode ;
	/**
	 * 灭菌有效日期
	 */
	private java.util.Date sterilizationEndDate;
	/**
	 * 灭菌日期
	 */
	private java.util.Date sterilizationDate;
	/**
	 * 有效日期
	*/
	private java.util.Date expdtEndDate ;
	/**
	 * 生产日期
	*/
	private java.util.Date productDate ;
	/**
	 * 配送单位
	*/
	private String sendUnit ;
	/**
	 * 配送单位转换比率
	*/
	private java.math.BigDecimal sendScale ;
	private BigDecimal canSendQty;
	/**
	 * 配送单位数量
	*/
	private java.math.BigDecimal sendQty ;
	private java.math.BigDecimal receiveQty;
	/**
	 * 配送单位单价
	*/
	private java.math.BigDecimal sendPrice ;
	/**
	 * 请购单位
	*/
	private String hosUnit ;
	/**
	 * 请购单位数量
	*/
	private java.math.BigDecimal hosQty ;
	/**
	 * 请购单位单价
	*/
	private java.math.BigDecimal hosPrice ;
	/**
	 * 出库库区
	*/
	private String outStorearea ;
	/**
	 * 出库类型
	*/
	private int outKind ;
	/**
	 * 总行数
	*/
	private int sumRow ;
	/**
	 * 说明
	*/
	private String memo ;
	/**
	 * 条码
	*/
	private String barcode ;
	/**
	 * 定量包装
	*/
	private String packetCode ;
	/**
	 * 数据来源
	*/
	private int sourceData ;
	/**
	 * 换货标识
	*/
	private String isExchange ;
	private float sendTemprature;
	private float sendHumidity;
	private java.math.BigDecimal rejectedQty = new BigDecimal(0);
	private java.math.BigDecimal rejectedQtyUnit = new BigDecimal(0);
	private Integer uniqueKind ;
	/**
	 * 数据版本
	*/
	private int version ;
	private int status;
	/**
	 * 注册证
	*/
	private String certificateCode ;
	private List<DistrBillHumitureListVo> distrBillHumitureList;
	private List<DistrBillUniqueListVo> distrBillUniqueList;
	
	/**
	 * 储运方式
	 */
	private String stCondition;
	/**
	 * 生产厂家名称
	 */
	private String mfrsName;
    private List<Integer> arrStatus;
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
	public String getPurconfirmBillId ()
	{
		return purconfirmBillId ;
	}
	
	public void setPurconfirmBillId (String purconfirmBillId )
	{
		this.purconfirmBillId = purconfirmBillId;
	}
	public java.util.Date getSterilizationDate() {
		return sterilizationDate;
	}

	public void setSterilizationDate(java.util.Date sterilizationDate) {
		this.sterilizationDate = sterilizationDate;
	}

	public Integer getPurconfirmRowNumber ()
	{
		return purconfirmRowNumber ;
	}
	
	public void setPurconfirmRowNumber (Integer purconfirmRowNumber )
	{
		this.purconfirmRowNumber = purconfirmRowNumber;
	}
	public String getPurBillId ()
	{
		return purBillId ;
	}
	
	public void setPurBillId (String purBillId )
	{
		this.purBillId = purBillId;
	}
	public int getPurRowNumber ()
	{
		return purRowNumber ;
	}
	
	public void setPurRowNumber (int purRowNumber )
	{
		this.purRowNumber = purRowNumber;
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
	public String getSaleBillId ()
	{
		return saleBillId ;
	}
	
	public void setSaleBillId (String saleBillId )
	{
		this.saleBillId = saleBillId;
	}
	public java.util.Date getDeliveryDate ()
	{
		return deliveryDate ;
	}
	
	public void setDeliveryDate (java.util.Date deliveryDate )
	{
		this.deliveryDate = deliveryDate;
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
	public String getBatchNum ()
	{
		return batchNum ;
	}
	
	public void setBatchNum (String batchNum )
	{
		this.batchNum = batchNum;
	}
	public String getIsUniqueCode ()
	{
		return isUniqueCode ;
	}
	
	public void setIsUniqueCode (String isUniqueCode )
	{
		this.isUniqueCode = isUniqueCode;
	}
	public String getBatchCode ()
	{
		return batchCode ;
	}
	
	public void setBatchCode (String batchCode )
	{
		this.batchCode = batchCode;
	}
	public String getSterilizationCode ()
	{
		return sterilizationCode ;
	}
	
	public void setSterilizationCode (String sterilizationCode )
	{
		this.sterilizationCode = sterilizationCode;
	}
	public java.util.Date getExpdtEndDate ()
	{
		return expdtEndDate ;
	}
	
	public void setExpdtEndDate (java.util.Date expdtEndDate )
	{
		this.expdtEndDate = expdtEndDate;
	}
	public java.util.Date getProductDate ()
	{
		return productDate ;
	}
	
	public void setProductDate (java.util.Date productDate )
	{
		this.productDate = productDate;
	}
	public String getSendUnit ()
	{
		return sendUnit ;
	}
	
	public void setSendUnit (String sendUnit )
	{
		this.sendUnit = sendUnit;
	}
	public java.math.BigDecimal getSendScale ()
	{
		return sendScale ;
	}
	
	public void setSendScale (java.math.BigDecimal sendScale )
	{
		this.sendScale = sendScale;
	}
	public java.math.BigDecimal getSendQty ()
	{
		return sendQty ;
	}
	
	public void setSendQty (java.math.BigDecimal sendQty )
	{
		this.sendQty = sendQty;
	}
	public java.math.BigDecimal getSendPrice ()
	{
		return sendPrice ;
	}
	
	public void setSendPrice (java.math.BigDecimal sendPrice )
	{
		this.sendPrice = sendPrice;
	}
	public String getHosUnit ()
	{
		return hosUnit ;
	}
	
	public void setHosUnit (String hosUnit )
	{
		this.hosUnit = hosUnit;
	}
	public java.math.BigDecimal getHosQty ()
	{
		return hosQty ;
	}
	
	public void setHosQty (java.math.BigDecimal hosQty )
	{
		this.hosQty = hosQty;
	}
	public java.math.BigDecimal getHosPrice ()
	{
		return hosPrice ;
	}
	
	public void setHosPrice (java.math.BigDecimal hosPrice )
	{
		this.hosPrice = hosPrice;
	}
	public String getOutStorearea ()
	{
		return outStorearea ;
	}
	
	public void setOutStorearea (String outStorearea )
	{
		this.outStorearea = outStorearea;
	}
	public int getOutKind ()
	{
		return outKind ;
	}
	
	public void setOutKind (int outKind )
	{
		this.outKind = outKind;
	}
	public int getSumRow ()
	{
		return sumRow ;
	}
	
	public void setSumRow (int sumRow )
	{
		this.sumRow = sumRow;
	}
	public String getMemo ()
	{
		return memo ;
	}
	
	public void setMemo (String memo )
	{
		this.memo = memo;
	}
	public String getBarcode ()
	{
		return barcode ;
	}
	
	public void setBarcode (String barcode )
	{
		this.barcode = barcode;
	}
	public String getPacketCode ()
	{
		return packetCode ;
	}
	
	public void setPacketCode (String packetCode )
	{
		this.packetCode = packetCode;
	}
	public int getSourceData ()
	{
		return sourceData ;
	}
	
	public void setSourceData (int sourceData )
	{
		this.sourceData = sourceData;
	}
	public String getIsExchange ()
	{
		return isExchange ;
	}
	
	public void setIsExchange (String isExchange )
	{
		this.isExchange = isExchange;
	}
	public int getVersion ()
	{
		return version ;
	}
	
	public void setVersion (int version )
	{
		this.version = version;
	}
	public List<DistrBillHumitureListVo> getDistrBillHumitureList() {
		return distrBillHumitureList;
	}

	public void setDistrBillHumitureList(List<DistrBillHumitureListVo> distrBillHumitureList) {
		this.distrBillHumitureList = distrBillHumitureList;
	}

	public List<DistrBillUniqueListVo> getDistrBillUniqueList() {
		return distrBillUniqueList;
	}

	public void setDistrBillUniqueList(List<DistrBillUniqueListVo> distrBillUniqueList) {
		this.distrBillUniqueList = distrBillUniqueList;
	}

	public BigDecimal getCanSendQty() {
		return canSendQty;
	}

	public void setCanSendQty(BigDecimal canSendQty) {
		this.canSendQty = canSendQty;
	}

	public java.util.Date getSterilizationEndDate() {
		return sterilizationEndDate;
	}

	public void setSterilizationEndDate(java.util.Date sterilizationEndDate) {
		this.sterilizationEndDate = sterilizationEndDate;
	}

	public java.math.BigDecimal getReceiveQty() {
		if(receiveQty == null){
			return new java.math.BigDecimal(0);
		}
		return receiveQty;
	}

	public void setReceiveQty(java.math.BigDecimal receiveQty) {
		this.receiveQty = receiveQty;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getSendTemprature() {
		return sendTemprature;
	}

	public void setSendTemprature(float sendTemprature) {
		this.sendTemprature = sendTemprature;
	}

	public float getSendHumidity() {
		return sendHumidity;
	}

	public void setSendHumidity(float sendHumidity) {
		this.sendHumidity = sendHumidity;
	}

	public java.math.BigDecimal getRejectedQty() {
		return rejectedQty;
	}

	public void setRejectedQty(java.math.BigDecimal rejectedQty) {
		this.rejectedQty = rejectedQty;
	}

	public java.math.BigDecimal getRejectedQtyUnit() {
		return rejectedQtyUnit;
	}

	public void setRejectedQtyUnit(java.math.BigDecimal rejectedQtyUnit) {
		this.rejectedQtyUnit = rejectedQtyUnit;
	}

	public Integer getUniqueKind() {
		return uniqueKind;
	}

	public void setUniqueKind(Integer uniqueKind) {
		this.uniqueKind = uniqueKind;
	}

	public String getCertificateCode() {
		return certificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}
	
	public String getStCondition() {
		return stCondition;
	}

	public void setStCondition(String stCondition) {
		this.stCondition = stCondition;
	}

	public String getMfrsName() {
		return mfrsName;
	}

	public void setMfrsName(String mfrsName) {
		this.mfrsName = mfrsName;
	}

    public List<Integer> getArrStatus() {
        return arrStatus;
    }

    public void setArrStatus(List<Integer> arrStatus) {
        this.arrStatus = arrStatus;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() == obj.getClass()){
			 DistrBillListVo dblv = (DistrBillListVo) obj;
			 return this.pid.equals(dblv.getPid())
						&& this.rowNumber.equals(dblv.getRowNumber());
	     }
		if (obj instanceof InStockListVo) {
			InStockListVo inSl = (InStockListVo) obj;
			//明细行比较则表头信息匹配
			return this.getRowNumber().equals(inSl.getOutBillRow()) && this.getBillId().equals(inSl.getOutBillId());
			//return this.getRowNumber().equals(inSl.getOutBillRow());
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "DistrBillList [" +" id=" +  id   +", pid=" +  pid   +", billId=" +  billId   +", rowNumber=" +  rowNumber   +", purconfirmBillId=" +  purconfirmBillId   +", purconfirmRowNumber=" +  purconfirmRowNumber   +", purBillId=" +  purBillId   +", purRowNumber=" +  purRowNumber   +", purBillDate=" +  purBillDate   +", applyBillId=" +  applyBillId   +", saleBillId=" +  saleBillId   +", deliveryDate=" +  deliveryDate   +", provGoodsId=" +  provGoodsId   +", goodsGg=" +  goodsGg   +", goodsMrfsId=" +  goodsMrfsId   +", made=" +  made   +", hosGoodsId=" +  hosGoodsId   +", hosGoodsCode=" +  hosGoodsCode   +", hosGoodsName=" +  hosGoodsName   +", provGoodsErpCode=" +  provGoodsErpCode   +", provGoodsErpName=" +  provGoodsErpName   +", batchNum=" +  batchNum   +", isUniqueCode=" +  isUniqueCode   +", batchCode=" +  batchCode   +", sterilizationCode=" +  sterilizationCode   +", expdtEndDate=" +  expdtEndDate   +", productDate=" +  productDate   +", sendUnit=" +  sendUnit   +", sendScale=" +  sendScale   +", sendQty=" +  sendQty   +", sendPrice=" +  sendPrice   +", hosUnit=" +  hosUnit   +", hosQty=" +  hosQty   +", hosPrice=" +  hosPrice   +", outStorearea=" +  outStorearea   +", outKind=" +  outKind   +", sumRow=" +  sumRow   +", memo=" +  memo   +", barcode=" +  barcode   +", packetCode=" +  packetCode +", certificateCode=" +  certificateCode  +", sourceData=" +  sourceData   +", isExchange=" +  isExchange   +", version=" +  version   
			 + "]";
	}

}
