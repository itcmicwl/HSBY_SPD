
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 货位信息
*/
@Table(name="stock_shelf")
public class StockShelf implements IBean
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
	 * 仓库ID
	*/
	@Size(max=36)
	@Column(name="prov_id")
	@Title("仓库ID")
	private String provId ;
	/**
	 * 区位编码
	*/
	@Size(max=36)
	@Column(name="area_code")
	@Title("区位编码")
	private String areaCode ;
	/**
	 * 区位名称
	*/
	@Size(max=100)
	@Column(name="area_name")
	@Title("区位名称")
	private String areaName ;
	/**
	 * 货位编码
	*/
	@Size(max=36)
	@Column(name="shelf_code")
	@Title("货位编码")
	private String shelfCode ;
	/**
	 * 货位名称
	*/
	@Size(max=100)
	@Column(name="shelf_name")
	@Title("货位名称")
	private String shelfName ;
	/**
	 * 类型
	 * 10--区位，20--货位
	*/
	@Column(name="kind")
	@Title("类型")
	private int kind ;
	/**
	 * 上级ID
	*/
	@Size(max=36)
	@Column(name="pid")
	@Title("上级ID")
	private String pid ;
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
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public String getAreaCode ()
	{
		return areaCode ;
	}
	
	public void setAreaCode (String areaCode )
	{
		this.areaCode = areaCode;
	}
	public String getAreaName ()
	{
		return areaName ;
	}
	
	public void setAreaName (String areaName )
	{
		this.areaName = areaName;
	}
	public String getShelfCode ()
	{
		return shelfCode ;
	}
	
	public void setShelfCode (String shelfCode )
	{
		this.shelfCode = shelfCode;
	}
	public String getShelfName ()
	{
		return shelfName ;
	}
	
	public void setShelfName (String shelfName )
	{
		this.shelfName = shelfName;
	}
	public int getKind ()
	{
		return kind ;
	}
	
	public void setKind (int kind )
	{
		this.kind = kind;
	}
	public String getPid ()
	{
		return pid ;
	}
	
	public void setPid (String pid )
	{
		this.pid = pid;
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
		return "StockShelf [" +" id=" +  id   +", hosId=" +  hosId   +", provId=" +  provId   +", areaCode=" +  areaCode   +", areaName=" +  areaName   +", shelfCode=" +  shelfCode   +", shelfName=" +  shelfName   +", kind=" +  kind   +", pid=" +  pid   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}