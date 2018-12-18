package gyqx.spdherp.stockout.dao.mapper;

import java.util.List;

import gyqx.spdherp.stockout.vo.SickerUserVo;
import gyqx.spdherp.stockout.vo.Sickuse4print;
import gyqx.spdherp.stockout.vo.SickuseGoods4print;

import org.apache.ibatis.annotations.Param;

public interface ISickerUseMapper {
	SickerUserVo get(@Param("id")String id);
	int update(SickerUserVo bean);
	int insert(SickerUserVo bean);
	List<SickerUserVo> list(SickerUserVo queryInfo) ;
	Sickuse4print getSickuser4Print(String billId);
	List<SickuseGoods4print> getSickuseGoods4print(String billId);
}

