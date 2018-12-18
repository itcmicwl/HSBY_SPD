package gyqx.spdherp.stockout.dao;

import common.utils.UtilsContext;
import common.web.UserOnlineInfo;
import gyqx.spdherp.basedatamaint.vo.BarcodeParseResult;
import gyqx.spdherp.stockout.dao.mapper.PackageOutMapper;
import gyqx.spdherp.stockout.vo.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Repository
public class PackageOutDao {
    @Resource
    PackageOutMapper mapper;
    @Resource
    UtilsContext utilsContext;

    public List<StockpileHzVo> listStockGoods(String hosId, String stockId, Integer stocKind, String filter) {
        List<StockpileHzVo> result = mapper.listStockGoods(hosId, stockId, stocKind, filter);
        return result;
    }

    public List<IdNameVo> listOrgsByHosId(String hosId, String deptId) {
        return mapper.listOrgsByHosId(hosId, deptId);
    }


    public Collection<StockpileHzVo> listStockGoodsByGoodsIds(String hosId, String stockId, Integer stocKind, Collection<BarcodeParseResult> barcodeParseResults) {
        return mapper.listStockGoodsByGoodsIds(hosId, stockId, stocKind, barcodeParseResults);
    }

    public String modifyQty(String uniqueCode, String hosId) {
        return mapper.modifyQty(uniqueCode, hosId);
    }

    public List<PackageOutVo> parseHosPackageInfo(PackageOutVo packageOutVo) {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        String corpId = user.getCorpId();
        packageOutVo.setHosId(corpId);
        return mapper.parseHosPackageInfo(packageOutVo);
    }

    public int writeStatusPkg(String packageId) {
        return mapper.writeStatusPkg(packageId);
    }

    public PackageWhole getPkgResult(PackageWhole packageWhole) {
        packageWhole.setPackageInfos(mapper.listPgkInfo(packageWhole));
        List<HosPackageInfoVo> packageInfos = packageWhole.getPackageInfos();
        packageInfos.forEach(res -> {
            res.setHosPackageBatchDetailLst(mapper.listPkgBatchInfo(res));
        });
        return packageWhole;
    }
}
