package gyqx.spdherp.spdOutPtl.service.impl;

import javax.annotation.Resource;

import common.db.SimpleDao;
import common.transform.Tx;
import common.utils.UtilsContext;
import common.web.UserOnlineInfo;
import gyqx.spdherp.po.JdeGoodsStockInfo;
import gyqx.spdherp.po.SpdInventoryList;
import gyqx.spdherp.po.SpdOutPtl;
import gyqx.spdherp.spdOutPtl.dao.JdeGoodsStockInfoDao;
import gyqx.spdherp.spdOutPtl.dao.SpdInventoryDao;
import gyqx.spdherp.spdOutPtl.dao.SpdInventoryListDao;
import gyqx.spdherp.spdOutPtl.dao.SpdOutPtlDao;
import gyqx.spdherp.spdOutPtl.service.ISpdInventoryService;
import gyqx.spdherp.spdOutPtl.service.ISpdOutPtlService;
import gyqx.spdherp.spdOutPtl.vo.DeptBuyForSpdPtl;
import gyqx.spdherp.spdOutPtl.vo.SpdInventoryVo;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.SpdInventory;

@Service
public class SpdInventoryService implements ISpdInventoryService {
	
	@Resource
	private SpdInventoryDao dao;

	@Resource
	private SpdOutPtlDao spdOutPtlDao;

	@Resource
	private UtilsContext utilsContext;
	@Resource
	private JdeGoodsStockInfoDao jdeGoodsStockInfoDao;
	@Resource
	private ISpdOutPtlService iSpdOutPtlService;
	@Resource
	private SpdInventoryListDao spdInventoryListDao;
	@Resource
	private SimpleDao simpleDao;
	
	public SpdInventory get(String id)
	{
		return dao.get( id);	
	}	

	public SpdInventoryVo add(SpdInventoryVo spdInventoryVo) throws Exception
	{
		spdInventoryVo.setHosId(spdInventoryVo.getHosId());
		spdInventoryVo.setHosName(spdInventoryVo.getHosName());
		String id = utilsContext.getSysAtomUtil().newValue("spd_inventory_id");
		spdInventoryVo.setId(id);
		spdInventoryVo.setOperationer(spdInventoryVo.getOperationer());
		spdInventoryVo.setOperationtime(new Date());
		spdInventoryVo.setLastUpdateDatetime(new Date());
		spdInventoryVo.setState("10");
		spdInventoryVo.setVersion(0);
		SpdInventory spdInventory = new SpdInventory();
		Tx.transform(spdInventoryVo).to(spdInventory);
		dao.insertAndGet(spdInventory);
		//查询所有JDE视图库存信息
		spdInventoryVo.setSpdInventoryLists(spdInventoryListDao.getSpdInventoryList(spdInventoryVo.getHosId()));
		List<SpdInventoryList> spdInventoryLists = spdInventoryVo.getSpdInventoryLists();
		List<SpdOutPtl> spdOutPtlList = new ArrayList<>();
		for(int i = 0;i<spdInventoryLists.size();i++){
			spdInventoryLists.get(i).setId(UUID.randomUUID().toString().replace("-", ""));
			spdInventoryLists.get(i).setPid(id);
			spdInventoryLists.get(i).setLastUpdateDatetime(new Date());
			spdInventoryLists.get(i).setRowNum(i+1);
			spdInventoryLists.get(i).setVersion(1);
			if(!spdInventoryLists.get(i).getGoodsStockLocation().equals("合格品")){
				spdInventoryLists.get(i).setGoodsStockLocation(spdInventoryLists.get(i).getGoodsStockLocation().substring(0,11));
			}

			SpdOutPtl spdOutPtl = new SpdOutPtl();
			spdOutPtl.setId(UUID.randomUUID().toString().replace("-", "")+spdInventoryLists.get(i).getRowNum());
			spdOutPtl.setSerialno(spdInventoryLists.get(i).getRowNum());
			spdOutPtl.setOrderid(spdInventoryLists.get(i).getPid());
			spdOutPtl.setLocation(spdInventoryLists.get(i).getGoodsStockLocation());
			spdOutPtl.setName(spdInventoryLists.get(i).getGoodsName());
			spdOutPtl.setBarcode(spdInventoryLists.get(i).getErpCode());
			spdOutPtl.setSpecification(spdInventoryLists.get(i).getGoodsGg());
			spdOutPtl.setBatchnumber(spdInventoryLists.get(i).getBatchCode());
			spdOutPtl.setManufacturer(spdInventoryLists.get(i).getMfrsName());
			spdOutPtl.setQuantity(spdInventoryLists.get(i).getStockSupplyQty());
			spdOutPtl.setUnit(spdInventoryLists.get(i).getUnit());
			spdOutPtl.setState("1");
			spdOutPtl.setDowndate(new Date());
			spdOutPtl.setTasktype("盘点任务");
			spdOutPtl.setLastUpdateDatetime(new Date());
			spdOutPtl.setVersion(0);
			spdOutPtlList.add(spdOutPtl);
		}
		if(dao.insertBatchSpdInvertory(spdInventoryLists)==0){
			throw new Exception("插入盘点单子表失败");
		}
		if(spdOutPtlDao.insertSpdOutPtlLists(spdOutPtlList)==0){
			throw new Exception("插入电子标签中间表失败！");
		}
		return spdInventoryVo;
	}
		
	public String update(SpdInventory spdInventory) throws Exception {
		//批量更新子表盘点数量
		if(spdInventoryListDao.updateBatch(spdInventory.getId())>0){
			//更新成功后判断是否还有没有盘点的产品
			if(spdInventoryListDao.countState(spdInventory.getId())==0){
				//更新主表状态
				String sql = "UPDATE spd_inventory set state = '20',version = version + 1,last_update_datetime = now() where id = ?";
				if(simpleDao.executeSql(sql,spdInventory.getId())<=0){
					throw new Exception("更新盘点单主表失败！");
				}
			}
		}else {
			throw new Exception("更新盘点单子表盘点数量失败！");
		}
		return "ok";
	}
	public int updateFields(SpdInventory spdInventory,String fieldNames) throws Exception
	{
		return dao.updateFields(spdInventory, fieldNames);
	}

	public void delete(SpdInventory spdInventory) throws Exception {
		dao.delete(spdInventory);
	}
	
	public SpdInventory getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (SpdInventory) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<SpdInventory> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<SpdInventory>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<SpdInventoryVo> list(SpdInventoryVo bean) throws Exception
	{
		return (List<SpdInventoryVo>) dao.list(bean);
	}	
	
	public QueryResult<SpdInventoryVo> listByPage(QueryInfo<SpdInventoryVo> queryInfo) throws Exception{
		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

	@Override
	public String closeBill(String hosId) throws Exception {
		String id = dao.getId(hosId);
		SpdInventory spdInventory = new SpdInventory();
		spdInventory.setId(id);
		this.update(spdInventory);
		String sql = "update spd_inventory set state = '20',version = version + 1,last_update_datetime = now() where state='10' and hos_id = ?";
		simpleDao.executeSql(sql,hosId);
		return "ok";
	}
}


