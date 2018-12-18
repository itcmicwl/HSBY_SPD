package gyqx.spdherp.surgery.service.impl;

import gyqx.spdherp.surgery.vo.PkgLogVo;
import gyqx.spdherp.surgery.dao.PkgLogDao;
import gyqx.spdherp.surgery.service.IPkgLogService;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.utils.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 包日志表(PkgLog)表服务实现类
 *
 * @author moonbless
 * @since 2018-09-29 14:39:05
 */
@Service("pkgLogService")
public class PkgLogServiceImpl implements IPkgLogService {
    @Resource
    private PkgLogDao pkgLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PkgLogVo getById(String id) {
        return this.pkgLogDao.getById(id);
    }
    
    /**
     * 查询
     *
     * @return pkgLogVo 对象实例
     */
    @Override
    public List<PkgLogVo> list(PkgLogVo pkgLogVo) {
        return this.pkgLogDao.list(pkgLogVo);
    }
    
    /**
     * 分页查询
     *
     * @return query对象
     */
    @Override
    public QueryResult<PkgLogVo> listPage(QueryInfo<PkgLogVo> query) {
        PageUtils.startPage(query);
        return PageUtils.endPage(this.pkgLogDao.list(query.getQueryObject()));
    }

    /**
     * 新增数据
     *
     * @param pkgLogVo 实例对象
     * @return 实例对象
     */
    @Override
    public PkgLogVo insert(PkgLogVo pkgLogVo) {
        this.pkgLogDao.insert(pkgLogVo);
        return pkgLogVo;
    }
    /**
     * 批量新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insertByBatch(List<PkgLogVo> lst) {
        return this.pkgLogDao.insertByBatch(lst);
    }
    /**
     * 修改数据
     *
     * @param pkgLogVo 实例对象
     * @return 实例对象
     */
    @Override
    public PkgLogVo update(PkgLogVo pkgLogVo) {
        this.pkgLogDao.update(pkgLogVo);
        return this.getById(pkgLogVo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.pkgLogDao.deleteById(id);
    }
}