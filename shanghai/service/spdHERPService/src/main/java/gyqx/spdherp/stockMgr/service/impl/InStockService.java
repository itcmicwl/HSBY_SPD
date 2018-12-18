package gyqx.spdherp.stockMgr.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import gyqx.spdherp.po.*;
import gyqx.spdherp.stockMgr.vo.*;
import gyqx.spdherp.stockout.service.BillMgrService;
import gyqx.spdherp.stockout.service.IFillBillService;
import gyqx.spdherp.stockout.service.ISickerUseListService;
import gyqx.spdherp.stockout.vo.BigBatch4FillVo;
import gyqx.spdherp.stockout.vo.EntireOutStockListVo;
import gyqx.spdherp.stockout.vo.SickerUserListVo;
import gyqx.spdherp.stockout.vo.SickerUserVo;
import gyqx.spdherp.surgery.constant.Constants;
import gyqx.spdherp.surgery.service.ISurgeryPkgService;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ParameterException;
import common.exception.ValidateException;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdhdi.orderMgr.dao.DistrBillDao;
import gyqx.spdhdi.orderMgr.dao.DistrBillListDao;
import gyqx.spdhdi.orderMgr.service.IDistrBillService;
import gyqx.spdhdi.orderMgr.service.IPurchaseService;
import gyqx.spdhdi.orderMgr.vo.DistrBillListVo;
import gyqx.spdhdi.orderMgr.vo.DistrBillVo;
import gyqx.spdhdi.orderMgr.vo.PurchaseListVo;
import gyqx.spdherp.applyMgr.service.IDeptBuyMainService;
import gyqx.spdherp.applyMgr.service.IDeptBuySubService;
import gyqx.spdherp.stockMgr.Constance;
import gyqx.spdherp.stockMgr.dao.InStockDao;
import gyqx.spdherp.stockMgr.service.IInStockBatchService;
import gyqx.spdherp.stockMgr.service.IInStockListService;
import gyqx.spdherp.stockMgr.service.IInStockService;
import gyqx.spdherp.stockMgr.service.IInStockUniqueCodeService;
import gyqx.spdherp.stockout.service.OutBill4InService;
import gyqx.spdherp.stockout.vo.vo4In.Out4Settle;

import static gyqx.spdherp.stockMgr.Constance.INSTOCK_KIND_RETURNS;
import static gyqx.spdherp.stockMgr.Constance.INSTOCK_TYPE_TRUE;

@Service
public class InStockService implements IInStockService {
	private final Log logger = LogFactory.getLog(InStockService.class);
	@Resource
	private SimpleDao simpleDao;
	@Resource
	private IDeptBuyMainService deptMainService;
	@Resource
	private IDeptBuySubService deptSubService;
	@Resource
	private InStockDao  dao;
	@Resource
	private OutBill4InService o4iService;
	@Resource
	private IInStockListService detailService;
	@Resource
	private IInStockBatchService batchService;
	@Resource
	private IInStockUniqueCodeService codeService;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IDistrBillService distrService;
	@Resource
	private IPurchaseService purService;
	@Resource
	private DistrBillDao distrdao;
	@Resource
	private DistrBillListDao distrListdetailDao; // 明细表DAO
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
    @Resource
    private IFillBillService fillbillSer;
    @Resource
    private ISickerUseListService sickerUseLstSer;

    @Resource
	private IInStockBatchService inStockBatchService;
	@Autowired
	private ISurgeryPkgService surPkgSer;
    @Resource
	private BillMgrService billMgrService;
	@Override
	public InStockVo getByBillId(String billId) throws Exception {
        InStockVo inVo =  dao.getByBillId(billId);
        InStockListVo query = new InStockListVo();
        query.setPid(inVo.getId());
        List<InStockListVo> lstDetail = detailService.list(query);
        for (InStockListVo inStockListVo : lstDetail) {
            InStockBatchVo batchQ = new InStockBatchVo();
            batchQ.setPid(inStockListVo.getId());
            batchQ.setPRowId(inStockListVo.getInBillRow());
            InStockUniqueCodeVo codeQ = new InStockUniqueCodeVo();
            codeQ.setPid(inStockListVo.getId());
            codeQ.setPRowId(inStockListVo.getInBillRow());
            inStockListVo.setLstBatch(batchService.list(batchQ));
            inStockListVo.setLstUniqueCode(codeService.list(codeQ));
        }
        inVo.setLstDetail(lstDetail);
        return inVo;
	}

	public InStockVo getById(String id) throws Exception{
		InStockVo inVo =  dao.getById(id);
		InStockListVo query = new InStockListVo();
		query.setPid(inVo.getId());
		List<InStockListVo> lstDetail = detailService.list(query);
		for (InStockListVo inStockListVo : lstDetail) {
			InStockBatchVo batchQ = new InStockBatchVo();
			batchQ.setPid(inStockListVo.getId());
			batchQ.setPRowId(inStockListVo.getInBillRow());
			InStockUniqueCodeVo codeQ = new InStockUniqueCodeVo();
			codeQ.setPid(inStockListVo.getId());
			codeQ.setPRowId(inStockListVo.getInBillRow());
			List<InStockBatchVo> temps =	batchService.list(batchQ);
			inStockListVo.setLstBatch(temps);
			inStockListVo.setLstUniqueCode(codeService.list(codeQ));
			if(temps.size()>0)
			inStockListVo.setInPrice(temps.get(0).getInPrice());
		}
		inVo.setLstDetail(lstDetail);
		return inVo;
	}

	@Override
	public List<InStockVo> listByUniqueCode(List<String> qUniqueCodes) throws Exception {
		return dao.listByUniqueCode(qUniqueCodes);
	}

	@Override
	public InStockInfoVo getInStockInfoVo(List<String> uniqueCodes) throws Exception {
		if(uniqueCodes == null || uniqueCodes.size() == 0){
			return null;
		}
		InStockInfoVo res = new InStockInfoVo();
		res.setLstInStockBatch(batchService.listByUniqueCode(uniqueCodes));
		res.setLstInStockUnique(codeService.listByUniqueCode(uniqueCodes));
		res.setLstInStockDetail(detailService.listByUniqueCode(uniqueCodes));
		res.setLstInStock(this.listByUniqueCode(uniqueCodes));
		return res;
	}

	/**
	 * 采购入库查询入库单明细
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InStockVo getById4Instock(String id) throws Exception
	{
		return getById(id);
	}
	@Override
	public InStockVo add(InStockVo inStock) throws Exception{
		this.checkBill(inStock);
		String billId = atomUtil.newId("cgInStock").prefix(inStock.getInOrgId()).get();//atomUtil.newValue("cgInStock");
		inStock.setBillId(billId);
		inStock.setId(billId);
		List<InStockListVo> lstDetail = inStock.getLstDetail();
		List<InStockList> lstDetailPo = new ArrayList<>();
		List<InStockBatch> lstBatch;
		List<InStockUniqueCodeVo> lstUnCode = new ArrayList<>();
        //for (InStockListVo inStockListVo : lstDetail) {
		for (int i = 0; i<lstDetail.size(); i++) {
			InStockListVo inStockListVo = lstDetail.get(i);
            String detailId = atomUtil.newId("cgInStockDetail").prefix(inStock.getInOrgId()).get();//atomUtil.newValue("cgInStockDetail");
            inStockListVo.setId(detailId);
            inStockListVo.setPid(billId);
            inStockListVo.setBillId(inStock.getBillId());
            inStockListVo.setOutBillId(inStock.getOutBillId());
			inStockListVo.setInBillRow(i + 1);
			for (InStockUniqueCodeVo inStockUniqueCodeVo : inStockListVo.getLstUniqueCode()) {
				//String unqCodeId = atomUtil.newValue("instockUnCodeId");
				//inStockUniqueCodeVo.setId(unqCodeId);
				inStockUniqueCodeVo.setBillId(inStock.getBillId());
				inStockUniqueCodeVo.setPid(inStockListVo.getId());
				inStockUniqueCodeVo.setBillId(inStock.getBillId());
				inStockUniqueCodeVo.setPRowId(inStockListVo.getInBillRow());
				if(Constance.INSTOCK_KIND_SEND.equals(inStock.getInStockKind())){
					inStockUniqueCodeVo.setGoodsBatchId(inStock.getOutBillId());
				}else if(!org.springframework.util.StringUtils.hasText(inStockUniqueCodeVo.getGoodsBatchId())){
					inStockUniqueCodeVo.setGoodsBatchId(inStock.getBillId());
				}
				inStockUniqueCodeVo.setInTime(inStock.getFillDate());
				lstUnCode.add(inStockUniqueCodeVo);
			}
            lstDetailPo.add(inStockListVo);
        }
        lstBatch = buildBatchRecodes(inStock);
        this.writeBackStatus(inStock);              //回写各单据状态
		if(lstBatch.size()  > 0){
			batchService.insertByBatch(lstBatch);
		}
		if(lstUnCode.size()>0){
			codeService.insertByBatch(lstUnCode);
		}
		detailService.insertByBatch(lstDetailPo);
        dao.insert(inStock);

		// ------------------------------
        // 提交直接记帐
        String userId =inStock.getFiller(); //userOnlineStateUtils.getCurrent().getUserId();
        inStock.setAccounter(userId);
        inStock.setAuditor(userId);
        if(Constance.INSTOCK_STATUS_SUBMIT.equals(inStock.getStatus()) && !audit(inStock)){
            throw new ParameterException("生成入库单错误。");
        }
        // ------------------------------

		if (inStock.getInStockKind().equals(Constance.INSTOCK_KIND_DEPTAPPLY)
				&& Optional.ofNullable(inStock.getRemoveRowList()).orElse(Collections.emptyList()).size() > 0) {
			if (!billMgrService.updateOutStockByReturn(inStock.getRemoveRowList())) {
				throw new ParameterException("拒收失败，请联系管理员。");
			}
		}
		return inStock;
	}

	@Override
	public InStockVo addSup(InStockVo inStock) throws Exception {
		InStockVo inStockVo = inStock;
		InStockVo inStockVoSup;
		if (inStockVo.getInStockType() == INSTOCK_TYPE_TRUE) {
			Map<String, BigDecimal> batchQty = inStock.getLstDetail().stream()
					.flatMap(item -> item.getLstBatch().stream())
					.collect(Collectors.toMap(InStockBatch::getId, InStockBatch::getQty, (oldValue, newValue) -> newValue));
			for (InStockListVo islv : inStock.getLstDetail()) {
				for (InStockBatchVo isbv : islv.getLstBatch()) {
					isbv.setQty(BigDecimal.valueOf(0));
				}
			}
			inStockVoSup = this.add(inStock);
			//this.audit(inStock);
			inStock.getLstDetail().stream()
					.flatMap(item -> item.getLstBatch().stream())
					.forEach(item -> item.setQty(batchQty.get(item.getId())));
			this.update(inStockVoSup);
		} else {
			//高值产品正常入库，待补出库单之后再写库存量为0
			Map<String, BigDecimal> uniqueMap = inStock.getLstDetail().stream()
					.flatMap(item -> item.getLstUniqueCode().stream())
					.collect(Collectors.toMap(InStockUniqueCode::getId, InStockUniqueCode::getQty, (oldValue, newValue) -> newValue));
			for (InStockListVo islv : inStock.getLstDetail()) {
				for (InStockUniqueCodeVo isqv : islv.getLstUniqueCode()) {
					isqv.setQty(BigDecimal.valueOf(0));
				}
			}
			inStockVoSup = this.add(inStock);
			//this.audit(inStock);
			inStock.getLstDetail().stream()
					.flatMap(item -> item.getLstUniqueCode().stream())
					.forEach(item -> item.setQty(uniqueMap.get(item.getId())));
			this.update(inStockVoSup);
		}

		// TODO: 根据出库单全部退货，未考虑部分入库。其中上面部分入库直接将出库单状态改为60,存在问题
		// 生成供应商退货单return_prov_bill
		if (inStock.getInStockKind().equals(INSTOCK_KIND_RETURNS)) {
			if (!billMgrService.genReturnProvBillByInStock(inStock)) {
				throw new ParameterException("生成退货单失败");
			}
		}

		return inStockVoSup;
	}

	@Override
	public InStockVo add4Return(InStockVo inStock) throws Exception{
		this.checkBill(inStock);
		String billId = atomUtil.newId("cgInStock").prefix(inStock.getInOrgId()).get();//atomUtil.newValue("cgInStock");
		inStock.setBillId(billId);
		inStock.setId(billId);
		inStock.setRemark("计费退货");
		List<InStockListVo> lstDetail = inStock.getLstDetail();
        List<InStockList> lstDetailPo = new ArrayList<>();
		List<InStockBatch> lstBatch;
		List<InStockUniqueCodeVo> lstUnCode = new ArrayList<>();
		for (InStockListVo inStockListVo : lstDetail) {
			String detailId = atomUtil.newId("cgInStockDetail").prefix(inStock.getInOrgId()).get();
			inStockListVo.setId(detailId);
			inStockListVo.setPid(billId);
			inStockListVo.setBillId(inStock.getBillId());
			for (InStockUniqueCodeVo inStockUniqueCodeVo : inStockListVo.getLstUniqueCode()) {
				inStockUniqueCodeVo.setBillId(inStock.getBillId());
				inStockUniqueCodeVo.setPid(inStockListVo.getId());
				inStockUniqueCodeVo.setBillId(inStock.getBillId());
				inStockUniqueCodeVo.setPRowId(inStockListVo.getInBillRow());
				if(Constance.INSTOCK_KIND_SEND.equals(inStock.getInStockKind())){
					inStockUniqueCodeVo.setGoodsBatchId(inStock.getOutBillId());
				}else if(!org.springframework.util.StringUtils.hasText(inStockUniqueCodeVo.getGoodsBatchId())){
					inStockUniqueCodeVo.setGoodsBatchId(inStock.getBillId());
				}
				inStockUniqueCodeVo.setInTime(inStock.getFillDate());
				lstUnCode.add(inStockUniqueCodeVo);
			}
            lstDetailPo.add(inStockListVo);
		}
		lstBatch = buildBatchRecodes(inStock);
		this.writeBackStatus(inStock);              //回写各单据状态
		if(lstBatch.size() > 0){
			batchService.insertByBatch(lstBatch);
		}
		if(lstUnCode.size()>0){
			codeService.insertByBatch(lstUnCode);
		}
		detailService.insertByBatch(lstDetailPo);
		dao.insert(inStock);

		// ------------------------------
		// 提交直接记帐
		String userId = inStock.getFiller();
		inStock.setAccounter(userId);
		inStock.setAuditor(userId);
		if(inStock.getStatus() == 20 && !audit(inStock)){
			throw new ParameterException("生成入库单错误。");
		}
		// ------------------------------
		return inStock;
	}
	private void checkBill(InStockVo inStock) throws Exception{
		if(StringUtils.isNullOrEmpty(inStock.getInStocId())){
			ValidateException.Throw("stocId","入库库房不能为空","");
		}
		if(inStock.getLstDetail().size() == 0){
			ValidateException.Throw("lstDetail","明细行不能为空","");
		}
		for (InStockListVo inStockListVo : inStock.getLstDetail()) {
			if(inStockListVo.getInQty() == null || inStockListVo.getInQty().compareTo(new BigDecimal(0)) == 0){
				ValidateException.Throw("hosPurQty",String.format("第%d行：%s的数量必须大于零",inStockListVo.getInBillRow(),inStockListVo.getGoodsName()),"");
			}
		}
	}
	public List<InStockBatch> buildBatchRecodes(InStockVo inStock)	{
		List<InStockListVo> lstDetail = inStock.getLstDetail();
		List<InStockBatch> lstBatch = new ArrayList<>();
		for (InStockListVo inStockListVo : lstDetail) {
			//if(Constance.INSTOCK_KIND_SEND.equals(inStock.getInStockKind()) || Constance.INSTOCK_KIND_ORIGINAL.equals(inStock.getInStockKind())){
              if(inStockListVo.getLstBatch() == null || inStockListVo.getLstBatch().size()==0){
                //手工入库和采购入库生成批次号（即入库单号）
                InStockBatchVo inBatchVo = new InStockBatchVo();
				//String batchId = atomUtil.newValue("inStockBatchId");
				//inBatchVo.setId(batchId);
				inBatchVo.setBillId(inStock.getBillId());
				inBatchVo.setPid(inStockListVo.getId());
				inBatchVo.setPRowId(inStockListVo.getInBillRow());
				inBatchVo.setProvId(inStockListVo.getProvId());
				inBatchVo.setGoodsId(inStockListVo.getGoodsId());
				//inBatchVo.setGoodsBatchId(inStockListVo.getBatchCode());
				inBatchVo.setInPrice(inStockListVo.getInPrice()==null?new BigDecimal(0):inStockListVo.getInPrice());
				inBatchVo.setQty(inStockListVo.getInQty());
				inBatchVo.setInTime(inStock.getFillDate());
				if(inStock.getInStockKind().equals(Constance.INSTOCK_KIND_SEND)){		//采购入库为配送单号
					inBatchVo.setGoodsBatchId(inStock.getOutBillId());
				}else if(inStock.getInStockKind().equals(Constance.INSTOCK_KIND_ORIGINAL)){
					inBatchVo.setGoodsBatchId(inStock.getBillId());							//手工入库为入库单号
				}
				String bigBatchCode = inBatchVo.getBigBatchC(inStockListVo,inStock.getInStockType());
			  	inBatchVo.setBigBatchCode(bigBatchCode);//大批号
				lstBatch.add(inBatchVo);
			}else {
				for (InStockBatchVo inStockBatchVo : inStockListVo.getLstBatch()) {
					inStockBatchVo.setBillId(inStock.getBillId());
					inStockBatchVo.setPid(inStockListVo.getId());
					inStockBatchVo.setPRowId(inStockListVo.getInBillRow());
					inStockBatchVo.setInTime(inStock.getFillDate());
					inStockBatchVo.setProvId(inStockListVo.getProvId());
					inStockBatchVo.setGoodsId(inStockListVo.getGoodsId());
					String sqlStr,bigBatchCode;
					if(org.springframework.util.StringUtils.hasText(inStockListVo.getBatchCode())){
						sqlStr="select big_batch_code from hos_stockpile where hos_goods_id=? and batch_id=? and batch_no =? limit 1";
						bigBatchCode =  simpleDao.queryForObject(sqlStr,String.class,inStockListVo.getGoodsId(),inStockBatchVo.getGoodsBatchId(),inStockListVo.getBatchCode());
					}else{
						sqlStr = "select big_batch_code from hos_stockpile where hos_goods_id=? and batch_id=? and (batch_no = '' or batch_no is null ) limit 1";
						bigBatchCode =  simpleDao.queryForObject(sqlStr,String.class,inStockListVo.getGoodsId(),inStockBatchVo.getGoodsBatchId());
					}
					// 请购入库大批号修改，采用采购入库生成的大批号
					if(StringUtils.isNullOrEmpty(bigBatchCode)){
					  	bigBatchCode = inStockBatchVo.getBigBatchC(inStockListVo,inStock.getInStockType());						//大批号
						inStockBatchVo.setBigBatchCode(bigBatchCode);
				  	}
					lstBatch.add(inStockBatchVo);
				}
			}
		}
		return lstBatch;
	}

	private void writeBackStatus(InStockVo inStock) throws Exception{
	    switch (inStock.getInStockKind()){
            case Constance.INSTOCK_KIND_DEPTAPPLY:                          //请领入库 修改出库单的状态为6
				o4iService.updateOutBillStatusByInstock(inStock);           //回写出库单状态
                this.setSurgeryStatus(inStock);

                break;
            case Constance.INSTOCK_KIND_SEND:
                this.setPurStatus(inStock);
                this.setDistrBillStatus(inStock);
                break;
            case Constance.INSTOCK_KIND_MOVE:
            case Constance.INSTOCK_KIND_RETURNS:
                o4iService.updateOutBillStatus(inStock.getOutBillId(), gyqx.spdherp.stockout.Constance.OUTSTOCKBILL_STATUS_INSTOCK);
                break;
			case Constance.INSTOCK_KIND_RETURNWITHMOEY:					//退货入库回写病人消耗单
				//// 退货入库回写病人消耗单
                List<SickerUserListVo> lstSickerUserDetail = inStock.getLstDetail().stream()
                        .map(o->{
                            SickerUserListVo sulv = new SickerUserListVo();
                            sulv.setPId(o.getOutBillId());
                            sulv.setRowNum(o.getOutBillRow());
                            sulv.setReturnQty(o.getInQty().abs());
                            return sulv;
                        }).collect(Collectors.toList());
                sickerUseLstSer.update4Return(lstSickerUserDetail);
                //// fillbill
				List<String> lstUniqueCodes = inStock.getLstDetail().stream()
						.flatMap(item -> item.getLstUniqueCode().stream())
						.map(InStockUniqueCode::getUniqueCode).collect(Collectors.toList());
                fillbillSer.createFillBillByUniqueCode(lstUniqueCodes,-1);
				break;

        }
    }

    /**
     * 回写手术包状态
     * @param instock
     * @throws Exception
     */
    private void setSurgeryStatus(InStockVo instock) throws Exception{
    	List<InStockListVo> lstDetail = instock.getLstDetail();
        if(StringUtils.isNullOrEmpty(lstDetail.get(0).getSurCode())){
            return;
        }
        List<SurgeryPkg> lstSur = new ArrayList<>();
		lstDetail.stream().map(item->item.getSurCode()).collect(Collectors.toSet()).forEach(surCode->{
			SurgeryPkgVo spv = new SurgeryPkgVo();
			spv.setId(surCode);
			spv.setCurStockId(instock.getInStocId());
			spv.setStatus(Constants.SUR_PKG_STATUS_QGRK);
			lstSur.add(spv);
		});
		surPkgSer.updateStatusByBatch(lstSur);
    }
	/**
	 * 回写采购单状态
	 * @param inStock
	 * @return
	 */
	public void setPurStatus(InStockVo inStock) throws Exception{
		List<InStockListVo> lstInStockDetail = inStock.getLstDetail();
		if(!Constance.INSTOCK_KIND_SEND.equals(inStock.getInStockKind())){
			return ;
		}
		if(StringUtils.isNullOrEmpty(inStock.getOutBillId())){
			return;
		}
		//查询配送单信息
		DistrBillVo distrBill = distrService.getByBillId(inStock.getOutBillId());
		if(distrBill != null && distrBill.getDistrBillList() !=null && distrBill.getDistrBillList().size()>0){//配送单明细不为空
			Map<String,List<PurchaseListVo>> purMap = new HashMap<>();
			for (InStockListVo inStockListVo : lstInStockDetail) {//循环入库单明细
				Optional<DistrBillListVo> opDetail = distrBill.getDistrBillList().stream().filter(item->item.getBillId().equals(inStock.getOutBillId())
						&& inStockListVo.getOutBillRow() ==item.getRowNumber()).findFirst();
				if(opDetail.isPresent()){
					DistrBillListVo distrDetail = opDetail.get();
					PurchaseListVo purLstVo = new PurchaseListVo();
					purLstVo.setRowNumber(distrDetail.getPurRowNumber());
					purLstVo.setBillId(distrDetail.getPurBillId());
					purLstVo.setStatus(gyqx.spdhdi.orderMgr.Constance.PURCHASE_LIST_STATUS_RECEIVE);
					if(purMap.containsKey(distrDetail.getPurBillId())){
						purMap.get(distrDetail.getPurBillId()).add(purLstVo);
					}else{
						List<PurchaseListVo> lst = new ArrayList<>();
						lst.add(purLstVo);
						purMap.put(distrDetail.getPurBillId(),lst);
					}
				}
			}
			purService.updatePurStatus(purMap);
		}
		return ;
	}
    /**
     * 回写配送单状态
     * @param inStock
     * @return
     */
	private void setDistrBillStatus(InStockVo inStock) throws Exception{
		List<InStockListVo> lstInStockDetail = inStock.getLstDetail();
		if(!Constance.INSTOCK_KIND_SEND.equals(inStock.getInStockKind())){
			return ;
		}
		if(StringUtils.isNullOrEmpty(inStock.getOutBillId())){
			return;
		}
		// 获取配送单
		DistrBillVo distrBill = distrService.getByBillId(inStock.getOutBillId());
		distrBill.setStatus(gyqx.spdhdi.orderMgr.Constance.DISTRBILL_STATUS_SOMEREC);
		BigDecimal allSend = new BigDecimal(0);
		BigDecimal allRec = new BigDecimal(0);
		for (DistrBillListVo dlst : distrBill.getDistrBillList()) {
			Optional<InStockListVo> inslOp = lstInStockDetail.stream().filter(o->dlst.getBillId().equals(o.getOutBillId()) && dlst.getRowNumber().equals(o.getOutBillRow())).findFirst();
			if(inslOp.isPresent()){
				InStockListVo tmp = inslOp.get();
				BigDecimal recQty  = tmp.getInQty().multiply(dlst.getSendScale());
				dlst.setReceiveQty(dlst.getReceiveQty().add(recQty));
				int flag = dlst.getSendQty().compareTo(dlst.getReceiveQty());
				if (flag < 0) {
					ValidateException.Throw("SendQty", String.format("%s收货数量大于发货数量", dlst.getHosGoodsName()), "");
				} else if (flag == 0) {
					dlst.setStatus(gyqx.spdhdi.orderMgr.Constance.DISTRBILL_DETAIL_STATUS_ALLREC);
				} else if (flag > 0) {
					dlst.setStatus(gyqx.spdhdi.orderMgr.Constance.DISTRBILL_DETAIL_STATUS_SOMEREC);
				}
			}
            allSend =allSend.add(dlst.getSendQty());
			allRec=allRec.add(dlst.getReceiveQty().add(dlst.getRejectedQty()));
		}
		if(allRec.compareTo(allSend)==0){
            distrBill.setStatus(gyqx.spdhdi.orderMgr.Constance.DISTRBILL_STATUS_ALLREC);
        }
		distrdao.update(distrBill);
		distrListdetailDao.updateByBatch(distrBill.getDistrBillList());
	}

    public InStockVo update(InStockVo inStock) throws Exception {
        List<InStockListVo> lstDetail = inStock.getLstDetail();
        List<InStockBatch> lstBatch;
        List<InStockUniqueCodeVo> lstUnCode = new ArrayList<>();
        List<InStockList> lstDetailPo = new ArrayList<>();
        for (InStockListVo inStockListVo : lstDetail) {
            String detailId = atomUtil.newId("cgInStockDetail").prefix(inStock.getInOrgId()).get();
            inStockListVo.setId(detailId);
            inStockListVo.setPid(inStock.getId());
            inStockListVo.setBillId(inStock.getBillId());
            for (InStockUniqueCodeVo inStockUniqueCodeVo : inStockListVo.getLstUniqueCode()) {
                //String unqCodeId = atomUtil.newValue("instockUnCodeId");
                //inStockUniqueCodeVo.setId(unqCodeId);
                inStockUniqueCodeVo.setBillId(inStock.getBillId());
                inStockUniqueCodeVo.setPid(inStockListVo.getId());
                inStockUniqueCodeVo.setBillId(inStock.getBillId());
                inStockUniqueCodeVo.setPRowId(inStockListVo.getInBillRow());
                inStockUniqueCodeVo.setGoodsBatchId(inStock.getBillId());
                inStockUniqueCodeVo.setInTime(inStock.getFillDate());
                lstUnCode.add(inStockUniqueCodeVo);
            }
            lstDetailPo.add(inStockListVo);
        }
        lstBatch = buildBatchRecodes(inStock);
        batchService.delByBillId(inStock.getBillId());
        codeService.delByBillId(inStock.getBillId());
        detailService.delByBillId(inStock.getBillId());
        if(lstBatch.size() > 0){
            batchService.insertByBatch(lstBatch);
        }
        if(lstUnCode.size()>0){
            codeService.insertByBatch(lstUnCode);
        }
        detailService.insertByBatch(lstDetailPo);
        dao.update(inStock);
        return inStock;
	}

	public List<InStockVo> list(InStockVo bean) throws Exception{
		return dao.list(bean);
	}

	public QueryResult<InStockVo> listByPage(QueryInfo<InStockVo> queryInfo) throws Exception{
		//获取当前登陆人,根据人员与供应商关系查询供应商信息
		String userId = userOnlineStateUtils.getCurrent().getUserId();
		String hosId = userOnlineStateUtils.getCurrent().getCorpId();
		String inStockind = queryInfo.getQueryObject().getInStockKind();
		queryInfo.getQueryObject().setUserId(userId);
		queryInfo.getQueryObject().setHosId(hosId);
		//入库类型 1：采购入库  2：请购入库
		if (org.apache.commons.lang3.StringUtils.equals(inStockind, "1")) {
			PageUtils.startPage(queryInfo);
			return PageUtils.endPage(dao.purchaseList(queryInfo.getQueryObject()));
		} else {
			PageUtils.startPage(queryInfo);
			return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
		}
	}
    @Override
    public boolean audit(InStockVo inStock) throws Exception {
		if(inStock.getInStockKind().equals(Constance.INSTOCK_KIND_DEPTAPPLY)){
            String applyBillId  = simpleDao.queryForObject("select source_bill_id from out_stock where bill_id=?",String.class,inStock.getOutBillId());
            if(!org.springframework.util.StringUtils.isEmpty(applyBillId)){
                deptSubService.updateMainStateByBillId(applyBillId);
            }
			if(org.springframework.util.StringUtils.hasText(inStock.getLstDetail().get(0).getSurCode())){//手术包请购入库直接改状态无需更新库存表
				return dao.charge4Sur(inStock.getBillId(),inStock.getFiller())>0;
			}
		}
        dao.auditAndunAudit(inStock,true);
        inStock.setVersion(inStock.getVersion()+1);

        List<OutStock> outStocks = simpleDao.queryByFieldName(OutStock.class, "billId",  inStock.getOutBillId());
        if(outStocks.size()>0){
        	 OutStock  out=	outStocks.get(0);
        	 if(!org.springframework.util.StringUtils.isEmpty(out.getSourceBillId())){
                 int state = 	 deptSubService.updateMainStateByBillId(out.getSourceBillId());

        	 }
        }


        return dao.charge(inStock);
    }

    @Override
    public boolean unAudit(InStockVo inStock) throws Exception {
        return dao.auditAndunAudit(inStock,false);
    }

    @Override
    public boolean charge(InStockVo inStock) throws Exception {
        return dao.charge(inStock);
    }

	@Override
	public QueryResult<Out4Settle> inBill4settle(QueryInfo<Out4Settle> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.inBill4settle(queryInfo.getQueryObject()));
	}

    @Override
    public int addFillInstockBill(List<String> uniqueCode,List<OutStockUniqueCode> outUniqueCodes,Integer flag, String execDeptId) throws Exception {
        InStockInfoVo inStockInfo= this.getInStockInfoVo(uniqueCode);
        List<InStockVo> lstInstockBill = inStockInfo.getLstInStock();
        List<InStock> lstInStock = new ArrayList<>();
        List<InStockList> lstInstockList = new ArrayList<>();
        List<InStockBatch> lstBatch = new ArrayList<>();
        List<InStockUniqueCode> lstUniqueCode = new ArrayList<>();
        for (InStockVo inStockVo : lstInstockBill) {
            if (flag < 0 && inStockVo.getInStockType() == Constance.INSTOCK_TYPE_FAKE) {
                continue;
            } else if (flag > 0 && inStockVo.getInStockType() == Constance.INSTOCK_TYPE_TRUE) {
                continue;
            }
            String oldBillId = inStockVo.getBillId();
            String billId = atomUtil.newId("cgInStock").prefix(inStockVo.getInOrgId()).get();
            inStockVo.setId(billId);
            inStockVo.setBillId(billId);
            inStockVo.setInStockType(INSTOCK_TYPE_TRUE);
            inStockVo.setStatus(Constance.INSTOCK_STATUS_CHARGE);
            if(inStockVo.getInStockKind().equals(Constance.INSTOCK_KIND_DEPTAPPLY)) {
                inStockVo.setRemark(flag < 0 ? "计费退货补请购入库单冲红" : "病人消耗补请购入库单");
				if (null != execDeptId) {
					inStockVo.setInDeptId(execDeptId);
				}
            }else if(inStockVo.getInStockKind().equals(Constance.INSTOCK_KIND_SEND)){
                inStockVo.setRemark(flag < 0 ? "计费退货补采购入库单冲红" : "病人消耗补采购入库单");
            }
            List<InStockListVo> lstDetail = inStockInfo.getLstInStockDetail().stream().filter(detail->detail.getBillId().equals(oldBillId)).collect(Collectors.toList());
            int i=1;
            for (InStockListVo inStockListVo : lstDetail) {
                Integer oldRowNum = inStockListVo.getInBillRow();
                String detailId = atomUtil.newId("cgInStockDetail").prefix(inStockVo.getInOrgId()).get();
                inStockListVo.setId(detailId);
                inStockListVo.setPid(billId);
                inStockListVo.setBillId(billId);
                inStockListVo.setInBillRow(i);
                Optional<InStockBatchVo> batchVoOptional = inStockInfo.getLstInStockBatch().stream().filter(batchItem->oldBillId.equals(batchItem.getBillId()) && oldRowNum.equals(batchItem.getPRowId())).findFirst();
                List<InStockUniqueCodeVo> lstUnique = inStockInfo.getLstInStockUnique().stream().filter(unique->oldBillId.equals(unique.getBillId()) && oldRowNum.equals(unique.getPRowId())).collect(Collectors.toList());
                inStockListVo.setInQty(new BigDecimal(lstUnique.size()*flag));
                inStockListVo.setOutQty(new BigDecimal(lstUnique.size()*flag));
                if(batchVoOptional.isPresent()){
                    InStockBatchVo batch = batchVoOptional.get();
                    batch.setPid(detailId);
                    batch.setBillId(billId);
                    batch.setPRowId(i);
                    batch.setQty(new BigDecimal(lstUnique.size()*flag));
                    lstBatch.add(batch);
                }
                for (InStockUniqueCodeVo inStockUniqueCodeVo : lstUnique) {
                    inStockUniqueCodeVo.setPid(detailId);
                    inStockUniqueCodeVo.setBillId(billId);
                    inStockUniqueCodeVo.setPRowId(i);
                    if(inStockVo.getInStockKind().equals(Constance.INSTOCK_KIND_DEPTAPPLY)){
                       Optional<OutStockUniqueCode>  op = outUniqueCodes.stream().filter(outBillUniqueCode -> inStockUniqueCodeVo.getUniqueCode().equals(outBillUniqueCode.getUniqueCode())).findAny();
                       if(op.isPresent()){
                           OutStockUniqueCode osuc = op.get();
                           inStockVo.setOutBillId(osuc.getBillId());
                           inStockListVo.setOutBillId(osuc.getBillId());
                           inStockListVo.setOutBillRow(osuc.getPRowId());
                       }
                    }
                }
                lstInstockList.add(inStockListVo);
            }
            lstInStock.add(inStockVo);
        }
        int resCount = 0;
        if(lstBatch.size() > 0){
            resCount += batchService.insertByBatch(lstBatch);
        }
        if(inStockInfo.getLstInStockUnique().size()>0){
            resCount += codeService.insertByBatch(inStockInfo.getLstInStockUnique());
        }
        if(lstInstockList.size()>0){
            resCount += detailService.insertByBatch(lstInstockList);
        }
        if(lstInStock.size()>0){
            resCount +=  dao.insertByBatch(lstInStock);
        }
        return  resCount;
    }

	@Override
	public int addFillInstockBillByBigBatchCode(List<BigBatch4FillVo> bigBatchCodes, List<EntireOutStockListVo> lstOutList, Integer flag, String execDeptId) throws Exception {
	    List<InStock> lstInStock = new ArrayList<>();
	    List<InStockList> lstInStockList=new ArrayList<>();
	    List<InStockBatch> lstBatch = new ArrayList<>();
		List<FillInstockVo> lstCG = dao.getCGByBigBatchCode(bigBatchCodes);//获取要补的采购入库单
        List<FillInstockVo> lstQG = dao.getQGByBigBatchCode(bigBatchCodes,execDeptId);//获取要补的请购入库单
        lstCG.addAll(lstQG);
		for (FillInstockVo inStock : lstCG) {
            String billId = atomUtil.newId("cgInStock").prefix(inStock.getInOrgId()).get();
            inStock.setId(billId);
            inStock.setBillId(billId);
            inStock.setInStockType(INSTOCK_TYPE_TRUE);
            inStock.setStatus(Constance.INSTOCK_STATUS_CHARGE);
            if(inStock.getInStockKind().equals(Constance.INSTOCK_KIND_DEPTAPPLY)) {
                inStock.setRemark(flag < 0 ? "计费退货补请购入库单冲红" : "病人消耗补请购入库单");
            }else if(inStock.getInStockKind().equals(Constance.INSTOCK_KIND_SEND)){
                inStock.setRemark(flag < 0 ? "计费退货补采购入库单冲红" : "病人消耗补采购入库单");
            }
			for (int i = 0; i < inStock.getLstDetail().size(); i++) {
				FillInstockListVo inStockDetail = inStock.getLstDetail().get(i);
				String detailId = atomUtil.newId("cgInStockDetail").prefix(inStock.getInOrgId()).get();
				inStockDetail.setId(detailId);
				inStockDetail.setPid(billId);
				inStockDetail.setBillId(billId);
				inStockDetail.setInBillRow(i);
				BigDecimal total=new BigDecimal(0);
				for (FillInstockBatchVo batch : inStockDetail.getLstBatch()) {
					batch.setPid(detailId);
					batch.setBillId(billId);
					batch.setPRowId(i);
					BigDecimal qty = null;
					if(inStock.getInStockKind().equals(Constance.INSTOCK_KIND_SEND)){//采购入库 数量为大批次相加之和
						qty = bigBatchCodes.stream().filter(o->o.getBigBatchCode().equals(batch.getBigBatchCode())).map(o->o.getQty()).reduce((cur,next)->{
							return cur.add(next);
						}).get();
					}else if(inStock.getInStockKind().equals(Constance.INSTOCK_KIND_DEPTAPPLY)){//请购入库还要写入出库单单号和行号
						qty = bigBatchCodes.stream()
								.filter(o->o.getSurCode().equals(inStockDetail.getSurCode()) && o.getBigBatchCode().equals(batch.getBigBatchCode()))
								.findFirst().get().getQty();
						Optional<EntireOutStockListVo>  op = lstOutList.stream()
								.filter(outLst -> outLst.getSurCode().equals(inStockDetail.getSurCode())
										&& outLst.getOutStockBatchList().stream()
										.filter(outbatch->outbatch.getBigBatchCode().equals(batch.getBigBatchCode())).count()>0).findAny();
						if(op.isPresent()){
							EntireOutStockListVo osuc = op.get();
							inStock.setOutBillId(osuc.getBillId());
							inStockDetail.setOutBillId(osuc.getBillId());
							inStockDetail.setOutBillRow(osuc.getOutBillRow());
						}
					}

					batch.setQty(qty.multiply(new BigDecimal(flag)));
					total = total.add(batch.getQty());
					lstBatch.add(batch);
				}
				inStockDetail.setInQty(total);
				lstInStockList.add(inStockDetail);
			}
			lstInStock.add(inStock);
        }
		int resCount = 0;
		if(lstBatch.size() > 0){
			resCount += batchService.insertByBatch(lstBatch);
		}
		if(lstInStockList.size()>0){
			resCount += detailService.insertByBatch(lstInStockList);
		}
		if(lstInStock.size()>0){
			resCount +=  dao.insertByBatch(lstInStock);
		}
		return  resCount;
	}
}
