package gyqx.spdherp.basedatamaint.dao.mapper;

import gyqx.spdherp.basedatamaint.vo.BasMfrsInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasMfrsInfoMapper {
    List<BasMfrsInfoVo> listBasMfrsInfo(@Param("mfrsName") String mfrsName,
                                        @Param("mfrsKind") String mfrsKind);
}