package gyqx.spdherp.applyMgr.service.impl;

import gyqx.spdherp.applyMgr.vo.ApplySurgeryPkgVo;
import gyqx.spdherp.applyMgr.dao.ApplySurgeryPkgDao;
import gyqx.spdherp.applyMgr.service.IApplySurgeryPkgService;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.utils.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 手术包请购单表(ApplySurgeryPkg)表服务实现类
 *
 * @author moonbless
 * @since 2018-10-09 09:07:14
 */
@Service("applySurgeryPkgService")
public class ApplySurgeryPkgServiceImpl implements IApplySurgeryPkgService {
    @Resource
    private ApplySurgeryPkgDao applySurgeryPkgDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ApplySurgeryPkgVo getById(String id) {
        return this.applySurgeryPkgDao.getById(id);
    }
    
    /**
     * 查询
     *
     * @return applySurgeryPkgVo 对象实例
     */
    @Override
    public List<ApplySurgeryPkgVo> list(ApplySurgeryPkgVo applySurgeryPkgVo) {
        return this.applySurgeryPkgDao.list(applySurgeryPkgVo);
    }
    
    /**
     * 分页查询
     *
     * @return query对象
     */
    @Override
    public QueryResult<ApplySurgeryPkgVo> listPage(QueryInfo<ApplySurgeryPkgVo> query) {
        PageUtils.startPage(query);
        return PageUtils.endPage(this.applySurgeryPkgDao.list(query.getQueryObject()));
    }

    /**
     * 新增数据
     *
     * @param applySurgeryPkgVo 实例对象
     * @return 实例对象
     */
    @Override
    public ApplySurgeryPkgVo insert(ApplySurgeryPkgVo applySurgeryPkgVo) {
        this.applySurgeryPkgDao.insert(applySurgeryPkgVo);
        return applySurgeryPkgVo;
    }
    /**
     * 批量新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insertByBatch(List<ApplySurgeryPkgVo> lst) {
        return this.applySurgeryPkgDao.insertByBatch(lst);
    }
    /**
     * 修改数据
     *
     * @param applySurgeryPkgVo 实例对象
     * @return 实例对象
     */
    @Override
    public ApplySurgeryPkgVo update(ApplySurgeryPkgVo applySurgeryPkgVo) {
        this.applySurgeryPkgDao.update(applySurgeryPkgVo);
        return this.getById(applySurgeryPkgVo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.applySurgeryPkgDao.deleteById(id);
    }

    @Override
    public List<ApplySurgeryPkgVo> getSurInfoByApplyBillId(String billId) {
        return this.applySurgeryPkgDao.getSurInfoByApplyBillId(billId);
    }

    @Override
    public void setSurQtyByPack(List<ApplySurgeryPkgVo> lst) {
        this.applySurgeryPkgDao.setSurQtyByPack(lst);
    }
}