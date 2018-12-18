package gyqx.spdherp.stockout.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gyqx.spdherp.po.HosPackageBatchDetail;
import gyqx.spdherp.po.HosPackageInfo;
import lombok.Data;

import java.util.List;

/**
 * Created by cjzyw on 2018/9/10.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HosPackageInfoVo extends HosPackageInfo {
    private String certificateCode;
    private List<HosPackageBatchDetailVo> hosPackageBatchDetailLst;
}
