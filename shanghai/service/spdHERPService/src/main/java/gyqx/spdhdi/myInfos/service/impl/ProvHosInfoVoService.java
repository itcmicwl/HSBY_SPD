package gyqx.spdhdi.myInfos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdhdi.myInfos.dao.ProvHosInfoVoDao;
import gyqx.spdhdi.myInfos.service.IProvHosInfoVoService;
import gyqx.spdhdi.myInfos.vo.HosInfoVo;
import gyqx.spdhdi.myInfos.vo.HosProvVo;
import gyqx.spdhdi.myInfos.vo.ProvHosInfoVo;

@Service	
public class ProvHosInfoVoService implements IProvHosInfoVoService {
	@Resource
	ProvHosInfoVoDao provHosInfoVoDao;
	@Resource
	SimpleDao simpleDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private UserOnlineStateUtils  userOnlineStateUtils;
	
	@Override
	public QueryResult<ProvHosInfoVo> getProvHosInfoVoList(QueryInfo<ProvHosInfoVo> queryInfo) throws Exception {
		QueryResult<ProvHosInfoVo> lstProvHosInfoVosInfo = new QueryResult<ProvHosInfoVo>();
		queryInfo.getQueryObject().setProvId(userOnlineStateUtils.getCurrent().getCorpId());
		PageInfo<ProvHosInfoVo> provHosInfoVoPage = provHosInfoVoDao.getProvHosInfoVoList(queryInfo);
		lstProvHosInfoVosInfo.setData(provHosInfoVoPage.getList());
		lstProvHosInfoVosInfo.setPageNum(provHosInfoVoPage.getPageNum());
		lstProvHosInfoVosInfo.setPageSize(provHosInfoVoPage.getPageSize());
		lstProvHosInfoVosInfo.setTotal(provHosInfoVoPage.getTotal());
		return lstProvHosInfoVosInfo;
	}
	
	@Override
	public QueryResult<HosInfoVo> getHosInfoVoList(QueryInfo<HosInfoVo> queryInfo) throws Exception {
		QueryResult<HosInfoVo> lstProvHosInfoVosInfo = new QueryResult<HosInfoVo>();
		queryInfo.getQueryObject().setId(userOnlineStateUtils.getCurrent().getCorpId());
		PageInfo<HosInfoVo> provHosInfoVoPage = provHosInfoVoDao.getHosInfoVoList(queryInfo);
		lstProvHosInfoVosInfo.setData(provHosInfoVoPage.getList());
		return lstProvHosInfoVosInfo;
	}
	
	
	@Override
	public QueryResult<HosProvVo> getHosProvVoList(QueryInfo<HosProvVo> queryInfo) throws Exception {
		QueryResult<HosProvVo> lstProvHosInfoVosInfo = new QueryResult<HosProvVo>();
		queryInfo.getQueryObject().setId(userOnlineStateUtils.getCurrent().getCorpId());
		PageInfo<HosProvVo> provHosInfoVoPage = provHosInfoVoDao.getHosProvVoList(queryInfo);
		lstProvHosInfoVosInfo.setData(provHosInfoVoPage.getList());
		return lstProvHosInfoVosInfo;
	}
	
	
	@Override
	public ProvHosInfoVo getProvHosInfoByid(String id) {
		return provHosInfoVoDao.getProvHosInfoByid(id);
	}
	
	@Override
	public int insertProvHosInfo(ProvHosInfoVo provHosInfoVo) throws Exception {
		
		String id = atomUtil.newValue("sys.config");
		provHosInfoVo.setId(id);
		provHosInfoVo.setStatus("1");// 医院待审
		provHosInfoVo.setUid(userOnlineStateUtils.getCurrent().getUserId());
		provHosInfoVo.setProvId(userOnlineStateUtils.getCurrent().getCorpId());
//		simpleDao.insert(basMfrsInfo);
		return provHosInfoVoDao.insertProvHosInfo(provHosInfoVo);
	}
	
	@Override
	public int setStatus(ProvHosInfoVo provHosInfoVo) throws Exception {
		provHosInfoVo.setUid(userOnlineStateUtils.getCurrent().getUserId());
		return provHosInfoVoDao.setStatus(provHosInfoVo);
	}
	
	@Override
	public int setProvHosInfo(ProvHosInfoVo provHosInfoVo) throws Exception {
		provHosInfoVo.setUid(userOnlineStateUtils.getCurrent().getUserId());
		return provHosInfoVoDao.setProvHosInfo(provHosInfoVo);
	}
	
	@Override
	public int deleteByPrimaryKey(String id) throws Exception {
		return provHosInfoVoDao.deleteByPrimaryKey(id);
	}
	
	@Override
	public boolean existProvHosInfo(ProvHosInfoVo provHosInfoVo) throws Exception {
		return provHosInfoVoDao.existProvHosInfo(provHosInfoVo);
	}


}
