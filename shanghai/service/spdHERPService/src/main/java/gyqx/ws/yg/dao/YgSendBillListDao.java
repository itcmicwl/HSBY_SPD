package gyqx.ws.yg.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.ws.yg.dao.mapper.IYgSendBillListMapper;
import gyqx.ws.yg.vo.repVo.YY155_REP_DETAIL;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class YgSendBillListDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IYgSendBillListMapper mapper;

	public YY155_REP_DETAIL get(String psmxbh) throws Exception{
		return mapper.get(psmxbh);
	}
	public int update(YY155_REP_DETAIL bean)throws Exception{
		return mapper.update(bean);
	}
	public int updateByBatch(List<YY155_REP_DETAIL> lst)throws Exception{
		return mapper.updateByBatch(lst);
	}
	public int insert(YY155_REP_DETAIL bean)throws Exception{
		return mapper.insert(bean);
	}
	public int insertByBatch(List<YY155_REP_DETAIL> lst)throws Exception{
		return mapper.insertByBatch(lst);
	}

	public List<YY155_REP_DETAIL> list(YY155_REP_DETAIL qbean) throws Exception{
		
		return mapper.list(qbean);
	}
}


