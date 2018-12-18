package gyqx.spdherp.deptMgr.vo;

public class StocInfoVo {
	private String id;
	private String hosId;
	private String stocName;
	private String stocLevel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getStocName() {
		return stocName;
	}

	public void setStocName(String stocName) {
		this.stocName = stocName;
	}

	public String getStocLevel() {
		return stocLevel;
	}

	public void setStocLevel(String stocLevel) {
		this.stocLevel = stocLevel;
	}

	@Override
	public String toString() {
		return "StocInfoVo [id=" + id + ", hosId=" + hosId + ", stocName=" + stocName + ", stocLevel=" + stocLevel
				+ "]";
	}

}
