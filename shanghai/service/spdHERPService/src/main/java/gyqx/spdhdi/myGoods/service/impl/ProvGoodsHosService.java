package gyqx.spdhdi.myGoods.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.base.PinYin;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdhdi.myGoods.dao.ProvGoodsHosDao;
import gyqx.spdhdi.myGoods.service.IProvGoodsHosService;
import gyqx.spdhdi.myGoods.vo.ProHosGoodsInfoVo;
import gyqx.spdhdi.myGoods.vo.ProvGoods4HosVo;
import gyqx.spdhdi.myGoods.vo.ProvGoodsInfoVo;
import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdhdi.po.ProvGoodsHos;


@Service
public class ProvGoodsHosService implements IProvGoodsHosService {
	@Resource
	private SimpleDao simpleDao;

	@Resource
	private SysConfigUtil configUtil;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private ProvGoodsHosDao provGoodsHosDao;
	@Resource
	private UserOnlineStateUtils userOnline;
	
	public ProvGoodsHos updateProvGoodsHos(ProvGoodsHos good) throws Exception{
		good.setUid(userOnline.getCurrent().getUserId());
		good.setLastUpdateDatetime(new Date());
		//对码 查重处理
		 long  num=	simpleDao.queryForObject("select count(*) from prov_goods_hos where prov_id= ? and  hos_id=? and his_code=? and flag=1  and id<> ?", Long.class, good.getProvId(),good.getHosId(),good.getHisCode(),good.getId());
		 if(num>0){
		           ValidateException.Throw("hisCode", good.getHisCode() +  "该医院编码已与其他数据关联！",good.getHisCode() );
			      }
		return simpleDao.updateAndGet(good);
	}
	public void delProvGoodsHosLogical(ProvGoodsHos good) throws Exception{
		good.setFlag("0");
		simpleDao.update(good);
	}
	public void delProvGoodsHosPhysical(ProvGoodsHos good) throws Exception{
		simpleDao.delete(good);
	}
	@Override
	public QueryResult<ProHosGoodsInfoVo> getProHosGoodsList(QueryInfo<ProHosGoodsInfoVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(provGoodsHosDao.getProHosGoodsList(queryInfo.getQueryObject()));
	}

	public QueryResult<ProvGoods4HosVo> getProGoods4HosList(QueryInfo<ProvGoods4HosVo> queryInfo )throws Exception{
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(provGoodsHosDao.getProGoods4HosList(queryInfo.getQueryObject()));
	}

	public void addProvGoodsHosList(List<ProvGoodsHos> goods) throws Exception{
		for(ProvGoodsHos good:goods){
			String id = atomUtil.newValue("prov_goods_hos_id"); 
			good.setProvId(userOnline.getCurrent().getCorpId());
			good.setId(id);
			good.setFillDate(new Date());
			good.setVersion(0);
			good.setFlag("1");
			good.setUid(userOnline.getCurrent().getUserId());
			simpleDao.insert(good);
		}
	}

	public ProvGoodsHos convertProvGood2PGH(ProvGoodsInfoVo provGood,ProvGoodsHos provGoodhos){
		provGoodhos.setProvGoodsid(provGood.getId());
		return  provGoodhos;
	}
	public ProvGoodsHos getProvGoodsHosById(String id) throws Exception{
//	 	ProvGoodsHos provGoodhos = new ProvGoodsHos();

		return (ProvGoodsHos)simpleDao.queryByFieldName(ProvGoodsHos.class, "id", id).get(0);
	}
	public void multDelProvGoodsHos(List<ProvGoodsHos> goods) throws Exception{
		for(ProvGoodsHos pg:goods){
			 pg= (ProvGoodsHos)simpleDao.queryByFieldName(pg.getClass(), "id", pg.getId()).get(0);
			// simpleDao.delete(pg);
			 pg.setFlag("0");
			 simpleDao.update(pg);
		}
	}

	@Override
	public QueryResult<ProHosGoodsInfoVo> getProHosDeptGoodsList(QueryInfo<ProHosGoodsInfoVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(provGoodsHosDao.getProHosDeptGoodsList(queryInfo.getQueryObject()));
	}
}
