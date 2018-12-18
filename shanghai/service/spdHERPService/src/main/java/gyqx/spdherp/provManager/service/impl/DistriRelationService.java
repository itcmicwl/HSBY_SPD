package gyqx.spdherp.provManager.service.impl;

import java.util.List;

import javax.annotation.Resource;

import gyqx.platform.po.SysOrg;
import gyqx.spdherp.provManager.vo.*;
import org.springframework.stereotype.Service;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdherp.po.HosCollector;
import gyqx.spdherp.po.HosCollectorProv;
import gyqx.spdherp.po.ProvProvGoods;
import gyqx.spdherp.provManager.dao.DistriRelationDao;
import gyqx.spdherp.provManager.service.IDistriRelationService;

@Service
public class DistriRelationService implements IDistriRelationService {
	@Resource
	DistriRelationDao dao;
	@Resource
	SimpleDao simpleDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	@Resource
	private SysConfigUtil configUtil;
	@Override
	public QueryResult<DistriRelationVo> queryCollectorList(QueryInfo<DistriRelationVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.queryCollectorList(queryInfo.getQueryObject()));
	}
	
	@Override
	public QueryResult<DistriRelationVo> queryUserRelationCollectorList(QueryInfo<DistriRelationVo> queryInfo) throws Exception {
		//获取当前登陆人,根据人员与供应商关系查询供应商信息
		String userId = userOnlineStateUtils.getCurrent().getUserId();
		PageUtils.startPage(queryInfo);
		queryInfo.getQueryObject().setUserId(userId);
		return PageUtils.endPage(dao.queryUserRelationCollectorList(queryInfo.getQueryObject()));
	}

	@Override
	public QueryResult<DistriRelationVo> queryPartnerList(QueryInfo<PartnerParam> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.queryPartnerList(queryInfo.getQueryObject()));
	}
	
	@Override
	public QueryResult<DistriRelationVo> queryUserRelationPartnerList(QueryInfo<PartnerParam> queryInfo) throws Exception {
		//获取当前登陆人,根据人员与供应商关系查询供应商信息
		String userId = userOnlineStateUtils.getCurrent().getUserId();
		PageUtils.startPage(queryInfo);
		queryInfo.getQueryObject().setUserId(userId);
		return PageUtils.endPage(dao.queryPartnerList(queryInfo.getQueryObject()));
	}

	@Override
	public List<HosCollector> importCollectorList(List<ProvHosInfoVo> provHosInfoVos) throws Exception {
		for (ProvHosInfoVo p : provHosInfoVos) {
			HosCollector bean = new HosCollector();
			bean.setId(configUtil.getValue("sys.name")+"|"+atomUtil.newValue("hos_collector_id"));
			bean.setHosId(p.getHosId());
			bean.setProvId(p.getProvId());
			simpleDao.insert(bean);
		}
		
		return null;
	}

	@Override
	public List<HosCollectorProv> importPartnerList(PartnerImortParam param) throws Exception {
		String collectorId = param.getCollectorId();
		List<ProvHosInfoVo> provHosInfoVos = param.getProvHosInfoVos();
		for (ProvHosInfoVo p : provHosInfoVos) {
			HosCollectorProv bean = new HosCollectorProv();
			bean.setId(configUtil.getValue("sys.name")+"|"+atomUtil.newValue("hos_collector_prov_id"));
			bean.setHosId(p.getHosId());   
			bean.setProvId(p.getProvId());
			bean.setCollectorId(collectorId);
//			bean.setIsUseSubprov("0");
			bean.setIsUseSubprov(0);
			simpleDao.insert(bean);
		}
		return null;
	}

	@Override
	public List<HosCollector> mulDeleteCollectorList(List<DistriRelationVo> distriRelationVos) throws Exception {
		for (DistriRelationVo d : distriRelationVos) {
			HosCollector bean = new HosCollector();
			bean.setId(d.getCid());
			simpleDao.delete(bean);
		}
		return null;
	}

	@Override
	public List<HosCollector> mulDeletePartnerList(List<DistriRelationVo> distriRelationVos) throws Exception {
		for (DistriRelationVo d : distriRelationVos) {
			HosCollectorProv bean = new HosCollectorProv();
			//bean.setId(d.getCid());
			//还得删除prov_prov_goods 的数据 by 甘威   ：通过cid 查询hos_collector_prov 表 获取collector_id  hos_id  prov_id  再依据这三个条件 删除

					bean =(HosCollectorProv)simpleDao.getByFieldName(HosCollectorProv.class, "id", d.getCid());
				int num	= simpleDao.deleteByWhere("ProvProvGoods", "hosId,collectorId,provId",bean.getHosId(),bean.getCollectorId(),bean.getProvId());
			    simpleDao.delete(bean);
		
	
		}
		
		
		return null;
	}

	@Override
	public List<HosCollectorProvVo> getHosColProvs(HosCollectorProvVo drvo) throws Exception {
		return dao.getHosColProvs(drvo);
	}
	@Override
	public List<SysOrg> getCollectorList(String hosId) throws Exception {
		return dao.getCollectorList(hosId);
	}


	@Override
	public QueryResult<ProvProvGoodsVo> queryGoodsList(QueryInfo<ProvProvGoodsVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.queryGoodsList(queryInfo.getQueryObject()));
	}

	@Override
	public List<ProvProvGoodsVo> provProvGoodsSave(ProvProvGoodsVo vo) throws Exception {
		if (vo != null) {
			simpleDao.executeSql("DELETE FROM prov_prov_goods WHERE hos_id = ? AND collector_id = ? AND prov_id = ?",
					vo.getHosId(), vo.getCollectorId(), vo.getProvId());
		}
		if (vo.getProvGoodsIds() != null) {
			for (String goodsId : vo.getProvGoodsIds()) {
				ProvProvGoods bean = new ProvProvGoods();
				bean.setId(configUtil.getValue("sys.name")+"|"+atomUtil.newValue("pp_goods_id"));
				bean.setHosId(vo.getHosId());
				bean.setCollectorId(vo.getCollectorId());
				bean.setProvId(vo.getProvId());
				bean.setProvGoodsId(goodsId);
				simpleDao.insert(bean);
			}
		}
		return null;
	}

	@Override
	public DistriRelationVo updatePartnerKind(DistriRelationVo param) throws Exception {
		String sql = "UPDATE hos_collector_prov SET kind = ? WHERE id = ?";
		simpleDao.executeSql(sql, param.getKind(), param.getCid());
		return null;
	}
	
	@Override
	public DistriRelationVo updateSubprov(DistriRelationVo param) throws Exception {
		String sql = "UPDATE hos_collector_prov SET is_use_subprov = ? WHERE id = ?";
		simpleDao.executeSql(sql, param.getIsUseSubprov(), param.getCid());
		return null;
	}

	@Override
	public List<ProvProvGoodsVo> getProvProvGoodsId(ProvProvGoodsVo vo) {
		return dao.getProvProvGoodsId(vo);
	}

	@Override
	public String getProvByColHosAndGoods(ProvProvGoodsVo ppgv) throws Exception {
		return dao.getProvByColHosAndGoods(ppgv);
	}
}
