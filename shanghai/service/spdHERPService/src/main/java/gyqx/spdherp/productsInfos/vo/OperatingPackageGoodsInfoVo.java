
package gyqx.spdherp.productsInfos.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Size;

import common.db.meta.Title;

/**
 * 手术包对应品种
 */
public class OperatingPackageGoodsInfoVo {
	/**
	 * ID
	 */
	@Id
	@Size(max = 36)
	@Column(name = "id")
	@Title("ID")
	private String id;
	/**
	 * 手术包ID
	 */
	@Size(max = 36)
	@JoinColumn(name = "temp_id", table = "operating_package_info", referencedColumnName = "id")
	@Title("手术包ID")
	private String tempId;
	/**
	 * 产品ID
	 */
	@Size(max = 36)
	@Column(name = "goods_id")
	@Title("产品ID")
	private String goodsId;
	/**
	 * 数据版本
	 */
	@Column(name = "version")
	@Title("数据版本")
	private int version;

	// 添加数据
	private String hosId;
	private String goodsName;
	private List<String> goodsIds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public List<String> getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(List<String> goodsIds) {
		this.goodsIds = goodsIds;
	}

}