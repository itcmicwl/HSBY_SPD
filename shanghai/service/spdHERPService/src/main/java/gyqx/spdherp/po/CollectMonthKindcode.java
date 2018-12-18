
package gyqx.spdherp.po;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import common.db.IBean;
import common.db.meta.Title;
/**
 * 医院高值耗材月消耗汇总
*/
@Table(name="collect_month_kindcode")
public class CollectMonthKindcode implements IBean
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
	 * 医院id
	*/
	@Size(max=36)
	@Column(name="hos_id")
	@Title("医院id")
	private String hosId ;
	
	/**
	 * 汇总月份，yyyymm格式
	*/
	@Size(max=36)
	@Column(name="collect_date")
	@Title("汇总月份")
	private String collectDate ;
	
	/**
	 * 期初金额
	*/
	@Size(max=18)
	@Column(name="balance_begin")
	@Title("期初金额")
	private BigDecimal balanceBegin ;
	
	/**
	 * 入库金额
	*/
	@Size(max=18)
	@Column(name="instoc_money")
	@Title("入库金额")
	private BigDecimal instocMoney ;
	
	/**
	 * 出库金额
	*/
	@Size(max=18)
	@Column(name="outstoc_money")
	@Title("出库金额")
	private BigDecimal outstocMoney ;
	
	/**
	 * 结存金额
	*/
	@Size(max=18)
	@Column(name="balance_end")
	@Title("结存金额")
	private BigDecimal balanceEnd ;
	
	/**
	 * 低值或高值，1:高值，0:低值
	*/
	@Size(max=36)
	@Column(name="goods_type")
	@Title("低值或高值")
	private String goodsType ;
	
	/**
	 * 医院产品分类ID
	*/
	@Size(max=36)
	@Column(name="kind_id")
	@Title("医院产品分类ID")
	private String kindId ;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHosId() {
		return hosId;
	}
	public void setHosId(String hosId) {
		this.hosId = hosId;
	}
	public String getCollectDate() {
		return collectDate;
	}
	public void setCollectDate(String collectDate) {
		this.collectDate = collectDate;
	}
	public BigDecimal getBalanceBegin() {
		return balanceBegin;
	}
	public void setBalanceBegin(BigDecimal balanceBegin) {
		this.balanceBegin = balanceBegin;
	}
	public BigDecimal getInstocMoney() {
		return instocMoney;
	}
	public void setInstocMoney(BigDecimal instocMoney) {
		this.instocMoney = instocMoney;
	}
	public BigDecimal getOutstocMoney() {
		return outstocMoney;
	}
	public void setOutstocMoney(BigDecimal outstocMoney) {
		this.outstocMoney = outstocMoney;
	}
	public BigDecimal getBalanceEnd() {
		return balanceEnd;
	}
	public void setBalanceEnd(BigDecimal balanceEnd) {
		this.balanceEnd = balanceEnd;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getKindId() {
		return kindId;
	}
	public void setKindId(String kindId) {
		this.kindId = kindId;
	}
	public java.util.Date getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}
	public void setLastUpdateDatetime(java.util.Date lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "CollectMonthKindcode [id=" + id + ", hosId=" + hosId + ", collectDate=" + collectDate
				+ ", balanceBegin=" + balanceBegin + ", instocMoney=" + instocMoney + ", outstocMoney=" + outstocMoney
				+ ", balanceEnd=" + balanceEnd + ", goodsType=" + goodsType + ", kindId=" + kindId
				+ ", lastUpdateDatetime=" + lastUpdateDatetime + ", version=" + version + "]";
	}
}