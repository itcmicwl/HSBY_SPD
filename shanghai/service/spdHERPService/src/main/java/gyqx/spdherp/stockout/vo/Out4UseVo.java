package gyqx.spdherp.stockout.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by moonb on 2018/5/2.
 */
public class Out4UseVo {
    private String id;
    private String billId;
    private Integer outBillRow;
    private String goodsId;
    private String goodsName;
    private String goodsGg;
    private String unit;
    private String sterilizationCode;
    private Date expdtEndDate;
    private Date sterilizationDate;
    private Date sterilizationEndDate;
    private BigDecimal qty;
    private BigDecimal price;
    private BigDecimal hisPrice;            //暂无
    private String goodsBatchId;
    private String batchCode;
    private String outOrgId;
    private String outDeptId;
    private String outStocId;
    private String filler;
    private Date fillDate;
    private String masterCode;
    private String secCode;
    private String hibcCode;
    private String epcCode;             //暂无
    private String selfCode;            //暂无
    private String provId;            //暂无
    private String uniqueCode;            //暂无
    private String outStockType;            //暂无

	public String getOutStocId() {
		return outStocId;
	}

	public void setOutStocId(String outStocId) {
		this.outStocId = outStocId;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getOutBillRow() {
        return outBillRow;
    }

    public void setOutBillRow(Integer outBillRow) {
        this.outBillRow = outBillRow;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getSterilizationDate() {
        return sterilizationDate;
    }

    public void setSterilizationDate(Date sterilizationDate) {
        this.sterilizationDate = sterilizationDate;
    }

    public Date getSterilizationEndDate() {
        return sterilizationEndDate;
    }

    public void setSterilizationEndDate(Date sterilizationEndDate) {
        this.sterilizationEndDate = sterilizationEndDate;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getSterilizationCode() {
        return sterilizationCode;
    }

    public void setSterilizationCode(String sterilizationCode) {
        this.sterilizationCode = sterilizationCode;
    }

    public Date getExpdtEndDate() {
        return expdtEndDate;
    }

    public void setExpdtEndDate(Date expdtEndDate) {
        this.expdtEndDate = expdtEndDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getHisPrice() {
        return hisPrice;
    }

    public void setHisPrice(BigDecimal hisPrice) {
        this.hisPrice = hisPrice;
    }

    public String getGoodsBatchId() {
        return goodsBatchId;
    }

    public void setGoodsBatchId(String goodsBatchId) {
        this.goodsBatchId = goodsBatchId;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getOutOrgId() {
        return outOrgId;
    }

    public void setOutOrgId(String outOrgId) {
        this.outOrgId = outOrgId;
    }

    public String getOutDeptId() {
        return outDeptId;
    }

    public void setOutDeptId(String outDeptId) {
        this.outDeptId = outDeptId;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public Date getFillDate() {
        return fillDate;
    }

    public void setFillDate(Date fillDate) {
        this.fillDate = fillDate;
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

    public String getEpcCode() {
        return epcCode;
    }

    public void setEpcCode(String epcCode) {
        this.epcCode = epcCode;
    }

    public String getSelfCode() {
        return selfCode;
    }

    public void setSelfCode(String selfCode) {
        this.selfCode = selfCode;
    }

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public String getOutStockType() {
		return outStockType;
	}

	public void setOutStockType(String outStockType) {
		this.outStockType = outStockType;
	}
}
