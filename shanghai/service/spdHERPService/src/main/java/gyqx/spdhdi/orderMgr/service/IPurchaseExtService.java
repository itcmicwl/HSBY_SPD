package gyqx.spdhdi.orderMgr.service;

import gyqx.spdhdi.orderMgr.vo.PurchaseExtVo;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
/**
 * 采购单扩展表(PurchaseExt)表服务接口
 *
 * @author moonbless
 * @since 2018-09-29 10:36:41
 */
public interface IPurchaseExtService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PurchaseExtVo getById(String id);
    /**
     * lst
     * @param purchaseExtVo 实例对象
     * @return 实例对象
     */
    List<PurchaseExtVo> list(PurchaseExtVo purchaseExtVo);
    /**
     * 分页查询
     * @param queryInfo 实例对象
     * @return 实例对象
     */
    QueryResult<PurchaseExtVo> listPage(QueryInfo<PurchaseExtVo> queryInfo);

    /**
     * 新增数据
     *
     * @param purchaseExtVo 实例对象
     * @return 实例对象
     */
    PurchaseExtVo insert(PurchaseExtVo purchaseExtVo);
    /**
     * 新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    int insertByBatch(List<PurchaseExtVo> lst);

    /**
     * 修改数据
     *
     * @param purchaseExtVo) 实例对象
     * @return 实例对象
     */
    PurchaseExtVo update(PurchaseExtVo  purchaseExtVo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}