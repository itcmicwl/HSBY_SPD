package gyqx.spdherp.stockout.dao;

import gyqx.spdherp.stockout.dao.mapper.OutBillListMapper;
import gyqx.spdherp.stockout.vo.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Repository
public class OutBillListDao {
    @Resource
    private OutBillListMapper mapper;

    public List<EnameValVo> getOutStatus() {
        return mapper.getOutStatus();
    }

    public List<OutBillListVo> getOutBillList(OutBillListVo outBillListVo){
        return mapper.getOutBillList(outBillListVo);
    }
}
