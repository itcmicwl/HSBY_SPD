package gyqx.spdherp.reportAnalysis.service;

import java.util.List;

import gyqx.spdherp.provManager.vo.OutdateCertsVo;
import gyqx.spdherp.reportAnalysis.vo.CertificateStacsVo;

public interface IIndexStatisticsService {
	/**
	 * 查询医院供应商证各证照数量分布
	 * @param hosId 医院id
	 * @return
	 */
	public List<CertificateStacsVo> queryCertificateStacs(String hosId);
	
	/**
	 * 查询医院供应商证各证照过期情况
	 * @param hosId 医院id
	 * @return
	 */
	public List<OutdateCertsVo> queryProvOutdateCerts(String hosId);
}
