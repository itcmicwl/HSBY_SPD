package gyqx.spdherp.reportAnalysis.vo;

public class UserDeptVo {
	
	private String deptId;
	
	private String deptName;
	
	private String userId;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserDeptVo [deptId=" + deptId + ", deptName=" + deptName + ", userId=" + userId + "]";
	}
	

}
