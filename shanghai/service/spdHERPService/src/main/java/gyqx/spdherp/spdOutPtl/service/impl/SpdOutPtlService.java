package gyqx.spdherp.spdOutPtl.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import common.exception.ParameterException;
import common.utils.SysAtomUtil;
import common.utils.UtilsContext;
import gyqx.spdherp.po.JdeGoodsStockInfo;
import gyqx.spdherp.spdOutPtl.service.IJdeGoodsStockInfoService;
import gyqx.spdherp.spdOutPtl.vo.DeptBuyForSpdPtl;
import gyqx.spdherp.spdOutPtl.vo.MessageResponse;
import gyqx.spdherp.spdOutPtl.vo.OutStockForSpdPtl;
import gyqx.spdherp.stockout.dao.ReturnOutDao;
import gyqx.spdherp.stockout.vo.OutStockBillSupVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.SpdOutPtl;
import gyqx.spdherp.spdOutPtl.dao.SpdOutPtlDao;
import gyqx.spdherp.spdOutPtl.service.ISpdOutPtlService;

@Service
public class SpdOutPtlService implements ISpdOutPtlService {

    @Resource
    private SpdOutPtlDao dao;

    @Resource
    private UtilsContext utilsContext;

    @Resource
    private SysAtomUtil sysAtomUtil;

//    @Resource
//    private GroovyService groovyService;

    @Resource
    private IJdeGoodsStockInfoService jdeService;

    public SpdOutPtl get(String id) {
        return dao.get(id);
    }

    public SpdOutPtl add(SpdOutPtl spdOutPtl) throws Exception {
        return dao.insertAndGet(spdOutPtl);
    }

    public SpdOutPtl update(SpdOutPtl spdOutPtl) throws Exception {
        return dao.updateAndGet(spdOutPtl);
    }

    public void delete(SpdOutPtl spdOutPtl) throws Exception {
        dao.delete(spdOutPtl);
    }

    public List<SpdOutPtl> query(String predicate, String orderBy, Object... fieldValue) throws Exception {
        return (List<SpdOutPtl>) dao.query(predicate, orderBy, fieldValue);
    }

    public List<SpdOutPtl> list(SpdOutPtl bean) throws Exception {
        return (List<SpdOutPtl>) dao.list(bean);
    }

    public QueryResult<SpdOutPtl> listByPage(QueryInfo<SpdOutPtl> queryInfo) throws Exception {

        PageUtils.startPage(queryInfo);
        return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
    }

    @Override
    public boolean insertSpdOutPtlLists(List<SpdOutPtl> list) throws Exception {
        return dao.insertSpdOutPtlLists(list) > 0;
    }

    @Override
    public List<DeptBuyForSpdPtl> getPurListInfo(String billId) throws Exception {
        String provId = utilsContext.getSysConfigUtil().getValue("gyqx.provId");
        return dao.getPurListInfo(billId, provId);
    }

    @Override
    public boolean forPurSpdOutPtl(String billId) throws Exception {
        List<DeptBuyForSpdPtl> deptBuyForSpdPtls = this.getPurListInfo(billId);
        List<SpdOutPtl> ptlList = new ArrayList<>();
        if (deptBuyForSpdPtls == null) {
            throw new ParameterException("未找到该请购单对应的明细行!");
        }
        JdeGoodsStockInfo jdeGoodsStockInfo = new JdeGoodsStockInfo();
        for (int i = 0; i < deptBuyForSpdPtls.size(); i++) {
            String erpCode = deptBuyForSpdPtls.get(i).getErpCode();
            String goodsId = deptBuyForSpdPtls.get(i).getGoodsId();
            String shelfId;
            String barCode;
            jdeGoodsStockInfo.setErpCode(erpCode);
            List<JdeGoodsStockInfo> jdeGoodsStockInfos = jdeService.list(jdeGoodsStockInfo);
            if(jdeGoodsStockInfos.size()==0){
                shelfId = "empty";
                barCode = "empty";
            }else if(jdeGoodsStockInfos.get(0).getGoodsStockLocation().equals("合格品")){
                shelfId = "empty";
                barCode = jdeGoodsStockInfos.get(0).getBatchCode();
            }else {
                shelfId = jdeGoodsStockInfos.get(0).getGoodsStockLocation().substring(0,11);
                barCode = jdeGoodsStockInfos.get(0).getBatchCode();
            }
            SpdOutPtl spdOutPtl = new SpdOutPtl();
            String id = utilsContext.getSysAtomUtil().newValue("spd_out_ptl_id");
            spdOutPtl.setId(id);
            spdOutPtl.setSerialno(i + 1);
            spdOutPtl.setOrderid(billId);
            //货位编码
            spdOutPtl.setLocation(shelfId);
            spdOutPtl.setName(deptBuyForSpdPtls.get(i).getGoodsName());
            //产品编号
            spdOutPtl.setBarcode(deptBuyForSpdPtls.get(i).getErpCode());
            spdOutPtl.setSpecification(deptBuyForSpdPtls.get(i).getGoodsGg());
            //产品批号
            spdOutPtl.setBatchnumber(barCode);
            spdOutPtl.setManufacturer(deptBuyForSpdPtls.get(i).getMfrsName());
            spdOutPtl.setQuantity(deptBuyForSpdPtls.get(i).getQty());
            spdOutPtl.setUnit(deptBuyForSpdPtls.get(i).getUnit());
            spdOutPtl.setTruequantity(null);
            spdOutPtl.setState("1");
            spdOutPtl.setDowndate(deptBuyForSpdPtls.get(i).getLastUpdateDatetime());
            spdOutPtl.setOperationtime(null);
            spdOutPtl.setTasktype("拣货任务");
            spdOutPtl.setLastUpdateDatetime(new Date());
            spdOutPtl.setVersion(0);
            ptlList.add(spdOutPtl);
        }
        if (ptlList != null && ptlList.size() > 0) {
            boolean flag = this.insertSpdOutPtlLists(ptlList);
            if (flag) {
                return true;
            } else {
                throw new Exception("插入电子标签中间表失败");
            }
        } else {
            return false;
        }
    }

}


