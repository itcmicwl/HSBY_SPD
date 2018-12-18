
package gyqx.spdhdi.myInfos.vo;

import java.util.List;

/**
 * 生产厂商信息Vo
 */
public class BasMfrsInfoVo {
	/**
	 * ID
	 */
	private String id;
	/**
	 * 生产厂商证件信息
	 */
	private List<BasMfrsCertificateVo> BasMfrsCertificateVos;
	/**
	 * 生产厂家类型
	 */
	private String mfrsKind;
	/**
	 * 厂家名称
	 */
	private String mfrsName;
	/**
	 * 生产许可证
	 */
	private String productCode;

	public BasMfrsInfoVo() {
		super();
	}

	public BasMfrsInfoVo(String id, List<BasMfrsCertificateVo> basMfrsCertificateVos, String mfrsKind, String mfrsName,
			String productCode) {
		super();
		this.id = id;
		BasMfrsCertificateVos = basMfrsCertificateVos;
		this.mfrsKind = mfrsKind;
		this.mfrsName = mfrsName;
		this.productCode = productCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<BasMfrsCertificateVo> getBasMfrsCertificateVos() {
		return BasMfrsCertificateVos;
	}

	public void setBasMfrsCertificateVos(List<BasMfrsCertificateVo> basMfrsCertificateVos) {
		BasMfrsCertificateVos = basMfrsCertificateVos;
	}

	public String getMfrsKind() {
		return mfrsKind;
	}

	public void setMfrsKind(String mfrsKind) {
		this.mfrsKind = mfrsKind;
	}

	public String getMfrsName() {
		return mfrsName;
	}

	public void setMfrsName(String mfrsName) {
		this.mfrsName = mfrsName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Override
	public String toString() {
		return "BasMfrsInfoVo [id=" + id + ", BasMfrsCertificateVos=" + BasMfrsCertificateVos + ", mfrsKind=" + mfrsKind
				+ ", mfrsName=" + mfrsName + ", productCode=" + productCode + "]";
	}

}