package gyqx.ws.yg.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.ws.yg.dao.mapper.IYgSendBillListStateMapper;
import gyqx.ws.yg.vo.repVo.YY161_REP_DETAIL;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class YgSendBillListStateDao 
{

	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IYgSendBillListStateMapper mapper;
	public YY161_REP_DETAIL get(String psmxbh) throws Exception{
		return mapper.get(psmxbh);
	}
	public int updateByBatch(List<YY161_REP_DETAIL> lst) throws Exception{
		return mapper.updateByBatch(lst);
	}
	public int  insertByBatch(List<YY161_REP_DETAIL> lst) throws Exception{
		return mapper.insertByBatch(lst);
	}
	public int  update(YY161_REP_DETAIL bean) throws Exception{
		return mapper.update(bean);
	}
	public int  insert(YY161_REP_DETAIL bean) throws Exception{
		return mapper.insert(bean);
	}

	public List<YY161_REP_DETAIL> list(YY161_REP_DETAIL qbean) throws Exception{
		
		return mapper.list(qbean);
	}
}


