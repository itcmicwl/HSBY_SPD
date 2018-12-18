package gyqx.spdherp.productsInfos.service;

import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.OperatingPackageGoodsInfo;
import gyqx.spdherp.productsInfos.vo.OperatingPackageGoodsInfoVo;
import gyqx.spdherp.productsInfos.vo.OperatingPackageInfoVo;
import gyqx.spdherp.productsInfos.vo.PackGoodsInfoVo;

/**
 * 手术包维护
 * 
 * @date 2017年10月26日 上午10:12:34
 */
public interface OperationPackService {

	/**
	 * 查询手术包信息
	 * @param operatingPackageInfoVo
	 * @return
	 */
	List<OperatingPackageInfoVo> queryPackInfo(OperatingPackageInfoVo operatingPackageInfoVo);
	
	/**
	 * 添加手术包
	 * @param operatingPackageInfoVo
	 * @return
	 */
	OperatingPackageInfoVo insertPackInfo(OperatingPackageInfoVo operatingPackageInfoVo);
	
	/**
	 * 删除手术包
	 * @param operatingPackageInfoVo
	 */
	void deletePackInfo(OperatingPackageInfoVo operatingPackageInfoVo);
	
	/**
	 * 分页查询手术包产品信息
	 * @param queryInfo
	 * @return
	 */
	QueryResult<PackGoodsInfoVo> getPackGoodsInfo(QueryInfo<OperatingPackageGoodsInfoVo> queryInfo);

	/**
	 * 移除手术包产品
	 * @param bean
	 * @throws Exception 
	 */
	void deleteGood(OperatingPackageGoodsInfo bean) throws Exception;
	
	/**
	 * 批量删除手术包产品
	 * @param packGoods
	 * @throws Exception 
	 */
	void mulDelete(List<PackGoodsInfoVo> packGoods) throws Exception;
	
	/**
	 * 批量导入医院产品信息
	 * @param goodsInfos
	 * @throws Exception 
	 */
	void importHosGoods(OperatingPackageGoodsInfoVo goodsInfos) throws Exception;

}
