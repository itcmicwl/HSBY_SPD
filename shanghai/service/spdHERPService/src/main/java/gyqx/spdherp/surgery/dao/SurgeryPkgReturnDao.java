package gyqx.spdherp.surgery.dao;

import common.db.SimpleDao;
import gyqx.spdherp.surgery.dao.mapper.ISurgeryPkgReturnMapper;
import gyqx.spdherp.surgery.vo.SurgeryPkgBatchVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgListVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Repository
public class SurgeryPkgReturnDao {
    @Resource
    private SimpleDao dao;
    @Resource
    private ISurgeryPkgReturnMapper surReturnMapper;
    public List<SurgeryPkgVo> listPkgMain(SurgeryPkgVo query) {
        return surReturnMapper.listPkgMain(query);
    }
    public List<SurgeryPkgListVo> listPkgDetail(SurgeryPkgListVo query) {
        return surReturnMapper.listPkgDetail(query);
    }
    public List<SurgeryPkgBatchVo> listPkgBatch(SurgeryPkgBatchVo query) {
        return surReturnMapper.listPkgBatch(query);
    }

}
