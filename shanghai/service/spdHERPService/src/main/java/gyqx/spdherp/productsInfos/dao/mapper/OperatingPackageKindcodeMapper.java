package gyqx.spdherp.productsInfos.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gyqx.spdherp.po.OperatingPackageKindcode;

/**
 * 手术包类型
 * 
 * @date 2017年10月26日 上午9:56:07
 */
public interface OperatingPackageKindcodeMapper {
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	int update(OperatingPackageKindcode bean);
	
	/**
	 * 新增
	 * @param bean
	 * @return
	 */
	int insert(OperatingPackageKindcode bean);

	/**
	 * 根据id查询节点信息
	 * @param id
	 * @return
	 */
	OperatingPackageKindcode getOperatingPackageKindcodeById(@Param("hosId") String hosId, @Param("id") String id);

	/**
	 * 根据父节点获取子节点
	 * @param pid
	 * @return
	 */
	List<OperatingPackageKindcode> getOperatingPackageKindcodeByPid(@Param("hosId") String hosId, @Param("pid") String pid);
}
