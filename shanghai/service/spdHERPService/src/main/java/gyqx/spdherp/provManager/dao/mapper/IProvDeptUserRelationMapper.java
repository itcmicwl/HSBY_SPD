package gyqx.spdherp.provManager.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gyqx.spdherp.provManager.vo.ProvDeptUserVo;
import gyqx.spdherp.provManager.vo.ProvHosRelationVo;
import gyqx.spdherp.provManager.vo.ProvUserRelationVo;

public interface IProvDeptUserRelationMapper {
	
	/**
	 * 批量删除供应商科室人员关联关系
	 * @param hosId 医院id
	 * @param userId 人员id
	 * @param provIds 供应商id
	 */
	void deleteProvDeptUserRelation(@Param("hosId") String hosId, @Param("userId") String userId,
			@Param("provIds") List<String> provIds);
	

	/**
	 * 查询医院科室人员信息
	 * @param provId 供应商id
	 * @param hosId 医院id
	 * @param deptId 科室id
	 * @param ename 用戶
	 * @return
	 */
	List<ProvDeptUserVo> getUserList(@Param("provId") String provId, @Param("hosId") String hosId,
			@Param("deptId") String deptId,@Param("ename") String ename);

	/**
	 * 查询供应商医院科室人员关联数据信息
	 * @param provId 供应商id
	 * @param hosId 医院id
	 * @param deptId 科室id
	 * @return
	 */
	List<ProvDeptUserVo> getCheckRelUserList(@Param("provId") String provId, @Param("hosId") String hosId, @Param("deptId") String deptId);
	
	/**
	 * 根据用户id查询关联供应商信息
	 * @param hosId 医院id
	 * @param userId 用户id
	 * @param provName 供应商名称
	 * @return
	 */
	List<ProvUserRelationVo> getUserProvRelationList(@Param("hosId") String hosId, @Param("userId") String userId, @Param("provName") String provName);

	/**
	 * 根据用户id查询关联供应商信息
	 * @param hosId 医院id
	 * @param userId 用户id
	 * @param provName 供应商名称
	 * @return
	 */
	List<ProvHosRelationVo> getUserHosProvRelationList(@Param("hosId") String hosId, @Param("userId") String userId, @Param("provName") String provName);

}
