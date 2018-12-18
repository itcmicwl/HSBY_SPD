package gyqx.spdherp.provManager.dao.mapper;

import java.util.List;

import gyqx.spdherp.provManager.vo.ProvHosInfoVo;

public interface INewProvInfoMapper {

	List<ProvHosInfoVo> getNewProvInfoVoList(ProvHosInfoVo basMfrsInfo);

}