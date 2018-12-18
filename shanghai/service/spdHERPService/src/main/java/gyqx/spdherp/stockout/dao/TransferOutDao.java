package gyqx.spdherp.stockout.dao;

import gyqx.spdherp.stockout.dao.mapper.TransferOutMapper;
import gyqx.spdherp.stockout.vo.IdNameVo;
import gyqx.spdherp.stockout.vo.StockpileHzVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-9-8 下午3:32
 */
@Repository
public class TransferOutDao {
    @Resource
    TransferOutMapper mapper;

    public List<IdNameVo> listOrgsByHosId(String hosId, String deptId) {
        return mapper.listOrgsByHosId(hosId, deptId);
    }
}
