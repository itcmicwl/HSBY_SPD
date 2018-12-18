package gyqx.spdhdi.myGoods.dao.mapper;
import gyqx.spdhdi.myGoods.vo.ProHosGoodsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * (ProHosGoodsinfo)表数据库访问层
 *
 * @author moonbless
 * @since 2018-11-28 10:08:42
 */
public interface IProHosGoodsinfoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param
     * @return 实例对象
     */
    ProHosGoodsVo getById(@Param("id")String id);

     /**
     * 通过实体作为筛选条件查询
     *
     * @param vProHosGoodsinfoVo 实例对象
     * @return 对象列表
     */
    List<ProHosGoodsVo> list(ProHosGoodsVo vProHosGoodsinfoVo);
}