package gyqx.spdherp.stockMgr.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gyqx.spdherp.po.InStockBatch;
import gyqx.spdherp.po.InStockList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InStockBatchVo extends InStockBatch implements Serializable {

	/**
	 * 
	 */
	private BigDecimal settleQty;

	public BigDecimal getSettleQty() {
		return settleQty;
	}

	public void setSettleQty(BigDecimal settleQty) {
		this.settleQty = settleQty;
	}

	private static final long serialVersionUID = 8667360323053600775L;
	public String getBigBatchC(InStockList isl,Integer inStockType){
		String bigBatchCode = String.format("%s-%s-%s-%s-%s-%s-%s-%s-%s-%s-%s-%s", inStockType.toString(),this.getInPrice().toString(),isl.getBatchCode(),this.getGoodsBatchId(),parsDate(true,isl.getExpdtEndDate()),
				isl.getSterilizationCode(),parsDate(true,isl.getSterilizationEndDate()),parsDate(true,isl.getSterilizationDate()),parsDate(false,this.getInTime()),isl.getMasterCode(),isl.getSecCode(),isl.getHibcCode());
		return bigBatchCode;
	}
	private String parsDate(boolean flag, Date d){
		if(d == null){
			return "";
		}else{
			SimpleDateFormat f;
			if(flag){
				f = new SimpleDateFormat("yyMMdd");
			}else{
				f = new SimpleDateFormat("yyMMddhhmmss");
			}
			return f.format(d);
		}
	}
}
