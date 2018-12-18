package gyqx.spdhdi.myGoods.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import common.base.PinYin;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdhdi.baseData.vo.BasGoodsInfoVo;
import gyqx.spdhdi.myGoods.dao.ProvGoodsInfoDao;
import gyqx.spdhdi.myGoods.service.IProvGoodsInfoService;
import gyqx.spdhdi.myGoods.vo.ProvGoodsInfoVo;
import gyqx.spdhdi.po.BasGoodsInfo;
import gyqx.spdhdi.po.BasMfrsInfo;
import gyqx.spdhdi.po.ProvGoodsHos;
import gyqx.spdhdi.po.ProvGoodsInfo;

@Service
public class ProvGoodsInfoService implements IProvGoodsInfoService {
	@Resource
	private SimpleDao simpleDao;
	@Resource
	private ProvGoodsInfoDao provGoodsInfoDao;
	@Resource
	private SysConfigUtil configUtil;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private UserOnlineStateUtils userOnline;
	@Override
	public ProvGoodsInfo addProvGood(ProvGoodsInfo good) throws Exception {
		// TODO Auto-generated method stub
		if (simpleDao.checkExists(ProvGoodsInfo.class, "code", good.getCode()) > 0) {
			ValidateException.Throw("code", good.getCode()+ "已存在！", good.getCode());
		}
//    	List<BasGoodsInfo>  basGoodsList = new ArrayList();
    	BasGoodsInfo basGood = new BasGoodsInfo();
		List<BasGoodsInfo> basGoodsList= simpleDao.queryForList("select * from bas_goods_info where goods_name=? and goods_gg=? and made=? and mfrs_id=? and unit=?", BasGoodsInfo.class,  good.getGoodsName(),good.getGoodsGg(),good.getMade(),good.getMfrsId(),good.getUnit());
        //判断平台产品信息中是否有对应的数据，若无，则还需在平台产品中注入该数据 （通过这5个属性做全匹配 goodsName,goodsGg,made,mfrsId,unit）
	//	if (!(simpleDao.checkExists(BasGoodsInfo.class, "goods_name,goods_gg,made,mfrs_id,unit", good.getGoodsName(),good.getGoodsGg(),good.getMade(),good.getMfrsId(),good.getUnit()) > 0)) {
		if(basGoodsList.size()==0){	
    	//平台产品信息无匹配数据，需要添加
			//String id =atomUtil.newValue("prov_goods_info_id");  //good-00004
		
			String id =atomUtil.newValue("bGoods_id");
			basGood.setId(id);
		//	basGood.setCertificateCode("");//注册证号
			basGood.setCode(id.split("-")[1]);//平台商品编号 ,取id- 后面流水号 good-00004
			basGood.setFillDate(new Date());
			basGood.setGoodsGg(good.getGoodsGg());
			basGood.setGoodsName(good.getGoodsName());
			basGood.setShortPinyin(PinYin.getShortPinyin(good.getGoodsName()));
			basGood.setKind68code(good.getKind68code());
			basGood.setKindCode(good.getKindCode());
			basGood.setLbsx(good.getLbsx());
			basGood.setMade(good.getMade());
			basGood.setMasterCode(good.getMasterCode());
			basGood.setMfrsCode(good.getMfrsCode());
			basGood.setMfrsId(good.getMfrsId());
			basGood.setPackeage(good.getPackeage());
			basGood.setRemark(good.getRemark());
			basGood.setUid(userOnline.getCurrent().getUserId());
			basGood.setUnit(good.getUnit());
			basGood.setFlag("1");
			basGood=simpleDao.insertAndGet(basGood);
			
			//将新增的数据的code赋予到good上
			good.setSpdGoodsCode(basGood.getCode());
		}
		else { //平台产品中有匹配，取code
			good.setSpdGoodsCode(basGoodsList.get(0).getCode());	
		}
		
		String id = atomUtil.newValue("prov_goods_info_id");
		good.setShortPinyin(PinYin.getShortPinyin(good.getGoodsName()));
		good.setProvId(userOnline.getCurrent().getCorpId());
		good.setId(id);
		good.setFlag("1");
		good.setFillDate(new Date());
		good.setVersion(0);
		//good.setSpdGoodsCode(spdGoodsCode);
		good.setUid(userOnline.getCurrent().getUserId());
		return simpleDao.insertAndGet(good);
	}

	@Override
	public ProvGoodsInfo updateProvGood(ProvGoodsInfo good) throws Exception {
		// TODO Auto-generated method stub
		  long  num=	simpleDao.queryForObject("select count(*) from prov_Goods_info where code= ?  and flag =1  and id<>?", Long.class, good.getCode(),good.getId());
		   if(num>0){
			   ValidateException.Throw("code", good.getCode() +  "已存在！",good.getCode() );
		   }
			good.setShortPinyin(PinYin.getShortPinyin(good.getGoodsName()));
			good.setUid(userOnline.getCurrent().getUserId());
			good.setLastUpdateDatetime(new Date());
			return simpleDao.updateAndGet(good);
		
	}

	@Override
	public QueryResult<ProvGoodsInfoVo> listProvGoods(QueryInfo<ProvGoodsInfo> queryInfo) throws Exception {
		   QueryResult<ProvGoodsInfoVo> re = new  QueryResult<ProvGoodsInfoVo>();
				PageHelper.startPage(queryInfo.getPage(), queryInfo.getRows());
				List<ProvGoodsInfoVo> dataList = provGoodsInfoDao.getProvGoodsInfoList(queryInfo.getQueryObject());
				PageInfo<ProvGoodsInfoVo> sysConfigPage = new PageInfo<ProvGoodsInfoVo>(dataList);
				re.setData(sysConfigPage.getList());
				re.setPageNum(sysConfigPage.getPageNum());
				re.setPageSize(sysConfigPage.getPageSize());
				re.setTotal(sysConfigPage.getTotal());
		   	return re; 
		
	}

	@Override
	public void deleteProvGood(ProvGoodsInfo good) throws Exception {
		//改为逻辑删除 ，并判断 供货目录中是否有该商品，如有不允许删除
		if (simpleDao.checkExists(ProvGoodsHos.class, "provGoodsid,flag", good.getId(),"1") > 0) {
			ValidateException.Throw("goodsName", "您删除的"+good.getGoodsName()+ "在供货目录中存在，不允许删除！", good.getGoodsName());
		}
		good.setFlag("0");
  	    simpleDao.update(good);
	}
	public int multDelProvGoods(List<ProvGoodsInfo> goods) throws Exception{
		//改为逻辑删除 ，并判断 供货目录中是否有该商品，如有不允许删除
          int i=0;
		for(ProvGoodsInfo pg:goods){
			if (simpleDao.checkExists(ProvGoodsHos.class, "provGoodsid,flag", pg.getId(),"1") > 0) {
			   //	ValidateException.Throw("goodsName", "您删除的"+pg.getGoodsName()+ "在供货目录中存在，不允许删除！", pg.getGoodsName());
				i=i+1;
			}
			else{
			 pg.setFlag("0");
			 simpleDao.update(pg);
			}
		}
		return i;
	}
	public List<ProvGoodsInfo> addProvGoodS4ImportExcel(List<ProvGoodsInfo> goods) throws Exception{
		List<ProvGoodsInfo> pgErrorList = new ArrayList();
		for (int i = 0; i < goods.size(); i++) {
			ProvGoodsInfo good = goods.get(i);
			if (StringUtils.isEmpty(good.getGoodsName())) {
				good.setRemark("商品名称为空");
				pgErrorList.add(good);
				continue;
			}
			if (!StringUtils.isEmpty(good.getMfrsId()) ) {
				if (good.getMfrsId().indexOf("@@4mfrsName") > 0) {// 从Excel导入时附加的特殊字符@@4mfrsName
					// 从厂家表中查询：输入 厂家名 输出 厂家ID
					String mfrsId = good.getMfrsId().split("@@4mfrsName")[0];
					BasMfrsInfo mfr = (BasMfrsInfo) simpleDao.getByFieldName(BasMfrsInfo.class, "mfrsName", mfrsId);
					if (mfr == null) {
						pgErrorList.add(good);
						good.setRemark("厂家"+ mfrsId + "在基础数据中 没有对应记录");
						continue;
					} else {
						good.setMfrsId(mfr.getId());
						System.out
								.println("成功匹配   " + mfrsId + "到基础数据 ---->" + mfr.getMfrsName() + "+  " + mfr.getId());
					}
				}
			} else {
				good.setRemark("厂家名称为空");
				pgErrorList.add(good);
				continue;
			}
			
			//检查编号是否为空，编号是否重复
			if (!StringUtils.isEmpty(good.getCode()) ) {
				if (simpleDao.checkExists(ProvGoodsInfo.class, "code", good.getCode()) > 0){
					good.setRemark("编号与数据库中其他数据重复");
					pgErrorList.add(good);
					continue;
				}
				
			}
			else {
					good.setRemark("编号为空");
				 pgErrorList.add(good);
				 continue;
			}

			
			// simpleDao.insert(good);
			addProvGood(good);
			
		}
		return pgErrorList;
	}
	
	public void addProvGoodS(List<ProvGoodsInfo> goods) throws Exception {
		for (ProvGoodsInfo good : goods) {
			String id = atomUtil.newValue("prov_goods_info_id");
			good.setProvId(userOnline.getCurrent().getCorpId());
			good.setShortPinyin(PinYin.getShortPinyin(good.getGoodsName()));
			good.setId(id);
			good.setFillDate(new Date());
			good.setVersion(0);
			good.setUid(userOnline.getCurrent().getUserId());
			simpleDao.insert(good);
		}
	}
	
	
	public ProvGoodsInfo convertBasGood2ProvGood(BasGoodsInfoVo basGoodVo, ProvGoodsInfo provGood) throws Exception{
		 //provGood.setCode("");
		//provGood.setErpCode("");
		provGood.setFillDate(new Date() );
		provGood.setFlag("1");
		provGood.setGoodsGg(basGoodVo.getGoodsGg());
		provGood.setGoodsName(basGoodVo.getGoodsName());
		//provGood.setHitCode("");
		//provGood.setHitPrice("");
		//provGood.setId(id);
		provGood.setKind68code(basGoodVo.getKind68code());
		provGood.setKindCode(basGoodVo.getKindCode());
		//provGood.setLastUpdateDatetime(lastUpdateDatetime);
		provGood.setLbsx(basGoodVo.getLbsx());
		provGood.setMade(basGoodVo.getMade());
		provGood.setMasterCode(basGoodVo.getMasterCode());
		provGood.setMfrsCode(basGoodVo.getMfrsCode());
		provGood.setMfrsId(basGoodVo.getMfrsId());
		provGood.setPackeage(basGoodVo.getPackeage());
		//provGood.setProvId(); 从当前用户身份中获取
		provGood.setRemark(basGoodVo.getRemark());
		//provGood.setShortPinyin(shortPinyin);
		provGood.setSpdGoodsCode(basGoodVo.getCode());
		//provGood.setUid(uid);
		provGood.setUnit(basGoodVo.getUnit());
		//provGood.setVersion(version);
		return provGood;
	}
	
	public QueryResult<ProvGoodsInfoVo> getProvGoodsInfoListByProvAndHos_Pages(QueryInfo<ProvGoodsHos> queryInfo) throws Exception{		
		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(provGoodsInfoDao.getProvGoodsInfoListByProvAndHos(queryInfo.getQueryObject()));
	}
	
}
