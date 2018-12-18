package gyqx.spdherp.basedatamaint.dao.mapper;

import gyqx.spdherp.basedatamaint.vo.PurContractVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PurContractMapper {
    List<PurContractVo> listPurContract(@Param("hosId") String hosId,
                                        @Param("searchText") String searchText,
                                        @Param("signDateBegin") Date signDateBegin,
                                        @Param("signDateEnd") Date signDateEnd);

    List<PurContractVo> listProv(@Param("provName") String provName);
}