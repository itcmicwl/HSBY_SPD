package gyqx.spdherp.surgery.dao.mapper;

import gyqx.spdherp.surgery.vo.PkgLogVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 包日志表(PkgLog)表数据库访问层
 *
 * @author moonbless
 * @since 2018-09-29 14:39:05
 */
public interface IPkgLogMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PkgLogVo getById(String id);

     /**
     * 通过实体作为筛选条件查询
     *
     * @param pkgLogVo 实例对象
     * @return 对象列表
     */
    List<PkgLogVo> list(PkgLogVo pkgLogVo);

    /**
     * 新增数据
     *
     * @param pkgLogVo 实例对象
     * @return 影响行数
     */
    int insert(PkgLogVo pkgLogVo);
    /**
     * 批量新增
     *
     * @return 影响行数
     */
    int insertByBatch(List<PkgLogVo> lst);

    /**
     * 修改数据
     *
     * @param pkgLogVo 实例对象
     * @return 影响行数
     */
    int update(PkgLogVo pkgLogVo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}