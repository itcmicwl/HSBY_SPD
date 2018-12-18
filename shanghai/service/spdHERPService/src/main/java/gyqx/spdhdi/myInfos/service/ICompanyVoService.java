package gyqx.spdhdi.myInfos.service;

import java.util.List;

import gyqx.spdhdi.myInfos.vo.CompanyCertificateVo;
import gyqx.spdhdi.myInfos.vo.CompanyInfoVo;
import gyqx.spdhdi.po.BasCompanyCertificateImage;

public interface ICompanyVoService {

	public CompanyInfoVo getCompanyInfo(String corpId) throws Exception;
	
	public CompanyInfoVo getCompanyInfoById(String id) throws Exception;

	public List<CompanyCertificateVo> getCompanyCertificateVo(String companyId) throws Exception;

	public CompanyInfoVo updateCompanyInfoVo(CompanyInfoVo com) throws Exception;

	public CompanyInfoVo insertCompanyCertificate(CompanyInfoVo companyInfoVo) throws Exception;

	public CompanyInfoVo updateCompanyCertificate(CompanyInfoVo companyInfoVo) throws Exception;
	
	public int insertCompanyCertificateImage(BasCompanyCertificateImage certificateImageVo) throws Exception;

	public int updateCompanyCertificateImage(BasCompanyCertificateImage certificateImageVo) throws Exception;

	public int updateLogo(CompanyInfoVo company) throws Exception;

	public int deleteImage(String id) throws Exception;

}
