
package gyqx.spdherp.provManager.vo;

/**
 * 人员供应商关系vo
 */
public class ProvUserRelationVo {
	/**
	 * 医院ID
	 */
	private String hosId;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 供应商id
	 */
	private String provId;
	
	/**
	 * 供应商名称
	 */
	private String provName;

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

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}



}