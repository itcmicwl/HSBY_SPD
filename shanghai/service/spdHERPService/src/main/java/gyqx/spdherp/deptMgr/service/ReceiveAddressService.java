package gyqx.spdherp.deptMgr.service;

import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.deptMgr.vo.BaseAreaVo;
import gyqx.spdherp.deptMgr.vo.ReceiveAddressVo;

public interface ReceiveAddressService {

	/**
	 * 分页查询科室收货地址
	 * 
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	QueryResult<ReceiveAddressVo> getReceiveAddressList(QueryInfo<ReceiveAddressVo> queryInfo) throws Exception;

	void updateReceiveAddress(ReceiveAddressVo receiveAddressVo) throws Exception; // 更新

	ReceiveAddressVo insertReceiveAddress(ReceiveAddressVo receiveAddressVo) throws Exception; // 新增

	int deleteReceiveAddress(ReceiveAddressVo receiveAddressVo) throws Exception; // 删除

	/**
	 * 查询地理信息
	 * 
	 * @param pid
	 * @return
	 */
	List<BaseAreaVo> queryBaseArea(BaseAreaVo baseAreaVo);

	/**
	 * 设置默认地址
	 * 
	 * @param receiveAddressVo
	 */
	void setDefaultAddr(ReceiveAddressVo receiveAddressVo);

	/**
	 * 上传阳光采购平台
	 * 
	 * @param receiveAddressVo
	 * @throws Exception 
	 */
	String sendYg(ReceiveAddressVo receiveAddressVo) throws Exception;

	/**
	 * 根据科室id获取科室默认收货地址
	 * @param deptId 科室id
	 * @return
	 * @throws Exception
	 */
	ReceiveAddressVo getAddresByDeptId(String deptId) throws Exception;
}
