package gyqx.spdherp.barcodePrintingCenter.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.HosPackageBatchDetail;
 
public interface IHosPackageBatchDetailMapper {
	int update(HosPackageBatchDetail bean);
	int insert(HosPackageBatchDetail bean);
	List<HosPackageBatchDetail> list(HosPackageBatchDetail queryInfo) ;
}

