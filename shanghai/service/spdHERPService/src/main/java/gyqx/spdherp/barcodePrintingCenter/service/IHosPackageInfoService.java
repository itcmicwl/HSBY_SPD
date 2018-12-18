package gyqx.spdherp.barcodePrintingCenter.service;

import java.util.List;
import java.util.Map;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.barcodePrintingCenter.vo.HosPackageInfoVo;
import gyqx.spdherp.po.HosPackageInfo;
import gyqx.spdherp.stockout.vo.vo4In.OutSub4InVo;

public interface IHosPackageInfoService {
	
	List<HosPackageInfoVo> addHosPackageInfos(Map<String, List<OutSub4InVo>> map)  throws Exception;
	
	HosPackageInfo get(String id) throws Exception;

	HosPackageInfo add(HosPackageInfo hosPackageInfo) throws Exception;

	HosPackageInfo update(HosPackageInfo hosPackageInfo) throws Exception;

	List<HosPackageInfo> query(String predicate, String orderBy, Object... fieldValue) throws Exception;

	List<HosPackageInfo> list(HosPackageInfo bean) throws Exception;

	QueryResult<HosPackageInfo> listByPage(QueryInfo<HosPackageInfo> queryInfo) throws Exception;
	QueryResult<HosPackageInfo> listDsPacksByOutBillId(QueryInfo<HosPackageInfo> queryInfo) throws Exception;

	public List<String> listPackageCodeByBillId(String billId);


}
