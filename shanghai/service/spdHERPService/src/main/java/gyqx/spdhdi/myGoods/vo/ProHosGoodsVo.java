package gyqx.spdhdi.myGoods.vo;

import java.math.BigDecimal;
import gyqx.spdhdi.po.ProHosGoodsinfo;
import lombok.Data;

import java.io.Serializable;

/**
 * (ProHosGoodsinfo)实体类
 *
 * @author moonbless
 * @since 2018-11-28 10:08:42
 */
@Data
public class ProHosGoodsVo extends ProHosGoodsinfo implements Serializable {
    private static final long serialVersionUID = -78392852241583456L;
    private String query;
    private Integer limit;
}