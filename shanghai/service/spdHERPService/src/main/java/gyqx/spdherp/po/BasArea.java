
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 行政区划
*/
@Table(name="bas_area")
public class BasArea implements IBean
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
	 * 区域代码
	*/
	@Size(max=36)
	@Column(name="code")
	@Title("区域代码")
	private String code ;
	/**
	 * 名称
	*/
	@Size(max=36)
	@Column(name="cname")
	@Title("名称")
	private String cname ;
	/**
	 * 层次编码
	*/
	@Size(max=36)
	@Column(name="code_num")
	@Title("层次编码")
	private String codeNum ;
	/**
	 * 上级ID
	*/
	@Size(max=36)
	@Column(name="pid")
	@Title("上级ID")
	private String pid ;
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
	public String getCode ()
	{
		return code ;
	}
	
	public void setCode (String code )
	{
		this.code = code;
	}
	public String getCname ()
	{
		return cname ;
	}
	
	public void setCname (String cname )
	{
		this.cname = cname;
	}
	public String getCodeNum ()
	{
		return codeNum ;
	}
	
	public void setCodeNum (String codeNum )
	{
		this.codeNum = codeNum;
	}
	public String getPid ()
	{
		return pid ;
	}
	
	public void setPid (String pid )
	{
		this.pid = pid;
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
		return "BasArea [" +" id=" +  id   +", code=" +  code   +", cname=" +  cname   +", codeNum=" +  codeNum   +", pid=" +  pid   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}