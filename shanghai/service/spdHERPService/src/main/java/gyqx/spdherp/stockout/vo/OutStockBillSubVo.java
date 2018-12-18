package gyqx.spdherp.stockout.vo;

import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Auther Liangwu
 * @Date 17-8-29 下午4:32
 */
//@JsonInclude(value= JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class OutStockBillSubVo {
    /**
     * ID
     */
    @Id
    @Size(max = 36)
    @Column(name = "id")
    @Title("ID")
    private String id;
    /**
     * PID
     */
    @Size(max = 36)
    @Column(name = "pid")
    @Title("PID")
    private String pid;
    /**
     * 单号
     */
    @Size(max = 36)
    @Column(name = "bill_id")
    @Title("单号")
    private String billId;
    /**
     * 源单号
     */
    @Size(max = 36)
    @Column(name = "source_bill_id")
    @Title("源单号")
    private String sourceBillId;
    /**
     * 出库单行号
     */
    @Column(name = "out_bill_row")
    @Title("出库单行号")
    private int outBillRow;
    /**
     * 配送机构
     */
    @Size(max = 36)
    @Column(name = "prov_id")
    @Title("配送机构")
    private String provId;
    /**
     * 配送机构编号
     */
    @Size(max = 36)
    @Column(name = "prov_code")
    @Title("配送机构编号")
    private String provCode;
    /**
     * 配送机构名称
     */
    @Size(max = 36)
    @Column(name = "prov_name")
    @Title("配送机构名称")
    private String provName;
    /**
     * 产品ID
     */
    @Size(max = 36)
    @Column(name = "goods_id")
    @Title("产品ID")
    private String goodsId;
    /**
     * 产品名称
     */
    @Size(max = 36)
    @Column(name = "goods_name")
    @Title("产品名称")
    private String goodsName;
    /**
     * 规格
     */
    @Size(max = 36)
    @Column(name = "goods_gg")
    @Title("规格")
    private String goodsGg;
    /**
     * 生产厂商ID
     */
    @Size(max = 36)
    @Column(name = "mfrs_id")
    @Title("生产厂商ID")
    private String mfrsId;
    /**
     * 生产厂商名称
     */
    @Size(max = 200)
    @Column(name = "mfrs_name")
    @Title("生产厂商名称")
    private String mfrsName;
    /**
     * 产地
     */
    @Size(max = 36)
    @Column(name = "made")
    @Title("产地")
    private String made;
    /**
     * 包装数量
     */
    @Column(name = "packet_code")
    @Title("包装数量")
    private Double packetCode;
    /**
     * 是否定数包管理
     */
    @Size(max = 1)
    @Column(name = "is_packet")
    @Title("是否定数包管理")
    private String isPacket;
    /**
     * 是否唯一码管理
     */
    @Size(max = 1)
    @Column(name = "is_unique")
    @Title("是否唯一码管理")
    private String isUnique;
    /**
     * 批号
     */
    @Size(max = 36)
    @Column(name = "batch_code")
    @Title("批号")
    private String batchCode;
	/**
	 * 注册证
	*/
	@Size(max=256)
	@Column(name="certificate_code")
	@Title("注册证")
	private String certificateCode ;
    /**
     * 灭菌日期
     */
    @Column(name = "sterilization_date")
    @Title("灭菌日期")
    private java.util.Date sterilizationDate;
    /**
     * 灭菌批号
     */
    @Size(max = 36)
    @Column(name = "sterilization_code")
    @Title("灭菌批号")
    private String sterilizationCode;
    /**
     * 灭菌效期
     */
    @Column(name = "sterilization_end_date")
    @Title("灭菌效期")
    private java.util.Date sterilizationEndDate;
    /**
     * 有效期至
     */
    @Column(name = "expdt_end_date")
    @Title("有效期至")
    private java.util.Date expdtEndDate;
    /**
     * 单位
     */
    @Size(max = 36)
    @Column(name = "unit")
    @Title("单位")
    private String unit;
    /**
     * 出库数量
     */
    @Column(name = "out_qty")
    @Title("出库数量")
    private Double outQty;
    /**
     * 出库包数
     */
    @Column(name = "out_packet_qty")
    @Title("出库包数")
    private Double outPacketQty;
    /**
     * 主码
     */
    @Size(max = 128)
    @Column(name = "master_code")
    @Title("主码")
    private String masterCode;
    /**
     * 副码
     */
    @Size(max = 128)
    @Column(name = "sec_code")
    @Title("副码")
    private String secCode;
    /**
     * HIBC
     */
    @Size(max = 128)
    @Column(name = "hibc_code")
    @Title("HIBC")
    private String hibcCode;
    /**
     * 货位ID
     */
    @Size(max = 36)
    @Column(name = "shelf_id")
    @Title("货位ID")
    private String shelfId;
    /**
     * 数据版本
     */
    @Column(name = "version")
    @Title("数据版本")
    private int version;

    private Double price;

    private Double avlQty;

    private Double reqQty;

    private Double sentQty;

    private String efPoint;//是否有效1有效0过期
    
    private String saleBillId;//销售单号

    private String erpCode; //商品ERP编码

    public String getErpCode() {
        return erpCode;
    }

    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }

    public String getSaleBillId() {
		return saleBillId;
	}

	public void setSaleBillId(String saleBillId) {
		this.saleBillId = saleBillId;
	}

	public Double getAvlQty() {
        return avlQty;
    }

    public void setAvlQty(Double avlQty) {
        this.avlQty = avlQty;
    }

    public Double getReqQty() {
        return reqQty;
    }

    public void setReqQty(Double reqQty) {
        this.reqQty = reqQty;
    }

    public Double getSentQty() {
        return sentQty;
    }

    public void setSentQty(Double sentQty) {
        this.sentQty = sentQty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getSourceBillId() {
        return sourceBillId;
    }

    public void setSourceBillId(String sourceBillId) {
        this.sourceBillId = sourceBillId;
    }

    public int getOutBillRow() {
        return outBillRow;
    }

    public void setOutBillRow(int outBillRow) {
        this.outBillRow = outBillRow;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getProvCode() {
        return provCode;
    }

    public void setProvCode(String provCode) {
        this.provCode = provCode;
    }

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsGg() {
        return goodsGg;
    }

    public void setGoodsGg(String goodsGg) {
        this.goodsGg = goodsGg;
    }

    public String getMfrsId() {
        return mfrsId;
    }

    public void setMfrsId(String mfrsId) {
        this.mfrsId = mfrsId;
    }

    public String getMfrsName() {
        return mfrsName;
    }

    public void setMfrsName(String mfrsName) {
        this.mfrsName = mfrsName;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }

    public Double getPacketCode() {
        return packetCode;
    }

    public void setPacketCode(Double packetCode) {
        this.packetCode = packetCode;
    }

    public String getIsPacket() {
        return isPacket;
    }

    public void setIsPacket(String isPacket) {
        this.isPacket = isPacket;
    }

    public String getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(String isUnique) {
        this.isUnique = isUnique;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getCertificateCode() {
		return certificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}

	public Date getSterilizationDate() {
        return sterilizationDate;
    }

    public void setSterilizationDate(Date sterilizationDate) {
        this.sterilizationDate = sterilizationDate;
    }

    public String getSterilizationCode() {
        return sterilizationCode;
    }

    public void setSterilizationCode(String sterilizationCode) {
        this.sterilizationCode = sterilizationCode;
    }

    public Date getSterilizationEndDate() {
        return sterilizationEndDate;
    }

    public void setSterilizationEndDate(Date sterilizationEndDate) {
        this.sterilizationEndDate = sterilizationEndDate;
    }

    public Date getExpdtEndDate() {
        return expdtEndDate;
    }

    public void setExpdtEndDate(Date expdtEndDate) {
        this.expdtEndDate = expdtEndDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getOutQty() {
        return outQty;
    }

    public void setOutQty(Double outQty) {
        this.outQty = outQty;
    }

    public Double getOutPacketQty() {
        return outPacketQty;
    }

    public void setOutPacketQty(Double outPacketQty) {
        this.outPacketQty = outPacketQty;
    }

    public String getMasterCode() {
        return masterCode;
    }

    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getHibcCode() {
        return hibcCode;
    }

    public void setHibcCode(String hibcCode) {
        this.hibcCode = hibcCode;
    }

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

	public String getEfPoint() {
		return efPoint;
	}

	public void setEfPoint(String efPoint) {
		this.efPoint = efPoint;
	}
    
}
