package gyqx.spdherp.applyMgr.dao;


import java.util.List;
import javax.annotation.Resource;

import common.utils.*;
import common.web.UserOnlineInfo;
import gyqx.platform.sys.vo.SysOrgVo;
import gyqx.spdherp.applyMgr.vo.ApplySurPkgVo;
import gyqx.spdherp.applyMgr.vo.ProvGoodsKindsVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;

import gyqx.spdherp.applyMgr.dao.mapper.IDeptBuyMainMapper;
import gyqx.spdherp.applyMgr.vo.DeptBuyBillVo;
import gyqx.spdherp.po.DeptBuyMain;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class DeptBuyMainDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IDeptBuyMainMapper mainMapper;

	@Resource
    private UserOnlineStateUtils userOnlineStateUtils;


	public DeptBuyMain get(String id)
	{
		return dao.get(DeptBuyMain.class, id);	
	}	

	public DeptBuyMain insertAndGet(DeptBuyMain deptBuyMain) throws Exception 
	{
		return dao.insertAndGet(deptBuyMain);
	}

	public DeptBuyMain updateAndGet(DeptBuyMain deptBuyMain) throws Exception {
		return dao.updateAndGet(deptBuyMain);
	}
	public int updateFields(DeptBuyMain deptBuyMain,String fieldNames) throws Exception
	{
		return dao.updateFields(deptBuyMain, fieldNames);
	}
	public int updateNotNullFields(DeptBuyMain deptBuyMain) throws Exception
	{
		return dao.updateNotNullFields(deptBuyMain);
	}

	public void delete(DeptBuyMain deptBuyMain) throws Exception {
		dao.delete(deptBuyMain);
	}
	
	public DeptBuyMain getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (DeptBuyMain) dao.getByFieldName(DeptBuyMain.class, fieldName, fieldValue);
	}
	
	public List<DeptBuyMain> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<DeptBuyMain>) dao.queryBeanList(DeptBuyMain.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(DeptBuyMain.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<DeptBuyMain> list(DeptBuyMain qbean) throws Exception{		
		
		return mainMapper.list(qbean);
	}
	
	public List<DeptBuyBillVo> getVoList(DeptBuyBillVo queryInfo) throws Exception{
        UserOnlineInfo user = userOnlineStateUtils.getCurrent();
        return mainMapper.getMainBillVoList(queryInfo, user.getUserId(), user.getCorpId());
	}
	public List<DeptBuyBillVo> getMainBillVo4pur(DeptBuyBillVo queryInfo) throws Exception{
		return mainMapper.getMainBillVo4pur(queryInfo);
	}
	public List<DeptBuyBillVo> getMainBillVo4merger(DeptBuyBillVo queryInfo) throws Exception{
		return mainMapper.getMainBillVo4merger(queryInfo);
	}
	public List<SysOrgVo> getBuyDeptList(DeptBuyBillVo queryInfo) throws Exception{
		return mainMapper.getBuyDeptList(queryInfo);
	}
	public List<DeptBuyBillVo> getUnPackApplyBIllList(DeptBuyBillVo query) throws Exception{
		return mainMapper.getUnPackApplyBIllList(query);
	}
	public DeptBuyBillVo getUnPackApplyBIll(String billId) throws Exception{
		return mainMapper.getUnPackApplyBIll(billId);
	}
	public ApplySurPkgVo getPackApplyBill(String billId) throws Exception{
		return mainMapper.getPackApplyBill(billId);
	}
}


