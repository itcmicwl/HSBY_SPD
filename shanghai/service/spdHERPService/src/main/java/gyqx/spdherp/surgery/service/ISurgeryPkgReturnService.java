package gyqx.spdherp.surgery.service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;

import java.util.List;

/**
 * 手术包订单主表(SurgeryPkg)表服务接口
 *
 * @author moonbless
 * @since 2018-09-29 14:37:09
 */
public interface ISurgeryPkgReturnService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SurgeryPkgVo getById(String id);
    /**
     * lst
     * @param surgeryPkgVo 实例对象
     * @return 实例对象
     */
    List<SurgeryPkgVo> list(SurgeryPkgVo surgeryPkgVo);
    /**
     * 分页查询
     * @param queryInfo 实例对象
     * @return 实例对象
     */
    QueryResult<SurgeryPkgVo> listPage(QueryInfo<SurgeryPkgVo> queryInfo);


}