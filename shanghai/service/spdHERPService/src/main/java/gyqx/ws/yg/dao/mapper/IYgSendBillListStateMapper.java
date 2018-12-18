package gyqx.ws.yg.dao.mapper;

import gyqx.ws.yg.vo.repVo.YY161_REP_DETAIL;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IYgSendBillListStateMapper {
	YY161_REP_DETAIL get(@Param("psmxbh") String psmxbh);
	int updateByBatch(List<YY161_REP_DETAIL> lst);
	int insertByBatch(List<YY161_REP_DETAIL> lst);
	int update(YY161_REP_DETAIL bean);
	int insert(YY161_REP_DETAIL bean);
	List<YY161_REP_DETAIL> list(YY161_REP_DETAIL queryInfo) ;
}

