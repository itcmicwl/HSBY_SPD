
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 科室产品申请表
*/
@Table(name="dept_goods_apply")
public class DeptGoodsApply implements IBean
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
	 * 医疗机构ID
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("医疗机构ID")
	private String hosId ;

	/**
	 * 科室ID
	*/
	@Size(max=36)
	@JoinColumn(name="dept_id",table="sys_org",referencedColumnName="id")
	@Title("科室ID")
	private String deptId ;

	/**
	 * 医院商品ID
	*/
	@Size(max=36)
	@Column(name="hos_goods_id")
	@Title("医院商品ID")
	private String hosGoodsId ;

	/**
	 * 申请人
	*/
	@Size(max=1)
	@Column(name="fillter")
	@Title("申请人")
	private String fillter ;
	
	/**
	 * 申请原因
	*/
	@Size(max=256)
	@Column(name="apply_reason")
	@Title("申请原因")
	private String applyReason ;
	
	/**
	 * 申请时间
	*/
	@Column(name="apply_time")
	@Title("申请时间")
	private java.util.Date applyTime ;
	
	/**
	 * 审核意见
	*/
	@Size(max=256)
	@Column(name="audit_view")
	@Title("审核意见")
	private String auditView ;
	
	/**
	 * 审核人id
	*/
	@Size(max=36)
	@Column(name="auditer")
	@Title("审核人id")
	private String auditer ;
	
	/**
	 * 审核时间
	*/
	@Column(name="audit_time")
	@Title("审核时间")
	private java.util.Date auditTime ;
	
	/**
	 * 状态
	*/
	@Column(name="status")
	@Title("状态")
	private Integer status ;

	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private java.util.Date lastUpdateDatetime ;

	/**
	 * 数据版本
	*/
	@Column(name="version")
	@Title("数据版本")
	private int version ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}



	public String getHosGoodsId() {
		return hosGoodsId;
	}

	public void setHosGoodsId(String hosGoodsId) {
		this.hosGoodsId = hosGoodsId;
	}

	public String getFillter() {
		return fillter;
	}

	public void setFillter(String fillter) {
		this.fillter = fillter;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public java.util.Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(java.util.Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getAuditView() {
		return auditView;
	}

	public void setAuditView(String auditView) {
		this.auditView = auditView;
	}

	public String getAuditer() {
		return auditer;
	}

	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}

	public java.util.Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(java.util.Date auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public java.util.Date getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}

	public void setLastUpdateDatetime(java.util.Date lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	

}