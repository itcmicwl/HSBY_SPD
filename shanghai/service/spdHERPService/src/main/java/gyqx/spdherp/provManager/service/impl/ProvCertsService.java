package gyqx.spdherp.provManager.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import gyqx.spdhdi.myInfos.dao.CompanyInfoVoDao;
import gyqx.spdhdi.myInfos.vo.BasMfrsInfoVo;
import gyqx.spdhdi.myInfos.vo.CompanyCertificateVo;
import gyqx.spdhdi.myInfos.vo.CompanyInfoVo;
import gyqx.spdhdi.myInfos.vo.ProvFrwtsVoR;
import gyqx.spdhdi.myInfos.vo.ProvJxsqsVo;
import gyqx.spdhdi.myInfos.vo.ProvRegistInfoPushVo;
import gyqx.spdhdi.po.BasCompanyCertificateImage;
import gyqx.spdhdi.po.ProvCns;
import gyqx.spdherp.provManager.dao.ProvCertsDao;
import gyqx.spdherp.provManager.service.IProvCertsService;
import gyqx.spdherp.provManager.vo.OutdateCertsImgVo;
import gyqx.spdherp.provManager.vo.OutdateCertsVo;
import gyqx.spdherp.provManager.vo.ProvIncorruptAgrmtVo;
import gyqx.spdherp.provManager.vo.ProvSalesRepVo;

@Service
public class ProvCertsService implements IProvCertsService {
	@Resource
	ProvCertsDao dao;
	@Resource
	SimpleDao simpleDao;
	@Resource
	private CompanyInfoVoDao companyInfoVoDao;
	@Resource
	private SysAtomUtil atomUtil;

	/**
	 * 过期证件列表查询
	 */
	@Override
	public QueryResult<OutdateCertsVo> queryProvOutdateCerts(QueryInfo<OutdateCertsVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.queryProvOutdateCerts(queryInfo.getQueryObject()));
	}
	
	/**
	 * 注册证列表查询
	 */
	@Override
	public QueryResult<ProvRegistInfoPushVo> getPushedRegCertsList(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.getPushedRegCertsList(queryInfo.getQueryObject()));
	}
	
	/**
	 * 生产厂商列表
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@Override
	public QueryResult<BasMfrsInfoVo> getBasMfrsList(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception{
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.getBasMfrsList(queryInfo.getQueryObject()));
	}
	
	/**
	 * 经销授权书列表
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@Override
	public QueryResult<ProvJxsqsVo> jxsqsList(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.jxsqsList(queryInfo.getQueryObject()));
	}
	
	/**
	 * 法人委托书列表
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@Override
	public QueryResult<ProvFrwtsVoR> wtsList(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.wtsList(queryInfo.getQueryObject()));
	}
	
	/**
	 * 承诺书列表
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@Override
	public QueryResult<ProvCns> cnsList(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.cnsList(queryInfo.getQueryObject()));
	}
	
	/**
	 * 销售代表列表
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@Override
	public QueryResult<ProvSalesRepVo> salesRepList(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.salesRepList(queryInfo.getQueryObject()));
	}
	
	/**
	 * 廉政协议书列表
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@Override
	public QueryResult<ProvIncorruptAgrmtVo> listIncorruptAgrmt(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.listIncorruptAgrmt(queryInfo.getQueryObject()));
	}

	/**
	 * 过期证件图片路径查询
	 */
	@Override
	public List<OutdateCertsImgVo> queryOutdateCertsImg(OutdateCertsVo outdateCertsVo) throws Exception {
		String sql = "";
		String type = outdateCertsVo.getCertType();
		if("10".equals(type) || "20".equals(type) || "30".equals(type)){// 供应商三证查询
			if("10".equals(type)){
				outdateCertsVo.setCertKind("1");
			}else if("20".equals(type)){
				outdateCertsVo.setCertKind("2");
			}else{
				outdateCertsVo.setCertKind("3");
			}
			sql = "SELECT m.file_path as filePath FROM bas_company_certificate_image m WHERE m.certificate_id IN"
					+ "(SELECT c.id FROM bas_company_certificate c WHERE c.company_id = ?  AND c.cert_kind = ?)";
			return simpleDao.queryForList(sql, OutdateCertsImgVo.class, outdateCertsVo.getAboutId(),outdateCertsVo.getCertKind());
			
		}else if("40".equals(type) || "50".equals(type) || "60".equals(type)){// 生产厂家三证查询
			if("40".equals(type)){
				outdateCertsVo.setCertKind("1");
			}else if("50".equals(type)){
				outdateCertsVo.setCertKind("2");
			}else{
				outdateCertsVo.setCertKind("3");
			}
			sql = "SELECT m.file_path as filePath FROM prov_mfrs_certificate_image m WHERE m.certificate_id IN "
					+ "(SELECT c.id FROM prov_mfrs_certificate c WHERE c.id = ? AND c.cert_kind = ?)";
			return simpleDao.queryForList(sql, OutdateCertsImgVo.class, outdateCertsVo.getId(),outdateCertsVo.getCertKind());
			
		}else if("70".equals(type)){// 医疗器械注册证
			sql = "SELECT m.file_path as filePath FROM prov_registration_image m WHERE m.certificate_id IN"
					+ "(SELECT p.id FROM prov_registration_info p WHERE p.prov_id = ? AND p.certificate_code = ?)";
			return simpleDao.queryForList(sql, OutdateCertsImgVo.class, outdateCertsVo.getAboutId(),outdateCertsVo.getAboutCode());
			
		}else if("80".equals(type)){// 法人委托书
			sql = "SELECT p.file_path_wtr as filePath FROM prov_frwts p WHERE p.prov_id = ? AND p.hos_id = ?";
			return simpleDao.queryForList(sql, OutdateCertsImgVo.class, outdateCertsVo.getAboutId(),outdateCertsVo.getHosId());
			
		}else if("90".equals(type)){// 承诺书
			sql = "SELECT p.file_path_wts as filePath FROM prov_cns p WHERE p.prov_id = ? AND p.hos_id = ?";
			return simpleDao.queryForList(sql, OutdateCertsImgVo.class, outdateCertsVo.getAboutId(),outdateCertsVo.getHosId());
			
		}else if("91".equals(type)){// 销售代表
			sql = "SELECT p.file_path as filePath FROM prov_sales_rep p WHERE p.prov_id = ? AND p.hos_id = ?";
			return simpleDao.queryForList(sql, OutdateCertsImgVo.class, outdateCertsVo.getAboutId(),outdateCertsVo.getHosId());
		}else {// 廉政协议书
			sql = "SELECT p.file_path as filePath FROM prov_incorrupt_agreement p WHERE p.prov_id = ? AND p.hos_id = ?";
			return simpleDao.queryForList(sql, OutdateCertsImgVo.class, outdateCertsVo.getAboutId(),outdateCertsVo.getHosId());
		}
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

}
