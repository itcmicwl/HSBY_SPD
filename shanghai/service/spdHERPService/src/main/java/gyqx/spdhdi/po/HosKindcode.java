
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 医院产品类别
*/
@Table(name="hos_kindcode")
public class HosKindcode implements IBean
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
	@JoinColumn(name="hos_id",table="bas_company_info",referencedColumnName="id")
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
	@Size(max=50)
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
	 * neibu代码
	 */
	@Size(max=64)
	@Column(name="in_code")
	@Title("层级代码")
	private String inCode ;
	/**
	 * 状态
	 * 10启用,20停用
	*/
	@Column(name="state")
	@Title("状态")
	private int state ;
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
	public int getState ()
	{
		return state ;
	}
	
	public void setState (int state )
	{
		this.state = state;
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

	public String getInCode() {
		return inCode;
	}

	public void setInCode(String inCode) {
		this.inCode = inCode;
	}

	@Override
	public String toString() {
		return "HosKindcode [" +" id=" +  id   +", hosId=" +  hosId   +", pId=" +  pId   +", kindName=" +  kindName   +", levelCode=" +  levelCode   +", state=" +  state   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}