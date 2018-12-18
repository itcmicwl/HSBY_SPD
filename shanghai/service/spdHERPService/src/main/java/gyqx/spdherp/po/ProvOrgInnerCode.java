
package gyqx.spdherp.po;

import java.util.*;
import common.db.IBean;
import common.db.meta.Title;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.*;
/**
 * 配送机构内部标识
*/
@Table(name="prov_org_inner_code")
public class ProvOrgInnerCode implements IBean
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
	 * 配送商ID
	*/
	@Size(max=36)
	@Column(name="prov_id")
	@Title("配送商ID")
	private String provId ;
	/**
	 * 配送商系统标识
	*/
	@Size(max=36)
	@Column(name="sys_flag")
	@Title("配送商系统标识")
	private String sysFlag ;
	/**
	 * 配送商ERP标识
	*/
	@Size(max=36)
	@Column(name="prov_erp_code")
	@Title("配送商ERP标识")
	private String provErpCode ;
	/**
	 * 分配的KEY
	*/
	@Size(max=36)
	@Column(name="put_key")
	@Title("分配的KEY")
	private String putKey ;
	/**
	 * 分配的密码
	*/
	@Size(max=36)
	@Column(name="put_pass")
	@Title("分配的密码")
	private String putPass ;
	/**
	 * 上传KEY
	*/
	@Size(max=36)
	@Column(name="upload_key")
	@Title("上传KEY")
	private String uploadKey ;
	/**
	 * 上传密码
	*/
	@Size(max=36)
	@Column(name="upload_pass")
	@Title("上传密码")
	private String uploadPass ;
	/**
	 * 上传url
	*/
	@Size(max=200)
	@Column(name="upload_url")
	@Title("上传url")
	private String uploadUrl ;
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
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public String getSysFlag ()
	{
		return sysFlag ;
	}
	
	public void setSysFlag (String sysFlag )
	{
		this.sysFlag = sysFlag;
	}
	public String getProvErpCode ()
	{
		return provErpCode ;
	}
	
	public void setProvErpCode (String provErpCode )
	{
		this.provErpCode = provErpCode;
	}
	public String getPutKey ()
	{
		return putKey ;
	}
	
	public void setPutKey (String putKey )
	{
		this.putKey = putKey;
	}
	public String getPutPass ()
	{
		return putPass ;
	}
	
	public void setPutPass (String putPass )
	{
		this.putPass = putPass;
	}
	public String getUploadKey ()
	{
		return uploadKey ;
	}
	
	public void setUploadKey (String uploadKey )
	{
		this.uploadKey = uploadKey;
	}
	public String getUploadPass ()
	{
		return uploadPass ;
	}
	
	public void setUploadPass (String uploadPass )
	{
		this.uploadPass = uploadPass;
	}
	public String getUploadUrl ()
	{
		return uploadUrl ;
	}
	
	public void setUploadUrl (String uploadUrl )
	{
		this.uploadUrl = uploadUrl;
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
		return "ProvOrgInnerCode [" +" id=" +  id   +", provId=" +  provId   +", sysFlag=" +  sysFlag   +", provErpCode=" +  provErpCode   +", putKey=" +  putKey   +", putPass=" +  putPass   +", uploadKey=" +  uploadKey   +", uploadPass=" +  uploadPass   +", uploadUrl=" +  uploadUrl   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}