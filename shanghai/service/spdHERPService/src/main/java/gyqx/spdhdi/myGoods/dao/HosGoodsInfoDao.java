package gyqx.spdhdi.myGoods.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import gyqx.spdhdi.myGoods.dao.mapper.IHosGoodsInfoMapper;
import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdhdi.po.HosGoodsInfo;

@Repository
public class HosGoodsInfoDao {
	@Resource
	private IHosGoodsInfoMapper mapper;
	public List<HosGoodsInfoVo> getHosGoodsInfoList(HosGoodsInfo good) throws Exception {
		return mapper.getHosGoodsInfoList(good);
	}
	
	public List<HosGoodsInfoVo> listHosGoods4CodeMgr(HosGoodsInfoVo good) throws Exception {
		return mapper.listHosGoods4CodeMgr(good);
	}

	public HosGoodsInfoVo getHosGoodsInfoById(String id) {
		return mapper.getHosGoodsInfoById(id);
	}
	
	/**
	 * 批量更新科室产品信息采购模式
	 * 
	 * @param purMode
	 *            采购模式
	 * @param hosId
	 *            医院id
	 * @param goodsId
	 *            产品id
	 */
	public void batchUpdateDeptGoodsPurMode(String purMode, String hosId, String goodsId){
		mapper.batchUpdateDeptGoodsPurMode(purMode, hosId, goodsId);
	}

	public Integer listHosGoodsCount4CodeMgr(HosGoodsInfoVo good) {
		return mapper.listHosGoodsCount4CodeMgr(good);
	}
}
