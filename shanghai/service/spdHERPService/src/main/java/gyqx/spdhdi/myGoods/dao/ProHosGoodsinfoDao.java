package gyqx.spdhdi.myGoods.dao;

import gyqx.spdhdi.myGoods.dao.mapper.IProHosGoodsinfoMapper;
import gyqx.spdhdi.myGoods.vo.ProHosGoodsVo;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author moonbless
 * @since 2018-11-28 10:08:42
 */
@Repository
public class ProHosGoodsinfoDao {
    @Resource
    private IProHosGoodsinfoMapper vProHosGoodsinfoMapper;
    
    /**
     * 通过ID查询单条数据
     *
     * @param
     * @return 实例对象
     */
    public ProHosGoodsVo getById(String id) {
        return this.vProHosGoodsinfoMapper.getById(id);
    }
    
    /**
     * 通过对象实例查询列表
     *
     * @param query
     * @return 实例对象
     */
    public List<ProHosGoodsVo> list(ProHosGoodsVo query) {
        return this.vProHosGoodsinfoMapper.list(query);
    }
    
    }