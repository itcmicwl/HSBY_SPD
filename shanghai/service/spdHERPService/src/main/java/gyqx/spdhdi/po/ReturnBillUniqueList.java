
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 科室请退单唯一码明细
*/
@Table(name="return_bill_unique_list")
public class ReturnBillUniqueList implements IBean
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
	@JoinColumn(name="pid",table="return_bill_list",referencedColumnName="id")
	@Title("pid")
	private String pid ;
	/**
	 * 请退单号
	*/
	@Size(max=36)
	@Column(name="return_bill_id")
	@Title("请退单号")
	private String returnBillId ;
	/**
	 * 请退单行号
	*/
	@JoinColumn(name="return_row_number",table="return_bill_list",referencedColumnName="row_number")
	@Title("请退单行号")
	private int returnRowNumber ;
	/**
	 * 医院产品ID
	*/
	@Size(max=36)
	@JoinColumn(name="hos_goods_id",table="hos_goods_info",referencedColumnName="id")
	@Title("医院产品ID")
	private String hosGoodsId ;
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
	public String getReturnBillId ()
	{
		return returnBillId ;
	}
	
	public void setReturnBillId (String returnBillId )
	{
		this.returnBillId = returnBillId;
	}
	public int getReturnRowNumber ()
	{
		return returnRowNumber ;
	}
	
	public void setReturnRowNumber (int returnRowNumber )
	{
		this.returnRowNumber = returnRowNumber;
	}
	public String getHosGoodsId ()
	{
		return hosGoodsId ;
	}
	
	public void setHosGoodsId (String hosGoodsId )
	{
		this.hosGoodsId = hosGoodsId;
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
		return "ReturnBillUniqueList [" +" id=" +  id   +", pid=" +  pid   +", returnBillId=" +  returnBillId   +", returnRowNumber=" +  returnRowNumber   +", hosGoodsId=" +  hosGoodsId   +", batchCode=" +  batchCode   +", uniqueCode=" +  uniqueCode   +", sourceData=" +  sourceData   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}