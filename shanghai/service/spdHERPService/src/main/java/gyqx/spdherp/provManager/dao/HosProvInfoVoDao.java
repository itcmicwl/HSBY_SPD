package gyqx.spdherp.provManager.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;

import common.db.query.QueryInfo;
import gyqx.spdhdi.myInfos.vo.HosInfoVo;
import gyqx.spdherp.provManager.dao.mapper.IHosProvInfoMapper;
import gyqx.spdherp.provManager.vo.ProvHosInfoVo;

@Repository
public class HosProvInfoVoDao {
	@Resource
	IHosProvInfoMapper hosProvInfoMapper;

	/**
	 * 获取医院供应商列表
	 * @param provHosInfoVo
	 * @return
	 */
	public List<ProvHosInfoVo> getHosProvInfoVoList(ProvHosInfoVo  provHosInfoVo) {
		return hosProvInfoMapper.getHosProvInfoVoList(provHosInfoVo);
	}
	
	public PageInfo<HosInfoVo> getHosInfoVoList(QueryInfo<HosInfoVo> queryInfo) {
		List<HosInfoVo> lstProvHosInfoVo = hosProvInfoMapper.getHosInfoVoList(queryInfo.getQueryObject());
		PageInfo<HosInfoVo> ProvHosInfoVoPage = new PageInfo<HosInfoVo>(lstProvHosInfoVo);
		return ProvHosInfoVoPage;
	}

	public int insertProvHosInfo(ProvHosInfoVo provHosInfoVo) {
		return hosProvInfoMapper.insertProvHosInfo(provHosInfoVo);
	}

	public int setStatus(ProvHosInfoVo provHosInfoVo) {
		return hosProvInfoMapper.setStatus(provHosInfoVo);
	}

	public int setProvHosInfo(ProvHosInfoVo provHosInfoVo) {
		return hosProvInfoMapper.setProvHosInfo(provHosInfoVo);
	}

	public int deleteByPrimaryKey(String id) {
		return hosProvInfoMapper.deleteByPrimaryKey(id);
	}

	public boolean existProvHosInfo(ProvHosInfoVo ProvHosInfoVo) {
		Integer count = hosProvInfoMapper.existProvHosInfo(ProvHosInfoVo);
//		count = count == null ? 0 : count;
		return count > 0;
	}

	public ProvHosInfoVo getProvHosInfoByid(String id) {
		return hosProvInfoMapper.getProvHosInfoByid(id);
	}

	public int updateLogo(String logo) {
		return hosProvInfoMapper.updateLogo(logo);
	}

}
