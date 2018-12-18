package gyqx.spdhdi.orderMgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import gyqx.spdhdi.orderMgr.dao.PurchaseStatisticsDao;
import gyqx.spdhdi.orderMgr.service.IPurchaseStatisticsService;
import gyqx.spdhdi.orderMgr.vo.DeptApplyGoodsAggregateVo;
import gyqx.spdhdi.orderMgr.vo.DeptApplyGoodsUnderAvgVo;
import gyqx.spdhdi.orderMgr.vo.DeptApplyGoodsUpperAnalysisVo;
import gyqx.spdhdi.orderMgr.vo.HosMonthHighValueConsumeVo;
import gyqx.spdhdi.orderMgr.vo.HosMonthInStockBillQueryVo;
import gyqx.spdhdi.orderMgr.vo.HosMonthLowValueConsumeVo;
import gyqx.spdhdi.orderMgr.vo.HosOutStockGoodsQueryVo;
import gyqx.spdhdi.orderMgr.vo.PurchaseStatisticsVo;

@Service
public class PurchaseStatisticsService implements IPurchaseStatisticsService {
	@Resource
	private SimpleDao simpleDao;
	@Resource
	private PurchaseStatisticsDao purchaseStatisticsDao;
	@Override
	public QueryResult<PurchaseStatisticsVo> getPurchaseAmountForMonth(QueryInfo<PurchaseStatisticsVo> queryInfo)
			throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(purchaseStatisticsDao.getPurchaseAmountForMonth(queryInfo.getQueryObject()));
	}
	
	/**
	 * 可是请购数量分析
	 */
	public QueryResult<DeptApplyGoodsUpperAnalysisVo> listByPage(QueryInfo<DeptApplyGoodsUpperAnalysisVo> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(purchaseStatisticsDao.list(queryInfo.getQueryObject()));
	}

	@Override
	public QueryResult<DeptApplyGoodsAggregateVo> getDeptApplyGoodsAggregate(
			QueryInfo<DeptApplyGoodsAggregateVo> queryInfo) {
		DeptApplyGoodsAggregateVo obj = queryInfo.getQueryObject();
		StringBuffer sqlSb = new StringBuffer()
		.append("SELECT  ")
		.append("   hosGoodsInfo.goods_name,")
		.append("   hosGoodsInfo.goods_gg,")
		.append("   hosGoodsInfo.unit,")
		.append("   deptApplyGoodsAnalyses.goods_id,")
		.append("   deptApplyGoodsAnalyses.QtyAggregate ")
		.append("FROM (SELECT goods_id,SUM(qty)  AS QtyAggregate ")
		.append("   FROM (SELECT ")
		.append("            subBills.id, ")
		.append("            subBills.bill_id, ")
		.append("            subBills.goods_id, ")
		.append("            IFNULL(subBills.qty, 0)   AS qty, ")
		.append("            subBills.buy_dept_id, ")
		.append("            subBills.hos_id, ")
		.append("            subBills.send_type, ")
		.append("            subBills.sub_state, ")
		.append("            subBills.warehouse_view, ")
		.append("            subBills.warehouse_deal_time, ")
		.append("            subBills.warehouse_reject_type, ")
		.append("            subBills.warehouse_deal_man, ")
		.append("            subBills.row_number, ")
		.append("            subBills.wh_qty, ")
		.append("            subBills.send_qty, ")
		.append("            subBills.unique_kind ")
		.append("        FROM dept_buy_sub subBills ")
		.append("            LEFT JOIN dept_buy_main mainBill ")
		.append("               ON subBills.bill_id = mainBill.bill_id ")
		.append("        WHERE mainBill.fill_date BETWEEN ? and ?")
		.append("              AND subBills.buy_dept_id = ? ")
		.append("              AND subBills.hos_id = ? ) deptApplyGoods ")
		.append("   GROUP BY goods_id) deptApplyGoodsAnalyses ")
		.append("  LEFT JOIN hos_goods_info hosGoodsInfo ")
		.append("    ON deptApplyGoodsAnalyses.goods_id = hosGoodsInfo.id where 1=1 ");
		if(StringUtil.isNotEmpty(obj.getGoodsName())){
			sqlSb.append(" and hosGoodsInfo.goods_name like concat('%','"+obj.getGoodsName()+"','%') ");
		}
		
		List<DeptApplyGoodsAggregateVo> queryResult = null;
		queryResult = simpleDao.queryForList(sqlSb.toString(), DeptApplyGoodsAggregateVo.class, 
									obj.getsDate(),obj.geteDate(),obj.getDeptId(),obj.getHosId());
		PageUtils.startPage(queryInfo);
		return  PageUtils.endPage(queryResult);
	}

	
	@Override
	public QueryResult<DeptApplyGoodsUnderAvgVo> getDeptApplyGoodsUnderAvg(
			QueryInfo<DeptApplyGoodsUnderAvgVo> queryInfo) {
		DeptApplyGoodsUnderAvgVo obj = queryInfo.getQueryObject();
		StringBuffer sqlSb = new StringBuffer()
		.append("	SELECT EE.goods_id AS goodsId, EE.weekQtyAvg, hosGoodsInfo.hos_id AS hosId, hosGoodsInfo.goods_gg AS goodsGg, hosGoodsInfo.unit, ")
		.append("  hosGoodsInfo.goods_name AS goodsName, hosGoodsInfo.prov_id AS provId, IFNULL(FF.QtyAggregate, 0) AS curWeekQty ")
		.append("FROM (SELECT BB.goods_id, SUM(BB.QtyAggregate) / COUNT(BB.goods_id) AS weekQtyAvg ")
		.append("FROM (SELECT goods_id, SUM(qty) AS QtyAggregate, weeks ")
		.append("FROM (SELECT subBills.id, subBills.bill_id, subBills.goods_id, IFNULL(subBills.qty, 0) AS qty, subBills.buy_dept_id ")
		.append("		, subBills.hos_id, DATE_FORMAT(mainBill.fill_date, '%Y%u') AS weeks ")
		.append("	FROM dept_buy_sub subBills LEFT JOIN dept_buy_main mainBill ON subBills.bill_id = mainBill.bill_id ")
		.append("	WHERE DATE_FORMAT(mainBill.fill_date, '%Y') = DATE_FORMAT(NOW(), '%Y') ")
		.append("		AND subBills.buy_dept_id = ? ")
		.append("		AND subBills.hos_id = ? ")
		.append("	) deptApplyGoods ")
		.append("		GROUP BY goods_id, weeks ")
		.append("		) BB ")
		.append(" GROUP BY BB.goods_id ")
		.append("	) EE LEFT JOIN hos_goods_info hosGoodsInfo ON EE.goods_id = hosGoodsInfo.id LEFT JOIN (SELECT CC.goods_id, SUM(CC.qty) AS QtyAggregate ")
		.append(" FROM (SELECT subBills.id, subBills.bill_id, subBills.goods_id, IFNULL(subBills.qty, 0) AS qty, subBills.buy_dept_id ")
		.append("	, subBills.hos_id, mainBill.fill_date ")
		.append(" FROM dept_buy_sub subBills LEFT JOIN dept_buy_main mainBill ON subBills.bill_id = mainBill.bill_id ")
		.append(" WHERE YEARWEEK(DATE_FORMAT(mainBill.fill_date, '%Y-%m-%d')) = YEARWEEK(NOW()) ")
		.append("	AND subBills.buy_dept_id = ? ")
		.append("	AND subBills.hos_id = ? ")
		.append(") CC ")
		.append(" GROUP BY CC.goods_id ")
		.append(" ) FF ON EE.goods_id = FF.goods_id WHERE 1=1 ");
		if(StringUtil.isNotEmpty(obj.getGoodsName())){
			sqlSb.append(" and hosGoodsInfo.goods_name like concat('%','"+obj.getGoodsName()+"','%') ");
		}
		
		List<DeptApplyGoodsUnderAvgVo> queryResult = null;
		queryResult = simpleDao.queryForList(sqlSb.toString(), DeptApplyGoodsUnderAvgVo.class, 
				obj.getDeptId(),obj.getHosId(),obj.getDeptId(),obj.getHosId());
        PageUtils.startPage(queryInfo);
		return  PageUtils.endPage(queryResult);
	}

	/**
	 * 医院高值耗材月消耗汇总
	 */
	@Override
	public QueryResult<HosMonthHighValueConsumeVo> getHosMonthHighValueConsume(
			QueryInfo<HosMonthHighValueConsumeVo> queryInfo) {
		
		return null;
	}

	/**
	 * 医院低值耗材月核算
	 */
	@Override
	public QueryResult<HosMonthLowValueConsumeVo> getHosMonthLowValueConsume(
			QueryInfo<HosMonthLowValueConsumeVo> queryInfo) {
		HosMonthLowValueConsumeVo obj = queryInfo.getQueryObject();
		StringBuffer sqlSb = new StringBuffer()
		.append("SELECT t3.deptId,t3.deptName,ifnull(t3.ownExpense,0) as ownExpense,ifnull(t3.financialAppropriation,0) as financialAppropriation,ifnull(t3.donation,0) as donation,ifnull(t3.researchFee,0) researchFee, (ifnull(t3.ownExpense, 0) + ifnull(t3.financialAppropriation,0) + ifnull(t3.donation, 0) +")
		.append(" ifnull(t3.researchFee, 0)) AS total FROM (SELECT DISTINCT t1.out_dept_id AS deptId,t1.out_dept_name AS deptName,")
		.append(" (SELECT sum(price) FROM (SELECT os.out_dept_id,os.out_dept_name,ifnull(db.fund_type, '10') AS fund_type,")
		.append(" oss.bill_id,oss.source_bill_id,oss.goods_id,oss.out_qty * stockpile.price AS price FROM out_stock_list oss")
		.append(" LEFT JOIN dept_buy_sub db ON db.id = oss.source_bill_id LEFT OUTER JOIN out_stock os ON (os.id = oss.pid)")
		.append(" LEFT OUTER JOIN v_stockpile_hz stockpile ON (stockpile.hos_id = os.out_org_id AND stockpile.stoc_id = os.out_stoc_id")
		.append(" AND stockpile.hos_goods_id = oss.goods_id) LEFT OUTER JOIN hos_goods_info hg on hg.id=oss.goods_id WHERE year(os.audit_date)=year(now()) and month(os.audit_date)=? and hg.pur_mode='10') t2")//条件
		.append("	WHERE t1.out_dept_id = t2.out_dept_id AND t2.fund_type = '10' ) AS ownExpense,(SELECT sum(price) FROM")
		.append(" (SELECT os.out_dept_id,os.out_dept_name,ifnull(db.fund_type, '10') AS fund_type,oss.bill_id,oss.source_bill_id,")
		.append(" oss.goods_id,oss.out_qty * stockpile.price AS price FROM out_stock_list oss LEFT JOIN dept_buy_sub db ON db.id = oss.source_bill_id")
		.append(" LEFT OUTER JOIN out_stock os ON (os.id = oss.pid) LEFT OUTER JOIN v_stockpile_hz stockpile ON (stockpile.hos_id = os.out_org_id")
		.append("	AND stockpile.stoc_id = os.out_stoc_id AND stockpile.hos_goods_id = oss.goods_id) LEFT OUTER JOIN hos_goods_info hg on hg.id=oss.goods_id WHERE year(os.audit_date)=year(now()) and month(os.audit_date)=? and hg.pur_mode='10'")//条件
		.append("	) t2 WHERE t1.out_dept_id = t2.out_dept_id AND t2.fund_type = '20') AS financialAppropriation,(SELECT sum(price)")
		.append(" FROM (SELECT os.out_dept_id,os.out_dept_name,ifnull(db.fund_type, '10') AS fund_type,oss.bill_id,oss.source_bill_id,")
		.append(" oss.goods_id,oss.out_qty * stockpile.price AS price FROM out_stock_list oss LEFT JOIN dept_buy_sub db ON db.id = oss.source_bill_id")
		.append(" LEFT OUTER JOIN out_stock os ON (os.id = oss.pid) LEFT OUTER JOIN v_stockpile_hz stockpile ON (stockpile.hos_id = os.out_org_id")
		.append("  AND stockpile.stoc_id = os.out_stoc_id AND stockpile.hos_goods_id = oss.goods_id) LEFT OUTER JOIN hos_goods_info hg on hg.id=oss.goods_id WHERE year(os.audit_date)=year(now()) and month(os.audit_date)=? and hg.pur_mode='10'")//条件
		.append(" ) t2 WHERE t1.out_dept_id = t2.out_dept_id AND t2.fund_type = '30' ) AS donation,(SELECT sum(price) FROM (")
		.append("  SELECT os.out_dept_id,os.out_dept_name,ifnull(db.fund_type, '10') AS fund_type,oss.bill_id,oss.source_bill_id,")
		.append(" oss.goods_id,oss.out_qty * stockpile.price AS price FROM out_stock_list oss LEFT JOIN dept_buy_sub db ON db.id = oss.source_bill_id")
		.append(" LEFT OUTER JOIN out_stock os ON (os.id = oss.pid) LEFT OUTER JOIN v_stockpile_hz stockpile ON (stockpile.hos_id = os.out_org_id")
		.append("  AND stockpile.stoc_id = os.out_stoc_id AND stockpile.hos_goods_id = oss.goods_id) LEFT OUTER JOIN hos_goods_info hg on hg.id=oss.goods_id WHERE year(os.audit_date)=year(now()) and month(os.audit_date)=? and hg.pur_mode='10') t2")//条件
		.append("  WHERE t1.out_dept_id = t2.out_dept_id AND t2.fund_type = '40' ) AS researchFee FROM (SELECT os.out_dept_id,os.out_dept_name,")
		.append("  ifnull(db.fund_type, '10') AS fund_type,oss.bill_id,oss.source_bill_id,oss.goods_id,oss.out_qty,stockpile.price,os.audit_date")
		.append("  FROM out_stock_list oss LEFT JOIN dept_buy_sub db ON db.id = oss.source_bill_id LEFT OUTER JOIN out_stock os ON (os.id = oss.pid)")
		.append("  LEFT OUTER JOIN v_stockpile_hz stockpile ON (stockpile.hos_id = os.out_org_id AND stockpile.stoc_id = os.out_stoc_id")
		.append(" AND stockpile.hos_goods_id = oss.goods_id) LEFT OUTER JOIN hos_goods_info hg on hg.id=oss.goods_id WHERE year(os.audit_date)=year(now()) and month(os.audit_date)=? and hg.pur_mode='10') t1) t3")//条件
		;
		List<HosMonthLowValueConsumeVo> queryResult = null;
		queryResult = simpleDao.queryForList(sqlSb.toString(), HosMonthLowValueConsumeVo.class,obj.getFillMonth()
				,obj.getFillMonth(),obj.getFillMonth(),obj.getFillMonth(),obj.getFillMonth());
        PageUtils.startPage(queryInfo);
		return  PageUtils.endPage(queryResult);
	}

	/**
	 * 医院月高、低值耗材入库单据表查询
	 */
	@Override
	public QueryResult<HosMonthInStockBillQueryVo> getHosMonthInStockBillQuery(
			QueryInfo<HosMonthInStockBillQueryVo> queryInfo) {
		return null;
	}

	/**
	 * 医院产品出库信息查询
	 */
	@Override
	public QueryResult<HosOutStockGoodsQueryVo> getHosOutStockGoodsQuery(QueryInfo<HosOutStockGoodsQueryVo> queryInfo) {
		return null;
	}

}
