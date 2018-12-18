package gyqx.spdherp.provManager.service;

import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.platform.po.SysOrg;
import gyqx.spdherp.po.HosCollector;
import gyqx.spdherp.po.HosCollectorProv;
import gyqx.spdherp.provManager.vo.*;

public interface IDistriRelationService {

	QueryResult<DistriRelationVo> queryCollectorList(QueryInfo<DistriRelationVo> queryInfo) throws Exception;

	List<HosCollector> importCollectorList(List<ProvHosInfoVo> provHosInfoVos) throws Exception;

	List<HosCollector> mulDeleteCollectorList(List<DistriRelationVo> distriRelationVos) throws Exception;

	QueryResult<DistriRelationVo> queryPartnerList(QueryInfo<PartnerParam> queryInfo) throws Exception;

	List<HosCollectorProv> importPartnerList(PartnerImortParam param) throws Exception;

	List<HosCollectorProvVo> getHosColProvs(HosCollectorProvVo drvo) throws Exception;
	/**
	 * 分页查询产品信息
	 * @param queryInfo hosId collectorId provId goodsName
	 * @return
	 */
	QueryResult<ProvProvGoodsVo> queryGoodsList(QueryInfo<ProvProvGoodsVo> queryInfo);
	
	/**
	 * 保存集配商配送商商品信息
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	List<ProvProvGoodsVo> provProvGoodsSave(ProvProvGoodsVo vo) throws Exception;
	List<SysOrg> getCollectorList(String hosId)throws Exception;
	List<HosCollector> mulDeletePartnerList(List<DistriRelationVo> distriRelationVos) throws Exception;

	DistriRelationVo updatePartnerKind(DistriRelationVo param) throws Exception;
	
	/**
	 * 默认选中的产品
	 * @param vo
	 * @return
	 */
	List<ProvProvGoodsVo> getProvProvGoodsId(ProvProvGoodsVo vo);

	DistriRelationVo updateSubprov(DistriRelationVo param) throws Exception;
	String getProvByColHosAndGoods(ProvProvGoodsVo ppgv) throws Exception;

	/**
	 * 我要采购中查询人员与供应商建立关系的集配商
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	QueryResult<DistriRelationVo> queryUserRelationCollectorList(QueryInfo<DistriRelationVo> queryInfo)
			throws Exception;
	
	/**
	 * 我要采购中查询人员与供应商建立关系的供应商
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	QueryResult<DistriRelationVo> queryUserRelationPartnerList(QueryInfo<PartnerParam> queryInfo) throws Exception;
}
