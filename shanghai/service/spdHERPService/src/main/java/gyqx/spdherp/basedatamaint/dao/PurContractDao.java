package gyqx.spdherp.basedatamaint.dao;

import gyqx.spdherp.basedatamaint.dao.mapper.PurContractMapper;
import gyqx.spdherp.basedatamaint.vo.PurContractVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-8-16 下午6:11
 */
@Repository
public class PurContractDao {
    @Resource
    private PurContractMapper mapper;
    public List<PurContractVo> listPurContract(String hosId, String searchText, Date signDateBegin, Date signDateEnd) {
        return mapper.listPurContract(hosId, searchText, signDateBegin, signDateEnd);
    }

    // TODO: 只显示与医院相关联的供应商
    public List<PurContractVo> listProv(String provName) {
        return mapper.listProv(provName);
    }
}
