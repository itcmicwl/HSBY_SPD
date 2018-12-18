package gyqx.spdherp.spdOutPtl.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.SpdInventory;
import gyqx.spdherp.po.SpdInventoryList;
import gyqx.spdherp.spdOutPtl.vo.DeptBuyForSpdPtl;
import gyqx.spdherp.spdOutPtl.vo.SpdInventoryVo;
import org.apache.ibatis.annotations.Param;

public interface ISpdInventoryMapper {
	int update(SpdInventory bean);
	int insert(SpdInventory bean);
	List<SpdInventoryVo> list(SpdInventoryVo queryInfo) ;

	String getId(@Param("hosId")String hosId);

	List<DeptBuyForSpdPtl> getIdAndName(@Param("erpCode") String erpCode,
										@Param("hosId") String hosId);

	int insertBatchSpdInvertory(List<SpdInventoryList> spdInventoryLists);

	List<SpdInventoryVo> getPtlInfo(SpdInventoryVo spdInventory);
}

