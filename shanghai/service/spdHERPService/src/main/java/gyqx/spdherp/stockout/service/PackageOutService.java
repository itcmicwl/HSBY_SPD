package gyqx.spdherp.stockout.service;

import common.db.query.QueryResult;
import gyqx.spdherp.stockout.vo.*;

import java.util.List;

public interface PackageOutService {
    QueryResult<StockpileHzVo> listStockGoods(String stockId, Integer stocKind, String filter, int pageNum, int pageSize);
    List<IdNameVo> listOrgs();
    //List<PackageOutVo> parseHosPackageInfo(PackageOutVo packageOutVo) throws  Exception;
    String saveOutStockBill(PackageWhole packageWhole) throws Exception;
    String addPkg(PackageWhole packageWhole) throws Exception;
    PackageWhole parseHosPackageInfo(PackageWhole packageWhole) throws Exception;
}
