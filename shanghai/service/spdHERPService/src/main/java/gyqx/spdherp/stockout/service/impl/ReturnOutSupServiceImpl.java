package gyqx.spdherp.stockout.service.impl;

import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.UtilsContext;
import common.web.UserOnlineInfo;
import gyqx.spdherp.stockout.dao.ReturnOutSupDao;
import gyqx.spdherp.stockout.service.ReturnOutSupService;
import gyqx.spdherp.stockout.vo.IdNameVo;
import gyqx.spdherp.stockout.vo.IdNameVoSup;
import gyqx.spdherp.stockout.vo.StockpileHzVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cjzyw on 2018/5/18.
 */
@Service
public class ReturnOutSupServiceImpl implements ReturnOutSupService {

    @Resource
    private ReturnOutSupDao returnOutSupDao;

    @Resource
    private UtilsContext utilsContext;

    @Override
    public List<IdNameVoSup> listSup(String stockid) {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        return returnOutSupDao.listSup(stockid,user.getCorpId());
    }


    @Override
    public QueryResult<StockpileHzVo> listStockGoodsSup(String stockId, Integer stocKind, String filter, int pageNum, int pageSize,String provId) {
        String hosId = utilsContext.getUserStateUtils().getCurrent().getCorpId();
        PageUtils.startPage(pageNum, pageSize);
        return PageUtils.endPage(returnOutSupDao.listStockGoodsSup(hosId, stockId, stocKind, filter,provId));
    }
}
