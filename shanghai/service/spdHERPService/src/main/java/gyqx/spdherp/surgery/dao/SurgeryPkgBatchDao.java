package gyqx.spdherp.surgery.dao;

import gyqx.spdherp.po.HosTakingStock;
import gyqx.spdherp.po.SurgeryPkgBatch;
import gyqx.spdherp.stockout.dao.mapper.RequestOutMapper;
import gyqx.spdherp.surgery.vo.SurgeryPkgBatchVo;
import gyqx.spdherp.surgery.dao.mapper.ISurgeryPkgBatchMapper;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author moonbless
 * @since 2018-09-29 16:29:09
 */
@Repository
public class SurgeryPkgBatchDao {
    @Resource
    private ISurgeryPkgBatchMapper surgeryPkgBatchMapper;
    @Resource
    private RequestOutMapper mapper;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public SurgeryPkgBatchVo getById(String id) {
        return this.surgeryPkgBatchMapper.getById(id);
    }
    
    /**
     * 通过对象实例查询列表
     *
     * @param query
     * @return 实例对象
     */
    public List<SurgeryPkgBatchVo> list(SurgeryPkgBatchVo query) {
        return this.surgeryPkgBatchMapper.list(query);
    }
    
    /**
     * 新增数据
     *
     * @param surgeryPkgBatchVo 实例对象
     * @return 实例对象
     */
    public SurgeryPkgBatchVo insert(SurgeryPkgBatchVo surgeryPkgBatchVo) {
        this.surgeryPkgBatchMapper.insert(surgeryPkgBatchVo);
        return surgeryPkgBatchVo;
    }
    
    /**
     * 批量新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    public Integer insertByBatch(List<SurgeryPkgBatchVo> lst) {
        return this.surgeryPkgBatchMapper.insertByBatch(lst);
    }
    /**
     * 修改数据
     *
     * @param surgeryPkgBatchVo 实例对象
     * @return 实例对象
     */
    public SurgeryPkgBatchVo update(SurgeryPkgBatchVo surgeryPkgBatchVo) {
        this.surgeryPkgBatchMapper.update(surgeryPkgBatchVo);
        return this.getById(surgeryPkgBatchVo.getId());
    }
    public int updateStatus(List<SurgeryPkgBatch> lst) {
       return this.surgeryPkgBatchMapper.updateStatus(lst);
    }
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(String id) {
        return this.surgeryPkgBatchMapper.deleteById(id) > 0;
    }
    public Integer insertHosTakingStock(List<HosTakingStock> hosTakingStocks){
        return mapper.insertHosTakingStock(hosTakingStocks);
    }
}