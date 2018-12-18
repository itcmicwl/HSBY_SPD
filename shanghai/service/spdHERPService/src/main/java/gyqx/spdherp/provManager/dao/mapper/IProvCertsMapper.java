package gyqx.spdherp.provManager.dao.mapper;

import java.util.List;

import gyqx.spdhdi.myInfos.vo.BasMfrsInfoVo;
import gyqx.spdhdi.myInfos.vo.ProvFrwtsVoR;
import gyqx.spdhdi.myInfos.vo.ProvJxsqsVo;
import gyqx.spdhdi.myInfos.vo.ProvRegistInfoPushVo;
import gyqx.spdhdi.po.ProvCns;
import gyqx.spdhdi.po.ProvRegistrationImage;
import gyqx.spdherp.provManager.vo.OutdateCertsVo;
import gyqx.spdherp.provManager.vo.ProvIncorruptAgrmtVo;
import gyqx.spdherp.provManager.vo.ProvSalesRepVo;

public interface IProvCertsMapper {

	List<OutdateCertsVo> queryProvOutdateCerts(OutdateCertsVo outdateCertsVo);

	List<ProvRegistInfoPushVo> getPushedRegCertsList(ProvRegistInfoPushVo provRegistInfoPushVo);

	List<ProvRegistrationImage> getPushedRegCertsImgList(ProvRegistInfoPushVo provRegistInfoPushVo);

	List<BasMfrsInfoVo> getBasMfrsList(ProvRegistInfoPushVo provRegistInfoPushVo);
	
	List<ProvJxsqsVo> jxsqsList(ProvRegistInfoPushVo provRegistInfoPushVo);
	
	List<ProvFrwtsVoR> wtsList(ProvRegistInfoPushVo queryInfo);
	
	List<ProvCns> cnsList(ProvRegistInfoPushVo queryInfo);
	
	List<ProvSalesRepVo> salesRepList(ProvRegistInfoPushVo queryInfo);
	
	List<ProvIncorruptAgrmtVo> listIncorruptAgrmt(ProvRegistInfoPushVo queryInfo);

}
