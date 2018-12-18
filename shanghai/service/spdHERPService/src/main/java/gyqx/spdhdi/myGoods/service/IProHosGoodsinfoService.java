package gyqx.spdhdi.myGoods.service;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdhdi.myGoods.vo.ProHosGoodsVo;

/**
 * (ProHosGoodsinfo)表服务接口
 *
 * @author moonbless
 * @since 2018-11-28 10:08:42
 */
public interface IProHosGoodsinfoService {


    ProHosGoodsVo getById(String id) throws Exception;
    /**
     * lst
     * @param vProHosGoodsinfoVo 实例对象
     * @return 实例对象
     */
    List<ProHosGoodsVo> list(ProHosGoodsVo vProHosGoodsinfoVo) throws Exception;
    /**
     * 分页查询
     * @param queryInfo 实例对象
     * @return 实例对象
     */
    QueryResult<ProHosGoodsVo> listPage(QueryInfo<ProHosGoodsVo> queryInfo) throws Exception;


}