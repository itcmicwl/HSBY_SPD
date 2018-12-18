
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 科室请购单明细
*/
@Table(name="sup_apply_list")
public class SupApplyList implements IBean
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
	@JoinColumn(name="pid",table="sup_apply",referencedColumnName="id")
	@Title("pid")
	private String pid ;
	/**
	 * 单号
	*/
	@Size(max=36)
	@JoinColumn(name="bill_id",table="sup_apply",referencedColumnName="bill_id")
	@Title("单号")
	private String billId ;
	/**
	 * 行号
	*/
	@Column(name="row_number")
	@Title("行号")
	private int rowNumber ;
	/**
	 * 医院产品ID
	*/
	@Size(max=36)
	@JoinColumn(name="hos_goods_id",table="hos_goods_info",referencedColumnName="id")
	@Title("医院产品ID")
	private String hosGoodsId ;
	/**
	 * 医院产品编号
	*/
	@Size(max=36)
	@JoinColumn(name="hos_goods_code",table="hos_goods_info",referencedColumnName="code")
	@Title("医院产品编号")
	private String hosGoodsCode ;
	/**
	 * 医院内部名称
	*/
	@Size(max=36)
	@JoinColumn(name="hos_goods_name",table="hos_goods_info",referencedColumnName="goods_name")
	@Title("医院内部名称")
	private String hosGoodsName ;
	/**
	 * 单价
	*/
	@JoinColumn(name="hos_price",table="hos_goods_info",referencedColumnName="price")
	@Title("单价")
	private java.math.BigDecimal hosPrice ;
	/**
	 * 院内单位
	*/
	@Size(max=36)
	@JoinColumn(name="hos_unit",table="hos_goods_info",referencedColumnName="unit")
	@Title("院内单位")
	private String hosUnit ;
	/**
	 * 转换比率
	*/
	@Column(name="hos_scale")
	@Title("转换比率")
	private java.math.BigDecimal hosScale ;
	/**
	 * 院内单位请购数量
	*/
	@Column(name="hos_apply_qty")
	@Title("院内单位请购数量")
	private java.math.BigDecimal hosApplyQty ;
	/**
	 * 请购数量
	*/
	@Column(name="apply_qty")
	@Title("请购数量")
	private java.math.BigDecimal applyQty ;
	/**
	 * 院内单位数量
	*/
	@Column(name="unit_qty")
	@Title("院内单位数量")
	private java.math.BigDecimal unitQty ;
	/**
	 * 数量
	*/
	@Column(name="qty")
	@Title("数量")
	private java.math.BigDecimal qty ;
	/**
	 * 指定供应商
	*/
	@Size(max=36)
	@Column(name="prov_id")
	@Title("指定供应商")
	private String provId ;
	/**
	 * 取消数量
	*/
	@Column(name="cancle_qty")
	@Title("取消数量")
	private java.math.BigDecimal cancleQty ;
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
	public java.math.BigDecimal getHosPrice ()
	{
		return hosPrice ;
	}
	
	public void setHosPrice (java.math.BigDecimal hosPrice )
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
	public java.math.BigDecimal getHosScale ()
	{
		return hosScale ;
	}
	
	public void setHosScale (java.math.BigDecimal hosScale )
	{
		this.hosScale = hosScale;
	}
	public java.math.BigDecimal getHosApplyQty ()
	{
		return hosApplyQty ;
	}
	
	public void setHosApplyQty (java.math.BigDecimal hosApplyQty )
	{
		this.hosApplyQty = hosApplyQty;
	}
	public java.math.BigDecimal getApplyQty ()
	{
		return applyQty ;
	}
	
	public void setApplyQty (java.math.BigDecimal applyQty )
	{
		this.applyQty = applyQty;
	}
	public java.math.BigDecimal getUnitQty ()
	{
		return unitQty ;
	}
	
	public void setUnitQty (java.math.BigDecimal unitQty )
	{
		this.unitQty = unitQty;
	}
	public java.math.BigDecimal getQty ()
	{
		return qty ;
	}
	
	public void setQty (java.math.BigDecimal qty )
	{
		this.qty = qty;
	}
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public java.math.BigDecimal getCancleQty ()
	{
		return cancleQty ;
	}
	
	public void setCancleQty (java.math.BigDecimal cancleQty )
	{
		this.cancleQty = cancleQty;
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
		return "SupApplyList [" +" id=" +  id   +", pid=" +  pid   +", billId=" +  billId   +", rowNumber=" +  rowNumber   +", hosGoodsId=" +  hosGoodsId   +", hosGoodsCode=" +  hosGoodsCode   +", hosGoodsName=" +  hosGoodsName   +", hosPrice=" +  hosPrice   +", hosUnit=" +  hosUnit   +", hosScale=" +  hosScale   +", hosApplyQty=" +  hosApplyQty   +", applyQty=" +  applyQty   +", unitQty=" +  unitQty   +", qty=" +  qty   +", provId=" +  provId   +", cancleQty=" +  cancleQty   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}