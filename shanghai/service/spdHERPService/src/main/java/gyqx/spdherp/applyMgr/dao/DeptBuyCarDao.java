package gyqx.spdherp.applyMgr.dao;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import gyqx.spdherp.applyMgr.vo.DeptCarSurGoodsStVo;
import gyqx.spdherp.applyMgr.vo.SurGoods4carVo;
import gyqx.spdherp.po.SurgeryPkgDefList;
import org.springframework.stereotype.Repository;

import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdherp.applyMgr.dao.mapper.IDeptBuyCarMapper;
import gyqx.spdherp.applyMgr.vo.DeptCarGoodsStVo;
import gyqx.spdherp.po.DeptBuyCar;

@Repository
public class DeptBuyCarDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IDeptBuyCarMapper mapper;

	public DeptBuyCar get(String id)
	{
		return dao.get(DeptBuyCar.class, id);	
	}	

	public DeptBuyCar insertAndGet(DeptBuyCar deptBuyCar) throws Exception 
	{
		deptBuyCar.setId(atomUtil.newValue("dept-buy-car"));
		deptBuyCar.setLastUpdateDatetime(new Date());
		return dao.insertAndGet(deptBuyCar);
	}

	public DeptBuyCar updateAndGet(DeptBuyCar deptBuyCar) throws Exception {
		deptBuyCar.setLastUpdateDatetime(new Date());
		return dao.updateAndGet(deptBuyCar);
	}
	public int updateFields(DeptBuyCar deptBuyCar,String fieldNames) throws Exception
	{
		return dao.updateFields(deptBuyCar, fieldNames);
	}
	public int updateNotNullFields(DeptBuyCar deptBuyCar) throws Exception
	{
		return dao.updateNotNullFields(deptBuyCar);
	}

	public void delete(DeptBuyCar deptBuyCar) throws Exception {
		dao.delete(deptBuyCar);
	}
	
	public DeptBuyCar getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (DeptBuyCar) dao.getByFieldName(DeptBuyCar.class, fieldName, fieldValue);
	}
	
	public List<DeptBuyCar> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<DeptBuyCar>) dao.queryBeanList(DeptBuyCar.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(DeptBuyCar.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<DeptBuyCar> list(DeptBuyCar qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
	
    public List<DeptCarGoodsStVo> deptCarGoodsStlist(DeptCarGoodsStVo qbean) throws Exception{		
		
		return mapper.deptCarGoodsStlist(qbean);
	}
	public List<DeptCarSurGoodsStVo> deptCarGoodsStlist4sur(DeptCarGoodsStVo qbean) throws Exception{
		List<DeptCarSurGoodsStVo> lst = mapper.deptCarGoodsStlist4sur(qbean); //获取请购车中的手术包 定义
        //处理手术包数量及其商品
		lst.forEach(s->{

		List<SurGoods4carVo> surGoodsDefLst =	dao.queryForList("SELECT d.sur_id, d.goods_id,d.qty,v.goods_name,v.goods_gg,v.made,v.mfrs_name,v.unit FROM surgery_pkg_def_list d " +
				 " join v_pro_hos_goodsinfo v on  v.id = d.goods_id where d.sur_id=? and d.hos_id =?",SurGoods4carVo.class,s.getSurId(),qbean.getHosId());
			s.setSurDefGoodsLst(surGoodsDefLst);
		List<DeptBuyCar>  surDeptCarGoodsLst =dao.queryForList("select * from dept_buy_car c where c.sur_id=? and c.hos_id =? and c.dept_id=?",DeptBuyCar.class,s.getSurId(),qbean.getHosId(),qbean.getDeptId());
			surGoodsDefLst.get(0).getGoodsId();
			surGoodsDefLst.get(0).getQty();
			Optional<DeptBuyCar> carGoods=	surDeptCarGoodsLst.stream().filter(carGood->surGoodsDefLst.get(0).getGoodsId().equals(carGood.getGoodsId())).findFirst();
			int qty =carGoods.get().getNeedQty()/surGoodsDefLst.get(0).getQty();
			s.setNeedSurPkgQty(String.valueOf(qty));
		});
		return lst;
		}



}


