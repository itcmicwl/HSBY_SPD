package gyqx.spdherp.stockout.vo;

import gyqx.spdherp.basedatamaint.vo.BarcodeParseResult;
import gyqx.spdherp.po.HosPackageBatchDetail;
import gyqx.spdherp.po.HosPackageInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * Created by cjzyw on 2018/9/5.
 */
@Setter
@Getter
@ToString
public class PackageOutVo extends HosPackageInfo implements Serializable{
    private int rowId ;
    private String goodsBatchId ;
    private java.math.BigDecimal inPrice ;
    private java.math.BigDecimal batchqty ;
    private String bigBatchCode ;
    private String label;
    private String certificateCode;
}
