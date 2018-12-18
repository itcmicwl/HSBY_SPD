package gyqx.ws.yg.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.ws.yg.vo.repVo.YY131_REP_DETAIL;
import gyqx.ws.yg.vo.repVo.YY155_REP_DETAIL;

public interface IYgSendBillListService 
{

	YY155_REP_DETAIL get(String psmxbh)throws Exception;
	int update(YY155_REP_DETAIL bean)throws Exception;
	int updateByBatch(List<YY155_REP_DETAIL> lst)throws Exception;
	int insert(YY155_REP_DETAIL bean)throws Exception;
	int insertByBatch(List<YY155_REP_DETAIL> lst)throws Exception;
	List<YY155_REP_DETAIL> list(YY155_REP_DETAIL queryInfo) throws Exception;
	List<YY155_REP_DETAIL> list(String billId) throws Exception;
	QueryResult<YY155_REP_DETAIL> listByPage(QueryInfo<YY155_REP_DETAIL> queryInfo) throws Exception;

}


