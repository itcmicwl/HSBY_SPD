package gyqx.spdherp.stocInfoMgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdherp.po.HosDeptPurStock;
import gyqx.spdherp.stocInfoMgr.dao.HosDeptPurStockDao;
import gyqx.spdherp.stocInfoMgr.service.HosDeptPurStockService;
import gyqx.spdherp.stocInfoMgr.vo.HosDeptPurStockVo;

@Service
public class HosDeptPurStockServiceImpl implements HosDeptPurStockService {

	@Resource
	private SimpleDao dao;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private HosDeptPurStockDao hosDeptPurStockDao;

	@Override
	public List<HosDeptPurStockVo> queryDeptRelation(HosDeptPurStockVo hosDeptPurStockVo) throws Exception {
		/*
		 * String sql =
		 * "SELECT * FROM hos_dept_pur_stock h WHERE h.hos_id = ? AND h.dept_id = ?;"
		 * ; List<HosDeptPurStockVo> deptLists = dao.queryForList(sql,
		 * HosDeptPurStockVo.class,
		 * userOnlineStateUtils.getCurrent().getCorpId(),
		 * hosDeptPurStockVo.getDeptId()); return deptLists;
		 */

		if (hosDeptPurStockVo.getHosId() == null || "".equals(hosDeptPurStockVo.getHosId())) {
			hosDeptPurStockVo.setHosId(userOnlineStateUtils.getCurrent().getCorpId());
		}
		List<HosDeptPurStockVo> deptLists = hosDeptPurStockDao.queryDeptRelation(hosDeptPurStockVo);
		return deptLists;
	}

	@Override
	public void deptRelationSave(HosDeptPurStockVo hosDeptPurStockVo) throws Exception {
		dao.executeSql("delete from hos_dept_pur_stock where hos_id = ? and dept_id = ?", hosDeptPurStockVo.getHosId(),
				hosDeptPurStockVo.getDeptId());
		String defaultDeptId = hosDeptPurStockVo.getDefaultDeptId();
		String deptId = hosDeptPurStockVo.getDeptId();
		String hosId = hosDeptPurStockVo.getHosId();
		for (String stockId : hosDeptPurStockVo.getStockIds()) {
			HosDeptPurStock h = new HosDeptPurStock();
			if (hosDeptPurStockVo.getHosId() == null || "".equals(hosDeptPurStockVo.getHosId())) {
				hosDeptPurStockVo.setHosId(userOnlineStateUtils.getCurrent().getCorpId());
			}
			h.setId(atomUtil.newValue("hos-dept-pur-stock-id"));
			h.setHosId(hosId);
			h.setDeptId(deptId);
			h.setStockId(stockId);
			if (StringUtils.isNotEmpty(defaultDeptId) && defaultDeptId.equals(stockId)) {
				h.setIsDefault("1");
			}else{
				h.setIsDefault("0");
			}
			dao.insert(h);
		}
	}

	@Override
	public void setDefaultDept(HosDeptPurStockVo hosDeptPurStockVo) throws Exception {
		HosDeptPurStockVo qbean = new HosDeptPurStockVo();
		qbean.setHosId(hosDeptPurStockVo.getHosId());
		qbean.setDeptId(hosDeptPurStockVo.getDeptId());
		List<HosDeptPurStockVo> deptLists = hosDeptPurStockDao.queryDeptRelation(qbean);
		if (deptLists != null && deptLists.size() > 0) {
			for (HosDeptPurStockVo vo : deptLists) {
				if ("1".equals(vo.getIsDefault())) {
					vo.setIsDefault("0");
					hosDeptPurStockDao.update(vo);
				}
			}
		}
		hosDeptPurStockVo.setIsDefault("1");
		hosDeptPurStockDao.update(hosDeptPurStockVo);
	}

}
