package gyqx.spdherp.reportAnalysis.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import gyqx.spdhdi.orderMgr.Constance;
import gyqx.spdherp.po.CollectMonthKindcode;
import gyqx.spdherp.reportAnalysis.dao.ReportStatisticsDao;
import gyqx.spdherp.reportAnalysis.service.IReportStatisticsService;
import gyqx.spdherp.reportAnalysis.vo.DeptApplyGoodsAggregateVo;
import gyqx.spdherp.reportAnalysis.vo.DeptApplyGoodsUnderAvgVo;
import gyqx.spdherp.reportAnalysis.vo.DeptApplyGoodsUpperAnalysisVo;
import gyqx.spdherp.reportAnalysis.vo.HosKindcodeVo;
import gyqx.spdherp.reportAnalysis.vo.HosMonthGoodsReceiptDetailVo;
import gyqx.spdherp.reportAnalysis.vo.HosMonthGoodsReceiptQueryVo;
import gyqx.spdherp.reportAnalysis.vo.HosMonthHighValueConsumeVo;
import gyqx.spdherp.reportAnalysis.vo.HosMonthInStockBillQueryVo;
import gyqx.spdherp.reportAnalysis.vo.HosMonthLowValueConsumeVo;
import gyqx.spdherp.reportAnalysis.vo.HosOutStockGoodsQueryVo;
import gyqx.spdherp.reportAnalysis.vo.HosSettleDXQueryVo;
import gyqx.spdherp.reportAnalysis.vo.HosSettleDetailDXQueryVo;
import gyqx.spdherp.reportAnalysis.vo.HosSettleDetailZXQueryVo;
import gyqx.spdherp.reportAnalysis.vo.HosSettleMsgQueryVo;
import gyqx.spdherp.reportAnalysis.vo.HosSettleZXQueryVo;
import gyqx.spdherp.reportAnalysis.vo.PurchaseStatisticsVo;
import gyqx.spdherp.reportAnalysis.vo.SysConfigVo;
import gyqx.spdherp.reportAnalysis.vo.UserDeptVo;

@Service
public class ReportStatisticsService implements IReportStatisticsService {
	@Resource
	private SimpleDao simpleDao;
	@Resource
	private ReportStatisticsDao reportStatisticsDao;
	@Override
	public QueryResult<PurchaseStatisticsVo> getPurchaseAmountForMonth(QueryInfo<PurchaseStatisticsVo> queryInfo)
			throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(reportStatisticsDao.getPurchaseAmountForMonth(queryInfo.getQueryObject()));
	}
	
	/**
	 * 科室请购数量分析
	 */
	public QueryResult<DeptApplyGoodsUpperAnalysisVo> listByPage(QueryInfo<DeptApplyGoodsUpperAnalysisVo> queryInfo) throws Exception{		
		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(reportStatisticsDao.list(queryInfo.getQueryObject()));
	}

	/**
	 * 月请购汇总分析
	 */
	public QueryResult<DeptApplyGoodsAggregateVo> getDeptApplyGoodsAggregate(
			QueryInfo<DeptApplyGoodsAggregateVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(reportStatisticsDao.getDeptApplyGoodsAggregate(queryInfo.getQueryObject()));
	}

	
	@Override
	public QueryResult<DeptApplyGoodsUnderAvgVo> getDeptApplyGoodsUnderAvg(
			QueryInfo<DeptApplyGoodsUnderAvgVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(reportStatisticsDao.getDeptApplyGoodsUnderAvg(queryInfo.getQueryObject()));
		/*DeptApplyGoodsUnderAvgVo obj = queryInfo.getQueryObject();
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
		return  PageUtils.endPage(queryResult);*/
	}

	/**
	 * 医院高值耗材月消耗汇总
	 */
	@Override
	public QueryResult<HosMonthHighValueConsumeVo> getHosMonthHighValueConsume(
			QueryInfo<HosMonthHighValueConsumeVo> queryInfo) {
		// 日期查询处理
		queryInfo.getQueryObject().setMonth(
				new SimpleDateFormat("yyyy").format(new Date()) + queryInfo.getQueryObject().getMonth());
		//设置上一月份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(queryInfo.getQueryObject().getMonth() + "01");
		} catch (ParseException e) {
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		String prevMonth = c.get(Calendar.YEAR) + "" + (c.get(Calendar.MONTH) + 1);
		queryInfo.getQueryObject().setPrevMonth(prevMonth);
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(reportStatisticsDao.getHosMonthHighValueConsume(queryInfo.getQueryObject()));
	}

	/**
	 * 医院低值耗材月核算
	 */
	@Override
	public QueryResult<HosMonthLowValueConsumeVo> getHosMonthLowValueConsume(
			QueryInfo<HosMonthLowValueConsumeVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(reportStatisticsDao.getHosMonthLowValueConsume(queryInfo.getQueryObject()));
	}

	/**
	 * 医院月高、低值耗材入库单据表查询
	 */
	@Override
	public QueryResult<HosMonthInStockBillQueryVo> getHosMonthInStockBillQuery(
			QueryInfo<HosMonthInStockBillQueryVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		// 日期查询处理
		queryInfo.getQueryObject().setAccountingPeriod(
				new SimpleDateFormat("yyyy").format(new Date()) + queryInfo.getQueryObject().getAccountingPeriod());
		String instockType = queryInfo.getQueryObject().getInstockType();
		if (StringUtils.isNotBlank(instockType)) {
			if (StringUtils.equals(instockType, Constance.IN_STOCK_TYPE_VIRTUAL_KEY)) {
				queryInfo.getQueryObject().setInstockType(Constance.IN_STOCK_TYPE_VIRTUAL_KEY);
			} else {
				queryInfo.getQueryObject().setInstockType(Constance.IN_STOCK_TYPE_MATERIAL_KEY);
			}
		}
		List<HosMonthInStockBillQueryVo> list = reportStatisticsDao
				.getHosMonthInStockBillQuery(queryInfo.getQueryObject());
		if (list != null && !list.isEmpty()) {
			for (HosMonthInStockBillQueryVo hosMonthInStockBillQueryVo : list) {
				if (StringUtils.equals(Constance.IN_STOCK_TYPE_VIRTUAL_KEY,
						hosMonthInStockBillQueryVo.getInstockType())) {
					hosMonthInStockBillQueryVo.setHighOrLower(Constance.IN_STOCK_TYPE_VIRTUAL_NAME);
				} else {
					hosMonthInStockBillQueryVo.setHighOrLower(Constance.IN_STOCK_TYPE_MATERIAL_NAME);
				}
				if (StringUtils.isBlank(hosMonthInStockBillQueryVo.getFundType())) {
					hosMonthInStockBillQueryVo.setFundType(Constance.FUND_TYPE_DEFAULT_VALUE);
				}
				hosMonthInStockBillQueryVo.setAccountingPeriod(
						new SimpleDateFormat("yyyyMM").format(hosMonthInStockBillQueryVo.getAuditDate()));
			}
		}
		return PageUtils.endPage(list);
	}

	/**
	 * 医院产品出库信息查询
	 */
	@Override
	public QueryResult<HosOutStockGoodsQueryVo> getHosOutStockGoodsQuery(QueryInfo<HosOutStockGoodsQueryVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		/*int pageSize = queryInfo.getRows()<1?10:queryInfo.getRows();
		int pageNo = queryInfo.getPage()<1?1:queryInfo.getPage();
		HosOutStockGoodsQueryVo obj = queryInfo.getQueryObject();
		StringBuffer sqlSb = new StringBuffer()
		.append("SELECT  ")
		.append("	oss.bill_id AS billId, ")
		.append("	oss.goods_id AS goodsId, ")
		.append("	oss.goods_name AS goodsName, ")
		.append("	oss.goods_gg AS goodsGg, ")
		.append("	oss.unit, ")
		.append("	oss.out_qty AS outQty, ")
		.append("	stockpile.price, ")
		.append("   oss.out_qty*stockpile.price AS outAmount, ")
		.append("   os.in_dept_id AS inDeptId, ")
		.append("   os.in_dept_name AS inDeptName, ")
		.append("   os.out_dept_id AS outDeptId, ")
		.append("   os.out_dept_name AS outDeptName, ")
		.append("   os.fill_date as fillDate, ")
		.append("   os.audit_date as auditDate, ")
		.append("   os.`status`, ")
		.append("   os.out_stock_kind AS outStockKind, ")
		.append("   os.out_stock_type AS outStockkType ")
		.append("FROM ")
		.append("	out_stock_list oss ")
		.append("LEFT OUTER JOIN out_stock os ON (os.id = oss.pid) ")
		.append("LEFT OUTER JOIN v_stockpile_hz stockpile ON ( ")
		.append("	stockpile.hos_id = os.out_org_id ")
		.append("	AND stockpile.stoc_id = os.out_stoc_id ")
		.append("	AND stockpile.hos_goods_id = oss.goods_id ) ");
		List<HosOutStockGoodsQueryVo> queryResult = simpleDao.queryForList(sqlSb.toString(), HosOutStockGoodsQueryVo.class);
		if(queryResult!=null && queryResult.size()>0){
			return PageUtils.endPage(getSubListForPage(queryResult,pageNo,pageSize));
		}*/
		return  PageUtils.endPage(reportStatisticsDao.getHosOutStockGoodsQuery(queryInfo.getQueryObject()));
	}
	
	private List getSubListForPage(List list,int pageNo,int pageSize){
		int totalCount=list.size();
		int pageCount = 0;
		int m = totalCount%pageSize;
		if (m>0){
			pageCount=totalCount/pageSize+1;
		}else{
			pageCount=totalCount/pageSize;
		}
		List subList=new ArrayList();
		if(m == 0){
		   subList= list.subList((pageNo-1)*pageSize,pageSize*(pageNo));
		}else{
			if (pageNo == pageCount) {
			    subList= list.subList((pageNo-1)*pageSize,totalCount);
		    }else{
			    subList= list.subList((pageNo-1)*pageSize,pageSize*(pageNo));
		    }
		}
		return subList;
	}

/*	@Override
	public void processStatisticsHosMonthHighValueConsume() throws Exception {
		// 通过配置表sys_config获取需要统计的医院信息
		List<SysConfigVo> statisticsonfigList = reportStatisticsDao.getHosStatisticsConfig();
		Map<String, SysConfigVo> map = new HashMap<String, SysConfigVo>();
		// 设置上一月份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat currentSdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		String prevMonthDate;
		String prevMonthStartDate;
		if (c.get(Calendar.MONTH) + 1 < 10) {
			prevMonthDate = c.get(Calendar.YEAR) + "0" + (c.get(Calendar.MONTH) + 1);
			prevMonthStartDate = c.get(Calendar.YEAR) + "-0" + (c.get(Calendar.MONTH) + 1);
		} else {
			prevMonthDate = c.get(Calendar.YEAR) + "" + (c.get(Calendar.MONTH) + 1);
			prevMonthStartDate = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1);
		}
		if (statisticsonfigList != null && !statisticsonfigList.isEmpty()) {
			for (SysConfigVo vo : statisticsonfigList) {
				String hosId = vo.getEname().split("_")[0];
				String[] date = vo.getEvalue().split("_");
				String startDate = prevMonthStartDate + "-" + date[0];
				String endDate = sdf.format(new Date()) + "-" + date[1];
				vo.setHosId(hosId);
				vo.setStartDate(startDate);
				vo.setEndDate(endDate);
				if (StringUtils.equals(currentSdf.format(new Date()), endDate)) {
					map.put(hosId, vo);
				}
			}
			// 进行统计
			if (!map.isEmpty()) {
				String currentYearMonth = shortSdf.format(new Date());
				Date date = new Date();
				for (String hostId : map.keySet()) {
					SysConfigVo vo = map.get(hostId);
					// 获取医院高值耗材月消耗数据
					List<HosMonthHighValueConsumeVo> highList = reportStatisticsDao.getHosMonthHighValueStatistics(
							vo.getHosId(), prevMonthDate, vo.getStartDate(), vo.getEndDate(), "1", "20");
					// 写入数据到数据库
					for (HosMonthHighValueConsumeVo highVo : highList) {
						CollectMonthKindcode collectMonthKindcode = new CollectMonthKindcode();
						collectMonthKindcode.setId(UUID.randomUUID().toString());
						collectMonthKindcode.setHosId(vo.getHosId());
						collectMonthKindcode.setCollectDate(currentYearMonth);
						collectMonthKindcode.setBalanceBegin(highVo.getInitialAmount());
						collectMonthKindcode.setInstocMoney(highVo.getInStocAmount());
						collectMonthKindcode.setOutstocMoney(highVo.getOutStocAmount());
						BigDecimal blanceEnd = highVo.getInStocAmount().subtract(highVo.getOutStocAmount());
						collectMonthKindcode.setBalanceEnd(blanceEnd);
						collectMonthKindcode.setGoodsType("1");
						collectMonthKindcode.setKindId(highVo.getKindId());
						collectMonthKindcode.setLastUpdateDatetime(date);
						simpleDao.insert(collectMonthKindcode);
					}
					// 获取医院低值耗材月消耗数据
					List<HosMonthHighValueConsumeVo> lowList = reportStatisticsDao.getHosMonthHighValueStatistics(
							vo.getHosId(), prevMonthDate, vo.getStartDate(), vo.getEndDate(), "0", "10");
					// 写入数据到数据库
					for (HosMonthHighValueConsumeVo lowVo : lowList) {
						CollectMonthKindcode collectMonthKindcode = new CollectMonthKindcode();
						collectMonthKindcode.setId(UUID.randomUUID().toString());
						collectMonthKindcode.setHosId(vo.getHosId());
						collectMonthKindcode.setCollectDate(currentYearMonth);
						collectMonthKindcode.setBalanceBegin(lowVo.getInitialAmount());
						collectMonthKindcode.setInstocMoney(lowVo.getInStocAmount());
						collectMonthKindcode.setOutstocMoney(lowVo.getOutStocAmount());
						BigDecimal blanceEnd = lowVo.getInStocAmount().subtract(lowVo.getOutStocAmount());
						collectMonthKindcode.setBalanceEnd(blanceEnd);
						collectMonthKindcode.setGoodsType("0");
						collectMonthKindcode.setKindId(lowVo.getKindId());
						collectMonthKindcode.setLastUpdateDatetime(date);
						simpleDao.insert(collectMonthKindcode);
					}
				}
			}
		}
	}*/
	

	/**
	 * 执行医院高低值耗材月消耗统计
	 * 统计各分类下高低值耗材月消耗数据
	 * 分类中会统计包含的子分类数据信息
	 */
	@Override
	public void processStatisticsHosMonthHighValueConsume() throws Exception {
		// 通过配置表sys_config获取需要统计的医院信息
		List<SysConfigVo> statisticsonfigList = reportStatisticsDao.getHosStatisticsConfig();
		Map<String, SysConfigVo> map = new HashMap<String, SysConfigVo>();
		// 设置上一月份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat currentSdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		String prevMonthDate;
		String prevMonthStartDate;
		if (c.get(Calendar.MONTH) + 1 < 10) {
			prevMonthDate = c.get(Calendar.YEAR) + "0" + (c.get(Calendar.MONTH) + 1);
			prevMonthStartDate = c.get(Calendar.YEAR) + "-0" + (c.get(Calendar.MONTH) + 1);
		} else {
			prevMonthDate = c.get(Calendar.YEAR) + "" + (c.get(Calendar.MONTH) + 1);
			prevMonthStartDate = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1);
		}
		if (statisticsonfigList != null && !statisticsonfigList.isEmpty()) {
			for (SysConfigVo vo : statisticsonfigList) {
				String hosId = vo.getEname().split("_")[0];
				String[] date = vo.getEvalue().split("_");
				String startDate = prevMonthStartDate + "-" + date[0];
				String endDate = sdf.format(new Date()) + "-" + date[1];
				vo.setHosId(hosId);
				vo.setStartDate(startDate);
				vo.setEndDate(endDate);
				if (StringUtils.equals(currentSdf.format(new Date()), endDate)) {
					map.put(hosId, vo);
				}
			}
			// 进行统计
			if (!map.isEmpty()) {
				String currentYearMonth = shortSdf.format(new Date());
				Date date = new Date();
				for (Map.Entry<String, SysConfigVo> entrySet : map.entrySet()) {
					String hosId = entrySet.getKey();
					SysConfigVo vo = entrySet.getValue();
					// 查询医院下所有产品分类id信息
					List<HosKindcodeVo> kindcodes = reportStatisticsDao.getHosKindcodeVoByHosId(hosId);
					if (kindcodes != null && !kindcodes.isEmpty()) {
						for (HosKindcodeVo hosKindcodeVo : kindcodes) {
							Set<HosKindcodeVo> kindcodeSet = new HashSet<HosKindcodeVo>();
							List<String> kindIds = new ArrayList<String>();
							findChildHosKindcodes(kindcodeSet, hosKindcodeVo.getId());
							for (HosKindcodeVo hosKindcodeSetVo : kindcodeSet) {
								kindIds.add(hosKindcodeSetVo.getId());
							}
							// 获取医院高值耗材月消耗数据
							List<HosMonthHighValueConsumeVo> highList = reportStatisticsDao
									.getHosMonthHighValueStatisticsByKindId(vo.getHosId(), prevMonthDate,
											vo.getStartDate(), vo.getEndDate(), "1", "20", hosKindcodeVo.getId(),
											kindIds);
							// 写入数据到数据库
							for (HosMonthHighValueConsumeVo highVo : highList) {
								CollectMonthKindcode collectMonthKindcode = new CollectMonthKindcode();
								collectMonthKindcode.setId(UUID.randomUUID().toString());
								collectMonthKindcode.setHosId(vo.getHosId());
								collectMonthKindcode.setCollectDate(currentYearMonth);
								collectMonthKindcode.setBalanceBegin(highVo.getInitialAmount());
								collectMonthKindcode.setInstocMoney(highVo.getInStocAmount());
								collectMonthKindcode.setOutstocMoney(highVo.getOutStocAmount());
								BigDecimal blanceEnd = highVo.getInStocAmount().subtract(highVo.getOutStocAmount());
								collectMonthKindcode.setBalanceEnd(blanceEnd);
								collectMonthKindcode.setGoodsType("1");
								collectMonthKindcode.setKindId(highVo.getKindId());
								collectMonthKindcode.setLastUpdateDatetime(date);
								simpleDao.insert(collectMonthKindcode);
							}
							// 获取医院低值耗材月消耗数据
							List<HosMonthHighValueConsumeVo> lowList = reportStatisticsDao
									.getHosMonthHighValueStatisticsByKindId(vo.getHosId(), prevMonthDate,
											vo.getStartDate(), vo.getEndDate(), "0", "10", hosKindcodeVo.getId(),
											kindIds);
							// 写入数据到数据库
							for (HosMonthHighValueConsumeVo lowVo : lowList) {
								CollectMonthKindcode collectMonthKindcode = new CollectMonthKindcode();
								collectMonthKindcode.setId(UUID.randomUUID().toString());
								collectMonthKindcode.setHosId(vo.getHosId());
								collectMonthKindcode.setCollectDate(currentYearMonth);
								collectMonthKindcode.setBalanceBegin(lowVo.getInitialAmount());
								collectMonthKindcode.setInstocMoney(lowVo.getInStocAmount());
								collectMonthKindcode.setOutstocMoney(lowVo.getOutStocAmount());
								BigDecimal blanceEnd = lowVo.getInStocAmount().subtract(lowVo.getOutStocAmount());
								collectMonthKindcode.setBalanceEnd(blanceEnd);
								collectMonthKindcode.setGoodsType("0");
								collectMonthKindcode.setKindId(lowVo.getKindId());
								collectMonthKindcode.setLastUpdateDatetime(date);
								simpleDao.insert(collectMonthKindcode);
							}
						}
					}
				}
			}
		}
	}
	
    /**
     * 递归查询指定产品分类下的所有子分类节点
     * @param kindcodeSet
     * @param kindId 分类id
     */
    private void findChildHosKindcodes(Set<HosKindcodeVo> kindcodeSet, String kindId) {  
    	HosKindcodeVo hosKindcodeVo=reportStatisticsDao.getHosKindcodeVoById(kindId);
        List<HosKindcodeVo> HosKindcodeVos = new ArrayList<HosKindcodeVo>();  
        HosKindcodeVos.add(hosKindcodeVo); 
        kindcodeSet.addAll(HosKindcodeVos.stream().collect(Collectors.toList()));  
        List<HosKindcodeVo> hosKindcodes = reportStatisticsDao.getHosKindcodeVoByPid(kindId);  
        for (HosKindcodeVo d : hosKindcodes) {  
        	findChildHosKindcodes(kindcodeSet, d.getId());  
        }  
    }

	@Override
	public QueryResult<HosMonthGoodsReceiptQueryVo> getHosMonthGoodsReceiptQuery(
			QueryInfo<HosMonthGoodsReceiptQueryVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		return  PageUtils.endPage(reportStatisticsDao.getHosMonthGoodsReceiptQuery(queryInfo.getQueryObject()));
	}

	@Override
	public QueryResult<HosMonthGoodsReceiptDetailVo> getHosMonthGoodsReceiptDetail(
			QueryInfo<HosMonthGoodsReceiptDetailVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		return  PageUtils.endPage(reportStatisticsDao.getHosMonthGoodsReceiptDetail(queryInfo.getQueryObject()));
	}

	/**
	 * 查询当前登陆用户所管理的库房对应的科室（报表查询中当前用户可见的科室）
	 */
	@Override
	public QueryResult<UserDeptVo> getUserDepts(QueryInfo<UserDeptVo> queryInfo) {
		String sql = "SELECT vUserDept.cname AS deptName,vUserDept.dept_id AS deptId,vUserDept.user_id AS userId FROM v_user_dept vUserDept WHERE vUserDept.user_id = ?";
		List<UserDeptVo> voList = this.simpleDao.queryForList(sql, UserDeptVo.class, queryInfo.getQueryObject().getUserId());
		return PageUtils.endPage(voList);
	} 
	

	/**
	 * 直销结算发票查询
	 */
	@Override
	public QueryResult<HosSettleZXQueryVo> getHosSettleForZX(QueryInfo<HosSettleZXQueryVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(reportStatisticsDao.getHosSettleForZX(queryInfo.getQueryObject()));
	}

	/**
	 * 直销结算明细
	 */
	@Override
	public QueryResult<HosSettleDetailZXQueryVo> getHosSettleDetailForZX(
			QueryInfo<HosSettleDetailZXQueryVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(reportStatisticsDao.getHosSettleDetailForZX(queryInfo.getQueryObject()));
	}

	/**
	 * 统计i信息
	 */
	@Override
	public QueryResult<HosSettleMsgQueryVo> getHosSettleForZXMsg(QueryInfo<HosSettleZXQueryVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(reportStatisticsDao.getHosSettleForZXMsg(queryInfo.getQueryObject()));
	}

	/**
	 * 定向结算查询
	 */
	@Override
	public QueryResult<HosSettleDXQueryVo> getHosSettleForDX(QueryInfo<HosSettleDXQueryVo> queryInfo) {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(reportStatisticsDao.getHosSettleForDX(queryInfo.getQueryObject()));
	}

	/**
	 * 定向结算详情
	 */
	@Override
	public QueryResult<HosSettleDetailDXQueryVo> getHosSettleDetailForDX(
			QueryInfo<HosSettleDetailDXQueryVo> queryInfo) {
		return PageUtils.endPage(reportStatisticsDao.getHosSettleDetailForDX(queryInfo.getQueryObject()));
	}

	/**
	 * 定向发票统计信息
	 */
	@Override
	public QueryResult<HosSettleMsgQueryVo> getHosSettleForDXMsg(QueryInfo<HosSettleDXQueryVo> queryInfo) {
		return PageUtils.endPage(reportStatisticsDao.getHosSettleForDXMsg(queryInfo.getQueryObject()));
	}
}
