
package gyqx.spdherp.applyMgr.vo;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import common.db.meta.Title;
import gyqx.spdherp.po.DeptGoodsInfo;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.math.BigDecimal;
@JsonIgnoreProperties
public class DeptGoodsStVo extends DeptGoodsInfo
{

//手术包请购添加字段start
	private String surId;
	private String surName;
	private java.math.BigDecimal needSurPacketQty ;
//手术包请购添加字段end
	//当前操作人
	private String oppertor ;

	
	public String getOppertor() {
		return oppertor;
	}

	public void setOppertor(String oppertor) {
		this.oppertor = oppertor;
	}

	// 科室模板Id
	@Column(name="tempId")
	@Title("科室模板")
	private String tempId ;

	/**
	 * needQty 请购数量
	*/
	@Column(name="needQty")
	@Title("请购数量")
	private java.math.BigDecimal needQty ;
	/**
	 * qty 库存数量
	*/
	@Column(name="qty")
	@Title("库存数量")
	private java.math.BigDecimal qty ;
	
	/**
	 * code 商品编码
	*/
	@Column(name="code")
	@Title("商品编码")
	private String code ;

	/**
	 * provId 供应商ID
	*/
	@Column(name="provId")
	@Title("供应商ID")
	private String provId ;

	/**
	 * provName 供应商名
	*/
	@Column(name="provName")
	@Title("供应商名")
	private String provName ;
	
	/**
	 * privce 
	*/
	@Column(name="privce")
	@Title("单价")
	private java.math.BigDecimal price ;
	
	/**
	 * je 
	*/
	@Column(name="je")
	@Title("库存总价")
	private java.math.BigDecimal je ;

	// 俗称
	private String generalName;

	// 描述
	private String goodsDesc;

	// 商品是否存在照片
	private Boolean imgAvailable;

	// 件包数 hos_goods_info.field_code2
	private String packetSpec;

	public java.math.BigDecimal getNeedQty() {
		return needQty;
	}

	public java.math.BigDecimal getQty() {
		return qty;
	}

	public String getCode() {
		return code;
	}

	public String getProvId() {
		return provId;
	}

	public String getProvName() {
		return provName;
	}

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public java.math.BigDecimal getJe() {
		return je;
	}

	public void setNeedQty(java.math.BigDecimal needQty) {
		this.needQty = needQty;
	}

	public void setQty(java.math.BigDecimal qty) {
		this.qty = qty;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	public void setPrivce(java.math.BigDecimal price) {
		this.price = price;
	}

	public void setJe(java.math.BigDecimal je) {
		this.je = je;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}

    public String getGeneralName() {
        return generalName;
    }

    public void setGeneralName(String generalName) {
        this.generalName = generalName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

	public Boolean getImgAvailable() {
		return imgAvailable;
	}

	public void setImgAvailable(Boolean imgAvailable) {
		this.imgAvailable = imgAvailable;
	}

    public String getPacketSpec() {
        return packetSpec;
    }

    public void setPacketSpec(String packetSpec) {
        this.packetSpec = packetSpec;
    }

	public String getSurId() {
		return surId;
	}

	public void setSurId(String surId) {
		this.surId = surId;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public BigDecimal getNeedSurPacketQty() {
		return needSurPacketQty;
	}

	public void setNeedSurPacketQty(BigDecimal needSurPacketQty) {
		this.needSurPacketQty = needSurPacketQty;
	}
}