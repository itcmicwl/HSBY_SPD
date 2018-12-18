package gyqx.ws.yg.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.ws.yg.dao.mapper.IYgSendBillMapper;
import gyqx.ws.yg.vo.repVo.YY154_REP_DETAIL;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class YgSendBillDao 
{

	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IYgSendBillMapper mapper;

	public YY154_REP_DETAIL get(String psdbh) throws  Exception{
		return mapper.get(psdbh);
	}
	public int update(YY154_REP_DETAIL bean) throws Exception{
		return mapper.update(bean);
	}

	public int  insert(YY154_REP_DETAIL bean) throws Exception{
		return mapper.insert(bean);
	}
	public int insertByBatch(List<YY154_REP_DETAIL> lst) throws Exception{
		return mapper.insertByBatch(lst);
	}
	public List<YY154_REP_DETAIL> list(YY154_REP_DETAIL qbean) throws Exception{
		return mapper.list(qbean);
	}
}


