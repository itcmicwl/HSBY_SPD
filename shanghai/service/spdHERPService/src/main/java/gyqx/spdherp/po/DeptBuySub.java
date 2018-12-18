
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 请购单明细
*/
@Table(name="dept_buy_sub")
public class DeptBuySub implements IBean
{
	private static final long serialVersionUID = -2846317853947098941L;
	/**
	 * ID
	*/
	@Id
	@Size(max=36)
	@Column(name="id")
	@Title("ID")
	private String id ;
	/**
	 * 请领单ID
	*/
	@Size(max=36)
	@JoinColumn(name="bill_id",table="dept_buy_main",referencedColumnName="bill_id")
	@Title("请领单ID")
	private String billId ;
	/**
	 * 商品ID
	*/
	@Size(max=36)
	@Column(name="goods_id")
	@Title("商品ID")
	private String goodsId ;
	/**
	 * 设备科审核数量
	*/
	@Column(name="qty")
	@Title("设备科审核数量")
	private java.math.BigDecimal qty ;
	/**
	 * 请领科室ID
	*/
	@Size(max=36)
	@Column(name="buy_dept_id")
	@Title("请领科室ID")
	private String buyDeptId ;
	/**
	 * 医院ID
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 送货方式
	 * 10、自取 20、库房、30、直送
	*/
	@Column(name="send_type")
	@Title("送货方式")
	private int sendType ;
	/**
	 * 商品审核状态
	 * 待处理：10 已驳回：20 不采购：30 已采购：60 部分配送：40 全部配送：50
	*/
	@Column(name="sub_state")
	@Title("商品审核状态")
	private int subState ;
	/**
	 * 货源仓管意见
	*/
	@Size(max=128)
	@Column(name="warehouse_view")
	@Title("货源仓管意见")
	private String warehouseView ;
	/**
	 * 货源仓管处理时间
	*/
	@Column(name="warehouse_deal_time")
	@Title("货源仓管处理时间")
	private java.util.Date warehouseDealTime ;
	/**
	 * 货源仓管驳回原因类型
	 * 10停产 20更换耗材 30重新招标 40 其他
	*/
	@Column(name="warehouse_reject_type")
	@Title("货源仓管驳回原因类型")
	private int warehouseRejectType ;
	/**
	 * 货源仓管处理人
	*/
	@Size(max=36)
	@Column(name="warehouse_deal_man")
	@Title("货源仓管处理人")
	private String warehouseDealMan ;
	/**
	 * 行号
	*/
	@Column(name="row_number")
	@Title("行号")
	private int rowNumber ;
	/**
	 * 采购数量
	*/
	@Column(name="wh_qty")
	@Title("采购数量")
	private java.math.BigDecimal whQty ;
	/**
	 * 已发数量
	*/
	@Column(name="send_qty")
	@Title("已发数量")
	private java.math.BigDecimal sendQty ;
	/**
	 * 唯一码管理
	*/
	@Column(name="unique_kind")
	@Title("唯一码管理")
	private int uniqueKind ;
	/**
	 * 资金来源
	*/
	@Size(max=20)
	@Column(name="fund_type")
	@Title("资金来源")
	private String fundType ;
	/**
	 * 科室请购数量
	*/
	@Column(name="apply_qty")
	@Title("科室请购数量")
	private int applyQty ;
	/**
	 * 商品单价
	*/
	@Column(name="price")
	@Title("商品单价")
	private java.math.BigDecimal price ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 版本控制
	*/
	@Column(name="version")
	@Title("版本控制")
	private int version ;
	/**
	 * surId 手术包Id
	 */
	@Size(max=36)
	@Column(name="sur_id")
	@Title("surId")
	private String surId ;

	public String getId ()
	{
		return id ;
	}
	
	public void setId (String id )
	{
		this.id = id;
	}
	public String getBillId ()
	{
		return billId ;
	}
	
	public void setBillId (String billId )
	{
		this.billId = billId;
	}
	public String getGoodsId ()
	{
		return goodsId ;
	}
	
	public void setGoodsId (String goodsId )
	{
		this.goodsId = goodsId;
	}
	public java.math.BigDecimal getQty ()
	{
		return qty ;
	}
	
	public void setQty (java.math.BigDecimal qty )
	{
		this.qty = qty;
	}
	public String getBuyDeptId ()
	{
		return buyDeptId ;
	}
	
	public void setBuyDeptId (String buyDeptId )
	{
		this.buyDeptId = buyDeptId;
	}
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
	}
	public int getSendType ()
	{
		return sendType ;
	}
	
	public void setSendType (int sendType )
	{
		this.sendType = sendType;
	}
	public int getSubState ()
	{
		return subState ;
	}
	
	public void setSubState (int subState )
	{
		this.subState = subState;
	}
	public String getWarehouseView ()
	{
		return warehouseView ;
	}
	
	public void setWarehouseView (String warehouseView )
	{
		this.warehouseView = warehouseView;
	}
	public java.util.Date getWarehouseDealTime ()
	{
		return warehouseDealTime ;
	}
	
	public void setWarehouseDealTime (java.util.Date warehouseDealTime )
	{
		this.warehouseDealTime = warehouseDealTime;
	}
	public int getWarehouseRejectType ()
	{
		return warehouseRejectType ;
	}
	
	public void setWarehouseRejectType (int warehouseRejectType )
	{
		this.warehouseRejectType = warehouseRejectType;
	}
	public String getWarehouseDealMan ()
	{
		return warehouseDealMan ;
	}
	
	public void setWarehouseDealMan (String warehouseDealMan )
	{
		this.warehouseDealMan = warehouseDealMan;
	}
	public int getRowNumber ()
	{
		return rowNumber ;
	}
	
	public void setRowNumber (int rowNumber )
	{
		this.rowNumber = rowNumber;
	}
	public java.math.BigDecimal getWhQty ()
	{
		return whQty ;
	}
	
	public void setWhQty (java.math.BigDecimal whQty )
	{
		this.whQty = whQty;
	}
	public java.math.BigDecimal getSendQty ()
	{
		return sendQty ;
	}
	
	public void setSendQty (java.math.BigDecimal sendQty )
	{
		this.sendQty = sendQty;
	}
	public int getUniqueKind ()
	{
		return uniqueKind ;
	}
	
	public void setUniqueKind (int uniqueKind )
	{
		this.uniqueKind = uniqueKind;
	}
	public String getFundType ()
	{
		return fundType ;
	}
	
	public void setFundType (String fundType )
	{
		this.fundType = fundType;
	}
	public int getApplyQty ()
	{
		return applyQty ;
	}
	
	public void setApplyQty (int applyQty )
	{
		this.applyQty = applyQty;
	}
	public java.math.BigDecimal getPrice ()
	{
		return price ;
	}
	
	public void setPrice (java.math.BigDecimal price )
	{
		this.price = price;
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

	public String getSurId() {
		return surId;
	}

	public void setSurId(String surId) {
		this.surId = surId;
	}

	@Override
	public String toString() {
		return "DeptBuySub{" +
				"id='" + id + '\'' +
				", billId='" + billId + '\'' +
				", goodsId='" + goodsId + '\'' +
				", qty=" + qty +
				", buyDeptId='" + buyDeptId + '\'' +
				", hosId='" + hosId + '\'' +
				", sendType=" + sendType +
				", subState=" + subState +
				", warehouseView='" + warehouseView + '\'' +
				", warehouseDealTime=" + warehouseDealTime +
				", warehouseRejectType=" + warehouseRejectType +
				", warehouseDealMan='" + warehouseDealMan + '\'' +
				", rowNumber=" + rowNumber +
				", whQty=" + whQty +
				", sendQty=" + sendQty +
				", uniqueKind=" + uniqueKind +
				", fundType='" + fundType + '\'' +
				", applyQty=" + applyQty +
				", price=" + price +
				", lastUpdateDatetime=" + lastUpdateDatetime +
				", version=" + version +
				", surId='" + surId + '\'' +
				'}';
	}

}