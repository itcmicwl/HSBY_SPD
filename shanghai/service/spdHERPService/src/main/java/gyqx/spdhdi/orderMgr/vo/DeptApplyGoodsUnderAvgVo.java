
package gyqx.spdhdi.orderMgr.vo;

import java.math.BigDecimal;

import javax.persistence.Column;

import common.db.meta.Title;
/**
 * 低于周请购查询分析
 * @author CHENJIANGHUA
 *
 */
public class DeptApplyGoodsUnderAvgVo 
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
	 * 供货机构
	 */
	private String provId;
	
	/**
	 * 周请购平均值
	 */
	private BigDecimal weekQtyAvg;
	
	/**
	 * 本周请购数量
	 */
	private BigDecimal curWeekQty;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public String getHosId() {
		return hosId;
	}

	@Override
	public String toString() {
		return "DeptApplyGoodsUnderAvgVo [goodsName=" + goodsName + ", goodsGg=" + goodsGg + ", unit=" + unit
				+ ", goodsId=" + goodsId + ", hosId=" + hosId + ", deptId=" + deptId + ", provId=" + provId
				+ ", weekQtyAvg=" + weekQtyAvg + ", curWeekQty=" + curWeekQty + "]";
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

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public BigDecimal getWeekQtyAvg() {
		return weekQtyAvg;
	}

	public void setWeekQtyAvg(BigDecimal weekQtyAvg) {
		this.weekQtyAvg = weekQtyAvg;
	}

	public BigDecimal getCurWeekQty() {
		return curWeekQty;
	}

	public void setCurWeekQty(BigDecimal curWeekQty) {
		this.curWeekQty = curWeekQty;
	}
}