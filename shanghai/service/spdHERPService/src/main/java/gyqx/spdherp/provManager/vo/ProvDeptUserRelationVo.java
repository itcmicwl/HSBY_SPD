
package gyqx.spdherp.provManager.vo;

import java.util.List;

/**
 * 医院供应商Vo
 */
public class ProvDeptUserRelationVo {
	/**
	 * 医院ID
	 */
	private String hosId;
	
	private String provId;
	
	private String deptId;
	
	private String userId;
	
	private List<ProvDeptUserVo> userIds;
	
	private List<ProvHosRelationVo> provIds;
	
	private String ename;
	
	

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ProvHosRelationVo> getProvIds() {
		return provIds;
	}

	public void setProvIds(List<ProvHosRelationVo> provIds) {
		this.provIds = provIds;
	}

	public List<ProvDeptUserVo> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<ProvDeptUserVo> userIds) {
		this.userIds = userIds;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	

}