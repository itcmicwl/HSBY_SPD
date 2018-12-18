package gyqx.spdherp.surgery.dao.mapper;

import gyqx.spdherp.po.SurgeryPkgKind;
import gyqx.spdherp.surgery.vo.SurgeryPkgKindVo;

import java.util.List;
import java.util.Map;

public interface ISurgeryPkgKindMapper {
	int update(SurgeryPkgKind bean);
	int updateAllSonCode(Map map);
	int insert(SurgeryPkgKind bean);
	List<SurgeryPkgKindVo> list(SurgeryPkgKindVo queryInfo) ;
}

