package gyqx.spdherp.deptMgr.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import common.db.meta.Title;

public class BaseAreaVo {

	/**
	 * ID
	 */
	@Id
	@Size(max = 36)
	@Column(name = "id")
	@Title("ID")
	private String id;
	/**
	 * 区域代码
	 */
	@Size(max = 36)
	@Column(name = "code")
	@Title("区域代码")
	private String code;
	/**
	 * 名称
	 */
	@Size(max = 36)
	@Column(name = "cname")
	@Title("名称")
	private String cname;
	/**
	 * 层次编码
	 */
	@Size(max = 36)
	@Column(name = "code_num")
	@Title("层次编码")
	private String codeNum;
	/**
	 * 上级ID
	 */
	@Size(max = 36)
	@Column(name = "pid")
	@Title("上级ID")
	private String pid;
	/**
	 * 数据版本
	 */
	@Column(name = "version")
	@Title("数据版本")
	private int version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
