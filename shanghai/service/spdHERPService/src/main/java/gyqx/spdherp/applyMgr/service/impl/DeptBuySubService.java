package gyqx.spdherp.applyMgr.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.UserOnlineStateUtils;
import gyqx.spdherp.applyMgr.Constance;
import gyqx.spdherp.applyMgr.dao.DeptBuyMainDao;
import gyqx.spdherp.applyMgr.dao.DeptBuySubDao;
import gyqx.spdherp.applyMgr.service.IDeptBuySubService;
import gyqx.spdherp.applyMgr.vo.DeptBuySubVo;
import gyqx.spdherp.applyMgr.vo.ProvGoodsKindsVo;
import gyqx.spdherp.po.DeptBuyMain;
import gyqx.spdherp.po.DeptBuySub;
import gyqx.spdherp.provManager.service.IDistriRelationService;
import gyqx.spdherp.provManager.vo.HosCollectorProvVo;

@Service
public class DeptBuySubService implements IDeptBuySubService {
	
	@Resource
	private DeptBuySubDao  dao;
	@Resource
	private DeptBuyMainDao  mainDao;
	@Resource
	private IDistriRelationService dRService;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	public DeptBuySub get(String id)
	{
		return dao.get( id);	
	}	

	public DeptBuySub add(DeptBuySub deptBuySub) throws Exception 
	{
		return dao.insertAndGet(deptBuySub);
	}
		
	public DeptBuySub update(DeptBuySub deptBuySub) throws Exception {
		return dao.updateAndGet(deptBuySub);
	}
	public int updateFields(DeptBuySub deptBuySub,String fieldNames) throws Exception
	{
		return dao.updateFields(deptBuySub, fieldNames);
	}

	public void delete(DeptBuySub deptBuySub) throws Exception {
		dao.delete(deptBuySub);
	}
	
	public DeptBuySub getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (DeptBuySub) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<DeptBuySub> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<DeptBuySub>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<DeptBuySub> list(DeptBuySub bean) throws Exception
	{
		return (List<DeptBuySub>) dao.list(bean);
	}	
	
	public List<DeptBuySubVo> listVo(DeptBuySubVo bean) throws Exception
	{
		return (List<DeptBuySubVo>) dao.getVoList(bean);
	}	
	
	public QueryResult<DeptBuySub> listByPage(QueryInfo<DeptBuySub> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}
	public QueryResult<DeptBuySubVo> listVoByPage(QueryInfo<DeptBuySubVo> queryInfo) throws Exception{
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.getVoList(queryInfo.getQueryObject()));
	}
	public List<DeptBuySubVo> getDetailsVo4Pur(DeptBuySubVo queryInfo)  throws Exception{
	     return dao.getDetailsVo4Pur(queryInfo);
	}
	
	public List<DeptBuySubVo> getDetailsVo4Merger(DeptBuySubVo queryInfo)  throws Exception{
	     return dao.getDetailsVo4Merger(queryInfo);
	}
    public  void updateBill4Pur( List<DeptBuySubVo> voList) throws Exception{
    	for(DeptBuySubVo vo:voList){	
    	
    	//	DeptBuySub sub =dao.getByFieldName("id", vo.getId());//sub表ID
    		 DeptBuySub sub = new DeptBuySub();
    		  sub.setBillId(vo.getBillId());
    		  sub.setRowNumber(vo.getRowNumber());
    		  List<DeptBuySub> res = dao.list(sub);
    		 // if(res.size()>0)
    			  sub = res.get(0);
    		sub.setSubState(vo.getSubState()); //10 待处理 20已驳回 30 不采购  40部分配送  50全部配送  60 已采购
    		sub.setWarehouseDealMan(vo.getWarehouseDealMan());
    		sub.setWarehouseDealTime(new Date());
    		sub.setWhQty(new BigDecimal(0)); //采购 只有 驳回 和不采购两种操作才调用该方法  
    		
    		if(Constance.DEPTAPPLAY_SUB_STATUS_PURCHAED.equals(vo.getSubState())){  //回写采购数量  DEPTAPPLAY_SUB_STATUS_PURCHAED 60
    			sub.setWhQty(vo.getWhQty());		    
    		}
    		dao.updateAndGet(sub);
    		//修改主表信息
    		DeptBuyMain main =	mainDao.getByFieldName("billId", vo.getBillId());
    		main.setAuditor(vo.getWarehouseDealMan());
    		main.setAuditDate(new Date());
    		if(Constance.DEPTAPPLAY_SUB_STATUS_NOTPURCHASE.equals(vo.getSubState()) ||Constance.DEPTAPPLAY_SUB_STATUS_REJECTED.equals(vo.getSubState())){  // 20 已驳回 
    			main.setWhSumrow(main.getWhSumrow()-1);
    		}
    		main.setState(checkMainState(main,sub)); //主表状态 10 已保存（科室内） 20已提交（科室内）   30 处理中 40 已处理
    		
			if(main.getState()==Constance.DEPTAPPLAY_MAIN_STATUS_HANDLED)  {//40//如果是正常结束，也需要将主表的ifClosed 进行关闭
				main.setIfClosed(1);
			}   
    		mainDao.updateAndGet(main);
    	}
    }
    
   public DeptBuySub updateBill4OutStock(DeptBuySub sub) throws Exception{
	      //查询mian
		DeptBuyMain main =	mainDao.getByFieldName("billId", sub.getBillId());
		main.setState(checkMainState(main,sub));
		
		if(main.getState()==Constance.DEPTAPPLAY_MAIN_STATUS_HANDLED)  {//40//如果是正常结束，也需要将主表的ifClosed 进行关闭
			main.setIfClosed(1);
		}   
		mainDao.updateAndGet(main);
		return sub;
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
        List<DeptBuySub> subs =	dao.list(qbean);
    	for(DeptBuySub s:subs){
    		if (s.getId() == nowSub.getId() ){
    			if(Constance.DEPTAPPLAY_SUB_STATUS_PENDING==nowSub.getSubState()||Constance.DEPTAPPLAY_SUB_STATUS_PARTDISTRED==nowSub.getSubState()||Constance.DEPTAPPLAY_SUB_STATUS_PURCHAED==nowSub.getSubState()||Constance.DEPTAPPLAY_SUB_STATUS_NOTPURCHASE==nowSub.getSubState()){//10 待处理 20已驳回 30 不采购  40部分配送  50全部配送 60 yi'cai'g
    				flag =false;
    				break;
    			}

    		}
    		else{
    			if(Constance.DEPTAPPLAY_SUB_STATUS_PENDING==s.getSubState()||Constance.DEPTAPPLAY_SUB_STATUS_PARTDISTRED==s.getSubState()||Constance.DEPTAPPLAY_SUB_STATUS_PURCHAED==s.getSubState()||Constance.DEPTAPPLAY_SUB_STATUS_NOTPURCHASE==s.getSubState()){//10 待处理 20已驳回 30 不采购  40部分配送  50全部配送
    				 flag = false;
    				 break;
    			 }
    		}
    	}
    	if(flag)
    		re= Constance.DEPTAPPLAY_MAIN_STATUS_HANDLED;//40
    	return re;
    }
    
    
    public int updateMainStateByBillId(String  billId) throws Exception{
    	DeptBuyMain main =mainDao.get(billId);
    	int re = Constance.DEPTAPPLAY_MAIN_STATUS_HANDLING; // 30 处理中
    	boolean flag = true;
    	//查询所有sub
    	DeptBuySub qbean = new DeptBuySub();
    	qbean.setBillId(main.getBillId());
        List<DeptBuySub> subs =	dao.list(qbean);
    	for(DeptBuySub s:subs){
    		
    			if(Constance.DEPTAPPLAY_SUB_STATUS_PENDING==s.getSubState()||Constance.DEPTAPPLAY_SUB_STATUS_PARTDISTRED==s.getSubState()||Constance.DEPTAPPLAY_SUB_STATUS_PURCHAED==s.getSubState()||Constance.DEPTAPPLAY_SUB_STATUS_NOTPURCHASE==s.getSubState()){//10 待处理 20已驳回 30 不采购  40部分配送  50全部配送
    				 flag = false;
    				 break;
    			
    		}
    	}
    	if(flag)
    		re= Constance.DEPTAPPLAY_MAIN_STATUS_HANDLED;//40
    	
    	if(re == Constance.DEPTAPPLAY_MAIN_STATUS_HANDLED ){
    		main.setState(Constance.DEPTAPPLAY_MAIN_STATUS_HANDLED);  		
    		main.setIfClosed(1);						//40//如果是正常结束，也需要将主表的ifClosed 进行关闭
    		mainDao.updateAndGet(main);
    	}
    	return re;
    }
    
	public List<ProvGoodsKindsVo> getProvQGList(DeptBuySubVo deptBuyBillVo) throws Exception {
		//获取当前登陆人,根据人员与供应商关系查询供应商信息
		String userId = userOnlineStateUtils.getCurrent().getUserId();
		HosCollectorProvVo hcp = new HosCollectorProvVo();
		hcp.setUserId(userId);
		hcp.setHosId(deptBuyBillVo.getHosId());
		List<HosCollectorProvVo> lstProv = dRService.getHosColProvs(hcp);
		List<ProvGoodsKindsVo> lstPgoods = dao.getProvGoodsKinds(deptBuyBillVo);
		List<ProvGoodsKindsVo> lstRes = new ArrayList<>();
		for (ProvGoodsKindsVo pgoodsKind : lstPgoods) {
			int index = lstProv.indexOf(pgoodsKind);
			if(index >= 0){
				HosCollectorProvVo hosCP = lstProv.get(index);
				//if(hosCP.getIsUseSubprov().equals(0)){				//启用了二级供应商管理
				if(hosCP.getIsUseSubprov() == 1){				//启用了二级供应商管理
					pgoodsKind.setProvName(hosCP.getProvName());
					List<String> subProvs = new ArrayList<>();
					subProvs.add(hosCP.getProvId());
                    pgoodsKind.setSubProvId(subProvs);
					pgoodsKind.setColId(hosCP.getCollectorId());
					pgoodsKind.setColName(hosCP.getColName());
					lstRes.add(pgoodsKind);
				}else {														//没启用二级供应商管理
                    int cIndex = lstRes.indexOf(hosCP);//查找集配商
                    if(cIndex>=0){		//存在集配商 加数量
                        ProvGoodsKindsVo cpk = lstRes.get(cIndex);
                        cpk.getSubProvId().add(hosCP.getProvId());
                        cpk.setGoodsKinds(cpk.getGoodsKinds() + pgoodsKind.getGoodsKinds());
                    }else{				//不存在集配商则先添加集配商再加数量
                        ProvGoodsKindsVo cPk = new ProvGoodsKindsVo();
                        cPk.setProvId(hosCP.getCollectorId());
                        cPk.setProvName(hosCP.getColName());
                        cPk.setGoodsKinds(cPk.getGoodsKinds() + pgoodsKind.getGoodsKinds());
                        List<String> subProvs = new ArrayList<>();
                        subProvs.add(hosCP.getProvId());
                        cPk.setSubProvId(subProvs);
						cPk.setColId(hosCP.getCollectorId());
						cPk.setColName(hosCP.getColName());
                        lstRes.add(cPk);
                    }
				}
			}
		}
		return lstRes;
	}

	@Override
	public List<DeptBuySubVo> getVoListByOut(String id) {
		// TODO Auto-generated method stub
		return dao.getVoListByOut(id);
	}
}


