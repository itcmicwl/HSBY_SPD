package gyqx.spdhdi.myInfos.dao.mapper;

import java.util.List;

import gyqx.spdhdi.myInfos.vo.BasMfrsInfoVo;
import gyqx.spdhdi.myInfos.vo.OutdateCertsImgVo;
import gyqx.spdhdi.myInfos.vo.OutdateCertsVo;
import gyqx.spdhdi.myInfos.vo.ProvFrwtsVoR;
import gyqx.spdhdi.myInfos.vo.ProvJxsqsVo;
import gyqx.spdhdi.myInfos.vo.ProvRegistInfoPushVo;
import gyqx.spdhdi.po.ProvCns;
import gyqx.spdhdi.po.ProvRegistrationImage;

public interface IProvFrwtsVoRMapper {

	List<ProvFrwtsVoR> wtsList(ProvRegistInfoPushVo queryInfo);

	List<ProvCns> cnsList(ProvRegistInfoPushVo queryInfo);

	List<ProvJxsqsVo> jxsqsList(ProvRegistInfoPushVo queryInfo);

	List<ProvJxsqsVo> certCheckJxsqs(ProvRegistInfoPushVo queryInfo);

	List<BasMfrsInfoVo> getBasMfrsList(ProvRegistInfoPushVo queryInfo);

	List<ProvRegistInfoPushVo> getPushedRegCertsList(ProvRegistInfoPushVo queryInfo);

	List<ProvRegistrationImage> getPushedRegCertsImgList(ProvRegistInfoPushVo queryInfo);

	List<OutdateCertsVo> getOutdateCerts(ProvRegistInfoPushVo qbean);
	
	//////////////////////////////////////////////////////////////////////////////////////////
	List<OutdateCertsImgVo> getOutdateCompanyCertsImg(OutdateCertsVo outdateCertsVo);

	List<OutdateCertsImgVo> getOutdateMfrsCertsImg(OutdateCertsVo outdateCertsVo);

	List<OutdateCertsImgVo> getOutdateRegistCertsImg(OutdateCertsVo outdateCertsVo);

	List<OutdateCertsImgVo> getOutdateWtsCertsImg(OutdateCertsVo outdateCertsVo);

	List<OutdateCertsImgVo> getOutdateCnsCertsImg(OutdateCertsVo outdateCertsVo);

}
