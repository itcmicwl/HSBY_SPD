package gyqx.spdherp.stockout.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author liangwu
 * @Date 18-10-8 下午1:51
 */
@Data
public class SurPkgListVo {
    private String id;
    private String goodsId;
    private String goodsName;
    private String goodsSpec;
    @JsonIgnore
    private String provId;
    @JsonIgnore
    private String provCode;
    @JsonIgnore
    private String provName;
    @JsonIgnore
    private String mfrsId;
    @JsonIgnore
    private String mfrsName;
    @JsonIgnore
    private String made;
    @JsonIgnore
    private BigDecimal packetCode;
    @JsonIgnore
    private String certCode;
    private int uniqueKind;
    private int status;
    private BigDecimal qty;
    private String unit;
    private int version;
    private List<SurPkgBatchVo> surPkgBatchList;
}
