
package gyqx.spdherp.po;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * 手术包配置表
*/
@Table(name="surgery_pkg_def")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurgeryPkgDef implements IBean
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
	@Column(name="kind_id")
	@Title("上级ID")
	private String kindId ;
	/**
	 * 手术包名
	*/
	@Size(max=256)
	@Column(name="cname")
	@Title("手术包名")
	private String cname ;
	/**
	 * 手术包英文名
	*/
	@Size(max=256)
	@Column(name="ename")
	@Title("手术包英文名")
	private String ename ;
	/**
	 * 删除标识
	*/
	@Column(name="flag")
	@Title("删除标识")
	private int flag ;

	/**
	 * 备注
	*/
	@Size(max=256)
	@Column(name="remark")
	@Title("备注")
	private String remark ;
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
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
	}
	public String getKindId ()
	{
		return kindId ;
	}
	
	public void setKindId (String kindId )
	{
		this.kindId = kindId;
	}
	public String getCname ()
	{
		return cname ;
	}
	
	public void setCname (String cname )
	{
		this.cname = cname;
	}
	public String getEname ()
	{
		return ename ;
	}
	
	public void setEname (String ename )
	{
		this.ename = ename;
	}
	public int getFlag ()
	{
		return flag ;
	}
	
	public void setFlag (int flag )
	{
		this.flag = flag;
	}

	public String getRemark ()
	{
		return remark ;
	}
	
	public void setRemark (String remark )
	{
		this.remark = remark;
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
		return "SurgeryPkgDef [" +" id=" +  id   +", hosId=" +  hosId   +", kindId=" +  kindId   +", cname=" +  cname   +", ename=" +  ename   +", flag=" +  flag   +", remark=" +  remark   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version
			 + "]";
	}

}