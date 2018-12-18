package gyqx.spdherp.forRiva.dao.mapper;

import gyqx.spdhdi.orderMgr.vo.DistrBillListVo;
import org.apache.ibatis.annotations.Param;

public interface ICallForRivaMapper {
	String getStocIdByEname(@Param("hosId") String hosId,@Param("ename") String ename);
}

