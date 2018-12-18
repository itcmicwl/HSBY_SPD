package gyqx.spdherp.productsInfos.vo;

import java.util.List;

public class GoodsKindTreeVo {
	private String id;
	private String label;
	private String nodeType;
	private String state;  //状态 10 启用 20 停用
	private String code;
	private String inCode;
	//private String pId;


	private String hosId;
	private  List<GoodsKindTreeVo> children;

	/*public String getPId() {
		return pId;
	}
	public void setPId(String pId) {
		this.pId = pId;
	}*/
	public String getHosId() {
		return hosId;
	}
	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getInCode() {
		return inCode;
	}

	public void setInCode(String inCode) {
		this.inCode = inCode;
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
	public List<GoodsKindTreeVo> getChildren() {
		return children;
	}
	public void setChildren(List<GoodsKindTreeVo> children) {
		this.children = children;
	}
	

}
