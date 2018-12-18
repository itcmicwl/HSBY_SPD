package gyqx.spdherp.stockout.vo;

import gyqx.spdherp.po.DeptBuyMain;
import gyqx.spdherp.po.DeptBuySub;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author liangwu
 * @Date 18-10-9 上午9:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EntireDeptBuyVo extends DeptBuyMain {
    List<DeptBuySub> subList;
}
