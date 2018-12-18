package gyqx.spdherp.surgery.service;

import gyqx.spdherp.surgery.vo.SurgeryPkgBatchVo;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
/**
 * 手术包订单批次表 (SurgeryPkgBatch)表服务接口
 *
 * @author moonbless
 * @since 2018-09-29 16:29:10
 */
public interface ISurgeryPkgBatchService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SurgeryPkgBatchVo getById(String id);
    /**
     * lst
     * @param surgeryPkgBatchVo 实例对象
     * @return 实例对象
     */
    List<SurgeryPkgBatchVo> list(SurgeryPkgBatchVo surgeryPkgBatchVo);
    /**
     * 分页查询
     * @param queryInfo 实例对象
     * @return 实例对象
     */
    QueryResult<SurgeryPkgBatchVo> listPage(QueryInfo<SurgeryPkgBatchVo> queryInfo);

    /**
     * 新增数据
     *
     * @param surgeryPkgBatchVo 实例对象
     * @return 实例对象
     */
    SurgeryPkgBatchVo insert(SurgeryPkgBatchVo surgeryPkgBatchVo);
    /**
     * 新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    Integer insertByBatch(List<SurgeryPkgBatchVo> lst);

    /**
     * 修改数据
     *
     * @param surgeryPkgBatchVo) 实例对象
     * @return 实例对象
     */
    SurgeryPkgBatchVo update(SurgeryPkgBatchVo  surgeryPkgBatchVo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}