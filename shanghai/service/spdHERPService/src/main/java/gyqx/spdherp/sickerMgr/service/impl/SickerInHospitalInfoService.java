package gyqx.spdherp.sickerMgr.service.impl;

import javax.annotation.Resource;

import gyqx.spdherp.po.HisKs;
import gyqx.spdherp.sickerMgr.vo.SickerInHospitalInfoVo;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.po.SickerInHospitalInfo;
import gyqx.spdherp.sickerMgr.dao.SickerInHospitalInfoDao;
import gyqx.spdherp.sickerMgr.service.ISickerInHospitalInfoService;

@Service
public class SickerInHospitalInfoService implements ISickerInHospitalInfoService {

	@Resource
	private SickerInHospitalInfoDao  dao;

	public SickerInHospitalInfo get(String id)
	{
		return dao.get( id);
	}

	public SickerInHospitalInfo add(SickerInHospitalInfo sickerInHospitalInfo) throws Exception
	{
		return dao.insertAndGet(sickerInHospitalInfo);
	}

	public SickerInHospitalInfo update(SickerInHospitalInfo sickerInHospitalInfo) throws Exception {
		return dao.updateAndGet(sickerInHospitalInfo);
	}
	public int updateFields(SickerInHospitalInfo sickerInHospitalInfo,String fieldNames) throws Exception
	{
		return dao.updateFields(sickerInHospitalInfo, fieldNames);
	}

	public void delete(SickerInHospitalInfo sickerInHospitalInfo) throws Exception {
		dao.delete(sickerInHospitalInfo);
	}

	public SickerInHospitalInfo getByFieldName(String fieldName, Object fieldValue) throws Exception
	{
		return (SickerInHospitalInfoVo) dao.getByFieldName(fieldName, fieldValue);
	}

	public List<SickerInHospitalInfo> query(String predicate,String orderBy,Object... fieldValue) throws Exception
	{
		return (List<SickerInHospitalInfo>) dao.query(predicate,orderBy,fieldValue);
	}

	public List<SickerInHospitalInfoVo> list(SickerInHospitalInfoVo bean) throws Exception
	{
		return (List<SickerInHospitalInfoVo>) dao.list(bean);
	}

	public QueryResult<SickerInHospitalInfoVo> listByPage(QueryInfo<SickerInHospitalInfoVo> queryInfo) throws Exception{
		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

	@Override
	public List<HisKs> getHisDepts(HisKs hisKs) throws Exception {
		return dao.getHisDepts(hisKs);
	}

}


