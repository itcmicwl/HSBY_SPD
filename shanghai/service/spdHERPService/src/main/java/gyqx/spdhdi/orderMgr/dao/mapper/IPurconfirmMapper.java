package gyqx.spdhdi.orderMgr.dao.mapper;

import gyqx.spdhdi.orderMgr.vo.PurconfirmVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPurconfirmMapper {
	int insert(PurconfirmVo purconfirmVo);
	int update(PurconfirmVo purconfirmVo);
	int updateByBatch(List<PurconfirmVo> purconfirmVo);
	int delete(PurconfirmVo purconfirmVo);
	PurconfirmVo get(@Param("id") String id);
	PurconfirmVo getByBillid(@Param("billid") String billid);
	List<PurconfirmVo> list(PurconfirmVo purconfirmVo) ;
	List<PurconfirmVo> list4yg(PurconfirmVo purconfirmVo);
}

