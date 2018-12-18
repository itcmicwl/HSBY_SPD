
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
 * 手术包类型
*/
@Table(name="operating_package_kindcode")
public class OperatingPackageKindcode implements IBean
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
	 * 医院ID
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 上级ID
	*/
	@Size(max=36)
	@Column(name="p_id")
	@Title("上级ID")
	private String pId ;
	/**
	 * 类别名称
	*/
	@Size(max=100)
	@Column(name="kind_name")
	@Title("类别名称")
	private String kindName ;
	/**
	 * 层级代码
	*/
	@Size(max=200)
	@Column(name="level_code")
	@Title("层级代码")
	private String levelCode ;
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
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
	}
	public String getPId ()
	{
		return pId ;
	}
	
	public void setPId (String pId )
	{
		this.pId = pId;
	}
	public String getKindName ()
	{
		return kindName ;
	}
	
	public void setKindName (String kindName )
	{
		this.kindName = kindName;
	}
	public String getLevelCode ()
	{
		return levelCode ;
	}
	
	public void setLevelCode (String levelCode )
	{
		this.levelCode = levelCode;
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
		return "OperatingPackageKindcode [" +" id=" +  id   +", hosId=" +  hosId   +", pId=" +  pId   +", kindName=" +  kindName   +", levelCode=" +  levelCode   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}