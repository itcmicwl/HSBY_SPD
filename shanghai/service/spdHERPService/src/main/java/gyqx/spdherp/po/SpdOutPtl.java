
package gyqx.spdherp.po;

import java.math.BigDecimal;
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
 * 电子标签中间表
*/
@Table(name="spd_out_ptl")
public class SpdOutPtl implements IBean
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
	 * 任务流水（行号）
	*/
	@Column(name="serialno")
	@Title("任务流水（行号）")
	private int serialno ;
	/**
	 * 订单编号
	*/
	@Size(max=50)
	@Column(name="orderid")
	@Title("订单编号")
	private String orderid ;
	/**
	 * 货位编号
	*/
	@Size(max=20)
	@Column(name="location")
	@Title("货位编号")
	private String location ;
	/**
	 * 商品名称
	*/
	@Size(max=500)
	@Column(name="name")
	@Title("商品名称")
	private String name ;
	/**
	 * 商品编号
	*/
	@Size(max=20)
	@Column(name="barcode")
	@Title("商品编号")
	private String barcode ;
	/**
	 * 商品规格
	*/
	@Size(max=500)
	@Column(name="specification")
	@Title("商品规格")
	private String specification ;
	/**
	 * 商品批号
	*/
	@Size(max=50)
	@Column(name="batchnumber")
	@Title("商品批号")
	private String batchnumber ;
	/**
	 * 厂家
	*/
	@Size(max=50)
	@Column(name="manufacturer")
	@Title("厂家")
	private String manufacturer ;
	/**
	 * 数量
	*/
	@Column(name="quantity")
	@Title("数量")
	private BigDecimal quantity ;
	/**
	 * 包装单位
	*/
	@Size(max=50)
	@Column(name="unit")
	@Title("包装单位")
	private String unit ;
	/**
	 * 实际拣货数量
	*/
	@Column(name="truequantity")
	@Title("实际拣货数量")
	private Integer truequantity ;
	/**
	 * 数据状态
	*/
	@Size(max=50)
	@Column(name="state")
	@Title("数据状态")
	private String state ;
	/**
	 * 创建时间
	*/
	@Column(name="downdate")
	@Title("创建时间")
	private Date downdate ;
	/**
	 * 拍灯时间
	*/
	@Column(name="operationtime")
	@Title("拍灯时间")
	private Date operationtime ;
	/**
	 * 任务类型
	 * 任务类型（拣货任务；盘点任务）
	*/
	@Size(max=50)
	@Column(name="tasktype")
	@Title("任务类型")
	private String tasktype ;
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
	public int getSerialno ()
	{
		return serialno ;
	}
	
	public void setSerialno (int serialno )
	{
		this.serialno = serialno;
	}
	public String getOrderid ()
	{
		return orderid ;
	}
	
	public void setOrderid (String orderid )
	{
		this.orderid = orderid;
	}
	public String getLocation ()
	{
		return location ;
	}
	
	public void setLocation (String location )
	{
		this.location = location;
	}
	public String getName ()
	{
		return name ;
	}
	
	public void setName (String name )
	{
		this.name = name;
	}
	public String getBarcode ()
	{
		return barcode ;
	}
	
	public void setBarcode (String barcode )
	{
		this.barcode = barcode;
	}
	public String getSpecification ()
	{
		return specification ;
	}
	
	public void setSpecification (String specification )
	{
		this.specification = specification;
	}
	public String getBatchnumber ()
	{
		return batchnumber ;
	}
	
	public void setBatchnumber (String batchnumber )
	{
		this.batchnumber = batchnumber;
	}
	public String getManufacturer ()
	{
		return manufacturer ;
	}
	
	public void setManufacturer (String manufacturer )
	{
		this.manufacturer = manufacturer;
	}
	public BigDecimal getQuantity ()
	{
		return quantity ;
	}
	
	public void setQuantity (BigDecimal quantity )
	{
		this.quantity = quantity;
	}
	public String getUnit ()
	{
		return unit ;
	}
	
	public void setUnit (String unit )
	{
		this.unit = unit;
	}
	public Integer getTruequantity ()
	{
		return truequantity ;
	}
	
	public void setTruequantity (Integer truequantity )
	{
		this.truequantity = truequantity;
	}
	public String getState ()
	{
		return state ;
	}
	
	public void setState (String state )
	{
		this.state = state;
	}
	public Date getDowndate ()
	{
		return downdate ;
	}
	
	public void setDowndate (Date downdate )
	{
		this.downdate = downdate;
	}
	public Date getOperationtime ()
	{
		return operationtime ;
	}
	
	public void setOperationtime (Date operationtime )
	{
		this.operationtime = operationtime;
	}
	public String getTasktype ()
	{
		return tasktype ;
	}
	
	public void setTasktype (String tasktype )
	{
		this.tasktype = tasktype;
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
		return "SpdOutPtl [" +" id=" +  id   +", serialno=" +  serialno   +", orderid=" +  orderid   +", location=" +  location   +", name=" +  name   +", barcode=" +  barcode   +", specification=" +  specification   +", batchnumber=" +  batchnumber   +", manufacturer=" +  manufacturer   +", quantity=" +  quantity   +", unit=" +  unit   +", truequantity=" +  truequantity   +", state=" +  state   +", downdate=" +  downdate   +", operationtime=" +  operationtime   +", tasktype=" +  tasktype   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}