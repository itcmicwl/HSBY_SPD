package gyqx.spdherp.applyMgr.dao.mapper;

import gyqx.spdherp.applyMgr.vo.ApplySurgeryPkgVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 手术包请购单表(ApplySurgeryPkg)表数据库访问层
 *
 * @author moonbless
 * @since 2018-10-09 09:07:14
 */
public interface IApplySurgeryPkgMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ApplySurgeryPkgVo getById(String id);

     /**
     * 通过实体作为筛选条件查询
     *
     * @param applySurgeryPkgVo 实例对象
     * @return 对象列表
     */
    List<ApplySurgeryPkgVo> list(ApplySurgeryPkgVo applySurgeryPkgVo);

    /**
     * 新增数据
     *
     * @param applySurgeryPkgVo 实例对象
     * @return 影响行数
     */
    int insert(ApplySurgeryPkgVo applySurgeryPkgVo);
    /**
     * 批量新增
     *
     * @return 影响行数
     */
    int insertByBatch(List<ApplySurgeryPkgVo> lst);

    /**
     * 修改数据
     *
     * @param applySurgeryPkgVo 实例对象
     * @return 影响行数
     */
    int update(ApplySurgeryPkgVo applySurgeryPkgVo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);
    List<ApplySurgeryPkgVo> getSurInfoByApplyBillId(@Param("billId")String billId);
    void setSurQtyByPack(List<ApplySurgeryPkgVo> lst);
}