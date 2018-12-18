package gyqx.spdherp.productsInfos.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.HosCostCode;
import gyqx.spdherp.productsInfos.vo.HosCostCodeVo;
 
public interface IHosCostCodeMapper {
	int update(HosCostCode bean);
	List<HosCostCode> list(HosCostCode queryInfo) ;
	List<HosCostCodeVo> getHosCostCodeGoodsList(HosCostCodeVo queryInfo) ;
}

