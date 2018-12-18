package gyqx.spdherp.stockout.service.impl;

import javax.annotation.Resource;

import common.db.SimpleDao;
import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.po.OutStockUniqueCode;
import gyqx.spdherp.stockout.Constance;
import gyqx.spdherp.stockout.service.IFillBillService;
import gyqx.spdherp.stockout.vo.OutStockBillVo;
import gyqx.spdherp.stockout.vo.SickerUserListVo;
import gyqx.spdherp.stockout.vo.SickerUserVo;
import gyqx.spdherp.stockout.vo.Sickuse4print;
import gyqx.spdherp.stockout.vo.SickuseGoods4print;

import gyqx.spdherp.surgery.service.ISurgeryPkgService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import org.springframework.util.StringUtils;

import com.github.pagehelper.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.stockout.dao.OutBill4InDao;
import gyqx.spdherp.stockout.dao.SickerUseDao;
import gyqx.spdherp.stockout.dao.SickerUseListDao;
import gyqx.spdherp.stockout.service.ISickerUseService;

@Service
public class SickerUseService implements ISickerUseService {

    @Resource
    private SickerUseDao dao;
    @Resource
    private SickerUseListDao userListdao;
    @Resource
    private SimpleDao simpleDao;
    @Resource
    private OutBill4InDao o4iDao;
    @Resource
    private SysAtomUtil atomUtil;
    @Resource
    private IFillBillService fillBillService;
    @Autowired
    private ISurgeryPkgService surPkgSer;
    public SickerUserVo get(String id) throws Exception {
    	SickerUserVo SickerUserVo = dao.get(id);
    	List<SickerUserListVo> detailList= userListdao.getListByBillId(id);
    	SickerUserVo.setLstDetail(detailList);
        return SickerUserVo;
    }

    public int add(SickerUserVo sickerUse) throws Exception {
        String useId = atomUtil.newValue("sicker_use_id");
        sickerUse.setId(useId);
        List<SickerUserListVo> sickerUserList = sickerUse.getLstDetail();
        if (sickerUserList != null && sickerUserList.size() > 0) {
            for (int i = 0; i < sickerUserList.size(); i++) {
                sickerUserList.get(i).setPId(useId);
                sickerUserList.get(i).setPurMode(Constance.OUTSTOCKBILL_TYPE_FAKE);
                sickerUserList.get(i).setRowNum(i);
                sickerUserList.get(i).setStatus(Constance.BEFORE_CANCEL);
            }
            //主表录入
            int res = dao.insert(sickerUse);
            //字表录入
            userListdao.insertByBatch(sickerUserList);
            //高值产品补实库单据
            if (Constance.SICKER_STATUS_SUMIT.equals(sickerUse.getStatus())) {
                this.setStockPile(sickerUserList,sickerUse.getFiller());                //删除库存占用信息
                this.setOutBillStatus(sickerUse.getLstDetail());                        //回写领用出库单（科室出库）状态
                surPkgSer.setSurStatusBySickerUse(sickerUserList);                      //手术包消耗要改写手术包状态
                fillBillService.createFillBillBySickerUse(sickerUse);                   //补单据
            }
            return 0;
        }

        return 0;
    }
    private void setStockPile(List<SickerUserListVo> sickerUserList,String fillerMan){
        if(sickerUserList == null || sickerUserList.size()==0 || StringUtils.hasText(sickerUserList.get(0).getSurId())){    //手术包的消耗单不影响库存和库存占用
            return;
        }
        for (SickerUserListVo aSickerUserList : sickerUserList) {
            OutStockBillVo outStockBillVo = new OutStockBillVo();
            outStockBillVo.setBillId(aSickerUserList.getId());
            outStockBillVo.setAccounter(fillerMan);
            outStockBillVo.setVersion(aSickerUserList.getVersion());
            o4iDao.stockpilebatch(outStockBillVo); //减库存，调用存储过程
        }
    }
    public void setOutBillStatus(List<SickerUserListVo> lst) throws Exception {
        //按出库单编号分组
        Map<String, List<SickerUserListVo>> collect = lst.stream()
                .collect(Collectors.groupingBy(SickerUserListVo::getOutBillId));
        collect.forEach((outBillId, lstSul) -> {
            List<OutStockUniqueCode> uniqueCodeList = o4iDao.getUniqueCodeList(outBillId);
            for (SickerUserListVo aLstSul : lstSul) {
                Integer outBillRow = aLstSul.getOutBillRow();
                String uniqueCode = aLstSul.getSelfCode();
                for (OutStockUniqueCode outStockUniqueCode : uniqueCodeList) {
                    if (outStockUniqueCode.getPRowId() == outBillRow && outStockUniqueCode.getUniqueCode().equals(uniqueCode)) {
                        outStockUniqueCode.setIsUsed(1);
                    }
                }
            }
            long count = uniqueCodeList.stream().filter(unique -> unique.getIsUsed() == null || unique.getIsUsed() == 0).count();

            OutStock outStock = new OutStock();
            outStock.setBillId(outBillId);
            if (count == 0) {
                //全部验收
                outStock.setStatus(Constance.OUTSTOCKBILL_ALLUSE_STATUS);
            } else {
                //未全部验收
                outStock.setStatus(Constance.OUTSTOCKBILL_HALFUSE_STATUS);
            }
            o4iDao.updateOutStockStatus(outStock);
            //update outStockBill
            //update 唯一码表信息
            List<OutStockUniqueCode> uniqueCLst = uniqueCodeList.stream().filter(unique -> unique.getIsUsed() != null && unique.getIsUsed() == 1).collect(Collectors.toList());
            if (uniqueCLst!=null&&uniqueCLst.size()!=0) {
            	o4iDao.updateUniqueCodesUsed(uniqueCLst);
			}
        });
    }

    public int update(SickerUserVo sickerUse) throws Exception {
        if (Constance.SICKER_STATUS_SUMIT.equals(sickerUse.getStatus())) {        //提交回写领用出库单（科室出库）状态
            this.setStockPile(sickerUse.getLstDetail(),sickerUse.getFiller());                //删除库存占用信息
            this.setOutBillStatus(sickerUse.getLstDetail());
            fillBillService.createFillBillBySickerUse(sickerUse);                   //补单据
        }
        return dao.update(sickerUse);
    }

    public QueryResult<SickerUserVo> list(QueryInfo<SickerUserVo> queryInfo) throws Exception {
    	PageUtils.startPage(queryInfo);
        return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
    }

    public QueryResult<SickerUserVo> listByPage(QueryInfo<SickerUserVo> queryInfo) throws Exception {

        PageUtils.startPage(queryInfo);
        return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
    }

	@Override
	public Sickuse4print getSickuserList4Print(String billId) {
		// TODO Auto-generated method stub
		Sickuse4print sickuse4print = dao.getSickuser4Print(billId);
		List<SickuseGoods4print> sickuse4printList = dao.getSickuseGoods4print(billId);
		sickuse4print.setDetaill(sickuse4printList);
		return sickuse4print;
	}
}

