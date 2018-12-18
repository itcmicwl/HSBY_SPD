package gyqx.ws.yg.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.ws.yg.vo.repVo.YY131_REP_DETAIL;
import gyqx.ws.yg.vo.repVo.YY161_REP_DETAIL;
import gyqx.ws.yg.vo.repVo.YY161_REP_XML;
import gyqx.ws.yg.vo.reqVo.YY131_REQ_DETAIL;

public interface IYgSendBillListStateService 
{
	YY161_REP_DETAIL get(String psmxbh) throws Exception;
	int updateByBatch(List<YY161_REP_DETAIL> lst) throws Exception;
	int insertByBatch(List<YY161_REP_DETAIL> lst)  throws Exception;
	int update(YY161_REP_DETAIL bean) throws Exception;
	int insert(YY161_REP_DETAIL bean) throws Exception;
	List<YY161_REP_DETAIL> list(YY161_REP_DETAIL queryInfo)  throws Exception;
	List<YY161_REP_DETAIL> list(String billId)  throws Exception;
	QueryResult<YY161_REP_DETAIL> listByPage(QueryInfo<YY161_REP_DETAIL> queryInfo) throws Exception;
	YY161_REP_XML checkIn(String billId) throws Exception;
}


