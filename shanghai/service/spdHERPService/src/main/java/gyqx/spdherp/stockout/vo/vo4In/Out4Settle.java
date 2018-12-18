package gyqx.spdherp.stockout.vo.vo4In;

import gyqx.spdherp.po.OutStockList;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by moonb on 2017/9/20.
 */
public class Out4Settle extends OutStockList implements Serializable {

    private Date startDate;
    private Date endDate;
    private String hosId;
    private String colId;//集配商ID
    private int outKind;
    private String provUnit;
    private float hosScale;
    private String outDeptId;
    private String outDeptName;
    private String outDeptErpCode;
    private Date outDate;
    private Date produceDatetime;
    private String provGoodsName;
    private String provGoodsId;
    private String saleMan;
    private String hosErpCode;
    private BigDecimal settleAmount;
    private BigDecimal price;
    private String[] toJdeProvIds;
    private String subProvName;
    private String subProvId;
    private String subProvErpCode;
    
    private BigDecimal receiveTemprature;
    private BigDecimal sendTemprature;
    private Date receiveDate;
    private Date deliveryDate;
    private BigDecimal purPrice;
    private String transportType;//货运方式，自运、自提、外运
    private String batchId;//批次 
    private BigDecimal canQty;// 可结算数量
    private BigDecimal settleQty; //已经结算数量
    private String goodsErpCode;//商品erpCode
    private int purMode;//采购模式，虚拟采购20，实际采购:10
    private BigDecimal waitQty;//待结算
    
    
    public BigDecimal getWaitQty() {
		return waitQty;
	}

	public void setWaitQty(BigDecimal waitQty) {
		this.waitQty = waitQty;
	}

	public int getPurMode() {
		return purMode;
	}

	public void setPurMode(int purMode) {
		this.purMode = purMode;
	}

	public String getGoodsErpCode() {
		return goodsErpCode;
	}

	public void setGoodsErpCode(String goodsErpCode) {
		this.goodsErpCode = goodsErpCode;
	}

	public BigDecimal getCanQty() {
		return canQty;
	}

	public BigDecimal getSettleQty() {
		return settleQty;
	}

	public void setCanQty(BigDecimal canQty) {
		this.canQty = canQty;
	}

	public void setSettleQty(BigDecimal settleQty) {
		this.settleQty = settleQty;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public BigDecimal getPurPrice() {
		return purPrice;
	}

	public void setPurPrice(BigDecimal purPrice) {
		this.purPrice = purPrice;
	}

	public Date getProduceDatetime() {
		return produceDatetime;
	}

	public void setProduceDatetime(Date produceDatetime) {
		this.produceDatetime = produceDatetime;
	}

	public BigDecimal getReceiveTemprature() {
		return receiveTemprature;
	}

	public void setReceiveTemprature(BigDecimal receiveTemprature) {
		this.receiveTemprature = receiveTemprature;
	}

	public BigDecimal getSendTemprature() {
		return sendTemprature;
	}

	public void setSendTemprature(BigDecimal sendTemprature) {
		this.sendTemprature = sendTemprature;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getSubProvName() {
		return subProvName;
	}

	public void setSubProvName(String subProvName) {
		this.subProvName = subProvName;
	}

	public String getSubProvId() {
		return subProvId;
	}

	public String getSubProvErpCode() {
		return subProvErpCode;
	}

	public void setSubProvErpCode(String subProvErpCode) {
		this.subProvErpCode = subProvErpCode;
	}

	public void setSubProvId(String subProvId) {
		this.subProvId = subProvId;
	}

	public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getHosId() {
        return hosId;
    }

    public void setHosId(String hosId) {
        this.hosId = hosId;
    }

    public String getColId() {
        return colId;
    }

    public void setColId(String colId){this.colId = colId;}

    public int getOutKind() {
        return outKind;
    }

    public void setOutKind(int outKind) {
        this.outKind = outKind;
    }

    public String getProvUnit() {
        return provUnit;
    }

    public void setProvUnit(String provUnit) {
        this.provUnit = provUnit;
    }

    public float getHosScale() {
        return hosScale;
    }

    public void setHosScale(float hosScale) {
        this.hosScale = hosScale;
    }

    public String getOutDeptId() {
        return outDeptId;
    }

    public void setOutDeptId(String outDeptId) {
        this.outDeptId = outDeptId;
    }

    public String getOutDeptName() {
        return outDeptName;
    }

    public void setOutDeptName(String outDeptName) {
        this.outDeptName = outDeptName;
    }

    public String getOutDeptErpCode() {
        return outDeptErpCode;
    }

    public void setOutDeptErpCode(String outDeptErpCode) {
        this.outDeptErpCode = outDeptErpCode;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getProvGoodsName() {
        return provGoodsName;
    }

    public void setProvGoodsName(String provGoodsName) {
        this.provGoodsName = provGoodsName;
    }

    public String getProvGoodsId() {
        return provGoodsId;
    }

    public void setProvGoodsId(String provGoodsId) {
        this.provGoodsId = provGoodsId;
    }

    public String getSaleMan() {
        return saleMan;
    }

    public void setSaleMan(String saleMan) {
        this.saleMan = saleMan;
    }

    public String getHosErpCode() {
        return hosErpCode;
    }

    public void setHosErpCode(String hosErpCode) {
        this.hosErpCode = hosErpCode;
    }

    public BigDecimal getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(BigDecimal settleAmount) {
        this.settleAmount = settleAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String[] getToJdeProvIds() {
        return toJdeProvIds;
    }

    public void setToJdeProvIds(String[] toJdeProvIds) {
        this.toJdeProvIds = toJdeProvIds;
    }
}
