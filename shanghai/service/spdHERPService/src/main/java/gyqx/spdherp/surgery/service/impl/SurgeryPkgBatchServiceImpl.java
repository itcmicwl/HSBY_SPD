package gyqx.spdherp.surgery.service.impl;

import gyqx.spdherp.surgery.vo.SurgeryPkgBatchVo;
import gyqx.spdherp.surgery.dao.SurgeryPkgBatchDao;
import gyqx.spdherp.surgery.service.ISurgeryPkgBatchService;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.utils.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 手术包订单批次表 (SurgeryPkgBatch)表服务实现类
 *
 * @author moonbless
 * @since 2018-09-29 16:29:11
 */
@Service
public class SurgeryPkgBatchServiceImpl implements ISurgeryPkgBatchService {
    @Resource
    private SurgeryPkgBatchDao surgeryPkgBatchDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SurgeryPkgBatchVo getById(String id) {
        return this.surgeryPkgBatchDao.getById(id);
    }
    
    /**
     * 查询
     *
     * @return surgeryPkgBatchVo 对象实例
     */
    @Override
    public List<SurgeryPkgBatchVo> list(SurgeryPkgBatchVo surgeryPkgBatchVo) {
        return this.surgeryPkgBatchDao.list(surgeryPkgBatchVo);
    }
    
    /**
     * 分页查询
     *
     * @return query对象
     */
    @Override
    public QueryResult<SurgeryPkgBatchVo> listPage(QueryInfo<SurgeryPkgBatchVo> query) {
        PageUtils.startPage(query);
        return PageUtils.endPage(this.surgeryPkgBatchDao.list(query.getQueryObject()));
    }

    /**
     * 新增数据
     *
     * @param surgeryPkgBatchVo 实例对象
     * @return 实例对象
     */
    @Override
    public SurgeryPkgBatchVo insert(SurgeryPkgBatchVo surgeryPkgBatchVo) {
        this.surgeryPkgBatchDao.insert(surgeryPkgBatchVo);
        return surgeryPkgBatchVo;
    }
    /**
     * 批量新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insertByBatch(List<SurgeryPkgBatchVo> lst) {
        return this.surgeryPkgBatchDao.insertByBatch(lst);
    }
    /**
     * 修改数据
     *
     * @param surgeryPkgBatchVo 实例对象
     * @return 实例对象
     */
    @Override
    public SurgeryPkgBatchVo update(SurgeryPkgBatchVo surgeryPkgBatchVo) {
        this.surgeryPkgBatchDao.update(surgeryPkgBatchVo);
        return this.getById(surgeryPkgBatchVo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.surgeryPkgBatchDao.deleteById(id);
    }
}