package gyqx.spdherp.surgeryPkgDept.dao;

import gyqx.spdherp.surgeryPkgDept.dao.mapper.ISurgeryDeptMapper;
import gyqx.spdherp.surgeryPkgDept.vo.SurgeryPkgListVo;
import gyqx.spdherp.surgeryPkgDept.vo.SurgeryPkgVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Repository
public class SurgeryDeptDao {
    @Resource
    private ISurgeryDeptMapper mapper;
    public SurgeryPkgVo getSurgeryInfoByCode(SurgeryPkgVo qBean){
        return mapper.getSurgeryInfoByCode(qBean);
    }
    public SurgeryPkgVo getSurgeryBatch(SurgeryPkgVo qBean){
        return mapper.getSurgeryBatch(qBean);
    }
    public List<SurgeryPkgListVo> getSurgeryUnique(SurgeryPkgVo qBean){
        return mapper.getSurgeryUnique(qBean);
    }
    public int updateStatus(SurgeryPkgVo surgeryPkgVo){
        return mapper.updateStatus(surgeryPkgVo);
    }
    public List<SurgeryPkgVo> getSurgeryBatchList(SurgeryPkgVo qBean){
        return mapper.getSurgeryBatchList(qBean);
    }
}
