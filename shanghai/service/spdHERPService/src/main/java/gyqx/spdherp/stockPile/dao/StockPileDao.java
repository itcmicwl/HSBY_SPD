package gyqx.spdherp.stockPile.dao;

import java.util.List;
import gyqx.spdherp.stockPile.dao.mapper.IStockPileMapper;
import gyqx.spdherp.stockPile.vo.DeptGoodsVo;
import gyqx.spdherp.stockPile.vo.StockPileHZVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by zouliang on 2017-8-22.
 */
@Repository
public class StockPileDao {
    @Resource
    private IStockPileMapper mapper;

    public List<StockPileHZVo> listStockPileHZ(StockPileHZVo queryInfo){
        return mapper.listStockPileHZ(queryInfo);
    }

    /**
     * 获取要自动补货的品种
     * @param dgv
     * @return
     */
    public List<DeptGoodsVo> getGoodsInfo4AutoPur(DeptGoodsVo dgv){
        return mapper.getGoodsInfo4AutoPur(dgv);
    }
}
