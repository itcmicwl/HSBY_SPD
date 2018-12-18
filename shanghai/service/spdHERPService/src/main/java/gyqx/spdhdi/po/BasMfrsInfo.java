
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 生产厂商信息表
*/
@Table(name="bas_mfrs_info")
public class BasMfrsInfo implements IBean
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
	 * 生产厂家类型
	 * 
	 * 生产厂家类型
	*/
	@Size(max=36)
	@Column(name="mfrs_kind")
	@Title("生产厂家类型")
	private String mfrsKind ;
	/**
	 * 厂家名称
	*/
	@Size(max=300)
	@Column(name="mfrs_name")
	@Title("厂家名称")
	private String mfrsName ;
	/**
	 * 全国总代理
	*/
	@Size(max=36)
	@Column(name="agent_name")
	@Title("全国总代理")
	private String agentName ;
	/**
	 * 营业执照注册号
	*/
	@Size(max=36)
	@Column(name="reg_code")
	@Title("营业执照注册号")
	private String regCode ;
	/**
	 * 经营许可证编号
	*/
	@Size(max=36)
	@Column(name="jyxk_code")
	@Title("经营许可证编号")
	private String jyxkCode ;
	/**
	 * 纳税人识别号
	*/
	@Size(max=36)
	@Column(name="nsrsb_code")
	@Title("纳税人识别号")
	private String nsrsbCode ;
	/**
	 * 生产许可证
	*/
	@Size(max=36)
	@Column(name="product_code")
	@Title("生产许可证")
	private String productCode ;
	/**
	 * 备注信息
	*/
	@Size(max=128)
	@Column(name="remark")
	@Title("备注信息")
	private String remark ;
	/**
	 * 是否三证合一
	*/
	@Size(max=1)
	@Column(name="is_three_in_one")
	@Title("是否三证合一")
	private String isThreeInOne ;
	/**
	 * 统一码
	*/
	@Size(max=128)
	@Column(name="master_code")
	@Title("统一码")
	private String masterCode ;
	/**
	 * 标志
	*/
	@Size(max=1)
	@Column(name="flag")
	@Title("标志")
	private String flag ;
	/**
	 * 操作员ID
	 * 新增或修改的操作员ID
	*/
	@Size(max=36)
	@Column(name="uxid")
	@Title("操作员ID")
	private String uxid ;
	/**
	 * 建档时间
	*/
	@Column(name="fill_date")
	@Title("建档时间")
	private java.util.Date fillDate ;
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

	public String getId ()
	{
		return id ;
	}
	
	public void setId (String id )
	{
		this.id = id;
	}
	public String getMfrsKind ()
	{
		return mfrsKind ;
	}
	
	public void setMfrsKind (String mfrsKind )
	{
		this.mfrsKind = mfrsKind;
	}
	public String getMfrsName ()
	{
		return mfrsName ;
	}
	
	public void setMfrsName (String mfrsName )
	{
		this.mfrsName = mfrsName;
	}
	public String getAgentName ()
	{
		return agentName ;
	}
	
	public void setAgentName (String agentName )
	{
		this.agentName = agentName;
	}
	public String getRegCode ()
	{
		return regCode ;
	}
	
	public void setRegCode (String regCode )
	{
		this.regCode = regCode;
	}
	public String getJyxkCode ()
	{
		return jyxkCode ;
	}
	
	public void setJyxkCode (String jyxkCode )
	{
		this.jyxkCode = jyxkCode;
	}
	public String getNsrsbCode ()
	{
		return nsrsbCode ;
	}
	
	public void setNsrsbCode (String nsrsbCode )
	{
		this.nsrsbCode = nsrsbCode;
	}
	public String getProductCode ()
	{
		return productCode ;
	}
	
	public void setProductCode (String productCode )
	{
		this.productCode = productCode;
	}
	public String getRemark ()
	{
		return remark ;
	}
	
	public void setRemark (String remark )
	{
		this.remark = remark;
	}
	public String getIsThreeInOne ()
	{
		return isThreeInOne ;
	}
	
	public void setIsThreeInOne (String isThreeInOne )
	{
		this.isThreeInOne = isThreeInOne;
	}
	public String getMasterCode ()
	{
		return masterCode ;
	}
	
	public void setMasterCode (String masterCode )
	{
		this.masterCode = masterCode;
	}
	public String getFlag ()
	{
		return flag ;
	}
	
	public void setFlag (String flag )
	{
		this.flag = flag;
	}
	public String getUxid ()
	{
		return uxid ;
	}
	
	public void setUxid (String uxid )
	{
		this.uxid = uxid;
	}
	public java.util.Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (java.util.Date fillDate )
	{
		this.fillDate = fillDate;
	}
	public java.util.Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (java.util.Date lastUpdateDatetime )
	{
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	public int getVersion ()
	{
		return version ;
	}
	
	public void setVersion (int version )
	{
		this.version = version;
	}

	public String getUid ()
	{
		return uxid ;
	}
	
	public void setUid (String uxid )
	{
		this.uxid = uxid;
	}

	@Override
	public String toString() {
		return "BasMfrsInfo [" +" id=" +  id   +", mfrsKind=" +  mfrsKind   +", mfrsName=" +  mfrsName   +", agentName=" +  agentName   +", regCode=" +  regCode   +", jyxkCode=" +  jyxkCode   +", nsrsbCode=" +  nsrsbCode   +", productCode=" +  productCode   +", remark=" +  remark   +", isThreeInOne=" +  isThreeInOne   +", masterCode=" +  masterCode   +", flag=" +  flag   +", uxid=" +  uxid   +", fillDate=" +  fillDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}