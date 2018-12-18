package gyqx.spdhdi.myInfos.service;

import java.util.List;
import java.util.Map;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.myInfos.vo.OutdateCertsImgVo;
import gyqx.spdhdi.myInfos.vo.OutdateCertsVo;
import gyqx.spdhdi.myInfos.vo.ProvRegGoodsInfoQ;
import gyqx.spdhdi.myInfos.vo.ProvRegistGoodsPushQ;
import gyqx.spdhdi.myInfos.vo.ProvRegistGoodsPushQR;
import gyqx.spdhdi.myInfos.vo.ProvRegistInfoPushVo;
import gyqx.spdhdi.po.ProvRegistInfoPush;

public interface IProvRegistInfoPushService {

	ProvRegistInfoPush get(String id) throws Exception;

	QueryResult<ProvRegistGoodsPushQR> pushlistByPage(QueryInfo<ProvRegistGoodsPushQ> queryInfo) throws Exception;

	ProvRegistInfoPush add(ProvRegistInfoPush provRegistInfoPush) throws Exception;

	ProvRegistInfoPush updateProvRegistInfoPush(ProvRegistInfoPush bean) throws Exception;

	List<ProvRegistInfoPush> query(String predicate, String orderBy, Object... fieldValue) throws Exception;

	List<ProvRegistInfoPush> list(ProvRegistInfoPush bean) throws Exception;

	QueryResult<ProvRegistInfoPush> listByPage(QueryInfo<ProvRegistInfoPush> queryInfo) throws Exception;

	List<ProvRegistInfoPush> multipleAdd(List<ProvRegGoodsInfoQ> provRegGoodsInfoQs) throws Exception;

	QueryResult<ProvRegistInfoPushVo> pushedRegCertsProvListByPage(QueryInfo<ProvRegistInfoPushVo> queryInfo)
			throws Exception;

	QueryResult<ProvRegistInfoPushVo> pushedGoodsProvListByPage(QueryInfo<ProvRegistInfoPushVo> queryInfo)
			throws Exception;

	Map<String, Object> getRetrospectMap(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception;

	Map<String, Object> getPushedCertsMap(QueryInfo<ProvRegistInfoPushVo> queryInfo) throws Exception;

	List<OutdateCertsImgVo> getOutdateCertsImg(OutdateCertsVo queryInfo) throws Exception;

	ProvRegistInfoPush update(ProvRegistInfoPushVo provRegistInfoPushVo) throws Exception;

	ProvRegistInfoPush update(ProvRegistInfoPush provRegistInfoPush) throws Exception;

}
