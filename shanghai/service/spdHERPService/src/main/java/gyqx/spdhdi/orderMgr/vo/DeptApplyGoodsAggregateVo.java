
package gyqx.spdhdi.orderMgr.vo;

import javax.persistence.Column;

import common.db.meta.Title;
/**
 * 请购汇总数据
 * @author CHENJIANGHUA
 *
 */
public class DeptApplyGoodsAggregateVo 
{
	/**
	 * 名称
	*/
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
	 * 医院ID
	*/
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 科室ID
	*/
	@Column(name="dept_id")
	@Title("科室ID")
	private String deptId ;
	/**
	 * 请购会总数
	 */
	private java.math.BigDecimal qtyAggregate;
	/**
	 * 请购开始时间，设置查询范围用于查询
	 */
	private java.util.Date sDate;
	/**
	 * 请购结束时间，设置查询范围用于查询
	 */
	private java.util.Date eDate;

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public java.util.Date getsDate() {
		return sDate;
	}

	public void setsDate(java.util.Date sDate) {
		this.sDate = sDate;
	}

	public java.util.Date geteDate() {
		return eDate;
	}

	public void seteDate(java.util.Date eDate) {
		this.eDate = eDate;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Override
	public String toString() {
		return "DeptApplyGoodsAggregateVo [goodsName=" + goodsName + ", goodsGg=" + goodsGg + ", unit=" + unit
				+ ", goodsId=" + goodsId + ", hosId=" + hosId + ", deptId=" + deptId + ", qtyAggregate=" + qtyAggregate
				+ ", sDate=" + sDate + ", eDate=" + eDate + "]";
	}

	public String getGoodsGg() {
		return goodsGg;
	}

	public void setGoodsGg(String goodsGg) {
		this.goodsGg = goodsGg;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public java.math.BigDecimal getQtyAggregate() {
		return qtyAggregate;
	}

	public void setQtyAggregate(java.math.BigDecimal qtyAggregate) {
		this.qtyAggregate = qtyAggregate;
	} 
}