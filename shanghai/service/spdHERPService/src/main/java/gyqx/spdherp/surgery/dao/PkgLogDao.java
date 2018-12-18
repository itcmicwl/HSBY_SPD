package gyqx.spdherp.surgery.dao;

import gyqx.spdherp.surgery.vo.PkgLogVo;
import gyqx.spdherp.surgery.dao.mapper.IPkgLogMapper;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author moonbless
 * @since 2018-09-29 14:39:05
 */
@Repository
public class PkgLogDao {
    @Resource
    private IPkgLogMapper pkgLogMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public PkgLogVo getById(String id) {
        return this.pkgLogMapper.getById(id);
    }
    
    /**
     * 通过对象实例查询列表
     *
     * @param query
     * @return 实例对象
     */
    public List<PkgLogVo> list(PkgLogVo query) {
        return this.pkgLogMapper.list(query);
    }
    
    /**
     * 新增数据
     *
     * @param pkgLogVo 实例对象
     * @return 实例对象
     */
    public PkgLogVo insert(PkgLogVo pkgLogVo) {
        this.pkgLogMapper.insert(pkgLogVo);
        return pkgLogVo;
    }
    
    /**
     * 批量新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    public Integer insertByBatch(List<PkgLogVo> lst) {
        return this.pkgLogMapper.insertByBatch(lst);
    }
    /**
     * 修改数据
     *
     * @param pkgLogVo 实例对象
     * @return 实例对象
     */
    public PkgLogVo update(PkgLogVo pkgLogVo) {
        this.pkgLogMapper.update(pkgLogVo);
        return this.getById(pkgLogVo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(String id) {
        return this.pkgLogMapper.deleteById(id) > 0;
    }
}