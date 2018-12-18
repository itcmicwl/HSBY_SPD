package gyqx.spdherp.provManager.dao.mapper;

import java.util.List;

import gyqx.spdhdi.myInfos.vo.HosInfoVo;
import gyqx.spdherp.provManager.vo.ProvHosInfoVo;

public interface IHosProvInfoMapper {

	/**
	 * 获取医院供应商列表
	 * @param basMfrsInfo
	 * @return
	 */
	List<ProvHosInfoVo> getHosProvInfoVoList(ProvHosInfoVo basMfrsInfo);
	
	List<HosInfoVo> getHosInfoVoList(HosInfoVo hosInfoVo);

	int insertProvHosInfo(ProvHosInfoVo basMfrsInfo);

	int setStatus(ProvHosInfoVo basMfrsInfo);

	int setProvHosInfo(ProvHosInfoVo basMfrsInfo);

	int deleteByPrimaryKey(String id);

	ProvHosInfoVo getProvHosInfoByid(String id);

	int existProvHosInfo(ProvHosInfoVo basMfrsInfo);

	int updateLogo(String logo);

}