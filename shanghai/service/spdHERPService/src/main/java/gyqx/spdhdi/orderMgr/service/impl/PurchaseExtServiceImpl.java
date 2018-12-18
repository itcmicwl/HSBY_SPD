package gyqx.spdhdi.orderMgr.service.impl;

import gyqx.spdhdi.orderMgr.vo.PurchaseExtVo;
import gyqx.spdhdi.orderMgr.dao.PurchaseExtDao;
import gyqx.spdhdi.orderMgr.service.IPurchaseExtService;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.utils.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 采购单扩展表(PurchaseExt)表服务实现类
 *
 * @author moonbless
 * @since 2018-09-29 10:36:41
 */
@Service("purchaseExtService")
public class PurchaseExtServiceImpl implements IPurchaseExtService {
    @Resource
    private PurchaseExtDao purchaseExtDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PurchaseExtVo getById(String id) {
        return this.purchaseExtDao.getById(id);
    }
    
    /**
     * 查询
     *
     * @return purchaseExtVo 对象实例
     */
    @Override
    public List<PurchaseExtVo> list(PurchaseExtVo purchaseExtVo) {
        return this.purchaseExtDao.list(purchaseExtVo);
    }
    
    /**
     * 分页查询
     *
     * @return query对象
     */
    @Override
    public QueryResult<PurchaseExtVo> listPage(QueryInfo<PurchaseExtVo> query) {
        PageUtils.startPage(query);
        return PageUtils.endPage(this.purchaseExtDao.list(query.getQueryObject()));
    }

    /**
     * 新增数据
     *
     * @param purchaseExtVo 实例对象
     * @return 实例对象
     */
    @Override
    public PurchaseExtVo insert(PurchaseExtVo purchaseExtVo) {
        this.purchaseExtDao.insert(purchaseExtVo);
        return purchaseExtVo;
    }
    /**
     * 批量新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    @Override
    public int insertByBatch(List<PurchaseExtVo> lst) {
        return this.purchaseExtDao.insertByBatch(lst);
    }
    /**
     * 修改数据
     *
     * @param purchaseExtVo 实例对象
     * @return 实例对象
     */
    @Override
    public PurchaseExtVo update(PurchaseExtVo purchaseExtVo) {
        this.purchaseExtDao.update(purchaseExtVo);
        return this.getById(purchaseExtVo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.purchaseExtDao.deleteById(id);
    }
}