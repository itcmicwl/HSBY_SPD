package gyqx.spdherp.stocInfoMgr.vo;

import java.util.List;

public class StockShelfGoodsVo {

	private List<QueryResultVo> stockShelfGoods;
	private StockShelfVo stockShelfVo;

	public List<QueryResultVo> getStockShelfGoods() {
		return stockShelfGoods;
	}

	public void setStockShelfGoods(List<QueryResultVo> stockShelfGoods) {
		this.stockShelfGoods = stockShelfGoods;
	}

	public StockShelfVo getStockShelfVo() {
		return stockShelfVo;
	}

	public void setStockShelfVo(StockShelfVo stockShelfVo) {
		this.stockShelfVo = stockShelfVo;
	}

	@Override
	public String toString() {
		return "StockShelfGoodsVo [stockShelfGoods=" + stockShelfGoods + ", stockShelfVo=" + stockShelfVo + "]";
	}

}
