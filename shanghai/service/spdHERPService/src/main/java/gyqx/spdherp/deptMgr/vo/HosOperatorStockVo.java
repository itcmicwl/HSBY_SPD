
package gyqx.spdherp.deptMgr.vo;

/**
 * 人员库房授权Vo
 */
public class HosOperatorStockVo  {
	
	private String stockId ;

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	@Override
	public String toString() {
		return "HosOperatorStockVo [stockId=" + stockId + "]";
	}
}