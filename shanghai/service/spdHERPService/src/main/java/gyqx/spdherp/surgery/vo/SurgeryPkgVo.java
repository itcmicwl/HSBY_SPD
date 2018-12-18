package gyqx.spdherp.surgery.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gyqx.spdherp.po.SurgeryPkg;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 手术包订单主表(SurgeryPkg)实体类
 *
 * @author moonbless
 * @since 2018-09-29 14:37:12
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurgeryPkgVo extends SurgeryPkg implements Serializable {
    private static final long serialVersionUID = -42434040802583898L;

    private String deptName;
    private String stocName;
    private String selType;
    private String packerName;
    private List<Integer> statusArr;
    private List<SurgeryPkgListVo> surgeryPkgListVos = new ArrayList<>();

}