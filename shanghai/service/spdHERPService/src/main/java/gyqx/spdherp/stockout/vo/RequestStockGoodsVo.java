package gyqx.spdherp.stockout.vo;

import common.db.meta.Title;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import java.util.Date;

/**
 * @Auther Liangwu
 * @Date 17-8-22 上午9:45
 */
@Getter
@Setter
@ToString
public class RequestStockGoodsVo {
    private String billId;
    private String bsId;
    private String goodsId;
    private String goodsName;
    private String goodsGg;
    private Double pkg;
    private String unit;
    private Double price;
    private String mfrsId;
    private String mfrsName;
    private String made;
    private Double reqQty;
    private Double avlQty;
    private Double lockQty;
    private Double outQty;
    private Double undoneQty;
    private Double packetCode;
    private String isPacket;
    private String isUnique;
    private String mfrsCode;
    private Date requestDate;
    private String recAddressId;
    private String recLinkman;
    private String recLinkmanPhone;
    private String recAddress;
    private Double qty;
    private String provId;
    private String provCode;
    private String provName;
    private String batchNo;
    private String certificateCode;
	private String batchId;
    private String uniqueCode;
    private String stockTableId;
    private Date sterilizationDate;
    private String sterilizationCode;
    private Date sterilizationEndDate;
    private Date expdtEndDate;
    private String shelfCode;
    private String  sourceBillId;
    private String bigBatchCode;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RequestStockGoodsVo that = (RequestStockGoodsVo) o;

        return new EqualsBuilder()
                .append(goodsId, that.goodsId)
                .append(batchNo, that.batchNo)
                .append(sterilizationCode, that.sterilizationCode)
                .append(expdtEndDate, that.expdtEndDate)
                .append(certificateCode, that.certificateCode)
                .append(shelfCode, that.shelfCode)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(goodsId)
                .append(batchNo)
                .append(sterilizationCode)
                .append(expdtEndDate)
                .append(certificateCode)
                .append(shelfCode)
                .toHashCode();
    }
}
