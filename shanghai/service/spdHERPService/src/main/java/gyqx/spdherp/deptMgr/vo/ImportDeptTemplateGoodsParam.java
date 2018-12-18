package gyqx.spdherp.deptMgr.vo;

import java.util.List;

public class ImportDeptTemplateGoodsParam {
	
	private List<DeptGoodsInfoVo> goodsList;
	
	private String tempId;

	public List<DeptGoodsInfoVo> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<DeptGoodsInfoVo> goodsList) {
		this.goodsList = goodsList;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	@Override
	public String toString() {
		return "ImportDeptTemplateGoodsParam [goodsList=" + goodsList + ", tempId=" + tempId + "]";
	}
	
	
	
}
