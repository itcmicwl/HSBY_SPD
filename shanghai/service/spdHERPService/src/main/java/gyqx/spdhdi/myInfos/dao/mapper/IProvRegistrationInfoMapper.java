package gyqx.spdhdi.myInfos.dao.mapper;

import java.util.List;

import gyqx.spdhdi.po.ProvRegistrationInfo;
 
public interface IProvRegistrationInfoMapper {
	int update(ProvRegistrationInfo bean);
	List<ProvRegistrationInfo> list(ProvRegistrationInfo queryInfo) ;
}

