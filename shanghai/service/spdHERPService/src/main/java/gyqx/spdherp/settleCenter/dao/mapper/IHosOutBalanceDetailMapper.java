package gyqx.spdherp.settleCenter.dao.mapper;

import java.util.List;

import gyqx.spdherp.settleCenter.vo.HosOutBalanceDetailVo;
import org.apache.ibatis.annotations.Param;

public interface IHosOutBalanceDetailMapper {
	HosOutBalanceDetailVo get(String id);
	int update(HosOutBalanceDetailVo bean);
	int insert(HosOutBalanceDetailVo bean);
	int insertByBatch(List<HosOutBalanceDetailVo> lst);
	int updateByBatch(List<HosOutBalanceDetailVo> lst);
	int delById(@Param("id") String id);
	int delByBillId(@Param("billId") String billId);
	List<HosOutBalanceDetailVo> list(HosOutBalanceDetailVo queryInfo) ;
}

