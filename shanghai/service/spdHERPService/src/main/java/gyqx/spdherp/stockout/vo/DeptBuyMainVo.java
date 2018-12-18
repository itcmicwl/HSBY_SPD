package gyqx.spdherp.stockout.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DeptBuyMainVo {
    private String id;

    private String billId;

    private Integer buyKind;

    private String remark;

    private Integer state;

    private String deptView;

    private String fillter;

    private Date fillDate;

    private String recAddressId;

    private String recLinkman;

    private String recLinkmanPhone;

    private String recAddress;

    private Integer kindsCount;

    private Double sumQty;

    private Double undoneQty;

    private Boolean available;
}
