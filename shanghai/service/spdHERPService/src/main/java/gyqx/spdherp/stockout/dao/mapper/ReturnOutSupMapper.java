package gyqx.spdherp.stockout.dao.mapper;

import gyqx.spdherp.stockout.vo.IdNameVoSup;
import gyqx.spdherp.stockout.vo.StockpileHzVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by cjzyw on 2018/5/18.
 */
public interface ReturnOutSupMapper {
    List<IdNameVoSup> listSupsForStoc(@Param("stockid") String hosId,
                                      @Param("hosid") String deptId);

    List<StockpileHzVo> listStockGoodsSup(@Param("hosId") String hosId,
                                       @Param("stockId") String stockId,
                                       @Param("stocKind") Integer stocKind,
                                       @Param("filter") String filter,
                                          @Param("provId")String provId);
}
