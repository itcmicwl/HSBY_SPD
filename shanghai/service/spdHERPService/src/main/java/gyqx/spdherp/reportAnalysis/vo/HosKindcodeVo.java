package gyqx.spdherp.reportAnalysis.vo;

import java.io.Serializable;



/**
 * 医院产品分类信息vo
 * @author Administrator
 *
 */
public class HosKindcodeVo implements Serializable {

	private String id;
	
	private String kindName;
	
	private String pid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
}
