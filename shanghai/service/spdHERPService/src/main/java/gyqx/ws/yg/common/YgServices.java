package gyqx.ws.yg.common;

import com.mysql.jdbc.StringUtils;
import gyqx.ws.yg.common.JAXBUtil;
import gyqx.ws.yg.common.YgSendUtil;
import gyqx.ws.yg.vo.SMPType;
import gyqx.ws.yg.vo.repVo.*;
import gyqx.ws.yg.vo.reqVo.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by moonb on 2017/12/6.
 */
@Component
public class YgServices {
	@Resource
	private YgSendUtil ygUtils;
	@Resource
	private YsxtUtil ysxtUtil;
	// 获取采购单YY159
	public  YY159_REP_XML getPurStatusByBillNo(String BillNo) throws Exception {
		YY159_REQ_MAIN main = new YY159_REQ_MAIN();
		main.setDdbh(BillNo);
		YY159_REQ_XML param = new YY159_REQ_XML();
		param.setMain(main);
		param.setReqHead(HostUtils.getReqHead(""));
		String xml = JAXBUtil.toXML(param, param.getClass());
		YY159_REP_XML res = ygUtils.sendRecv(YY159_REP_XML.class, SMPType.YY159, xml);
		while (res.getMain().getSfwj().equals("0")) {
			param.getMain().setDdmxbhcxtj(res.getMain().getDczhddmxbh());
			String subXml = JAXBUtil.toXML(param, param.getClass());
			YY159_REP_XML tmp = ygUtils.sendRecv(YY159_REP_XML.class, SMPType.YY159, subXml);
			res.setMain(tmp.getMain());
			res.getDetail().addAll(tmp.getDetail());
		}
		return res;
	}

	// 获取企业信息YY164
	public YY164_REP_XML getProvInfo(String qybm, String qymc) throws Exception {
		YY164_REQ_MAIN main = new YY164_REQ_MAIN();
		main.setQybm(qybm);
		main.setQymc(qymc);
		YY164_REQ_XML param = new YY164_REQ_XML();
		param.setMain(main);
		param.setReqHead(HostUtils.getReqHead(""));
		String xml = JAXBUtil.toXML(param, param.getClass());
		YY164_REP_XML res = ygUtils.sendRecv(YY164_REP_XML.class, SMPType.YY164, xml);
		while (res.getMain().getSfwj().equals("0")) {
			param.getMain().setQybmcxtj(res.getMain().getDczhqybm());
			String subXml = JAXBUtil.toXML(param, param.getClass());
			YY164_REP_XML tmp = ygUtils.sendRecv(YY164_REP_XML.class, SMPType.YY164, subXml);
			res.setMain(tmp.getMain());
			res.getDetail().addAll(tmp.getDetail());
		}
		return res;
	}
	/**
	 * 获取配送单列表
	 * @param yy154M
	 * @return
	 * @throws Exception
	 */
	public YY154_REP_XML getSendBillList(YY154_REQ_MAIN yy154M) throws Exception {
		if(StringUtils.isNullOrEmpty(yy154M.getQsrq()) || StringUtils.isNullOrEmpty(yy154M.getJzrq())){
			throw new Exception("起始日期或截止日期不能为空");
		}
		YY154_REQ_XML param = new YY154_REQ_XML();
		param.setMain(yy154M);
		param.setReqHead(HostUtils.getReqHead(""));
		String xml = JAXBUtil.toXML(param, param.getClass());
		YY154_REP_XML res = ygUtils.sendRecv(YY154_REP_XML.class, SMPType.YY154, xml);
		if(!res.getRepHead().getRes().equals("00000")){
			throw new Exception(res.getRepHead().getErrMsg());
		}
		while (res.getMain().getSfwj().equals("0")) {
			param.getMain().setPsdbhcxtj(res.getMain().getDczhpsdbh());
			String subXml = JAXBUtil.toXML(param, param.getClass());
			YY154_REP_XML tmp = ygUtils.sendRecv(YY154_REP_XML.class, SMPType.YY154, subXml);
			if(!tmp.getRepHead().getRes().equals("00000")){
				throw new Exception(tmp.getRepHead().getErrMsg());
			}
			res.setMain(tmp.getMain());
			res.getDetail().addAll(tmp.getDetail());
		}
		return res;
		//return ysxtUtil.getSendBill();

	}
	/**
	 * 根据配送单号查询所有配送明细
	 * @param billId 企业内部配送单号
	 * @return
	 * @throws Exception
	 */
	public YY155_REP_XML getSendBillByBillId(String billId) throws Exception {
		YY155_REQ_MAIN main = new YY155_REQ_MAIN();
		main.setPsdbh(billId);
		YY155_REQ_XML param = new YY155_REQ_XML();
		param.setMain(main);
		param.setReqHead(HostUtils.getReqHead(""));
		String xml = JAXBUtil.toXML(param, param.getClass());
		YY155_REP_XML res = ygUtils.sendRecv(YY155_REP_XML.class, SMPType.YY155, xml);
		if(!res.getRepHead().getRes().equals("00000")){
			throw new Exception(res.getRepHead().getErrMsg());
		}
		while (res.getMain().getSfwj().equals("0")) {
			//param.getMain().setQybmcxtj(res.getMain().getDczhqybm());
			param.getMain().setPsmxbhcxtj(res.getMain().getDczhpsmxbh());
			String subXml = JAXBUtil.toXML(param, param.getClass());
			YY155_REP_XML tmp = ygUtils.sendRecv(YY155_REP_XML.class, SMPType.YY155, subXml);
			if(!tmp.getRepHead().getRes().equals("00000")){
				throw new Exception(tmp.getRepHead().getErrMsg());
			}
			res.setMain(tmp.getMain());
			res.getDetail().addAll(tmp.getDetail());
		}
		return res;
//		return ysxtUtil.getSendBillLst(billId);
	}

	/**
	 * 根据配送单号查询所有配送明细状态
	 * @param billId
	 * @return
	 * @throws Exception
	 */
	public YY161_REP_XML getSendBillStateByBillId(String billId) throws Exception{
		YY161_REQ_MAIN main = new YY161_REQ_MAIN();
		main.setPsdbh(billId);
		YY161_REQ_XML param = new YY161_REQ_XML();
		param.setMain(main);
		param.setReqHead(HostUtils.getReqHead(""));
		String xml = JAXBUtil.toXML(param, param.getClass());
		YY161_REP_XML res = ygUtils.sendRecv(YY161_REP_XML.class, SMPType.YY161, xml);
		if(!res.getRepHead().getRes().equals("00000")){
			throw new Exception(res.getRepHead().getErrMsg());
		}
		while (res.getMain().getSfwj().equals("0")) {
			param.getMain().setPsmxbhcxtj(res.getMain().getDczhpsmxbh());
			String subXml = JAXBUtil.toXML(param, param.getClass());
			YY161_REP_XML tmp = ygUtils.sendRecv(YY161_REP_XML.class, SMPType.YY161, subXml);
			if(!tmp.getRepHead().getRes().equals("00000")){
				throw new Exception(tmp.getRepHead().getErrMsg());
			}
			res.setMain(tmp.getMain());
			res.getDetail().addAll(tmp.getDetail());
		}
		return res;
		//return ysxtUtil.getSendBillState(billId);
	}
	/**
	 * 获取发票信息列表
	 * @param yy157M
	 * @return
	 * @throws Exception
	 */
	public YY157_REP_XML getInvoiceList(YY157_REQ_MAIN yy157M) throws Exception {
		if(StringUtils.isNullOrEmpty(yy157M.getQsrq()) || StringUtils.isNullOrEmpty(yy157M.getJzrq())){
			throw new Exception("起始日期或截止日期不能为空");
		}
		YY157_REQ_XML param = new YY157_REQ_XML();
		param.setMain(yy157M);
		param.setReqHead(HostUtils.getReqHead(""));
		String xml = JAXBUtil.toXML(param, param.getClass());
		YY157_REP_XML res = ygUtils.sendRecv(YY157_REP_XML.class, SMPType.YY157, xml);
		while (res.getMain().getSfwj().equals("0")) {
			param.getMain().setFpbhcxtj(res.getMain().getDczhfpbh());
			String subXml = JAXBUtil.toXML(param, param.getClass());
			YY157_REP_XML tmp = ygUtils.sendRecv(YY157_REP_XML.class, SMPType.YY157, subXml);
			res.setMain(tmp.getMain());
			res.getDetail().addAll(tmp.getDetail());
		}
		return res;
	}
	/**
	 * 根据发票编号查询所有发票明细
	 * @param billId 发票编号
	 * @return
	 * @throws Exception
	 */
	public YY158_REP_XML getInvoiceDetailByBillId(String billId) throws Exception {
		YY158_REQ_MAIN main = new YY158_REQ_MAIN();
		main.setFpbh(billId);
		YY158_REQ_XML param = new YY158_REQ_XML();
		param.setMain(main);
		param.setReqHead(HostUtils.getReqHead(""));
		String xml = JAXBUtil.toXML(param, param.getClass());
		YY158_REP_XML res = ygUtils.sendRecv(YY158_REP_XML.class, SMPType.YY158, xml);
		while (res.getMain().getSfwj().equals("0")) {
			param.getMain().setFpmxbhcxtj(res.getMain().getDczhfpmxbh());
			String subXml = JAXBUtil.toXML(param, param.getClass());
			YY158_REP_XML tmp = ygUtils.sendRecv(YY158_REP_XML.class, SMPType.YY158, subXml);
			res.setMain(tmp.getMain());
			res.getDetail().addAll(tmp.getDetail());
		}
		return res;
	}
	/**
	 * 根据发票号查询所有发票明细状态
	 * @param yy160m
	 * @return
	 * @throws Exception
	 */
	public YY160_REP_XML getInvoiceState(YY160_REQ_MAIN yy160m) throws Exception{
		if(StringUtils.isNullOrEmpty(yy160m.getQsrq()) || StringUtils.isNullOrEmpty(yy160m.getJzrq())){
			throw new Exception("起始日期或截止日期为空");
		}
		YY160_REQ_XML param = new YY160_REQ_XML();
		param.setMain(yy160m);
		param.setReqHead(HostUtils.getReqHead(""));
		String xml = JAXBUtil.toXML(param, param.getClass());
		YY160_REP_XML res = ygUtils.sendRecv(YY160_REP_XML.class, SMPType.YY160, xml);
		while (res.getMain().getSfwj().equals("0")) {
			param.getMain().setFpbhcxtj(res.getMain().getDczhfpbh());
			String subXml = JAXBUtil.toXML(param, param.getClass());
			YY160_REP_XML tmp = ygUtils.sendRecv(YY160_REP_XML.class, SMPType.YY160, subXml);
			res.setMain(tmp.getMain());
			res.getDetail().addAll(tmp.getDetail());
		}
		return res;
	}
}
