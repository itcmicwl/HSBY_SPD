package gyqx.spdherp.surgery.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdherp.po.SurgeryPkgList;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 手术包订单明细表(SurgeryPkgList)实体类
 *
 * @author moonbless
 * @since 2018-09-29 14:41:33
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurgeryPkgListVo extends SurgeryPkgList implements Serializable {
    private static final long serialVersionUID = -96608020045860451L;
    private List<SurgeryPkgBatchVo> lstGoodsBatch;
    // 某一商品消耗的总数量
    private BigDecimal sumUseQty;
    private HosGoodsInfoVo hosGoods;
    private String goodsName;
    private String goodsGg;
    private String made;
    private String mfrsName;
    private String brand;
    private BigDecimal takeQty;
    private BigDecimal storeQty;
    private String outBillId;
    private Integer outBillRow;
    private Integer purMode;
    private String provId;
}