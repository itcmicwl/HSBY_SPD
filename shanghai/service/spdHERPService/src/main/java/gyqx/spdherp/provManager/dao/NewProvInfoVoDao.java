package gyqx.spdherp.provManager.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import common.db.query.QueryInfo;
import gyqx.spdherp.provManager.vo.ProvHosInfoVo;
import gyqx.spdherp.provManager.dao.mapper.INewProvInfoMapper;

@Repository
public class NewProvInfoVoDao {
	@Resource
	INewProvInfoMapper newProvInfoMapper;

	public PageInfo<ProvHosInfoVo> getNewProvInfoVoList(QueryInfo<ProvHosInfoVo> queryInfo) {
		PageHelper.startPage(queryInfo.getPage(), queryInfo.getRows());
		List<ProvHosInfoVo> lstProvHosInfoVo = newProvInfoMapper.getNewProvInfoVoList(queryInfo.getQueryObject());
		PageInfo<ProvHosInfoVo> ProvHosInfoVoPage = new PageInfo<ProvHosInfoVo>(lstProvHosInfoVo);
		return ProvHosInfoVoPage;
	}

}
