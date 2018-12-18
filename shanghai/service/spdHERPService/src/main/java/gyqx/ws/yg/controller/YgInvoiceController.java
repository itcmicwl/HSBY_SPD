package gyqx.ws.yg.controller;

import com.mysql.jdbc.StringUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.ws.yg.common.*;
import gyqx.ws.yg.vo.SMPType;
import gyqx.ws.yg.vo.repVo.*;
import gyqx.ws.yg.vo.reqVo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


/**
 * 阳光平台发票相关业务
 * Created by moonb on 2018/2/11.
 */
@Controller
@RequestMapping("yg/invoice")
public class YgInvoiceController extends BaseController {
    @Resource
    private YgSendUtil ygUtils;
    @Resource
    private YgServices ygServices;

    /**
     * 获取发票信息列表
     * @param yy157M
     * @return
     * @throws Exception
     */
    @RequestMapping("/getInvoiceBillLst")
    @ResponseBody
    public AjaxResult<YY157_REP_XML> getInvoiceBillLst(@RequestBody YY157_REQ_MAIN yy157M) throws Exception {
        AjaxResult<YY157_REP_XML> r = new AjaxResult<>();
        if(StringUtils.isNullOrEmpty(yy157M.getQsrq()) || StringUtils.isNullOrEmpty(yy157M.getJzrq())){
            throw new Exception("起始日期或截止日期不能为空");
        }
        YY157_REQ_XML param = new YY157_REQ_XML();
        param.setMain(yy157M);
        param.setReqHead(HostUtils.getReqHead(""));
        String xml = JAXBUtil.toXML(param, param.getClass());
        YY157_REP_XML res = ygUtils.sendRecv(YY157_REP_XML.class, SMPType.YY157, xml);

        //YY157_REP_XML res = ygUtils.praseXml(YY157_REP_XML.class, xml);
        r.setData(res);
        return r;
    }

    /**
     * 获取发票明细
     * @param billId
     * @return
     * @throws Exception
     */
    @RequestMapping("/getInvoiceDetailByBillId/{billId}")
    @ResponseBody
    public AjaxResult<YY158_REP_XML> getInvoiceDetailByBillId(@PathVariable String billId) throws Exception {
        if(StringUtils.isNullOrEmpty(billId)){
            throw new Exception("配送单编号不能为空");
        }
        AjaxResult<YY158_REP_XML> r = new AjaxResult<>();
        YY158_REP_XML res = ygServices.getInvoiceDetailByBillId(billId);
        r.setData(res);
        return r;
    }

    /**
     * 获取发票状态
     * @param yy160m
     * @return
     * @throws Exception
     */
    @RequestMapping("/getInvoiceState")
    @ResponseBody
    public AjaxResult<YY160_REP_XML> getInvoiceStateByBillId(@PathVariable YY160_REQ_MAIN yy160m) throws Exception {
        AjaxResult<YY160_REP_XML> r = new AjaxResult<>();
        YY160_REP_XML res = ygServices.getInvoiceState(yy160m);
        r.setData(res);
        return r;
    }

    /**
     * YY132发票验收确认
     * 发票验收时是按整张发票进行验收而不是按发票明细进行验收
     * 发票验收时需填写发票上的发票含税金额 （非明细的汇总金额）
     * 若发票验收不通过，企业可以重新开具相同发票代码+发票号的发票
     * @param yy132m
     * @return
     * @throws Exception
     */
    @RequestMapping("/invoiceConfirm")
    @ResponseBody
    public AjaxResult<YY132_REP_XML> invoiceConfirm(@RequestBody YY132_REQ_MAIN yy132m) throws Exception {
        if(StringUtils.isNullOrEmpty(yy132m.getFpid())){
            throw new Exception("发票ID不能为空");
        }
        if(StringUtils.isNullOrEmpty(yy132m.getFpdm())){
            throw new Exception("发票代码不能为空");
        }
        if(StringUtils.isNullOrEmpty(yy132m.getFph())){
            throw new Exception("发票号不能为空");
        }
        if(StringUtils.isNullOrEmpty(yy132m.getQybm())){
            throw new Exception("企业编码不能为空");
        }
        if(StringUtils.isNullOrEmpty(yy132m.getFpysjg())){
            throw new Exception("发票验收结果不能为空");
        }
        if(yy132m.getFphszje().compareTo(new BigDecimal(0))<=0){
            throw new Exception("发票含税金额必须大于零");
        }
        AjaxResult<YY132_REP_XML> r = new AjaxResult<>();
        YY132_REQ_XML yy132 = new YY132_REQ_XML();
        yy132.setReqHead(HostUtils.getReqHead(""));
        yy132.setMain(yy132m);
        String xmlData = JAXBUtil.toXML(yy132,YY132_REQ_XML.class);
        YY132_REP_XML res = ygUtils.sendRecv(YY132_REP_XML.class, SMPType.YY132,xmlData);
        r.setData(res);
        return r;
    }
}
