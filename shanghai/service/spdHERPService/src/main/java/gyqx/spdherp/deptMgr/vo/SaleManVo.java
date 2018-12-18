package gyqx.spdherp.deptMgr.vo;

/**
 * 用于取业务员
 * 
 * @author LIWENKANG
 *
 */
public class SaleManVo {

	private String id;
	private String userId;
	private String ename;
	private String erpCode;
	private String orgId;
	private String orgName;
	private String orgErpCode;
	private String corpId;
	private String corpName;
	private String corpErpCode;

	private String hosId;
	private String hosGoodsId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getErpCode() {
		return erpCode;
	}

	public void setErpCode(String erpCode) {
		this.erpCode = erpCode;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgErpCode() {
		return orgErpCode;
	}

	public void setOrgErpCode(String orgErpCode) {
		this.orgErpCode = orgErpCode;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getCorpErpCode() {
		return corpErpCode;
	}

	public void setCorpErpCode(String corpErpCode) {
		this.corpErpCode = corpErpCode;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getHosGoodsId() {
		return hosGoodsId;
	}

	public void setHosGoodsId(String hosGoodsId) {
		this.hosGoodsId = hosGoodsId;
	}

	@Override
	public String toString() {
		return "SaleManVo [id=" + id + ", userId=" + userId + ", ename=" + ename + ", erpCode=" + erpCode + ", orgId="
				+ orgId + ", orgName=" + orgName + ", orgErpCode=" + orgErpCode + ", corpId=" + corpId + ", corpName="
				+ corpName + ", corpErpCode=" + corpErpCode + ", hosId=" + hosId + ", hosGoodsId=" + hosGoodsId + "]";
	}

}