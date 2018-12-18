package gyqx.spdherp.provManager.vo;

import java.util.List;

public class PartnerImortParam {

	/**
	 * 集配商ID
	 */
	private String collectorId;
	/**
	 * 供应商列表
	 */
	private List<ProvHosInfoVo> provHosInfoVos;

	public String getCollectorId() {
		return collectorId;
	}

	public void setCollectorId(String collectorId) {
		this.collectorId = collectorId;
	}

	public List<ProvHosInfoVo> getProvHosInfoVos() {
		return provHosInfoVos;
	}

	public void setProvHosInfoVos(List<ProvHosInfoVo> provHosInfoVos) {
		this.provHosInfoVos = provHosInfoVos;
	}

}
