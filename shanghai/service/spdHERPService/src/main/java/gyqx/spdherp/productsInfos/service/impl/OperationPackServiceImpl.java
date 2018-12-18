package gyqx.spdherp.productsInfos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdherp.po.OperatingPackageGoodsInfo;
import gyqx.spdherp.productsInfos.dao.OperationPackDao;
import gyqx.spdherp.productsInfos.service.OperationPackService;
import gyqx.spdherp.productsInfos.vo.OperatingPackageGoodsInfoVo;
import gyqx.spdherp.productsInfos.vo.OperatingPackageInfoVo;
import gyqx.spdherp.productsInfos.vo.PackGoodsInfoVo;

@Service
public class OperationPackServiceImpl implements OperationPackService{
	
	@Resource
	private OperationPackDao operationPackDao;
	@Resource
	private SimpleDao dao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;

	@Override
	public List<OperatingPackageInfoVo> queryPackInfo(OperatingPackageInfoVo operatingPackageInfoVo) {
		return operationPackDao.queryPackInfo(operatingPackageInfoVo);
	}

	@Override
	public OperatingPackageInfoVo insertPackInfo(OperatingPackageInfoVo operatingPackageInfoVo) {
		operatingPackageInfoVo.setId(atomUtil.newValue("operating_package_info_id"));
		if(operatingPackageInfoVo.getHosId() == null || "".equals(operatingPackageInfoVo.getHosId())){
			operatingPackageInfoVo.setHosId(userOnlineStateUtils.getCurrent().getCorpId());
		}
		operationPackDao.insertPackInfo(operatingPackageInfoVo);
		return operatingPackageInfoVo;
	}

	@Override
	public void deletePackInfo(OperatingPackageInfoVo operatingPackageInfoVo) {
		operationPackDao.deletePackInfo(operatingPackageInfoVo);
	}

	@Override
	public QueryResult<PackGoodsInfoVo> getPackGoodsInfo(QueryInfo<OperatingPackageGoodsInfoVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(operationPackDao.getPackGoodsInfo(queryInfo.getQueryObject()));
	}

	@Override
	public void deleteGood(OperatingPackageGoodsInfo bean) throws Exception {
		dao.delete(bean);
	}

	@Override
	public void mulDelete(List<PackGoodsInfoVo> packGoods) throws Exception {
		for (PackGoodsInfoVo vo : packGoods) {
			OperatingPackageGoodsInfo info = new OperatingPackageGoodsInfo();
			info.setId(vo.getId());
			dao.delete(info);
		}
	}

	@Override
	public void importHosGoods(OperatingPackageGoodsInfoVo goodsInfos) throws Exception {
		List<String> goodsIds = goodsInfos.getGoodsIds();
		for (String goodsId : goodsIds) {
			OperatingPackageGoodsInfo bean = new OperatingPackageGoodsInfo();
			bean.setId(atomUtil.newValue("operating_package_goods_info_id"));
			bean.setTempId(goodsInfos.getTempId());
			bean.setGoodsId(goodsId);
			dao.insert(bean);
		}
	}

}
