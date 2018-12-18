package gyqx.ws.yg.service;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.ws.yg.vo.repVo.YY161_REP_DETAIL;
import gyqx.ws.yg.vo.reqVo.YY131_REQ_DETAIL;

import java.util.List;

public interface IYgRecConfirmService
{
	YY131_REQ_DETAIL get(String psmxbh) throws Exception;
	int updateByBatch(List<YY131_REQ_DETAIL> lst) throws Exception;
	int insertByBatch(List<YY131_REQ_DETAIL> lst)  throws Exception;
	int update(YY131_REQ_DETAIL bean) throws Exception;
	int insert(YY131_REQ_DETAIL bean) throws Exception;
	List<YY131_REQ_DETAIL> list(YY131_REQ_DETAIL queryInfo)  throws Exception;
	QueryResult<YY131_REQ_DETAIL> listByPage(QueryInfo<YY131_REQ_DETAIL> queryInfo) throws Exception;
	void autoRecSendBill() throws Exception;
	int setHadRecConfirm(String billId) throws Exception;
    int setBillConfirm(String billId) throws Exception;
}


