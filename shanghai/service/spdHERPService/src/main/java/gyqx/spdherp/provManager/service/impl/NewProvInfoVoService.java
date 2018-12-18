package gyqx.spdherp.provManager.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdhdi.myInfos.dao.CompanyInfoVoDao;
import gyqx.spdhdi.myInfos.vo.CompanyCertificateVo;
import gyqx.spdhdi.myInfos.vo.CompanyInfoVo;
import gyqx.spdhdi.myInfos.vo.ProvRegistInfoPushVo;
import gyqx.spdhdi.po.BasCompanyCertificateImage;
import gyqx.spdherp.provManager.dao.NewProvInfoVoDao;
import gyqx.spdherp.provManager.service.INewProvInfoVoService;
import gyqx.spdherp.provManager.vo.ProvHosInfoVo;

@Service
public class NewProvInfoVoService implements INewProvInfoVoService {
	@Resource
	NewProvInfoVoDao newProvInfoVoDao;
	@Resource
	SimpleDao simpleDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private CompanyInfoVoDao companyInfoVoDao;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;

	@Override
	public QueryResult<ProvHosInfoVo> getNewProvInfoVoList(QueryInfo<ProvHosInfoVo> queryInfo) throws Exception {
		QueryResult<ProvHosInfoVo> lstProvHosInfoVosInfo = new QueryResult<ProvHosInfoVo>();
		queryInfo.getQueryObject().setHosId(userOnlineStateUtils.getCurrent().getCorpId());
		PageInfo<ProvHosInfoVo> provHosInfoVoPage = newProvInfoVoDao.getNewProvInfoVoList(queryInfo);
		lstProvHosInfoVosInfo.setData(provHosInfoVoPage.getList());
		lstProvHosInfoVosInfo.setPageNum(provHosInfoVoPage.getPageNum());
		lstProvHosInfoVosInfo.setPageSize(provHosInfoVoPage.getPageSize());
		lstProvHosInfoVosInfo.setTotal(provHosInfoVoPage.getTotal());
		return lstProvHosInfoVosInfo;
	}

	@Override
	public CompanyInfoVo getProvCompanyInfo(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception {
		CompanyInfoVo companyInfoVo = null;
		companyInfoVo = companyInfoVoDao.getCompanyInfo(queryInfo.getQueryObject().getProvId());
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
