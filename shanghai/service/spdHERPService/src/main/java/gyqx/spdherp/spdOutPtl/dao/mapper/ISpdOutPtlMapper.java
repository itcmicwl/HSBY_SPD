package gyqx.spdherp.spdOutPtl.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.SpdOutPtl;
import gyqx.spdherp.spdOutPtl.vo.DeptBuyForSpdPtl;
import gyqx.spdherp.spdOutPtl.vo.OutStockForSpdPtl;
import org.springframework.data.repository.query.Param;

public interface ISpdOutPtlMapper {
	int update(SpdOutPtl bean);
	int insert(SpdOutPtl bean);
	List<SpdOutPtl> list(SpdOutPtl queryInfo) ;
	int insertSpdOutPtlLists(List<SpdOutPtl> spdOutPtlLists);
	List<OutStockForSpdPtl> getOutListAddErpCode(@Param("pid") String pid);
	List<DeptBuyForSpdPtl> getPurListInfo(@Param("billId") String billId);
	List<SpdOutPtl> getSpdOutPtl(@Param("hosId") String hosId);

}

