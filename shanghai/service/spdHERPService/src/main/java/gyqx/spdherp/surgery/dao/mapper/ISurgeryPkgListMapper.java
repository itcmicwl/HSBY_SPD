package gyqx.spdherp.surgery.dao.mapper;

import gyqx.spdherp.po.SurgeryPkgList;
import gyqx.spdherp.surgery.vo.SurgeryPkgListVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 手术包订单明细表(SurgeryPkgList)表数据库访问层
 *
 * @author moonbless
 * @since 2018-09-29 14:41:33
 */
public interface ISurgeryPkgListMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SurgeryPkgListVo getById(String id);

     /**
     * 通过实体作为筛选条件查询
     *
     * @param surgeryPkgListVo 实例对象
     * @return 对象列表
     */
    List<SurgeryPkgListVo> list(SurgeryPkgListVo surgeryPkgListVo);
    /**
     * 通过实体作为筛选条件查询
     *
     * @param surgeryPkgListVo 实例对象
     * @return 对象列表
     */
    List<SurgeryPkgListVo> listVo(SurgeryPkgListVo surgeryPkgListVo);

    /**
     * 新增数据
     *
     * @param surgeryPkgListVo 实例对象
     * @return 影响行数
     */
    int insert(SurgeryPkgListVo surgeryPkgListVo);
    /**
     * 批量新增
     *
     * @return 影响行数
     */
    int insertByBatch(List<SurgeryPkgListVo> lst);

    /**
     * 修改数据
     *
     * @param surgeryPkgListVo 实例对象
     * @return 影响行数
     */
    int update(SurgeryPkgListVo surgeryPkgListVo);
    /**
     * 批量更新状态
     *
     * @param lst 实例对象
     * @return 影响行数
     */
    int updateStatus(List<SurgeryPkgList> lst);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}