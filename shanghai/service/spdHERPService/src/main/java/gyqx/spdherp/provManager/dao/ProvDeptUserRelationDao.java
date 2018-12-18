package gyqx.spdherp.provManager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.db.query.QueryInfo;
import gyqx.spdherp.provManager.dao.mapper.IProvDeptUserRelationMapper;
import gyqx.spdherp.provManager.vo.ProvDeptUserVo;
import gyqx.spdherp.provManager.vo.ProvHosRelationVo;
import gyqx.spdherp.provManager.vo.ProvUserRelationVo;

@Repository
public class ProvDeptUserRelationDao {

	@Autowired
	private IProvDeptUserRelationMapper provDeptUserRelationMapper;

	/**
	 * 批量删除供应商科室人员关联关系
	 * @param hosId 医院id
	 * @param userId 人员id
	 * @param provIds 供应商id
	 */
	public void deleteProvDeptUserRelation(String hosId, String userId, List<String> provIds) {
		provDeptUserRelationMapper.deleteProvDeptUserRelation(hosId, userId, provIds);
	}
	
	/**
	 * 查询医院科室人员信息且不在已关联数据
	 * @param provId 供应商id
	 * @param hosId 医院id
	 * @param deptId 科室id
	 * @param ename 用戶
	 * @return
	 */
	public List<ProvDeptUserVo> getUserList(String provId,String hosId,String deptId,String ename) throws Exception{		
		
		return provDeptUserRelationMapper.getUserList(provId,hosId,deptId,ename);
	}
	
	/**
	 * 查询医院科室人员信息
	 * @param provId 供应商id
	 * @param hosId 医院id
	 * @param deptId 科室id
	 * @return
	 */
	public List<ProvDeptUserVo> getCheckRelUserList(String provId,String hosId,String deptId) throws Exception{		
		
		return provDeptUserRelationMapper.getCheckRelUserList(provId, hosId, deptId);
	}
	
	/**
	 * 根据用户id查询关联供应商信息
	 * 
	 * @param hosId
	 *            医院id
	 * @param userId
	 *            用户id
	 * @param provName
	 *            供应商名称
	 * @return
	 */
	public List<ProvUserRelationVo> getUserProvRelationList(String hosId, String userId, String provName) {
		
		return provDeptUserRelationMapper.getUserProvRelationList(hosId, userId, provName);
	}
	
	/**
	 * 查询除关系人已建立关系的医院与供应商关系列表
	 * 
	 * @param hosId
	 *            医院id
	 * @param userId
	 *            用户id
	 * @param provName
	 *            供应商名称
	 * @return
	 */
	public List<ProvHosRelationVo> getUserHosProvRelationList(String hosId, String userId, String provName) {
		return provDeptUserRelationMapper.getUserHosProvRelationList(hosId, userId, provName);
	}

}
