package gyqx.spdhdi.orderMgr.vo;

import java.io.Serializable;


/**
 * 请购配送单温湿度明细
 */
public class DistrBillHumitureListVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6038528358694070472L;
	private String id ;
	private String pid ;
	/**
	 * 箱号
	*/
	private String boxCode ;
	/**
	 * 配送单行号
	*/
	private int distrRowNumber ;
	/**
	 * 配送商产品ID
	*/
	private String provGoodsId ;
	private String goodsName;
	/**
	 * 产品批次
	*/
	private String batchCode ;
	/**
	 * 唯一码
	*/
	private String uniqueCode ;
	/**
	 * 温度
	*/
	private String temprature ;
	/**
	 * 湿度
	*/
	private String humidity ;
	/**
	 * 测量时间
	*/
	private java.util.Date measureDate ;
	/**
	 * 数据来源
	*/
	private int sourceData ;
	/**
	 * 数据版本
	*/
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
	public int getVersion ()
	{
		return version ;
	}
	
	public void setVersion (int version )
	{
		this.version = version;
	}
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Override
	public String toString() {
		return "DistrBillHumitureList [" +" id=" +  id   +", pid=" +  pid   +", boxCode=" +  boxCode   +", distrRowNumber=" +  distrRowNumber   +", provGoodsId=" +  provGoodsId   +", batchCode=" +  batchCode   +", uniqueCode=" +  uniqueCode   +", temprature=" +  temprature   +", humidity=" +  humidity   +", measureDate=" +  measureDate   +", sourceData=" +  sourceData   +", version=" +  version   
			 + "]";
	}

}
