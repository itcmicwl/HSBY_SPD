package gyqx.spdherp.stocInfoMgr.vo;

import java.util.Date;

/**
 * 库位管理
 * 
 * @date 2017年8月21日 上午11:54:23
 */
public class StocShelfVo {

	/**
	 * ID
	 */
	private String id;
	/**
	 * 上级ID
	 */
	private String pid;
	/**
	 * 医院ID
	 */
	private String hosId;
	/**
	 * 仓库ID
	 */
	private String provId;
	/**
	 * 区位编码
	 */
	private String areaCode;
	/**
	 * 货位编码
	 */
	private String shelfCode;
	/**
	 * 区位名称
	 */
	private String areaName;
	/**
	 * 货位名称
	 */
	private String shelfName;
	/**
	 * 货位类型
	 */
	private String shelfType;
	/**
	 * 类型 10--区位，20--货位
	 */
	private int kind;
	/**
	 * 数据版本
	 */
	private int version;

	// 添加查询
	private String code;

	private Date lastUpdateDatetime;

	public String getShelfType() {
		return shelfType;
	}

	public void setShelfType(String shelfType) {
		this.shelfType = shelfType;
	}

	public Date getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}

	public void setLastUpdateDatetime(Date lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getShelfCode() {
		return shelfCode;
	}

	public void setShelfCode(String shelfCode) {
		this.shelfCode = shelfCode;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
}