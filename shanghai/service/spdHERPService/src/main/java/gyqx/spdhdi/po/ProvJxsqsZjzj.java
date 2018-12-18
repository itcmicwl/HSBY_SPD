
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 供应商经销授权书逐级证件
*/
@Table(name="prov_jxsqs_zjzj")
public class ProvJxsqsZjzj implements IBean
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
	 * 授权书ID
	*/
	@Size(max=36)
	@JoinColumn(name="p_id",table="prov_jxsqs",referencedColumnName="id")
	@Title("授权书ID")
	private String pId ;
	/**
	 * 厂家/供商名称
	*/
	@Size(max=36)
	@Column(name="p_name")
	@Title("厂家/供商名称")
	private String pName ;
	/**
	 * 附件路径
	*/
	@Size(max=128)
	@Column(name="file_path")
	@Title("附件路径")
	private String filePath ;
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
	public String getPId ()
	{
		return pId ;
	}
	
	public void setPId (String pId )
	{
		this.pId = pId;
	}
	public String getPName ()
	{
		return pName ;
	}
	
	public void setPName (String pName )
	{
		this.pName = pName;
	}
	public String getFilePath ()
	{
		return filePath ;
	}
	
	public void setFilePath (String filePath )
	{
		this.filePath = filePath;
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
		return "ProvJxsqsZjzj [" +" id=" +  id   +", pId=" +  pId   +", pName=" +  pName   +", filePath=" +  filePath   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}