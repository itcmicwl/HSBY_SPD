package gyqx.spdherp.surgery.dao.mapper;

import gyqx.spdherp.po.SurgeryPkgBatch;
import gyqx.spdherp.surgery.vo.SurgeryPkgBatchVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 手术包订单批次表 (SurgeryPkgBatch)表数据库访问层
 *
 * @author moonbless
 * @since 2018-09-29 16:29:13
 */
public interface ISurgeryPkgBatchMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SurgeryPkgBatchVo getById(String id);

     /**
     * 通过实体作为筛选条件查询
     *
     * @param surgeryPkgBatchVo 实例对象
     * @return 对象列表
     */
    List<SurgeryPkgBatchVo> list(SurgeryPkgBatchVo surgeryPkgBatchVo);

    /**
     * 新增数据
     *
     * @param surgeryPkgBatchVo 实例对象
     * @return 影响行数
     */
    int insert(SurgeryPkgBatchVo surgeryPkgBatchVo);
    /**
     * 批量新增
     *
     * @return 影响行数
     */
    int insertByBatch(List<SurgeryPkgBatchVo> lst);

    /**
     * 修改数据
     *
     * @param surgeryPkgBatchVo 实例对象
     * @return 影响行数
     */
    int update(SurgeryPkgBatchVo surgeryPkgBatchVo);
    int updateStatus(List<SurgeryPkgBatch> lst);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}