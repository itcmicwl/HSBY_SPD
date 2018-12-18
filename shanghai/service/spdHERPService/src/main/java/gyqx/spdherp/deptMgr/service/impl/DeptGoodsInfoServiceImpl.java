package gyqx.spdherp.deptMgr.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import gyqx.spdherp.deptMgr.vo.*;
import org.springframework.stereotype.Service;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import gyqx.spdhdi.myInfos.dao.ProvHosDeptInfoCodeDao;
import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdherp.deptMgr.dao.DeptGoodsInfoMgrDao;
import gyqx.spdherp.deptMgr.service.IDeptGoodsInfoService;
import gyqx.spdherp.po.DeptGoodsInfo;

@Service
public class DeptGoodsInfoServiceImpl implements IDeptGoodsInfoService {

	@Resource
	SimpleDao simpleDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private DeptGoodsInfoMgrDao dao;
	@Resource
	private ProvHosDeptInfoCodeDao provHosDeptInfoCodeDao;
	
	@Override
	public QueryResult<DeptGoodsInfoVo> deptGoodsInfoList(QueryInfo<DeptGoodsInfoVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.deptGoodsInfoList(queryInfo.getQueryObject()));
	}
	
	@Override
	public QueryResult<HosGoodsInfo> deptGoodsInfoImportQuery(QueryInfo<DeptGoodsInfoVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.deptGoodsInfoImportQuery(queryInfo.getQueryObject()));
	}
	
//	@Override
//	public QueryResult<HosGoodsInfo> deptGoodsInfoImportQuery(QueryInfo<DeptGoodsInfoVo> queryInfo) throws Exception {
//		PageUtils.startPage(queryInfo);
//		String sql = "SELECT * FROM hos_goods_info g WHERE g.flag = 1 AND g.hos_id = ? AND NOT EXISTS "
//				+ "(SELECT * FROM dept_goods_info d WHERE d.hos_id = ? AND d.dept_id = ? and d.goods_id = g.id)";
//		DeptGoodsInfoVo obj = queryInfo.getQueryObject();
//		List<HosGoodsInfo> queryResult = null;
//		if(StringUtils.isNotEmpty(obj.getGoodsName())){
//			sql += " AND g.goods_name LIKE '%"+obj.getGoodsName()+"%'";
//			queryResult = simpleDao.queryForList(sql, HosGoodsInfo.class, obj.getHosId(),obj.getHosId(),obj.getDeptId());
//		}else{
//			queryResult = simpleDao.queryForList(sql, HosGoodsInfo.class, obj.getHosId(),obj.getHosId(),obj.getDeptId());
//		}
//		return  PageUtils.endPage(queryResult);
//	}

	@Override
	public DeptGoodsInfo deptGoodsInfoEdit(DeptGoodsInfo deptGoodsInfo) throws Exception {
		simpleDao.update(deptGoodsInfo);
		return null;
	}

	@Override
	public DeptGoodsInfo deptGoodsInfoDelete(DeptGoodsInfo deptGoodsInfo) throws Exception {
		simpleDao.delete(deptGoodsInfo);
		return null;
	}

	@Override
	public List<DeptGoodsInfo> deptGoodsInfoMulDelete(List<DeptGoodsInfo> deptGoodsInfos) throws Exception {
		for(DeptGoodsInfo d : deptGoodsInfos){
			simpleDao.delete(d);
		}
		return null;
	}

	@Override
	public List<DeptGoodsInfo> deptGoodsInfoImport(DeptGoodsInfoImportVo vo) throws Exception {
		List<DeptGoodsInfo> deptGoodsInfos = new ArrayList<DeptGoodsInfo>();
		List<HosGoodsInfo> hosGoodsInfos = vo.getHosGoodsInfos();
		for(HosGoodsInfo h:hosGoodsInfos){
			DeptGoodsInfo bean = new DeptGoodsInfo();
			bean.setId(atomUtil.newValue("dept-goods-info-id"));
			bean.setHosId(vo.getHosId());
			bean.setHosName(vo.getHosId());
			bean.setDeptId(vo.getDeptId());
			bean.setDeptName(vo.getDeptName());
			//科室产品采购模式 若医院没有指定采购模式，则默认为 10 实物采购，否则采用医院产品的采购模式
			bean.setPurMode(h.getPurMode());
			//bean.setPurMode("10");// 科室产品导入，默认采购模式为 '10:实物采购'
			bean.setIsPacket("0");// 是否定数包管理：默认为0：否
			
			bean.setGoodsId(h.getId());
			bean.setGoodsName(h.getGoodsName());
			bean.setGoodsGg(h.getGoodsGg());
			bean.setMade(h.getMade());
			bean.setMfrsId(h.getMfrsId());
			bean.setMfrsName(h.getHosMfrsName());
			bean.setUnit(h.getUnit());
			bean.setPacketCode(h.getPackeage());
			bean.setBrand(h.getBrand());

            // 医保、最小请购数量
            bean.setMiCode(h.getMiCode());
            bean.setReqNum(new BigDecimal(1.00));

			DeptGoodsInfo deptGoodsInfo = simpleDao.insertAndGet(bean);
			deptGoodsInfos.add(deptGoodsInfo);
		}
		return deptGoodsInfos;
	}
	public QueryResult<DeptGoodsList4InStockVo> getDeptGoodsList4InStock(QueryInfo<DeptGoodsList4InStockVo> queryInfo) throws Exception{
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.getDeptGoodsList4InStock(queryInfo.getQueryObject()));
	}

	@Override
	public QueryResult<SaleManVo> getSaleManList(QueryInfo<SaleManVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.getSaleManList(queryInfo));
	}

	@Override
	public DeptGoodsReplaceVo getGoodsByErpCode(String hosId, String erpCode) {
		return dao.getGoodsByErpCode(hosId,erpCode);
	}


}
