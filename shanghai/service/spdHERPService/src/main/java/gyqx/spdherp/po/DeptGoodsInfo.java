
package gyqx.spdherp.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 科室商品信息
*/
@Table(name="dept_goods_info")
public class DeptGoodsInfo implements IBean
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
	 * 医疗机构ID
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("医疗机构ID")
	private String hosId ;
	/**
	 * 医疗机构名称
	*/
	@Size(max=300)
	@Column(name="hos_name")
	@Title("医疗机构名称")
	private String hosName ;
	/**
	 * 在供应商ERP中编码(供应商)
	*/
	@Size(max=36)
	@Column(name="erp_code")
	@Title("在供应商ERP中编码(供应商)")
	private String erpCode ;
	/**
	 * 科室ID
	*/
	@Size(max=36)
	@JoinColumn(name="dept_id",table="sys_org",referencedColumnName="id")
	@Title("科室ID")
	private String deptId ;
	/**
	 * 科室名称
	*/
	@Size(max=300)
	@Column(name="dept_name")
	@Title("科室名称")
	private String deptName ;
	/**
	 * 产品ID
	*/
	@Size(max=36)
	@Column(name="goods_id")
	@Title("产品ID")
	private String goodsId ;
	/**
	 * 产品名称
	*/
	@Size(max=300)
	@Column(name="goods_name")
	@Title("产品名称")
	private String goodsName ;
	/**
	 * 规格
	*/
	@Size(max=150)
	@Column(name="goods_gg")
	@Title("规格")
	private String goodsGg ;
	/**
	 * 单位
	*/
	@Size(max=36)
	@Column(name="unit")
	@Title("单位")
	private String unit ;
	/**
	 * 生产厂家ID
	*/
	@Size(max=36)
	@Column(name="mfrs_id")
	@Title("生产厂家ID")
	private String mfrsId ;
	/**
	 * 生产厂家名称
	*/
	@Size(max=300)
	@Column(name="mfrs_name")
	@Title("生产厂家名称")
	private String mfrsName ;
	/**
	 * 生产国
	*/
	@Size(max=100)
	@Column(name="made")
	@Title("生产国")
	private String made ;
	/**
	 * 库存上限
	*/
	@Column(name="stoc_upper")
	@Title("库存上限")
	private java.math.BigDecimal stocUpper ;
	/**
	 * 库存下限
	*/
	@Column(name="stoc_lower")
	@Title("库存下限")
	private java.math.BigDecimal stocLower ;
	/**
	 * 是否定数包管理
	*/
	@Size(max=1)
	@Column(name="is_packet")
	@Title("是否定数包管理")
	private String isPacket ;
	/**
	 * 是否唯一码管理
	*/
	@Size(max=1)
	@Column(name="is_unique")
	@Title("是否唯一码管理")
	private String isUnique ;
	/**
	 * 包装数量
	*/
	@Column(name="packet_code")
	@Title("包装数量")
	private java.math.BigDecimal packetCode ;
	/**
	 * 定数包混批
	*/
	@Size(max=1)
	@Column(name="is_mix")
	@Title("定数包混批")
	private String isMix ;
	/**
	 * 采购模式
	*/
	@Size(max=2)
	@Column(name="pur_mode")
	@Title("采购模式")
	private String purMode ;
	/**
	 * 对应库房
	*/
	@Size(max=36)
	@Column(name="stoc_id")
	@Title("对应库房")
	private String stocId ;
	/**
	 * 最小请购数量
	*/
	@Column(name="req_num")
	@Title("最小请购数量")
	private java.math.BigDecimal reqNum ;
	/**
	 * 医保编码
	*/
	@Size(max=36)
	@Column(name="mi_code")
	@Title("医保编码")
	private String miCode ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 品牌
	*/
	@Size(max=128)
	@Column(name="brand")
	@Title("品牌")
	private String brand ;
	/**
	 * 业务员ID
	*/
	@Size(max=36)
	@Column(name="saleman_id")
	@Title("业务员ID")
	private String salemanId ;
	/**
	 * 业务员ERP中编码(供应商)
	*/
	@Size(max=36)
	@Column(name="saleman_code")
	@Title("业务员ERP中编码(供应商)")
	private String salemanCode ;
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
	public String getErpCode ()
	{
		return erpCode ;
	}
	
	public void setErpCode (String erpCode )
	{
		this.erpCode = erpCode;
	}
	public String getDeptId ()
	{
		return deptId ;
	}
	
	public void setDeptId (String deptId )
	{
		this.deptId = deptId;
	}
	public String getDeptName ()
	{
		return deptName ;
	}
	
	public void setDeptName (String deptName )
	{
		this.deptName = deptName;
	}
	public String getGoodsId ()
	{
		return goodsId ;
	}
	
	public void setGoodsId (String goodsId )
	{
		this.goodsId = goodsId;
	}
	public String getGoodsName ()
	{
		return goodsName ;
	}
	
	public void setGoodsName (String goodsName )
	{
		this.goodsName = goodsName;
	}
	public String getGoodsGg ()
	{
		return goodsGg ;
	}
	
	public void setGoodsGg (String goodsGg )
	{
		this.goodsGg = goodsGg;
	}
	public String getUnit ()
	{
		return unit ;
	}
	
	public void setUnit (String unit )
	{
		this.unit = unit;
	}
	public String getMfrsId ()
	{
		return mfrsId ;
	}
	
	public void setMfrsId (String mfrsId )
	{
		this.mfrsId = mfrsId;
	}
	public String getMfrsName ()
	{
		return mfrsName ;
	}
	
	public void setMfrsName (String mfrsName )
	{
		this.mfrsName = mfrsName;
	}
	public String getMade ()
	{
		return made ;
	}
	
	public void setMade (String made )
	{
		this.made = made;
	}
	public java.math.BigDecimal getStocUpper ()
	{
		return stocUpper ;
	}
	
	public void setStocUpper (java.math.BigDecimal stocUpper )
	{
		this.stocUpper = stocUpper;
	}
	public java.math.BigDecimal getStocLower ()
	{
		return stocLower ;
	}
	
	public void setStocLower (java.math.BigDecimal stocLower )
	{
		this.stocLower = stocLower;
	}
	public String getIsPacket ()
	{
		return isPacket ;
	}
	
	public void setIsPacket (String isPacket )
	{
		this.isPacket = isPacket;
	}
	public String getIsUnique ()
	{
		return isUnique ;
	}
	
	public void setIsUnique (String isUnique )
	{
		this.isUnique = isUnique;
	}
	public java.math.BigDecimal getPacketCode ()
	{
		return packetCode ;
	}
	
	public void setPacketCode (java.math.BigDecimal packetCode )
	{
		this.packetCode = packetCode;
	}
	public String getIsMix ()
	{
		return isMix ;
	}
	
	public void setIsMix (String isMix )
	{
		this.isMix = isMix;
	}
	public String getPurMode ()
	{
		return purMode ;
	}
	
	public void setPurMode (String purMode )
	{
		this.purMode = purMode;
	}
	public String getStocId ()
	{
		return stocId ;
	}
	
	public void setStocId (String stocId )
	{
		this.stocId = stocId;
	}
	public java.math.BigDecimal getReqNum ()
	{
		return reqNum ;
	}
	
	public void setReqNum (java.math.BigDecimal reqNum )
	{
		this.reqNum = reqNum;
	}
	public String getMiCode ()
	{
		return miCode ;
	}
	
	public void setMiCode (String miCode )
	{
		this.miCode = miCode;
	}
	public java.util.Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (java.util.Date lastUpdateDatetime )
	{
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	public String getBrand ()
	{
		return brand ;
	}
	
	public void setBrand (String brand )
	{
		this.brand = brand;
	}
	public String getSalemanId ()
	{
		return salemanId ;
	}
	
	public void setSalemanId (String salemanId )
	{
		this.salemanId = salemanId;
	}
	public String getSalemanCode ()
	{
		return salemanCode ;
	}
	
	public void setSalemanCode (String salemanCode )
	{
		this.salemanCode = salemanCode;
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
		return "DeptGoodsInfo [" +" id=" +  id   +", hosId=" +  hosId   +", hosName=" +  hosName   +", erpCode=" +  erpCode   +", deptId=" +  deptId   +", deptName=" +  deptName   +", goodsId=" +  goodsId   +", goodsName=" +  goodsName   +", goodsGg=" +  goodsGg   +", unit=" +  unit   +", mfrsId=" +  mfrsId   +", mfrsName=" +  mfrsName   +", made=" +  made   +", stocUpper=" +  stocUpper   +", stocLower=" +  stocLower   +", isPacket=" +  isPacket   +", isUnique=" +  isUnique   +", packetCode=" +  packetCode   +", isMix=" +  isMix   +", purMode=" +  purMode   +", stocId=" +  stocId   +", reqNum=" +  reqNum   +", miCode=" +  miCode   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", brand=" +  brand   +", salemanId=" +  salemanId   +", salemanCode=" +  salemanCode   +", version=" +  version   
			 + "]";
	}

}