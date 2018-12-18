package gyqx.spdherp.stocInfoMgr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import common.web.UserOnlineInfo;
import gyqx.spdherp.stocInfoMgr.vo.*;
import org.springframework.stereotype.Service;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdherp.po.GoodsPurTemplateGoods;
import gyqx.spdherp.po.StocShelfGoodsInfo;
import gyqx.spdherp.po.StockShelf;
import gyqx.spdherp.stocInfoMgr.dao.StocShelfGoodsInfoDao;
import gyqx.spdherp.stocInfoMgr.service.IStocShelfGoodsInfoService;
import org.springframework.util.StringUtils;

@Service
public class StocShelfGoodsInfoServiceImpl implements IStocShelfGoodsInfoService {
	
	@Resource
	private StocShelfGoodsInfoDao dao;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private SimpleDao simpleDao;
	
	@Override
	public List<StockShelfVo> queryStockShelfList(StockShelf paramObj) throws Exception {
		String sql = "select s.*,IFNULL(s.area_code,s.shelf_code) as code FROM stock_shelf s WHERE s.hos_id = ? AND prov_id = ?";
		List<StockShelfVo> list = simpleDao.queryForList(sql, StockShelfVo.class, paramObj.getHosId(),paramObj.getProvId());
		return list;
	}
	
	public QueryResult<QueryResultVo> listByPage(QueryInfo<QueryVo> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}
	
	@Override
	public QueryResult<HosGoodsInfoVo> queryStockShelfGoodsList(QueryInfo<StockShelfGoodsInfoVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.queryStockShelfGoodsList(queryInfo.getQueryObject()));
	}

	@Override
	public List<GoodsPurTemplateGoods> importStockShelfGoods(StockShelfGoodsVo param) throws Exception {
		StockShelfVo vo = param.getStockShelfVo();
		List<QueryResultVo> list = param.getStockShelfGoods();
		for(QueryResultVo o:list){
			StocShelfGoodsInfo bean = new StocShelfGoodsInfo();
			bean.setId(atomUtil.newValue("stoc_shelf_goods_info_id"));
			bean.setAreaCode(vo.getAreaCode());
			bean.setGoodsId(o.getGoodsId());
			bean.setHosId(vo.getHosId());
			bean.setShelfCode(vo.getShelfCode());
			bean.setStocId(vo.getProvId());
			
			simpleDao.insert(bean);
		}
		return null;
	}

	@Override
	public List<HosGoodsInfoVo> deleteStockShelfGoods(List<HosGoodsInfoVo> hosGoodsInfoVos) throws Exception {
		for(HosGoodsInfoVo h:hosGoodsInfoVos){
			StocShelfGoodsInfo bean = new StocShelfGoodsInfo();
			bean.setId(h.getSsgiId());
			simpleDao.delete(bean);
		}
		return null;
	}

	@Override
	public List<GoodsShelfInfoVo> findShelfByGoodsIdList(List<String> goodsIdList, String stocId) {
		String hosId = userOnlineStateUtils.getCurrent().getCorpId();
		return dao.findShelfByGoodsIdList(goodsIdList.stream().filter(StringUtils::hasText).collect(Collectors.toList()), stocId, hosId);
	}

}
