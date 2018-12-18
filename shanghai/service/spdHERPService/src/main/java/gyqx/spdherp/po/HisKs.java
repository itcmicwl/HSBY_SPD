
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
 * HIS科室部门
*/
@Table(name="his_ks")
public class HisKs implements IBean
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
	 * 医院编码
	*/
	@Size(max=36)
	@Column(name="hosp_code")
	@Title("医院编码")
	private String hospCode ;
	/**
	 * 科室编码
	*/
	@Size(max=36)
	@Column(name="dept_code")
	@Title("科室编码")
	private String deptCode ;
	/**
	 * 科室名称
	*/
	@Size(max=36)
	@Column(name="dept_name")
	@Title("科室名称")
	private String deptName ;
	/**
	 * 类别
	*/
	@Size(max=36)
	@Column(name="kind")
	@Title("类别")
	private String kind ;
	/**
	 * 类别名称
	*/
	@Size(max=36)
	@Column(name="kind_name")
	@Title("类别名称")
	private String kindName ;
	/**
	 * 属性名称
	*/
	@Size(max=36)
	@Column(name="attr_name")
	@Title("属性名称")
	private String attrName ;
	/**
	 * 属性编码
	*/
	@Size(max=36)
	@Column(name="attr_code")
	@Title("属性编码")
	private String attrCode ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private Date lastUpdateDatetime ;
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
	public String getHospCode ()
	{
		return hospCode ;
	}
	
	public void setHospCode (String hospCode )
	{
		this.hospCode = hospCode;
	}
	public String getDeptCode ()
	{
		return deptCode ;
	}
	
	public void setDeptCode (String deptCode )
	{
		this.deptCode = deptCode;
	}
	public String getDeptName ()
	{
		return deptName ;
	}
	
	public void setDeptName (String deptName )
	{
		this.deptName = deptName;
	}
	public String getKind ()
	{
		return kind ;
	}
	
	public void setKind (String kind )
	{
		this.kind = kind;
	}
	public String getKindName ()
	{
		return kindName ;
	}
	
	public void setKindName (String kindName )
	{
		this.kindName = kindName;
	}
	public String getAttrName ()
	{
		return attrName ;
	}
	
	public void setAttrName (String attrName )
	{
		this.attrName = attrName;
	}
	public String getAttrCode ()
	{
		return attrCode ;
	}
	
	public void setAttrCode (String attrCode )
	{
		this.attrCode = attrCode;
	}
	public Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (Date lastUpdateDatetime )
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
		return "HisKs [" +" id=" +  id   +", hospCode=" +  hospCode   +", deptCode=" +  deptCode   +", deptName=" +  deptName   +", kind=" +  kind   +", kindName=" +  kindName   +", attrName=" +  attrName   +", attrCode=" +  attrCode   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}