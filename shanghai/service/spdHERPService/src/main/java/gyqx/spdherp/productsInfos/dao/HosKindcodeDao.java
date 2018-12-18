package gyqx.spdherp.productsInfos.dao;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdhdi.po.HosKindcode;
import gyqx.spdherp.productsInfos.dao.mapper.IHosKindcodeMapper;

@Repository
public class HosKindcodeDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IHosKindcodeMapper mapper;

	public HosKindcode get(String id)
	{
		return dao.get(HosKindcode.class, id);	
	}	

	public HosKindcode insertAndGet(HosKindcode hosKindcode) throws Exception 
	{
	   //添加时处理code 
		String id = hosKindcode.getHosId()+atomUtil.newValue("hos-kindCode-id");
		hosKindcode.setId(id);  
		//处理层级关系
	/*	HosKindcode parent = new  HosKindcode();
		parent = get(hosKindcode.getPId());*/
		if(hosKindcode.getPId()==null){
			hosKindcode.setLevelCode(hosKindcode.getHosId()+":"+id);
		}else
		hosKindcode.setLevelCode(hosKindcode.getLevelCode()+"."+id);
		
		return dao.insertAndGet(hosKindcode);
	}
 
	public HosKindcode updateAndGet(HosKindcode hosKindcode) throws Exception {
		
		return dao.updateAndGet(hosKindcode);
	}
	public int updateFields(HosKindcode hosKindcode,String fieldNames) throws Exception
	{
		return dao.updateFields(hosKindcode, fieldNames);
	}
	public int updateNotNullFields(HosKindcode hosKindcode) throws Exception
	{
		return dao.updateNotNullFields(hosKindcode);
	}

	public void delete(HosKindcode hosKindcode) throws Exception {
		//dao.delete(hosKindcode);
		mapper.delete(hosKindcode);
	}
	
	public HosKindcode getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (HosKindcode) dao.getByFieldName(HosKindcode.class, fieldName, fieldValue);
	}
	
	public List<HosKindcode> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<HosKindcode>) dao.queryBeanList(HosKindcode.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(HosKindcode.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<HosKindcode> list(HosKindcode qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
	
}


