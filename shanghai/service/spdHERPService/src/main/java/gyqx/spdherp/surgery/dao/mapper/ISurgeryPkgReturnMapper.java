package gyqx.spdherp.surgery.dao.mapper;

import gyqx.spdherp.surgery.vo.SurgeryPkgBatchVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgListVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgVo;

import java.util.List;

public interface ISurgeryPkgReturnMapper {
	List<SurgeryPkgVo> listPkgMain(SurgeryPkgVo queryInfo);
	List<SurgeryPkgListVo> listPkgDetail(SurgeryPkgListVo queryInfo);
	List<SurgeryPkgBatchVo> listPkgBatch(SurgeryPkgBatchVo queryInfo);

}

