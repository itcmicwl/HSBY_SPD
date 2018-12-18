package gyqx.spdherp.provManager.service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.provManager.vo.ProvDeptUserRelationVo;
import gyqx.spdherp.provManager.vo.ProvDeptUserVo;
import gyqx.spdherp.provManager.vo.ProvHosRelationVo;
import gyqx.spdherp.provManager.vo.ProvUserRelationVo;

public interface IProvDeptUserRelationService {
	
	/**
	 * 执行保存供应商科室人员关联关系
	 * @param provDeptUserRelationVo
	 * @throws Exception 
	 */
	public void processProvDeptUserRelation(ProvDeptUserRelationVo provDeptUserRelationVo) throws Exception;
	
	/**
	 * 刪除关联关系
	 * @param provDeptUserRelationVo
	 * @throws Exception
	 */
	public void processDelProvDeptUserRelation(ProvDeptUserRelationVo provDeptUserRelationVo) throws Exception;
	
	/**
	 * 查询人员信息
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	public QueryResult<ProvDeptUserVo> getUserList(QueryInfo<ProvDeptUserRelationVo> queryInfo) throws Exception;

	/**
	 * 查询人员信息
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	public QueryResult<ProvDeptUserVo> getCheckRelUserList(QueryInfo<ProvDeptUserRelationVo> queryInfo) throws Exception;


	/**
	 * 根据用户id查询关联供应商信息
	 * @param queryInfo
	 * @return
	 */
	public QueryResult<ProvUserRelationVo> getUserProvRelationList(QueryInfo<ProvUserRelationVo> queryInfo)  throws Exception;

	/**
	 * 查询除关系人已建立关系的医院与供应商关系列表
	 * @param queryInfo
	 * @return
	 */
	public QueryResult<ProvHosRelationVo> getUserHosProvRelationList(QueryInfo<ProvHosRelationVo> queryInfo)  throws Exception;

}
