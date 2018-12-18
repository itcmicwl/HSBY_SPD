package gyqx.spdherp.surgery.dao.mapper;

import gyqx.spdherp.po.SurgeryPkg;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 手术包订单主表(SurgeryPkg)表数据库访问层
 *
 * @author moonbless
 * @since 2018-09-29 14:37:14
 */
public interface ISurgeryPkgMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SurgeryPkgVo getById(String id);

     /**
     * 通过实体作为筛选条件查询
     *
     * @param surgeryPkgVo 实例对象
     * @return 对象列表
     */
    List<SurgeryPkgVo> list(SurgeryPkgVo surgeryPkgVo);
    /**
     * 通过实体作为筛选条件查询
     *
     * @param surgeryPkgVo 实例对象
     * @return 对象列表
     */
    List<SurgeryPkgVo> listVo(SurgeryPkgVo surgeryPkgVo);
    List<SurgeryPkgVo> list4Use(SurgeryPkgVo surgeryPkgVo);
    /**
     * 新增数据
     *
     * @param surgeryPkgVo 实例对象
     * @return 影响行数
     */
    int insert(SurgeryPkgVo surgeryPkgVo);
    /**
     * 批量新增
     *
     * @return 影响行数
     */
    int insertByBatch(List<SurgeryPkgVo> lst);

    /**
     * 修改数据
     *
     * @param surgeryPkgVo 实例对象
     * @return 影响行数
     */
    int update(SurgeryPkgVo surgeryPkgVo);
    int updateByBatch(List<SurgeryPkgVo> surgeryPkgVo);
    int updateStatusByBatch(List<SurgeryPkg> surgeryPkgVo) throws Exception;
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}