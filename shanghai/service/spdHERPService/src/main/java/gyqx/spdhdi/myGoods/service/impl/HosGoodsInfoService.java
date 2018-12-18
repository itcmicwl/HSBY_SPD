package gyqx.spdhdi.myGoods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import common.base.PinYin;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.utils.*;
import gyqx.spdhdi.baseData.vo.BasGoodsInfoVo;
import gyqx.spdhdi.myGoods.dao.HosGoodsInfoDao;
import gyqx.spdhdi.myGoods.service.IHosGoodsInfoService;
import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdhdi.myGoods.vo.ProvGoods4HosVo;
import gyqx.spdhdi.po.BasMfrsInfo;
import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdherp.deptMgr.dao.DeptGoodsInfoMgrDao;
import gyqx.spdherp.deptMgr.vo.DeptGoodsInfoVo;
import gyqx.spdherp.po.DeptGoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HosGoodsInfoService implements IHosGoodsInfoService {
	@Resource
	private SimpleDao simpleDao;
	@Resource
	private HosGoodsInfoDao hosGoodsDao;
	@Resource
	private SysConfigUtil configUtil;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private UserOnlineStateUtils userOnline;
	@Autowired
	@Resource
	private DeptGoodsInfoMgrDao deptGoodsdao;
	@Override
	public HosGoodsInfo addHosGood(HosGoodsInfo good) throws Exception {
		// TODO Auto-generated method stub
		if (simpleDao.checkExists(HosGoodsInfo.class, "code,hosId", good.getCode(),good.getHosId()) > 0) {
			ValidateException.Throw("code", good.getCode()+ "已存在！", good.getCode());
		}
		 
		
		good.setHosId(userOnline.getCurrent().getCorpId());
		String id =good.getHosId()+ atomUtil.newValue("hos_goods_info_id");
		good.setShortPinyin(PinYin.getShortPinyin(good.getGoodsName()));
		good.setId(id);
		good.setFillDate(new Date());
		good.setVersion(0);
		good.setFlag("1");
		if(StringUtils.isEmpty(good.getPurMode())){
			good.setPurMode("10");
		}
		   good.setPrice(good.getPrice()!=null?good.getPrice():new BigDecimal(0));
           good.setUniqueCodeStrategy(good.getUniqueCodeStrategy()!=null?good.getUniqueCodeStrategy():"3");
           good.setTaxRate(good.getTaxRate()!=null?good.getTaxRate():new BigDecimal(0.17));
		good.setUid(userOnline.getCurrent().getUserId());
		return simpleDao.insertAndGet(good);
	}

	@Override
	public HosGoodsInfo updateHosGood(HosGoodsInfo good) throws Exception {
		// TODO Auto-generated method stub
		 long  num=	simpleDao.queryForObject("select count(*) from hos_goods_info where code= ? and hos_id=? and id<>?", Long.class, good.getCode(),good.getHosId(),good.getId());
		   if(num>0){
			   ValidateException.Throw("code", good.getCode() +  "已存在！",good.getCode() );
		   }
			good.setUid(userOnline.getCurrent().getUserId());
            good.setLastUpdateDatetime(new Date());
			good.setShortPinyin(PinYin.getShortPinyin(good.getGoodsName()));
			// 对院级产品采购模式做变更,则将所有科室关联下该产品的采购模式做相应变更
/*			String oldPurMode=simpleDao.queryForObject(
					"select pur_mode from hos_goods_info where id = ?", String.class,good.getId());
			if (org.apache.commons.lang3.StringUtils.isNotBlank(oldPurMode)
					&& !org.apache.commons.lang3.StringUtils.equals(oldPurMode, good.getPurMode())){
				hosGoodsDao.batchUpdateDeptGoodsPurMode(good.getPurMode(), good.getHosId(), good.getId());
			}*/
			return simpleDao.updateAndGet(good);
		
	}

	@Override
	public QueryResult<HosGoodsInfoVo> listHosGoods(QueryInfo<HosGoodsInfo> queryInfo) throws Exception {
		// TODO Auto-generated method stub
		 QueryResult<HosGoodsInfoVo> re = new  QueryResult<HosGoodsInfoVo>();
			PageHelper.startPage(queryInfo.getPage(), queryInfo.getRows());
			List<HosGoodsInfoVo> dataList = hosGoodsDao.getHosGoodsInfoList(queryInfo.getQueryObject());
			PageInfo<HosGoodsInfoVo> sysConfigPage = new PageInfo<HosGoodsInfoVo>(dataList);
			re.setData(sysConfigPage.getList());
			re.setPageNum(sysConfigPage.getPageNum());
			re.setPageSize(sysConfigPage.getPageSize());
			re.setTotal(sysConfigPage.getTotal());
		
			 
		   	return re; 
		
	}
	public QueryResult<HosGoodsInfoVo> listHosGoods4CodeMgr(QueryInfo<HosGoodsInfoVo> queryInfo) throws Exception{
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(hosGoodsDao.listHosGoods4CodeMgr(queryInfo.getQueryObject()));
	}


	public void addHosGoodS(List<HosGoodsInfo> goods) throws Exception{
	/*	goods.forEach(good->{
			try {
				addHosGood(good);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});*/
		
		for(HosGoodsInfo good:goods){
			
			good.setHosId(userOnline.getCurrent().getCorpId());
			good.setShortPinyin(PinYin.getShortPinyin(good.getGoodsName()));
			String id =good.getHosId()+ atomUtil.newValue("hos_goods_info_id");
			   good.setPrice(good.getPrice()!=null?good.getPrice():new BigDecimal(0));
	            good.setUniqueCodeStrategy(good.getUniqueCodeStrategy()!=null?good.getUniqueCodeStrategy():"3");
	            good.setTaxRate(good.getTaxRate()!=null?good.getTaxRate():new BigDecimal(0.17));
			good.setId(id);
			good.setFillDate(new Date());
			good.setVersion(0);
			good.setUid(userOnline.getCurrent().getUserId());
			good.setFlag("1");
			if(StringUtils.isEmpty(good.getPurMode())){
				good.setPurMode("10");
			}
			simpleDao.insert(good);
		}
	}
	public HosGoodsInfo convertBasGood2HosGood(BasGoodsInfoVo basGood,HosGoodsInfo hosGood){
		hosGood.setCode(null);//医院商品编码，需要医院自行维护
		hosGood.setGoodsName(basGood.getGoodsName());
		hosGood.setGoodsGg(basGood.getGoodsGg());
		hosGood.setMade(basGood.getMade());
		hosGood.setMfrsId(basGood.getMfrsId());
		hosGood.setUnit(basGood.getUnit());
		hosGood.setBrand(basGood.getBrand());
		hosGood.setPackeage(basGood.getPackeage());
	//	hosGood.setPrice(basGood.getp);
	//	hosGood.setMasterCode(hosGood.getMasterCode());
		hosGood.setMfrsCode(basGood.getMfrsCode());
		hosGood.setKindCode(basGood.getKindCode());
		hosGood.setKind68code(basGood.getKind68code());
	//	hosGood.setErpCode(basGood.getErpCode());
		hosGood.setSpdGoodsCode(basGood.getCode()); //产品基础信息的code对应
		hosGood.setLbsx(basGood.getLbsx());
		hosGood.setRemark(basGood.getRemark());
		return hosGood;
	}
	
	public HosGoodsInfo convertPHGood2HosGood(ProvGoods4HosVo phGoodVo,HosGoodsInfo hosGood){
		hosGood.setCode(null);//医院商品编码，需要医院自行维护
		hosGood.setGoodsName(phGoodVo.getProvGoodsName());
		hosGood.setGoodsGg(phGoodVo.getProvGoodsGg());
		hosGood.setMade(phGoodVo.getProvGoodsMade());
		hosGood.setMfrsId(phGoodVo.getProvGoodsMfrsId());
		hosGood.setUnit(phGoodVo.getProvGoodsUnit());
		hosGood.setBrand(phGoodVo.getBrand());
		hosGood.setPackeage(phGoodVo.getProvGoodsPackeage());
	//	hosGood.setPrice(basGood.getp);
	//	hosGood.setMasterCode(hosGood.getMasterCode());
		hosGood.setMfrsCode(phGoodVo.getProvGoodsMfrsCode());
		hosGood.setKindCode(phGoodVo.getProvGoodsKindCode());
		hosGood.setKind68code(phGoodVo.getProvGoodsKind68code());
	//	hosGood.setErpCode(basGood.getErpCode());
		hosGood.setSpdGoodsCode(phGoodVo.getProvGoodsSpdGoodsCode()); //产品基础信息的code对应
		//hosGood.setLbsx(phGoodVo.getProvGoodsLbsx());
		//hosGood.setProvId(phGoodVo.getProvId());
		hosGood.setHosMfrsName(phGoodVo.getProvGoodsMfrsName());

        hosGood.setCharging("1");
        hosGood.setGoodsDesc(hosGood.getGoodsName() + " " + hosGood.getGoodsGg());
        hosGood.setGeneralName(hosGood.getGoodsName());
        return hosGood;
    }
	
	
	
	@Override
	public void deleteHosGood(HosGoodsInfo good) throws Exception {
    	//simpleDao.delete(good);
		good.setFlag("0");
		simpleDao.update(good);
	}
	
	public void multDelHosGoods(List<HosGoodsInfo> goods) throws Exception{
		for(HosGoodsInfo pg:goods){
			// simpleDao.delete(pg);
			pg.setFlag("0");
			simpleDao.update(pg);
		}
	}
	public List<HosGoodsInfo> addHosGoodS4ImportExcel(List<HosGoodsInfo> goods) throws Exception{
		List<HosGoodsInfo> pgErrorList = new ArrayList();
		for (int i = 0; i < goods.size(); i++) {
			HosGoodsInfo good = goods.get(i);
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
				if (simpleDao.checkExists(HosGoodsInfo.class, "code", good.getCode()) > 0){
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
			addHosGood(good);
			
		}
		return pgErrorList;
	}

	@Override
	public HosGoodsInfoVo getHosGoodsInfoById(HosGoodsInfoVo good) {
		return hosGoodsDao.getHosGoodsInfoById(good.getId());
	}

	@Override
	public Integer listHosGoodsCount4CodeMgr(QueryInfo<HosGoodsInfoVo> queryInfo) {
		return hosGoodsDao.listHosGoodsCount4CodeMgr(queryInfo.getQueryObject());
	}

	@Override
	public Integer updateChangeHosGoods(String newGoodsId, String oldGoodsId,String newErpCode) throws Exception {
		if(!StringUtils.hasText(newGoodsId) || !StringUtils.hasText(oldGoodsId)){
			throw new Exception("新旧产品ID不能为空");
		}
		//将hos_goods_info中对应旧产品记录flag置为0
		HosGoodsInfoVo hosGoodsInfoVo = hosGoodsDao.getHosGoodsInfoById(newGoodsId);
		if(hosGoodsInfoVo == null){
			throw new Exception("替换出错");
		}
		DeptGoodsInfoVo query = new DeptGoodsInfoVo();
		query.setGoodsId(oldGoodsId);
		List<DeptGoodsInfo> lst = new ArrayList<>();
		List<DeptGoodsInfoVo> lstDeptGoods = deptGoodsdao.deptGoodsInfoList(query);
		query.setGoodsId(newGoodsId);
		List<DeptGoodsInfoVo> lstNewDeptGoods = deptGoodsdao.deptGoodsInfoList(query);
		for (DeptGoodsInfoVo deptGoods : lstDeptGoods) {
			Optional<DeptGoodsInfoVo> ODeptGoods = lstNewDeptGoods.stream()
					.filter(goods->goods.getDeptId().equals(deptGoods.getDeptId())
					&& goods.getHosId().equals(deptGoods.getHosId()) && goods.getGoodsId().equals(newGoodsId)).findAny();
			if(ODeptGoods.isPresent()){
				continue;
			}
            DeptGoodsInfo dgoods = new DeptGoodsInfo();
			String id = atomUtil.newId("dept-goods-info-id").get();
            dgoods.setId(id);
            dgoods.setErpCode(newErpCode);
            dgoods.setSalemanId(deptGoods.getSalemanId());
            dgoods.setBrand(hosGoodsInfoVo.getBrand());
            dgoods.setHosId(deptGoods.getHosId());
            dgoods.setHosName(deptGoods.getHosName());
            dgoods.setDeptId(deptGoods.getDeptId());
            dgoods.setDeptName(deptGoods.getDeptName());
            dgoods.setGoodsId(hosGoodsInfoVo.getId());
            dgoods.setGoodsName(hosGoodsInfoVo.getGoodsName());
            dgoods.setGoodsGg(hosGoodsInfoVo.getGoodsGg());
            dgoods.setUnit(hosGoodsInfoVo.getUnit());
            dgoods.setMfrsId(hosGoodsInfoVo.getMfrsId());
            dgoods.setMfrsName(hosGoodsInfoVo.getMfrsName());
            dgoods.setMade(hosGoodsInfoVo.getMade());
            dgoods.setStocUpper(deptGoods.getStocUpper());
            dgoods.setStocLower(deptGoods.getStocLower());
            dgoods.setIsPacket(deptGoods.getIsPacket());
            dgoods.setIsUnique(hosGoodsInfoVo.getUniqueCodeStrategy());
            dgoods.setPacketCode(deptGoods.getPacketCode());
            dgoods.setIsMix(deptGoods.getIsMix());
            dgoods.setPurMode(hosGoodsInfoVo.getPurMode());
            dgoods.setStocId(deptGoods.getStocId());
            dgoods.setReqNum(deptGoods.getReqNum());
            dgoods.setMiCode(hosGoodsInfoVo.getMiCode());
            dgoods.setSalemanCode(deptGoods.getSalemanCode());
			lst.add(dgoods);
		}
		Integer count = deptGoodsdao.insertByBatch(lst);
		Integer res = simpleDao.executeSql(String.format("update hos_goods_info set flag = 0, LAST_UPDATE_DATETIME=sysdate(),VERSION = VERSION+1 where id='%s'",oldGoodsId));
		simpleDao.executeSql(String.format("update goods_pur_template_goods set goods_id='%s', LAST_UPDATE_DATETIME=sysdate(),VERSION = VERSION+1 where goods_id='%s'",newGoodsId,oldGoodsId));
		return res;
	}
}
