package gyqx.spdherp.surgery.service.impl;

import gyqx.spdherp.surgery.vo.SurgeryPkgListVo;
import gyqx.spdherp.surgery.dao.SurgeryPkgListDao;
import gyqx.spdherp.surgery.service.ISurgeryPkgListService;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.utils.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 手术包订单明细表(SurgeryPkgList)表服务实现类
 *
 * @author moonbless
 * @since 2018-09-29 14:41:33
 */
@Service("surgeryPkgListService")
public class SurgeryPkgListServiceImpl implements ISurgeryPkgListService {
    @Resource
    private SurgeryPkgListDao surgeryPkgListDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SurgeryPkgListVo getById(String id) {
        return this.surgeryPkgListDao.getById(id);
    }
    
    /**
     * 查询
     *
     * @return surgeryPkgListVo 对象实例
     */
    @Override
    public List<SurgeryPkgListVo> list(SurgeryPkgListVo surgeryPkgListVo) {
        return this.surgeryPkgListDao.list(surgeryPkgListVo);
    }
    
    /**
     * 分页查询
     *
     * @return query对象
     */
    @Override
    public QueryResult<SurgeryPkgListVo> listPage(QueryInfo<SurgeryPkgListVo> query) {
        PageUtils.startPage(query);
        return PageUtils.endPage(this.surgeryPkgListDao.list(query.getQueryObject()));
    }

    /**
     * 新增数据
     *
     * @param surgeryPkgListVo 实例对象
     * @return 实例对象
     */
    @Override
    public SurgeryPkgListVo insert(SurgeryPkgListVo surgeryPkgListVo) {
        this.surgeryPkgListDao.insert(surgeryPkgListVo);
        return surgeryPkgListVo;
    }
    /**
     * 批量新增数据
     *
     * @param lst 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insertByBatch(List<SurgeryPkgListVo> lst) {
        return this.surgeryPkgListDao.insertByBatch(lst);
    }
    /**
     * 修改数据
     *
     * @param surgeryPkgListVo 实例对象
     * @return 实例对象
     */
    @Override
    public SurgeryPkgListVo update(SurgeryPkgListVo surgeryPkgListVo) {
        this.surgeryPkgListDao.update(surgeryPkgListVo);
        return this.getById(surgeryPkgListVo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.surgeryPkgListDao.deleteById(id);
    }
}