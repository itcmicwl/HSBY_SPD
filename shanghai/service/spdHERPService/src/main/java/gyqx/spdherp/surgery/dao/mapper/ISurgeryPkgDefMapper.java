package gyqx.spdherp.surgery.dao.mapper;

import gyqx.spdherp.po.SurgeryPkgDef;
import gyqx.spdherp.surgery.vo.SurgeryPkgDefVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISurgeryPkgDefMapper {
	int update(SurgeryPkgDef bean);
	int insert(SurgeryPkgDef bean);
	List<SurgeryPkgDefVo> list(SurgeryPkgDefVo queryInfo) ;
	List<SurgeryPkgDefVo> listByApplyBillId(@Param("billId")String billId) ;
}

