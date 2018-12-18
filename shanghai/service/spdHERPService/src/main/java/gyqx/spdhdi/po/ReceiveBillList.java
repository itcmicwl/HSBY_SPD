
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 订单收货明细
*/
@Table(name="receive_bill_list")
public class ReceiveBillList implements IBean
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
	@JoinColumn(name="pid",table="receive_bill",referencedColumnName="id")
	@Title("pid")
	private String pid ;
	/**
	 * 请购单号
	*/
	@Size(max=36)
	@Column(name="bill_id")
	@Title("请购单号")
	private String billId ;
	/**
	 * 行号
	*/
	@Column(name="row_number")
	@Title("行号")
	private int rowNumber ;
	/**
	 * 采购订单号
	*/
	@Size(max=36)
	@Column(name="pur_bill_id")
	@Title("采购订单号")
	private String purBillId ;
	/**
	 * 采购订单行号
	*/
	@Column(name="pur_row_number")
	@Title("采购订单行号")
	private int purRowNumber ;
	/**
	 * 采购订单日期
	*/
	@Column(name="pur_bill_date")
	@Title("采购订单日期")
	private java.util.Date purBillDate ;
	/**
	 * 科室请购单号
	*/
	@Size(max=36)
	@Column(name="apply_bill_id")
	@Title("科室请购单号")
	private String applyBillId ;
	/**
	 * 销售单号
	*/
	@Size(max=36)
	@Column(name="sale_bill_id")
	@Title("销售单号")
	private String saleBillId ;
	/**
	 * 配送商产品ID
	*/
	@Size(max=36)
	@JoinColumn(name="prov_goods_id",table="hos_goods_info",referencedColumnName="id")
	@Title("配送商产品ID")
	private String provGoodsId ;
	/**
	 * 规格
	*/
	@Size(max=36)
	@Column(name="goods_gg")
	@Title("规格")
	private String goodsGg ;
	/**
	 * 生产厂商
	*/
	@Size(max=36)
	@Column(name="goods_mrfs_id")
	@Title("生产厂商")
	private String goodsMrfsId ;
	/**
	 * 产地
	*/
	@Size(max=36)
	@Column(name="made")
	@Title("产地")
	private String made ;
	/**
	 * 医院产品ID
	*/
	@Size(max=36)
	@JoinColumn(name="hos_goods_id",table="hos_goods_info",referencedColumnName="id")
	@Title("医院产品ID")
	private String hosGoodsId ;
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
	 * 启用唯一码管理
	*/
	@Size(max=1)
	@Column(name="is_unique_code")
	@Title("启用唯一码管理")
	private String isUniqueCode ;
	/**
	 * 批号
	*/
	@Size(max=128)
	@Column(name="batch_code")
	@Title("批号")
	private String batchCode ;
	/**
	 * 产品批次
	*/
	@Size(max=128)
	@Column(name="batch_num")
	@Title("产品批次")
	private String batchNum ;
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
	 * 待验收数量
	*/
	@Column(name="unrecieve_qty")
	@Title("待验收数量")
	private java.math.BigDecimal unrecieveQty ;
	/**
	 * 数量
	*/
	@Column(name="qty")
	@Title("数量")
	private java.math.BigDecimal qty ;
	/**
	 * 单价
	*/
	@Column(name="price")
	@Title("单价")
	private java.math.BigDecimal price ;
	/**
	 * 验收结论
	 * 
	 * 验收结论_简
	*/
	@Column(name="revieve_result")
	@Title("验收结论")
	private int revieveResult ;
	/**
	 * 任务总行数
	*/
	@Column(name="sum_rows")
	@Title("任务总行数")
	private int sumRows ;
	/**
	 * 收货时温度
	*/
	@Column(name="receive_temprature")
	@Title("收货时温度")
	private java.math.BigDecimal receiveTemprature ;
	/**
	 * 收货时湿度
	*/
	@Column(name="receive_humidity")
	@Title("收货时湿度")
	private java.math.BigDecimal receiveHumidity ;
	/**
	 * 当前审批级别
	*/
	@Column(name="status")
	@Title("当前审批级别")
	private int status ;
	/**
	 * 说明
	*/
	@Size(max=300)
	@Column(name="memo")
	@Title("说明")
	private String memo ;
	/**
	 * 配送单号
	*/
	@Size(max=36)
	@JoinColumn(name="distr_bill_id",table="distr_bill",referencedColumnName="bill_id")
	@Title("配送单号")
	private String distrBillId ;
	/**
	 * 配送单行号
	*/
	@Column(name="distr_row_number")
	@Title("配送单行号")
	private int distrRowNumber ;
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
	public String getBillId ()
	{
		return billId ;
	}
	
	public void setBillId (String billId )
	{
		this.billId = billId;
	}
	public int getRowNumber ()
	{
		return rowNumber ;
	}
	
	public void setRowNumber (int rowNumber )
	{
		this.rowNumber = rowNumber;
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
	public String getBatchNum ()
	{
		return batchNum ;
	}
	
	public void setBatchNum (String batchNum )
	{
		this.batchNum = batchNum;
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
	public java.math.BigDecimal getUnrecieveQty ()
	{
		return unrecieveQty ;
	}
	
	public void setUnrecieveQty (java.math.BigDecimal unrecieveQty )
	{
		this.unrecieveQty = unrecieveQty;
	}
	public java.math.BigDecimal getQty ()
	{
		return qty ;
	}
	
	public void setQty (java.math.BigDecimal qty )
	{
		this.qty = qty;
	}
	public java.math.BigDecimal getPrice ()
	{
		return price ;
	}
	
	public void setPrice (java.math.BigDecimal price )
	{
		this.price = price;
	}
	public int getRevieveResult ()
	{
		return revieveResult ;
	}
	
	public void setRevieveResult (int revieveResult )
	{
		this.revieveResult = revieveResult;
	}
	public int getSumRows ()
	{
		return sumRows ;
	}
	
	public void setSumRows (int sumRows )
	{
		this.sumRows = sumRows;
	}
	public java.math.BigDecimal getReceiveTemprature ()
	{
		return receiveTemprature ;
	}
	
	public void setReceiveTemprature (java.math.BigDecimal receiveTemprature )
	{
		this.receiveTemprature = receiveTemprature;
	}
	public java.math.BigDecimal getReceiveHumidity ()
	{
		return receiveHumidity ;
	}
	
	public void setReceiveHumidity (java.math.BigDecimal receiveHumidity )
	{
		this.receiveHumidity = receiveHumidity;
	}
	public int getStatus ()
	{
		return status ;
	}
	
	public void setStatus (int status )
	{
		this.status = status;
	}
	public String getMemo ()
	{
		return memo ;
	}
	
	public void setMemo (String memo )
	{
		this.memo = memo;
	}
	public String getDistrBillId ()
	{
		return distrBillId ;
	}
	
	public void setDistrBillId (String distrBillId )
	{
		this.distrBillId = distrBillId;
	}
	public int getDistrRowNumber ()
	{
		return distrRowNumber ;
	}
	
	public void setDistrRowNumber (int distrRowNumber )
	{
		this.distrRowNumber = distrRowNumber;
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
		return "ReceiveBillList [" +" id=" +  id   +", pid=" +  pid   +", billId=" +  billId   +", rowNumber=" +  rowNumber   +", purBillId=" +  purBillId   +", purRowNumber=" +  purRowNumber   +", purBillDate=" +  purBillDate   +", applyBillId=" +  applyBillId   +", saleBillId=" +  saleBillId   +", provGoodsId=" +  provGoodsId   +", goodsGg=" +  goodsGg   +", goodsMrfsId=" +  goodsMrfsId   +", made=" +  made   +", hosGoodsId=" +  hosGoodsId   +", hosGoodsCode=" +  hosGoodsCode   +", hosGoodsName=" +  hosGoodsName   +", isUniqueCode=" +  isUniqueCode   +", batchCode=" +  batchCode   +", batchNum=" +  batchNum   +", sterilizationCode=" +  sterilizationCode   +", sterilizationEndDate=" +  sterilizationEndDate   +", sterilizationDate=" +  sterilizationDate   +", expdtEndDate=" +  expdtEndDate   +", productDate=" +  productDate   +", unrecieveQty=" +  unrecieveQty   +", qty=" +  qty   +", price=" +  price   +", revieveResult=" +  revieveResult   +", sumRows=" +  sumRows   +", receiveTemprature=" +  receiveTemprature   +", receiveHumidity=" +  receiveHumidity   +", status=" +  status   +", memo=" +  memo   +", distrBillId=" +  distrBillId   +", distrRowNumber=" +  distrRowNumber   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}