package gyqx.spdherp.stockout.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.po.OutStockUniqueCode;
import gyqx.spdherp.po.SickerUseList;
import gyqx.spdherp.stockout.vo.SickerUserListVo;
import gyqx.spdherp.stockout.vo.SickerUserVo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryResult;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.stockout.dao.mapper.ISickerUseListMapper;


@Repository
public class SickerUseListDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private ISickerUseListMapper mapper;
	public SickerUserListVo get(String id){
		return mapper.get(id);
	}
	public List<SickerUserListVo> getListByBillId(String billId) throws Exception{
		return mapper.getListByBillId(billId);
	}
	public int insertByBatch(List<SickerUserListVo> lst)throws Exception{
		return mapper.insertByBatch(lst);
	}
	public int updateByBatch(List<SickerUserListVo> lst)throws Exception{
		return mapper.updateByBatch(lst);
	}
	public int update(SickerUserListVo bean)throws Exception{
		return mapper.update(bean);
	}
	public int insert(SickerUserListVo bean)throws Exception{
		return mapper.insert(bean);
	}
	public int delByBillId(@Param("billId") String billId)throws Exception{
		return mapper.delByBillId(billId);
	}
	public int delById(@Param("id") String id)throws Exception{
		return mapper.delById(id);
	}
	public List<SickerUserListVo> list(SickerUserListVo qbean) throws Exception{
		return mapper.list(qbean);
	}
	public QueryResult<SickerUserVo> getSickerUseListById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public int update4Return(List<SickerUserListVo> lst) throws Exception{
		return mapper.update4Return(lst);
	}
}


