package gyqx.spdherp.surgery.dao.mapper;

import gyqx.spdherp.po.SurgeryPkg;
import gyqx.spdherp.surgery.vo.SurgeryPkgListVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISterilizationMapper {
	List<SurgeryPkgVo> listFirst(SurgeryPkgVo queryInfo);

	List<SurgeryPkgVo> listFirstExpire(SurgeryPkgVo queryInfo);

	List<SurgeryPkgVo> listSec(SurgeryPkgVo queryInfo);

	/**
	 * 获取退还消毒过期的手术包列表
	 * @param queryInfo
	 * @return
	 */
	List<SurgeryPkgVo> listSecExpire(SurgeryPkgVo queryInfo);

	List<SurgeryPkgListVo> getNotFirstPkgListBySurCode(@Param("surCode") String surCode);

	List<SurgeryPkgListVo> getNotSecPkgListBySurCode(@Param("surCode") String surCode);

	int getCountNotSecPkgListBySurCode(@Param("surCode") String surCode);

	int updateFirstSterialization(List<SurgeryPkgListVo> list);

	int updateSecSterialization(List<SurgeryPkgListVo> list);



	int updatePkgStatus(SurgeryPkgVo surgeryPkgVo);
}

