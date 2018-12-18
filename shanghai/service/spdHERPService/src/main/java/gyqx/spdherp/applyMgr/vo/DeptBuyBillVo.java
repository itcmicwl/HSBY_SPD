package gyqx.spdherp.applyMgr.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import common.db.meta.Title;
import gyqx.spdherp.po.DeptBuyMain;
import gyqx.spdherp.po.DeptBuySub;

public class DeptBuyBillVo extends DeptBuyMain {

	private static final long serialVersionUID = 7715943062892107406L;
	private List<SurDef4applyBillVo> surDef4applyBillVoList ;  //手术包对象
	private List<DeptBuySub> subList;
	
	private List<DeptCarGoodsStVo> carList;
	
	
	private List <DeptBuySubVo> subVoList;
 
	private String fillterName;
	private String deptDealManName;
	private String auditorName;

	private int[] stateArr;
	
	private String auditType;//审核类型:护士长审核，设备科审核
	
	private String point;//是否标红
	
	//for 请领采购添加属性
	
	private String rowNumber;
	private String subId ;
	private String goodsId;
	private String provId;
	private String[] provIdArr;
 	private java.util.Date sDate;
	private java.util.Date eDate;
	private int qty; //请购数量
	private int purQty; //请购数量
	private java.math.BigDecimal je ; //请购数量*price
	
	private int sendType;
	
	private String subState;

	private String uniqueCodeStrategy;
	private String salemanId;
	public String getUniqueCodeStrategy() {
		return uniqueCodeStrategy;
	}

	public void setUniqueCodeStrategy(String uniqueCodeStrategy) {
		this.uniqueCodeStrategy = uniqueCodeStrategy;
	}

	public String getSubState() {
		return subState;
	}

	public void setSubState(String subState) {
		this.subState = subState;
	}

	public int getSendType() {
		return sendType;
	}

	public void setSendType(int sendType) {
		this.sendType = sendType;
	}

	public java.util.Date getsDate() {
		return sDate;
	}

	public void setsDate(java.util.Date sDate) {
		this.sDate = sDate;
	}

	public java.util.Date geteDate() {
		return eDate;
	}

	public void seteDate(java.util.Date eDate) {
		this.eDate = eDate;
	}
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getPurQty() {
		return purQty;
	}

	public void setPurQty(int purQty) {
		this.purQty = purQty;
	}

	public java.math.BigDecimal getJe() {
		return je;
	}

	public void setJe(java.math.BigDecimal je) {
		this.je = je;
	}

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * je 单据总金额
	*/
	@Column(name="total_je")
	@Title("请购单据总金额")
	private java.math.BigDecimal totalJe ;
	
	/**
	 * 请购科室名
	*/
	@Size(max=36)
	@Column(name="buy_dept_name")
	@Title("请购科室名")
	private String buyDeptName ;

	private String buyDeptCode;
	/**
	 * 货源仓库Id（科室）  目前确定只能向科室请购
	*/
	@Size(max=36)
	@Column(name="source_warehouse_name")
	@Title("货源仓库名（科室）")
	private String sourceWarehouseName ;
	
	public List<DeptCarGoodsStVo> getCarList() {
		return carList;
	}

	public void setCarList(List<DeptCarGoodsStVo> carList) {
		this.carList = carList;
	}

	public List<DeptBuySub> getSubList() {
		return subList;
	}

	public void setSubList(List<DeptBuySub> subList) {
		this.subList = subList;
	}
	public List<DeptBuySubVo> getSubVoList() {
		return subVoList;
	}

	public void setSubVoList(List<DeptBuySubVo> subVoList) {
		this.subVoList = subVoList;
	}


	public String getBuyDeptName() {
		return buyDeptName;
	}

	public String getSourceWarehouseName() {
		return sourceWarehouseName;
	}



	public java.math.BigDecimal getTotalJe() {
		return totalJe;
	}

	public void setTotalJe(java.math.BigDecimal totalJe) {
		this.totalJe = totalJe;
	}

	public void setBuyDeptName(String buyDeptName) {
		this.buyDeptName = buyDeptName;
	}

	public void setSourceWarehouseName(String sourceWarehouseName) {
		this.sourceWarehouseName = sourceWarehouseName;
	}
	public String getFillterName() {
		return fillterName;
	}

	public void setFillterName(String fillterName) {
		this.fillterName = fillterName;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
	public String getDeptDealManName() {
		return deptDealManName;
	}

	public void setDeptDealManName(String deptDealManName) {
		this.deptDealManName = deptDealManName;
	}

	public String[] getProvIdArr() {
		return provIdArr;
	}

	public void setProvIdArr(String[] provIdArr) {
		this.provIdArr = provIdArr;
	}
	public int[] getStateArr() {
		return stateArr;
	}

	public void setStateArr(int[] stateArr) {
		this.stateArr = stateArr;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getSalemanId() {
		return salemanId;
	}

	public void setSalemanId(String salemanId) {
		this.salemanId = salemanId;
	}

	public List<SurDef4applyBillVo> getSurDef4applyBillVoList() {
		return surDef4applyBillVoList;
	}

	public void setSurDef4applyBillVoList(List<SurDef4applyBillVo> surDef4applyBillVoList) {
		this.surDef4applyBillVoList = surDef4applyBillVoList;
	}
}
