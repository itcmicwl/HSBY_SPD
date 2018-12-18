package gyqx.spdhdi.myInfos.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import common.base.PinYin;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdhdi.myInfos.dao.CompanyInfoVoDao;
import gyqx.spdhdi.myInfos.dao.ProvFrwtsVoRDao;
import gyqx.spdhdi.myInfos.dao.ProvRegistGoodsPushDao;
import gyqx.spdhdi.myInfos.dao.ProvRegistInfoPushDao;
import gyqx.spdhdi.myInfos.service.IProvRegistInfoPushService;
import gyqx.spdhdi.myInfos.vo.BasMfrsInfoVo;
import gyqx.spdhdi.myInfos.vo.CompanyCertificateVo;
import gyqx.spdhdi.myInfos.vo.CompanyInfoVo;
import gyqx.spdhdi.myInfos.vo.OutdateCertsImgVo;
import gyqx.spdhdi.myInfos.vo.OutdateCertsVo;
import gyqx.spdhdi.myInfos.vo.ProvFrwtsVoR;
import gyqx.spdhdi.myInfos.vo.ProvJxsqsVo;
import gyqx.spdhdi.myInfos.vo.ProvRegGoodsInfoQ;
import gyqx.spdhdi.myInfos.vo.ProvRegistGoodsPushQ;
import gyqx.spdhdi.myInfos.vo.ProvRegistGoodsPushQR;
import gyqx.spdhdi.myInfos.vo.ProvRegistInfoPushVo;
import gyqx.spdhdi.po.BasCompanyCertificateImage;
import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdhdi.po.ProvCns;
import gyqx.spdhdi.po.ProvGoodsHos;
import gyqx.spdhdi.po.ProvRegistGoodsPush;
import gyqx.spdhdi.po.ProvRegistInfoPush;
import gyqx.spdhdi.po.ProvRegistrationImage;

@Service
public class ProvRegistInfoPushService implements IProvRegistInfoPushService {

	@Resource
	private ProvFrwtsVoRDao frwtsVoRDao;
	@Resource
	private ProvRegistInfoPushDao dao;
	@Resource
	private ProvRegistGoodsPushDao goodsPushDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private CompanyInfoVoDao companyInfoVoDao;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;

	public ProvRegistInfoPush get(String id) {
		return dao.get(id);
	}

	public ProvRegistInfoPush add(ProvRegistInfoPush provRegistInfoPush) throws Exception {
		return dao.insertAndGet(provRegistInfoPush);
	}

	@Override
	public List<ProvRegistInfoPush> multipleAdd(List<ProvRegGoodsInfoQ> provRegGoodsInfoQs) throws Exception {
		List<ProvRegistInfoPush> infoPushs = new ArrayList<>();
		ProvRegGoodsInfoQ goodsInfoQ = provRegGoodsInfoQs.get(0);
		ProvRegistInfoPush infoPush = new ProvRegistInfoPush();
		infoPush.setId(atomUtil.newValue("sys.config"));
		infoPush.setProvRegistId(goodsInfoQ.getCertificateId());
		infoPush.setProvId(goodsInfoQ.getProvId());//
		infoPush.setHosId(goodsInfoQ.getHosId());//
		infoPush.setRegKind(goodsInfoQ.getRegKind());
		infoPush.setProductName(goodsInfoQ.getProductName());
		infoPush.setCertificateCode(goodsInfoQ.getCertificateCode());
		infoPush.setMfrsId(goodsInfoQ.getMfrsId());
		infoPush.setExpdtBeginDate(goodsInfoQ.getExpdtBeginDate());
		infoPush.setExpdtEndDate(goodsInfoQ.getExpdtEndDate());
		infoPush.setPushStatus("10");// 医院待审
		String userId = userOnlineStateUtils.getCurrent().getUserId();
		infoPush.setPushUid(userId);
		infoPush.setFillDate(new Date());
		infoPush = add(infoPush);
		infoPushs.add(infoPush);
		for (ProvRegGoodsInfoQ p : provRegGoodsInfoQs) {
			ProvRegistGoodsPush goodsPush = new ProvRegistGoodsPush();
			goodsPush.setId(atomUtil.newValue("sys.config"));
			goodsPush.setPushId(infoPush.getId());
			goodsPush.setProvId(infoPush.getProvId());
			goodsPush.setHosId(infoPush.getHosId());
			goodsPush.setCertificateId(infoPush.getProvRegistId());
			goodsPush.setGoodsId(p.getGoodsId());
			goodsPush.setUid(userId);
			goodsPush.setFillDate(new Date());
			goodsPush.setLastUpdateDatetime(new Date());
			goodsPushDao.insertAndGet(goodsPush);
		}
		return infoPushs;
	}

	public ProvRegistInfoPush update(ProvRegistInfoPush provRegistInfoPush) throws Exception {
		return dao.update(provRegistInfoPush);
	}
	
	@Override
	public ProvRegistInfoPush update(ProvRegistInfoPushVo provRegistInfoPushVo) throws Exception {
		// 如果是证件审核"接受"操作，则供应商供货目录及医院采购目录都要新插入相关数据
		if("30".equals(provRegistInfoPushVo.getPushStatus())){
			// 供应商供货目录添加
			addProvGoodsHos(provRegistInfoPushVo);
			// 医院采购目录添加
			addHosGood(provRegistInfoPushVo);
		}
		provRegistInfoPushVo.setAuditDate(new Date());
		return dao.update(provRegistInfoPushVo);
	}
	
	public void addHosGood(ProvRegistInfoPushVo provRegistInfoPushVo) throws Exception {
		List<ProvRegistGoodsPushQR> goodsPushQRs = provRegistInfoPushVo.getGoodsPushQRs();
		for(ProvRegistGoodsPushQR g:goodsPushQRs){
			HosGoodsInfo good = new HosGoodsInfo();
			good.setGoodsName(g.getGoodsName());
			good.setShortPinyin(PinYin.getShortPinyin(g.getGoodsName()));
			good.setUnit(g.getUnit());
			good.setGoodsGg(g.getGoodsGg());
			good.setMade(g.getMade());
			good.setMfrsId(g.getMfrsId());
			good.setMfrsCode((g.getMfrsCode()));
			good.setMasterCode(g.getMasterCode());
			good.setKindCode(g.getKindCode());
			good.setKind68code(g.getKind68code());
			good.setErpCode(g.getErpCode());
			good.setSpdGoodsCode(g.getSpdGoodsCode());
			good.setHitCode(g.getHitCode());
			good.setHitPrice(g.getHitPrice());
			good.setLbsx(g.getLbsx());
			good.setRemark(g.getRemark());

			good.setPrice(good.getPrice()!=null?good.getPrice():new BigDecimal(0));
			good.setUniqueCodeStrategy(good.getUniqueCodeStrategy()!=null?good.getUniqueCodeStrategy():"3");
			good.setTaxRate(good.getTaxRate()!=null?good.getTaxRate():new BigDecimal(0.17));

			String id = atomUtil.newValue("hos_goods_info_id");
			String hosId = provRegistInfoPushVo.getHosId();
			good.setHosId(hosId);
			good.setProvId(provRegistInfoPushVo.getProvId());
			good.setId(hosId + "|" + id);
			good.setFillDate(new Date());
			good.setLastUpdateDatetime(new Date());
			good.setVersion(0);
			good.setFlag("1");
			good.setPurMode("10");// 添加医院产品信息，采购模式，默认为"10"（实物采购）
			good.setCharging("1");
			good.setGoodsDesc(g.getGoodsName() + g.getGoodsGg());
			good.setUid(userOnlineStateUtils.getCurrent().getUserId());
			good.setGeneralName(g.getGoodsName());
			frwtsVoRDao.insertAndGet(good);
		}
	}
	
	public void addProvGoodsHos(ProvRegistInfoPushVo provRegistInfoPushVo) throws Exception {
		List<ProvRegistGoodsPushQR> goodsPushQRs = provRegistInfoPushVo.getGoodsPushQRs();
		for(ProvRegistGoodsPushQR g:goodsPushQRs){
			ProvGoodsHos good = new ProvGoodsHos();
			String id = atomUtil.newValue("prov_goods_hos_id"); 
			good.setId(id);
			good.setProvId(g.getProvId());
			good.setHosId(provRegistInfoPushVo.getHosId());
			good.setProvGoodsid(g.getId());
			good.setUnitRate(new BigDecimal(1));
			good.setFillDate(new Date());
			good.setLastUpdateDatetime(new Date());
			good.setVersion(0);
			good.setFlag("1");
			good.setUid(userOnlineStateUtils.getCurrent().getUserId());
			frwtsVoRDao.insert(good);
		}
	}
	
	public void addProvGoodsHosList(ProvRegistInfoPush provRegistInfoPush) throws Exception{
//		provRegistInfoPush.get
//		for(ProvGoodsHos good:goods){
//			ProvGoodsHos good = new ProvGoodsHos();
//			String id = atomUtil.newValue("prov_goods_hos_id"); 
//			good.setProvId(userOnline.getCurrent().getCorpId());
//			good.setId(id);
//			good.setFillDate(new Date());
//			good.setVersion(0);
//			good.setFlag("1");
//			good.setUid(userOnline.getCurrent().getUserId());
//			simpleDao.insert(good);
//		}
	}

	public int updateFields(ProvRegistInfoPush provRegistInfoPush, String fieldNames) throws Exception {
		return dao.updateFields(provRegistInfoPush, fieldNames);
	}

	public void delete(ProvRegistInfoPush provRegistInfoPush) throws Exception {
		dao.delete(provRegistInfoPush);
	}

	public ProvRegistInfoPush getByFieldName(String fieldName, Object fieldValue) throws Exception {
		return (ProvRegistInfoPush) dao.getByFieldName(fieldName, fieldValue);
	}

	public List<ProvRegistInfoPush> query(String predicate, String orderBy, Object... fieldValue) throws Exception {
		return (List<ProvRegistInfoPush>) dao.query(predicate, orderBy, fieldValue);
	}

	public List<ProvRegistInfoPush> list(ProvRegistInfoPush bean) throws Exception {
		return (List<ProvRegistInfoPush>) dao.list(bean);
	}

	public QueryResult<ProvRegistInfoPush> listByPage(QueryInfo<ProvRegistInfoPush> queryInfo) throws Exception {
		ProvRegistInfoPush provRegistInfoPush = queryInfo.getQueryObject();
		provRegistInfoPush.setProvId(userOnlineStateUtils.getCurrent().getCorpId());
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

	@Override
	public QueryResult<ProvRegistGoodsPushQR> pushlistByPage(QueryInfo<ProvRegistGoodsPushQ> queryInfo)
			throws Exception {
		ProvRegistGoodsPushQ provRegistInfoPush = queryInfo.getQueryObject();
		provRegistInfoPush.setProvId(userOnlineStateUtils.getCurrent().getCorpId());
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(goodsPushDao.list(queryInfo.getQueryObject()));
	}

	@Override
	public QueryResult<ProvRegistInfoPushVo> pushedRegCertsProvListByPage(QueryInfo<ProvRegistInfoPushVo> queryInfo)
			throws Exception {
		ProvRegistInfoPushVo infoPushVo = queryInfo.getQueryObject();
		infoPushVo.setHosId(userOnlineStateUtils.getCurrent().getCorpId());
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.pushedRegCertsProvListByPage(infoPushVo));
	}

	@Override
	public QueryResult<ProvRegistInfoPushVo> pushedGoodsProvListByPage(QueryInfo<ProvRegistInfoPushVo> queryInfo)
			throws Exception {
		List<ProvRegistInfoPushVo> infoPushVos = dao.pushedGoodsProvListByPage(queryInfo.getQueryObject());
		for (ProvRegistInfoPushVo p : infoPushVos) {
			ProvRegistInfoPushVo infoPushVo = new ProvRegistInfoPushVo();
			infoPushVo.setPushId(p.getId());// 推送ID
			List<ProvRegistGoodsPushQR> goodsPushQRs = dao.pushedGoodsList(infoPushVo);
			p.setGoodsPushQRs(goodsPushQRs);
		}
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(infoPushVos);
	}
	
	/**
	 * 待审证件
	 */
	@Override
	public Map<String, Object> getRetrospectMap(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception {
		ProvRegistInfoPushVo infoPushVo = queryInfo.getQueryObject();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 获取法人委托书对象
		List<ProvFrwtsVoR> frwtsVoRs = frwtsVoRDao.wtsList(infoPushVo);
		resultMap.put("frwt", frwtsVoRs.size()<1?new ProvFrwtsVoR():frwtsVoRs.get(0));
		
		// 获取授权书对象
		List<ProvJxsqsVo> jxsqs = frwtsVoRDao.certCheckJxsqs(infoPushVo);
		resultMap.put("jxsqs", jxsqs.size()<1?new ArrayList<ProvJxsqsVo>():jxsqs.get(0));
		
		// 获取承诺书书对象
		List<ProvCns> cns = frwtsVoRDao.cnsList(infoPushVo);
		resultMap.put("cns", cns.size()<1?new ArrayList<ProvCns>():cns.get(0));
		
		// 获取厂家三证对象
		List<BasMfrsInfoVo> mfrs = frwtsVoRDao.getBasMfrsList(infoPushVo);
		resultMap.put("mfrs", mfrs.size()<1?new ArrayList<BasMfrsInfoVo>():mfrs);
		
		// 获取供应商对象
		CompanyInfoVo companyInfoVo = getProvCompanyInfo(infoPushVo);
		resultMap.put("provInfo", companyInfoVo == null ? new CompanyInfoVo() : companyInfoVo);
		
		List<ProvRegistrationImage> registrationImages = frwtsVoRDao.getPushedRegCertsImgList(infoPushVo);
		resultMap.put("registCertImg", registrationImages.size()<1?new ArrayList<ProvRegistrationImage>():registrationImages);
		
		return resultMap;
	}
	
	/**
	 * 证件列表
	 */
	@Override
	public Map<String, Object> getPushedCertsMap(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception {
//		PageUtils.startPage(queryInfo);
		ProvRegistInfoPushVo infoPushVo = queryInfo.getQueryObject();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 获取法人委托书对象
		List<ProvFrwtsVoR> frwtsVoRs = frwtsVoRDao.wtsList(infoPushVo);
		resultMap.put("frwt", frwtsVoRs.size()<1?new ArrayList<ProvFrwtsVoR>():frwtsVoRs);
		// 获取承诺书书对象
		List<ProvCns> cns = frwtsVoRDao.cnsList(infoPushVo);
		resultMap.put("cns", cns.size()<1?new ArrayList<ProvCns>():cns);
		// 获取授权书对象
		List<ProvJxsqsVo> jxsqs = frwtsVoRDao.jxsqsList(infoPushVo);
		resultMap.put("jxsqs", jxsqs.size()<1?new ArrayList<ProvJxsqsVo>():jxsqs);
		// 获取厂家三证对象
		List<BasMfrsInfoVo> mfrs = frwtsVoRDao.getBasMfrsList(infoPushVo);
		resultMap.put("mfrs", mfrs.size()<1?new ArrayList<BasMfrsInfoVo>():mfrs);
		// 获取注册证对象
		List<ProvRegistInfoPushVo> registCerts = frwtsVoRDao.getPushedRegCertsList(infoPushVo);
		for(ProvRegistInfoPushVo p:registCerts){
			List<ProvRegistrationImage> registrationImages = frwtsVoRDao.getPushedRegCertsImgList(p);
			p.setRegistrationImages(registrationImages.size()<1?new ArrayList<ProvRegistrationImage>():registrationImages);
		}
		resultMap.put("registCerts", registCerts.size()<1?new ArrayList<ProvRegistInfoPushVo>():registCerts);
		// 获取供应商对象
		CompanyInfoVo companyInfoVo = getProvCompanyInfo(infoPushVo);
		resultMap.put("provInfo", companyInfoVo == null ? new CompanyInfoVo() : companyInfoVo);
		// 获取过期证件列表
		List<OutdateCertsVo> certsVos = frwtsVoRDao.getOutdateCerts(infoPushVo);
		
		PageHelper.startPage(queryInfo.getPage(), queryInfo.getRows());
		QueryResult<OutdateCertsVo> certsVosResult = new QueryResult<>();
		PageInfo<OutdateCertsVo> pageInfo = new PageInfo<OutdateCertsVo>(certsVos);
		certsVosResult.setData(pageInfo.getList());
		certsVosResult.setPageNum(pageInfo.getPageNum());
		certsVosResult.setPageSize(pageInfo.getPageSize());
		certsVosResult.setTotal(pageInfo.getTotal());
		
		
		resultMap.put("certsVos", certsVos == null ? new ArrayList<OutdateCertsVo>() : certsVosResult);
//		resultMap.put("certsVos", certsVos == null ? new ArrayList<OutdateCertsVo>() : PageUtils.endPage(certsVos));
		
		return resultMap;
	}
	
	/**
	 * 获取供应商信息
	 * @param infoPushVo
	 * @return
	 */
	public CompanyInfoVo getProvCompanyInfo(ProvRegistInfoPushVo infoPushVo){
		CompanyInfoVo companyInfoVo = null;
		companyInfoVo = companyInfoVoDao.getCompanyInfo(infoPushVo.getProvId());
		List<CompanyCertificateVo> companyCertificateVos = null;
		companyCertificateVos = companyInfoVoDao.getCompanyCertificateVo(companyInfoVo.getId());
		for (CompanyCertificateVo c : companyCertificateVos) {
			List<BasCompanyCertificateImage> basCompanyCertificateImages = null;
			basCompanyCertificateImages = companyInfoVoDao.getBasCompanyCertificateImage(c.getId());
			c.setBasCompanyCertificateImages(basCompanyCertificateImages);
		}
		companyInfoVo.setCompanyCertificateVos(companyCertificateVos);
		return companyInfoVo;
		
	}

	@Override
	public ProvRegistInfoPush updateProvRegistInfoPush(ProvRegistInfoPush bean) throws Exception {
		return dao.update(bean);
	}

	@Override
	public List<OutdateCertsImgVo> getOutdateCertsImg(OutdateCertsVo outdateCertsVo) throws Exception {
		outdateCertsVo.setHosId(userOnlineStateUtils.getCurrent().getCorpId());
		String type = outdateCertsVo.getCertType();
		if("10".equals(type) || "20".equals(type) || "30".equals(type)){// 供应商三证查询
			if("10".equals(type)){
				outdateCertsVo.setCertKind("1");
			}else if("20".equals(type)){
				outdateCertsVo.setCertKind("2");
			}else{
				outdateCertsVo.setCertKind("3");
			}
			return frwtsVoRDao.getOutdateCompanyCertsImg(outdateCertsVo);
		}else if("40".equals(type) || "50".equals(type) || "60".equals(type)){// 生产厂家三证查询
			if("40".equals(type)){
				outdateCertsVo.setCertKind("1");
			}else if("50".equals(type)){
				outdateCertsVo.setCertKind("2");
			}else{
				outdateCertsVo.setCertKind("3");
			}
			return frwtsVoRDao.getOutdateMfrsCertsImg(outdateCertsVo);
		}else if("70".equals(type)){// 医疗器械注册证
			return frwtsVoRDao.getOutdateRegistCertsImg(outdateCertsVo);
		}else if("80".equals(type)){// 法人委托书
			return frwtsVoRDao.getOutdateWtsCertsImg(outdateCertsVo);
		}else if("90".equals(type)){// 承诺书
			return frwtsVoRDao.getOutdateCnsCertsImg(outdateCertsVo);
		}else{
			return null;
		}
	}

}
