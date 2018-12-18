package gyqx.spdherp.applyMgr.service;

import gyqx.spdherp.applyMgr.vo.ApplySurgeryPkgVo;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
/**
 * 手术包请购单表(ApplySurgeryPkg)表服务接口
 *
 * @author moonbless
 * @since 2018-10-09 09:07:14
 */
public interface IApplySurgeryPkgService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ApplySurgeryPkgVo getById(String id);
    /**
     * lst
     * @param applySurgeryPkgVo 实例对象
     * @return 实例对象
     */
    List<ApplySurgeryPkgVo> list(ApplySurgeryPkgVo applySurgeryPkgVo);
    /**
     * 分页查询
     * @param queryInfo 实例对象
     * @return 实例对象
     */
    QueryResult<ApplySurgeryPkgVo> listPage(QueryInfo<ApplySurgeryPkgVo> queryInfo);

    /**
     * 新增数据
     *
     * @param applySurgeryPkgVo 实例对象
     * @return 实例对象
     */
    ApplySurgeryPkgVo insert(ApplySurgeryPkgVo applySurgeryPkgVo);
    /**
     * 新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    Integer insertByBatch(List<ApplySurgeryPkgVo> lst);

    /**
     * 修改数据
     *
     * @param applySurgeryPkgVo) 实例对象
     * @return 实例对象
     */
    ApplySurgeryPkgVo update(ApplySurgeryPkgVo  applySurgeryPkgVo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);
    /**
     * 通过主键删除数据
     *
     * @param billId 请购单号
     * @return 是否成功
     */
    List<ApplySurgeryPkgVo> getSurInfoByApplyBillId(String billId);

    /**
     * 手术包打包后回写请购单手术包打包数量
     * @param lst
     */
    void setSurQtyByPack(List<ApplySurgeryPkgVo> lst);
}