package gyqx.spdherp.forRiva.service.impl;

import common.db.SimpleDao;
import gyqx.spdherp.forRiva.dao.AddReturnDao;
import gyqx.spdherp.forRiva.service.IAddReturnService;
import gyqx.spdherp.stockout.Constance;
import gyqx.spdherp.stockout.service.IFillBillService;
import gyqx.spdherp.stockout.service.ISickerUseService;
import gyqx.spdherp.stockout.vo.SickerUserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by cjzyw on 2018/6/1.
 */
@Service
public class AddReturnService implements IAddReturnService {
    @Resource
    private AddReturnDao addReturnDao;

    @Resource
    private IFillBillService fillBillService;

    @Resource
    private SimpleDao simpleDao;

    @Resource
    private ISickerUseService sickerUseService;

    @Override
    public SickerUserVo getSickerUseResult(String id) throws Exception {
        return addReturnDao.getSickerUseResult(id);
    }

    @Override
    public int stockpilebatch(SickerUserVo sickerUserVo) throws Exception {
        return 0;
    }

    @Override
    public int addSickerUse(String billId) throws Exception {
        String sql = "UPDATE sicker_use_list set status = 2 where p_id = ? and status = 1";
        SickerUserVo sickerUseResult = this.getSickerUseResult(billId);
        if (sickerUseResult == null) {
            return -1;
        }
        //调存储过程
        this.addReturnDao.stockpilebatch(sickerUseResult);
        if (Constance.SICKER_STATUS_SUMIT.equals(sickerUseResult.getStatus())) {
            //高值产品补实库单据
            fillBillService.createFillBillBySickerUse(sickerUseResult);

            sickerUseService.setOutBillStatus(sickerUseResult.getLstDetail());
            //更新消耗从表状态为2
            simpleDao.executeSql(sql, sickerUseResult.getId());
            return 0;
        } else {
            return -1;
        }
    }
}
