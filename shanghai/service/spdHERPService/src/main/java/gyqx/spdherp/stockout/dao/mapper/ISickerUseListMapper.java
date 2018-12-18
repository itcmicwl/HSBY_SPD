package gyqx.spdherp.stockout.dao.mapper;

import java.util.List;

import com.mysql.jdbc.StringUtils;

import gyqx.spdherp.po.OutStock;
import gyqx.spdherp.po.OutStockUniqueCode;
import gyqx.spdherp.po.SickerUseList;
import gyqx.spdherp.stockout.vo.SickerUserListVo;
import org.apache.ibatis.annotations.Param;

public interface ISickerUseListMapper {
	SickerUserListVo get(@Param("id")String id);
	List<SickerUserListVo> getListByBillId(@Param("billId")String billId);
	int insertByBatch(List<SickerUserListVo> lst);
	int updateByBatch(List<SickerUserListVo> lst);
	int update(SickerUserListVo bean);
	int insert(SickerUserListVo bean);
	int delByBillId(@Param("billId") String billId);
	int delById(@Param("id") String id);
	List<SickerUserListVo> list(SickerUserListVo queryInfo) ;
	int update4Return(List<SickerUserListVo> lst);
}