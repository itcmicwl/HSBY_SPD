package gyqx.spdhdi.myInfos.dao;


import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdhdi.myInfos.dao.mapper.IProvHosDeptInfoCodeMapper;
import gyqx.spdhdi.myInfos.vo.ProvHosDeptInfoCodeVo;
import gyqx.spdhdi.myInfos.vo.SaleCodeInfoVo;
import gyqx.spdhdi.po.ProvHosDeptInfoCode;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ProvHosDeptInfoCodeDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IProvHosDeptInfoCodeMapper mapper;

	public ProvHosDeptInfoCode get(String id)
	{
		return dao.get(ProvHosDeptInfoCode.class, id);	
	}	

	public ProvHosDeptInfoCode insertAndGet(ProvHosDeptInfoCode provHosDeptInfoCode) throws Exception 
	{
		return dao.insertAndGet(provHosDeptInfoCode);
	}

	public ProvHosDeptInfoCode updateAndGet(ProvHosDeptInfoCode provHosDeptInfoCode) throws Exception {
		return dao.updateAndGet(provHosDeptInfoCode);
	}
	public int updateFields(ProvHosDeptInfoCode provHosDeptInfoCode,String fieldNames) throws Exception
	{
		return dao.updateFields(provHosDeptInfoCode, fieldNames);
	}
	public int updateNotNullFields(ProvHosDeptInfoCode provHosDeptInfoCode) throws Exception
	{
		return dao.updateNotNullFields(provHosDeptInfoCode);
	}

	public void delete(ProvHosDeptInfoCode provHosDeptInfoCode) throws Exception {
		dao.delete(provHosDeptInfoCode);
	}
	
	public ProvHosDeptInfoCode getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (ProvHosDeptInfoCode) dao.getByFieldName(ProvHosDeptInfoCode.class, fieldName, fieldValue);
	}
	
	public List<ProvHosDeptInfoCode> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<ProvHosDeptInfoCode>) dao.queryBeanList(ProvHosDeptInfoCode.class,predicate,orderBy,fieldValue);
	}
	public long count(String predicate,Object...fieldValue) throws Exception
	{
		return dao.count(ProvHosDeptInfoCode.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception
	{
		return dao.updateVersion(bean);
	}
	public List<ProvHosDeptInfoCode> list(ProvHosDeptInfoCode qbean) throws Exception{		
		
		return mapper.list(qbean);
	}
	public List<ProvHosDeptInfoCodeVo> list_PH_dept_code_vo(ProvHosDeptInfoCodeVo qbean) throws Exception{

		return mapper.list_PH_dept_code_vo(qbean);
	}

	public SaleCodeInfoVo getSaleCodeInfo(SaleCodeInfoVo vo) {
		return mapper.getSaleCodeInfo(vo);
	}
	
    /**
     * 根据医院id，医院部门id，供应商id查询数据
     * @param queryInfo 
     * @return
     */
    public List<ProvHosDeptInfoCode> getProvHosDeptInfoCode(ProvHosDeptInfoCode queryInfo) {
    	return mapper.getProvHosDeptInfoCode(queryInfo);
    }
}


