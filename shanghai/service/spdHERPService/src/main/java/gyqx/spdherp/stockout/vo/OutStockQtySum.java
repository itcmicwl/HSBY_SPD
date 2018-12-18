package gyqx.spdherp.stockout.vo;

import lombok.Data;

/**
 * @Author liangwu
 * @Date 6/6/18 10:56 AM
 */
@Data
public class OutStockQtySum {
    private String id;
    private Integer version;
    private Integer outQty;
    private Integer batchInQty;
    private Integer uniqueInQty;
}
