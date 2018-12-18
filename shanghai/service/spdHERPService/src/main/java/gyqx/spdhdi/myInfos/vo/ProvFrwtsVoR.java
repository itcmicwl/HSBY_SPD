
package gyqx.spdhdi.myInfos.vo;

import java.util.*;
import javax.persistence.Column;
import common.db.meta.Title;

public class ProvFrwtsVoR 
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
	 * 有效开始日期
	*/
	@Column(name="expdt_begin_date")
	@Title("有效开始日期")
	private Date expdtBeginDate ;
	/**
	 * 有效结束日期
	*/
	@Column(name="expdt_end_date")
	@Title("有效结束日期")
	private Date expdtEndDate ;
	/**
	 * 操作员ID
	 * 新增或修改的操作员ID
	*/
	@Column(name="uxid")
	@Title("操作员ID")
	private String uid ;
	/**
	 * 建档时间
	*/
	@Column(name="fill_date")
	@Title("建档时间")
	private Date fillDate ;
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
	public Date getExpdtBeginDate ()
	{
		return expdtBeginDate ;
	}
	
	public void setExpdtBeginDate (Date expdtBeginDate )
	{
		this.expdtBeginDate = expdtBeginDate;
	}
	public Date getExpdtEndDate ()
	{
		return expdtEndDate ;
	}
	
	public void setExpdtEndDate (Date expdtEndDate )
	{
		this.expdtEndDate = expdtEndDate;
	}
	public String getUid ()
	{
		return uid ;
	}
	
	public void setUid (String uid )
	{
		this.uid = uid;
	}
	public Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (Date fillDate )
	{
		this.fillDate = fillDate;
	}
	public Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (Date lastUpdateDatetime )
	{
		this.lastUpdateDatetime = lastUpdateDatetime;
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
		return "ProvFrwtsVoR ["  +" ename=" +  ename    +", id=" +  id    +", provId=" +  provId    +", hosId=" +  hosId    +", wtrId=" +  wtrId    +", wtrMoveNum=" +  wtrMoveNum    +", filePathWts=" +  filePathWts    +", filePathWtr=" +  filePathWtr    +", expdtBeginDate=" +  expdtBeginDate    +", expdtEndDate=" +  expdtEndDate    +", uid=" +  uid    +", fillDate=" +  fillDate    +", lastUpdateDatetime=" +  lastUpdateDatetime    +", version=" +  version   
			 + "]";
	}

}