
package gyqx.spdherp.productsInfos.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import common.db.meta.Title;
/**
 * 手术包类型
*/
@Table(name="operating_package_kindcode")
public class OperatingPackageKindcodeVo
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
		return "OperatingPackageKindcode [" +" id=" +  id   +", hosId=" +  hosId   +", pId=" +  pId   +", kindName=" +  kindName   +", levelCode=" +  levelCode   +", version=" +  version   
			 + "]";
	}

}