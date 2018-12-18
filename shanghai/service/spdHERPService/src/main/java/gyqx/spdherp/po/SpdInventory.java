
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
 * 盘点单主表
*/
@Table(name="spd_inventory")
public class SpdInventory implements IBean
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
	 * 医院名称
	*/
	@Size(max=36)
	@Column(name="hos_name")
	@Title("医院名称")
	private String hosName ;
	/**
	 * 操作人
	*/
	@Size(max=50)
	@Column(name="operationer")
	@Title("操作人")
	private String operationer ;
	/**
	 * 操作时间
	*/
	@Column(name="operationtime")
	@Title("操作时间")
	private Date operationtime ;
	/**
	 * 数据状态
	 * 10：提交 20：盘点后的状态
	*/
	@Size(max=50)
	@Column(name="state")
	@Title("数据状态")
	private String state ;
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
	public String getHosName ()
	{
		return hosName ;
	}
	
	public void setHosName (String hosName )
	{
		this.hosName = hosName;
	}
	public String getOperationer ()
	{
		return operationer ;
	}
	
	public void setOperationer (String operationer )
	{
		this.operationer = operationer;
	}
	public Date getOperationtime ()
	{
		return operationtime ;
	}
	
	public void setOperationtime (Date operationtime )
	{
		this.operationtime = operationtime;
	}
	public String getState ()
	{
		return state ;
	}
	
	public void setState (String state )
	{
		this.state = state;
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
		return "SpdInventory [" +" id=" +  id   +", hosId=" +  hosId   +", hosName=" +  hosName   +", operationer=" +  operationer   +", operationtime=" +  operationtime   +", state=" +  state   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}