package gyqx.ws.yg.common;

import gyqx.ws.yg.service.IYgSendBillListService;
import gyqx.ws.yg.service.IYgSendBillListStateService;
import gyqx.ws.yg.service.IYgSendBillService;
import gyqx.ws.yg.service.impl.YgSendBillService;
import gyqx.ws.yg.vo.repVo.YY154_REP_DETAIL;
import gyqx.ws.yg.vo.repVo.YY154_REP_XML;
import gyqx.ws.yg.vo.repVo.YY155_REP_XML;
import gyqx.ws.yg.vo.repVo.YY161_REP_XML;
import gyqx.ws.yg.vo.reqVo.YY154_REQ_MAIN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by moonb on 2018/3/15.
 */
@Component
public class ImportSendBill {
    @Resource
    private YgServices ygServices;
    @Resource
    private IYgSendBillService sBillSer ;
    static Logger log = LoggerFactory.getLogger(ImportSendBill.class);
    public  ImportSendBillInfo importInfo = new ImportSendBillInfo();
    public void autoImport(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String jzrq = dateFormat.format(date);
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_MONTH, -1);
        date = ca.getTime();
        String qsrq = dateFormat.format(date);
        YY154_REQ_MAIN yy154M = new YY154_REQ_MAIN();
        yy154M.setJzrq(jzrq);
        yy154M.setQsrq(qsrq);
        this.start(yy154M);
    }
    public boolean start(final YY154_REQ_MAIN yy154M){
        synchronized (importInfo) {
            if (!importInfo.isEmpty()) {
                return false;
            }
            importInfo.setEmpty(false);
        }
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String importTime = dateFormat.format(date);
        log.info(String.format("%s开始导入配送单任务:日期范围是%s-%s",importTime,yy154M.getQsrq(),yy154M.getJzrq()));
        new Thread(()->{
            importBill(yy154M);
        }).start();
        return true;
    }

    public void importBill(YY154_REQ_MAIN yy154M){
        try {
            YY154_REP_XML res = ygServices.getSendBillList(yy154M);
            importInfo.setTotal(res.getDetail().size());
            Integer index =0;
            log.info("开始导入：共计"+importInfo.getTotal()+"条数据");
            for(YY154_REP_DETAIL bill : res.getDetail()){
                YY155_REP_XML y155 = ygServices.getSendBillByBillId(bill.getPsdbh());
                YY161_REP_XML y161 = ygServices.getSendBillStateByBillId(bill.getPsdbh());
                bill.setLstDeatil(y155.getDetail());
                bill.setLstState(y161.getDetail());
                sBillSer.insert(bill);
                importInfo.setBillId(bill.getPsdbh());
                importInfo.getBillLst().add(bill.getPsdbh());
                importInfo.setCur(++index);
            }
            Thread.sleep(6*1000);//等待前端调用，返回最后一次的进度信息
        }catch (Exception ex){
            log.error("导入出错",ex);
        }finally {
            importInfo.dispose();
        }

    }
}

