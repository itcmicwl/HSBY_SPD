package gyqx.spdherp.applyMgr.vo;

import javax.persistence.Column;

import common.db.meta.Title;
import gyqx.spdhdi.myGoods.vo.ProHosGoodsInfoVo;

public class DeptBuyGoodsVo extends ProHosGoodsInfoVo{
	/**
	 * 商品数量
	*/
	@Column(name="qty")
	@Title("商品数量")
	private int qty ;
	
	/**
	 * 库存数量
	*/
	@Column(name=" stockQty")
	@Title("库存数量")
	private int stockQty ;
	
	/**
	 * 部门ID
	*/
	@Column(name=" deptId")
	@Title("部门ID")
	private String deptId;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}
	
	
}
