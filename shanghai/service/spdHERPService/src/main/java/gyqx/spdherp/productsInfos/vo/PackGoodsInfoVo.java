package gyqx.spdherp.productsInfos.vo;

/**
 * 手术包对应品种vo
 */
public class PackGoodsInfoVo{

	private String id;
	private String hosId;
	private String tempId; // 手术包id
	private String cname;  // 手术包名称
	private String goodsId;
	
	// 产品信息
	private String code ;	// 产品编号
	private String goodsName ;	// 产品名称
	private String goodsGg ;	// 规格型号
	private String made ;		// 产地
	private String mfrsId ;		// 生产厂商
	private String mfrsName ;	// 厂家名称
	private String unit ;		// 单位
	private java.math.BigDecimal packeage ; // 包装
	private java.math.BigDecimal price ;	// 单价

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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsGg() {
		return goodsGg;
	}

	public void setGoodsGg(String goodsGg) {
		this.goodsGg = goodsGg;
	}

	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
	}

	public String getMfrsId() {
		return mfrsId;
	}

	public void setMfrsId(String mfrsId) {
		this.mfrsId = mfrsId;
	}

	public String getMfrsName() {
		return mfrsName;
	}

	public void setMfrsName(String mfrsName) {
		this.mfrsName = mfrsName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public java.math.BigDecimal getPackeage() {
		return packeage;
	}

	public void setPackeage(java.math.BigDecimal packeage) {
		this.packeage = packeage;
	}

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}

}
