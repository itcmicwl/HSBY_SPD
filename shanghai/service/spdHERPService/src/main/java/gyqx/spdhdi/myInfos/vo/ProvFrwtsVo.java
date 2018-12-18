
package gyqx.spdhdi.myInfos.vo;

import java.util.*;
import javax.persistence.Column;
import common.db.meta.Title;

public class ProvFrwtsVo 
{
	/**
	 * 用户名称
	 * 在机构中显示的名称
	*/
	@Column(name="ename")
	@Title("用户名称")
	private String ename ;
	/**
	 * ID
	*/
	@Column(name="id")
	@Title("ID")
	private String id ;
	/**
	 * 供应商ID
	*/
	@Column(name="prov_id")
	@Title("供应商ID")
	private String provId ;
	/**
	 * 医院ID
	*/
	@Column(name="hos_id")
	@Title("医院ID")
	private String hosId ;
	/**
	 * 委托人
	*/
	@Column(name="wtr_id")
	@Title("委托人")
	private String wtrId ;
	/**
	 * 委托人手机
	*/
	@Column(name="wtr_move_num")
	@Title("委托人手机")
	private String wtrMoveNum ;
	/**
	 * 委托书附件路径
	*/
	@Column(name="file_path_wts")
	@Title("委托书附件路径")
	private String filePathWts ;
	/**
	 * 委托人身份证路径
	*/
	@Column(name="file_path_wtr")
	@Title("委托人身份证路径")
	private String filePathWtr ;
	/**
	 * expdtBeginDate
	*/
	@Column(name="expdt_begin_date")
	@Title("expdtBeginDate$begin")
	private Date expdtBeginDate$begin ;
	/**
	 * expdtBeginDate
	*/
	@Column(name="expdt_begin_date")
	@Title("expdtBeginDate$end")
	private Date expdtBeginDate$end ;
	/**
	 * expdtEndDate
	*/
	@Column(name="expdt_end_date")
	@Title("expdtEndDate$begin")
	private Date expdtEndDate$begin ;
	/**
	 * expdtEndDate
	*/
	@Column(name="expdt_end_date")
	@Title("expdtEndDate$end")
	private Date expdtEndDate$end ;
	/**
	 * 操作员ID
	 * 新增或修改的操作员ID
	*/
	@Column(name="uxid")
	@Title("操作员ID")
	private String uid ;
	/**
	 * fillDate
	*/
	@Column(name="fill_date")
	@Title("fillDate$begin")
	private Date fillDate$begin ;
	/**
	 * fillDate
	*/
	@Column(name="fill_date")
	@Title("fillDate$end")
	private Date fillDate$end ;
	/**
	 * lastUpdateDatetime
	*/
	@Column(name="last_update_datetime")
	@Title("lastUpdateDatetime$begin")
	private Date lastUpdateDatetime$begin ;
	/**
	 * lastUpdateDatetime
	*/
	@Column(name="last_update_datetime")
	@Title("lastUpdateDatetime$end")
	private Date lastUpdateDatetime$end ;
	/**
	 * 数据版本
	*/
	@Column(name="version")
	@Title("数据版本")
	private Integer version ;

	public String getEname ()
	{
		return ename ;
	}
	
	public void setEname (String ename )
	{
		this.ename = ename;
	}
	public String getId ()
	{
		return id ;
	}
	
	public void setId (String id )
	{
		this.id = id;
	}
	public String getProvId ()
	{
		return provId ;
	}
	
	public void setProvId (String provId )
	{
		this.provId = provId;
	}
	public String getHosId ()
	{
		return hosId ;
	}
	
	public void setHosId (String hosId )
	{
		this.hosId = hosId;
	}
	public String getWtrId ()
	{
		return wtrId ;
	}
	
	public void setWtrId (String wtrId )
	{
		this.wtrId = wtrId;
	}
	public String getWtrMoveNum ()
	{
		return wtrMoveNum ;
	}
	
	public void setWtrMoveNum (String wtrMoveNum )
	{
		this.wtrMoveNum = wtrMoveNum;
	}
	public String getFilePathWts ()
	{
		return filePathWts ;
	}
	
	public void setFilePathWts (String filePathWts )
	{
		this.filePathWts = filePathWts;
	}
	public String getFilePathWtr ()
	{
		return filePathWtr ;
	}
	
	public void setFilePathWtr (String filePathWtr )
	{
		this.filePathWtr = filePathWtr;
	}
	public Date getExpdtBeginDate$begin ()
	{
		return expdtBeginDate$begin ;
	}
	
	public void setExpdtBeginDate$begin (Date expdtBeginDate$begin )
	{
		this.expdtBeginDate$begin = expdtBeginDate$begin;
	}
	public Date getExpdtBeginDate$end ()
	{
		return expdtBeginDate$end ;
	}
	
	public void setExpdtBeginDate$end (Date expdtBeginDate$end )
	{
		this.expdtBeginDate$end = expdtBeginDate$end;
	}
	public Date getExpdtEndDate$begin ()
	{
		return expdtEndDate$begin ;
	}
	
	public void setExpdtEndDate$begin (Date expdtEndDate$begin )
	{
		this.expdtEndDate$begin = expdtEndDate$begin;
	}
	public Date getExpdtEndDate$end ()
	{
		return expdtEndDate$end ;
	}
	
	public void setExpdtEndDate$end (Date expdtEndDate$end )
	{
		this.expdtEndDate$end = expdtEndDate$end;
	}
	public String getUid ()
	{
		return uid ;
	}
	
	public void setUid (String uid )
	{
		this.uid = uid;
	}
	public Date getFillDate$begin ()
	{
		return fillDate$begin ;
	}
	
	public void setFillDate$begin (Date fillDate$begin )
	{
		this.fillDate$begin = fillDate$begin;
	}
	public Date getFillDate$end ()
	{
		return fillDate$end ;
	}
	
	public void setFillDate$end (Date fillDate$end )
	{
		this.fillDate$end = fillDate$end;
	}
	public Date getLastUpdateDatetime$begin ()
	{
		return lastUpdateDatetime$begin ;
	}
	
	public void setLastUpdateDatetime$begin (Date lastUpdateDatetime$begin )
	{
		this.lastUpdateDatetime$begin = lastUpdateDatetime$begin;
	}
	public Date getLastUpdateDatetime$end ()
	{
		return lastUpdateDatetime$end ;
	}
	
	public void setLastUpdateDatetime$end (Date lastUpdateDatetime$end )
	{
		this.lastUpdateDatetime$end = lastUpdateDatetime$end;
	}
	public Integer getVersion ()
	{
		return version ;
	}
	
	public void setVersion (Integer version )
	{
		this.version = version;
	}
	@Override
	public String toString() {
		return "ProvFrwtsVo ["  +" ename=" +  ename    +", id=" +  id    +", provId=" +  provId    +", hosId=" +  hosId    +", wtrId=" +  wtrId    +", wtrMoveNum=" +  wtrMoveNum    +", filePathWts=" +  filePathWts    +", filePathWtr=" +  filePathWtr    +", expdtBeginDate$begin=" +  expdtBeginDate$begin    +", expdtBeginDate$end=" +  expdtBeginDate$end    +", expdtEndDate$begin=" +  expdtEndDate$begin    +", expdtEndDate$end=" +  expdtEndDate$end    +", uid=" +  uid    +", fillDate$begin=" +  fillDate$begin    +", fillDate$end=" +  fillDate$end    +", lastUpdateDatetime$begin=" +  lastUpdateDatetime$begin    +", lastUpdateDatetime$end=" +  lastUpdateDatetime$end    +", version=" +  version   
			 + "]";
	}

}