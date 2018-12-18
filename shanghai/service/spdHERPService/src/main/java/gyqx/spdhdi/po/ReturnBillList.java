
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 科室请退单明细
*/
@Table(name="return_bill_list")
public class ReturnBillList implements IBean
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
	 * pid
	*/
	@Size(max=36)
	@JoinColumn(name="pid",table="return_bill",referencedColumnName="id")
	@Title("pid")
	private String pid ;
	/**
	 * 行号
	*/
	@Column(name="row_number")
	@Title("行号")
	private int rowNumber ;
	/**
	 * 请退单号
	*/
	@Size(max=36)
	@JoinColumn(name="return_bill_id",table="return_bill",referencedColumnName="bill_id")
	@Title("请退单号")
	private String returnBillId ;
	/**
	 * 医院产品ID
	*/
	@Size(max=36)
	@JoinColumn(name="hos_goods_id",table="hos_goods_info",referencedColumnName="id")
	@Title("医院产品ID")
	private String hosGoodsId ;
	/**
	 * 产品批次
	*/
	@Size(max=128)
	@Column(name="batch_num")
	@Title("产品批次")
	private String batchNum ;
	/**
	 * 批号
	*/
	@Size(max=128)
	@Column(name="batch_code")
	@Title("批号")
	private String batchCode ;
	/**
	 * 启用唯一码管理
	*/
	@Size(max=1)
	@Column(name="is_unique_code")
	@Title("启用唯一码管理")
	private String isUniqueCode ;
	/**
	 * 医院内部产品编号
	*/
	@Size(max=36)
	@JoinColumn(name="hos_goods_code",table="hos_goods_info",referencedColumnName="code")
	@Title("医院内部产品编号")
	private String hosGoodsCode ;
	/**
	 * 医院内部产品名称
	*/
	@Size(max=128)
	@JoinColumn(name="hos_goods_name",table="hos_goods_info",referencedColumnName="goods_name")
	@Title("医院内部产品名称")
	private String hosGoodsName ;
	/**
	 * 质量状态
	 * 
	 * 库区质量状态
	*/
	@Column(name="goods_status")
	@Title("质量状态")
	private int goodsStatus ;
	/**
	 * 入库单价
	*/
	@Column(name="instore_price")
	@Title("入库单价")
	private java.math.BigDecimal instorePrice ;
	/**
	 * 请退单价
	*/
	@Column(name="outstore_price")
	@Title("请退单价")
	private java.math.BigDecimal outstorePrice ;
	/**
	 * 可退数量
	*/
	@Column(name="can_return_qty")
	@Title("可退数量")
	private java.math.BigDecimal canReturnQty ;
	/**
	 * 数量
	*/
	@Column(name="qty")
	@Title("数量")
	private java.math.BigDecimal qty ;
	/**
	 * 采购订单号
	*/
	@Size(max=36)
	@Column(name="pur_bill_id")
	@Title("采购订单号")
	private String purBillId ;
	/**
	 * 科室请购单号
	*/
	@Size(max=36)
	@Column(name="apply_bill_id")
	@Title("科室请购单号")
	private String applyBillId ;
	/**
	 * 退货原因
	*/
	@Size(max=128)
	@Column(name="return_reason")
	@Title("退货原因")
	private String returnReason ;
	/**
	 * 状态
	 * 1、待确认 0、已确认（退货确认单回写）
	*/
	@Column(name="status")
	@Title("状态")
	private int status ;
	/**
	 * 灭菌批号
	*/
	@Size(max=128)
	@Column(name="sterilization_code")
	@Title("灭菌批号")
	private String sterilizationCode ;
	/**
	 * 灭菌有效日期
	*/
	@Column(name="sterilization_end_date")
	@Title("灭菌有效日期")
	private java.util.Date sterilizationEndDate ;
	/**
	 * 灭菌日期
	*/
	@Column(name="sterilization_date")
	@Title("灭菌日期")
	private java.util.Date sterilizationDate ;
	/**
	 * 有效日期
	*/
	@Column(name="expdt_end_date")
	@Title("有效日期")
	private java.util.Date expdtEndDate ;
	/**
	 * 生产日期
	*/
	@Column(name="product_date")
	@Title("生产日期")
	private java.util.Date productDate ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 数据版本
	*/
	@Column(name="version")
	@Title("数据版本")
	private int version ;

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
	public int getRowNumber ()
	{
		return rowNumber ;
	}
	
	public void setRowNumber (int rowNumber )
	{
		this.rowNumber = rowNumber;
	}
	public String getReturnBillId ()
	{
		return returnBillId ;
	}
	
	public void setReturnBillId (String returnBillId )
	{
		this.returnBillId = returnBillId;
	}
	public String getHosGoodsId ()
	{
		return hosGoodsId ;
	}
	
	public void setHosGoodsId (String hosGoodsId )
	{
		this.hosGoodsId = hosGoodsId;
	}
	public String getBatchNum ()
	{
		return batchNum ;
	}
	
	public void setBatchNum (String batchNum )
	{
		this.batchNum = batchNum;
	}
	public String getBatchCode ()
	{
		return batchCode ;
	}
	
	public void setBatchCode (String batchCode )
	{
		this.batchCode = batchCode;
	}
	public String getIsUniqueCode ()
	{
		return isUniqueCode ;
	}
	
	public void setIsUniqueCode (String isUniqueCode )
	{
		this.isUniqueCode = isUniqueCode;
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
	public int getGoodsStatus ()
	{
		return goodsStatus ;
	}
	
	public void setGoodsStatus (int goodsStatus )
	{
		this.goodsStatus = goodsStatus;
	}
	public java.math.BigDecimal getInstorePrice ()
	{
		return instorePrice ;
	}
	
	public void setInstorePrice (java.math.BigDecimal instorePrice )
	{
		this.instorePrice = instorePrice;
	}
	public java.math.BigDecimal getOutstorePrice ()
	{
		return outstorePrice ;
	}
	
	public void setOutstorePrice (java.math.BigDecimal outstorePrice )
	{
		this.outstorePrice = outstorePrice;
	}
	public java.math.BigDecimal getCanReturnQty ()
	{
		return canReturnQty ;
	}
	
	public void setCanReturnQty (java.math.BigDecimal canReturnQty )
	{
		this.canReturnQty = canReturnQty;
	}
	public java.math.BigDecimal getQty ()
	{
		return qty ;
	}
	
	public void setQty (java.math.BigDecimal qty )
	{
		this.qty = qty;
	}
	public String getPurBillId ()
	{
		return purBillId ;
	}
	
	public void setPurBillId (String purBillId )
	{
		this.purBillId = purBillId;
	}
	public String getApplyBillId ()
	{
		return applyBillId ;
	}
	
	public void setApplyBillId (String applyBillId )
	{
		this.applyBillId = applyBillId;
	}
	public String getReturnReason ()
	{
		return returnReason ;
	}
	
	public void setReturnReason (String returnReason )
	{
		this.returnReason = returnReason;
	}
	public int getStatus ()
	{
		return status ;
	}
	
	public void setStatus (int status )
	{
		this.status = status;
	}
	public String getSterilizationCode ()
	{
		return sterilizationCode ;
	}
	
	public void setSterilizationCode (String sterilizationCode )
	{
		this.sterilizationCode = sterilizationCode;
	}
	public java.util.Date getSterilizationEndDate ()
	{
		return sterilizationEndDate ;
	}
	
	public void setSterilizationEndDate (java.util.Date sterilizationEndDate )
	{
		this.sterilizationEndDate = sterilizationEndDate;
	}
	public java.util.Date getSterilizationDate ()
	{
		return sterilizationDate ;
	}
	
	public void setSterilizationDate (java.util.Date sterilizationDate )
	{
		this.sterilizationDate = sterilizationDate;
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
	public java.util.Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (java.util.Date lastUpdateDatetime )
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


	@Override
	public String toString() {
		return "ReturnBillList [" +" id=" +  id   +", pid=" +  pid   +", rowNumber=" +  rowNumber   +", returnBillId=" +  returnBillId   +", hosGoodsId=" +  hosGoodsId   +", batchNum=" +  batchNum   +", batchCode=" +  batchCode   +", isUniqueCode=" +  isUniqueCode   +", hosGoodsCode=" +  hosGoodsCode   +", hosGoodsName=" +  hosGoodsName   +", goodsStatus=" +  goodsStatus   +", instorePrice=" +  instorePrice   +", outstorePrice=" +  outstorePrice   +", canReturnQty=" +  canReturnQty   +", qty=" +  qty   +", purBillId=" +  purBillId   +", applyBillId=" +  applyBillId   +", returnReason=" +  returnReason   +", status=" +  status   +", sterilizationCode=" +  sterilizationCode   +", sterilizationEndDate=" +  sterilizationEndDate   +", sterilizationDate=" +  sterilizationDate   +", expdtEndDate=" +  expdtEndDate   +", productDate=" +  productDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}