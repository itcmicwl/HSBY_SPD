package gyqx.spdhdi.myInfos.dao.mapper;

import java.util.List;

import gyqx.spdhdi.myInfos.vo.CompanyCertificateVo;
import gyqx.spdhdi.myInfos.vo.CompanyInfoVo;
import gyqx.spdhdi.po.BasCompanyCertificateImage;

public interface ICompanyInfoVoMapper {
	
	CompanyInfoVo getCompanyInfo(String id);

	List<BasCompanyCertificateImage> getBasCompanyCertificateImage(String certificateId);

	List<CompanyCertificateVo> getCompanyCertificateVo(String companyId);

	int updateCompanyInfoVo(CompanyInfoVo companyInfoVo);
	
	int insertCompanyCertificate(CompanyCertificateVo certificateVo);
	
	int updateCompanyCertificate(CompanyCertificateVo certificateVo);

	int insertCompanyCertificateImage(BasCompanyCertificateImage certificateImageVo);

	int updateCompanyCertificateImage(BasCompanyCertificateImage certificateImageVo);

	int updateLogo(CompanyInfoVo company);

	int deleteImage(String id);	
	
	int deleteBasCertificateImg(CompanyCertificateVo basCompanyCertificate);	

	int deleteBasCertificate(CompanyCertificateVo basCompanyCertificate);
	
}
