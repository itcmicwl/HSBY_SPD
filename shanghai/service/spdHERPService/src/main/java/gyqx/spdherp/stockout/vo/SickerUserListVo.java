package gyqx.spdherp.stockout.vo;

import gyqx.spdherp.po.SickerUseList;

/**
 * Created by moonb on 2018/4/24.
 */
public class SickerUserListVo extends SickerUseList {
    private String outOrgId;
	 private String outStocId;

	public String getOutOrgId() {
		return outOrgId;
	}

	public void setOutOrgId(String outOrgId) {
		this.outOrgId = outOrgId;
	}

	public String getOutStocId() {
		return outStocId;
	}

	public void setOutStocId(String outStocId) {
		this.outStocId = outStocId;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
