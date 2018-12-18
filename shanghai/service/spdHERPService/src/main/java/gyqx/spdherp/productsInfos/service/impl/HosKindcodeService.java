package gyqx.spdherp.productsInfos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.utils.PageUtils;
import gyqx.spdhdi.po.HosKindcode;
import gyqx.spdherp.productsInfos.dao.HosKindcodeDao;
import gyqx.spdherp.productsInfos.service.IHosKindcodeService;

@Service
public class HosKindcodeService implements IHosKindcodeService {
	
	@Resource
	private HosKindcodeDao  dao;
	@Resource
	private SimpleDao simpleDao;

	public HosKindcode get(String id)
	{
		return dao.get( id);	
	}	

	public HosKindcode add(HosKindcode hosKindcode) throws Exception 
	{
		 //查询该父分类下是否有有效商品，有则不允许添加子分类 根节点为“商品分类 id =null”  
		if(StringUtils.hasText(hosKindcode.getPId())){
			   long  num=	simpleDao.queryForObject("select count(*) from hos_goods_info  where flag=1 and hos_id=? and lbsx =?", Long.class,hosKindcode.getHosId(),hosKindcode.getPId() );
			   if(num>0){
			        ValidateException.Throw("kindName",  "分类下有商品，不能添加子分类！",hosKindcode.getKindName() );
				}
		}
		
		return dao.insertAndGet(hosKindcode);
	}
		
	public HosKindcode update(HosKindcode hosKindcode) throws Exception {
		//先查询
		HosKindcode temp =  dao.get( hosKindcode.getId());
		hosKindcode.setVersion(temp.getVersion());
		hosKindcode.setPId(temp.getPId());
		//如果是 停用(10 启用 20停用)还需判断分类下是否有flag= 1 的有效商品，如有 不能停用
		if(hosKindcode.getState()==20){
			  /* long  num=	simpleDao.queryForObject("select count(*) from hos_goods_info where  flag=1 and  lbsx =? ", Long.class, hosKindcode.getId());
			   if(num>0){
			        ValidateException.Throw("kindName", hosKindcode.getKindName()  +  "下存在有效商品，不能停用！",hosKindcode.getKindName() );
				}*/
			   long  num=	simpleDao.queryForObject("select count(*) from hos_goods_info where lbsx in (select id from hos_kindcode where  flag=1  and level_code like ?) ", Long.class, "%"+hosKindcode.getId()+"%");
			   if(num>0){
			        ValidateException.Throw("kindName", hosKindcode.getKindName()  +  "或其子节点下存在有效商品，不能停用！",hosKindcode.getKindName() );
				}
		}
		
		return dao.updateAndGet(hosKindcode);
	}
	public int updateFields(HosKindcode hosKindcode,String fieldNames) throws Exception
	{
		return dao.updateFields(hosKindcode, fieldNames);
	}

	public void delete(HosKindcode hosKindcode) throws Exception {
		//查询所有孩子下是否有有效商品，由则不能删除
		
		 long  num=	simpleDao.queryForObject("select count(*) from hos_goods_info where lbsx in (select id from hos_kindcode where  flag=1  and level_code like ?) ", Long.class, "%"+hosKindcode.getId()+"%");
		   if(num>0){
		        ValidateException.Throw("kindName", hosKindcode.getKindName()  +  "或其子节点下存在有效商品，不能停用！",hosKindcode.getKindName() );
			}
		
		 
		dao.delete(hosKindcode);
	}
	
	public HosKindcode getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (HosKindcode) dao.getByFieldName(fieldName, fieldValue);
	}
	
	public List<HosKindcode> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<HosKindcode>) dao.query(predicate,orderBy,fieldValue);
	}	
	
	public List<HosKindcode> list(HosKindcode bean) throws Exception
	{
		return (List<HosKindcode>) dao.list(bean);
	}	
	
	
	public QueryResult<HosKindcode> listByPage(QueryInfo<HosKindcode> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


