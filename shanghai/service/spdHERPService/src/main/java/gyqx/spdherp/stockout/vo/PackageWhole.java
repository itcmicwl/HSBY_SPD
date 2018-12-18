package gyqx.spdherp.stockout.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by cjzyw on 2018/9/10.
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageWhole {
    private String hosId;
    private String packageId;
    private String provId;
    private String outDeptId;
    private String outStocId;
    private String outStockKind;
    private String outStockType;
    private List<HosPackageInfoVo> packageInfos;
}
