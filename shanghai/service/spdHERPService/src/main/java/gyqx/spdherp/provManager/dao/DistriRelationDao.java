package gyqx.spdherp.provManager.dao;

import java.util.List;


import javax.annotation.Resource;

import gyqx.platform.po.SysOrg;
import gyqx.spdherp.provManager.vo.HosCollectorProvVo;
import org.springframework.stereotype.Repository;

import gyqx.spdherp.provManager.dao.mapper.IDistriRelationMapper;
import gyqx.spdherp.provManager.vo.DistriRelationVo;
import gyqx.spdherp.provManager.vo.PartnerParam;
import gyqx.spdherp.provManager.vo.ProvProvGoodsVo;

@Repository
public class DistriRelationDao {

	@Resource
	IDistriRelationMapper mapper;

	public List<DistriRelationVo> queryCollectorList(DistriRelationVo distriRelationVo) {
		return mapper.queryCollectorList(distriRelationVo);
	}
	
	public List<DistriRelationVo> queryUserRelationCollectorList(DistriRelationVo distriRelationVo) {
		return mapper.queryUserRelationCollectorList(distriRelationVo);
	}

	public List<DistriRelationVo> queryPartnerList(PartnerParam partnerParam) {
		return mapper.queryPartnerList(partnerParam);
	}
	
	public List<DistriRelationVo> queryUserRelationPartnerList(PartnerParam partnerParam) {
		return mapper.queryUserRelationPartnerList(partnerParam);
	}
	
	public List<HosCollectorProvVo> getHosColProvs(HosCollectorProvVo distriRelationVo){
		return  mapper.getHosColProvs(distriRelationVo);
	}

	public List<SysOrg> getCollectorList(String hosId){
		return mapper.getCollectorList(hosId);
	}


	public List<ProvProvGoodsVo> queryGoodsList(ProvProvGoodsVo queryObject) {
		return mapper.queryGoodsList(queryObject);
	}

	public List<ProvProvGoodsVo> getProvProvGoodsId(ProvProvGoodsVo vo) {
		return mapper.getProvProvGoodsId(vo);
	}

	public String getProvByColHosAndGoods(ProvProvGoodsVo ppgv){
		return mapper.getProvByColHosAndGoods(ppgv);
	}

}
