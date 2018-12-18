package gyqx.spdherp.productsInfos.dao.mapper;

import java.util.List;

import gyqx.spdherp.productsInfos.vo.OperatingPackageGoodsInfoVo;
import gyqx.spdherp.productsInfos.vo.OperatingPackageInfoVo;
import gyqx.spdherp.productsInfos.vo.PackGoodsInfoVo;

public interface OperationPackMapper {

	/**
	 * 查询手术包信息
	 * @param operatingPackageInfoVo
	 * @return
	 */
	List<OperatingPackageInfoVo> queryPackInfo(OperatingPackageInfoVo operatingPackageInfoVo);

	/**
	 * 添加手术包
	 * @param operatingPackageInfoVo
	 */
	void insertPackInfo(OperatingPackageInfoVo operatingPackageInfoVo);

	/**
	 * 查询手术包产品信息
	 * @param operatingPackageGoodsInfoVo
	 * @return
	 */
	List<PackGoodsInfoVo> getPackGoodsInfo(OperatingPackageGoodsInfoVo operatingPackageGoodsInfoVo);

}
