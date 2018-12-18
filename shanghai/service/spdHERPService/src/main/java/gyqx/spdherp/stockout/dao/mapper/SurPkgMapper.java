package gyqx.spdherp.stockout.dao.mapper;

import gyqx.spdherp.stockout.vo.EntireDeptBuyVo;
import gyqx.spdherp.stockout.vo.SurPkgBatchVo;
import gyqx.spdherp.stockout.vo.SurPkgVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author liangwu
 * @Date 18-10-8 下午2:17
 */
public interface SurPkgMapper {
    List<SurPkgVo> listSurPkg(@Param("stockId") String stockId,
                              @Param("deptId") String deptId,
                              @Param("hosId") String hosId,
                              @Param("pkgDabao") int pkgDabao);

    @Select("SELECT count(id) FROM hos_operator_stock WHERE hos_id = #{hosId} AND user_id = #{userId} AND stock_id = #{stockId}")
    int checkUserOpStock(@Param("hosId") String hosId,
                         @Param("userId") String userId,
                         @Param("stockId") String stockId);

    SurPkgVo getSurPkg(@Param("pkgCode") String pkgCode,
                       @Param("hosId") String hosId,
                       @Param("pkgDabao") int pkgDabao);

    EntireDeptBuyVo getEntireDeptBuyVo(@Param("id") String id);

    int updateTakingStock(@Param("pkgCodeList") List<String> pkgCode,
                          @Param("outStockBillId") String outStockBillId);

    int updateSurPkg(@Param("ids") List<String> ids,
                     @Param("pkgDabao") int pkgDabao,
                     @Param("pkgQgck") int pkgQgck);

    int insertSurLog(@Param("surCodeList") List<String> surCodeList,
                     @Param("billId") String billId,
                     @Param("userId") String userId,
                     @Param("desc") String desc);

    List<SurPkgVo> getSurPkgs(@Param("pkgCodeList") List<String> pkgCodeList,
                              @Param("hosId") String hosId,
                              @Param("pkgDabao") int pkgDabao);
}
