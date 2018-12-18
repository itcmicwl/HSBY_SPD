package gyqx.spdherp.stockout.vo;

import lombok.Data;

@Data
public class DeptBuySubVo {
    private String bsId;
    private String goodsId;
    private String sendType;
    private String subState;
    private String goodsName;
    private String goodsGg;
    private String unit;
    private Double price;
    private Double pkg;
    private String mfrsId;
    private String mfrsName;
    private String made;
    private Double packetCode;
    private String isPacket;
    private String isUnique;
    private String mfrsCode;
    private Double reqQty;
    private Double avlQty;
    private Double lockQty;
    private Double undoneQty;
    private String provId;
    private String provCode;
    private String provName;
    private Double sendQty;
}
