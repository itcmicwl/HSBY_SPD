package gyqx.ws.yg.dao.mapper;

import gyqx.ws.yg.vo.repVo.YY154_REP_DETAIL;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IYgSendBillMapper {
	YY154_REP_DETAIL get(@Param("psdbh") String psdbh);
	int update(YY154_REP_DETAIL bean);
	int insert(YY154_REP_DETAIL bean);
	int insertByBatch(List<YY154_REP_DETAIL> lst);
	List<YY154_REP_DETAIL> list(YY154_REP_DETAIL queryInfo) ;
}

