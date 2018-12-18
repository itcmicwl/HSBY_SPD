package gyqx.spdherp.stockMgr.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gyqx.spdhdi.orderMgr.vo.DistrBillListVo;
import gyqx.spdherp.po.InStockList;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InStockListVo extends InStockList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6557360787597881439L;
	private String outBillId;
	private BigDecimal inPrice;
	private List<InStockBatchVo> lstBatch;
	private List<InStockUniqueCodeVo> lstUniqueCode;
	private String shelfInfo;
	private BigDecimal unUsedQty;
	private BigDecimal usedQty;
	/**
	 * 储运条件
	 */
	private String stCondition;

	public BigDecimal getInPrice() {
		return inPrice;
	}
	public void setInPrice(BigDecimal inPrice) {
		this.inPrice = inPrice;
	}
	public List<InStockBatchVo> getLstBatch() {
		return lstBatch;
	}
	public void setLstBatch(List<InStockBatchVo> lstBatch) {
		this.lstBatch = lstBatch;
	}
	public List<InStockUniqueCodeVo> getLstUniqueCode() {
		return lstUniqueCode;
	}
	public void setLstUniqueCode(List<InStockUniqueCodeVo> lstUniqueCode) {
		this.lstUniqueCode = lstUniqueCode;
	}

	public String getOutBillId() {
		return outBillId;
	}

	public void setOutBillId(String outBillId) {
		this.outBillId = outBillId;
	}

	public String getStCondition() {
		return stCondition;
	}
	public void setStCondition(String stCondition) {
		this.stCondition = stCondition;
	}

	public BigDecimal getUnUsedQty() {
		return unUsedQty;
	}

	public void setUnUsedQty(BigDecimal unUsedQty) {
		this.unUsedQty = unUsedQty;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public BigDecimal getUsedQty() {
		return usedQty;
	}

	public void setUsedQty(BigDecimal usedQty) {
		this.usedQty = usedQty;
	}

	public String getShelfInfo() {
        return shelfInfo;
    }

    public void setShelfInfo(String shelfInfo) {
        this.shelfInfo = shelfInfo;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (obj instanceof DistrBillListVo) {
			DistrBillListVo d = (DistrBillListVo) obj;
			return d.getRowNumber() == this.getOutBillRow();
		}
		return super.equals(obj);
	}
}