package gyqx.spdherp.stockout.vo.vo4In;

import java.math.BigDecimal;
import java.util.List;

import gyqx.spdherp.po.OutStock;

public class OutBill4InVo extends OutStock {
	
	private static final long serialVersionUID = 1L;
	private List<Integer> arrStatus;
	private int goodsCount;
	private Double goodsSum;
	private Double amount;
	/**
	 * 是否定数包管理：非空即为定数包管理 add by xuLiqiang 2017/09/19
	 */
	private String isPacket;
	
	private List<OutSub4InVo> lstOutsubVo;

	public List<OutSub4InVo> getLstOutsubVo() {
		return lstOutsubVo;
	}

	public void setLstOutsubVo(List<OutSub4InVo> lstOutsubVo) {
		this.lstOutsubVo = lstOutsubVo;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public Double getGoodsSum() {
		return goodsSum;
	}

	public Double getAmount() {
		return amount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}

	public void setGoodsSum(Double goodsSum) {
		this.goodsSum = goodsSum;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getIsPacket() {
		return isPacket;
	}

	public void setIsPacket(String isPacket) {
		this.isPacket = isPacket;
	}

	public List<Integer> getArrStatus() {
		return arrStatus;
	}

	public void setArrStatus(List<Integer> arrStatus) {
		this.arrStatus = arrStatus;
	}
}
