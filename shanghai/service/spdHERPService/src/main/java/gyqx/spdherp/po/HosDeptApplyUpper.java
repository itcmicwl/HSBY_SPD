
package gyqx.spdherp.po;

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
 * 科室品种月请购上限
*/
@Table(name="hos_dept_apply_upper")
public class HosDeptApplyUpper implements IBean
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
	 * 医院ID
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 科室ID
	*/
	@Size(max=36)
	@Column(name="dept_id")
	@Title("科室ID")
	private String deptId ;
	/**
	 * 医院商品ID
	*/
	@Size(max=36)
	@Column(name="hos_goods_id")
	@Title("医院商品ID")
	private String hosGoodsId ;
	/**
	 * 一月数量
	*/
	@Column(name="m1_qty")
	@Title("一月数量")
	private java.math.BigDecimal m1Qty ;
	/**
	 * 二月数量
	*/
	@Column(name="m2_qty")
	@Title("二月数量")
	private java.math.BigDecimal m2Qty ;
	/**
	 * 三月数量
	*/
	@Column(name="m3_qty")
	@Title("三月数量")
	private java.math.BigDecimal m3Qty ;
	/**
	 * 四月数量
	*/
	@Column(name="m4_qty")
	@Title("四月数量")
	private java.math.BigDecimal m4Qty ;
	/**
	 * 五月数量
	*/
	@Column(name="m5_qty")
	@Title("五月数量")
	private java.math.BigDecimal m5Qty ;
	/**
	 * 六月数量
	*/
	@Column(name="m6_qty")
	@Title("六月数量")
	private java.math.BigDecimal m6Qty ;
	/**
	 * 七月数量
	*/
	@Column(name="m7_qty")
	@Title("七月数量")
	private java.math.BigDecimal m7Qty ;
	/**
	 * 八月数量
	*/
	@Column(name="m8_qty")
	@Title("八月数量")
	private java.math.BigDecimal m8Qty ;
	/**
	 * 九月数量
	*/
	@Column(name="m9_qty")
	@Title("九月数量")
	private java.math.BigDecimal m9Qty ;
	/**
	 * 十月数量
	*/
	@Column(name="m10_qty")
	@Title("十月数量")
	private java.math.BigDecimal m10Qty ;
	/**
	 * 十一月数量
	*/
	@Column(name="m11_qty")
	@Title("十一月数量")
	private java.math.BigDecimal m11Qty ;
	/**
	 * 十二月数量
	*/
	@Column(name="m12_qty")
	@Title("十二月数量")
	private java.math.BigDecimal m12Qty ;
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
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
	}
	public String getDeptId ()
	{
		return deptId ;
	}
	
	public void setDeptId (String deptId )
	{
		this.deptId = deptId;
	}
	public String getHosGoodsId ()
	{
		return hosGoodsId ;
	}
	
	public void setHosGoodsId (String hosGoodsId )
	{
		this.hosGoodsId = hosGoodsId;
	}
	public java.math.BigDecimal getM1Qty ()
	{
		return m1Qty ;
	}
	
	public void setM1Qty (java.math.BigDecimal m1Qty )
	{
		this.m1Qty = m1Qty;
	}
	public java.math.BigDecimal getM2Qty ()
	{
		return m2Qty ;
	}
	
	public void setM2Qty (java.math.BigDecimal m2Qty )
	{
		this.m2Qty = m2Qty;
	}
	public java.math.BigDecimal getM3Qty ()
	{
		return m3Qty ;
	}
	
	public void setM3Qty (java.math.BigDecimal m3Qty )
	{
		this.m3Qty = m3Qty;
	}
	public java.math.BigDecimal getM4Qty ()
	{
		return m4Qty ;
	}
	
	public void setM4Qty (java.math.BigDecimal m4Qty )
	{
		this.m4Qty = m4Qty;
	}
	public java.math.BigDecimal getM5Qty ()
	{
		return m5Qty ;
	}
	
	public void setM5Qty (java.math.BigDecimal m5Qty )
	{
		this.m5Qty = m5Qty;
	}
	public java.math.BigDecimal getM6Qty ()
	{
		return m6Qty ;
	}
	
	public void setM6Qty (java.math.BigDecimal m6Qty )
	{
		this.m6Qty = m6Qty;
	}
	public java.math.BigDecimal getM7Qty ()
	{
		return m7Qty ;
	}
	
	public void setM7Qty (java.math.BigDecimal m7Qty )
	{
		this.m7Qty = m7Qty;
	}
	public java.math.BigDecimal getM8Qty ()
	{
		return m8Qty ;
	}
	
	public void setM8Qty (java.math.BigDecimal m8Qty )
	{
		this.m8Qty = m8Qty;
	}
	public java.math.BigDecimal getM9Qty ()
	{
		return m9Qty ;
	}
	
	public void setM9Qty (java.math.BigDecimal m9Qty )
	{
		this.m9Qty = m9Qty;
	}
	public java.math.BigDecimal getM10Qty ()
	{
		return m10Qty ;
	}
	
	public void setM10Qty (java.math.BigDecimal m10Qty )
	{
		this.m10Qty = m10Qty;
	}
	public java.math.BigDecimal getM11Qty ()
	{
		return m11Qty ;
	}
	
	public void setM11Qty (java.math.BigDecimal m11Qty )
	{
		this.m11Qty = m11Qty;
	}
	public java.math.BigDecimal getM12Qty ()
	{
		return m12Qty ;
	}
	
	public void setM12Qty (java.math.BigDecimal m12Qty )
	{
		this.m12Qty = m12Qty;
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
		return "HosDeptApplyUpper [" +" id=" +  id   +", hosId=" +  hosId   +", deptId=" +  deptId   +", hosGoodsId=" +  hosGoodsId   +", m1Qty=" +  m1Qty   +", m2Qty=" +  m2Qty   +", m3Qty=" +  m3Qty   +", m4Qty=" +  m4Qty   +", m5Qty=" +  m5Qty   +", m6Qty=" +  m6Qty   +", m7Qty=" +  m7Qty   +", m8Qty=" +  m8Qty   +", m9Qty=" +  m9Qty   +", m10Qty=" +  m10Qty   +", m11Qty=" +  m11Qty   +", m12Qty=" +  m12Qty   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}