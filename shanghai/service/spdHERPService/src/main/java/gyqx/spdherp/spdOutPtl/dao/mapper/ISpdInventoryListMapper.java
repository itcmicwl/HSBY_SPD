package gyqx.spdherp.spdOutPtl.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.SpdInventoryList;
import org.apache.ibatis.annotations.Param;

public interface ISpdInventoryListMapper {
	int update(SpdInventoryList bean);
	int insert(SpdInventoryList bean);
	List<SpdInventoryList> list(SpdInventoryList queryInfo) ;
	int updateBatch(@Param("pid") String pid);
	int countState(@Param("pid") String pid);
	List<SpdInventoryList> getSpdInventoryList(@Param("hosId") String hosId);
}

