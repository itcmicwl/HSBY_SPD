
package gyqx.spdherp.stocInfoMgr.vo;

public class QueryVo {

	/**
	 * 医疗机构ID
	 */
	private String hosId;

	/**
	 * 对应库房
	 */
	private String stocId;
	/**
	 * 产品名称
	 */
	private String goodsName;
	/**
	 * 库房名称
	 */
	private String stocName;

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getStocId() {
		return stocId;
	}

	public void setStocId(String stocId) {
		this.stocId = stocId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getStocName() {
		return stocName;
	}

	public void setStocName(String stocName) {
		this.stocName = stocName;
	}

	@Override
	public String toString() {
		return "QueryVo [hosId=" + hosId + ", stocId=" + stocId + ", goodsName=" + goodsName + "]";
	}

}