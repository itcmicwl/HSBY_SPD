package gyqx.spdherp.applyMgr.service.impl;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import common.utils.UtilsContext;
import gyqx.platform.sys.vo.SysOrgVo;
import gyqx.spdherp.applyMgr.service.IApplySurgeryPkgService;
import gyqx.spdherp.applyMgr.vo.*;
import gyqx.spdherp.po.ApplySurgeryPkg;
import gyqx.spdherp.spdOutPtl.service.ISpdOutPtlService;
import gyqx.spdherp.stockPile.service.IHosStockpileService;
import gyqx.spdherp.stockPile.vo.HosStockPileVo;
import gyqx.spdherp.applyMgr.vo.SurDef4applyBillVo;
import gyqx.spdherp.po.*;
import gyqx.spdherp.spdOutPtl.service.ISpdOutPtlService;
import gyqx.spdherp.surgery.constant.Constants;
import gyqx.spdherp.surgery.dao.SurgeryPkgDefListDao;
import gyqx.spdherp.surgery.service.ISurgeryPkgService;
import gyqx.spdherp.surgery.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.transform.Tx;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.message.MessageVo;
import gyqx.spdherp.applyMgr.Constance;
import gyqx.spdherp.applyMgr.dao.DeptBuyCarDao;
import gyqx.spdherp.applyMgr.dao.DeptBuyMainDao;
import gyqx.spdherp.applyMgr.dao.DeptBuySubDao;
import gyqx.spdherp.applyMgr.service.IDeptBuyMainService;
import gyqx.spdherp.po.DeptBuyCar;
import gyqx.spdherp.po.DeptBuyMain;
import gyqx.spdherp.po.DeptBuySub;
import gyqx.spdherp.applyMgr.vo.DeptBuyBillVo;
import gyqx.spdherp.applyMgr.vo.DeptBuySubVo;
import gyqx.spdherp.applyMgr.vo.DeptCarGoodsStVo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class DeptBuyMainService implements IDeptBuyMainService {
	@Resource
	private SysAtomUtil atomUtil;

	@Resource
	private DeptBuyMainDao dao;
	@Resource
	private DeptBuySubDao subDao;
	@Resource
	private DeptBuyCarDao carDao;
	@Resource
	private SimpleDao simpleDao;
	@Resource
	private SurgeryPkgDefListDao surDefListDao;
	@Resource
	private ISurgeryPkgService surPkgSer;
	@Resource
	private ISpdOutPtlService iSpdOutPtlService;
    @Autowired
    private IApplySurgeryPkgService appSurPkgSer;
    @Autowired
    private IHosStockpileService hosStockSer;
    @Value("${label.isElectronicTag:false}")
	private Boolean isElectronicTag;
	public DeptBuyMain get(String id) {
		return dao.get(id);
	}

	public DeptBuyMain add(DeptBuyMain deptBuyMain) throws Exception {
		return dao.insertAndGet(deptBuyMain);
	}

	public DeptBuyMain update(DeptBuyMain deptBuyMain) throws Exception {
		return dao.updateAndGet(deptBuyMain);
	}

	public int close(String billId) throws Exception{
		return simpleDao.executeSql("update dept_buy_main set if_closed=1 where bill_Id =? ", billId);
	}

	public int updateFields(DeptBuyMain deptBuyMain, String fieldNames) throws Exception {
		return dao.updateFields(deptBuyMain, fieldNames);
	}

	public void delete(DeptBuyMain deptBuyMain) throws Exception {
		dao.delete(deptBuyMain);
	}

	public DeptBuyMain getByFieldName(String fieldName, Object fieldValue) throws Exception {
		return (DeptBuyMain) dao.getByFieldName(fieldName, fieldValue);
	}

	public List<DeptBuyMain> query(String predicate, String orderBy, Object... fieldValue) throws Exception {
		return (List<DeptBuyMain>) dao.query(predicate, orderBy, fieldValue);
	}

	public List<DeptBuyMain> list(DeptBuyMain bean) throws Exception {
		return (List<DeptBuyMain>) dao.list(bean);
	}

	public QueryResult<DeptBuyMain> listByPage(QueryInfo<DeptBuyMain> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

	public QueryResult<DeptBuyBillVo> getVoList(QueryInfo<DeptBuyBillVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.getVoList(queryInfo.getQueryObject()));
	}

	/**
	 * 请购车商品生成请购单
	 * 
	 * ganwei :华山北院buyKind 全为10  ， 依据purMode 拆单
	 */
	public void addDeptBuyBillVoByMix(DeptBuyBillVo vo) throws Exception { // 实采与虚采混合提交调用，执行拆单
		List<DeptBuySub> sub10 = new ArrayList<DeptBuySub>(); //purMode 普通耗材
		List<DeptBuySub> sub20 = new ArrayList<DeptBuySub>();//purMode 高值耗材
		List<DeptBuySub> sub30 = new ArrayList<DeptBuySub>();//purMode 办公用品
		List<DeptBuySub> sub40 = new ArrayList<DeptBuySub>();//purMode 手术包商品
	//	List<ApplySurgeryPkg> aspLst = new ArrayList();
		//List<DeptBuySub> mixSubs = vo.getSubList();
		List<DeptCarGoodsStVo> mixCars = vo.getCarList();

		for (DeptCarGoodsStVo c : mixCars) {
			if ("10".equals(c.getPurMode())) {
				for (DeptBuySub s : vo.getSubList()) {
					if (org.springframework.util.StringUtils.hasText(s.getGoodsId())&&s.getGoodsId().equals(c.getGoodsId())) {
						s.setPrice(c.getPrice());
						s.setApplyQty(c.getNeedQty().intValue());//设置请购数量
						sub10.add(s);
					}
				}
			} if ("20".equals(c.getPurMode())) {
				for (DeptBuySub s : vo.getSubList()) {
					if (org.springframework.util.StringUtils.hasText(s.getGoodsId())&&s.getGoodsId().equals(c.getGoodsId())) {
						s.setPrice(c.getPrice());
						s.setApplyQty(c.getNeedQty().intValue());//设置请购数量
						sub20.add(s);
					}
				}
			}
			if ("30".equals(c.getPurMode())) {
				for (DeptBuySub s : vo.getSubList()) {
					if (org.springframework.util.StringUtils.hasText(s.getGoodsId())&&s.getGoodsId().equals(c.getGoodsId())) {
						s.setPrice(c.getPrice());
						s.setApplyQty(c.getNeedQty().intValue());//设置请购数量
						sub30.add(s);
					}
				}
			}
			if ("40".equals(c.getPurMode())) {

                          //查找手术包定义表 中的商品
				  //      List<SurgeryPkgDefList> surGoods4Def =simpleDao.queryForList("SELECT * FROM  surgery_pkg_def_list where sur_id =? and hos_id =? ",SurgeryPkgDefList.class,c.getSurId(),c.getHosId());
				SurgeryPkgDefListVo  qb = new SurgeryPkgDefListVo();
				qb.setSurId(c.getSurId());
				qb.setHosId(c.getHosId());
				List<SurgeryPkgDefListVo> surGoods4Def =	surDefListDao.list(qb);
				for (SurgeryPkgDefListVo d : surGoods4Def) {
					DeptBuySub s = new DeptBuySub();
					s.setApplyQty(d.getQty().intValue()*c.getNeedSurPkgQty());
					s.setBuyDeptId(c.getDeptId());
					s.setSubState(10);
					s.setQty(new BigDecimal(s.getApplyQty()));
					s.setWhQty(new BigDecimal(s.getApplyQty()));
					s.setGoodsId(d.getGoodsId());
					s.setSurId(c.getSurId());
					s.setHosId(d.getHosId());
					s.setUniqueKind(Integer.valueOf(d.getHosGoods().getUniqueCodeStrategy()));
					s.setPrice(d.getHosGoods().getPrice());
					sub40.add(s);

				}

			/*	if(aspLst.stream().noneMatch(p->p.getSurId().equals(c.getSurId()))){ //准备apply_surgery_pkg表数据 依据sub40 中的 surId 进行分组
					ApplySurgeryPkg pk  = new ApplySurgeryPkg();
					pk.setApplyQty(new BigDecimal(c.getNeedSurPkgQty()));
					pk.setSurId(c.getSurId());
					pk.setStatus(0);
					aspLst.add(pk);
				}*/



			}

		}
		if(sub40.size()>0){  //生成手术包单据
			DeptBuyMain main = new DeptBuyMain();
			Tx.transform(vo).to(main);
			String id = main.getHosId() + atomUtil.newValue("dept-buy-main-id");
			main.setBuyKind(20);
			main.setPurMode(40);
			main.setFillDate(new Date());
			main.setId(id);
			main.setBillId(id);
			main.setSumRow(sub40.size());
			main.setState(Constance.DEPTAPPLAY_MAIN_STATUS_SAVED); // 未处理
			dao.insertAndGet(main);
	/*		for(ApplySurgeryPkg p :aspLst){
				p.setBillId(main.getBillId());
				p.setId(java.util.UUID.randomUUID().toString());
				p.setVersion(0);
				p.setLastUpdateDatetime(new java.util.Date());
				simpleDao.insert(p);
			}*/

			for (int i = 1; i <= sub40.size(); i++) {
				DeptBuySub sub = sub40.get(i - 1);
				String subId = main.getHosId() + atomUtil.newValue("dept-buy-sub-id");
				sub.setSendType(30); // 新增时设置默认值
				sub.setId(subId);
				sub.setBillId(id);
				sub.setBuyDeptId(vo.getBuyDeptId());
				sub.setRowNumber(i);
				sub.setSubState(Constance.DEPTAPPLAY_SUB_STATUS_PENDING);//待处理
				sub.setFundType(Constance.DEPTAPPLAY_SUB_FUND_TYPE_SELF_FINANCING);//自筹经费
				sub.setQty(new BigDecimal(0));//设备科审核数量设置为0
				sub.setApplyQty(sub.getApplyQty());//科室请购数量
				sub.setWhQty(new BigDecimal(0)); // 采购数量默认为0
				sub.setSendQty(new BigDecimal(0)); // 默认将已发数量设置为0
				sub.setLastUpdateDatetime(new Date()); // 最后修改时间
				//sub.setSurId();
				subDao.insertAndGet(sub);
			}
		}
		if (sub10.size() > 0) { // 生成实采单
			DeptBuyMain main = new DeptBuyMain();
			Tx.transform(vo).to(main);
			String id = main.getHosId() + atomUtil.newValue("dept-buy-main-id");
			main.setBuyKind(10);
			main.setPurMode(10);
			main.setFillDate(new Date());
			main.setId(id);
			main.setBillId(id);
			//main.setSumRow(vo.getSubList().size());
			main.setSumRow(sub10.size());
			main.setState(Constance.DEPTAPPLAY_MAIN_STATUS_SAVED); // 未处理
			// main.setDeptState(10); //暂存 该字段停用
			dao.insertAndGet(main);
			for (int i = 1; i <= sub10.size(); i++) {
				DeptBuySub sub = sub10.get(i - 1);
				String subId = main.getHosId() + atomUtil.newValue("dept-buy-sub-id");
				sub.setSendType(30); // 新增时设置默认值
				sub.setId(subId);
				sub.setBillId(id);
				sub.setBuyDeptId(vo.getBuyDeptId());
				sub.setRowNumber(i);
				sub.setSubState(Constance.DEPTAPPLAY_SUB_STATUS_PENDING);//待处理
				sub.setFundType(Constance.DEPTAPPLAY_SUB_FUND_TYPE_SELF_FINANCING);//自筹经费
				sub.setQty(new BigDecimal(0));//设备科审核数量设置为0
				sub.setApplyQty(sub.getApplyQty());//科室请购数量
				sub.setWhQty(new BigDecimal(0)); // 采购数量默认为0
				sub.setSendQty(new BigDecimal(0)); // 默认将已发数量设置为0
				sub.setLastUpdateDatetime(new Date()); // 最后修改时间
				subDao.insertAndGet(sub);
			}
		}
		if (sub20.size() > 0) { // 生成虚采单
			DeptBuyMain main = new DeptBuyMain();
			Tx.transform(vo).to(main);
			String id = main.getHosId() + atomUtil.newValue("dept-buy-main-id");
			//main.setBuyKind(20);
			main.setPurMode(20);
			//main.setBuyKind(10); 启动真实高值关联模式
			main.setBuyKind(20);
			main.setFillDate(new Date());
			main.setId(id);
			main.setBillId(id);
			main.setSumRow(sub20.size());
			main.setState(Constance.DEPTAPPLAY_MAIN_STATUS_SAVED); // 未处理
			// main.setDeptState(10); //暂存 该字段停用
			dao.insertAndGet(main);
			for (int i = 1; i <= sub20.size(); i++) {
				DeptBuySub sub = sub20.get(i - 1);
				String subId = main.getHosId() + atomUtil.newValue("dept-buy-sub-id");
				sub.setSendType(30); // 新增时设置默认值
				sub.setId(subId);
				sub.setBillId(id);
				sub.setBuyDeptId(vo.getBuyDeptId());
				sub.setRowNumber(i);
				sub.setSubState(Constance.DEPTAPPLAY_SUB_STATUS_PENDING);//待处理
				sub.setFundType(Constance.DEPTAPPLAY_SUB_FUND_TYPE_SELF_FINANCING);//自筹经费
				sub.setQty(new BigDecimal(0));//设备科审核数量设置为0
				sub.setApplyQty(sub.getApplyQty());//科室请购数量
				sub.setWhQty(new BigDecimal(0)); // 采购数量默认为0
				sub.setLastUpdateDatetime(new Date()); // 最后修改时间
				subDao.insertAndGet(sub);
			}
		}
		if (sub30.size() > 0) { // 生成办公用品请购单
			DeptBuyMain main = new DeptBuyMain();
			Tx.transform(vo).to(main);
			String id = main.getHosId() + atomUtil.newValue("dept-buy-main-id");
			main.setPurMode(30);
			main.setBuyKind(30);
			main.setFillDate(new Date());
			main.setId(id);
			main.setBillId(id);
			main.setSumRow(sub30.size());
			main.setState(Constance.DEPTAPPLAY_MAIN_STATUS_SAVED); // 未处理
			// main.setDeptState(10); //暂存 该字段停用
			dao.insertAndGet(main);
			for (int k = 0; k <sub30.size(); k++) {
				DeptBuySub sub = sub30.get(k);
				String subId = main.getHosId() + atomUtil.newValue("dept-buy-sub-id");
				sub.setSendType(30); // 新增时设置默认值
				sub.setId(subId);
				sub.setBillId(id);
				sub.setBuyDeptId(vo.getBuyDeptId());
				sub.setRowNumber(k+1);
				sub.setSubState(Constance.DEPTAPPLAY_SUB_STATUS_PENDING);//待处理
				sub.setFundType(Constance.DEPTAPPLAY_SUB_FUND_TYPE_SELF_FINANCING);//自筹经费
				sub.setQty(new BigDecimal(0));//设备科审核数量设置为0
				sub.setApplyQty(sub.getApplyQty());//科室请购数量
				sub.setWhQty(new BigDecimal(0)); // 采购数量默认为0
				sub.setLastUpdateDatetime(new Date()); // 最后修改时间
				subDao.insertAndGet(sub);
			}
		}
		for (DeptCarGoodsStVo carvo : vo.getCarList()) {
			DeptBuyCar car = new DeptBuyCar();
			// Tx.transform(carvo).to(car);
			if(org.springframework.util.StringUtils.hasText(carvo.getCarId())){
				car = carDao.get(carvo.getCarId());
				carDao.delete(car);
			}
			else  simpleDao.executeSql("delete from dept_buy_car where dept_id =? and hos_id =? and sur_id =?",carvo.getDeptId(),carvo.getHosId(),carvo.getSurId());

		}

	}
	
	/**
	 * 合并三级科室请购单明细
	 */
	public void mergeDeptApply(DeptBuyBillVo vo) throws Exception { // 实采与虚采混合提交调用，执行拆单
		List<DeptBuySub> sub10 = new ArrayList<DeptBuySub>();
		List<DeptBuySub> sub20 = new ArrayList<DeptBuySub>();
		//List<DeptBuySub> mixSubs = vo.getSubList();
		List<DeptCarGoodsStVo> mixCars = vo.getCarList();

		for (DeptCarGoodsStVo c : mixCars) {
			if (c.getBuyKind() == 10) {
				for (DeptBuySub s : vo.getSubList()) {
					if (s.getGoodsId().equals(c.getGoodsId())) {
						s.setPrice(c.getPrice());
						s.setApplyQty(c.getNeedQty().intValue());//设置请购数量
						sub10.add(s);
					}
				}
			} else {
				for (DeptBuySub s : vo.getSubList()) {
					if (s.getGoodsId().equals(c.getGoodsId())) {
						s.setPrice(c.getPrice());
						s.setApplyQty(c.getNeedQty().intValue());//设置请购数量
						sub20.add(s);
					}
				}
			}

		}
		if (sub10.size() > 0) { // 生成实采单
			DeptBuyMain main = new DeptBuyMain();
			Tx.transform(vo).to(main);
			String id = main.getHosId() + atomUtil.newValue("dept-buy-main-id");
			main.setBuyKind(10);
			main.setFillDate(new Date());
			main.setId(id);
			main.setBillId(id);
			main.setSumRow(vo.getSubList().size());
			main.setState(Constance.DEPTAPPLAY_MAIN_STATUS_SAVED); // 未处理
			// main.setDeptState(10); //暂存 该字段停用
			dao.insertAndGet(main);
			for (int i = 1; i <= sub10.size(); i++) {
				DeptBuySub sub = sub10.get(i - 1);
				String subId = main.getHosId() + atomUtil.newValue("dept-buy-sub-id");
				sub.setSendType(30); // 新增时设置默认值
				sub.setId(subId);
				sub.setBillId(id);
				sub.setBuyDeptId(vo.getBuyDeptId());
				sub.setRowNumber(i);
				sub.setSubState(Constance.DEPTAPPLAY_SUB_STATUS_PENDING);//待处理
				sub.setFundType(Constance.DEPTAPPLAY_SUB_FUND_TYPE_SELF_FINANCING);//自筹经费
				sub.setQty(new BigDecimal(0));//设备科审核数量设置为0
				sub.setApplyQty(sub.getApplyQty());//科室请购数量
				sub.setWhQty(new BigDecimal(0)); // 采购数量默认为0
				sub.setSendQty(new BigDecimal(0)); // 默认将已发数量设置为0
				sub.setLastUpdateDatetime(new Date()); // 最后修改时间
				subDao.insertAndGet(sub);
			}
		}
		if (sub20.size() > 0) { // 生成虚采单
			DeptBuyMain main = new DeptBuyMain();
			Tx.transform(vo).to(main);
			String id = main.getHosId() + atomUtil.newValue("dept-buy-main-id");
			main.setBuyKind(20);
			main.setFillDate(new Date());
			main.setId(id);
			main.setBillId(id);
			main.setSumRow(vo.getSubList().size());
			main.setState(Constance.DEPTAPPLAY_MAIN_STATUS_SAVED); // 未处理
			// main.setDeptState(10); //暂存 该字段停用
			dao.insertAndGet(main);
			for (int i = 1; i <= sub20.size(); i++) {
				DeptBuySub sub = sub20.get(i - 1);
				String subId = main.getHosId() + atomUtil.newValue("dept-buy-sub-id");
				sub.setSendType(30); // 新增时设置默认值
				sub.setId(subId);
				sub.setBillId(id);
				sub.setBuyDeptId(vo.getBuyDeptId());
				sub.setRowNumber(i);
				sub.setSubState(Constance.DEPTAPPLAY_SUB_STATUS_PENDING);//待处理
				sub.setFundType(Constance.DEPTAPPLAY_SUB_FUND_TYPE_SELF_FINANCING);//自筹经费
				sub.setQty(new BigDecimal(0));//设备科审核数量设置为0
				sub.setApplyQty(sub.getApplyQty());//科室请购数量
				sub.setWhQty(new BigDecimal(0)); // 采购数量默认为0
				sub.setLastUpdateDatetime(new Date()); // 最后修改时间
				subDao.insertAndGet(sub);
			}
		}
		//将三级请购单明细设置为不采购，更新主表状态，二级科室走采购出库
		for (DeptCarGoodsStVo carvo : vo.getCarList()) {
			List<String> subIds = carvo.getSubIds();
			for (String id : subIds) {
				DeptBuySub sub = subDao.get(id);
				sub.setSubState(Constance.DEPTAPPLAY_SUB_STATUS_NOTPURCHASE);// 不采购
				subDao.updateNotNullFields(sub);
				DeptBuyMain main = dao.get(sub.getBillId());
				main.setState(checkMainState(main,sub));
				
				if(main.getState()==Constance.DEPTAPPLAY_MAIN_STATUS_HANDLED)  {//40//如果是正常结束，也需要将主表的ifClosed 进行关闭
					main.setIfClosed(1);
				}   
				dao.updateNotNullFields(main);
			}
		}

	}
	
    /**
     * 判读请购单主单状态
     * @param main
     * @param nowSub
     * @return
     * @throws Exception
     */
    public int checkMainState(DeptBuyMain main,DeptBuySub nowSub) throws Exception{
    	int re = Constance.DEPTAPPLAY_MAIN_STATUS_HANDLING; // 30 处理中
    	boolean flag = true;
    	//查询所有sub
    	DeptBuySub qbean = new DeptBuySub();
    	qbean.setBillId(main.getBillId());
        List<DeptBuySub> subs =	subDao.list(qbean);
    	for(DeptBuySub s:subs){
    		if (s.getId() == nowSub.getId() ){
    			if(Constance.DEPTAPPLAY_SUB_STATUS_PENDING.equals(nowSub.getSubState())||Constance.DEPTAPPLAY_SUB_STATUS_PARTDISTRED.equals(nowSub.getSubState())||Constance.DEPTAPPLAY_SUB_STATUS_PURCHAED.equals(nowSub.getSubState())||Constance.DEPTAPPLAY_SUB_STATUS_NOTPURCHASE.equals(nowSub.getSubState())){//10 待处理 20已驳回 30 不采购  40部分配送  50全部配送 60 yi'cai'g
    				flag =false;
    				break;
    			}

    		}
    		else{
    			if(Constance.DEPTAPPLAY_SUB_STATUS_PENDING.equals(s.getSubState())||Constance.DEPTAPPLAY_SUB_STATUS_PARTDISTRED.equals(s.getSubState())||Constance.DEPTAPPLAY_SUB_STATUS_PURCHAED.equals(s.getSubState())||Constance.DEPTAPPLAY_SUB_STATUS_NOTPURCHASE.equals(s.getSubState())){//10 待处理 20已驳回 30 不采购  40部分配送  50全部配送
    				 flag = false;
    				 break;
    			 }
    		}
    	}
    	if(flag){
    		re= Constance.DEPTAPPLAY_MAIN_STATUS_HANDLED;//40
    	}
    	return re;
    }	

	@Override
	public String createBillAgain(DeptBuyBillVo vo) throws Exception {
		// 添加主表、子表、 并从购物车中删除记录
		DeptBuyMain main = new DeptBuyMain();
		Tx.transform(vo).to(main);


		String id = main.getHosId() + atomUtil.newValue("dept-buy-main-id");
		main.setFillDate(new Date());
		main.setId(id);
		main.setBillId(id);
		List<DeptBuySubVo> subVoList = vo.getSubVoList();
		main.setSumRow(subVoList.size());
		main.setState(Constance.DEPTAPPLAY_MAIN_STATUS_SAVED); // 未处理
		// main.setDeptState(10); //暂存 该字段停用
		// 还原
		main.setAuditDate(null);
		main.setAuditor(null);
		main.setPurMode(vo.getPurMode());
		main.setWhSumrow(0);
		main.setDeptDealMan(null);
		main.setDeptDealTime(null);
		dao.insertAndGet(main);
		for (int i = 1, size = subVoList.size(); i <= size; i++) {
			DeptBuySubVo subVo = subVoList.get(i - 1);
			DeptBuySub sub = new DeptBuySub();
			Tx.transform(subVo).to(sub);
			String subId = main.getHosId() + atomUtil.newValue("dept-buy-sub-id");
			sub.setSendType(30); // 新增时设置默认值
			sub.setId(subId);
			sub.setBillId(id);
			sub.setBuyDeptId(vo.getBuyDeptId());
			sub.setRowNumber(i);
			sub.setQty(new BigDecimal(0));// 设备科审核数量
			sub.setWhQty(new BigDecimal(0)); // 实际采购数
			sub.setSendQty(new BigDecimal(0)); // 默认将已发数量设置为0
			// 状态还原
			sub.setSubState(Constance.DEPTAPPLAY_SUB_STATUS_PENDING); // 明细表状态
			sub.setWarehouseView(null);
			sub.setWarehouseRejectType(0);
			sub.setWarehouseDealMan(null); 
			sub.setWarehouseDealTime(null);
			subDao.insertAndGet(sub);
		}
		return id;
	}

	public void addDeptBuyBillVo(DeptBuyBillVo vo) throws Exception {
		// 添加主表、子表、 并从购物车中删除记录
		DeptBuyMain main = new DeptBuyMain();
		Tx.transform(vo).to(main);
		String id = main.getHosId() + atomUtil.newValue("dept-buy-main-id");
		main.setFillDate(new Date());
		main.setId(id);
		main.setBillId(id);
		main.setSumRow(vo.getSubList().size());
		main.setState(Constance.DEPTAPPLAY_MAIN_STATUS_SAVED); // 未处理
		// main.setDeptState(10); //暂存 该字段停用
		dao.insertAndGet(main);
		for (int i = 1; i <= vo.getSubList().size(); i++) {
			DeptBuySub sub = vo.getSubList().get(i - 1);
			String subId = main.getHosId() + atomUtil.newValue("dept-buy-sub-id");
			sub.setSendType(30); // 新增时设置默认值
			sub.setId(subId);
			sub.setBillId(id);
			sub.setBuyDeptId(vo.getBuyDeptId());
			sub.setRowNumber(i);
			sub.setWhQty(sub.getQty()); // 默认将 实际采购数码与请购数一致
			sub.setSendQty(new BigDecimal(0)); // 默认将已发数量设置为0
			subDao.insertAndGet(sub);
		}
		for (DeptCarGoodsStVo carvo : vo.getCarList()) {
//			DeptBuyCar car = new DeptBuyCar();
			// Tx.transform(carvo).to(car);
			DeptBuyCar car = carDao.get(carvo.getCarId());
			carDao.delete(car);
		}
	}
	
	public DeptBuyBillVo updateDeptBuyBillVo(DeptBuyBillVo vo) throws Exception {
		DeptBuyMain main = new DeptBuyMain();
		Tx.transform(vo).to(main);
		main.setLastUpdateDatetime(new Date());
		main.setSumRow(vo.getSubVoList().size());
		if (vo.getSubVoList().size() == 0) {// 所有明细行删除时，作废请购单
			main.setState(Constance.DEPTAPPLAY_MAIN_STATUS_CANCEL);
		}
		dao.updateAndGet(main);
		subDao.deleteByBillId(vo.getBillId());
		for (int i = 1; i <= vo.getSubVoList().size(); i++) {
			DeptBuySubVo subVo = vo.getSubVoList().get(i - 1);
			DeptBuySub sub = new DeptBuySub();
			Tx.transform(subVo).to(sub);
			String subId = main.getHosId() + atomUtil.newValue("dept-buy-sub-id");
			sub.setId(subId);
			sub.setBillId(vo.getBillId());
			sub.setBuyDeptId(vo.getBuyDeptId());
			sub.setRowNumber(i);
			sub.setQty(new BigDecimal(0));//设备科审核数量默认为0
			sub.setWhQty(new BigDecimal(0));
			sub.setSendQty(new BigDecimal(0)); // 默认将已发数量设置为0
			sub.setApplyQty(subVo.getApplyQty());//科室申请数量
			if (StringUtils.isNotBlank(subVo.getFundType())) {// 资金来源
				sub.setFundType(subVo.getFundType());
			} else {
				sub.setFundType(Constance.DEPTAPPLAY_SUB_FUND_TYPE_SELF_FINANCING);// 自筹经费
			}
			subDao.insertAndGet(sub);
		}
		return vo;
	}

	public DeptBuyBillVo getBillDetails(DeptBuyBillVo bv) throws Exception {
		bv = dao.getVoList(bv).get(0);
		DeptBuySubVo subQB = new DeptBuySubVo();
		subQB.setBillId(bv.getBillId());
		List<DeptBuySubVo> sbVos = subDao.getVoList(subQB);
		bv.setSubVoList(sbVos);
		return bv;
	}

	public List<DeptBuyBillVo> getMainBillVo4pur(DeptBuyBillVo queryInfo) throws Exception {
		return dao.getMainBillVo4pur(queryInfo);
	}
	public List<DeptBuyBillVo> getMainBillVo4merger(DeptBuyBillVo queryInfo) throws Exception {
		return dao.getMainBillVo4merger(queryInfo);
	}
	
	/**
	 * 单个提交审批单
	 * @param state
	 * @throws Exception
	 */
	@Transactional(propagation= Propagation.SUPPORTS)
	public DeptBuyBillVo processSubmitBill(DeptBuyBillVo vo, Integer state) throws Exception {
		DeptBuyMain main = new DeptBuyMain();
		Tx.transform(vo).to(main);
		
		if(Constance.DEPTAPPLAY_MAIN_STATUS_AUDIT.equals(state)){//科室审核
			main.setDeptDealTime(new Date());//科内审核时间
		} else if(Constance.DEPTAPPLAY_MAIN_STATUS_COMMITED.equals(state)){//请购审核
			main.setAuditDate(new Date());//审核时间(设备科审核时间)
		}
		main.setState(state);
		main.setLastUpdateDatetime(new Date());
		main.setSumRow(vo.getSubVoList().size());
		dao.updateAndGet(main);
		subDao.deleteByBillId(vo.getBillId());
		for (int i = 1; i <= vo.getSubVoList().size(); i++) {
			DeptBuySubVo subVo = vo.getSubVoList().get(i - 1);
			DeptBuySub sub = new DeptBuySub();
			Tx.transform(subVo).to(sub);
			String subId = main.getHosId() + atomUtil.newValue("dept-buy-sub-id");
			sub.setId(subId);
			sub.setBillId(vo.getBillId());
			sub.setBuyDeptId(vo.getBuyDeptId());
			sub.setRowNumber(i);
			sub.setSubState(Constance.DEPTAPPLAY_SUB_STATUS_PENDING);
			if(Constance.DEPTAPPLAY_MAIN_STATUS_AUDIT.equals(state)){//科室审核通过
				sub.setQty(new BigDecimal(sub.getApplyQty()));//设备科审核数量默认和科室审核数量一致
				sub.setApplyQty(sub.getApplyQty());//科室请购数量：页面审核数量
			}else if(Constance.DEPTAPPLAY_MAIN_STATUS_COMMITED.equals(state)){//设备科审核通过
				sub.setQty(sub.getQty());//设备科审核数量：页面审核数量
				sub.setApplyQty(sub.getApplyQty());//科室请购数量
			}
			sub.setWhQty(new BigDecimal(0)); // 采购数量默认为0
			sub.setSendQty(new BigDecimal(0)); // 默认将已发数量设置为0
			sub.setFundType(subVo.getFundType());//资金来源
			subDao.insertAndGet(sub);
		}
		//处理apply_surgery_pkg
		if(main.getPurMode()==40&&main.getState()==20){
			insertApplySurgeryPkg(main,vo);
		}


		if (Constance.DEPTAPPLAY_MAIN_STATUS_COMMITED.equals(main.getState())) {
			sendMessage(vo, main);
		}
		//请购类型实物请购，请购单设备科审核之后并且一级库存在在电子标签管理的情况，则回写电子标签中间表
		if(Constance.DEPTAPPLAY_MAIN_STATUS_COMMITED.equals(main.getState())&&vo.getPurMode()==Constance.DEPTAPPLAY_MAIN_PUR_MODE_FAKE
				&&isElectronicTag){
				//科室请购设备科审核之后写电子标签中间表
				iSpdOutPtlService.forPurSpdOutPtl(vo.getBillId());
		}
		return vo;
	}

	/**
	 * 设备科审核后 手术包的请购单需要写入ApplySurgeryPkg表
	 * @param main
	 * @param vo
	 * @throws Exception
	 */
	public void insertApplySurgeryPkg(DeptBuyMain main,DeptBuyBillVo vo) throws Exception{
		List<ApplySurgeryPkg> aspLst = new ArrayList();
		if(vo.getSubVoList()==null){
			//批量审核 需要查询子表集合
			DeptBuySubVo qb= new DeptBuySubVo();
			qb.setBillId(main.getBillId());
		    List<DeptBuySubVo> subLst=	subDao.getVoList(qb);
		    vo.setSubVoList(subLst);
		}
		Map<String,List<DeptBuySubVo>> surMap = vo.getSubVoList().stream().collect(Collectors.groupingBy(DeptBuySubVo::getSurId));
		surMap.forEach((key,val)->{
			SurgeryPkgDef surgeryPkgDef =simpleDao.queryForList("select * from surgery_pkg_def where id =?",SurgeryPkgDef.class,key).get(0);
			//处理该手术包的请购数量及其请购单明细
			List<SurgeryPkgDefList> surDefGoodsList = simpleDao.queryForList("SELECT * FROM surgery_pkg_def_list where sur_id =?",SurgeryPkgDefList.class,key);
			Optional<DeptBuySubVo> subBillTemp=val.stream().filter(subBillObj->surDefGoodsList.get(0).getGoodsId().equals(subBillObj.getGoodsId())).findFirst();

			if(aspLst.stream().noneMatch(p->p.getSurId().equals(subBillTemp.get().getSurId()))){
				ApplySurgeryPkg pk  = new ApplySurgeryPkg();
				pk.setApplyQty(subBillTemp.get().getQty().divide(surDefGoodsList.get(0).getQty()));
				pk.setSurId(subBillTemp.get().getSurId());
				pk.setStatus(0);
				aspLst.add(pk);
			}
		});
		for(ApplySurgeryPkg p :aspLst){
			p.setBillId(main.getBillId());
			p.setId(java.util.UUID.randomUUID().toString());
			p.setVersion(0);
			p.setLastUpdateDatetime(new java.util.Date());
			simpleDao.insert(p);
		}
	}

	/**
	 * 批量提交请购单
	 */
	public void processBatchSubmitBill(List<DeptBuyBillVo> billVoList, Integer state) throws Exception {
		for(DeptBuyBillVo vo:billVoList){
			submitBill(vo,state);
		}
	}

	/**
	 * 提交请购单
	 * @param vo 
	 * @param state 状态
	 * @throws Exception
	 * @throws JsonProcessingException
	 */
	private void submitBill(DeptBuyBillVo vo,Integer state) throws Exception, JsonProcessingException {
		DeptBuyMain main = new DeptBuyMain();
		Tx.transform(vo).to(main);
		Date lastUpdateTime = new Date();
		if(Constance.DEPTAPPLAY_MAIN_STATUS_AUDIT.equals(state)){//科室审核
			main.setDeptDealTime(lastUpdateTime);//科内审核时间
		} else if(Constance.DEPTAPPLAY_MAIN_STATUS_COMMITED.equals(state)){//请购审核
			main.setAuditDate(lastUpdateTime);//审核时间(设备科审核时间)
		}
		main.setState(state);
		main.setLastUpdateDatetime(lastUpdateTime);
		dao.updateAndGet(main);
		List<DeptBuySub> subList = simpleDao.queryByFieldName(DeptBuySub.class, "billId", main.getBillId());
		for (DeptBuySub sub:subList) {
			if(Constance.DEPTAPPLAY_MAIN_STATUS_AUDIT.equals(state)){//科室审核
				sub.setQty(new BigDecimal(sub.getApplyQty()));
			}
			sub.setLastUpdateDatetime(lastUpdateTime);
			simpleDao.updateNotNullFields(sub);
		}

		//处理apply_surgery_pkg
		if(main.getPurMode()==40&&main.getState()==20){
			insertApplySurgeryPkg(main,vo);
		}
		if (Constance.DEPTAPPLAY_MAIN_STATUS_COMMITED.equals(main.getState())) {
			sendMessage(vo, main);
		}
	}

	/**
	 * 发送站内消息
	 * @param vo
	 * @param main
	 * @throws JsonProcessingException
	 */
	private void sendMessage(DeptBuyBillVo vo, DeptBuyMain main) throws JsonProcessingException {
		MessageVo msgVo = new MessageVo();
		msgVo.setTitle("您有请购订单需要处理，请及时查看");
		msgVo.setUrl(String.format("/applyMgr/basePurchase?billId=%s",main.getBillId()));
		msgVo.setMessage(String.format("您有%s提交的请购订单需要处理:%s。",vo.getFillterName(),main.getBillId()));
		msgVo.setOrgId(main.getSourceWarehouseId());
		msgVo.setWriterId(main.getFillter());
		/*List<String> users = new ArrayList<>();
		users.add(pur.getFillter());*/
		//msgVo.setUserIds(users);
		//MessageSender.send(msgVo);
	}

	public List<SysOrgVo> getBuyDeptList(DeptBuyBillVo queryInfo) throws Exception{
		return  dao.getBuyDeptList(queryInfo);
	}

	@Override
	public QueryResult<DeptBuyBillVo> getUnPackApplyBIllList(QueryInfo<DeptBuyBillVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.getUnPackApplyBIllList(queryInfo.getQueryObject()));
	}

	@Override
	public UnPackApplyVo getUnPackApplyBIll(String billId,String stockId) throws Exception {
		UnPackApplyVo res = new UnPackApplyVo();
		DeptBuyBillVo dbb = dao.getUnPackApplyBIll(billId);
		DeptBuySubVo query = new DeptBuySubVo();
		query.setBillId(billId);
		List<DeptBuySubVo> lstDetail = subDao.getVoList4SurPack(billId,stockId);//请购单明细
		dbb.setSubVoList(lstDetail);
		List<ApplySurgeryPkgVo> pkgList = appSurPkgSer.getSurInfoByApplyBillId(billId);//手术包信息
        HosStockPileVo stocQuery = new HosStockPileVo();
        Set<String> lstGoodsId = lstDetail.stream().map(o->o.getGoodsId()).collect(Collectors.toSet());
        stocQuery.setHosId(dbb.getHosId());
        stocQuery.setStocId(stockId);
        stocQuery.setGoodsIds(lstGoodsId);
        List<HosStockPileVo> goodsStocInfoList = hosStockSer.getGoodsStoc(stocQuery).stream().filter(o->{
        	return o.getQty().compareTo(o.getTakeQty())>0;
		}).collect(Collectors.toList());
        //产品库存信息（包含批次）
		goodsStocInfoList.forEach(item->{
			if(item.getTakeQty()==null){
				item.setTakeQty(new BigDecimal(0));
			}else {
				item.setQty(item.getQty().subtract(item.getTakeQty()));
				item.setTakeQty(new BigDecimal(0));
			}
		});
        res.setMain(dbb);
		res.setLstDetail(goodsStocInfoList);
		res.setLstPkg(pkgList);
		return  res;
	}


	public DeptBuyBillVo getBillDetails4sur(DeptBuyBillVo bv) throws Exception {
		List<SurDef4applyBillVo> surDef4applyBillVoList  = new ArrayList<SurDef4applyBillVo>();

		bv = dao.getVoList(bv).get(0);
		DeptBuySubVo subQB = new DeptBuySubVo();
		subQB.setBillId(bv.getBillId());
		List<DeptBuySubVo> sbVos = subDao.getVoList(subQB);
		bv.setSubVoList(sbVos);
	    //查询该手术包请购单下的手术包及数量


		Map<String ,List<DeptBuySubVo>> subGroupBySurId = sbVos.stream().collect(Collectors.groupingBy(DeptBuySubVo::getSurId));
		subGroupBySurId.forEach((key,val)->{
			SurDef4applyBillVo	surObj = new  SurDef4applyBillVo();
			SurgeryPkgDef surgeryPkgDef =simpleDao.queryForList("select * from surgery_pkg_def where id =?",SurgeryPkgDef.class,key).get(0);
			//处理该手术包的请购数量及其请购单明细
			List<SurgeryPkgDefList> surDefGoodsList = simpleDao.queryForList("SELECT * FROM surgery_pkg_def_list where sur_id =?",SurgeryPkgDefList.class,key);
			Optional<DeptBuySubVo> subBillTemp=val.stream().filter(subBillObj->surDefGoodsList.get(0).getGoodsId().equals(subBillObj.getGoodsId())).findFirst();
			surObj.setSurDef(surgeryPkgDef);
			surObj.setQty(subBillTemp.get().getApplyQty()/surDefGoodsList.get(0).getQty().intValue());
			surObj.setSurDefGoodsList(surDefGoodsList);
			surObj.setSurDefSubBillList(val);
			surDef4applyBillVoList.add(surObj);
		});
		bv.setSurDef4applyBillVoList(surDef4applyBillVoList);
		return bv;
	}
}
