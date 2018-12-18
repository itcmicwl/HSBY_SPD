package gyqx.ws.yg.dao.mapper;

import gyqx.ws.yg.vo.repVo.YY155_REP_DETAIL;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IYgSendBillListMapper {
	YY155_REP_DETAIL get(@Param("psmxbh") String psmxbh);
	int update(YY155_REP_DETAIL bean);
	int updateByBatch(List<YY155_REP_DETAIL> lst);
	int insert(YY155_REP_DETAIL bean);
	int insertByBatch(List<YY155_REP_DETAIL> lst);
	List<YY155_REP_DETAIL> list(YY155_REP_DETAIL queryInfo) ;
}

