package gyqx.spdherp.stockPile.service.impl;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.UtilsContext;
import common.web.UserOnlineInfo;
import gyqx.spdherp.stockPile.dao.StockPileDao;
import gyqx.spdherp.stockPile.service.IStockPileService;
import gyqx.spdherp.stockPile.vo.DeptGoodsVo;
import gyqx.spdherp.stockPile.vo.StockPileHZVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zouliang on 2017-8-22.
 */
@Service
public class StockPileService implements IStockPileService {
    @Resource
    private StockPileDao dao;
    @Resource
    private UtilsContext utilsContext;

    public QueryResult<StockPileHZVo> stockPileHZ(QueryInfo<StockPileHZVo> bean) throws Exception {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        String corpId = user.getCorpId();
        StockPileHZVo queryInfo=bean.getQueryObject();
        queryInfo.setHosId(corpId);
        PageUtils.startPage(bean);
        return PageUtils.endPage(dao.listStockPileHZ(queryInfo));
    }

    @Override
    public QueryResult<DeptGoodsVo> getGoodsInfo4AutoPurByPage(QueryInfo<DeptGoodsVo> bean) throws Exception {
        PageUtils.startPage(bean);
        return PageUtils.endPage(dao.getGoodsInfo4AutoPur(bean.getQueryObject()));
    }

    @Override
    public List<DeptGoodsVo> getGoodsInfo4AutoPur(DeptGoodsVo dgv) throws Exception {
        return dao.getGoodsInfo4AutoPur(dgv);
    }
}
