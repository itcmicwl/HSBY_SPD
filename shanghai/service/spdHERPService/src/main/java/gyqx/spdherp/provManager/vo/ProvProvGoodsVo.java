package gyqx.spdherp.provManager.vo;

import java.math.BigDecimal;
import java.util.List;

public class ProvProvGoodsVo {

	private String id;
	private String hosId;
	private String collectorId;
	private String provId;

	private String provGoodsId;
	private String code;
	private String goodsName;
	private String goodsGg;
	private String mfrsName;
	private String unit;
	private BigDecimal packeage;
	private BigDecimal price;
	private String hosGoodsId;
	private List<String> provGoodsIds;
	
	public String getId() {
		return id;
	}
	public String getHosId() {
		return hosId;
	}
	public String getCollectorId() {
		return collectorId;
	}
	public String getProvId() {
		return provId;
	}
	public String getProvGoodsId() {
		return provGoodsId;
	}
	public String getCode() {
		return code;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public String getGoodsGg() {
		return goodsGg;
	}
	public String getMfrsName() {
		return mfrsName;
	}
	public String getUnit() {
		return unit;
	}
	public BigDecimal getPackeage() {
		return packeage;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setHosId(String hosId) {
		this.hosId = hosId;
	}
	public void setCollectorId(String collectorId) {
		this.collectorId = collectorId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	public void setProvGoodsId(String provGoodsId) {
		this.provGoodsId = provGoodsId;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public void setGoodsGg(String goodsGg) {
		this.goodsGg = goodsGg;
	}
	public void setMfrsName(String mfrsName) {
		this.mfrsName = mfrsName;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public void setPackeage(BigDecimal packeage) {
		this.packeage = packeage;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public List<String> getProvGoodsIds() {
		return provGoodsIds;
	}
	public void setProvGoodsIds(List<String> provGoodsIds) {
		this.provGoodsIds = provGoodsIds;
	}

	public String getHosGoodsId() {
		return hosGoodsId;
	}

	public void setHosGoodsId(String hosGoodsId) {
		this.hosGoodsId = hosGoodsId;
	}
}
