package gyqx.ws.yg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.ws.yg.common.HostUtils;
import gyqx.ws.yg.common.JAXBUtil;
import gyqx.ws.yg.common.YgSendUtil;
import gyqx.ws.yg.common.YgServices;
import gyqx.ws.yg.service.IYgCompanyInfoService;
import gyqx.ws.yg.vo.SMPType;
import gyqx.ws.yg.vo.repVo.YY164_REP_DETAIL;
import gyqx.ws.yg.vo.repVo.YY164_REP_XML;
import gyqx.ws.yg.vo.reqVo.YY164_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY164_REQ_XML;
import gyqx.ws.yg.vo.ygVo.YgCompanyInfo;

/**
 * 企业信息 相关业务
 * 
 * @author suCity
 * @time 2018/3/15
 *
 */
@Controller
@RequestMapping("yg/companyInfo")
public class YgCompanyInfoController extends BaseController {

	@Resource
	private YgSendUtil ygUtils;
	@Resource
	private YgServices ysService;
	@Resource
	private IYgCompanyInfoService ygCmpInfoService;

	
	/**
	 * 向阳光平台查询所有的 企业信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getYgCmpInfosAndSave")
	@ResponseBody
	public AjaxResult getYgCmpInfosAndSave() throws Exception {
		AjaxResult result = new AjaxResult();
		this.getProvInfoAndSave(null, null);
		//YY164_REP_XML res = this.saveComInfoTest(YY164_REP_XML.class);
		//this.ygCmpInfoService.saveYgCompanyInfos(this.toYgCompanyInfo(res.getDetail()));
		return result;
	}
	@SuppressWarnings("unchecked")
	public <T> T saveComInfoTest(Class<T> clazz) throws Exception {
		String res = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><XMLDATA><HEAD><JSSJ>20180315/032940/</JSSJ><ZTCLJG>00000</ZTCLJG><CWXX></CWXX><BZXX></BZXX></HEAD><MAIN><SFWJ>0</SFWJ><DCZHQYBM>QY000000000000000035</DCZHQYBM><JLS>30</JLS></MAIN><DETAIL><STRUCT><QYBM>QY000000000000000006</QYBM><QYMC>邦美(上海)商贸有限公司</QYMC><LXR>刘侃</LXR><LXDH>13916087458</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000007</QYBM><QYMC>上海熠利贸易商行</QYMC><LXR>沙牡丹</LXR><LXDH>13918382522</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000008</QYBM><QYMC>苏州市康力骨科器械有限公司</QYMC></STRUCT><STRUCT><QYBM>QY000000000000000009</QYBM><QYMC>上海择乔贸易商行</QYMC></STRUCT><STRUCT><QYBM>QY000000000000000010</QYBM><QYMC>强生(上海)医疗器材有限公司</QYMC><QYDZ>上海市虹桥路355号城开大厦4楼</QYDZ><LXR>胡翠翠</LXR><LXDH>13818048543</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000011</QYBM><QYMC>常州市康辉医疗器械有限公司</QYMC><LXR>莫绯</LXR><LXDH>18621908352</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000012</QYBM><QYMC>施乐辉医用产品国际贸易(上海)有限公司</QYMC><QYDZ>上海市西藏中路168号都市总部大楼12楼</QYDZ><LXR>陆雅军</LXR><LXDH>18918036525</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000013</QYBM><QYMC>上海欣承医疗器械有限公司</QYMC><QYDZ>上海市金山区石化卫清西路1355号33幢502室</QYDZ><LXR>张宏渠</LXR><LXDH>18621579966</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000014</QYBM><QYMC>上海常灏贸易有限公司</QYMC></STRUCT><STRUCT><QYBM>QY000000000000000015</QYBM><QYMC>捷迈(上海)医疗国际贸易有限公司</QYMC><QYDZ>中国（上海）自由贸易试验区荷丹路190号2层A部位</QYDZ><LXR>刘侃</LXR><LXDH>13916087458</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000016</QYBM><QYMC>瑞毅医疗器械(上海)有限公司</QYMC><QYDZ>上海市静安区延安中路1440号阿波罗大厦2G03-04室</QYDZ><LXR>华文科</LXR><LXDH>15800547501</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000017</QYBM><QYMC>贝朗医疗(上海)国际贸易有限公司</QYMC><LXR>方花</LXR><LXDH>13601669230</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000018</QYBM><QYMC>微创心脉医疗科技(上海)有限公司</QYMC><QYDZ>上海市浦东新区康新公路3399弄1号楼</QYDZ><LXR>周望佳</LXR><LXDH>021-38954600</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000019</QYBM><QYMC>上海兆科医疗器械有限公司</QYMC><QYDZ>上海市徐汇区桂平路470号11号楼6楼</QYDZ><LXR>林家骅</LXR><LXDH>021-56081816</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000020</QYBM><QYMC>上海微创电生理医疗科技股份有限公司</QYMC><QYDZ>上海市浦东新区周浦镇天雄路588弄1-28号第28幢</QYDZ><LXR>刘茸</LXR><LXDH>021-38954600-3168</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000021</QYBM><QYMC>上海旭显生物科技有限公司</QYMC></STRUCT><STRUCT><QYBM>QY000000000000000022</QYBM><QYMC>上海晟谷医疗设备有限公司</QYMC><QYDZ></QYDZ></STRUCT><STRUCT><QYBM>QY000000000000000023</QYBM><QYMC>上海迈凯医疗器械有限公司</QYMC><QYDZ>上海市瑞金南路345弄裕兴大厦1号楼21A1</QYDZ><LXR>王艳涛</LXR><LXDH>15801205411</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000024</QYBM><QYMC>美敦力(上海)管理有限公司</QYMC><QYDZ>中国上海市浦东龙东大道3000号11号楼</QYDZ><LXR>阮金玲</LXR><LXDH>13774241386</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000025</QYBM><QYMC>上海微创医疗器械(集团)有限公司</QYMC><QYDZ>上海市浦东新区张江高科技园区张东路1601号</QYDZ><LXR>周望佳</LXR><LXDH>13524082468</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000026</QYBM><QYMC>上海微创骨科医疗科技有限公司</QYMC><QYDZ>浦东新区张东路1601号</QYDZ><LXR>刘娟</LXR><LXDH>15900974150</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000027</QYBM><QYMC>北京美中双和医疗器械股份有限公司</QYMC><QYDZ>上海市黄浦区永嘉路31号B401室</QYDZ><LXR>沈忠磬</LXR><LXDH>13761507068</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000028</QYBM><QYMC>国药控股医疗器械有限公司</QYMC><QYDZ>中山西路1001号国药大厦8楼</QYDZ><LXR>刘俊涛</LXR><LXDH>13661985554</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000029</QYBM><QYMC>碧迪医疗器械(上海)有限公司</QYMC><LXR>陈钢</LXR><LXDH>13962164408</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000030</QYBM><QYMC>上海承坤科贸有限公司</QYMC><QYDZ>上海市瑞金南路345弄裕兴大厦1号楼21A1</QYDZ><LXR>张倪</LXR><LXDH>13482679830</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000031</QYBM><QYMC>上海泓慈贸易有限公司</QYMC><QYDZ>上海市瑞金南路345弄裕兴大厦1号楼21A1</QYDZ><LXR>何鄂伟</LXR><LXDH>18621656402</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000032</QYBM><QYMC>上海蓝山医疗器械有限公司</QYMC></STRUCT><STRUCT><QYBM>QY000000000000000033</QYBM><QYMC>圣犹达医疗用品(上海)有限公司</QYMC><QYDZ>上海市静安区南极西路688号15楼02-07室</QYDZ><LXR>钱丹</LXR><LXDH>13636552823</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000034</QYBM><QYMC>微创神通医疗科技(上海)有限公司</QYMC><QYDZ>上海市浦东新区广丹路222弄16幢</QYDZ><LXR>王静娴</LXR><LXDH>13641848376</LXDH></STRUCT><STRUCT><QYBM>QY000000000000000035</QYBM><QYMC>上海普映医疗器材有限公司</QYMC><QYDZ>青浦区白鹤镇白石路3338号202室</QYDZ><LXR>吴晓凤</LXR><LXDH>13524292976</LXDH></STRUCT></DETAIL></XMLDATA>";
 		T repXml = (T) JAXBUtil.formXML(clazz, res);
        
        return repXml;
    }
	/**
	 * 根据条件查询信息
	 * 
	 * @param queryVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getYgCmpInfo")
	@ResponseBody
	public AjaxResult<YgCompanyInfo> getYgCmpInfo(@RequestBody YgCompanyInfo queryVo) throws Exception {
		AjaxResult<YgCompanyInfo> result = new AjaxResult<YgCompanyInfo>();
		YgCompanyInfo cmp = null;
		YY164_REP_XML obj = this.ysService.getProvInfo(queryVo.getQybm(), queryVo.getQymc());
		if (obj != null) {
			List<YY164_REP_DETAIL> list = obj.getDetail();
			if (list != null && list.size() > 0) {
				YY164_REP_DETAIL item = list.get(0);
				YgCompanyInfo info = new YgCompanyInfo();
				info.setQybm(item.getQybm());
				info.setLxdh(item.getLxdh());
				info.setLxr(item.getLxr());
				info.setQydz(item.getQydz());
				info.setQymc(item.getQymc());
				info.setVersion(0);
				info.setLastUpdateDatetime(new java.util.Date());
				cmp = info;
				result.setData(cmp);
			}
		}
		return result;
	}
	/**
	 * 保存企业信息
	 * @param queryVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveYgCmpInfo")
	@ResponseBody
	public AjaxResult saveYgCmpInfo(@RequestBody YgCompanyInfo ygCmpInfo) throws Exception {
		AjaxResult result = new AjaxResult();
		YgCompanyInfo info = this.ygCmpInfoService.getYgCompanyInfo(ygCmpInfo.getQybm());
		if(info == null){
			List<YgCompanyInfo> cmps = new ArrayList<YgCompanyInfo>();
			cmps.add(ygCmpInfo);
			this.ygCmpInfoService.saveYgCompanyInfos(cmps);
		}else{
			this.ygCmpInfoService.updateYgCompanyInfo(ygCmpInfo);
		}
		return result;
	}
	// 获取企业信息YY164
	private YY164_REP_XML getProvInfoAndSave(String qybm, String qymc) throws Exception {
		YY164_REQ_MAIN main = new YY164_REQ_MAIN();
		main.setQybm(qybm);
		main.setQymc(qymc);
		YY164_REQ_XML param = new YY164_REQ_XML();
		param.setMain(main);
		param.setReqHead(HostUtils.getReqHead(""));
		String xml = JAXBUtil.toXML(param, param.getClass());
		YY164_REP_XML res = ygUtils.sendRecv(YY164_REP_XML.class, SMPType.YY164, xml);
		ygCmpInfoService.saveYgCompanyInfos(this.toYgCompanyInfo(res.getDetail()));
		while (res.getMain().getSfwj().equals("0")) {
			param.getMain().setQybmcxtj(res.getMain().getDczhqybm());
			String subXml = JAXBUtil.toXML(param, param.getClass());
			YY164_REP_XML tmp = ygUtils.sendRecv(YY164_REP_XML.class, SMPType.YY164, subXml);
			res.setMain(tmp.getMain());
			res.getDetail().addAll(tmp.getDetail());
			ygCmpInfoService.saveYgCompanyInfos(this.toYgCompanyInfo(tmp.getDetail()));
		}
		return res;
	}

	private List<YgCompanyInfo> toYgCompanyInfo(List<YY164_REP_DETAIL> list) {
		List<YgCompanyInfo> ygCmpInfoList = new ArrayList<YgCompanyInfo>();
		if (list != null && list.size() > 0) {
			list.forEach((item) -> {
				YgCompanyInfo info = new YgCompanyInfo();
				info.setQybm(item.getQybm());
				info.setLxdh(item.getLxdh());
				info.setLxr(item.getLxr());
				info.setQydz(item.getQydz());
				info.setQymc(item.getQymc());
				info.setVersion(0);
				info.setLastUpdateDatetime(new java.util.Date());
				ygCmpInfoList.add(info);
			});
		}
		return ygCmpInfoList;
	}
}
