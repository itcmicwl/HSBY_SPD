package gyqx.spdherp.stockout.dao.mapper;

import gyqx.spdherp.basedatamaint.vo.BarcodeParseResult;
import gyqx.spdherp.po.HosPackageBatchDetail;
import gyqx.spdherp.stockout.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Collection;
import java.util.List;

public interface PackageOutMapper {
    List<StockpileHzVo> listStockGoods(@Param("hosId") String hosId,
                                       @Param("stockId") String stockId,
                                       @Param("stocKind") Integer stocKind,
                                       @Param("filter") String filter);

    List<IdNameVo> listOrgsByHosId(@Param("hosId") String hosId,
                                   @Param("deptId") String deptId);


    Collection<StockpileHzVo> listStockGoodsByGoodsIds(@Param("hosId") String hosId,
                                                       @Param("stockId") String stockId,
                                                       @Param("stocKind") Integer stocKind,
                                                       @Param("goods") Collection<BarcodeParseResult> goods);

    String modifyQty(@Param("uniqueCode") String uniqueCode,
                     @Param("hosId") String hosId);

    List<PackageOutVo> parseHosPackageInfo(PackageOutVo packageOutVo);

    @Update("UPDATE hos_package_info set `status` = 2,version = version + 1,last_update_datetime = NOW() where package_id = #{packageId} and status = 1; ")
    int writeStatusPkg(String packageId);

    List<HosPackageInfoVo> listPgkInfo(PackageWhole packageWhole);

    List<HosPackageBatchDetailVo> listPkgBatchInfo(HosPackageInfoVo hosPackageInfoVo);
}
