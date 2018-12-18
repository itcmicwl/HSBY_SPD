package gyqx.ws.yg.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.ws.yg.common.HostUtils;
import gyqx.ws.yg.common.JAXBUtil;
import gyqx.ws.yg.common.YgSendUtil;
import gyqx.ws.yg.vo.SMPType;
import gyqx.ws.yg.vo.reqVo.YY101_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY101_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY111_REQ_DETAIL;
import gyqx.ws.yg.vo.reqVo.YY111_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY111_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY112_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY112_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY113_REQ_DETAIL;
import gyqx.ws.yg.vo.reqVo.YY113_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY113_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY114_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY114_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY131_REQ_DETAIL;
import gyqx.ws.yg.vo.reqVo.YY131_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY131_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY132_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY132_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY133_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY133_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY151_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY151_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY152_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY152_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY153_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY153_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY154_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY154_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY155_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY155_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY156_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY156_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY157_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY157_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY158_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY158_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY159_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY159_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY160_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY160_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY161_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY161_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY162_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY162_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY163_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY163_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY164_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY164_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY165_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY165_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY166_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY166_REQ_XML;

import javax.annotation.Resource;

@Controller
@RequestMapping("/yg/hosSend")
public class YgSendController extends BaseController {
	@Resource
	private YgSendUtil ygUtils;

	/**
	 * 获取xml格式的数据，展示在界面上，以便于修改
	 * 
	 * @param ygSendVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getReq")
	@ResponseBody
	public AjaxResult<YgSendVo> getReq(@RequestBody YgSendVo ygSendVo) throws Exception {
		AjaxResult<YgSendVo> res = new AjaxResult<>();
		String code = ygSendVo.getCode();
		String contentReq = ygSendVo.getContentReq();
		switch (code) {
		case "YY101":
			YY101_REQ_XML yy101 = new YY101_REQ_XML();
			YY101_REQ_MAIN main101 = new YY101_REQ_MAIN();
			yy101.setReqHead(HostUtils.getReqHead("耗材配送点传报测试"));
			yy101.setMain(main101);
			contentReq = JAXBUtil.toXML(yy101);
			break;
		case "YY111":
			YY111_REQ_XML yy111 = new YY111_REQ_XML();
			YY111_REQ_MAIN main111 = new YY111_REQ_MAIN();
			YY111_REQ_DETAIL detail111 = new YY111_REQ_DETAIL();
			List<YY111_REQ_DETAIL> details111 = new ArrayList<>();
			details111.add(detail111);
			yy111.setReqHead(HostUtils.getReqHead("耗材采购单填报测试"));
			yy111.setMain(main111);
			yy111.setDetail(details111);
			contentReq = JAXBUtil.toXML(yy111);
			break;
		case "YY112":
			YY112_REQ_XML yy112 = new YY112_REQ_XML();
			YY112_REQ_MAIN main112 = new YY112_REQ_MAIN();
			yy112.setReqHead(HostUtils.getReqHead("耗材采购单确认测试"));
			yy112.setMain(main112);
			contentReq = JAXBUtil.toXML(yy112);
			break;
		case "YY113":
			YY113_REQ_XML yy113 = new YY113_REQ_XML();
			YY113_REQ_MAIN main113 = new YY113_REQ_MAIN();
			YY113_REQ_DETAIL detail113 = new YY113_REQ_DETAIL();
			List<YY113_REQ_DETAIL> details113 = new ArrayList<>();
			details113.add(detail113);
			yy113.setReqHead(HostUtils.getReqHead("耗材退货单填报测试"));
			yy113.setMain(main113);
			yy113.setDetail(details113);
			contentReq = JAXBUtil.toXML(yy113);
			break;
		case "YY114":
			YY114_REQ_XML yy114 = new YY114_REQ_XML();
			YY114_REQ_MAIN main114 = new YY114_REQ_MAIN();
			yy114.setReqHead(HostUtils.getReqHead("耗材退货单确认测试"));
			yy114.setMain(main114);
			contentReq = JAXBUtil.toXML(yy114);
			break;
		case "YY131":
			YY131_REQ_XML yy131 = new YY131_REQ_XML();
			YY131_REQ_MAIN main131 = new YY131_REQ_MAIN();
			YY131_REQ_DETAIL detail131 = new YY131_REQ_DETAIL();
			List<YY131_REQ_DETAIL> details131 = new ArrayList<>();
			details131.add(detail131);
			yy131.setReqHead(HostUtils.getReqHead("耗材配送验收确认测试"));
			yy131.setMain(main131);
			yy131.setDetail(details131);
			contentReq = JAXBUtil.toXML(yy131);
			break;
		case "YY132":
			YY132_REQ_XML yy132 = new YY132_REQ_XML();
			YY132_REQ_MAIN main132 = new YY132_REQ_MAIN();
			yy132.setReqHead(HostUtils.getReqHead("耗材发票验收确认测试"));
			yy132.setMain(main132);
			contentReq = JAXBUtil.toXML(yy132);
			break;
		case "YY133":
			YY133_REQ_XML yy133 = new YY133_REQ_XML();
			YY133_REQ_MAIN main133 = new YY133_REQ_MAIN();
			yy133.setReqHead(HostUtils.getReqHead("耗材发票支付确认测试"));
			yy133.setMain(main133);
			contentReq = JAXBUtil.toXML(yy133);
			break;
		case "YY151":
			YY151_REQ_XML yy151 = new YY151_REQ_XML();
			YY151_REQ_MAIN main151 = new YY151_REQ_MAIN();
			yy151.setReqHead(HostUtils.getReqHead("耗材采购明细信息获取测试"));
			yy151.setMain(main151);
			contentReq = JAXBUtil.toXML(yy151);
			break;
		case "YY152":
			YY152_REQ_XML yy152 = new YY152_REQ_XML();
			YY152_REQ_MAIN main152 = new YY152_REQ_MAIN();
			yy152.setReqHead(HostUtils.getReqHead("耗材退货明细获取测试"));
			yy152.setMain(main152);
			contentReq = JAXBUtil.toXML(yy152);
			break;
		case "YY153":
			YY153_REQ_XML yy153 = new YY153_REQ_XML();
			YY153_REQ_MAIN main153 = new YY153_REQ_MAIN();
			yy153.setReqHead(HostUtils.getReqHead("耗材按配送明细获取测试"));
			yy153.setMain(main153);
			contentReq = JAXBUtil.toXML(yy153);
			break;
		case "YY154":
			YY154_REQ_XML yy154 = new YY154_REQ_XML();
			YY154_REQ_MAIN main154 = new YY154_REQ_MAIN();
			yy154.setReqHead(HostUtils.getReqHead("耗材配送单获取测试"));
			yy154.setMain(main154);
			contentReq = JAXBUtil.toXML(yy154);
			break;
		case "YY155":
			YY155_REQ_XML yy155 = new YY155_REQ_XML();
			YY155_REQ_MAIN main155 = new YY155_REQ_MAIN();
			yy155.setReqHead(HostUtils.getReqHead("耗材配送明细获取测试"));
			yy155.setMain(main155);
			contentReq = JAXBUtil.toXML(yy155);
			break;
		case "YY156":
			YY156_REQ_XML yy156 = new YY156_REQ_XML();
			YY156_REQ_MAIN main156 = new YY156_REQ_MAIN();
			yy156.setReqHead(HostUtils.getReqHead("耗材按发票明细获取测试"));
			yy156.setMain(main156);
			contentReq = JAXBUtil.toXML(yy156);
			break;
		case "YY157":
			YY157_REQ_XML yy157 = new YY157_REQ_XML();
			YY157_REQ_MAIN main157 = new YY157_REQ_MAIN();
			yy157.setReqHead(HostUtils.getReqHead("耗材发票信息获取测试"));
			yy157.setMain(main157);
			contentReq = JAXBUtil.toXML(yy157);
			break;
		case "YY158":
			YY158_REQ_XML yy158 = new YY158_REQ_XML();
			YY158_REQ_MAIN main158 = new YY158_REQ_MAIN();
			yy158.setReqHead(HostUtils.getReqHead("耗材发票明细获取测试"));
			yy158.setMain(main158);
			contentReq = JAXBUtil.toXML(yy158);
			break;
		case "YY159":
			YY159_REQ_XML yy159 = new YY159_REQ_XML();
			YY159_REQ_MAIN main159 = new YY159_REQ_MAIN();
			yy159.setReqHead(HostUtils.getReqHead("耗材按采购单获取采购明细状态测试"));
			yy159.setMain(main159);
			contentReq = JAXBUtil.toXML(yy159);
			break;
		case "YY160":
			YY160_REQ_XML yy160 = new YY160_REQ_XML();
			YY160_REQ_MAIN main160 = new YY160_REQ_MAIN();
			yy160.setReqHead(HostUtils.getReqHead("耗材发票状态获取测试"));
			yy160.setMain(main160);
			contentReq = JAXBUtil.toXML(yy160);
			break;
		case "YY161":
			YY161_REQ_XML yy161 = new YY161_REQ_XML();
			YY161_REQ_MAIN main161 = new YY161_REQ_MAIN();
			yy161.setReqHead(HostUtils.getReqHead("耗材配送单状态获取测试"));
			yy161.setMain(main161);
			contentReq = JAXBUtil.toXML(yy161);
			break;
		case "YY162":
			YY162_REQ_XML yy162 = new YY162_REQ_XML();
			YY162_REQ_MAIN main162 = new YY162_REQ_MAIN();
			yy162.setReqHead(HostUtils.getReqHead("耗材按退货单获取退货明细状态测试"));
			yy162.setMain(main162);
			contentReq = JAXBUtil.toXML(yy162);
			break;
		case "YY163":
			YY163_REQ_XML yy163 = new YY163_REQ_XML();
			YY163_REQ_MAIN main163 = new YY163_REQ_MAIN();
			yy163.setReqHead(HostUtils.getReqHead("耗材单品UDI信息获取测试"));
			yy163.setMain(main163);
			contentReq = JAXBUtil.toXML(yy163);
			break;
		case "YY164":
			YY164_REQ_XML yy164 = new YY164_REQ_XML();
			YY164_REQ_MAIN main164 = new YY164_REQ_MAIN();
			yy164.setReqHead(HostUtils.getReqHead("企业信息获取测试"));
			yy164.setMain(main164);
			contentReq = JAXBUtil.toXML(yy164);
			break;
		case "YY165":
			YY165_REQ_XML yy165 = new YY165_REQ_XML();
			YY165_REQ_MAIN main165 = new YY165_REQ_MAIN();
			yy165.setReqHead(HostUtils.getReqHead("企业资证信息获取测试"));
			yy165.setMain(main165);
			contentReq = JAXBUtil.toXML(yy165);
			break;
		case "YY166":
			YY166_REQ_XML yy166 = new YY166_REQ_XML();
			YY166_REQ_MAIN main166 = new YY166_REQ_MAIN();
			yy166.setReqHead(HostUtils.getReqHead("企业授权信息获取测试"));
			yy166.setMain(main166);
			contentReq = JAXBUtil.toXML(yy166);
			break;
		default:
			contentReq = "没有发现该接口，请联系管理员";
			break;
		}
		ygSendVo.setContentReq(contentReq);
		res.setData(ygSendVo);
		return res;
	}

	/**
	 * 点击发送，发送数据到阳光采购平台
	 * 
	 * @param ygSendVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/send")
	@ResponseBody
	public AjaxResult<YgSendVo> send(@RequestBody YgSendVo ygSendVo) throws Exception {
		AjaxResult<YgSendVo> res = new AjaxResult<>();
		String code = ygSendVo.getCode();
		String contentReq = ygSendVo.getContentReq();
		saveTxt("%s_req.txt", code, contentReq);
		String contentRep = ygSendVo.getContentRep();
		switch (code) {
		case "YY101":
			contentRep = ygUtils.sendRecv(SMPType.YY101, contentReq);
			break;
		case "YY111":
			contentRep = ygUtils.sendRecv(SMPType.YY111, contentReq);
			break;
		case "YY112":
			contentRep = ygUtils.sendRecv(SMPType.YY112, contentReq);
			break;
		case "YY113":
			contentRep = ygUtils.sendRecv(SMPType.YY113, contentReq);
			break;
		case "YY114":
			contentRep = ygUtils.sendRecv(SMPType.YY114, contentReq);
			break;
		case "YY131":
			contentRep = ygUtils.sendRecv(SMPType.YY131, contentReq);
			break;
		case "YY132":
			contentRep = ygUtils.sendRecv(SMPType.YY132, contentReq);
			break;
		case "YY133":
			contentRep = ygUtils.sendRecv(SMPType.YY133, contentReq);
			break;
		case "YY151":
			contentRep = ygUtils.sendRecv(SMPType.YY151, contentReq);
			break;
		case "YY152":
			contentRep = ygUtils.sendRecv(SMPType.YY152, contentReq);
			break;
		case "YY153":
			contentRep = ygUtils.sendRecv(SMPType.YY153, contentReq);
			break;
		case "YY154":
			contentRep = ygUtils.sendRecv(SMPType.YY154, contentReq);
			break;
		case "YY155":
			contentRep = ygUtils.sendRecv(SMPType.YY155, contentReq);
			break;
		case "YY156":
			contentRep = ygUtils.sendRecv(SMPType.YY156, contentReq);
			break;
		case "YY157":
			contentRep = ygUtils.sendRecv(SMPType.YY157, contentReq);
			break;
		case "YY158":
			contentRep = ygUtils.sendRecv(SMPType.YY158, contentReq);
			break;
		case "YY159":
			contentRep = ygUtils.sendRecv(SMPType.YY159, contentReq);
			break;
		case "YY160":
			contentRep = ygUtils.sendRecv(SMPType.YY160, contentReq);
			break;
		case "YY161":
			contentRep = ygUtils.sendRecv(SMPType.YY161, contentReq);
			break;
		case "YY162":
			contentRep = ygUtils.sendRecv(SMPType.YY162, contentReq);
			break;
		case "YY163":
			contentRep = ygUtils.sendRecv(SMPType.YY163, contentReq);
			break;
		case "YY164":
			contentRep = ygUtils.sendRecv(SMPType.YY164, contentReq);
			break;
		case "YY165":
			contentRep = ygUtils.sendRecv(SMPType.YY165, contentReq);
			break;
		case "YY166":
			contentRep = ygUtils.sendRecv(SMPType.YY166, contentReq);
			break;
		default:
			contentRep = "没有返回信息，请联系管理员";
			break;
		}
		saveTxt("%s_rep.txt", code, contentRep);
		ygSendVo.setContentRep(contentRep);
		res.setData(ygSendVo);
		return res;
	}

	private void saveTxt(String pattern, String code, String content) throws Exception {
		String path = "D:/yg_temp/";
		String filename = String.format(pattern, code);
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(path + filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(content.getBytes("utf-8"));
		fos.flush();
		fos.close();
	}

}
