package gyqx.spdherp.stockout.vo;

import gyqx.spdherp.po.OutStockBatch;

public class OutStockBatchVo extends OutStockBatch {
	//所属手术包
	private String surCode;
	
	//批号
	private String batchCode;
	
	
	
	public String getSurCode() {
		return surCode;
	}
	
	public void setSurCode(String surCode) {
		this.surCode = surCode;
	}
	
	public String getBatchCode() {
		return batchCode;
	}
	
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
}
