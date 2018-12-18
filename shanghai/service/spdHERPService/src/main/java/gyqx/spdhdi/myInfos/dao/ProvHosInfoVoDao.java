package gyqx.spdhdi.myInfos.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import common.db.query.QueryInfo;
import gyqx.spdhdi.myInfos.dao.mapper.IProvHosInfoMapper;
import gyqx.spdhdi.myInfos.vo.HosInfoVo;
import gyqx.spdhdi.myInfos.vo.HosProvVo;
import gyqx.spdhdi.myInfos.vo.ProvHosInfoVo;

@Repository
public class ProvHosInfoVoDao {
	@Resource
	IProvHosInfoMapper provHosInfoMapper;

	public PageInfo<ProvHosInfoVo> getProvHosInfoVoList(QueryInfo<ProvHosInfoVo> queryInfo) {
		PageHelper.startPage(queryInfo.getPage(), queryInfo.getRows());
		List<ProvHosInfoVo> lstProvHosInfoVo = provHosInfoMapper.getProvHosInfoVoList(queryInfo.getQueryObject());
		String stauts = "";
		for(ProvHosInfoVo p:lstProvHosInfoVo){
			stauts = p.getStatus();
			if("0".equals(stauts)){
				stauts = "已解除";
			}else if("1".equals(stauts)){
				stauts = "医院待审";
			}else if("2".equals(stauts)){
				stauts = "已审核";
			}else{
				stauts = "已驳回";
			}
			p.setStatus(stauts);
		}
		PageInfo<ProvHosInfoVo> ProvHosInfoVoPage = new PageInfo<ProvHosInfoVo>(lstProvHosInfoVo);
		return ProvHosInfoVoPage;
	}
	
	public PageInfo<HosInfoVo> getHosInfoVoList(QueryInfo<HosInfoVo> queryInfo) {
		List<HosInfoVo> lstProvHosInfoVo = provHosInfoMapper.getHosInfoVoList(queryInfo.getQueryObject());
		PageInfo<HosInfoVo> ProvHosInfoVoPage = new PageInfo<HosInfoVo>(lstProvHosInfoVo);
		return ProvHosInfoVoPage;
	}
	
	public PageInfo<HosProvVo> getHosProvVoList(QueryInfo<HosProvVo> queryInfo) {
		List<HosProvVo> lstProvHosInfoVo = provHosInfoMapper.getHosProvVoList(queryInfo.getQueryObject());
		PageInfo<HosProvVo> ProvHosInfoVoPage = new PageInfo<HosProvVo>(lstProvHosInfoVo);
		return ProvHosInfoVoPage;
	}

	public int insertProvHosInfo(ProvHosInfoVo provHosInfoVo) {
		return provHosInfoMapper.insertProvHosInfo(provHosInfoVo);
	}

	public int setStatus(ProvHosInfoVo provHosInfoVo) {
		return provHosInfoMapper.setStatus(provHosInfoVo);
	}

	public int setProvHosInfo(ProvHosInfoVo provHosInfoVo) {
		return provHosInfoMapper.setProvHosInfo(provHosInfoVo);
	}

	public int deleteByPrimaryKey(String id) {
		return provHosInfoMapper.deleteByPrimaryKey(id);
	}

	public boolean existProvHosInfo(ProvHosInfoVo ProvHosInfoVo) {
		Integer count = provHosInfoMapper.existProvHosInfo(ProvHosInfoVo);
//		count = count == null ? 0 : count;
		return count > 0;
	}

	public ProvHosInfoVo getProvHosInfoByid(String id) {
		return provHosInfoMapper.getProvHosInfoByid(id);
	}

	

}
