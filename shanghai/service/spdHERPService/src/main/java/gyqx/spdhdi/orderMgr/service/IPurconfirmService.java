package gyqx.spdhdi.orderMgr.service;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.orderMgr.vo.PurconfirmListVo;
import gyqx.spdhdi.orderMgr.vo.PurconfirmVo;
import gyqx.ws.yg.vo.repVo.YY111_REP_XML;
import gyqx.ws.yg.vo.repVo.YY112_REP_XML;
import gyqx.ws.yg.vo.repVo.YY159_REP_XML;
import gyqx.ws.yg.vo.reqVo.YY111_REQ_XML;
import gyqx.ws.yg.vo.reqVo.YY112_REQ_XML;

import java.util.List;

public interface IPurconfirmService 
{

	PurconfirmVo getById(String id) throws Exception ;
	PurconfirmVo getByBillid(String billid)  throws Exception ;
	List<PurconfirmVo> list(PurconfirmVo purconfirmVo) throws Exception;
	QueryResult<PurconfirmVo> listByPage(QueryInfo<PurconfirmVo> queryInfo) throws Exception;
	//明细表
	List<PurconfirmListVo> detailList(PurconfirmListVo bean) throws Exception;

	YY111_REP_XML submitPurListToYg(YY111_REQ_XML yy111) throws Exception;
	YY112_REP_XML updatePurToYg(String billId) throws Exception;

	void sendPurToYg() throws Exception;
	YY159_REP_XML getPurStatusByBillNo(String ygBillId) throws Exception;
}