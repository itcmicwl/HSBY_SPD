package gyqx.ws.yg.service.impl;

import javax.annotation.Resource;

import common.db.SimpleDao;
import gyqx.ws.yg.common.YgSendUtil;
import gyqx.ws.yg.dao.YgSendBillDao;
import gyqx.ws.yg.service.IYgRecConfirmService;
import gyqx.ws.yg.service.IYgSendBillListService;
import gyqx.ws.yg.service.IYgSendBillListStateService;
import gyqx.ws.yg.service.IYgSendBillService;
import gyqx.ws.yg.vo.repVo.YY154_REP_DETAIL;
import gyqx.ws.yg.vo.repVo.YY155_REP_DETAIL;
import gyqx.ws.yg.vo.repVo.YY161_REP_DETAIL;
import gyqx.ws.yg.vo.reqVo.YY131_REQ_DETAIL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Service
public class YgSendBillService implements IYgSendBillService {
	
	@Resource
	private YgSendBillDao dao;
	@Resource
	private IYgSendBillListService ygSendBillLstSer;
	@Resource
	private IYgSendBillListStateService ygSendBillLstStateSer;
	@Resource
	private IYgRecConfirmService ygRecSer;
	@Override
	public YY154_REP_DETAIL get(String psdbh) throws Exception{
        YY154_REP_DETAIL res = dao.get(psdbh);
        if(res != null){
            res.setLstDeatil(ygSendBillLstSer.list(psdbh));
            res.setLstState(ygSendBillLstStateSer.list(psdbh));
        }
        return res;
	}

    public boolean checkExists(String psdbh) throws Exception{
        YY154_REP_DETAIL query = new YY154_REP_DETAIL();
        query.setPsdbh(psdbh);
        List<YY154_REP_DETAIL> res = dao.list(query);
        if(res == null){
            return false;
        }
        return res.size()>0;
    }
	@Override
	public int update(YY154_REP_DETAIL bean) throws Exception {
		return dao.update(bean);
	}

	@Override
	public int insert(YY154_REP_DETAIL bean) throws Exception {
        boolean flag = checkExists(bean.getPsdbh());
        if(flag){return 0;}
		List<YY131_REQ_DETAIL> lstYY131D = new ArrayList<>();
		for (YY155_REP_DETAIL yy155_rep_detail : bean.getLstDeatil()) {
			yy155_rep_detail.setPsdbh(bean.getPsdbh());
			//写验收确认表以作自动验收
			YY131_REQ_DETAIL yy131d = new YY131_REQ_DETAIL();
			yy131d.setIsSend(0);
			yy131d.setPsdbh(bean.getPsdbh());
			yy131d.setHctbdm(yy155_rep_detail.getHctbdm());
			yy131d.setPsl(new BigDecimal(yy155_rep_detail.getPsl()));
			yy131d.setPsmxbh(yy155_rep_detail.getPsmxbh());
			yy131d.setScph(yy155_rep_detail.getScph());
			yy131d.setVersion(0);
			yy131d.setYsbgs(new BigDecimal(0));
			yy131d.setYsbzsm("SPD系统自动验收");
			yy131d.setYstgs(new BigDecimal(yy155_rep_detail.getPsl()));
			lstYY131D.add(yy131d);
		}
		for (YY161_REP_DETAIL yy161_rep_detail : bean.getLstState()) {
			yy161_rep_detail.setPsdbh(bean.getPsdbh());
		}
		ygSendBillLstSer.insertByBatch(bean.getLstDeatil());
		ygSendBillLstStateSer.insertByBatch(bean.getLstState());
		//ygRecSer.insertByBatch(lstYY131D);            //自动验收（以后若需要开启则取消注释本行）
		return dao.insert(bean);
	}

	@Override
	public int insertByBatch(List<YY154_REP_DETAIL> lst) throws Exception {
		return dao.insertByBatch(lst);
	}

	@Override
	public List<YY154_REP_DETAIL> list(YY154_REP_DETAIL queryInfo) throws Exception {
		return (List<YY154_REP_DETAIL>) dao.list(queryInfo);
	}

	@Override
	public QueryResult<YY154_REP_DETAIL> listByPage(QueryInfo<YY154_REP_DETAIL> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}


}


