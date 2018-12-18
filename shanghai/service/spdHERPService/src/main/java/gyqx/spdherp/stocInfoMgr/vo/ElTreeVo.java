package gyqx.spdherp.stocInfoMgr.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ElTreeVo {

	private String id; // 编号
	private String pid; // 父级编号
	private String label;
	private String nodeType;
	private Object obj;
	@JsonInclude(Include.NON_EMPTY)
	private List<ElTreeVo> children = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public List<ElTreeVo> getChildren() {
		return children;
	}

	public void setChildren(List<ElTreeVo> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", pid=" + pid + ", label=" + label + ", nodeType=" + nodeType + ", obj=" + obj
				+ ", children=" + children + "]";
	}

}
