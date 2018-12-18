package gyqx.spdherp.forRiva.dao.mapper;

import gyqx.spdhdi.orderMgr.vo.DistrBillListVo;
import org.apache.ibatis.annotations.Param;

public interface IPrintMapper {
	String receiveJfCodeById(@Param("goodsId") String goodsId,
                             @Param("hosId") String hosId);
	DistrBillListVo getById(@Param("id")String id);
}

