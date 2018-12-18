package gyqx.spdherp.surgeryPkgDept.dao.mapper;


import gyqx.spdherp.surgeryPkgDept.vo.SurgeryPkgListVo;
import gyqx.spdherp.surgeryPkgDept.vo.SurgeryPkgVo;
import org.apache.ibatis.annotations.Update;

import java.util.List;
public interface ISurgeryDeptMapper {
    SurgeryPkgVo getSurgeryInfoByCode(SurgeryPkgVo qBean);
    SurgeryPkgVo getSurgeryBatch(SurgeryPkgVo qBean);
    List<SurgeryPkgListVo> getSurgeryUnique(SurgeryPkgVo qBean);
    @Update("UPDATE surgery_pkg set sicker_name = #{sickerName},`status`= 40,last_update_datetime=NOW(),version = version+1 where sur_code=#{surCode} and hos_id=#{hosId} and dept_id=#{deptId}")
    int updateStatus(SurgeryPkgVo surgeryPkgVo);
    List<SurgeryPkgVo> getSurgeryBatchList(SurgeryPkgVo qBean);
}
