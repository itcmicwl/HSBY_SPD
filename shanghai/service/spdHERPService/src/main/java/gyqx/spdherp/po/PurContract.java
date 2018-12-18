
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 合同信息
*/
@Table(name="pur_contract")
public class PurContract implements IBean
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
	 * 合同编号
	*/
	@Size(max=50)
	@Column(name="contract_code")
	@Title("合同编号")
	private String contractCode ;
	/**
	 * 合同名称
	*/
	@Size(max=200)
	@Column(name="contract_name")
	@Title("合同名称")
	private String contractName ;
	/**
	 * 医院ID
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 供应商ID
	*/
	@Size(max=36)
	@Column(name="prov_id")
	@Title("供应商ID")
	private String provId ;
	/**
	 * 合同签订日期
	*/
	@Column(name="sign_date")
	@Title("合同签订日期")
	private java.util.Date signDate ;
	/**
	 * 合同生效日期
	*/
	@Column(name="begin_date")
	@Title("合同生效日期")
	private java.util.Date beginDate ;
	/**
	 * 截止日期
	*/
	@Column(name="end_date")
	@Title("截止日期")
	private java.util.Date endDate ;
	/**
	 * 附件
	*/
	@Size(max=500)
	@Column(name="attachment_path")
	@Title("附件")
	private String attachmentPath ;
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
	public String getContractCode ()
	{
		return contractCode ;
	}
	
	public void setContractCode (String contractCode )
	{
		this.contractCode = contractCode;
	}
	public String getContractName ()
	{
		return contractName ;
	}
	
	public void setContractName (String contractName )
	{
		this.contractName = contractName;
	}
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
	}
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public java.util.Date getSignDate ()
	{
		return signDate ;
	}
	
	public void setSignDate (java.util.Date signDate )
	{
		this.signDate = signDate;
	}
	public java.util.Date getBeginDate ()
	{
		return beginDate ;
	}
	
	public void setBeginDate (java.util.Date beginDate )
	{
		this.beginDate = beginDate;
	}
	public java.util.Date getEndDate ()
	{
		return endDate ;
	}
	
	public void setEndDate (java.util.Date endDate )
	{
		this.endDate = endDate;
	}
	public String getAttachmentPath ()
	{
		return attachmentPath ;
	}
	
	public void setAttachmentPath (String attachmentPath )
	{
		this.attachmentPath = attachmentPath;
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


	@Override
	public String toString() {
		return "PurContract [" +" id=" +  id   +", contractCode=" +  contractCode   +", contractName=" +  contractName   +", hosId=" +  hosId   +", provId=" +  provId   +", signDate=" +  signDate   +", beginDate=" +  beginDate   +", endDate=" +  endDate   +", attachmentPath=" +  attachmentPath   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}