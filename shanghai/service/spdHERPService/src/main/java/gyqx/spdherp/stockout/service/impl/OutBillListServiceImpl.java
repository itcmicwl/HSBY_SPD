package gyqx.spdherp.stockout.service.impl;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.UtilsContext;
import gyqx.spdherp.stockout.dao.OutBillListDao;
import gyqx.spdherp.stockout.service.OutBillListService;
import gyqx.spdherp.stockout.vo.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class OutBillListServiceImpl implements OutBillListService {
    @Resource
    private UtilsContext utilsContext;

    @Resource
    private OutBillListDao outBillListDao;

    @Resource
    private SimpleDao simpleDao;


    @Override
    public List<EnameValVo> getOutStatus() throws Exception {
        return outBillListDao.getOutStatus();
    }

    @Override
    public QueryResult<OutBillListVo> listByPage(QueryInfo<OutBillListVo> queryInfo) throws Exception {
        PageUtils.startPage(queryInfo);
        return PageUtils.endPage(outBillListDao.getOutBillList(queryInfo.getQueryObject()));

    }

    @Override
    public OutBillListVo getByBillId(OutBillListVo outBillListVo) throws Exception {
        return outBillListDao.getOutBillList(outBillListVo).get(0);
    }
}
