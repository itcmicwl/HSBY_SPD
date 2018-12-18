package gyqx.spdhdi.myInfos.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import common.base.PinYin;
import common.db.SimpleDao;
import common.exception.ValidateException;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdhdi.myInfos.dao.CompanyInfoVoDao;
import gyqx.spdhdi.myInfos.service.ICompanyVoService;
import gyqx.spdhdi.myInfos.vo.CompanyCertificateVo;
import gyqx.spdhdi.myInfos.vo.CompanyInfoVo;
import gyqx.spdhdi.po.BasCompanyCertificate;
import gyqx.spdhdi.po.BasCompanyCertificateImage;

@Service
public class CompanyVoService implements ICompanyVoService {
	@Resource
	private SimpleDao simpleDao;
	@Resource
	private CompanyInfoVoDao companyInfoVoDao;
	@Resource
	private SysConfigUtil configUtil;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;

	@Override
	public CompanyInfoVo getCompanyInfo(String corpId) throws Exception {
		CompanyInfoVo companyInfoVo = companyInfoVoDao.getCompanyInfo(corpId);
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
	public List<CompanyCertificateVo> getCompanyCertificateVo(String companyId) throws Exception {
		return companyInfoVoDao.getCompanyCertificateVo(companyId);
	}
	
	@Override
	public CompanyInfoVo updateCompanyInfoVo(CompanyInfoVo companyInfoVo) throws Exception {
		companyInfoVo.setUid(userOnlineStateUtils.getCurrent().getUserId());
		companyInfoVo.setShortPinyin(PinYin.getShortPinyin(companyInfoVo.getCname()));
		companyInfoVoDao.updateCompanyInfoVo(companyInfoVo);
		return companyInfoVo;
	}

	@Override
	public CompanyInfoVo insertCompanyCertificate(CompanyInfoVo companyInfoVo) throws Exception {
		List<CompanyCertificateVo> certificateVos = companyInfoVo.getCompanyCertificateVos();
		String uid = userOnlineStateUtils.getCurrent().getUserId();
		for(CompanyCertificateVo c : certificateVos){// 前台保证图片信息certificateVos不会为空
			// 清空企业某种证照(比如营业执照)的照片，以便重新添加
			companyInfoVoDao.deleteBasCertificateImg(c);
			companyInfoVoDao.deleteBasCertificate(c);
				String cid = atomUtil.newValue("sys.config");
				c.setId(cid);
				c.setUid(uid);
				c.setCompanyId(companyInfoVo.getId());
				c.setCertCode("");
				companyInfoVoDao.insertCompanyCertificate(c);
				List<BasCompanyCertificateImage> basCompanyCertificateImages = c.getBasCompanyCertificateImages();
				for(BasCompanyCertificateImage b:basCompanyCertificateImages){
					String bid = atomUtil.newValue("sys.config");
					b.setId(bid);
					b.setUid(uid);
					b.setCertificateId(c.getId());
					simpleDao.insert(b);
				}
				c.setBasCompanyCertificateImages(basCompanyCertificateImages);
		}
		companyInfoVo.setCompanyCertificateVos(certificateVos);
		return companyInfoVo;
	}

	@Override
	public CompanyInfoVo updateCompanyCertificate(CompanyInfoVo companyInfoVo) throws Exception {
		List<CompanyCertificateVo> certificateVos = companyInfoVo.getCompanyCertificateVos();
		String uid = userOnlineStateUtils.getCurrent().getUserId();
		String companyId = userOnlineStateUtils.getCurrent().getCorpId();
		for(CompanyCertificateVo c : certificateVos){
			BasCompanyCertificate bean = new BasCompanyCertificate();
			bean.setCertCode(c.getCertCode());
			bean.setCertKind(c.getCertKind());
			bean.setCompanyId(c.getCompanyId());
			bean.setExpdtBeginDate(c.getExpdtBeginDate());
			bean.setExpdtEndDate(c.getExpdtEndDate());
			bean.setIsValiding(c.getIsValiding());
			bean.setUid(uid);
			bean.setFillDate(new Date());
			bean.setLastUpdateDatetime(new Date());
			if(StringUtils.isEmpty(c.getId())){
				bean.setVersion(c.getVersion());
				String cid = atomUtil.newValue("sys.config");
				bean.setId(cid);
				bean.setFillDate(new Date());
				simpleDao.insertAndGet(bean); 
			}else{
				//查出版本信息
				BasCompanyCertificate basCompanyCertificate = simpleDao.get(BasCompanyCertificate.class,c.getId());
				bean.setId(c.getId());
				bean.setVersion(basCompanyCertificate.getVersion());
				simpleDao.update(bean);
			}
		}
		companyInfoVo.setCompanyCertificateVos(certificateVos);
		//若医院为三证合一，则删除税务代码证和组织机构代码证
		if(StringUtils.equals(companyInfoVo.getIsThreeInOne(), "1")){
			CompanyCertificateVo c3 = new CompanyCertificateVo();
			c3.setCompanyId(companyId);
			c3.setCertKind("3");
			CompanyCertificateVo c4 = new CompanyCertificateVo();
			c4.setCompanyId(companyId);
			c4.setCertKind("4");
			companyInfoVoDao.deleteBasCertificateImg(c3);
			companyInfoVoDao.deleteBasCertificate(c3);
			companyInfoVoDao.deleteBasCertificateImg(c4);
			companyInfoVoDao.deleteBasCertificate(c4);
		}
		return updateCompanyInfoVo(companyInfoVo);
	}

	@Override
	public int insertCompanyCertificateImage(BasCompanyCertificateImage certificateImageVo) throws Exception {
		return companyInfoVoDao.insertCompanyCertificateImage(certificateImageVo);
	}

	@Override
	public int updateCompanyCertificateImage(BasCompanyCertificateImage certificateImageVo) throws Exception {
		return companyInfoVoDao.updateCompanyCertificateImage(certificateImageVo);
	}

	public void checkDuplicate(CompanyInfoVo com) throws Exception {
		long num = simpleDao.queryForObject("select count(*) from bas_company_info where code= ?  and id<>?",
				Long.class, com.getCode(), com.getId());
		if (num > 0) {
			ValidateException.Throw("code", com.getCode() + "已存在！", com.getCode());
		}
		num = simpleDao.queryForObject("select count(*) from bas_company_info where cname=?  and id<>?", 
				Long.class, com.getCname(), com.getId());
		if (num > 0) {
			ValidateException.Throw("cname", com.getCname() + "已存在！", com.getCname());
		}
	}

	@Override
	public int updateLogo(CompanyInfoVo company) throws Exception {
		String uid = userOnlineStateUtils.getCurrent().getUserId();
		String id = userOnlineStateUtils.getCurrent().getCorpId();
		company.setUid(uid);
		company.setId(id);
		return companyInfoVoDao.updateLogo(company);
	}

	@Override
	public int deleteImage(String id) throws Exception {
		return companyInfoVoDao.deleteImage(id);
	}

	@Override
	public CompanyInfoVo getCompanyInfoById(String id) throws Exception {
		if(StringUtils.isEmpty(id)){
			id = userOnlineStateUtils.getCurrent().getCorpId();
		}
		return companyInfoVoDao.getCompanyInfo(id);
	}

}
