
package gyqx.spdherp.reportAnalysis.vo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;

import common.db.meta.Title;

public class DeptApplyGoodsUpperAnalysisVo 
{
	/**
	 * 请购部门Id 列表
	 */
	private List<String> deptIds;
	public List<String> getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}
	@Column(name="goods_name")
	@Title("名称")
	private String goodsName ;
	/**
	 * 规格型号
	*/
	@Column(name="goods_gg")
	@Title("规格型号")
	private String goodsGg ;
	/**
	 * 单位
	*/
	@Column(name="unit")
	@Title("单位")
	private String unit ;
	/**
	 * 商品ID
	*/
	@Column(name="goods_id")
	@Title("商品ID")
	private String goodsId ;
	/**
	 * fillYearMonth
	*/
	@Column(name="fill_year_month")
	@Title("fillYearMonth")
	private String fillYearMonth ;
	/**
	 * fillMonth
	*/
	@Column(name="fill_month")
	@Title("fillMonth")
	private String fillMonth ;
	/**
	 * monthQty
	*/
	@Column(name="monthQty")
	@Title("monthQty")
	private java.math.BigDecimal monthQty ;
	/**
	 * 医院ID
	*/
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 医院商品ID
	*/
	@Column(name="hos_goods_id")
	@Title("医院商品ID")
	private String hosGoodsId ;
	/**
	 * 科室ID
	*/
	@Column(name="dept_id")
	@Title("科室ID")
	private String deptId ;
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
	public String getUnit ()
	{
		return unit ;
	}
	
	public void setUnit (String unit )
	{
		this.unit = unit;
	}
	public String getGoodsId ()
	{
		return goodsId ;
	}
	
	public void setGoodsId (String goodsId )
	{
		this.goodsId = goodsId;
	}
	public String getFillYearMonth ()
	{
		return fillYearMonth ;
	}
	
	public void setFillYearMonth (String fillYearMonth )
	{
		this.fillYearMonth = fillYearMonth;
	}
	public String getFillMonth ()
	{
		return fillMonth ;
	}
	
	public void setFillMonth (String fillMonth )
	{
		this.fillMonth = fillMonth;
	}
	public java.math.BigDecimal getMonthQty ()
	{
		return monthQty ;
	}
	
	public void setMonthQty (java.math.BigDecimal monthQty )
	{
		this.monthQty = monthQty;
	}
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
	}
	public String getHosGoodsId ()
	{
		return hosGoodsId ;
	}
	
	public void setHosGoodsId (String hosGoodsId )
	{
		this.hosGoodsId = hosGoodsId;
	}
	public String getDeptId ()
	{
		return deptId ;
	}
	
	public void setDeptId (String deptId )
	{
		this.deptId = deptId;
	}
	public java.math.BigDecimal getM1Qty ()
	{
		return m1Qty ;
	}
	
	public void setM1Qty (java.math.BigDecimal m1Qty )
	{
		if(m1Qty==null){
			this.m1Qty = BigDecimal.ZERO;
		}else{
			this.m1Qty = m1Qty;
		}
	}
	public java.math.BigDecimal getM2Qty ()
	{
		return m2Qty ;
	}
	
	public void setM2Qty (java.math.BigDecimal m2Qty  )
	{
		if(m2Qty==null){
			this.m2Qty = BigDecimal.ZERO;
		}else{
			this.m2Qty = m2Qty;
		}
	}
	public java.math.BigDecimal getM3Qty ()
	{
		return m3Qty ;
	}
	
	public void setM3Qty (java.math.BigDecimal m3Qty )
	{
		if(m3Qty==null){
			this.m3Qty = BigDecimal.ZERO;
		}else{
			this.m3Qty = m3Qty;
		}
	}
	public java.math.BigDecimal getM4Qty ()
	{
		return m4Qty ;
	}
	
	public void setM4Qty (java.math.BigDecimal m4Qty )
	{
		if(m4Qty==null){
			this.m4Qty = BigDecimal.ZERO;
		}else{
			this.m4Qty = m4Qty;
		}
	}
	public java.math.BigDecimal getM5Qty ()
	{
		return m5Qty ;
	}
	
	public void setM5Qty (java.math.BigDecimal m5Qty )
	{
		if(m5Qty==null){
			this.m5Qty = BigDecimal.ZERO;
		}else{
			this.m5Qty = m5Qty;
		}
	}
	public java.math.BigDecimal getM6Qty ()
	{
		return m6Qty ;
	}
	
	public void setM6Qty (java.math.BigDecimal m6Qty )
	{
		if(m6Qty==null){
			this.m6Qty = BigDecimal.ZERO;
		}else{
			this.m6Qty = m6Qty;
		}
	}
	public java.math.BigDecimal getM7Qty ()
	{
		return m7Qty ;
	}
	
	public void setM7Qty (java.math.BigDecimal m7Qty )
	{
		if(m7Qty==null){
			this.m7Qty = BigDecimal.ZERO;
		}else{
			this.m7Qty = m7Qty;
		}
	}
	public java.math.BigDecimal getM8Qty ()
	{
		return m8Qty ;
	}
	
	public void setM8Qty (java.math.BigDecimal m8Qty )
	{
		if(m8Qty==null){
			this.m8Qty = BigDecimal.ZERO;
		}else{
			this.m8Qty = m8Qty;
		}
	}
	public java.math.BigDecimal getM9Qty ()
	{
		return m9Qty ;
	}
	
	public void setM9Qty (java.math.BigDecimal m9Qty )
	{
		if(m9Qty==null){
			this.m9Qty = BigDecimal.ZERO;
		}else{
			this.m9Qty = m9Qty;
		}
	}
	public java.math.BigDecimal getM10Qty ()
	{
		return m10Qty ;
	}
	
	public void setM10Qty (java.math.BigDecimal m10Qty )
	{
		if(m10Qty==null){
			this.m10Qty = BigDecimal.ZERO;
		}else{
			this.m10Qty = m10Qty;
		}
	}
	public java.math.BigDecimal getM11Qty ()
	{
		return m11Qty ;
	}
	
	public void setM11Qty (java.math.BigDecimal m11Qty )
	{
		if(m11Qty==null){
			this.m11Qty = BigDecimal.ZERO;
		}else{
			this.m11Qty = m11Qty;
		}
	}
	public java.math.BigDecimal getM12Qty ()
	{
		return m12Qty ;
	}
	
	public void setM12Qty (java.math.BigDecimal m12Qty )
	{
		if(m12Qty==null){
			this.m12Qty = BigDecimal.ZERO;
		}else{
			this.m12Qty = m12Qty;
		}
	}
	@Override
	public String toString() {
		return "DeptApplyGoodsUpperAnalysisVo ["  +" goodsName=" +  goodsName    +", goodsGg=" +  goodsGg    +", unit=" +  unit    +", goodsId=" +  goodsId    +", fillYearMonth=" +  fillYearMonth    +", fillMonth=" +  fillMonth    +", monthQty=" +  monthQty    +", hosId=" +  hosId    +", hosGoodsId=" +  hosGoodsId    +", deptId=" +  deptId    +", m1Qty=" +  m1Qty    +", m2Qty=" +  m2Qty    +", m3Qty=" +  m3Qty    +", m4Qty=" +  m4Qty    +", m5Qty=" +  m5Qty    +", m6Qty=" +  m6Qty    +", m7Qty=" +  m7Qty    +", m8Qty=" +  m8Qty    +", m9Qty=" +  m9Qty    +", m10Qty=" +  m10Qty    +", m11Qty=" +  m11Qty    +", m12Qty=" +  m12Qty   
			 + "]";
	}

}