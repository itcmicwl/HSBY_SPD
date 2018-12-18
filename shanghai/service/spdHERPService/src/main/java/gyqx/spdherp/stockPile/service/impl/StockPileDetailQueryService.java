package gyqx.spdherp.stockPile.service.impl;

import javax.annotation.Resource;

import common.utils.UtilsContext;
import common.web.UserOnlineInfo;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;

import gyqx.spdherp.stockPile.dao.StockPileDetailQueryDao;
import gyqx.spdherp.stockPile.service.IStockPileDetailQueryService;
import gyqx.spdherp.stockPile.vo.StockPileDetailQueryVo;

@Service
public class StockPileDetailQueryService implements IStockPileDetailQueryService {

    @Resource
    private StockPileDetailQueryDao dao;
    @Resource
    private UtilsContext utilsContext;

    public QueryResult<StockPileDetailQueryVo> listByPage(QueryInfo<StockPileDetailQueryVo> queryInfo) throws Exception {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        String corpId = user.getCorpId();
        StockPileDetailQueryVo bean = queryInfo.getQueryObject();
        bean.setHosId(corpId);
        PageUtils.startPage(queryInfo);
        return PageUtils.endPage(dao.list(bean));
    }

}


