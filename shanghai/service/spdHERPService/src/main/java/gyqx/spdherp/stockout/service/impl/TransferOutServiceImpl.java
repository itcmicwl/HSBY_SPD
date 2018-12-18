package gyqx.spdherp.stockout.service.impl;

import common.db.SimpleDao;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.UtilsContext;
import common.web.UserOnlineInfo;
import gyqx.spdherp.stockout.dao.TransferOutDao;
import gyqx.spdherp.stockout.service.TransferOutService;
import gyqx.spdherp.stockout.vo.IdNameVo;
import gyqx.spdherp.stockout.vo.StockpileHzVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-9-8 下午3:32
 */
@Service
public class TransferOutServiceImpl implements TransferOutService {
    @Resource
    private TransferOutDao transferOutDao;

    @Resource
    private UtilsContext utilsContext;

    @Override
    public List<IdNameVo> listOrgs() {
        UserOnlineInfo user = utilsContext.getUserStateUtils().getCurrent();
        return transferOutDao.listOrgsByHosId(user.getCorpId(), user.getOrgId());
    }
}
