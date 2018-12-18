package gyqx.spdherp.provManager.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdhdi.myInfos.vo.BasMfrsInfoVo;
import gyqx.spdhdi.myInfos.vo.ProvFrwtsVoR;
import gyqx.spdhdi.myInfos.vo.ProvJxsqsVo;
import gyqx.spdhdi.myInfos.vo.ProvRegistInfoPushVo;
import gyqx.spdhdi.po.ProvCns;
import gyqx.spdhdi.po.ProvRegistrationImage;
import gyqx.spdherp.provManager.dao.mapper.IProvCertsMapper;
import gyqx.spdherp.provManager.vo.OutdateCertsVo;
import gyqx.spdherp.provManager.vo.ProvIncorruptAgrmtVo;
import gyqx.spdherp.provManager.vo.ProvSalesRepVo;

@Repository
public class ProvCertsDao {
	@Resource
	IProvCertsMapper mapper;

	public List<OutdateCertsVo> queryProvOutdateCerts(OutdateCertsVo outdateCertsVo) {
		return mapper.queryProvOutdateCerts(outdateCertsVo);
	}

	public List<ProvRegistInfoPushVo> getPushedRegCertsList(ProvRegistInfoPushVo provRegistInfoPushVo) {
		List<ProvRegistInfoPushVo> registCerts = mapper.getPushedRegCertsList(provRegistInfoPushVo);
		for(ProvRegistInfoPushVo p:registCerts){
			List<ProvRegistrationImage> registrationImages = this.getPushedRegCertsImgList(p);
			p.setRegistrationImages(registrationImages.size()<1?new ArrayList<ProvRegistrationImage>():registrationImages);
		}
		return registCerts;
	}
	
	public List<ProvRegistrationImage> getPushedRegCertsImgList(ProvRegistInfoPushVo provRegistInfoPushVo){
		return mapper.getPushedRegCertsImgList(provRegistInfoPushVo);
	}
	
	public List<BasMfrsInfoVo> getBasMfrsList(ProvRegistInfoPushVo provRegistInfoPushVo){
		return mapper.getBasMfrsList(provRegistInfoPushVo);
	}
	
	public List<ProvJxsqsVo> jxsqsList(ProvRegistInfoPushVo qbean) throws Exception {
		return mapper.jxsqsList(qbean);
	}
	
	public List<ProvFrwtsVoR> wtsList(ProvRegistInfoPushVo qbean) throws Exception {
		return mapper.wtsList(qbean);
	}
	
	public List<ProvCns> cnsList(ProvRegistInfoPushVo qbean) throws Exception {
		return mapper.cnsList(qbean);
	}
	
	public List<ProvSalesRepVo> salesRepList(ProvRegistInfoPushVo qbean) throws Exception {
		return mapper.salesRepList(qbean);
	}
	
	public List<ProvIncorruptAgrmtVo> listIncorruptAgrmt(ProvRegistInfoPushVo qbean) throws Exception {
		return mapper.listIncorruptAgrmt(qbean);
	}

}
