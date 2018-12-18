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
 * 包日志表
*/
@Table(name="pkg_log")
public class PkgLog implements IBean
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
	 * 包码
	*/
	@Size(max=36)
	@Column(name="code")
	@Title("包码")
	private String code ;
	/**
	 * 包类型
	*/
	@Column(name="package_kind")
	@Title("包类型")
	private int packageKind ;
	/**
	 * 内容
	*/
	@Size(max=128)
	@Column(name="description")
	@Title("内容")
	private String description ;
	/**
	 * 相关单号
	*/
	@Size(max=36)
	@Column(name="bill_id")
	@Title("相关单号")
	private String billId ;
	/**
	 * 操作人
	*/
	@Size(max=36)
	@Column(name="filler")
	@Title("操作人")
	private String filler ;
	/**
	 * 操作时间
	*/
	@Column(name="fill_date")
	@Title("操作时间")
	private Date fillDate ;
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
	public String getCode ()
	{
		return code ;
	}
	
	public void setCode (String code )
	{
		this.code = code;
	}
	public int getPackageKind ()
	{
		return packageKind ;
	}
	
	public void setPackageKind (int packageKind )
	{
		this.packageKind = packageKind;
	}
	public String getDescription ()
	{
		return description ;
	}
	
	public void setDescription (String description )
	{
		this.description = description;
	}
	public String getBillId ()
	{
		return billId ;
	}
	
	public void setBillId (String billId )
	{
		this.billId = billId;
	}
	public String getFiller ()
	{
		return filler ;
	}
	
	public void setFiller (String filler )
	{
		this.filler = filler;
	}
	public Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (Date fillDate )
	{
		this.fillDate = fillDate;
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
		return "PkgLog [" +" id=" +  id   +", code=" +  code   +", packageKind=" +  packageKind   +", description=" +  description   +", billId=" +  billId   +", filler=" +  filler   +", fillDate=" +  fillDate   +", remark=" +  remark   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}