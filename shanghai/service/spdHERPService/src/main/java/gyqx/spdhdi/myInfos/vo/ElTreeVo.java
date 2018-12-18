package gyqx.spdhdi.myInfos.vo;

import java.util.List;

public class ElTreeVo {
	private String id;
	private String label;
	private String nodeType;
	private boolean disabled  = false;

	private Object ob;
	private String code;
	private String pid;

	private  List<ElTreeVo> children;
	
	
	
	
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}

	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<ElTreeVo> getChildren() {
		return children;
	}
	public void setChildren(List<ElTreeVo> children) {
		this.children = children;
	}
	public Object getOb() {
		return ob;
	}
	public void setOb(Object ob) {
		this.ob = ob;
	}

}
