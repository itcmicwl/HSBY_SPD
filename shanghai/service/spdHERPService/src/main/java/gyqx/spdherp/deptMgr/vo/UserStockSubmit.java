
package gyqx.spdherp.deptMgr.vo;

import java.util.List;

/**
 * 人员库房授权提交
 */
public class UserStockSubmit{
	/**
	 * ID
	 */
	private String id;
	/**
	 * 医院ID
	 */
	private String hosId;
	/**
	 * 人员ID
	 */
	private String userId;
	/**
	 * 库房ID
	 */
	private List<String> stockIds;
	/**
	 * 数据版本
	 */
	private int version;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getStockIds() {
		return stockIds;
	}

	public void setStockIds(List<String> stockIds) {
		this.stockIds = stockIds;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "UserStockSubmit [id=" + id + ", hosId=" + hosId + ", userId=" + userId + ", stockIds=" + stockIds
				+ ", version=" + version + "]";
	}

}