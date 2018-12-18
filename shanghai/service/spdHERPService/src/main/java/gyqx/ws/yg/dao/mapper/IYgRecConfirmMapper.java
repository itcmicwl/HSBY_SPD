package gyqx.ws.yg.dao.mapper;

import gyqx.ws.yg.vo.repVo.YY161_REP_DETAIL;
import gyqx.ws.yg.vo.reqVo.YY131_REQ_DETAIL;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IYgRecConfirmMapper {
	YY131_REQ_DETAIL get(@Param("psmxbh") String psmxbh);
	int setBillConfirm(@Param("psdbh") String psdbh);
	int updateByBatch(List<YY131_REQ_DETAIL> lst);
	int insertByBatch(List<YY131_REQ_DETAIL> lst);
	int update(YY131_REQ_DETAIL bean);
	int insert(YY131_REQ_DETAIL bean);
	List<YY131_REQ_DETAIL> list(YY131_REQ_DETAIL queryInfo) ;
}

