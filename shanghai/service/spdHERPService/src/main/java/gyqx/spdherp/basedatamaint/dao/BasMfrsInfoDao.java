package gyqx.spdherp.basedatamaint.dao;

import gyqx.spdherp.basedatamaint.dao.mapper.BasMfrsInfoMapper;
import gyqx.spdherp.basedatamaint.vo.BasMfrsInfoVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-8-14 下午5:02
 */
@Repository
public class BasMfrsInfoDao {
    @Resource
    private BasMfrsInfoMapper mapper;

    public List<BasMfrsInfoVo> listBasMfrsInfo(String mfrsName, String mfrsKind) {
        return mapper.listBasMfrsInfo(mfrsName, mfrsKind);
    }
}
