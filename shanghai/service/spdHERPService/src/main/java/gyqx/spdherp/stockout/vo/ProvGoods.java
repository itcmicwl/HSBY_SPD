package gyqx.spdherp.stockout.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author liangwu
 * @Date 18-11-29 下午5:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvGoods {
    private String goodsId;
    private String provId;
    private String provName;
    private String provErpCode;
    private String provGoodsId;
    private String provGoodsErp;
}