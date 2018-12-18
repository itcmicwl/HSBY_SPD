
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
 * 科室请领关系
*/
@Table(name="hos_dept_pur_stock")
public class HosDeptPurStock implements IBean
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
	 * 科室ID
	*/
	@Size(max=36)
	@Column(name="dept_id")
	@Title("科室ID")
	private String deptId ;
	/**
	 * 库房ID
	*/
	@Size(max=36)
	@Column(name="stock_id")
	@Title("库房ID")
	private String stockId ;
	/**
	 * 是否默认
	*/
	@Size(max=1)
	@Column(name="is_default")
	@Title("是否默认")
	private String isDefault ;
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
	public String getDeptId ()
	{
		return deptId ;
	}
	
	public void setDeptId (String deptId )
	{
		this.deptId = deptId;
	}
	public String getStockId ()
	{
		return stockId ;
	}
	
	public void setStockId (String stockId )
	{
		this.stockId = stockId;
	}
	public String getIsDefault ()
	{
		return isDefault ;
	}
	
	public void setIsDefault (String isDefault )
	{
		this.isDefault = isDefault;
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
		return "HosDeptPurStock [" +" id=" +  id   +", hosId=" +  hosId   +", deptId=" +  deptId   +", stockId=" +  stockId   +", isDefault=" +  isDefault   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}