
package gyqx.spdherp.deptMgr.vo;

/**
 * 模板产品信息Vo
 */
public class TemplateGoodsVo extends DeptGoodsInfoVo {
	/**
	 * 模板产品ID
	 */
	private String tgId;
	/**
	 * 模板ID
	 */
	private String tempId;
	/**
	 * 模板名称
	 */
	private String cname;

	// 是否有商品图片
	private String imgAvailable;

	public String getTgId() {
		return tgId;
	}

	public void setTgId(String tgId) {
		this.tgId = tgId;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getImgAvailable() {
		return imgAvailable;
	}

	public void setImgAvailable(String imgAvailable) {
		this.imgAvailable = imgAvailable;
	}
}