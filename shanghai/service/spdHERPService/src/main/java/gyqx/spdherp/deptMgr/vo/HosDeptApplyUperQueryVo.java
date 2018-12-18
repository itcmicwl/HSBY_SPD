
package gyqx.spdherp.deptMgr.vo;

import java.util.*;
import javax.persistence.Column;
import common.db.meta.Title;

public class HosDeptApplyUperQueryVo 
{
	/**
	 * ID
	*/
	@Column(name="id")
	@Title("ID")
	private String id ;
	/**
	 * 医疗机构ID
	*/
	@Column(name="hos_id")
	@Title("医疗机构ID")
	private String hosId ;
	/**
	 * 医疗机构名称
	*/
	@Column(name="hos_name")
	@Title("医疗机构名称")
	private String hosName ;
	/**
	 * 科室ID
	*/
	@Column(name="dept_id")
	@Title("科室ID")
	private String deptId ;
	/**
	 * 科室名称
	*/
	@Column(name="dept_name")
	@Title("科室名称")
	private String deptName ;
	/**
	 * 产品ID
	*/
	@Column(name="goods_id")
	@Title("产品ID")
	private String goodsId ;
	/**
	 * 产品名称
	*/
	@Column(name="goods_name")
	@Title("产品名称")
	private String goodsName ;
	/**
	 * 规格
	*/
	@Column(name="goods_gg")
	@Title("规格")
	private String goodsGg ;
	/**
	 * 单位
	*/
	@Column(name="unit")
	@Title("单位")
	private String unit ;
	/**
	 * 生产厂家ID
	*/
	@Column(name="mfrs_id")
	@Title("生产厂家ID")
	private String mfrsId ;
	/**
	 * 生产厂家名称
	*/
	@Column(name="mfrs_name")
	@Title("生产厂家名称")
	private String mfrsName ;
	/**
	 * 生产国
	*/
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
	@Column(name="is_packet")
	@Title("是否定数包管理")
	private String isPacket ;
	/**
	 * 是否唯一码管理
	*/
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
	@Column(name="is_mix")
	@Title("定数包混批")
	private String isMix ;
	/**
	 * 采购模式
	*/
	@Column(name="pur_mode")
	@Title("采购模式")
	private String purMode ;
	/**
	 * 对应库房
	*/
	@Column(name="stoc_id")
	@Title("对应库房")
	private String stocId ;
	/**
	 * 数据版本
	*/
	@Column(name="version")
	@Title("数据版本")
	private Integer version ;
	/**
	 * ID
	*/
	@Column(name="hosDeptApplyUpperId")
	@Title("ID")
	private String hosDeptApplyUpperId ;
	/**
	 * 一月数量
	*/
	@Column(name="m1_qty")
	@Title("一月数量")
	private java.math.BigDecimal m1Qty ;
	/**
	 * 二月数量
	*/
	@Column(name="m2_qty")
	@Title("二月数量")
	private java.math.BigDecimal m2Qty ;
	/**
	 * 三月数量
	*/
	@Column(name="m3_qty")
	@Title("三月数量")
	private java.math.BigDecimal m3Qty ;
	/**
	 * 四月数量
	*/
	@Column(name="m4_qty")
	@Title("四月数量")
	private java.math.BigDecimal m4Qty ;
	/**
	 * 五月数量
	*/
	@Column(name="m5_qty")
	@Title("五月数量")
	private java.math.BigDecimal m5Qty ;
	/**
	 * 六月数量
	*/
	@Column(name="m6_qty")
	@Title("六月数量")
	private java.math.BigDecimal m6Qty ;
	/**
	 * 七月数量
	*/
	@Column(name="m7_qty")
	@Title("七月数量")
	private java.math.BigDecimal m7Qty ;
	/**
	 * 八月数量
	*/
	@Column(name="m8_qty")
	@Title("八月数量")
	private java.math.BigDecimal m8Qty ;
	/**
	 * 九月数量
	*/
	@Column(name="m9_qty")
	@Title("九月数量")
	private java.math.BigDecimal m9Qty ;
	/**
	 * 十月数量
	*/
	@Column(name="m10_qty")
	@Title("十月数量")
	private java.math.BigDecimal m10Qty ;
	/**
	 * 十一月数量
	*/
	@Column(name="m11_qty")
	@Title("十一月数量")
	private java.math.BigDecimal m11Qty ;
	/**
	 * 十二月数量
	*/
	@Column(name="m12_qty")
	@Title("十二月数量")
	private java.math.BigDecimal m12Qty ;
	/**
	 * 数据版本
	*/
	@Column(name="hosDeptApplyUpperVersion")
	@Title("数据版本")
	private Integer hosDeptApplyUpperVersion ;

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
	public Integer getVersion ()
	{
		return version ;
	}
	
	public void setVersion (Integer version )
	{
		this.version = version;
	}
	public String getHosDeptApplyUpperId ()
	{
		return hosDeptApplyUpperId ;
	}
	
	public void setHosDeptApplyUpperId (String hosDeptApplyUpperId )
	{
		this.hosDeptApplyUpperId = hosDeptApplyUpperId;
	}
	public java.math.BigDecimal getM1Qty ()
	{
		return m1Qty ;
	}
	
	public void setM1Qty (java.math.BigDecimal m1Qty )
	{
		this.m1Qty = m1Qty;
	}
	public java.math.BigDecimal getM2Qty ()
	{
		return m2Qty ;
	}
	
	public void setM2Qty (java.math.BigDecimal m2Qty )
	{
		this.m2Qty = m2Qty;
	}
	public java.math.BigDecimal getM3Qty ()
	{
		return m3Qty ;
	}
	
	public void setM3Qty (java.math.BigDecimal m3Qty )
	{
		this.m3Qty = m3Qty;
	}
	public java.math.BigDecimal getM4Qty ()
	{
		return m4Qty ;
	}
	
	public void setM4Qty (java.math.BigDecimal m4Qty )
	{
		this.m4Qty = m4Qty;
	}
	public java.math.BigDecimal getM5Qty ()
	{
		return m5Qty ;
	}
	
	public void setM5Qty (java.math.BigDecimal m5Qty )
	{
		this.m5Qty = m5Qty;
	}
	public java.math.BigDecimal getM6Qty ()
	{
		return m6Qty ;
	}
	
	public void setM6Qty (java.math.BigDecimal m6Qty )
	{
		this.m6Qty = m6Qty;
	}
	public java.math.BigDecimal getM7Qty ()
	{
		return m7Qty ;
	}
	
	public void setM7Qty (java.math.BigDecimal m7Qty )
	{
		this.m7Qty = m7Qty;
	}
	public java.math.BigDecimal getM8Qty ()
	{
		return m8Qty ;
	}
	
	public void setM8Qty (java.math.BigDecimal m8Qty )
	{
		this.m8Qty = m8Qty;
	}
	public java.math.BigDecimal getM9Qty ()
	{
		return m9Qty ;
	}
	
	public void setM9Qty (java.math.BigDecimal m9Qty )
	{
		this.m9Qty = m9Qty;
	}
	public java.math.BigDecimal getM10Qty ()
	{
		return m10Qty ;
	}
	
	public void setM10Qty (java.math.BigDecimal m10Qty )
	{
		this.m10Qty = m10Qty;
	}
	public java.math.BigDecimal getM11Qty ()
	{
		return m11Qty ;
	}
	
	public void setM11Qty (java.math.BigDecimal m11Qty )
	{
		this.m11Qty = m11Qty;
	}
	public java.math.BigDecimal getM12Qty ()
	{
		return m12Qty ;
	}
	
	public void setM12Qty (java.math.BigDecimal m12Qty )
	{
		this.m12Qty = m12Qty;
	}
	public Integer getHosDeptApplyUpperVersion ()
	{
		return hosDeptApplyUpperVersion ;
	}
	
	public void setHosDeptApplyUpperVersion (Integer hosDeptApplyUpperVersion )
	{
		this.hosDeptApplyUpperVersion = hosDeptApplyUpperVersion;
	}
	@Override
	public String toString() {
		return "HosDeptApplyUperQueryVo ["  +" id=" +  id    +", hosId=" +  hosId    +", hosName=" +  hosName    +", deptId=" +  deptId    +", deptName=" +  deptName    +", goodsId=" +  goodsId    +", goodsName=" +  goodsName    +", goodsGg=" +  goodsGg    +", unit=" +  unit    +", mfrsId=" +  mfrsId    +", mfrsName=" +  mfrsName    +", made=" +  made    +", stocUpper=" +  stocUpper    +", stocLower=" +  stocLower    +", isPacket=" +  isPacket    +", isUnique=" +  isUnique    +", packetCode=" +  packetCode    +", isMix=" +  isMix    +", purMode=" +  purMode    +", stocId=" +  stocId    +", version=" +  version    +", hosDeptApplyUpperId=" +  hosDeptApplyUpperId    +", m1Qty=" +  m1Qty    +", m2Qty=" +  m2Qty    +", m3Qty=" +  m3Qty    +", m4Qty=" +  m4Qty    +", m5Qty=" +  m5Qty    +", m6Qty=" +  m6Qty    +", m7Qty=" +  m7Qty    +", m8Qty=" +  m8Qty    +", m9Qty=" +  m9Qty    +", m10Qty=" +  m10Qty    +", m11Qty=" +  m11Qty    +", m12Qty=" +  m12Qty    +", hosDeptApplyUpperVersion=" +  hosDeptApplyUpperVersion   
			 + "]";
	}

}