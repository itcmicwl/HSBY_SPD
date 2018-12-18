
package gyqx.spdherp.provManager.vo;

/**
 * 关系人的医院供应商关系vo
 */
public class ProvHosRelationVo {
	/**
	 * 医院ID
	 */
	private String hosId;
	
	/**
	 * 关系人id
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
	
	
	/**
	 * 联系人
	 */
	private String contactUser;
	
	/**
	 * 联系方式
	 */
	private String contactPattern;

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

	public String getContactUser() {
		return contactUser;
	}

	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}

	public String getContactPattern() {
		return contactPattern;
	}

	public void setContactPattern(String contactPattern) {
		this.contactPattern = contactPattern;
	}



}