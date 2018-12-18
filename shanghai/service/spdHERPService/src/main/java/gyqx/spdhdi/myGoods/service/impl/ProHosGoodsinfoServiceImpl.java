package gyqx.spdhdi.myGoods.service.impl;

import gyqx.spdhdi.myGoods.vo.ProHosGoodsVo;
import gyqx.spdhdi.myGoods.dao.ProHosGoodsinfoDao;
import gyqx.spdhdi.myGoods.service.IProHosGoodsinfoService;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import common.db.SimpleDao;
import common.utils.UtilsContext;
/**
 * (ProHosGoodsinfo)表服务实现类
 *
 * @author moonbless
 * @since 2018-11-28 10:08:42
 */
@Service("vProHosGoodsinfoService")
public class ProHosGoodsinfoServiceImpl implements IProHosGoodsinfoService {
    @Autowired
    private UtilsContext utilsContext;
    @Autowired
    private SimpleDao simpleDao;
    @Resource
    private ProHosGoodsinfoDao proHosGoodsinfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param  id
     * @return 实例对象
     */
    @Override
    public ProHosGoodsVo getById(String id) throws Exception {
        return this.proHosGoodsinfoDao.getById(id);
    }
    
    /**
     * 查询
     *
     * @return vProHosGoodsinfoVo 对象实例
     */
    @Override
    public List<ProHosGoodsVo> list(ProHosGoodsVo vProHosGoodsinfoVo) throws Exception {
        return this.proHosGoodsinfoDao.list(vProHosGoodsinfoVo);
    }
    
    /**
     * 分页查询
     *
     * @return query对象
     */
    @Override
    public QueryResult<ProHosGoodsVo> listPage(QueryInfo<ProHosGoodsVo> query) throws Exception {
        PageUtils.startPage(query);
        return PageUtils.endPage(this.proHosGoodsinfoDao.list(query.getQueryObject()));
    }
}