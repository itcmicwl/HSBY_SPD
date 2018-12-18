
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
 * 产品信息接口历史数据
*/
@Table(name="pass_goods_info_sheet")
public class PassGoodsInfoSheet implements IBean
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
	 * SPD系统标识
	 * SPD系统标识
	*/
	@Size(max=50)
	@Column(name="spd_code")
	@Title("SPD系统标识")
	private String spdCode ;
	/**
	 * 配送机构系统标识
	*/
	@Size(max=50)
	@Column(name="prov_sys_code")
	@Title("配送机构系统标识")
	private String provSysCode ;
	/**
	 * 配送机构ERP编号
	 * ERP系统标识
	*/
	@Size(max=50)
	@Column(name="prov_erp_code")
	@Title("配送机构ERP编号")
	private String provErpCode ;
	/**
	 * 配送机构名称
	*/
	@Size(max=100)
	@Column(name="prov_name")
	@Title("配送机构名称")
	private String provName ;
	/**
	 * ERP商品编号
	*/
	@Size(max=50)
	@Column(name="goods_erp_code")
	@Title("ERP商品编号")
	private String goodsErpCode ;
	/**
	 * ERP商品名称
	*/
	@Size(max=100)
	@Column(name="goods_erp_name")
	@Title("ERP商品名称")
	private String goodsErpName ;
	/**
	 * 通用名
	*/
	@Size(max=100)
	@Column(name="goods_name")
	@Title("通用名")
	private String goodsName ;
	/**
	 * 简称
	*/
	@Size(max=100)
	@Column(name="goods_simple_name")
	@Title("简称")
	private String goodsSimpleName ;
	/**
	 * 注册商标
	*/
	@Size(max=100)
	@Column(name="goods_zcsb")
	@Title("注册商标")
	private String goodsZcsb ;
	/**
	 * 规格
	*/
	@Size(max=50)
	@Column(name="goods_gg")
	@Title("规格")
	private String goodsGg ;
	/**
	 * 产地
	*/
	@Size(max=100)
	@Column(name="made")
	@Title("产地")
	private String made ;
	/**
	 * 生产厂商
	*/
	@Size(max=100)
	@Column(name="mfrs_name")
	@Title("生产厂商")
	private String mfrsName ;
	/**
	 * 单位
	 * 计量单位
	*/
	@Size(max=50)
	@Column(name="unit")
	@Title("单位")
	private String unit ;
	/**
	 * 包装
	*/
	@Size(max=12)
	@Column(name="package_num")
	@Title("包装")
	private java.math.BigDecimal packageNum ;
	/**
	 * 中包装
	*/
	@Size(max=12)
	@Column(name="midd_package_num")
	@Title("中包装")
	private java.math.BigDecimal middPackageNum ;
	/**
	 * 条形码
	*/
	@Size(max=50)
	@Column(name="barcode")
	@Title("条形码")
	private String barcode ;
	/**
	 * 批准文号
	*/
	@Size(max=50)
	@Column(name="pzwh")
	@Title("批准文号")
	private String pzwh ;
	/**
	 * 注册证号
	 * 将取出的的多行E8ZZBM用逗号分隔拼在一起
	*/
	@Size(max=50)
	@Column(name="certificate_code")
	@Title("注册证号")
	private String certificateCode ;
	/**
	 * 注册证有效期至
	*/
	@Column(name="expdt_end_date")
	@Title("注册证有效期至")
	private java.util.Date expdtEndDate ;
	/**
	 * 类别属性
	 * 医药/医材/其他
	*/
	@Size(max=50)
	@Column(name="lbsx")
	@Title("类别属性")
	private String lbsx ;
	/**
	 * 类别
	 * 中药/中成药/西药/保健品/血液制品/化学试剂/日用品/化妆品/手术器械/诊察器械/治疗与康复设置/中医器械/仪器设置、部件及器具/检验分析仪器/植入材料和人工器官/医用材料/介入材料/软件/其他
	*/
	@Size(max=50)
	@Column(name="lb")
	@Title("类别")
	private String lb ;
	/**
	 * 剂型
	 * 片剂/注射剂/胶囊剂/软膏剂/栓剂/颗粒剂/滴剂/丸剂/气雾剂/搽剂/洗剂/散剂/混悬剂/喷雾剂/乳剂/溶液剂/
	*/
	@Size(max=50)
	@Column(name="jx")
	@Title("剂型")
	private String jx ;
	/**
	 * 储存条件
	 * 冷藏/常温/阴凉、避光/密封/密封保存/阴凉/遮光、密闭、凉暗处/遮光、密闭、在阴凉处保存/密封、干燥处保存/避光、干燥处保存/密封、防潮/密封、阴凉干燥处保存/遮光、密闭保存
	*/
	@Size(max=50)
	@Column(name="store_condition")
	@Title("储存条件")
	private String storeCondition ;
	/**
	 * 启用唯一码管理
	 * 是否禁用，1代表启用，0代表禁用
	*/
	@Column(name="unique_kind")
	@Title("启用唯一码管理")
	private int uniqueKind ;
	/**
	 * 其他分类1
	*/
	@Size(max=50)
	@Column(name="other_code1")
	@Title("其他分类1")
	private String otherCode1 ;
	/**
	 * 其他分类2
	*/
	@Size(max=50)
	@Column(name="other_code2")
	@Title("其他分类2")
	private String otherCode2 ;
	/**
	 * 其它分类3
	*/
	@Size(max=50)
	@Column(name="other_code3")
	@Title("其它分类3")
	private String otherCode3 ;
	/**
	 * 备注
	*/
	@Size(max=200)
	@Column(name="remark")
	@Title("备注")
	private String remark ;
	/**
	 * 封存
	 * 是否禁用，1代表禁用，0代表可用
	*/
	@Column(name="save")
	@Title("封存")
	private int save ;
	/**
	 * 封存时间
	*/
	@Column(name="save_date")
	@Title("封存时间")
	private java.util.Date saveDate ;
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
	public String getSpdCode ()
	{
		return spdCode ;
	}
	
	public void setSpdCode (String spdCode )
	{
		this.spdCode = spdCode;
	}
	public String getProvSysCode ()
	{
		return provSysCode ;
	}
	
	public void setProvSysCode (String provSysCode )
	{
		this.provSysCode = provSysCode;
	}
	public String getProvErpCode ()
	{
		return provErpCode ;
	}
	
	public void setProvErpCode (String provErpCode )
	{
		this.provErpCode = provErpCode;
	}
	public String getProvName ()
	{
		return provName ;
	}
	
	public void setProvName (String provName )
	{
		this.provName = provName;
	}
	public String getGoodsErpCode ()
	{
		return goodsErpCode ;
	}
	
	public void setGoodsErpCode (String goodsErpCode )
	{
		this.goodsErpCode = goodsErpCode;
	}
	public String getGoodsErpName ()
	{
		return goodsErpName ;
	}
	
	public void setGoodsErpName (String goodsErpName )
	{
		this.goodsErpName = goodsErpName;
	}
	public String getGoodsName ()
	{
		return goodsName ;
	}
	
	public void setGoodsName (String goodsName )
	{
		this.goodsName = goodsName;
	}
	public String getGoodsSimpleName ()
	{
		return goodsSimpleName ;
	}
	
	public void setGoodsSimpleName (String goodsSimpleName )
	{
		this.goodsSimpleName = goodsSimpleName;
	}
	public String getGoodsZcsb ()
	{
		return goodsZcsb ;
	}
	
	public void setGoodsZcsb (String goodsZcsb )
	{
		this.goodsZcsb = goodsZcsb;
	}
	public String getGoodsGg ()
	{
		return goodsGg ;
	}
	
	public void setGoodsGg (String goodsGg )
	{
		this.goodsGg = goodsGg;
	}
	public String getMade ()
	{
		return made ;
	}
	
	public void setMade (String made )
	{
		this.made = made;
	}
	public String getMfrsName ()
	{
		return mfrsName ;
	}
	
	public void setMfrsName (String mfrsName )
	{
		this.mfrsName = mfrsName;
	}
	public String getUnit ()
	{
		return unit ;
	}
	
	public void setUnit (String unit )
	{
		this.unit = unit;
	}
	public java.math.BigDecimal getPackageNum ()
	{
		return packageNum ;
	}
	
	public void setPackageNum (java.math.BigDecimal packageNum )
	{
		this.packageNum = packageNum;
	}
	public java.math.BigDecimal getMiddPackageNum ()
	{
		return middPackageNum ;
	}
	
	public void setMiddPackageNum (java.math.BigDecimal middPackageNum )
	{
		this.middPackageNum = middPackageNum;
	}
	public String getBarcode ()
	{
		return barcode ;
	}
	
	public void setBarcode (String barcode )
	{
		this.barcode = barcode;
	}
	public String getPzwh ()
	{
		return pzwh ;
	}
	
	public void setPzwh (String pzwh )
	{
		this.pzwh = pzwh;
	}
	public String getCertificateCode ()
	{
		return certificateCode ;
	}
	
	public void setCertificateCode (String certificateCode )
	{
		this.certificateCode = certificateCode;
	}
	public java.util.Date getExpdtEndDate ()
	{
		return expdtEndDate ;
	}
	
	public void setExpdtEndDate (java.util.Date expdtEndDate )
	{
		this.expdtEndDate = expdtEndDate;
	}
	public String getLbsx ()
	{
		return lbsx ;
	}
	
	public void setLbsx (String lbsx )
	{
		this.lbsx = lbsx;
	}
	public String getLb ()
	{
		return lb ;
	}
	
	public void setLb (String lb )
	{
		this.lb = lb;
	}
	public String getJx ()
	{
		return jx ;
	}
	
	public void setJx (String jx )
	{
		this.jx = jx;
	}
	public String getStoreCondition ()
	{
		return storeCondition ;
	}
	
	public void setStoreCondition (String storeCondition )
	{
		this.storeCondition = storeCondition;
	}
	public int getUniqueKind ()
	{
		return uniqueKind ;
	}
	
	public void setUniqueKind (int uniqueKind )
	{
		this.uniqueKind = uniqueKind;
	}
	public String getOtherCode1 ()
	{
		return otherCode1 ;
	}
	
	public void setOtherCode1 (String otherCode1 )
	{
		this.otherCode1 = otherCode1;
	}
	public String getOtherCode2 ()
	{
		return otherCode2 ;
	}
	
	public void setOtherCode2 (String otherCode2 )
	{
		this.otherCode2 = otherCode2;
	}
	public String getOtherCode3 ()
	{
		return otherCode3 ;
	}
	
	public void setOtherCode3 (String otherCode3 )
	{
		this.otherCode3 = otherCode3;
	}
	public String getRemark ()
	{
		return remark ;
	}
	
	public void setRemark (String remark )
	{
		this.remark = remark;
	}
	public int getSave ()
	{
		return save ;
	}
	
	public void setSave (int save )
	{
		this.save = save;
	}
	public java.util.Date getSaveDate ()
	{
		return saveDate ;
	}
	
	public void setSaveDate (java.util.Date saveDate )
	{
		this.saveDate = saveDate;
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
		return "PassGoodsInfoSheet [" +" id=" +  id   +", spdCode=" +  spdCode   +", provSysCode=" +  provSysCode   +", provErpCode=" +  provErpCode   +", provName=" +  provName   +", goodsErpCode=" +  goodsErpCode   +", goodsErpName=" +  goodsErpName   +", goodsName=" +  goodsName   +", goodsSimpleName=" +  goodsSimpleName   +", goodsZcsb=" +  goodsZcsb   +", goodsGg=" +  goodsGg   +", made=" +  made   +", mfrsName=" +  mfrsName   +", unit=" +  unit   +", packageNum=" +  packageNum   +", middPackageNum=" +  middPackageNum   +", barcode=" +  barcode   +", pzwh=" +  pzwh   +", certificateCode=" +  certificateCode   +", expdtEndDate=" +  expdtEndDate   +", lbsx=" +  lbsx   +", lb=" +  lb   +", jx=" +  jx   +", storeCondition=" +  storeCondition   +", uniqueKind=" +  uniqueKind   +", otherCode1=" +  otherCode1   +", otherCode2=" +  otherCode2   +", otherCode3=" +  otherCode3   +", remark=" +  remark   +", save=" +  save   +", saveDate=" +  saveDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}