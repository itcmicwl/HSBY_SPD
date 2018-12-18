package gyqx.spdherp.forRiva.service.impl;

import cn.rivamed.entity.PrintData;
import common.exception.ExceptionMessage;
import common.utils.UtilsContext;
import gyqx.api.forRiva.service.IStockCaller;
import gyqx.spdhdi.orderMgr.service.IDistrBillService;
import gyqx.spdhdi.orderMgr.vo.DistrBillListVo;
import gyqx.spdherp.forRiva.dao.PrintForRivaDao;
import gyqx.spdherp.forRiva.service.IPrintForRivaService;
import gyqx.spdherp.forRiva.vo.CheckCode;
import gyqx.spdherp.forRiva.vo.ForRivaH02;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjzyw on 2018/6/1.
 */
@Service
public class PrintForRivaService implements IPrintForRivaService{
    private static Logger log = LoggerFactory.getLogger(PrintForRivaService.class);
    @Resource
    private PrintForRivaDao printForRivaDao;
    @Resource(name="riva.cabCaller")
    private IStockCaller iStockCaller;
    @Resource
    private UtilsContext utilsContext;
    @Resource
    private IDistrBillService iDistrBillService;

    @Override
    public String receiveJfCode(String goodsId, String hosId) throws Exception {
        return printForRivaDao.receiveJfCode(goodsId,hosId);
    }

    @Override
    public DistrBillListVo getById(String id) throws Exception {
        return printForRivaDao.getById(id);
    }

    /**
     * 高值耗材采购入库打印条码
     * @param forRivaH02
     * @return
     * @throws Exception
     */
    @Override
    public String printSer(ForRivaH02 forRivaH02) throws Exception {
        String hosId = utilsContext.getUserStateUtils().getCurrent().getCorpId();
        List<PrintData> printDataList = new ArrayList<>();
        List<CheckCode> checkCodes = forRivaH02.getCheckCodes();
        for(CheckCode cc:checkCodes){
            PrintData printData = new PrintData();
            DistrBillListVo byId = this.getById(cc.getPid());
            printData.setJfCode(this.receiveJfCode(byId.getHosGoodsId(),hosId));
            printData.setEpc(cc.getUniqueCode());
            printData.setSpec(cc.getGoodsGg());
            printData.setProductName(cc.getGoodsName());
            printData.setProductCode(byId.getHosGoodsId());
            printData.setIdCode(cc.getUniqueCode());
            printData.setExpiryDate(cc.getExpdtEndDate());
            printData.setBatchNum(cc.getBatchCode());
            printDataList.add(printData);
        }
        try {
            iStockCaller.print(printDataList);
        } catch (Exception e) {
            log.error("#############################调用打印接口{}, 打印条码失败, 错误信息: => {}",
                    ExceptionMessage.fromException(e));
            return "fail";
        }
        return "ok";
    }

}
