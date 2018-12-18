package gyqx.ws.yg.controller;

import com.mysql.jdbc.StringUtils;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.settleCenter.vo.HosOutBalanceVo;
import gyqx.ws.yg.common.*;
import gyqx.ws.yg.service.*;
import gyqx.ws.yg.vo.SMPType;
import gyqx.ws.yg.vo.repVo.*;
import gyqx.ws.yg.vo.reqVo.*;
import gyqx.ws.yg.vo.ygVo.YgCompanyInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;


/**
 * 阳光平台配送单服务
 * Created by moonb on 2018/2/11.
 */
@Controller
@RequestMapping("yg/ygSend")
public class YgSendBillController extends BaseController {
    @Resource
    private YgSendUtil ygUtils;
    @Resource
    private YgServices ygServices;
    @Resource
    ImportSendBill importSBill ;
    @Resource
    private IYgSendBillService ygSendBillSer;
    @Resource
    private IYgSendBillListService ygSBDetailSer;
    @Resource
    private IYgSendBillListStateService ygSBStateSer;
    @Resource
    private IYgRecConfirmService ygRecSer;
    @Resource
    private IYgCompanyInfoService ygComSer;
    static Logger log = LoggerFactory.getLogger(ImportSendBill.class);
    /**
     * 获取配送单列表
     * @param yy154M
     * @return
     * @throws Exception
     */
    @RequestMapping("/getYGSendBillLst")
    @ResponseBody
    public AjaxResult<YY154_REP_XML> getYGSendBillLst(@RequestBody YY154_REQ_MAIN yy154M) throws Exception {
        AjaxResult<YY154_REP_XML> r = new AjaxResult<>();
        if(StringUtils.isNullOrEmpty(yy154M.getQsrq()) || StringUtils.isNullOrEmpty(yy154M.getJzrq())){
            throw new Exception("起始日期或截止日期不能为空");
        }
        YY154_REQ_XML param = new YY154_REQ_XML();
        param.setMain(yy154M);
        param.setReqHead(HostUtils.getReqHead(""));
        String xml = JAXBUtil.toXML(param, param.getClass());
        YY154_REP_XML res = ygUtils.sendRecv(YY154_REP_XML.class, SMPType.YY154, xml);

        //YY154_REP_XML res = ygUtils.praseXml(YY154_REP_XML.class ,xml);
        r.setData(res);
        return r;
    }
    /**
     * 获取配送单列表
     * @param yy154d
     * @return
     * @throws Exception
     */
    @RequestMapping("/getSendBillLst")
    @ResponseBody
    public AjaxResult getSendBillLstByPage(@RequestBody QueryInfo<YY154_REP_DETAIL> yy154d) throws Exception {
        AjaxResult r = new AjaxResult<>();
        QueryResult<YY154_REP_DETAIL> ret = ygSendBillSer.listByPage(yy154d);
        r.setData(ret);
        return r;
    }
    @RequestMapping(value = "getByBillId/{billId}")
    @ResponseBody
    public AjaxResult getByBillId(@PathVariable("billId") String billId)  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        YY154_REP_DETAIL ret = ygSendBillSer.get(billId);
        result.setData(ret);
        return result;
    }
    @RequestMapping(value = "getYgComLst/{qymc}")
    @ResponseBody
    public AjaxResult getYgComLst(@PathVariable("qymc") String qymc)  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        YgCompanyInfo query = new YgCompanyInfo();
        query.setQymc(qymc);
        List<YgCompanyInfo> ret = ygComSer.list(query);
        result.setData(ret);
        return result;
    }
    @RequestMapping("/getSendBillDetail")
    @ResponseBody
    public AjaxResult getSendBillDetailByPsdbh(@RequestBody YY155_REP_DETAIL yy155D) throws Exception {
        AjaxResult r = new AjaxResult<>();
        List<YY155_REP_DETAIL> ret = ygSBDetailSer.list(yy155D);
        r.setData(ret);
        return r;
    }
    @RequestMapping("/getSendBillLstState")
    @ResponseBody
    public AjaxResult getSendBillLstState(@RequestBody YY161_REP_DETAIL yy161D) throws Exception {
        AjaxResult r = new AjaxResult<>();
        List<YY161_REP_DETAIL> ret = ygSBStateSer.list(yy161D);
        r.setData(ret);
        return r;
    }

    /**
     * 导入查询条件中所有配送单
     * @param yy154M
     * @return
     * @throws Exception
     */
    @RequestMapping("/importSendBill")
    @ResponseBody
    public AjaxResult importSendBill(@RequestBody YY154_REQ_MAIN yy154M) throws Exception {
        AjaxResult r = new AjaxResult<>();
        //r.setData(importSBill.importInfo);
       boolean res = importSBill.start(yy154M);
       r.setData(res);
        return r;
    }
    /**
     * 查询导入情况
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/getImportState")
    @ResponseBody
    public AjaxResult<ImportSendBillInfo> getImportState() throws Exception {
        AjaxResult<ImportSendBillInfo> r = new AjaxResult<>();
        r.setData(importSBill.importInfo);
        return r;
    }
    /**
     * 根据配送单号获取配送明细
     * @param billId
     * @return
     * @throws Exception
     */
    @RequestMapping("/getSendBillByBillId/{billId}")
    @ResponseBody
    public AjaxResult<YY155_REP_XML> getYGSendBillByBillId(@PathVariable String billId) throws Exception {
        if(StringUtils.isNullOrEmpty(billId)){
            throw new Exception("配送单编号不能为空");
        }
        AjaxResult<YY155_REP_XML> r = new AjaxResult<>();
        YY155_REP_XML res = ygServices.getSendBillByBillId(billId);
        r.setData(res);
        return r;
    }

    /**
     * 获取配送单中配送明细状态
     * @param billId 配送单号
     * @return
     * @throws Exception
     */
    @RequestMapping("/getSendBillStateByBillId/{billId}")
    @ResponseBody
    public AjaxResult<YY161_REP_XML> getYGSendBillStateByBillId(@PathVariable String billId) throws Exception {
        if(StringUtils.isNullOrEmpty(billId)){
            throw new Exception("配送单编号不能为空");
        }
        AjaxResult<YY161_REP_XML> r = new AjaxResult<>();
        YY161_REP_XML res = ygServices.getSendBillStateByBillId(billId);

        r.setData(res);
        return r;
    }

    /**
     * YY131配送验收确认
     * @param lstYY131D
     * @return
     * @throws Exception
     */
    @RequestMapping("/recConfirm/{billId}")
    @ResponseBody
    public AjaxResult<Boolean> recConfirm(@RequestBody List<YY131_REQ_DETAIL> lstYY131D,@PathVariable String billId) throws Exception {
        AjaxResult<Boolean> r = new AjaxResult<>();
//        YY131_REQ_XML yy131 = new YY131_REQ_XML();
//        yy131.setReqHead(HostUtils.getReqHead(""));
//        yy131.setDetail(lstYY131D);
//        YY131_REQ_MAIN YY131M = new YY131_REQ_MAIN();
//        YY131M.setJls(lstYY131D.size());
//        YY131M.setPsyslx(Constance.DISTR_16.ACTUAL.toString());
//        yy131.setMain(YY131M);
//        String xmlData = JAXBUtil.toXML(yy131,YY131_REQ_XML.class);
//        YY131_REP_XML res = ygUtils.sendRecv(YY131_REP_XML.class, SMPType.YY131,xmlData);
//        ygSBStateSer.checkIn(billId);
        for (YY131_REQ_DETAIL yy131_req_detail : lstYY131D) {
            yy131_req_detail.setPsdbh(billId);
        }
        Boolean res = ygRecSer.insertByBatch(lstYY131D) >0;
        r.setData(res);
        return r;
    }
}
