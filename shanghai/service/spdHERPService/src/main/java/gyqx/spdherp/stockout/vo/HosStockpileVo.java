package gyqx.spdherp.stockout.vo;

import common.db.meta.Title;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * 医院库存信息
 */
public class HosStockpileVo
{
    /**
     * ID
     */
    @Id
    @Size(max=36)
    @Column(name="id")
    @Title("ID")
    private String id ;
    /**
     * 医院ID
     */
    @Size(max=36)
    @Column(name="hos_id")
    @Title("医院ID")
    private String hosId ;
    /**
     * 仓库ID
     */
    @Size(max=36)
    @Column(name="stoc_id")
    @Title("仓库ID")
    private String stocId ;
    /**
     * 货位编码
     */
    @Size(max=36)
    @Column(name="shelf_code")
    @Title("货位编码")
    private String shelfCode ;
    /**
     * 医院产品ID
     * 10--区位，20--货位
     */
    @Size(max=36)
    @Column(name="hos_goods_id")
    @Title("医院产品ID")
    private String hosGoodsId ;
    /**
     * 数量
     */
    @Column(name="qty")
    @Title("数量")
    private java.math.BigDecimal qty ;
    /**
     * 单价
     */
    @Column(name="price")
    @Title("单价")
    private java.math.BigDecimal price ;
    /**
     * 批号
     */
    @Size(max=36)
    @Column(name="batch_no")
    @Title("批号")
    private String batchNo ;
    /**
     * 批次
     */
    @Size(max=36)
    @Column(name="batch_id")
    @Title("批次")
    private String batchId ;
    /**
     * 效期
     */
    @Column(name="expdt_end_date")
    @Title("效期")
    private java.util.Date expdtEndDate ;
    /**
     * 灭菌批号
     */
    @Size(max=36)
    @Column(name="sterilization_code")
    @Title("灭菌批号")
    private String sterilizationCode ;
    /**
     * 灭菌效期
     */
    @Column(name="sterilization_end_date")
    @Title("灭菌效期")
    private java.util.Date sterilizationEndDate ;
    /**
     * 灭菌日期
     */
    @Column(name="sterilization_date")
    @Title("灭菌日期")
    private java.util.Date sterilizationDate ;
    /**
     * 入库时间
     */
    @Column(name="in_time")
    @Title("入库时间")
    private java.util.Date inTime ;
    /**
     * 条码
     */
    @Size(max=128)
    @Column(name="barcode")
    @Title("条码")
    private String barcode ;
    /**
     * 库房类型
     * 10--实物库，20--虚拟库
     */
    @Column(name="stoc_kind")
    @Title("库房类型")
    private int stocKind ;
    /**
     * 大批次
     */
    @Size(max=2000)
    @Column(name="big_batch_code")
    @Title("大批次")
    private String bigBatchCode ;
    /**
     * 唯一码
     */
    @Size(max=128)
    @Column(name="unique_code")
    @Title("唯一码")
    private String uniqueCode ;
    /**
     * 副码
     */
    @Size(max=128)
    @Column(name="sec_code")
    @Title("副码")
    private String secCode ;
    /**
     * HIBC
     */
    @Size(max=128)
    @Column(name="hibc_code")
    @Title("HIBC")
    private String hibcCode ;
    /**
     * 包装数量
     */
    @Column(name="packet_code")
    @Title("包装数量")
    private java.math.BigDecimal packetCode ;
    /**
     * 是否定数包管理
     */
    @Size(max=1)
    @Column(name="is_packet")
    @Title("是否定数包管理")
    private String isPacket ;
    /**
     * 唯一码管理
     */
    @Column(name="unique_kind")
    @Title("唯一码管理")
    private int uniqueKind ;
    /**
     * 数据版本
     */
    @Column(name="version")
    @Title("数据版本")
    private int version ;

    public String getId ()
    {
        return id ;
    }

    public void setId (String id )
    {
        this.id = id;
    }
    public String getHosId ()
    {
        return hosId ;
    }

    public void setHosId (String hosId )
    {
        this.hosId = hosId;
    }
    public String getStocId ()
    {
        return stocId ;
    }

    public void setStocId (String stocId )
    {
        this.stocId = stocId;
    }
    public String getShelfCode ()
    {
        return shelfCode ;
    }

    public void setShelfCode (String shelfCode )
    {
        this.shelfCode = shelfCode;
    }
    public String getHosGoodsId ()
    {
        return hosGoodsId ;
    }

    public void setHosGoodsId (String hosGoodsId )
    {
        this.hosGoodsId = hosGoodsId;
    }
    public java.math.BigDecimal getQty ()
    {
        return qty ;
    }

    public void setQty (java.math.BigDecimal qty )
    {
        this.qty = qty;
    }
    public java.math.BigDecimal getPrice ()
    {
        return price ;
    }

    public void setPrice (java.math.BigDecimal price )
    {
        this.price = price;
    }
    public String getBatchNo ()
    {
        return batchNo ;
    }

    public void setBatchNo (String batchNo )
    {
        this.batchNo = batchNo;
    }
    public String getBatchId ()
    {
        return batchId ;
    }

    public void setBatchId (String batchId )
    {
        this.batchId = batchId;
    }
    public java.util.Date getExpdtEndDate ()
    {
        return expdtEndDate ;
    }

    public void setExpdtEndDate (java.util.Date expdtEndDate )
    {
        this.expdtEndDate = expdtEndDate;
    }
    public String getSterilizationCode ()
    {
        return sterilizationCode ;
    }

    public void setSterilizationCode (String sterilizationCode )
    {
        this.sterilizationCode = sterilizationCode;
    }
    public java.util.Date getSterilizationEndDate ()
    {
        return sterilizationEndDate ;
    }

    public void setSterilizationEndDate (java.util.Date sterilizationEndDate )
    {
        this.sterilizationEndDate = sterilizationEndDate;
    }
    public java.util.Date getSterilizationDate ()
    {
        return sterilizationDate ;
    }

    public void setSterilizationDate (java.util.Date sterilizationDate )
    {
        this.sterilizationDate = sterilizationDate;
    }
    public java.util.Date getInTime ()
    {
        return inTime ;
    }

    public void setInTime (java.util.Date inTime )
    {
        this.inTime = inTime;
    }
    public String getBarcode ()
    {
        return barcode ;
    }

    public void setBarcode (String barcode )
    {
        this.barcode = barcode;
    }
    public int getStocKind ()
    {
        return stocKind ;
    }

    public void setStocKind (int stocKind )
    {
        this.stocKind = stocKind;
    }
    public String getBigBatchCode ()
    {
        return bigBatchCode ;
    }

    public void setBigBatchCode (String bigBatchCode )
    {
        this.bigBatchCode = bigBatchCode;
    }
    public String getUniqueCode ()
    {
        return uniqueCode ;
    }

    public void setUniqueCode (String uniqueCode )
    {
        this.uniqueCode = uniqueCode;
    }
    public String getSecCode ()
    {
        return secCode ;
    }

    public void setSecCode (String secCode )
    {
        this.secCode = secCode;
    }
    public String getHibcCode ()
    {
        return hibcCode ;
    }

    public void setHibcCode (String hibcCode )
    {
        this.hibcCode = hibcCode;
    }
    public java.math.BigDecimal getPacketCode ()
    {
        return packetCode ;
    }

    public void setPacketCode (java.math.BigDecimal packetCode )
    {
        this.packetCode = packetCode;
    }
    public String getIsPacket ()
    {
        return isPacket ;
    }

    public void setIsPacket (String isPacket )
    {
        this.isPacket = isPacket;
    }
    public int getUniqueKind ()
    {
        return uniqueKind ;
    }

    public void setUniqueKind (int uniqueKind )
    {
        this.uniqueKind = uniqueKind;
    }
    public int getVersion ()
    {
        return version ;
    }

    public void setVersion (int version )
    {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        HosStockpileVo that = (HosStockpileVo) o;

        return new EqualsBuilder()
                .append(stocKind, that.stocKind)
                .append(hosId, that.hosId)
                .append(stocId, that.stocId)
                .append(shelfCode, that.shelfCode)
                .append(hosGoodsId, that.hosGoodsId)
                .append(batchNo, that.batchNo)
                .append(expdtEndDate, that.expdtEndDate)
                .append(sterilizationCode, that.sterilizationCode)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(hosId)
                .append(stocId)
                .append(shelfCode)
                .append(hosGoodsId)
                .append(batchNo)
                .append(expdtEndDate)
                .append(sterilizationCode)
                .append(stocKind)
                .toHashCode();
    }
}