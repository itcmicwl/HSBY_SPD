package gyqx.spdherp.surgery.service;

import gyqx.spdherp.surgery.vo.PkgLogVo;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
/**
 * 包日志表(PkgLog)表服务接口
 *
 * @author moonbless
 * @since 2018-09-29 14:39:05
 */
public interface IPkgLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PkgLogVo getById(String id);
    /**
     * lst
     * @param pkgLogVo 实例对象
     * @return 实例对象
     */
    List<PkgLogVo> list(PkgLogVo pkgLogVo);
    /**
     * 分页查询
     * @param queryInfo 实例对象
     * @return 实例对象
     */
    QueryResult<PkgLogVo> listPage(QueryInfo<PkgLogVo> queryInfo);

    /**
     * 新增数据
     *
     * @param pkgLogVo 实例对象
     * @return 实例对象
     */
    PkgLogVo insert(PkgLogVo pkgLogVo);
    /**
     * 新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    Integer insertByBatch(List<PkgLogVo> lst);

    /**
     * 修改数据
     *
     * @param pkgLogVo) 实例对象
     * @return 实例对象
     */
    PkgLogVo update(PkgLogVo  pkgLogVo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}