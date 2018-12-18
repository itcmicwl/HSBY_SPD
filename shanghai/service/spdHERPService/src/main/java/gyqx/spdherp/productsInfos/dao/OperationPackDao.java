package gyqx.spdherp.productsInfos.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import common.db.SimpleDao;
import gyqx.spdherp.productsInfos.dao.mapper.OperationPackMapper;
import gyqx.spdherp.productsInfos.vo.OperatingPackageGoodsInfoVo;
import gyqx.spdherp.productsInfos.vo.OperatingPackageInfoVo;
import gyqx.spdherp.productsInfos.vo.PackGoodsInfoVo;

@Repository
public class OperationPackDao {
	
	@Resource 
	private OperationPackMapper mapper;
	@Resource
	private SimpleDao dao;

	public List<OperatingPackageInfoVo> queryPackInfo(OperatingPackageInfoVo operatingPackageInfoVo) {
		return mapper.queryPackInfo(operatingPackageInfoVo);
	}

	public void insertPackInfo(OperatingPackageInfoVo operatingPackageInfoVo) {
		mapper.insertPackInfo(operatingPackageInfoVo);
	}

	public List<PackGoodsInfoVo> getPackGoodsInfo(OperatingPackageGoodsInfoVo operatingPackageGoodsInfoVo) {
		return mapper.getPackGoodsInfo(operatingPackageGoodsInfoVo);
	}

	public void deletePackInfo(OperatingPackageInfoVo operatingPackageInfoVo) {
		dao.executeSql("delete from operating_package_info where id = ?", operatingPackageInfoVo.getId());
		// 查询
		dao.executeSql("delete from operating_package_goods_info where temp_id = ?", operatingPackageInfoVo.getId());
	}

}
