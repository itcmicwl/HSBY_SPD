package gyqx.spdherp.stockout.dao;

import gyqx.spdherp.stockout.dao.mapper.ReturnOutSupMapper;
import gyqx.spdherp.stockout.vo.IdNameVo;
import gyqx.spdherp.stockout.vo.IdNameVoSup;
import gyqx.spdherp.stockout.vo.StockpileHzVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cjzyw on 2018/5/18.
 */
@Repository
public class ReturnOutSupDao {
    @Resource
    private ReturnOutSupMapper mapper;

    public List<IdNameVoSup> listSup(String stockid, String hosid) {
        return mapper.listSupsForStoc(stockid,hosid);
    }

    public List<StockpileHzVo> listStockGoodsSup(String hosId, String stockId, Integer stocKind, String filter,String provId) {
        List<StockpileHzVo> result = mapper.listStockGoodsSup(hosId, stockId, stocKind, filter,provId);
        return result;
    }


}
