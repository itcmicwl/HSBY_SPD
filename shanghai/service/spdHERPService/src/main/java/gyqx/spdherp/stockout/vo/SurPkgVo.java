package gyqx.spdherp.stockout.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author liangwu
 * @Date 18-10-8 下午1:47
 */
@Data
public class SurPkgVo {
    private String id;
    private String deptId;
    private String deptName;
    @JsonIgnore
    private String surId;
    private String surCode;
    private String surName;
    private String applyBillId;
    @JsonIgnore
    private String curStockId;
    private int status;
    private Date packageDate;
    private int version;
    private List<SurPkgListVo> surPkgSubList;
}
