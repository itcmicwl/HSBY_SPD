
package gyqx.spdherp.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import common.db.IBean;
import common.db.meta.Title;
/**
 * 人员与科室产品模版关系表
*/
@Table(name="goods_pur_template_user_rel")
public class GoodsPurTemplateUserRel implements IBean
{
	private static final long serialVersionUID = 3845364707511521358L;
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
	 * 用户ID
	*/
	@Size(max=36)
	@Column(name="user_id")
	@Title("用户ID")
	private String userId ;
	/**
	 * 科室ID
	*/
	@Size(max=36)
	@Column(name="dept_id")
	@Title("科室ID")
	private String deptId ;
	/**
	 * 模板ID
	*/
	@Size(max=36)
	@Column(name="temp_id")
	@Title("模板ID")
	private String tempId ;
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
	public String getUserId ()
	{
		return userId ;
	}
	
	public void setUserId (String userId )
	{
		this.userId = userId;
	}
	public String getDeptId ()
	{
		return deptId ;
	}
	
	public void setDeptId (String deptId )
	{
		this.deptId = deptId;
	}
	public String getTempId ()
	{
		return tempId ;
	}
	
	public void setTempId (String tempId )
	{
		this.tempId = tempId;
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
		return "GoodsPurTemplateUserRelation [" +" id=" +  id   +", hosId=" +  hosId   +", userId=" +  userId   +", deptId=" +  deptId   +", tempId=" +  tempId   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}