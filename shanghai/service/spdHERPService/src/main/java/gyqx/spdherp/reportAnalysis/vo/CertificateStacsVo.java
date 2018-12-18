package gyqx.spdherp.reportAnalysis.vo;

public class CertificateStacsVo {

	private String hosId;
	/**
	 * 证照类型
	 */
	private String certKind;
	/**
	 * 数量
	 */
	private Integer qty;
	
	public String getCertKind() {
		return certKind;
	}
	public void setCertKind(String certKind) {
		this.certKind = certKind;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	
	public String getHosId() {
		return hosId;
	}
	public void setHosId(String hosId) {
		this.hosId = hosId;
	}
	@Override
	public String toString() {
		return "CertificateStacsVo [hosId=" + hosId + ", certKind=" + certKind + ", qty=" + qty + "]";
	}
	
}
