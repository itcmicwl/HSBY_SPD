package gyqx.spdherp.surgery.service;

import gyqx.spdherp.applyMgr.vo.ApplySurPkgVo;
import gyqx.spdherp.applyMgr.vo.ApplySurgeryPkgVo;
import gyqx.spdherp.po.SurgeryPkg;
import gyqx.spdherp.stockout.vo.BigBatch4FillVo;
import gyqx.spdherp.stockout.vo.SickerUserListVo;
import gyqx.spdherp.surgery.vo.SurBill4Pack;
import gyqx.spdherp.surgery.vo.SurPackResVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;
import java.util.List;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
/**
 * 手术包订单主表(SurgeryPkg)表服务接口
 *
 * @author moonbless
 * @since 2018-09-29 14:37:09
 */
public interface ISurgeryPkgService {

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
     * lst
     * @param surgeryPkgVo 实例对象
     * @return 实例对象
     */
    List<SurgeryPkgVo> listVo(SurgeryPkgVo surgeryPkgVo);
    /**
     * 分页查询
     * @param queryInfo 实例对象
     * @return 实例对象
     */
    QueryResult<SurgeryPkgVo> listPage(QueryInfo<SurgeryPkgVo> queryInfo);

    /**
     * 新增数据
     *
     * @param surgeryPkgVo 实例对象
     * @return 实例对象
     */
    SurgeryPkgVo insert(SurgeryPkgVo surgeryPkgVo);
    /**
     * 新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    Integer insertByBatch(List<SurgeryPkgVo> lst);

    /**
     * 修改数据
     *
     * @param surgeryPkgVo) 实例对象
     * @return 实例对象
     */
    SurgeryPkgVo update(SurgeryPkgVo  surgeryPkgVo);
    int updateStatusByBatch(List<SurgeryPkg> lst) throws Exception;
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);
   // List<SurgeryPkgVo> SurPack(List<SurgeryPkgVo> surInfo) throws Exception;
    List<SurPackResVo> updateSurPack(List<SurgeryPkgVo> surInfo) throws Exception;
    ApplySurPkgVo creatOrGetSurPkgByApplyId(String billId, String stockId) throws Exception;
    SurBill4Pack getSurBill4Pack(String billId, String stockId) throws Exception;
    List<SurgeryPkgVo> list4Use(SurgeryPkgVo query) throws Exception;
    void setSurStatusBySickerUse(List<SickerUserListVo> lst) throws Exception;
    void setSurStatus(List<BigBatch4FillVo> lst,String desc) throws Exception ;
}