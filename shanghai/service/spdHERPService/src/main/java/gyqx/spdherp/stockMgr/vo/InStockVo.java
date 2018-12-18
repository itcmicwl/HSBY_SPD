package gyqx.spdherp.stockMgr.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gyqx.spdherp.po.InStock;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class InStockVo extends InStock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4418046541104926308L;
	private List<InStockListVo> lstDetail;
	private List<InStockListVo> removeRowList;
	private String fillterName;
	private String accounterName;
	private String audiorName;
	//入库科室
	private String inDeptCode;
	//入库仓库名
	private String inStocName;
	//入库参考code
	private String inStocCode;
	private String userId;
	
	private String hosId;

	//开始制单日期
	private Date startDate;
	private String startDateStr;
	//结束制单日期
	private Date endDate;
	private String endDateStr;
	//单据状态
	private String billStatus;

	// 条码
	private List<String> barcodeList;

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getInStocCode() {
		return inStocCode;
	}

	public void setInStocCode(String inStocCode) {
		this.inStocCode = inStocCode;
	}

	public String getInStocName() {
		return inStocName;
	}

	public void setInStocName(String inStocName) {
		this.inStocName = inStocName;
	}

	public String getInDeptCode() {
		return inDeptCode;
	}
    
	public void setInDeptCode(String inDeptCode) {
		this.inDeptCode = inDeptCode;
	}

	public String getAccounterName() {
		return accounterName;
	}

	public void setAccounterName(String accounterName) {
		this.accounterName = accounterName;
	}

	public String getAudiorName() {
		return audiorName;
	}

	public void setAudiorName(String audiorName) {
		this.audiorName = audiorName;
	}

	public String getFillterName() {
		return fillterName;
	}

	public void setFillterName(String fillterName) {
		this.fillterName = fillterName;
	}

	public List<InStockListVo> getLstDetail() {
		return lstDetail;
	}
	public void setLstDetail(List<InStockListVo> lstDetail) {
		this.lstDetail = lstDetail;
	}

	public List<String> getBarcodeList() {
		return barcodeList;
	}

	public void setBarcodeList(List<String> barcodeList) {
		this.barcodeList = barcodeList;
	}

	public List<InStockListVo> getRemoveRowList() {
		return removeRowList;
	}

	public void setRemoveRowList(List<InStockListVo> removeRowList) {
		this.removeRowList = removeRowList;
	}
}
