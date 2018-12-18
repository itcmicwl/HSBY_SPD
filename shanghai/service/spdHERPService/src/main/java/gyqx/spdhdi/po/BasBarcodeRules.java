
package gyqx.spdhdi.po;

import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * 条码规则信息
*/
@Table(name="bas_barcode_rules")
public class BasBarcodeRules implements IBean
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
	 * 机构ID
	*/
	@Size(max=36)
	@JoinColumn(name="company_id",table="bas_company_info",referencedColumnName="id")
	@Title("机构ID")
	private String companyId ;
	/**
	 * 机构类型
	 * 
	 * 机构类型
	*/
	@Size(max=36)
	@JoinColumn(name="company_kind",table="bas_company_info",referencedColumnName="kind")
	@Title("机构类型")
	private String companyKind ;
	/**
	 * 条码样例
	*/
	@Size(max=300)
	@Column(name="barcode")
	@Title("条码样例")
	private String barcode ;
	/**
	 * 条码长度
	*/
	@Column(name="barcode_len")
	@Title("条码长度")
	private int barcodeLen ;
	/**
	 * 条码前缀
	*/
	@Size(max=36)
	@Column(name="barleader")
	@Title("条码前缀")
	private String barleader ;
	/**
	 * 耗材码位置
	*/
	@Column(name="consumables_position")
	@Title("耗材码位置")
	private int consumablesPosition ;
	/**
	 * 耗材码长度
	*/
	@Column(name="consumables_len")
	@Title("耗材码长度")
	private int consumablesLen ;
	/**
	 * 是否主码
	*/
	@Size(max=1)
	@Column(name="is_main")
	@Title("是否主码")
	private String isMain ;
	/**
	 * 有效期位置
	*/
	@Column(name="period_position")
	@Title("有效期位置")
	private int periodPosition ;
	/**
	 * 有效期格式
	*/
	@Size(max=36)
	@Column(name="period_format")
	@Title("有效期格式")
	private String periodFormat ;
	/**
	 * 生产批号位置
	*/
	@Column(name="production_position")
	@Title("生产批号位置")
	private int productionPosition ;
	/**
	 * 生产批号长度
	*/
	@Column(name="production_len")
	@Title("生产批号长度")
	private int productionLen ;
	/**
	 * 特征1码位置
	*/
	@Column(name="sign1_position")
	@Title("特征1码位置")
	private int sign1Position ;
	/**
	 * 特征1码
	*/
	@Size(max=36)
	@Column(name="sign1")
	@Title("特征1码")
	private String sign1 ;
	/**
	 * 特征2码位置
	*/
	@Column(name="sign2_position")
	@Title("特征2码位置")
	private int sign2Position ;
	/**
	 * 特征2码
	*/
	@Size(max=36)
	@Column(name="sign2")
	@Title("特征2码")
	private String sign2 ;
	/**
	 * 跟踪码位置
	*/
	@Column(name="tracknum_position")
	@Title("跟踪码位置")
	private int tracknumPosition ;
	/**
	 * 跟踪码长度
	*/
	@Column(name="tracknum_len")
	@Title("跟踪码长度")
	private int tracknumLen ;
	/**
	 * 是否有效
	*/
	@Size(max=1)
	@Column(name="flag")
	@Title("是否有效")
	private String flag ;
	/**
	 * 操作员ID
	 * 新增或修改的操作员ID
	*/
	@Size(max=36)
	@Column(name="uxid")
	@Title("操作员ID")
	private String uxid ;
	/**
	 * 建档时间
	*/
	@Column(name="fill_date")
	@Title("建档时间")
	private java.util.Date fillDate ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 生产厂家
	*/
	@Size(max=36)
	@Column(name="mfrs_id")
	@Title("生产厂家")
	private String mfrsId ;
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
	public String getCompanyId ()
	{
		return companyId ;
	}
	
	public void setCompanyId (String companyId )
	{
		this.companyId = companyId;
	}
	public String getCompanyKind ()
	{
		return companyKind ;
	}
	
	public void setCompanyKind (String companyKind )
	{
		this.companyKind = companyKind;
	}
	public String getBarcode ()
	{
		return barcode ;
	}
	
	public void setBarcode (String barcode )
	{
		this.barcode = barcode;
	}
	public int getBarcodeLen ()
	{
		return barcodeLen ;
	}
	
	public void setBarcodeLen (int barcodeLen )
	{
		this.barcodeLen = barcodeLen;
	}
	public String getBarleader ()
	{
		return barleader ;
	}
	
	public void setBarleader (String barleader )
	{
		this.barleader = barleader;
	}
	public int getConsumablesPosition ()
	{
		return consumablesPosition ;
	}
	
	public void setConsumablesPosition (int consumablesPosition )
	{
		this.consumablesPosition = consumablesPosition;
	}
	public int getConsumablesLen ()
	{
		return consumablesLen ;
	}
	
	public void setConsumablesLen (int consumablesLen )
	{
		this.consumablesLen = consumablesLen;
	}
	public String getIsMain ()
	{
		return isMain ;
	}
	
	public void setIsMain (String isMain )
	{
		this.isMain = isMain;
	}
	public int getPeriodPosition ()
	{
		return periodPosition ;
	}
	
	public void setPeriodPosition (int periodPosition )
	{
		this.periodPosition = periodPosition;
	}
	public String getPeriodFormat ()
	{
		return periodFormat ;
	}
	
	public void setPeriodFormat (String periodFormat )
	{
		this.periodFormat = periodFormat;
	}
	public int getProductionPosition ()
	{
		return productionPosition ;
	}
	
	public void setProductionPosition (int productionPosition )
	{
		this.productionPosition = productionPosition;
	}
	public int getProductionLen ()
	{
		return productionLen ;
	}
	
	public void setProductionLen (int productionLen )
	{
		this.productionLen = productionLen;
	}
	public int getSign1Position ()
	{
		return sign1Position ;
	}
	
	public void setSign1Position (int sign1Position )
	{
		this.sign1Position = sign1Position;
	}
	public String getSign1 ()
	{
		return sign1 ;
	}
	
	public void setSign1 (String sign1 )
	{
		this.sign1 = sign1;
	}
	public int getSign2Position ()
	{
		return sign2Position ;
	}
	
	public void setSign2Position (int sign2Position )
	{
		this.sign2Position = sign2Position;
	}
	public String getSign2 ()
	{
		return sign2 ;
	}
	
	public void setSign2 (String sign2 )
	{
		this.sign2 = sign2;
	}
	public int getTracknumPosition ()
	{
		return tracknumPosition ;
	}
	
	public void setTracknumPosition (int tracknumPosition )
	{
		this.tracknumPosition = tracknumPosition;
	}
	public int getTracknumLen ()
	{
		return tracknumLen ;
	}
	
	public void setTracknumLen (int tracknumLen )
	{
		this.tracknumLen = tracknumLen;
	}
	public String getFlag ()
	{
		return flag ;
	}
	
	public void setFlag (String flag )
	{
		this.flag = flag;
	}
	public String getUxid ()
	{
		return uxid ;
	}
	
	public void setUxid (String uxid )
	{
		this.uxid = uxid;
	}
	public java.util.Date getFillDate ()
	{
		return fillDate ;
	}
	
	public void setFillDate (java.util.Date fillDate )
	{
		this.fillDate = fillDate;
	}
	public java.util.Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (java.util.Date lastUpdateDatetime )
	{
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	public String getMfrsId ()
	{
		return mfrsId ;
	}
	
	public void setMfrsId (String mfrsId )
	{
		this.mfrsId = mfrsId;
	}
	public int getVersion ()
	{
		return version ;
	}
	
	public void setVersion (int version )
	{
		this.version = version;
	}

	public String getUid ()
	{
		return uxid ;
	}
	
	public void setUid (String uxid )
	{
		this.uxid = uxid;
	}

	@Override
	public String toString() {
		return "BasBarcodeRules [" +" id=" +  id   +", companyId=" +  companyId   +", companyKind=" +  companyKind   +", barcode=" +  barcode   +", barcodeLen=" +  barcodeLen   +", barleader=" +  barleader   +", consumablesPosition=" +  consumablesPosition   +", consumablesLen=" +  consumablesLen   +", isMain=" +  isMain   +", periodPosition=" +  periodPosition   +", periodFormat=" +  periodFormat   +", productionPosition=" +  productionPosition   +", productionLen=" +  productionLen   +", sign1Position=" +  sign1Position   +", sign1=" +  sign1   +", sign2Position=" +  sign2Position   +", sign2=" +  sign2   +", tracknumPosition=" +  tracknumPosition   +", tracknumLen=" +  tracknumLen   +", flag=" +  flag   +", uxid=" +  uxid   +", fillDate=" +  fillDate   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", mfrsId=" +  mfrsId   +", version=" +  version   
			 + "]";
	}

}