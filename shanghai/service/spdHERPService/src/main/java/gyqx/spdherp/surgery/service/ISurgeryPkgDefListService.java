package gyqx.spdherp.surgery.service;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdherp.po.SurgeryPkgDefList;
import gyqx.spdherp.surgery.vo.SurgeryPkgDefListVo;

import java.util.List;

public interface ISurgeryPkgDefListService 
{
	/*SurgeryPkgDefList get(String id) throws Exception ;*/
/*	SurgeryPkgDefList add(SurgeryPkgDefList surgeryPkgDefList) throws Exception ;*/
	int update(SurgeryPkgDefList surgeryPkgDefList) throws Exception;

	List<SurgeryPkgDefListVo> list(SurgeryPkgDefListVo bean) throws Exception;
	List<SurgeryPkgDefListVo> list4QG(SurgeryPkgDefListVo queryInfo) throws Exception;
	QueryResult<SurgeryPkgDefListVo> listByPage(QueryInfo<SurgeryPkgDefListVo> queryInfo) throws Exception;

	QueryResult<SurgeryPkgDefListVo> listNotImportGoodsByPage(QueryInfo<SurgeryPkgDefListVo> queryInfo);

	int insertByBatch(List<SurgeryPkgDefListVo> surgeryPkgDefListVos) throws Exception;

	int delete(String id) throws Exception;

	int deleteByBatch(List<SurgeryPkgDefListVo> list);

	List<HosGoodsInfoVo> getHosGoodsInfoById(String goodsId);

	List<SurgeryPkgDefListVo> setUseSurPkg(List<String> surIds);
}


