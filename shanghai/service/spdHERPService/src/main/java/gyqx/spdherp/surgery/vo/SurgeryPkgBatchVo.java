package gyqx.spdherp.surgery.vo;

import java.math.BigDecimal;
import java.util.Date;
import gyqx.spdherp.po.SurgeryPkgBatch;
import lombok.Data;

import java.io.Serializable;

/**
 * 手术包订单批次表 (SurgeryPkgBatch)实体类
 *
 * @author moonbless
 * @since 2018-09-29 16:29:12
 */
@Data
public class SurgeryPkgBatchVo extends SurgeryPkgBatch implements Serializable {
    private static final long serialVersionUID = -29312724071236624L;
    private String storeId;
    private Integer outBillRow;
    private String outBillId;
    private Integer purMode;
    private String provId;
    private String provName;
    private String provCode;
}