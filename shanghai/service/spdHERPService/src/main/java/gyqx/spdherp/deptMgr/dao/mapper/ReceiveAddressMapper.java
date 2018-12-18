package gyqx.spdherp.deptMgr.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gyqx.spdherp.deptMgr.vo.BaseAreaVo;
import gyqx.spdherp.deptMgr.vo.ReceiveAddressVo;

public interface ReceiveAddressMapper {
	
	/**
	 * 分页查询科室地址
	 * @param receiveAddressVo
	 * @return
	 */
	List<ReceiveAddressVo> getReceiveAddressList(ReceiveAddressVo receiveAddressVo);
	
	/**
	 * 更新
	 * @param receiveAddressVo
	 */
	void updateReceiveAddress(ReceiveAddressVo receiveAddressVo);
	
	/**
	 * 新增
	 * @param receiveAddressVo
	 */
	void insertReceiveAddress(ReceiveAddressVo receiveAddressVo);
	
	/**
	 * 查询地理信息
	 * @param pid
	 * @return
	 */
	List<BaseAreaVo> queryBaseArea(@Param("pid") String pid);
	
	/**
	 * 根据id查询地理信息
	 * @param id
	 * @return
	 */
	BaseAreaVo queryBaseAreaById(@Param("id") String id);
	
	/**
	 * 根据id查询收货地址
	 * @param id
	 * @return
	 */
	ReceiveAddressVo queryReceiveAddressById(@Param("id") String id);
	ReceiveAddressVo getAddresByDeptId(@Param("deptId")String deptId);
}
