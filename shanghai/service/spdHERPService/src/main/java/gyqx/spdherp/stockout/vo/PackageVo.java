package gyqx.spdherp.stockout.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by cjzyw on 2018/9/7.
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageVo {

    private String batchCode;

    private java.math.BigDecimal batchqty;

    private String bigBatchCode;

    private String billId;

    private String certificateCode;

    private java.util.Date expdtEndDate;

    private String goodsBatchId;

    private String goodsGg;

    private String goodsId;

    private String goodsName;

    private String hosId;

    private String hosName;

    private String id;

    private  java.math.BigDecimal inPrice;

    private String label;

    private String lastUpdateDatetime;

    private String made;

    private String mfrsId;

    private String mfrsName;

    private String packageId;

    private java.math.BigDecimal packetCode;

    private String packetQty;

    private String provId;

    private String provName;

    private String qty;

    private String rowId;

    private String sterilizationCode;

    private java.util.Date sterilizationDate;

    private java.util.Date sterilizationEndDate;

    private String stockTableId;

    private String unit;

    private String version;

}
