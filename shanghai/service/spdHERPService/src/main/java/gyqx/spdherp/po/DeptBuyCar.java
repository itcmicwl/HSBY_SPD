
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 请购车
*/
@Table(name="dept_buy_car")
public class DeptBuyCar implements IBean
{
	/**
	 * ID
	*/
	@Id
	@Size(max=128)
	@Column(name="id")
	@Title("ID")
	private String id ;
	/**
	 * 请购类型
	 * 10--实物请购20--虚拟请购
	*/
	@Column(name="buy_kind")
	@Title("请购类型")
	private int buyKind ;
	/**
	 * 采购模式
	 * 10--实物请购20--虚拟请购 30--办公
	*/
	@Column(name="pur_mode")
	@Title("采购模式")
	private int purMode ;
	/**
	 * 商品ID
	*/
	@Size(max=36)
	@Column(name="goods_id")
	@Title("商品ID")
	private String goodsId ;
	/**
	 * 请购商品数量
	*/
	@Column(name="need_qty")
	@Title("请购商品数量")
	private int needQty ;
	/**
	 * 部门ID
	*/
	@Size(max=36)
	@Column(name="dept_id")
	@Title("部门ID")
	private String deptId ;
	/**
	 * 医院ID
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 最后修改时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后修改时间")
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 版本控制
	*/
	@Column(name="version")
	@Title("版本控制")
	private int version ;

	/**
	 * 手术包定义ID
	 */
	@Column(name="sur_id")
	@Title("手术包定义ID")
	private String surId ;

	public String getId ()
	{
		return id ;
	}
	
	public void setId (String id )
	{
		this.id = id;
	}
	public int getBuyKind ()
	{
		return buyKind ;
	}
	
	public void setBuyKind (int buyKind )
	{
		this.buyKind = buyKind;
	}
	public String getGoodsId ()
	{
		return goodsId ;
	}
	
	public void setGoodsId (String goodsId )
	{
		this.goodsId = goodsId;
	}
	public int getNeedQty ()
	{
		return needQty ;
	}
	
	public void setNeedQty (int needQty )
	{
		this.needQty = needQty;
	}
	public String getDeptId ()
	{
		return deptId ;
	}
	
	public void setDeptId (String deptId )
	{
		this.deptId = deptId;
	}
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
	}
	public java.util.Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (java.util.Date lastUpdateDatetime )
	{
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	public int getPurMode() {
		return purMode;
	}

	public void setPurMode(int purMode) {
		this.purMode = purMode;
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
		return "DeptBuyCar{" +
				"id='" + id + '\'' +
				", buyKind=" + buyKind +
				", purMode=" + purMode +
				", goodsId='" + goodsId + '\'' +
				", needQty=" + needQty +
				", deptId='" + deptId + '\'' +
				", hosId='" + hosId + '\'' +
				", lastUpdateDatetime=" + lastUpdateDatetime +
				", version=" + version +
				", surId='" + surId + '\'' +
				'}';
	}

}