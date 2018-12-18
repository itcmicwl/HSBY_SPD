package gyqx.spdherp.surgery.service;

import gyqx.spdherp.surgery.vo.SurgeryPkgBatchVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgListVo;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
/**
 * 手术包订单明细表(SurgeryPkgList)表服务接口
 *
 * @author moonbless
 * @since 2018-09-29 14:41:33
 */
public interface ISurgeryPkgListService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SurgeryPkgListVo getById(String id);
    /**
     * lst
     * @param surgeryPkgListVo 实例对象
     * @return 实例对象
     */
    List<SurgeryPkgListVo> list(SurgeryPkgListVo surgeryPkgListVo);
    /**
     * 分页查询
     * @param queryInfo 实例对象
     * @return 实例对象
     */
    QueryResult<SurgeryPkgListVo> listPage(QueryInfo<SurgeryPkgListVo> queryInfo);

    /**
     * 新增数据
     *
     * @param surgeryPkgListVo 实例对象
     * @return 实例对象
     */
    SurgeryPkgListVo insert(SurgeryPkgListVo surgeryPkgListVo);
    /**
     * 新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    Integer insertByBatch(List<SurgeryPkgListVo> lst);

    /**
     * 修改数据
     *
     * @param surgeryPkgListVo) 实例对象
     * @return 实例对象
     */
    SurgeryPkgListVo update(SurgeryPkgListVo  surgeryPkgListVo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}