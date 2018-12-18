package gyqx.ws.yg.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.ws.yg.vo.repVo.YY154_REP_DETAIL;

public interface IYgSendBillService 
{

	YY154_REP_DETAIL get(String psdbh) throws Exception;
	int update(YY154_REP_DETAIL bean) throws Exception;
	int insert(YY154_REP_DETAIL bean) throws Exception;
	int insertByBatch(List<YY154_REP_DETAIL> lst) throws Exception;
	List<YY154_REP_DETAIL> list(YY154_REP_DETAIL queryInfo) throws Exception;
	boolean checkExists(String psdbh) throws Exception;
	QueryResult<YY154_REP_DETAIL> listByPage(QueryInfo<YY154_REP_DETAIL> queryInfo) throws Exception;

}


