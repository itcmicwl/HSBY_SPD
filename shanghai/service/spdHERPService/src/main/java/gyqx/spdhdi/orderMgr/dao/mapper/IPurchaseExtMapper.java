package gyqx.spdhdi.orderMgr.dao.mapper;

import gyqx.spdhdi.orderMgr.vo.PurchaseExtVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 采购单扩展表(PurchaseExt)表数据库访问层
 *
 * @author moonbless
 * @since 2018-09-29 10:36:41
 */
public interface IPurchaseExtMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PurchaseExtVo getById(String id);

     /**
     * 通过实体作为筛选条件查询
     *
     * @param purchaseExtVo 实例对象
     * @return 对象列表
     */
    List<PurchaseExtVo> list(PurchaseExtVo purchaseExtVo);

    /**
     * 新增数据
     *
     * @param purchaseExtVo 实例对象
     * @return 影响行数
     */
    int insert(PurchaseExtVo purchaseExtVo);
    /**
     * 批量新增
     *
     * @return 影响行数
     */
    int insertByBatch(@Param("list") List<PurchaseExtVo> lst);

    /**
     * 修改数据
     *
     * @param purchaseExtVo 实例对象
     * @return 影响行数
     */
    int update(PurchaseExtVo purchaseExtVo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}