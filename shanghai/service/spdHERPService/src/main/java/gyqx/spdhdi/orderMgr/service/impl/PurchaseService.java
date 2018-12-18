package gyqx.spdhdi.orderMgr.service.impl;

import com.mysql.jdbc.StringUtils;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.UtilsContext;
import gyqx.spdhdi.orderMgr.Constance;
import gyqx.spdhdi.orderMgr.dao.PurchaseDao;
import gyqx.spdhdi.orderMgr.dao.PurchaseListDao;
import gyqx.spdhdi.orderMgr.service.IPurchaseExtService;
import gyqx.spdhdi.orderMgr.service.IPurchaseService;
import gyqx.spdhdi.orderMgr.vo.PurchaseExtVo;
import gyqx.spdhdi.orderMgr.vo.PurchaseListVo;
import gyqx.spdhdi.orderMgr.vo.PurchaseVo;
import gyqx.spdhdi.po.Purchase;
import gyqx.spdherp.applyMgr.service.IDeptBuySubService;
import gyqx.spdherp.applyMgr.vo.DeptBuySubVo;
import gyqx.spdherp.deptMgr.service.ReceiveAddressService;
import gyqx.spdherp.deptMgr.vo.ReceiveAddressVo;
import gyqx.spdherp.provManager.service.IDistriRelationService;
import gyqx.spdherp.provManager.vo.ProvProvGoodsVo;
import gyqx.spdherp.quartz.smpaaQuartz;
import gyqx.spdherp.stockPile.vo.DeptGoodsVo;
import jdk.nashorn.internal.ir.ReturnNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

@Service
public class PurchaseService implements IPurchaseService {
	@Resource
	PurchaseDao purchaseDao;
	@Resource
	PurchaseListDao purchaseListDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IDeptBuySubService buySubService;
	@Resource
	private IDistriRelationService distrRelationService;
	@Resource
	private IPurchaseExtService purExtSer;
	@Autowired
	private UtilsContext utilsContext;
	@Autowired
    private ReceiveAddressService addressSer;
    private Logger logger = LoggerFactory.getLogger(smpaaQuartz.class);
    @Override
	public QueryResult<PurchaseVo> getPurchaseList(QueryInfo<PurchaseVo> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(purchaseDao.getPurchaseList(queryInfo.getQueryObject()));
	}
	@Override
	public PurchaseVo getPurchaseByBillId(String billId) throws Exception{
		PurchaseVo p =  purchaseDao.getPurchaseByBillId(billId);
		if(p== null){
			return null;
		}
		PurchaseListVo pd= new PurchaseListVo();
		pd.setBillId(billId);
		List<PurchaseListVo> pdlst = purchaseListDao.getPurchaseList(pd);
		if(pdlst != null){
			p.setPurchaseList(pdlst);
		}
		return p;
	}

	@Override
	public PurchaseVo insertPurchase(PurchaseVo purchaseVo) throws Exception{
        this.checkPurchase(purchaseVo);
		String billId = atomUtil.newId("purcharseID").prefix(purchaseVo.getPurchaseComId()).get();
		purchaseVo.setId(billId);
		purchaseVo.setBillId(billId);
		List<DeptBuySubVo> voList = new ArrayList<>();
		List<PurchaseExtVo> lstExt = new ArrayList<>();
		//
		for(PurchaseListVo pdetail : purchaseVo.getPurchaseList()){
			String detailId = atomUtil.newId("purcharseListID").prefix(purchaseVo.getPurchaseComId()).get();
			pdetail.setId(detailId);
			pdetail.setBillId(billId);
			pdetail.setPid(purchaseVo.getId());
			if(pdetail.getLstApply() != null && pdetail.getLstApply().size()>0){
				pdetail.getLstApply().forEach(item->{
				    item.setPid(detailId);
				    item.setUnit(pdetail.getUnit());

                    DeptBuySubVo dbsvo = new DeptBuySubVo();
                    dbsvo.setBillId(item.getApplyBillId());
                    dbsvo.setRowNumber(item.getApplyBillRow());
                    dbsvo.setWhQty(item.getQty());
                    dbsvo.setSubState(60);
                    dbsvo.setWarehouseDealMan(purchaseVo.getFillter());
                    voList.add(dbsvo);
				});
				lstExt.addAll(pdetail.getLstApply());
			}
			if(purchaseVo.getProvId().equals(purchaseVo.getSubProvId())){
				ProvProvGoodsVo ppgv = new ProvProvGoodsVo();
				ppgv.setCollectorId(purchaseVo.getProvId());
				ppgv.setHosId(purchaseVo.getPurchaseComId());
				ppgv.setHosGoodsId(pdetail.getHosGoodsId());
				String provId = distrRelationService.getProvByColHosAndGoods(ppgv);
				if(StringUtils.isNullOrEmpty(provId)){
					pdetail.setProvId(purchaseVo.getSubProvId());
				}else{
					pdetail.setProvId(provId);
				}
			}else {
				pdetail.setProvId(purchaseVo.getSubProvId());
			}
		}
		purExtSer.insertByBatch(lstExt);
		purchaseListDao.insertPurchaseListByBatch(purchaseVo.getPurchaseList());
		purchaseDao.insertPurchase(purchaseVo);
		if(Constance.PURCHASE_KIND_APPLY.equals(purchaseVo.getPurKind())){	//回写请购单数据
			buySubService.updateBill4Pur(voList);
		}
		return purchaseVo;
	}
	@Override
	public PurchaseVo updatePurchase(PurchaseVo purchaseVo) throws Exception{
        this.checkPurchase(purchaseVo);
		purchaseDao.updatePurchase(purchaseVo);
		//purchaseListDao.updatePurchaseListByBatch(purchaseVo.getPurchaseList());
		purchaseListDao.deletePurchaseListByBillId(purchaseVo.getBillId());
		for(PurchaseListVo pdetail : purchaseVo.getPurchaseList()){
			String detailId =atomUtil.newId("purcharseListID").prefix(purchaseVo.getPurchaseComId()).get();
			pdetail.setId(detailId);
			pdetail.setBillId(purchaseVo.getBillId());
			pdetail.setPid(purchaseVo.getId());
		}
		purchaseListDao.insertPurchaseListByBatch(purchaseVo.getPurchaseList());
		return purchaseVo;
	}
    private void checkPurchase(PurchaseVo p) throws Exception{
        if(p.getPurchaseList().size() == 0){
            ValidateException.Throw("purchaseList","明细行不能为空","");
        }
        for (PurchaseListVo purchaseListVo : p.getPurchaseList()) {
            if(purchaseListVo.getHosPurQty() == null || purchaseListVo.getHosPurQty().compareTo(new BigDecimal(0)) <= 0){
                ValidateException.Throw("hosPurQty",String.format("第%d行：%s的采购数量必须大于零",purchaseListVo.getRowNumber(),purchaseListVo.getHosGoodsName()),"");
            }
            if(StringUtils.isNullOrEmpty(purchaseListVo.getProvId())){
                ValidateException.Throw("hosPurQty",String.format("第%d行：%s的供应商不能为空",purchaseListVo.getRowNumber(),purchaseListVo.getHosGoodsName()),"");
            }
        }
    }
	@Override
	public int deletePurchase(PurchaseVo purchaseVo) throws Exception{
		purchaseListDao.deletePurchaseListByBillId(purchaseVo.getBillId());
		return purchaseDao.deletePurchase(purchaseVo);
	}

	@Override
	public PurchaseVo getPurByid(String id) throws Exception {
		PurchaseVo p =  purchaseDao.getPurByid(id);
		PurchaseListVo pd= new PurchaseListVo();
		pd.setPid(id);
		List<PurchaseListVo> pdlst = purchaseListDao.getPurchaseList(pd);
		if(pdlst != null){
			p.setPurchaseList(pdlst);
		}
		System.console().printf("123");
		return p;
	}
	@Override
	public PurchaseVo updatePurchaseStatus(PurchaseVo purchaseVo) throws Exception{
		purchaseDao.updatePurchase(purchaseVo);
		purchaseListDao.UpdatePurchaseListByBatch(purchaseVo.getPurchaseList());		
		return purchaseVo;
	}

    @Override
    public int updatePurStatus(Map<String, List<PurchaseListVo>> map) throws Exception {
        Iterator<Map.Entry<String, List<PurchaseListVo>>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, List<PurchaseListVo>> entry = entries.next();
            PurchaseVo purBill = this.getPurchaseByBillId(entry.getKey());
            if(purBill == null){
				continue;
			}
            for (PurchaseListVo purchaseListVo : purBill.getPurchaseList()) {
                Integer index = entry.getValue().indexOf(purchaseListVo);
                if(index>=0){
                    Integer subStatus = entry.getValue().get(index).getStatus();
                    purchaseListVo.setStatus(subStatus);
                }
            }
            purBill.setStatus(getPurStateByLst(purBill.getPurchaseList()));
            this.updatePurchaseStatus(purBill);
        }
        return 0;
    }
    private Integer getPurStateByLst(List<PurchaseListVo> lst){
	    Integer sStateC=0,sStateD=0;
        for (PurchaseListVo purchaseListVo : lst) {
            if(purchaseListVo.getStatus().equals(Constance.PURCHASE_LIST_STATUS_CONFIRM)){
                sStateC++;
            }else if(purchaseListVo.getStatus().equals(Constance.PURCHASE_LIST_STATUS_RECEIVE)){
                sStateD++;
            }
        }
        if(sStateD.equals(lst.size())){
            return Constance.PURCHASE_STATUS_ALLREC;
        }
        if(sStateC.equals(lst.size())){
            return Constance.PURCHASE_STATUS_ALLCONFIRM;
        }
        if(sStateD>0){
            return Constance.PURCHASE_STATUS_SOMEREC;
        }
        if(sStateC>0){
            return Constance.PURCHASE_STATUS_SOMECONFIRM;
        }
        return Constance.PURCHASE_STATUS_ERR;
    }

	@Override
	public List<PurchaseVo> creatPurBillByStoc(List<DeptGoodsVo> lstGoods) throws Exception {
		if(CollectionUtils.isEmpty(lstGoods)){
			return null;
		}
		List<PurchaseVo> lstPurBill = new ArrayList<>();
		//按subProvId分单
		Map<String,List<DeptGoodsVo>> goodsMap = lstGoods.stream().collect(Collectors.groupingBy(DeptGoodsVo::getSubProvId));
		goodsMap.forEach((subProvId,lst)->{
            //按采购模式分单
			Map<String,List<DeptGoodsVo>> purMap = lst.stream().collect(Collectors.groupingBy(DeptGoodsVo::getPurMode));
			purMap.forEach((purMode,lstGoodsInfo)->{
				PurchaseVo purBill = this.praseToPurBillByDeptGoods(lstGoodsInfo);
				if(purBill != null){
					lstPurBill.add(purBill);
				}
			});
		});
		if(CollectionUtils.isEmpty(lstPurBill)){
			return null;
		}
		List<PurchaseListVo> lstDetail = new ArrayList<>();
		lstPurBill.forEach(purBill->{
			lstDetail.addAll(purBill.getPurchaseList());
		});
        purchaseListDao.insertPurchaseListByBatch(lstDetail);
        purchaseDao.insertPurchaseByBatch(lstPurBill);
		return lstPurBill;
	}
    /**
     * 将拆分好的要请购品种转换为一个采购单
     * @param lstGoods
     * @return
     */
    private PurchaseVo praseToPurBillByDeptGoods(List<DeptGoodsVo> lstGoods){
        if(CollectionUtils.isEmpty(lstGoods)){
            return null;
        }
        DeptGoodsVo dgv = lstGoods.get(0);
        PurchaseVo purBill = new PurchaseVo();
        purBill.setRemark("根据库存补货");
        purBill.setPurKind(Constance.PURCHASE_KIND_AUTO);
        purBill.setPurType(new Integer(dgv.getPurMode()));
		purBill.setPurchaseComId(dgv.getHosId());
        String billId = atomUtil.newId("purcharseID").prefix(purBill.getPurchaseComId()).get();
		purBill.setId(billId);
		purBill.setBillId(billId);
		purBill.setPurDeptId(dgv.getDeptId());
        ReceiveAddressVo addressInfo = null;
        try {
            addressInfo = addressSer.getAddresByDeptId(purBill.getPurDeptId());
        }catch (Exception e){
            logger.info(String.format("自动补单据（%s）获取收货地址失败",purBill.getBillId()));
        }
        if(addressInfo != null){
            purBill.setRecAddress(addressInfo.getProvince()+addressInfo.getCity()+addressInfo.getArea() + addressInfo.getAddress());
            purBill.setRecAddressId(addressInfo.getId());
            purBill.setRecLinkman(addressInfo.getLxr());
            purBill.setRecLinkmanPhone(addressInfo.getLxrPhone());
        }
        String filler = utilsContext.getSysConfigUtil().getValue(Constance.SYS_AUTO_FILLER);
        purBill.setFillter(filler);
		purBill.setStatus(Constance.PURCHASE_STATUS_CREATEBYSTOC);
		purBill.setRecOrgId(purBill.getPurDeptId());
		purBill.setProvId(dgv.getProvId());
		purBill.setSubProvId(dgv.getSubProvId());
		purBill.setSumRow(lstGoods.size());
        purBill.setSumAmount(BigDecimal.ZERO);
        purBill.setSumTaxAmount(BigDecimal.ZERO);
		int i =0;
		List<PurchaseListVo> lstDetail = new ArrayList<>();
		for (DeptGoodsVo goodsVo : lstGoods) {
			i++;
			PurchaseListVo detail = new PurchaseListVo();
			String detailId = atomUtil.newId("purcharseListID").prefix(purBill.getPurchaseComId()).get();
			detail.setId(detailId);
			detail.setPid(billId);
			detail.setBillId(billId);
			detail.setRowNumber(i);
			detail.setApplyDeptId(goodsVo.getDeptId());
			detail.setApplyDeptName(goodsVo.getDeptName());
			detail.setSaleMan(goodsVo.getSalemanId());
			detail.setHosGoodsId(goodsVo.getGoodsId());
			detail.setHosGoodsCode(goodsVo.getErpCode());
			detail.setHosGoodsName(goodsVo.getGoodsName());
			detail.setHosPrice(goodsVo.getPurPrice());
			detail.setHosUnit(goodsVo.getUnit());
			detail.setHosScale(goodsVo.getHosScale()==0?1:goodsVo.getHosScale());
			detail.setPacketCode(goodsVo.getPacketCode());
			detail.setHosApplyQty(goodsVo.getNeedQty());
			detail.setApplyQty(detail.getHosApplyQty().multiply(new BigDecimal(detail.getHosScale())));
			detail.setHosPurQty(detail.getHosApplyQty());
			detail.setPurQty(detail.getApplyQty());
			detail.setUnitQty(BigDecimal.ZERO);
			detail.setQty(BigDecimal.ZERO);
			detail.setPurTax(goodsVo.getPurTax());
			detail.setPurTaxPrice(detail.getHosPrice().divide(detail.getPurTax().add(BigDecimal.ONE),6,ROUND_HALF_DOWN));
			detail.setPurTaxAmount(detail.getHosPurQty().multiply(detail.getPurTaxPrice()));
			detail.setPurAmount(detail.getHosPurQty().multiply(detail.getHosPrice()));
			detail.setPurTaxMoney(detail.getPurAmount().subtract(detail.getPurTaxAmount()));
			detail.setStatus(Constance.PURCHASE_LIST_STATUS_SAVE);
			detail.setProvId(goodsVo.getGoodsProvId());
			detail.setUniqueKind(new Integer(goodsVo.getIsUnique()));
            lstDetail.add(detail);

            purBill.setSumAmount(purBill.getSumAmount().add(detail.getPurAmount()));
            purBill.setSumTaxAmount(purBill.getSumTaxAmount().add(detail.getPurTaxAmount()));
		}
		purBill.setPurchaseList(lstDetail);
		return purBill;
    }

    @Override
    public Integer audit(PurchaseVo purBill,Integer flag) throws Exception {
        if(StringUtils.isNullOrEmpty(purBill.getBillId())){
            throw new Exception("采购单号不能为空");
        }
        purBill.setStatus(flag>0?Constance.PURCHASE_STATUS_SUBMIT:Constance.PURCHASE_STATUS_UNAUDIT);
        return purchaseDao.audit(purBill);
    }
}
