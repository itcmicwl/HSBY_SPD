package gyqx.spdhdi.orderMgr.dao;

import gyqx.spdhdi.orderMgr.vo.PurchaseExtVo;
import gyqx.spdhdi.orderMgr.dao.mapper.IPurchaseExtMapper;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author moonbless
 * @since 2018-09-29 10:36:41
 */
@Repository
public class PurchaseExtDao {
    @Resource
    private IPurchaseExtMapper purchaseExtMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public PurchaseExtVo getById(String id) {
        return this.purchaseExtMapper.getById(id);
    }
    
    /**
     * 通过对象实例查询列表
     *
     * @param query
     * @return 实例对象
     */
    public List<PurchaseExtVo> list(PurchaseExtVo query) {
        return this.purchaseExtMapper.list(query);
    }
    
    /**
     * 新增数据
     *
     * @param purchaseExtVo 实例对象
     * @return 实例对象
     */
    public PurchaseExtVo insert(PurchaseExtVo purchaseExtVo) {
        this.purchaseExtMapper.insert(purchaseExtVo);
        return purchaseExtVo;
    }
    
    /**
     * 批量新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    public int insertByBatch(List<PurchaseExtVo> lst) {
        return this.purchaseExtMapper.insertByBatch(lst);
    }
    /**
     * 修改数据
     *
     * @param purchaseExtVo 实例对象
     * @return 实例对象
     */
    public PurchaseExtVo update(PurchaseExtVo purchaseExtVo) {
        this.purchaseExtMapper.update(purchaseExtVo);
        return this.getById(purchaseExtVo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(String id) {
        return this.purchaseExtMapper.deleteById(id) > 0;
    }
}