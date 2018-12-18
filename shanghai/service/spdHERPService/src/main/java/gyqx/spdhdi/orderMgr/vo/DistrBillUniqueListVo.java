package gyqx.spdhdi.orderMgr.vo;

import java.io.Serializable;

/**
 * 请购配送单唯一码明细
 */
public class DistrBillUniqueListVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6379401495310551560L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * pid
	 */
	private String pid;
	/**
	 * 配送单行号
	 */
	private int distrRowNumber;
	/**
	 * 配送商产品ID
	 */
	private String provGoodsId;
	private String goodsName;
	

	/**
	 * 产品批次
	 */
	private String batchCode;
	/**
	 * 唯一码
	 */
	private String uniqueCode;
	/**
	 * 数据来源
	 */
	private int sourceData;
	/**
	 * 数据版本
	 */
	private int version;

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

	public int getDistrRowNumber() {
		return distrRowNumber;
	}

	public void setDistrRowNumber(int distrRowNumber) {
		this.distrRowNumber = distrRowNumber;
	}

	public String getProvGoodsId() {
		return provGoodsId;
	}

	public void setProvGoodsId(String provGoodsId) {
		this.provGoodsId = provGoodsId;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public int getSourceData() {
		return sourceData;
	}

	public void setSourceData(int sourceData) {
		this.sourceData = sourceData;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	@Override
	public String toString() {
		return "DistrBillUniqueList [" + " id=" + id + ", pid=" + pid + ", distrRowNumber=" + distrRowNumber
				+ ", provGoodsId=" + provGoodsId + ", batchCode=" + batchCode + ", uniqueCode=" + uniqueCode
				+ ", sourceData=" + sourceData + ", version=" + version + "]";
	}
}