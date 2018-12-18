package gyqx.spdherp.sickerMgr.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.HisKs;
import gyqx.spdherp.po.SickerInHospitalInfo;
import gyqx.spdherp.po.SickerInfo;
import gyqx.spdherp.sickerMgr.vo.SickerInHospitalInfoVo;
import org.springframework.data.repository.query.Param;

public interface ISickerInHospitalInfoMapper {
	int update(SickerInHospitalInfo bean);
	int insert(SickerInHospitalInfo bean);
	List<SickerInHospitalInfoVo> list(SickerInHospitalInfoVo sickerInHospitalInfoVo) ;
	List<HisKs> getHisDepts(HisKs hisKs);
}

