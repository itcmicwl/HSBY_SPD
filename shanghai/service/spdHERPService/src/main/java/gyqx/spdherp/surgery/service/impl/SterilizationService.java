package gyqx.spdherp.surgery.service.impl;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.UserOnlineStateUtils;
import gyqx.spdherp.surgery.constant.Constants;
import gyqx.spdherp.surgery.dao.SterilizationDao;
import gyqx.spdherp.surgery.service.IPkgLogService;
import gyqx.spdherp.surgery.service.ISterilizationService;
import gyqx.spdherp.surgery.vo.PkgLogVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgListVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class SterilizationService implements ISterilizationService {
	
	@Resource
	private SterilizationDao dao;

	@Resource
	private SimpleDao simpleDao;

	@Resource
	UserOnlineStateUtils userOnline;

	@Resource
	IPkgLogService pkgLogService;

	/**
	 * 获取需要消毒的手术包
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	public QueryResult<SurgeryPkgVo> listFirstPkgByPage(QueryInfo<SurgeryPkgVo> queryInfo) throws Exception{
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.listFirst(queryInfo.getQueryObject()));
	}

	public QueryResult<SurgeryPkgVo> listFirstExpireByPage(QueryInfo<SurgeryPkgVo> queryInfo) throws Exception{
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.listFirstExpire(queryInfo.getQueryObject()));
	}

	/**
	 * 获取需要退还消毒的手术包
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	public QueryResult<SurgeryPkgVo> listSecPkgByPage(QueryInfo<SurgeryPkgVo> queryInfo) throws Exception{
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.listSec(queryInfo.getQueryObject()));
	}

	/**
	 * 获取退还消毒后过期的手术包列表
	 * @param queryInfo
	 * @return
	 */
	public QueryResult<SurgeryPkgVo> listSecExpire(QueryInfo<SurgeryPkgVo> queryInfo){
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.listSecExpire(queryInfo.getQueryObject()));
	}

	/**
	 * 根据手术包码获取需要消毒的手术包商品信息
	 * @param queryInfo
	 * @return
	 */
	public QueryResult<SurgeryPkgListVo> getNotFirstPkgListBySurCode(QueryInfo<SurgeryPkgVo> queryInfo){
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.getNotFirstPkgListBySurCode(queryInfo.getQueryObject().getSurCode()));
	}

	/**
	 * 根据手术包码获取需要退还消毒的手术包商品信息
	 * @param queryInfo
	 * @return
	 */
	public QueryResult<SurgeryPkgListVo> getNotSecPkgListBySurCode(QueryInfo<SurgeryPkgVo> queryInfo){
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.getNotSecPkgListBySurCode(queryInfo.getQueryObject().getSurCode()));
	}

	/**
	 * 手术包消毒
	 * @param surgeryPkgVo
	 * @return
	 */
	public int updateFirstSterialization(SurgeryPkgVo surgeryPkgVo){
		// 更新手术包明细表消毒信息
		int count = dao.updateFirstSterialization(surgeryPkgVo.getSurgeryPkgListVos());
		if(count > 0){
			//List <SurgeryPkgListVo> surgeryPkgLists = dao.getNotFirstPkgListBySurCode(surgeryPkgVo.getSurCode());
			String cntSql = "select count(*) from surgery_pkg_list where should_sterilize =1 and sur_code = ? and (first_sterilizer is null or first_sterilizer = '')";
			long num = simpleDao.queryForObject(cntSql, long.class, surgeryPkgVo.getSurCode());

			// 回写手术包信息主表状态
			// 全部消毒
			int status = Constants.SURGERY_PKG_STATUS_STERILIZATION_FIRST_ALL;
			if(num > 0){
				// 手术包部分消毒
				status = Constants.SURGERY_PKG_STATUS_STERILIZATION_FIRST_PART;
			}
			surgeryPkgVo.setStatus(status);
			dao.updatePkgStatus(surgeryPkgVo);

			// 回写包日志表
			insertPkgLog(surgeryPkgVo, "手术包消毒");
		}

		return count;
	}

	/**
	 * 退回消毒
	 * @param surgeryPkgVo
	 * @return
	 */
	public int updateSecSterialization(SurgeryPkgVo surgeryPkgVo){
		// 更新手术包明细表消毒信息
		int count = dao.updateSecSterialization(surgeryPkgVo.getSurgeryPkgListVos());
		if(count > 0){
			/*String cntSql = "select count(*) from surgery_pkg_list where should_sterilize =1 and sur_code = ? and sec_sterilizer is not null and sec_sterilizer <> ''";
			long num = simpleDao.queryForObject(cntSql, long.class, surgeryPkgVo.getSurCode());*/
			long num = dao.getCountNotSecPkgListBySurCode(surgeryPkgVo.getSurCode());

			// 回写手术包信息主表状态
			// 全部消毒
			int status = Constants.SURGERY_PKG_STATUS_STERILIZATION_SEC_ALL;
			if(num > 0){
				// 手术包部分消毒
				status = Constants.SURGERY_PKG_STATUS_STERILIZATION_SEC_PART;
			}
			surgeryPkgVo.setStatus(status);
			dao.updatePkgStatus(surgeryPkgVo);

			// 回写包日志表
			insertPkgLog(surgeryPkgVo, "退回消毒");
		}

		return count;
	}

	private void insertPkgLog(SurgeryPkgVo surgeryPkgVo, String desc){
		PkgLogVo pkgLogVo = new PkgLogVo();
		pkgLogVo.setId(UUID.randomUUID().toString().replace("-", ""));
		pkgLogVo.setCode(surgeryPkgVo.getSurCode());
		// 手术包类型
		pkgLogVo.setPackageKind(Constants.PKG_KIND_SURGERY);
		pkgLogVo.setBillId(surgeryPkgVo.getApplyBillId());
		pkgLogVo.setDescription(desc);
		pkgLogVo.setFiller(userOnline.getCurrent().getUserId());
		pkgLogVo.setFillDate(new Date());

		pkgLogService.insert(pkgLogVo);
	}


}


