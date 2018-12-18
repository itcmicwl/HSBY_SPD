package gyqx.spdherp.productsInfos.service;

import java.util.List;

import gyqx.spdherp.po.OperatingPackageKindcode;

/**
 * 手术包类型
 * 
 * @date 2017年10月26日 上午10:12:18
 */
public interface OperatingPackageKindcodeService {

	/**
	 * 根据id获取手术包类型
	 * @param id
	 * @return
	 * @throws Exception
	 */
	OperatingPackageKindcode get(String id) throws Exception;

	/**
	 * 添加手术包类型
	 * @param operatingPackageKindcode
	 * @return
	 * @throws Exception
	 */
	OperatingPackageKindcode insertKind(OperatingPackageKindcode operatingPackageKindcode) throws Exception;

	/**
	 * 更新
	 * @param operatingPackageKindcode
	 * @return
	 * @throws Exception
	 */
	OperatingPackageKindcode update(OperatingPackageKindcode operatingPackageKindcode) throws Exception;

	/**
	 * 删除
	 * @param operatingPackageKindcode
	 * @throws Exception
	 */
	void delete(OperatingPackageKindcode operatingPackageKindcode) throws Exception;

	/**
	 * 根据id获取节点信息
	 * @param hosId
	 * @param id
	 * @return
	 * @throws Exception
	 */
	OperatingPackageKindcode getOperatingPackageKindcodeById(String hosId, String id) throws Exception;

	/**
	 * 根据父节点id获取所有子节点
	 * @param hosId
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	List<OperatingPackageKindcode> getOperatingPackageKindcodeByPid(String hosId, String pid) throws Exception;
}
