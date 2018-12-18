package gyqx.spdherp.applyMgr.dao;

import gyqx.spdherp.applyMgr.vo.ApplySurgeryPkgVo;
import gyqx.spdherp.applyMgr.dao.mapper.IApplySurgeryPkgMapper;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author moonbless
 * @since 2018-10-09 09:07:14
 */
@Repository
public class ApplySurgeryPkgDao {
    @Resource
    private IApplySurgeryPkgMapper applySurgeryPkgMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public ApplySurgeryPkgVo getById(String id) {
        return this.applySurgeryPkgMapper.getById(id);
    }
    
    /**
     * 通过对象实例查询列表
     *
     * @param query
     * @return 实例对象
     */
    public List<ApplySurgeryPkgVo> list(ApplySurgeryPkgVo query) {
        return this.applySurgeryPkgMapper.list(query);
    }
    
    /**
     * 新增数据
     *
     * @param applySurgeryPkgVo 实例对象
     * @return 实例对象
     */
    public ApplySurgeryPkgVo insert(ApplySurgeryPkgVo applySurgeryPkgVo) {
        this.applySurgeryPkgMapper.insert(applySurgeryPkgVo);
        return applySurgeryPkgVo;
    }
    
    /**
     * 批量新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    public Integer insertByBatch(List<ApplySurgeryPkgVo> lst) {
        return this.applySurgeryPkgMapper.insertByBatch(lst);
    }
    /**
     * 修改数据
     *
     * @param applySurgeryPkgVo 实例对象
     * @return 实例对象
     */
    public ApplySurgeryPkgVo update(ApplySurgeryPkgVo applySurgeryPkgVo) {
        this.applySurgeryPkgMapper.update(applySurgeryPkgVo);
        return this.getById(applySurgeryPkgVo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(String id) {
        return this.applySurgeryPkgMapper.deleteById(id) > 0;
    }

    public List<ApplySurgeryPkgVo> getSurInfoByApplyBillId(String billId){
        return this.applySurgeryPkgMapper.getSurInfoByApplyBillId(billId);
    }

    public void setSurQtyByPack(List<ApplySurgeryPkgVo> lst) {

        this.applySurgeryPkgMapper.setSurQtyByPack(lst);
    }
}