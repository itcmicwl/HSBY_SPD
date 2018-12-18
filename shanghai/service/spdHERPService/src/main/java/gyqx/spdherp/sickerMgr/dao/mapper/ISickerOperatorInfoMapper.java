package gyqx.spdherp.sickerMgr.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.SickerOperatorInfo;
import gyqx.spdherp.sickerMgr.vo.SickerOperatorInfoVo;

public interface ISickerOperatorInfoMapper {
	int update(SickerOperatorInfo bean);
	int insert(SickerOperatorInfo bean);
	List<SickerOperatorInfoVo> list(SickerOperatorInfoVo queryInfo) ;
	List<SickerOperatorInfo> getOperDepts(SickerOperatorInfo SickerOperatorInfo);
}

