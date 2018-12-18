package gyqx.spdherp.surgery.vo;

import gyqx.spdherp.po.SurgeryPkgDefList;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SurGoodsInfo extends SurgeryPkgDefList {
    private String goodsName;
    private String goodsGg;
    private String made;
    private BigDecimal takeQty;
    private BigDecimal storeQty;
    private String mfrsName;
    private String brand;
    private String unit;
    private Integer uniqueKind;
}
