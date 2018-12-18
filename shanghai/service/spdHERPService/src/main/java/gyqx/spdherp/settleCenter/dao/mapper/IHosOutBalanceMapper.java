package gyqx.spdherp.settleCenter.dao.mapper;

import java.util.List;

import gyqx.spdherp.settleCenter.vo.HosOutBalanceVo;
import org.apache.ibatis.annotations.Param;

public interface IHosOutBalanceMapper {
	int update(HosOutBalanceVo bean);
	int insert(HosOutBalanceVo bean);
	int delById(@Param("id") String id);
	HosOutBalanceVo getById(@Param("id") String id);
	HosOutBalanceVo getByBillId(@Param("billId") String billId);
	List<HosOutBalanceVo> list(HosOutBalanceVo queryInfo) ;
}

