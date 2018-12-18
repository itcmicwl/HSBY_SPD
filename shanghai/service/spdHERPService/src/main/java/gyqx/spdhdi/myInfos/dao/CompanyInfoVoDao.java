package gyqx.spdhdi.myInfos.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdhdi.myInfos.dao.mapper.ICompanyInfoVoMapper;
import gyqx.spdhdi.myInfos.vo.CompanyCertificateVo;
import gyqx.spdhdi.myInfos.vo.CompanyInfoVo;
import gyqx.spdhdi.po.BasCompanyCertificateImage;

@Repository
public class CompanyInfoVoDao {
	@Resource
	private ICompanyInfoVoMapper mapper;
	
	public CompanyInfoVo getCompanyInfo(String id){
		return mapper.getCompanyInfo(id);
	}
	
	public List<CompanyCertificateVo> getCompanyCertificateVo(String companyId){
		return mapper.getCompanyCertificateVo(companyId);
	}
	
	public List<BasCompanyCertificateImage> getBasCompanyCertificateImage(String certificateId){
		return mapper.getBasCompanyCertificateImage(certificateId);
	}
	
	public int insertCompanyCertificate(CompanyCertificateVo certificateVo){
		return mapper.insertCompanyCertificate(certificateVo);
	}
	
	public int updateCompanyCertificate(CompanyCertificateVo certificateVo){
		return mapper.updateCompanyCertificate(certificateVo);
	}
	
	public int updateCompanyInfoVo(CompanyInfoVo companyInfoVo){
		return mapper.updateCompanyInfoVo(companyInfoVo);
	}
	
	public int insertCompanyCertificateImage(BasCompanyCertificateImage certificateImageVo){
		return mapper.insertCompanyCertificateImage(certificateImageVo);
	}

	public int updateCompanyCertificateImage(BasCompanyCertificateImage certificateImageVo){
		return mapper.updateCompanyCertificateImage(certificateImageVo);
	}

	public int updateLogo(CompanyInfoVo company) {
		return mapper.updateLogo(company);
	}

	public int deleteImage(String id) {
		return mapper.deleteImage(id);
	}

	public int deleteBasCertificateImg(CompanyCertificateVo basCompanyCertificate) {
		return mapper.deleteBasCertificateImg(basCompanyCertificate);
	}

	public int deleteBasCertificate(CompanyCertificateVo basCompanyCertificate) {
		return mapper.deleteBasCertificate(basCompanyCertificate);
	}

}
