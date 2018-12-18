package gyqx.spdherp.provManager.vo;

import gyqx.spdhdi.po.ProvHosInfo;

public class DistriRelationVo extends ProvHosInfo {
	private static final long serialVersionUID = 1L;
	/**
	 * 配送关系类型
	 */
	private String kind;
	/**
	 * 是否启用二级供应商
	 */
	private String isUseSubprov;
	/**
	 * 集配商名称
	 */
	private String cname;
	/**
	 * 该字段两用： 医院集配商关系ID 集配商合作商关系ID
	 */
	private String cid;
	private String provName;
	
	private String userId;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getIsUseSubprov() {
		return isUseSubprov;
	}

	public void setIsUseSubprov(String isUseSubprov) {
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

		return "DistriRelationVo [kind=" + kind + ", isUseSubprov=" + isUseSubprov + ", cname=" + cname + ", cid=" + cid
				+ ", provName=" + provName + "]";
	}

}
