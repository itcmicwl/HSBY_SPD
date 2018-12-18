package gyqx.spdhdi.orderMgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import gyqx.spdhdi.orderMgr.Constance;
import org.springframework.stereotype.Service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdhdi.orderMgr.dao.DistrBillDao;
import gyqx.spdhdi.orderMgr.dao.DistrBillHumitureListDao;
import gyqx.spdhdi.orderMgr.dao.DistrBillListDao;
import gyqx.spdhdi.orderMgr.dao.DistrBillUniqueListDao;
import gyqx.spdhdi.orderMgr.service.IDistrBillService;
import gyqx.spdhdi.orderMgr.vo.DistrBillHumitureListVo;
import gyqx.spdhdi.orderMgr.vo.DistrBillListVo;
import gyqx.spdhdi.orderMgr.vo.DistrBillUniqueListVo;
import gyqx.spdhdi.orderMgr.vo.DistrBillVo;

@Service
public class DistrBillService implements IDistrBillService {

	@Resource
	private DistrBillDao dao;
	@Resource
	private DistrBillListDao detailDao; // 明细表DAO
	@Resource
	private DistrBillHumitureListDao distrHumitureDao; // 明细表DAO
	@Resource
	private DistrBillUniqueListDao distrUniqueDao; // 条码表DAO
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;

	@Override
	public DistrBillVo unRecDistrBillByBillId(String billId) throws Exception {
		DistrBillVo db = dao.getByBillId(billId);
		if (db != null && !db.getId().equals("")) {
			DistrBillListVo dblVo = new DistrBillListVo();
			List<Integer> arrStatus = new ArrayList<>();
			arrStatus.add(Constance.DISTRBILL_DETAIL_STATUS_SAVE);
			arrStatus.add(Constance.DISTRBILL_DETAIL_STATUS_SOMEREC);
			dblVo.setArrStatus(arrStatus);
			dblVo.setPid(db.getId());
			List<DistrBillListVo> lstDetail = detailDao.list4instock(dblVo);
			if (lstDetail.size() > 0) {
				for (DistrBillListVo dbDetail : lstDetail) {
					DistrBillHumitureListVo dbhv = new DistrBillHumitureListVo();
					dbhv.setPid(dbDetail.getId());
					dbhv.setDistrRowNumber(dbDetail.getRowNumber());
					List<DistrBillHumitureListVo> lstDbhv = distrHumitureDao.list(dbhv);
					dbDetail.setDistrBillHumitureList(lstDbhv);

					DistrBillUniqueListVo dbuv = new DistrBillUniqueListVo();
					dbuv.setPid(dbDetail.getId());
					dbuv.setDistrRowNumber(dbDetail.getRowNumber());
					List<DistrBillUniqueListVo> lstDbuv = distrUniqueDao.list(dbuv);
					dbDetail.setDistrBillUniqueList(lstDbuv);
				}
			}
			db.setDistrBillList(lstDetail);
		}
		return db;
	}

	public DistrBillVo getByBillId(String billId) throws Exception {
		DistrBillVo db = dao.getByBillId(billId);
		if (db != null && !db.getId().equals("")) {
			DistrBillListVo dblVo = new DistrBillListVo();
			dblVo.setPid(db.getId());
			List<DistrBillListVo> lstDetail = detailDao.list4instock(dblVo);
			if (lstDetail.size() > 0) {
				for (DistrBillListVo dbDetail : lstDetail) {
					DistrBillHumitureListVo dbhv = new DistrBillHumitureListVo();
					dbhv.setPid(dbDetail.getId());
					dbhv.setDistrRowNumber(dbDetail.getRowNumber());
					List<DistrBillHumitureListVo> lstDbhv = distrHumitureDao.list(dbhv);
					dbDetail.setDistrBillHumitureList(lstDbhv);

					DistrBillUniqueListVo dbuv = new DistrBillUniqueListVo();
					dbuv.setPid(dbDetail.getId());
					dbuv.setDistrRowNumber(dbDetail.getRowNumber());
					List<DistrBillUniqueListVo> lstDbuv = distrUniqueDao.list(dbuv);
					dbDetail.setDistrBillUniqueList(lstDbuv);
				}
			}
			db.setDistrBillList(lstDetail);
		}
		return db;
	}

	@Override
	public DistrBillVo getById(String id) throws Exception {
		DistrBillVo db = dao.getById(id);
		if (db != null && !db.getId().equals("")) {
			DistrBillListVo dblVo = new DistrBillListVo();
			dblVo.setPid(db.getId());
			db.setDistrBillList(detailDao.list(dblVo));
		}
		return db;
	}

	public DistrBillVo add(DistrBillVo distrBill) throws Exception {
		return null;
	}

	private List<DistrBillListVo> groupByDistrBillList(List<DistrBillListVo> lst) {
		List<DistrBillListVo> res = new ArrayList<DistrBillListVo>();
		if (null == lst || lst.isEmpty()) {
			return res;
		}
		for (DistrBillListVo dblv : lst) {
			if (res.contains(dblv)) {
				DistrBillListVo tmp = res.get(res.indexOf(dblv));
				tmp.setSendQty(tmp.getSendQty().add(dblv.getSendQty()));
			} else {
				res.add(dblv);
			}
		}
		return res;
	}

	public DistrBillVo updateState(DistrBillVo distrBill) throws Exception {
		dao.update(distrBill);
		return distrBill;
	}

	public DistrBillVo update(DistrBillVo distrBill) throws Exception {
		return null;
	}

	@Override
	public DistrBillVo updateStatus(DistrBillVo distrBill) throws Exception {
		dao.updateStatus(distrBill);
		return distrBill;
	}

	public List<DistrBillVo> list(DistrBillVo bean) throws Exception {
		return (List<DistrBillVo>) dao.list(bean);
	}

	public QueryResult<DistrBillVo> listByPage(QueryInfo<DistrBillVo> queryInfo) throws Exception {
		//获取当前登陆人,根据人员与供应商关系查询供应商信息
		String userId = userOnlineStateUtils.getCurrent().getUserId();
		String hosId = userOnlineStateUtils.getCurrent().getCorpId();
		queryInfo.getQueryObject().setUserId(userId);
		queryInfo.getQueryObject().setHosId(hosId);
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

	// -----------------------明细表----------------------------
	@Override
	public DistrBillListVo getDetail(String id) throws Exception {
		return detailDao.get(id);
	}

	@Override
	public int insertDetailByBatch(List<DistrBillListVo> distrBillList) throws Exception {
		return detailDao.insertBatch(distrBillList);
	}

	@Override
	public int updateDetail(DistrBillListVo distrBillList) throws Exception {
		return detailDao.update(distrBillList);
	}

	@Override
	public int updateDetailByBatch(List<DistrBillListVo> distrBillList) throws Exception {
		return detailDao.updateByBatch(distrBillList);
	}

	@Override
	public int delDistrBillDetailByPid(String pid) throws Exception {
		return detailDao.delByPid(pid);
	}

	@Override
	public int delDistrBillDetailByBillId(String billId) throws Exception {
		return detailDao.delByBillId(billId);
	}

	@Override
	public List<DistrBillListVo> detailList(DistrBillListVo bean) throws Exception {
		return (List<DistrBillListVo>) detailDao.list(bean);
	}

	// -------------------------温湿度----------------------------
	@Override
	public DistrBillHumitureListVo getDistrHumiture(String id) throws Exception {
		return distrHumitureDao.get(id);
	}

	@Override
	public int insertDistrHumiture(List<DistrBillHumitureListVo> lst) throws Exception {
		return distrHumitureDao.insertBatch(lst);
	}

	@Override
	public int updateDistrHumiture(DistrBillHumitureListVo distrBillHumitureList) throws Exception {
		return distrHumitureDao.update(distrBillHumitureList);
	}

	@Override
	public List<DistrBillHumitureListVo> listDistrHumiture(DistrBillHumitureListVo bean) throws Exception {
		return distrHumitureDao.list(bean);
	}

	// -------------------------条码----------------------------
	@Override
	public DistrBillUniqueListVo getDistrUnique(String id) throws Exception {
		return distrUniqueDao.get(id);
	}

	@Override
	public int insertDistrUnique(List<DistrBillUniqueListVo> distrBillUniqueList) throws Exception {
		return distrUniqueDao.insertBatch(distrBillUniqueList);
	}

	@Override
	public int updateDistrUnique(DistrBillUniqueListVo distrBillUniqueList) throws Exception {
		return distrUniqueDao.update(distrBillUniqueList);
	}

	@Override
	public List<DistrBillUniqueListVo> listDistrUnique(DistrBillUniqueListVo bean) throws Exception {
		return distrUniqueDao.list(bean);
	}

	@Override
	public boolean existUniqueCode(String uniqueCode) throws Exception {
		return distrUniqueDao.existUniqueCode(uniqueCode);
	}
}
