package gyqx.spdherp.applyMgr.vo;

import java.math.BigDecimal;
import gyqx.spdherp.po.ApplySurgeryPkg;
import gyqx.spdherp.po.SurgeryPkgDefList;
import gyqx.spdherp.surgery.vo.SurGoodsInfo;
import gyqx.spdherp.surgery.vo.SurgeryPkgDefListVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 手术包请购单表(ApplySurgeryPkg)实体类
 *
 * @author moonbless
 * @since 2018-10-08 11:11:05
 */
@Data
public class ApplySurgeryPkgVo extends ApplySurgeryPkg implements Serializable {
    private static final long serialVersionUID = 706434779693057019L;
    private String surName;
    private Integer packageNum;
    private List<SurGoodsInfo> lstGoodsInfo;
    private String remark;
    private String stockId;
    private String surCode;
}