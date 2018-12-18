package gyqx.spdherp.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import common.db.meta.Title;

/**
 * 供应商科室人员关系实体
 */
@Table(name="prov_dept_user_relation")
public class ProvDeptUserRelation {
	/**
	 * ID
	*/
	@Id
	@Size(max=36)
	@Column(name="id")
	@Title("ID")
	private String id ;
	/**
	 * 医院ID
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId;
	
	/**
	 * 人员ID
	*/
	@Size(max=36)
	@Column(name="user_id")
	@Title("人员ID")
	private String userId ;
	
	/**
	 * 供应商ID
	*/
	@Size(max=36)
	@Column(name="prov_id")
	@Title("供应商ID")
	private String provId ;
	
	/**
	 * 创建人ID
	*/
	@Size(max=36)
	@Column(name="fillter")
	@Title("创建人ID")
	private String fillter ;
	
	/**
	 * 数据版本
	*/
	@Column(name="version")
	@Title("数据版本")
	private int version ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private java.util.Date lastUpdateDatetime ;
	
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
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	public String getFillter() {
		return fillter;
	}
	public void setFillter(String fillter) {
		this.fillter = fillter;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public java.util.Date getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}
	public void setLastUpdateDatetime(java.util.Date lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	@Override
	public String toString() {
		return "ProvDeptUsreRelation [id=" + id + ", hosId=" + hosId + ", userId=" + userId + ", provId=" + provId
				+ ", fillter=" + fillter + ", version=" + version + ", lastUpdateDatetime=" + lastUpdateDatetime + "]";
	}
	
}
