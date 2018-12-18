package gyqx.spdherp.surgery.dao;

import gyqx.spdherp.po.SurgeryPkgList;
import gyqx.spdherp.surgery.vo.SurgeryPkgListVo;
import gyqx.spdherp.surgery.dao.mapper.ISurgeryPkgListMapper;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author moonbless
 * @since 2018-09-29 14:41:33
 */
@Repository
public class SurgeryPkgListDao {
    @Resource
    private ISurgeryPkgListMapper surgeryPkgListMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public SurgeryPkgListVo getById(String id) {
        return this.surgeryPkgListMapper.getById(id);
    }
    
    /**
     * 通过对象实例查询列表
     *
     * @param query
     * @return 实例对象
     */
    public List<SurgeryPkgListVo> list(SurgeryPkgListVo query) {
        return this.surgeryPkgListMapper.list(query);
    }
    
    /**
     * 新增数据
     *
     * @param surgeryPkgListVo 实例对象
     * @return 实例对象
     */
    public SurgeryPkgListVo insert(SurgeryPkgListVo surgeryPkgListVo) {
        this.surgeryPkgListMapper.insert(surgeryPkgListVo);
        return surgeryPkgListVo;
    }
    
    /**
     * 批量新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    public Integer insertByBatch(List<SurgeryPkgListVo> lst) {
        return this.surgeryPkgListMapper.insertByBatch(lst);
    }
    /**
     * 更新手术包明细表状态
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    public int updateStatus(List<SurgeryPkgList> lst) {
        return this.surgeryPkgListMapper.updateStatus(lst);
    }
    /**
     * 修改数据
     *
     * @param surgeryPkgListVo 实例对象
     * @return 实例对象
     */
    public SurgeryPkgListVo update(SurgeryPkgListVo surgeryPkgListVo) {
        this.surgeryPkgListMapper.update(surgeryPkgListVo);
        return this.getById(surgeryPkgListVo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(String id) {
        return this.surgeryPkgListMapper.deleteById(id) > 0;
    }
}