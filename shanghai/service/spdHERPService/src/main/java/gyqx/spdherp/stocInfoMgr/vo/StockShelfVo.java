package gyqx.spdherp.stocInfoMgr.vo;

import gyqx.spdherp.po.StockShelf;

public class StockShelfVo extends StockShelf {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 区位编码或者货位编码
	 */
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
