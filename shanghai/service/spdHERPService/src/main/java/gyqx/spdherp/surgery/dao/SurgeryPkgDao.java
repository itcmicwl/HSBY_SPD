package gyqx.spdherp.surgery.dao;

import gyqx.spdherp.po.SurgeryPkg;
import gyqx.spdherp.po.SurgeryPkgList;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;
import gyqx.spdherp.surgery.dao.mapper.ISurgeryPkgMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author moonbless
 * @since 2018-09-29 14:37:01
 */
@Repository
public class SurgeryPkgDao {
    @Resource
    private ISurgeryPkgMapper surgeryPkgMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public SurgeryPkgVo getById(String id) {
        return this.surgeryPkgMapper.getById(id);
    }
    
    /**
     * 通过对象实例查询列表
     *
     * @param query
     * @return 实例对象
     */
    public List<SurgeryPkgVo> list(SurgeryPkgVo query) {
        return this.surgeryPkgMapper.list(query);
    }
    /**
     * 通过对象实例查询列表(包含子表信息)
     *
     * @param query
     * @return 实例对象
     */
    public List<SurgeryPkgVo> listVo(SurgeryPkgVo query) {
        return this.surgeryPkgMapper.listVo(query);
    }
    /**
     * 通过对象实例查询列表(包含子表信息)
     *
     * @param query
     * @return 实例对象
     */
    public List<SurgeryPkgVo> list4Use(SurgeryPkgVo query) throws Exception{
        if(StringUtils.hasText(query.getHosId()) && StringUtils.hasText(query.getDeptId()) && StringUtils.hasText(query.getSickerName())){
            return this.surgeryPkgMapper.list4Use(query);
        }else {
            throw new Exception("查询关键字段为空");
        }
    }
    /**
     * 新增数据
     *
     * @param surgeryPkgVo 实例对象
     * @return 实例对象
     */
    public SurgeryPkgVo insert(SurgeryPkgVo surgeryPkgVo) {
        this.surgeryPkgMapper.insert(surgeryPkgVo);
        return surgeryPkgVo;
    }
    
    /**
     * 批量新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    public Integer insertByBatch(List<SurgeryPkgVo> lst) {
        return this.surgeryPkgMapper.insertByBatch(lst);
    }
    /**
     * 修改数据
     *
     * @param surgeryPkgVo 实例对象
     * @return 实例对象
     */
    public SurgeryPkgVo update(SurgeryPkgVo surgeryPkgVo) {
        this.surgeryPkgMapper.update(surgeryPkgVo);
        return this.getById(surgeryPkgVo.getId());
    }
    /**
     * 批量修改数据
     *
     * @param lst 实例对象列表
     * @return 实例对象
     */
    public int updateByBatch(List<SurgeryPkgVo> lst) throws Exception{
       return this.surgeryPkgMapper.updateByBatch(lst);
    }
    public int updateStatusByBatch(List<SurgeryPkg> lst)throws Exception{
        return this.surgeryPkgMapper.updateStatusByBatch(lst);
    }
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(String id) {
        return this.surgeryPkgMapper.deleteById(id) > 0;
    }
}