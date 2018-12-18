
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 请购配送单温湿度明细
*/
@Table(name="distr_bill_humiture_list")
public class DistrBillHumitureList implements IBean
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
	 * pid
	*/
	@Size(max=36)
	@JoinColumn(name="pid",table="distr_bill_list",referencedColumnName="id")
	@Title("pid")
	private String pid ;
	/**
	 * 箱号
	*/
	@Size(max=36)
	@Column(name="box_code")
	@Title("箱号")
	private String boxCode ;
	/**
	 * 配送单行号
	*/
	@JoinColumn(name="distr_row_number",table="distr_bill_list",referencedColumnName="row_number")
	@Title("配送单行号")
	private int distrRowNumber ;
	/**
	 * 配送商产品ID
	*/
	@Size(max=36)
	@JoinColumn(name="prov_goods_id",table="distr_bill_list",referencedColumnName="id")
	@Title("配送商产品ID")
	private String provGoodsId ;
	/**
	 * 产品批次
	*/
	@Size(max=128)
	@Column(name="batch_code")
	@Title("产品批次")
	private String batchCode ;
	/**
	 * 唯一码
	*/
	@Size(max=128)
	@Column(name="unique_code")
	@Title("唯一码")
	private String uniqueCode ;
	/**
	 * 温度
	*/
	@Size(max=128)
	@Column(name="temprature")
	@Title("温度")
	private String temprature ;
	/**
	 * 湿度
	*/
	@Size(max=128)
	@Column(name="humidity")
	@Title("湿度")
	private String humidity ;
	/**
	 * 测量时间
	*/
	@Column(name="measure_date")
	@Title("测量时间")
	private java.util.Date measureDate ;
	/**
	 * 数据来源
	*/
	@Column(name="source_data")
	@Title("数据来源")
	private int sourceData ;
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
	public String getPid ()
	{
		return pid ;
	}
	
	public void setPid (String pid )
	{
		this.pid = pid;
	}
	public String getBoxCode ()
	{
		return boxCode ;
	}
	
	public void setBoxCode (String boxCode )
	{
		this.boxCode = boxCode;
	}
	public int getDistrRowNumber ()
	{
		return distrRowNumber ;
	}
	
	public void setDistrRowNumber (int distrRowNumber )
	{
		this.distrRowNumber = distrRowNumber;
	}
	public String getProvGoodsId ()
	{
		return provGoodsId ;
	}
	
	public void setProvGoodsId (String provGoodsId )
	{
		this.provGoodsId = provGoodsId;
	}
	public String getBatchCode ()
	{
		return batchCode ;
	}
	
	public void setBatchCode (String batchCode )
	{
		this.batchCode = batchCode;
	}
	public String getUniqueCode ()
	{
		return uniqueCode ;
	}
	
	public void setUniqueCode (String uniqueCode )
	{
		this.uniqueCode = uniqueCode;
	}
	public String getTemprature ()
	{
		return temprature ;
	}
	
	public void setTemprature (String temprature )
	{
		this.temprature = temprature;
	}
	public String getHumidity ()
	{
		return humidity ;
	}
	
	public void setHumidity (String humidity )
	{
		this.humidity = humidity;
	}
	public java.util.Date getMeasureDate ()
	{
		return measureDate ;
	}
	
	public void setMeasureDate (java.util.Date measureDate )
	{
		this.measureDate = measureDate;
	}
	public int getSourceData ()
	{
		return sourceData ;
	}
	
	public void setSourceData (int sourceData )
	{
		this.sourceData = sourceData;
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
		return "DistrBillHumitureList [" +" id=" +  id   +", pid=" +  pid   +", boxCode=" +  boxCode   +", distrRowNumber=" +  distrRowNumber   +", provGoodsId=" +  provGoodsId   +", batchCode=" +  batchCode   +", uniqueCode=" +  uniqueCode   +", temprature=" +  temprature   +", humidity=" +  humidity   +", measureDate=" +  measureDate   +", sourceData=" +  sourceData   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}