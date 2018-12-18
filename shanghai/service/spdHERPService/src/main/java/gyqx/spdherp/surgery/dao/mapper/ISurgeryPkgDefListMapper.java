package gyqx.spdherp.surgery.dao.mapper;

import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdherp.po.SurgeryPkgDefList;
import gyqx.spdherp.surgery.vo.SurgeryPkgDefListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISurgeryPkgDefListMapper {
	int update(SurgeryPkgDefList bean);
	int insert(SurgeryPkgDefList bean);

	int insertByBatch(List<SurgeryPkgDefListVo> list);

	int delete(@Param("id") String id);

	int deleteByBatch(List<SurgeryPkgDefListVo> list);

	List<SurgeryPkgDefListVo> list(SurgeryPkgDefListVo queryInfo) ;
	List<SurgeryPkgDefListVo> list4QG(SurgeryPkgDefListVo queryInfo);

	List<SurgeryPkgDefListVo> listNotImportGoods(SurgeryPkgDefListVo surgeryPkgDefListVo);

	List<HosGoodsInfoVo> getHosGoodsInfoById(@Param("goodsId") String goodsId);
}

