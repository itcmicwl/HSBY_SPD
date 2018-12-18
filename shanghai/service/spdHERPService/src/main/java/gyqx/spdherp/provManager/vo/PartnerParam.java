package gyqx.spdherp.provManager.vo;

public class PartnerParam {
	/**
	 * 医院ID
	 */
	private String hosId;
	/**
	 * 集配商ID
	 */
	private String collectorId;
	/**
	 * 集配商名称
	 */
	private String cname;
	private Integer isUseSubprov;
	
	private String userId;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getCollectorId() {
		return collectorId;
	}

	public void setCollectorId(String collectorId) {
		this.collectorId = collectorId;
	}

	public Integer getIsUseSubprov() {
		return isUseSubprov;
	}

	public void setIsUseSubprov(Integer isUseSubprov) {
		this.isUseSubprov = isUseSubprov;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "PartnerParam [cname=" + cname + ", hosId=" + hosId + ", collectorId=" + collectorId + "]";
	}

}
