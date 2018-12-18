
package gyqx.spdherp.productsInfos.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import common.db.meta.Title;
/**
 * 手术包信息
*/
@Table(name="operating_package_info")
public class OperatingPackageInfoVo
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
	 * 手术包编码
	*/
	@Size(max=36)
	@Column(name="code")
	@Title("手术包编码")
	private String code ;
	/**
	 * 手术包名称
	*/
	@Size(max=100)
	@Column(name="cname")
	@Title("手术包名称")
	private String cname ;
	/**
	 * 拼音码
	*/
	@Size(max=150)
	@Column(name="short_pinyin")
	@Title("拼音码")
	private String shortPinyin ;
	/**
	 * 类别
	*/
	@Size(max=36)
	@Column(name="kind_code")
	@Title("类别")
	private String kindCode ;
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
	public String getShortPinyin ()
	{
		return shortPinyin ;
	}
	
	public void setShortPinyin (String shortPinyin )
	{
		this.shortPinyin = shortPinyin;
	}
	public String getKindCode ()
	{
		return kindCode ;
	}
	
	public void setKindCode (String kindCode )
	{
		this.kindCode = kindCode;
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
		return "OperatingPackageInfo [" +" id=" +  id   +", hosId=" +  hosId   +", code=" +  code   +", cname=" +  cname   +", shortPinyin=" +  shortPinyin   +", kindCode=" +  kindCode   +", version=" +  version   
			 + "]";
	}

}