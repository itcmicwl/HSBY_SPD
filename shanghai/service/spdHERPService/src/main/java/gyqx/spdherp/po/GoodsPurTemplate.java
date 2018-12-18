
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
 * 科室产品模板
*/
@Table(name="goods_pur_template")
public class GoodsPurTemplate implements IBean
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
	 * 上级ID
	*/
	@Size(max=36)
	@Column(name="p_id")
	@Title("上级ID")
	private String pId ;
	/**
	 * 名称
	*/
	@Size(max=100)
	@Column(name="cname")
	@Title("名称")
	private String cname ;
	/**
	 * 医院ID
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 科室ID
	*/
	@Size(max=36)
	@Column(name="dept_id")
	@Title("科室ID")
	private String deptId ;
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
	public String getCname ()
	{
		return cname ;
	}
	
	public void setCname (String cname )
	{
		this.cname = cname;
	}
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
	}
	public String getDeptId ()
	{
		return deptId ;
	}
	
	public void setDeptId (String deptId )
	{
		this.deptId = deptId;
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
		return "GoodsPurTemplate [" +" id=" +  id   +", pId=" +  pId   +", cname=" +  cname   +", hosId=" +  hosId   +", deptId=" +  deptId   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}