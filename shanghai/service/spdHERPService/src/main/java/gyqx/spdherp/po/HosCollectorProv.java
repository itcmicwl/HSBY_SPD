
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
 * 配送商对应合作商信息
*/
@Table(name="hos_collector_prov")
public class HosCollectorProv implements IBean
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
	 * 集配商ID
	*/
	@Size(max=36)
	@JoinColumn(name="collector_id",table="hos_collector",referencedColumnName="prov_id")
	@Title("集配商ID")
	private String collectorId ;
	/**
	 * 合作商ID
	*/
	@Size(max=36)
	@Column(name="prov_id")
	@Title("合作商ID")
	private String provId ;
	/**
	 * 配送关系类型
	*/
	@Column(name="kind")
	@Title("配送关系类型")
	private int kind ;
	/**
	 * 是否启用二级供应商
	 * 0--不启用，1、启用
	*/
	@Column(name="is_use_subprov")
	@Title("是否启用二级供应商")
	private int isUseSubprov ;
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
	public String getCollectorId ()
	{
		return collectorId ;
	}
	
	public void setCollectorId (String collectorId )
	{
		this.collectorId = collectorId;
	}
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public int getKind ()
	{
		return kind ;
	}
	
	public void setKind (int kind )
	{
		this.kind = kind;
	}
	public int getIsUseSubprov ()
	{
		return isUseSubprov ;
	}
	
	public void setIsUseSubprov (int isUseSubprov )
	{
		this.isUseSubprov = isUseSubprov;
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
		return "HosCollectorProv [" +" id=" +  id   +", hosId=" +  hosId   +", collectorId=" +  collectorId   +", provId=" +  provId   +", kind=" +  kind   +", isUseSubprov=" +  isUseSubprov   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}