package gyqx.spdherp.applyMgr.service.impl;

import javax.annotation.Resource;

import freemarker.template.utility.StringUtil;
import gyqx.spdherp.applyMgr.vo.DeptCarSurGoodsStVo;
import gyqx.spdherp.po.SurgeryPkgDefList;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import java.util.stream.Collectors;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import gyqx.spdherp.po.DeptBuyCar;
import gyqx.spdherp.applyMgr.dao.DeptBuyCarDao;
import gyqx.spdherp.applyMgr.service.IDeptBuyCarService;
import gyqx.spdherp.applyMgr.vo.DeptCarGoodsStVo;
import org.springframework.util.StringUtils;

@Service
public class DeptBuyCarService implements IDeptBuyCarService {
	
	@Resource
	private DeptBuyCarDao  dao;
	@Resource
	private SimpleDao simpleDao;
	public DeptBuyCar get(String id)
	{
		return dao.get( id);	
	}

	public DeptBuyCar add(DeptBuyCar deptBuyCar) throws Exception
	{
		//如果 购物车里有记录  hosId deptId  goods_id  一致，则 将need_qty 累加 进行update
		//DeptBuyCar   temp=	simpleDao.queryForObject("select * from dept_buy_car where hos_id = ? and dept_id = ? and goods_id =?  ", DeptBuyCar.class, deptBuyCar.getHosId(),deptBuyCar.getDeptId(),deptBuyCar.getGoodsId());
	//加入 surId 条件
		List temps = null;
       if(StringUtils.isEmpty(deptBuyCar.getSurId())){
		   temps  =	  simpleDao.queryForList("select * from dept_buy_car where ISNULL(sur_id) and hos_id = ? and dept_id = ? and goods_id =?  ",DeptBuyCar.class,deptBuyCar.getHosId(),deptBuyCar.getDeptId(),deptBuyCar.getGoodsId());
		  // temps  =	simpleDao.queryByFieldNames( DeptBuyCar.class, "hosId,deptId,goodsId,surId", deptBuyCar.getHosId(),deptBuyCar.getDeptId(),deptBuyCar.getGoodsId(),null);
	   }
	   else
		   temps  =	simpleDao.queryByFieldNames( DeptBuyCar.class, "hosId,deptId,goodsId,surId", deptBuyCar.getHosId(),deptBuyCar.getDeptId(),deptBuyCar.getGoodsId(),deptBuyCar.getSurId());

		if(temps.size()>0){
			deptBuyCar.setNeedQty(deptBuyCar.getNeedQty()+((DeptBuyCar)temps.get(0)).getNeedQty());
			deptBuyCar.setId(((DeptBuyCar)temps.get(0)).getId());
			deptBuyCar.setVersion(((DeptBuyCar)temps.get(0)).getVersion());
		       return   dao.updateAndGet(deptBuyCar);
			}
		else return dao.insertAndGet(deptBuyCar);
	}
	public void addList(List<DeptBuyCar> cars) throws Exception {
		cars.forEach(c->{
			try {
				this.add(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	public void addList4mixSur(List<DeptBuyCar> cars) throws Exception {
		cars.forEach(c->{
			try {
				this.add(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public DeptBuyCar update(DeptBuyCar deptBuyCar) throws Exception {
		return dao.updateAndGet(deptBuyCar);
	}
	public int updateFields(DeptBuyCar deptBuyCar,String fieldNames) throws Exception
	{
		return dao.updateFields(deptBuyCar, fieldNames);
	}

	public void delete(DeptBuyCar deptBuyCar) throws Exception {
		dao.delete(deptBuyCar);
	}
	
	public void deleteCars(List<DeptBuyCar> cars) throws Exception {

		List<DeptBuyCar> car4hl = cars.stream().filter(c -> StringUtils.isEmpty(c.getSurId())).collect(Collectors.toList());
		List<DeptBuyCar> car4sur =cars.stream().filter(c -> !StringUtils.isEmpty(c.getSurId())).collect(Collectors.toList());
		for(DeptBuyCar c : car4hl){
			//simpleDao.executeSql("delete from dept_buy_car where sur_id is null and goods_id = ? and hos_id =? and dept_id =?" ,c.getGoodsId(),c.getHosId(),c.getDeptId());
			simpleDao.executeSql("delete from dept_buy_car where id =?" ,c.getId());

		}
		for(DeptBuyCar c : car4sur){
			simpleDao.executeSql("delete from dept_buy_car where sur_id =?  and hos_id =? and dept_id =?",c.getSurId(),c.getHosId(),c.getDeptId());
		}
		/*for(DeptBuyCar c:cars){
			c = dao.get(c.getId());
			dao.delete(c);
		}*/
	}
	
	
	public DeptBuyCar getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (DeptBuyCar) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<DeptBuyCar> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<DeptBuyCar>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<DeptBuyCar> list(DeptBuyCar bean) throws Exception
	{
		return (List<DeptBuyCar>) dao.list(bean);
	}	
	
	public QueryResult<DeptBuyCar> listByPage(QueryInfo<DeptBuyCar> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

	public QueryResult<DeptCarGoodsStVo> listByPage_deptCarGoodsStlist(QueryInfo<DeptCarGoodsStVo> queryInfo) throws Exception{
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.deptCarGoodsStlist(queryInfo.getQueryObject()));
	}
	
	public List<DeptCarGoodsStVo> list_deptCarGoodsStlist(DeptCarGoodsStVo bean) throws Exception{
		return  dao.deptCarGoodsStlist(bean);
		
	}

	public QueryResult<DeptCarSurGoodsStVo> listByPage_deptCarGoodsStlist4sur(QueryInfo<DeptCarGoodsStVo> queryInfo) throws Exception{
		  PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.deptCarGoodsStlist4sur(queryInfo.getQueryObject()));

	}

	public String updateSurQty(DeptCarSurGoodsStVo row) throws Exception{
		String  deptId= row.getSurDefGoodsLst().get(0).getDeptId();
		String sql ="UPDATE dept_buy_car c join surgery_pkg_def_list d on (c.hos_id=d.hos_id and c.sur_id=d.sur_id and c.goods_id=d.goods_id) \n" +
					" set c.need_qty=d.qty*?   where  c.dept_id =? and c.sur_id=? and c.hos_id=?";
		simpleDao.executeSql(sql,row.getNeedSurPkgQty(),deptId,row.getSurId(),row.getHosId());
		return "ok";
	}


}


