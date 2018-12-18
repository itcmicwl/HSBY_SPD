package gyqx.spdherp.barcodePrintingCenter.vo;

import gyqx.spdherp.po.HosPackageBatchDetail;
import gyqx.spdherp.po.HosPackageInfo;

import java.util.ArrayList;
import java.util.List;

public class HosPackageInfoVo extends HosPackageInfo {

	private static final long serialVersionUID = 1L;

	private List<HosPackageBatchDetail> hosPackageBatchDetailLst = new ArrayList();

	public List<HosPackageBatchDetail> getHosPackageBatchDetailLst() {
		return hosPackageBatchDetailLst;
	}

	public void setHosPackageBatchDetailLst(List<HosPackageBatchDetail> hosPackageBatchDetailLst) {
		this.hosPackageBatchDetailLst = hosPackageBatchDetailLst;
	}
}
