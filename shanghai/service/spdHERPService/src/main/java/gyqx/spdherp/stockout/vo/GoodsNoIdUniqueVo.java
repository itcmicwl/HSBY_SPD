package gyqx.spdherp.stockout.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther Liangwu
 * @Date 17-9-1 下午4:32
 */
@Data
public class GoodsNoIdUniqueVo {
    private String id;

    private String shelfCode;

    private String goodsId;

    private String batchNo;

    private String batchId;

    private String uniqueCode;

    private BigDecimal qty;

    private BigDecimal price;

    private Date expiredDate;

    private String certificateCode;

    private String saleBillId;

    private String buyDeptName;
}
