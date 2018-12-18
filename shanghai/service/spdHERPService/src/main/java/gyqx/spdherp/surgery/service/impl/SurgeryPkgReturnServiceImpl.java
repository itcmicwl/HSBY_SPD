package gyqx.spdherp.surgery.service.impl;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import gyqx.spdherp.surgery.dao.SurgeryPkgBatchDao;
import gyqx.spdherp.surgery.dao.SurgeryPkgDao;
import gyqx.spdherp.surgery.dao.SurgeryPkgListDao;
import gyqx.spdherp.surgery.dao.SurgeryPkgReturnDao;
import gyqx.spdherp.surgery.service.ISurgeryPkgReturnService;
import gyqx.spdherp.surgery.vo.SurgeryPkgBatchVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgListVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 手术包订单主表(SurgeryPkg)表服务实现类
 *
 * @author moonbless
 * @since 2018-09-29 14:37:11
 */
@Service("surgeryPkgReturnService")
public class SurgeryPkgReturnServiceImpl implements ISurgeryPkgReturnService {
    @Resource
    private SurgeryPkgReturnDao surgeryPkgReturnDao;
    @Resource
    private SurgeryPkgDao surgeryPkgDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SurgeryPkgVo getById(String id) {
        return this.surgeryPkgDao.getById(id);
    }
    
    /**
     * 查询
     *
     * @return surgeryPkgVo 对象实例
     */
    @Override
    public List<SurgeryPkgVo> list(SurgeryPkgVo surgeryPkgVo) {
        return surgeryPkgReturnDao.listPkgMain(surgeryPkgVo);
    }
    
    /**
     * 分页查询
     *
     * @return query对象
     */
    @Override
    public QueryResult<SurgeryPkgVo> listPage(QueryInfo<SurgeryPkgVo> query) {
        PageUtils.startPage(query);
        List<SurgeryPkgVo> pkgLst = this.surgeryPkgReturnDao.listPkgMain(query.getQueryObject());
        pkgLst.forEach(p->{
            SurgeryPkgListVo detailQb= new SurgeryPkgListVo();
            detailQb.setSurCode(p.getSurCode());
            List<SurgeryPkgListVo> pkgDetailLst= surgeryPkgReturnDao.listPkgDetail(detailQb);
            pkgDetailLst.forEach(d->{
                SurgeryPkgBatchVo batchQb = new SurgeryPkgBatchVo();
                batchQb.setSurCode(d.getSurCode());
                batchQb.setGoodsId(d.getGoodsId());
                List<SurgeryPkgBatchVo> batchVoList = surgeryPkgReturnDao.listPkgBatch(batchQb);
                d.setLstGoodsBatch(batchVoList);
            });
            p.setSurgeryPkgListVos(pkgDetailLst);
        });

        return PageUtils.endPage(pkgLst);
    }


}