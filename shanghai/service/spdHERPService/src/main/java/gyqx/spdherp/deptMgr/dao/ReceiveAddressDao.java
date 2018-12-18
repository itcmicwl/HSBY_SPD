package gyqx.spdherp.deptMgr.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import common.db.query.QueryInfo;
import common.utils.UserOnlineStateUtils;
import gyqx.spdherp.deptMgr.dao.mapper.ReceiveAddressMapper;
import gyqx.spdherp.deptMgr.vo.BaseAreaVo;
import gyqx.spdherp.deptMgr.vo.ReceiveAddressVo;

@Repository
public class ReceiveAddressDao {

	@Resource
	private ReceiveAddressMapper mapper;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;

	public PageInfo<ReceiveAddressVo> getReceiveAddressList(QueryInfo<ReceiveAddressVo> queryInfo) {
		PageHelper.startPage(queryInfo.getPage(), queryInfo.getRows());
		List<ReceiveAddressVo> receiveAddressList = mapper.getReceiveAddressList(queryInfo.getQueryObject());
		// 处理一些状态数据等
		PageInfo<ReceiveAddressVo> pageInfo = new PageInfo<>(receiveAddressList);
		return pageInfo;
	}

	public void updateReceiveAddress(ReceiveAddressVo receiveAddressVo) {
		ReceiveAddressVo vo = new ReceiveAddressVo();
		vo.setHosId(receiveAddressVo.getHosId());
		vo.setDeptId(receiveAddressVo.getDeptId());
		vo.setIsDefaulte("1");
		List<ReceiveAddressVo> receiveAddressList = mapper.getReceiveAddressList(vo);
		if (receiveAddressList.size() == 1) {
			if (!receiveAddressList.get(0).getId().equals(receiveAddressVo.getId())) {
				receiveAddressList.get(0).setIsDefaulte("0");
				mapper.updateReceiveAddress(receiveAddressList.get(0));
			}
		}
		mapper.updateReceiveAddress(receiveAddressVo);
	}

	public ReceiveAddressVo insertReceiveAddress(ReceiveAddressVo receiveAddressVo) {
		ReceiveAddressVo vo = new ReceiveAddressVo();
		vo.setHosId(receiveAddressVo.getHosId());
		vo.setDeptId(receiveAddressVo.getDeptId());
		vo.setIsDefaulte("1");
		List<ReceiveAddressVo> receiveAddressList = mapper.getReceiveAddressList(vo);
		if (receiveAddressVo.getIsDefaulte().equals("1") && receiveAddressList.size() == 1) {
			receiveAddressList.get(0).setIsDefaulte("0");
			mapper.updateReceiveAddress(receiveAddressList.get(0));
		}
		mapper.insertReceiveAddress(receiveAddressVo);
		return receiveAddressVo;
	}


	public List<BaseAreaVo> queryBaseArea(String pid) {
		return mapper.queryBaseArea(pid);
	}

	public void setDefaultAddr(ReceiveAddressVo receiveAddressVo) {
		ReceiveAddressVo addressVo = mapper.queryReceiveAddressById(receiveAddressVo.getId());
		if(addressVo.getIsDefaulte().equals("0")){
			addressVo.setIsDefaulte("1");
			ReceiveAddressVo vo = new ReceiveAddressVo();
			vo.setHosId(addressVo.getHosId());
			vo.setDeptId(addressVo.getDeptId());
			vo.setIsDefaulte("1");
			List<ReceiveAddressVo> addrList = mapper.getReceiveAddressList(vo);
			if (addrList.size() == 1) {
				if (!addrList.get(0).getId().equals(addressVo.getId())) {
					addrList.get(0).setIsDefaulte("0");
					mapper.updateReceiveAddress(addrList.get(0));
				}
			}
		}else{
			if(receiveAddressVo.getIsDefaulte() == null){
				addressVo.setIsDefaulte("0");
			}
		}
		mapper.updateReceiveAddress(addressVo);
	}
	public ReceiveAddressVo getAddresByDeptId(String deptId){
		return mapper.getAddresByDeptId(deptId);
	}
}
