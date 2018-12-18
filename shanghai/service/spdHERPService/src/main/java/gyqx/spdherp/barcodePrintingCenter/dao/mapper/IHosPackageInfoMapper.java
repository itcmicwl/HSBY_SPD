package gyqx.spdherp.barcodePrintingCenter.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.HosPackageInfo;
import org.apache.ibatis.annotations.Param;

public interface IHosPackageInfoMapper {
	int update(HosPackageInfo bean);
	int insert(HosPackageInfo bean);
	List<HosPackageInfo> list(HosPackageInfo queryInfo) ;
	List<HosPackageInfo> listDsPacksByOutBillId(HosPackageInfo queryInfo) ;

	List<String> listPackageCodeByBillId(@Param("billId") String billId) ;
}

