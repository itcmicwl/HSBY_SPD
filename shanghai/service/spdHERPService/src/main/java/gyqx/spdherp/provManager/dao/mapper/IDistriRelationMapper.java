package gyqx.spdherp.provManager.dao.mapper;

import java.util.List;

import gyqx.platform.po.SysOrg;
import gyqx.spdherp.provManager.vo.DistriRelationVo;
import gyqx.spdherp.provManager.vo.HosCollectorProvVo;
import gyqx.spdherp.provManager.vo.PartnerParam;

import org.apache.ibatis.annotations.Param;

import gyqx.spdherp.provManager.vo.ProvProvGoodsVo;

public interface IDistriRelationMapper {

	List<DistriRelationVo> queryCollectorList(DistriRelationVo distriRelationVo);
	
	/**
	 * 我要采购中查询人员与供应商建立关系的集配商
	 * @param distriRelationVo
	 * @return
	 */
	List<DistriRelationVo> queryUserRelationCollectorList(DistriRelationVo distriRelationVo);

	List<SysOrg> getCollectorList(@Param("hosId") String hosId);
	String getProvByColHosAndGoods(ProvProvGoodsVo ppgv);
	List<DistriRelationVo> queryPartnerList(PartnerParam partnerParam);
	
	/**
	 * 我要采购中查询人员与供应商建立关系的供应商
	 * @param partnerParam
	 * @return
	 */
	List<DistriRelationVo> queryUserRelationPartnerList(PartnerParam partnerParam);

	List<HosCollectorProvVo>getHosColProvs(HosCollectorProvVo distriRelationVo);

	/**
	 * 查询集配商配送商商品信息
	 * 
	 * @param queryObject
	 * @return
	 */
	List<ProvProvGoodsVo> queryGoodsList(ProvProvGoodsVo queryObject);

	/**
	 * 默认选中的产品
	 * 
	 * @param vo
	 * @return
	 */
	List<ProvProvGoodsVo> getProvProvGoodsId(ProvProvGoodsVo vo);
}
