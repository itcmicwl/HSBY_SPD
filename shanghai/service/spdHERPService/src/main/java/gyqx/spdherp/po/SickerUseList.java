
package gyqx.spdherp.po;

import java.math.BigDecimal;
import java.util.*;
import common.db.IBean;
import common.db.meta.Title;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.*;
/**
 * 病人消耗记录明细
*/
@Table(name="sicker_use_list")
public class SickerUseList implements IBean
{
	/**
	 * ID
	*/
	@Id
	@Size(max=36)
	@Column(name="id")
	@Title("ID")
	private String id ;
	/**
	 * PID
	*/
	@Size(max=36)
	@Column(name="p_id")
	@Title("PID")
	private String pId ;
	/**
	 * 出库单号
	*/
	@Size(max=36)
	@Column(name="out_bill_id")
	@Title("出库单号")
	private String outBillId ;
	
	@Column(name="row_num")
	@Title("明细行号")
	private int rowNum ;
	
	/**
	 * 出库单行号
	*/
	@Column(name="out_bill_row")
	@Title("出库单行号")
	private int outBillRow ;
	
    /*
     * 采购模式
     */
	@Column(name="pur_mode")
	@Title("采购模式")
	private int purMode ;
	/**
	 * 产品ID
	*/
	@Size(max=36)
	@Column(name="goods_id")
	@Title("产品ID")
	private String goodsId ;
	/**
	 * 产品名称
	*/
	@Size(max=36)
	@Column(name="goods_name")
	@Title("产品名称")
	private String goodsName ;
	/**
	 * 规格
	*/
	@Size(max=1000)
	@Column(name="goods_gg")
	@Title("规格")
	private String goodsGg ;
	/**
	 * 收费单价
	*/
	@Column(name="his_price")
	@Title("收费单价")
	private java.math.BigDecimal hisPrice ;
	/**
	 * 单价
	*/
	@Column(name="price")
	@Title("单价")
	private java.math.BigDecimal price ;
	/**
	 * 批号
	*/
	@Size(max=36)
	@Column(name="batch_code")
	@Title("批号")
	private String batchCode ;
	/**
	 * 批次
	*/
	@Size(max=36)
	@Column(name="goods_batch_id")
	@Title("批次")
	private String goodsBatchId ;
	/**
	 * 单位
	*/
	@Size(max=36)
	@Column(name="unit")
	@Title("单位")
	private String unit ;
	/**
	 *
	 */
	@Column(name="return_qty")
	@Title("")
	private java.math.BigDecimal returnQty;
	/**
	 * 使用数量
	*/
	@Column(name="use_qty")
	@Title("使用数量")
	private java.math.BigDecimal useQty ;
	/**
	 * 灭菌日期
	*/
	@Column(name="sterilization_date")
	@Title("灭菌日期")
	private Date sterilizationDate ;
	/**
	 * 灭菌批号
	*/
	@Size(max=36)
	@Column(name="sterilization_code")
	@Title("灭菌批号")
	private String sterilizationCode ;
	/**
	 * 灭菌效期
	*/
	@Column(name="sterilization_end_date")
	@Title("灭菌效期")
	private Date sterilizationEndDate ;
	/**
	 * 有效期至
	*/
	@Column(name="expdt_end_date")
	@Title("有效期至")
	private Date expdtEndDate ;
	/**
	 * 主码
	*/
	@Size(max=128)
	@Column(name="master_code")
	@Title("主码")
	private String masterCode ;
	/**
	 * 副码
	*/
	@Size(max=128)
	@Column(name="sec_code")
	@Title("副码")
	private String secCode ;
	/**
	 * HIBC
	*/
	@Size(max=128)
	@Column(name="hibc_code")
	@Title("HIBC")
	private String hibcCode ;
	/**
	 * EPC码
	*/
	@Size(max=128)
	@Column(name="epc_code")
	@Title("EPC码")
	private String epcCode ;
	/**
	 * 自编码
	 * 自定义规则的唯一码
	*/
	@Size(max=128)
	@Column(name="self_code")
	@Title("自编码")
	private String selfCode ;
	/**
	 * 状态
	*/
	@Column(name="status")
	@Title("状态")
	private int status ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private Date lastUpdateDatetime ;
	/**
	 * 数据版本
	*/
	@Column(name="version")
	@Title("数据版本")
	private int version ;
	
	@Column(name="provId")
	@Title("供应商ID")
	private String provId;
	
	@Column(name="unique_code")
	@Title("唯一码")
	private String uniqueCode;
	
	@Column(name="is_used")
	@Title("是否使用")
	private String isUsed;

	@Column(name="big_batch_code")
	@Title("大批号")
	private String bigBatchCode;

	@Column(name="sur_id")
	@Title("所属手术包")
	private String surId;
	
	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getPurMode() {
		return purMode;
	}

	public void setPurMode(int purMode) {
		this.purMode = purMode;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public String getId ()
	{
		return id ;
	}
	
	public void setId (String id )
	{
		this.id = id;
	}
	public String getPId ()
	{
		return pId ;
	}
	
	public void setPId (String pId )
	{
		this.pId = pId;
	}
	public String getOutBillId ()
	{
		return outBillId ;
	}
	
	public void setOutBillId (String outBillId )
	{
		this.outBillId = outBillId;
	}
	public int getOutBillRow ()
	{
		return outBillRow ;
	}
	
	public void setOutBillRow (int outBillRow )
	{
		this.outBillRow = outBillRow;
	}
	public String getGoodsId ()
	{
		return goodsId ;
	}
	
	public void setGoodsId (String goodsId )
	{
		this.goodsId = goodsId;
	}
	public String getGoodsName ()
	{
		return goodsName ;
	}
	
	public void setGoodsName (String goodsName )
	{
		this.goodsName = goodsName;
	}
	public String getGoodsGg ()
	{
		return goodsGg ;
	}
	
	public void setGoodsGg (String goodsGg )
	{
		this.goodsGg = goodsGg;
	}
	public java.math.BigDecimal getHisPrice ()
	{
		return hisPrice ;
	}
	
	public void setHisPrice (java.math.BigDecimal hisPrice )
	{
		this.hisPrice = hisPrice;
	}
	public java.math.BigDecimal getPrice ()
	{
		return price ;
	}
	
	public void setPrice (java.math.BigDecimal price )
	{
		this.price = price;
	}
	public String getBatchCode ()
	{
		return batchCode ;
	}
	
	public void setBatchCode (String batchCode )
	{
		this.batchCode = batchCode;
	}
	public String getGoodsBatchId ()
	{
		return goodsBatchId ;
	}
	
	public void setGoodsBatchId (String goodsBatchId )
	{
		this.goodsBatchId = goodsBatchId;
	}
	public String getUnit ()
	{
		return unit ;
	}
	
	public void setUnit (String unit )
	{
		this.unit = unit;
	}
	public java.math.BigDecimal getUseQty ()
	{
		return useQty ;
	}
	
	public void setUseQty (java.math.BigDecimal useQty )
	{
		this.useQty = useQty;
	}
	public Date getSterilizationDate ()
	{
		return sterilizationDate ;
	}
	
	public void setSterilizationDate (Date sterilizationDate )
	{
		this.sterilizationDate = sterilizationDate;
	}
	public String getSterilizationCode ()
	{
		return sterilizationCode ;
	}
	
	public void setSterilizationCode (String sterilizationCode )
	{
		this.sterilizationCode = sterilizationCode;
	}
	public Date getSterilizationEndDate ()
	{
		return sterilizationEndDate ;
	}
	
	public void setSterilizationEndDate (Date sterilizationEndDate )
	{
		this.sterilizationEndDate = sterilizationEndDate;
	}
	public Date getExpdtEndDate ()
	{
		return expdtEndDate ;
	}
	
	public void setExpdtEndDate (Date expdtEndDate )
	{
		this.expdtEndDate = expdtEndDate;
	}
	public String getMasterCode ()
	{
		return masterCode ;
	}
	
	public void setMasterCode (String masterCode )
	{
		this.masterCode = masterCode;
	}
	public String getSecCode ()
	{
		return secCode ;
	}
	
	public void setSecCode (String secCode )
	{
		this.secCode = secCode;
	}
	public String getHibcCode ()
	{
		return hibcCode ;
	}
	
	public void setHibcCode (String hibcCode )
	{
		this.hibcCode = hibcCode;
	}
	public String getEpcCode ()
	{
		return epcCode ;
	}
	
	public void setEpcCode (String epcCode )
	{
		this.epcCode = epcCode;
	}
	public String getSelfCode ()
	{
		return selfCode ;
	}
	
	public void setSelfCode (String selfCode )
	{
		this.selfCode = selfCode;
	}
	public int getStatus ()
	{
		return status ;
	}
	
	public void setStatus (int status )
	{
		this.status = status;
	}
	public Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (Date lastUpdateDatetime )
	{
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	public int getVersion ()
	{
		return version ;
	}
	
	public void setVersion (int version )
	{
		this.version = version;
	}

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public BigDecimal getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(BigDecimal returnQty) {
		this.returnQty = returnQty;
	}

	public String getBigBatchCode() {
		return bigBatchCode;
	}

	public void setBigBatchCode(String bigBatchCode) {
		this.bigBatchCode = bigBatchCode;
	}

	public String getSurId() {
		return surId;
	}

	public void setSurId(String surId) {
		this.surId = surId;
	}

	@Override
	public String toString() {
		return "SickerUseList{" +
				"id='" + id + '\'' +
				", pId='" + pId + '\'' +
				", outBillId='" + outBillId + '\'' +
				", rowNum=" + rowNum +
				", outBillRow=" + outBillRow +
				", purMode=" + purMode +
				", goodsId='" + goodsId + '\'' +
				", goodsName='" + goodsName + '\'' +
				", goodsGg='" + goodsGg + '\'' +
				", hisPrice=" + hisPrice +
				", price=" + price +
				", batchCode='" + batchCode + '\'' +
				", goodsBatchId='" + goodsBatchId + '\'' +
				", unit='" + unit + '\'' +
				", returnQty=" + returnQty +
				", useQty=" + useQty +
				", sterilizationDate=" + sterilizationDate +
				", sterilizationCode='" + sterilizationCode + '\'' +
				", sterilizationEndDate=" + sterilizationEndDate +
				", expdtEndDate=" + expdtEndDate +
				", masterCode='" + masterCode + '\'' +
				", secCode='" + secCode + '\'' +
				", hibcCode='" + hibcCode + '\'' +
				", epcCode='" + epcCode + '\'' +
				", selfCode='" + selfCode + '\'' +
				", status=" + status +
				", lastUpdateDatetime=" + lastUpdateDatetime +
				", version=" + version +
				", provId='" + provId + '\'' +
				", uniqueCode='" + uniqueCode + '\'' +
				", isUsed='" + isUsed + '\'' +
				", bigBatchCode='" + bigBatchCode + '\'' +
				", surId='" + surId + '\'' +
				'}';
	}

}