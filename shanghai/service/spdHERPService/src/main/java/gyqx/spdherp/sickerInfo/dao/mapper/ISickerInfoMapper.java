package gyqx.spdherp.sickerInfo.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.SickerInfo;
 
public interface ISickerInfoMapper {
	int update(SickerInfo bean);
	int insert(SickerInfo bean);
	List<SickerInfo> list(SickerInfo queryInfo) ;
}

