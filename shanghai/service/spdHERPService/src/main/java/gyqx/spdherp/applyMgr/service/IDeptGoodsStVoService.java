package gyqx.spdherp.applyMgr.service;


import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.applyMgr.vo.DeptGoodsStVo;
import gyqx.spdherp.applyMgr.vo.DeptGoodsStVo;

public interface IDeptGoodsStVoService 
{
	QueryResult<DeptGoodsStVo> listByPage(QueryInfo<DeptGoodsStVo> queryInfo) throws Exception;	
}


