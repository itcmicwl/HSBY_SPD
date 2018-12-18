package gyqx.spdhdi.orderMgr.service.impl;

import com.mysql.jdbc.StringUtils;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.transform.Tx;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import common.utils.UtilsContext;
import gyqx.spdhdi.myInfos.service.IProvHosDeptInfoCodeService;
import gyqx.spdhdi.myInfos.vo.ProvHosDeptInfoCodeVo;
import gyqx.spdhdi.myInfos.vo.SaleCodeInfoVo;
import gyqx.spdhdi.orderMgr.Constance;
import gyqx.spdhdi.orderMgr.dao.PurconfirmDao;
import gyqx.spdhdi.orderMgr.dao.PurconfirmListDao;
import gyqx.spdhdi.orderMgr.service.IPurchaseService;
import gyqx.spdhdi.orderMgr.service.IPurconfirmService;
import gyqx.spdhdi.orderMgr.vo.PurchaseListVo;
import gyqx.spdhdi.orderMgr.vo.PurchaseVo;
import gyqx.spdhdi.orderMgr.vo.PurconfirmListVo;
import gyqx.spdhdi.orderMgr.vo.PurconfirmVo;
import gyqx.ws.yg.common.*;
import gyqx.ws.yg.vo.SMPType;
import gyqx.ws.yg.vo.repVo.YY111_REP_DETAIL;
import gyqx.ws.yg.vo.repVo.YY111_REP_XML;
import gyqx.ws.yg.vo.repVo.YY112_REP_XML;
import gyqx.ws.yg.vo.repVo.YY159_REP_XML;
import gyqx.ws.yg.vo.reqVo.*;
import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PurconfirmService implements IPurconfirmService {
	private static Logger logger = LogManager.getLogger(PurconfirmService.class);
	@Resource
	private PurconfirmDao dao;
	@Resource
	private PurconfirmListDao detailDao;
	@Resource
	private SysConfigUtil configUtil;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private SysConfigUtil sysConfigUtil;
	@Resource
	IPurchaseService purService;
	@Resource
	private IProvHosDeptInfoCodeService povHosDeptInfoCodeService;
	@Resource
	private UtilsContext utils;
	@Resource
    private YgSendUtil ygUtils;
    @Resource
    private YgServices ygServices;
    @Override
	public PurconfirmVo getById(String id) throws Exception {
		PurconfirmVo p = dao.get(id);
		if (p != null) {
			PurconfirmListVo pdetail = new PurconfirmListVo();
			pdetail.setPid(p.getId());
			List<PurconfirmListVo> lst = detailList(pdetail);
			p.setLstDetail(lst);
		}
		return p;
	}

	@Override
	public PurconfirmVo getByBillid(String billid) throws Exception {
		PurconfirmVo p = dao.getByBillid(billid);
		if (p != null) {
			PurconfirmListVo pdetail = new PurconfirmListVo();
			pdetail.setPid(p.getId());
			List<PurconfirmListVo> lst = detailList(pdetail);
			p.setLstDetail(lst);
		}
		return p;
	}



	@Override
	public List<PurconfirmVo> list(PurconfirmVo bean) throws Exception {
		return dao.list(bean);
	}

	@Override
	public QueryResult<PurconfirmVo> listByPage(QueryInfo<PurconfirmVo> queryInfo) throws Exception {

		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}


	@Override
	public List<PurconfirmListVo> detailList(PurconfirmListVo bean) throws Exception {
		return (List<PurconfirmListVo>) detailDao.list(bean);
	}

	@Override
	public void sendPurToYg() throws Exception {
        String deProvId = utils.getSysConfigUtil().getValue(gyqx.common.Constance.GYQX_SPD_DEFAULT_PROV_ID);
        String deHosId = utils.getSysConfigUtil().getValue(gyqx.common.Constance.GYQX_SPD_DEFAULT_HOS_ID);
        if(StringUtils.isNullOrEmpty(deProvId) || StringUtils.isNullOrEmpty(deHosId)){
			logger.error(String.format("未开始上传原因:%s或%s未配置",gyqx.common.Constance.GYQX_SPD_DEFAULT_PROV_ID,
                    gyqx.common.Constance.GYQX_SPD_DEFAULT_HOS_ID));
        	return;
		}
		PurconfirmVo param = new PurconfirmVo();
        param.setPurchaseCompanyId(deHosId);
        param.setProvId(deProvId);
		List<PurconfirmVo> lst = dao.list4yg(param);
		for (PurconfirmVo pur : lst) {
			if(StringUtils.isNullOrEmpty(pur.getYgBillId())){
				YY111_REP_XML res = this.praseAndSendToYG(pur.getBillId());
			}else{
				this.updatePurToYg(pur.getBillId());				//采购单确认
			}
		}
	}
	private YY111_REP_XML praseAndSendToYG(String purBillId) throws Exception {
        PurconfirmVo purc = this.getByBillid(purBillId);
        return praseAndSendToYG(purc);
    }

	private YY111_REP_XML praseAndSendToYG(PurconfirmVo purc) throws Exception{
//        purc.setProvYgCode("QY000000000000000346");
//        purc.setHosYgCode("56806983600");
		boolean flag = checkPurc4YG(purc);
		if(!flag){
		    return null;
        }
		YY111_REQ_XML yy111 = new YY111_REQ_XML();
		YY111_REQ_MAIN yy111Main = new YY111_REQ_MAIN();
		yy111Main.setOpType(gyqx.ws.yg.common.Constance.OP_11.OP_ADD.toString());
		yy111Main.setHosCode(purc.getHosYgCode());
		yy111Main.setAddressCode(purc.getRecAddress());
        //yy111Main.setAddressCode("test01");
		yy111Main.setPurType(gyqx.ws.yg.common.Constance.PUR_BILL_12.YYZXCGD);
		yy111Main.setPurBillId(purc.getYgBillId());
		yy111Main.setApplyBillId(purc.getBillId());
        List<YY111_REQ_DETAIL> lstDetail = new ArrayList<>();
		for (PurconfirmListVo purDetail : purc.getLstDetail()) {
			YY111_REQ_DETAIL yyDetail = new YY111_REQ_DETAIL();
			yyDetail.setRowNum(purDetail.getRowNumber());
			yyDetail.setPurType(gyqx.ws.yg.common.Constance.PUR_13.DLCG);
			yyDetail.setGoodsCode(purDetail.getMiCode());
			yyDetail.setGoodsDetailCode("");
			yyDetail.setGoodsHosId(purDetail.getHosGoodsId());
			yyDetail.setGoodsGG(purDetail.getGoodsGg());
			yyDetail.setPurQty(purDetail.getUnitQty().toString());
			yyDetail.setPurPrice(purDetail.getProvGoodsErpPrice().toString());
			yyDetail.setProvCode(purc.getProvYgCode());
			yyDetail.setIsFast("0");
			yyDetail.setDeliverRequire(StringUtils.isNullOrEmpty(purDetail.getMiCode())?gyqx.ws.yg.common.Constance.DISTR_14.AXPS:gyqx.ws.yg.common.Constance.DISTR_14.ADPS);
			yyDetail.setDeliverRemark(StringUtils.isNullOrEmpty(purDetail.getMiCode())?"spd系统自动填报":"spd系统自动填报");
			yyDetail.setIsDeliverMany("0");
			yyDetail.setShelfInfo("");
            lstDetail.add(yyDetail);
		}
        yy111.setDetail(lstDetail);
        yy111Main.setSumRowCount(lstDetail.size());
        yy111.setMain(yy111Main);
		return submitPurListToYg(yy111);
	}
    private boolean checkPurc4YG(PurconfirmVo purc){
        boolean res = true;
        if(purc == null){
           res = false;
        }
        if(StringUtils.isNullOrEmpty(purc.getProvYgCode())){
            logger.error(String.format("供应商：%s机构编码为空",purc.getProvName()));
            res = false;
        }
        if( StringUtils.isNullOrEmpty(purc.getHosYgCode())){
            logger.error(String.format("医院：%s机构编码为空",purc.getPurchaseCompanyName()));
            res = false;
        }
        return  res;
    }
	/**
	 * 上传采购单到阳光平台
	 * @param yy111
	 * @return
	 * @throws Exception
	 */
	@Override
	public YY111_REP_XML submitPurListToYg(YY111_REQ_XML yy111) throws Exception {
        yy111.setReqHead(HostUtils.getReqHead(""));
		String xmlData = JAXBUtil.toXML(yy111,YY111_REQ_XML.class);
		YY111_REP_XML res = ygUtils.sendRecv(YY111_REP_XML.class, SMPType.YY111,xmlData);
		if(res.getRepHead().getRes().equals(ResCode.SUCCESS.getCode())) {                              //上传成功写入确认单表
            PurconfirmVo pVo = this.getByBillid(yy111.getMain().getApplyBillId());
            if (pVo != null) {
                pVo.setYgBillId(res.getMain().getOrderNo());
                pVo.getLstDetail().forEach(item -> {
                    Optional<YY111_REP_DETAIL> op = res.getDetail().stream().filter(detail -> item.getRowNumber().equals(detail.getRowNumber())).findAny();
                    if (op.isPresent()) {
                        YY111_REP_DETAIL yy111Detail = op.get();
                        item.setYgBillId(yy111Detail.getDetailNo());
                    }
                });
                dao.update(pVo);
                detailDao.updateByBatch(pVo.getLstDetail());
            }
        }else{
			logger.info("阳光平台-上传采购单失败："+res.getRepHead().getRes()+res.getRepHead().getErrMsg());
		}
		return res;
	}

	/**
	 * 采购单确认
	 * @param billId
	 * @return
	 * @throws Exception
	 */
    @Override
    public YY112_REP_XML updatePurToYg(String billId) throws Exception {
        YY112_REP_XML res = null;
        PurconfirmVo pVo = this.getByBillid(billId);
        if(pVo != null && !StringUtils.isNullOrEmpty(pVo.getYgBillId()) && pVo.getIsConfirm() == 0) {
            YY112_REQ_MAIN main = new YY112_REQ_MAIN();
            main.setAddressCode(pVo.getRecAddress());
            main.setPurOrderNo(pVo.getYgBillId());
            main.setYgHosCode(pVo.getHosYgCode());
            main.setPurType(gyqx.ws.yg.common.Constance.PUR_13.DLCG.toString());
            main.setSumRow(pVo.getLstDetail().size());
            YY112_REQ_XML param = new YY112_REQ_XML();
            param.setMain(main);
            param.setReqHead(HostUtils.getReqHead(""));
            String xmlData = JAXBUtil.toXML(param,YY112_REQ_XML.class);
            res = ygUtils.sendRecv(YY112_REP_XML.class, SMPType.YY112,xmlData);
            if(res.getRepHead().getRes().equals(ResCode.SUCCESS.getCode())){
                pVo.setIsConfirm(1);
                dao.update(pVo);
            }else{
				logger.info("阳光平台-确认采购单失败："+res.getRepHead().getRes()+res.getRepHead().getErrMsg());
			}
        }else {
            throw new Exception("单据状态不符合");
        }
        return res;
    }

    @Override
    public YY159_REP_XML getPurStatusByBillNo(String ygBillId) throws Exception {
        return ygServices.getPurStatusByBillNo(ygBillId);
    }
}
