package gyqx.spdhdi.myInfos.dao.mapper;

import java.util.List;

import gyqx.spdhdi.myInfos.vo.ProvHosInfoCodeVo;
import gyqx.spdhdi.po.ProvHosInfoCode;
 
public interface IProvHosInfoCodeMapper {
	int update(ProvHosInfoCode bean);
	List<ProvHosInfoCode> list(ProvHosInfoCode queryInfo) ;
	List<ProvHosInfoCodeVo> list_PH_code_vo(ProvHosInfoCodeVo queryInfo) ;
}

