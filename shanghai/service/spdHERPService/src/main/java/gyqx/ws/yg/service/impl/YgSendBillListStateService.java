package gyqx.ws.yg.service.impl;

import javax.annotation.Resource;

import com.mysql.jdbc.StringUtils;
import gyqx.ws.yg.common.YgServices;
import gyqx.ws.yg.dao.YgSendBillListStateDao;
import gyqx.ws.yg.service.IYgSendBillListStateService;
import gyqx.ws.yg.vo.repVo.YY131_REP_DETAIL;
import gyqx.ws.yg.vo.repVo.YY161_REP_DETAIL;
import gyqx.ws.yg.vo.repVo.YY161_REP_XML;
import gyqx.ws.yg.vo.reqVo.YY131_REQ_DETAIL;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;

import java.util.ArrayList;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Service
public class YgSendBillListStateService implements IYgSendBillListStateService {
	
	@Resource
	private YgSendBillListStateDao dao;
	@Resource
	private YgServices ygServices;
	@Override
	public YY161_REP_DETAIL get(String psmxbh) throws Exception {
		return dao.get(psmxbh);
	}

	@Override
	public int updateByBatch(List<YY161_REP_DETAIL> lst) throws Exception {
		return dao.updateByBatch(lst);
	}

	@Override
	public int insertByBatch(List<YY161_REP_DETAIL> lst) throws Exception {
		return dao.insertByBatch(lst);
	}

	@Override
	public int update(YY161_REP_DETAIL bean) throws Exception {
		return dao.update(bean);
	}

	@Override
	public int insert(YY161_REP_DETAIL bean) throws Exception {
		return dao.insert(bean);
	}

	@Override
	public List<YY161_REP_DETAIL> list(YY161_REP_DETAIL queryInfo) throws Exception {
		return dao.list(queryInfo);
	}

	@Override
	public List<YY161_REP_DETAIL> list(String billId) throws Exception {
		YY161_REP_DETAIL quer = new YY161_REP_DETAIL();
		quer.setPsdbh(billId);
		return dao.list(quer);
	}

	@Override
	public QueryResult<YY161_REP_DETAIL> listByPage(QueryInfo<YY161_REP_DETAIL> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}
	@Override
	public YY161_REP_XML checkIn(String billId) throws Exception {
//		if(lst == null || lst.size()==0){return 0;}
//		List<YY161_REP_DETAIL> lstDetail = new ArrayList<>();
//		for (YY131_REP_DETAIL yy131_rep_detail : lst) {
//			if(yy131_rep_detail.getCljg().equals("00000")){
//				YY161_REP_DETAIL item = new YY161_REP_DETAIL();
//				item.setPsmxbh(yy131_rep_detail.getPsmxbh());
//				int index = lstYY131D.indexOf(yy131_rep_detail);
//				if(index >=0){
//					YY131_REQ_DETAIL req = lstYY131D.get(index);
//					item.setYsybgs(req.getYsbgs());
//					item.setYsytgs(req.getYstgs());
//				}
//			}
//		}
		if(StringUtils.isNullOrEmpty(billId)){
			return null;
		}
		YY161_REP_XML y161 = ygServices.getSendBillStateByBillId(billId);
		if(y161.getRepHead().getRes().equals("00000")){
			for (YY161_REP_DETAIL yy161_rep_detail : y161.getDetail()) {
				yy161_rep_detail.setPsdbh(billId);
			}
			this.updateByBatch(y161.getDetail());
		}
		return y161;

	}


}


