package gyqx.spdhdi.po;
import java.math.BigDecimal;
import common.db.IBean;
import common.db.meta.Title;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/**
 * (ProHosGoodsinfo)实体类
 *
 * @author moonbless
 * @since 2018-11-28 10:08:42
 */
@Table(name="v_pro_hos_goodsinfo")
public class ProHosGoodsinfo {
    //ID
    @Size(max=36)
    @Column(name="id")
    @Title("ID")    
     private String id;
    //供应商ID
    @Size(max=36)
    @Column(name="prov_id")
    @Title("供应商ID")    
     private String provId;
    //医院ID
    @Size(max=36)
    @Column(name="hos_id")
    @Title("医院ID")    
     private String hosId;
    //供应商产品ID
    @Size(max=36)
    @Column(name="prov_goodsid")
    @Title("供应商产品ID")    
     private String provGoodsid;
    //对应医院产品编码
    @Size(max=36)
    @Column(name="his_code")
    @Title("对应医院产品编码")    
     private String hisCode;
    //对应医院产品单位
    @Size(max=36)
    @Column(name="his_unit")
    @Title("对应医院产品单位")    
     private String hisUnit;
    //单位比率
    @Size(max=10)
    @Column(name="unit_rate")
    @Title("单位比率")    
     private BigDecimal unitRate;
    
    @Size(max=500)
    @Column(name="goods_gg")
        
     private String goodsGg;
    //产地
    @Size(max=36)
    @Column(name="made")
    @Title("产地")    
     private String made;
    //生产厂商
    @Size(max=36)
    @Column(name="mfrs_id")
    @Title("生产厂商")    
     private String mfrsId;
    //erp编码
    @Size(max=36)
    @Column(name="erp_code")
    @Title("erp编码")    
     private String erpCode;
    //阳光采购编码
    @Size(max=36)
    @Column(name="hit_code")
    @Title("阳光采购编码")    
     private String hitCode;
    //阳光采购价
    @Size(max=18)
    @Column(name="hit_price")
    @Title("阳光采购价")    
     private BigDecimal hitPrice;
    //包装
    @Size(max=18)
    @Column(name="packeage")
    @Title("包装")    
     private BigDecimal packeage;
    //统一码
    @Size(max=128)
    @Column(name="master_code")
    @Title("统一码")    
     private String masterCode;
    //68分类
    @Size(max=36)
    @Column(name="kind_68code")
    @Title("68分类")    
     private String kind_68code;
    //拼音码
    @Size(max=72)
    @Column(name="short_pinyin")
    @Title("拼音码")    
     private String shortPinyin;
    
    @Size(max=500)
    @Column(name="goods_name")
        
     private String goodsName;
    //编号
    @Size(max=36)
    @Column(name="code")
    @Title("编号")    
     private String code;
    //单位
    @Size(max=36)
    @Column(name="unit")
    @Title("单位")    
     private String unit;
    
    @Size(max=300)
    @Column(name="prov_name")
        
     private String provName;
    
    @Size(max=300)
    @Column(name="hos_name")
        
     private String hosName;
    
    @Size(max=300)
    @Column(name="mfrs_name")
        
     private String mfrsName;
    
    @Size(max=500)
    @Column(name="his_name")
        
     private String hisName;
    //单价
    @Size(max=18)
    @Column(name="price")
    @Title("单价")    
     private BigDecimal price;
    //税率
    @Size(max=18)
    @Column(name="tax_rate")
    @Title("税率")    
     private BigDecimal taxRate;
    
    @Size(max=18)
    @Column(name="sale_price")
        
     private BigDecimal salePrice;
    //内部ERP编码
    @Size(max=36)
    @Column(name="prov_erp_code")
    @Title("内部ERP编码")    
     private String provErpCode;
    //内部ERP编码
    @Size(max=36)
    @Column(name="hos_erp_code")
    @Title("内部ERP编码")    
     private String hosErpCode;
    
    @Size(max=18)
    @Column(name="pur_price")
        
     private BigDecimal purPrice;
    //唯一码管理策略
    @Size(max=10)
    @Column(name="unique_code_strategy")
    @Title("唯一码管理策略")    
     private String uniqueCodeStrategy;
    //厂家出厂编码
    @Size(max=128)
    @Column(name="mfrs_goods_code")
    @Title("厂家出厂编码")    
     private String mfrsGoodsCode;
    //标志
    @Size(max=1)
    @Column(name="flag")
    @Title("标志")    
     private String flag;
    //医保编号
    @Size(max=36)
    @Column(name="mi_code")
    @Title("医保编号")    
     private String miCode;
    //采购模式
    @Size(max=2)
    @Column(name="pur_mode")
    @Title("采购模式")    
     private String purMode;
    //品牌
    @Size(max=128)
    @Column(name="brand")
    @Title("品牌")    
     private String brand;
    //储运条件
    @Size(max=36)
    @Column(name="st_condition")
    @Title("储运条件")    
     private String stCondition;
    
    @Size(max=500)
    @Column(name="certificate_code")
        
     private String certificateCode;
    
     public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
        
   
     public String getProvId() {
        return provId;
    }
    public void setProvId(String provId) {
        this.provId = provId;
    }
        
   
     public String getHosId() {
        return hosId;
    }
    public void setHosId(String hosId) {
        this.hosId = hosId;
    }
        
   
     public String getProvGoodsid() {
        return provGoodsid;
    }
    public void setProvGoodsid(String provGoodsid) {
        this.provGoodsid = provGoodsid;
    }
        
   
     public String getHisCode() {
        return hisCode;
    }
    public void setHisCode(String hisCode) {
        this.hisCode = hisCode;
    }
        
   
     public String getHisUnit() {
        return hisUnit;
    }
    public void setHisUnit(String hisUnit) {
        this.hisUnit = hisUnit;
    }
        
   
     public BigDecimal getUnitRate() {
        return unitRate;
    }
    public void setUnitRate(BigDecimal unitRate) {
        this.unitRate = unitRate;
    }
        
   
     public String getGoodsGg() {
        return goodsGg;
    }
    public void setGoodsGg(String goodsGg) {
        this.goodsGg = goodsGg;
    }
        
   
     public String getMade() {
        return made;
    }
    public void setMade(String made) {
        this.made = made;
    }
        
   
     public String getMfrsId() {
        return mfrsId;
    }
    public void setMfrsId(String mfrsId) {
        this.mfrsId = mfrsId;
    }
        
   
     public String getErpCode() {
        return erpCode;
    }
    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }
        
   
     public String getHitCode() {
        return hitCode;
    }
    public void setHitCode(String hitCode) {
        this.hitCode = hitCode;
    }
        
   
     public BigDecimal getHitPrice() {
        return hitPrice;
    }
    public void setHitPrice(BigDecimal hitPrice) {
        this.hitPrice = hitPrice;
    }
        
   
     public BigDecimal getPackeage() {
        return packeage;
    }
    public void setPackeage(BigDecimal packeage) {
        this.packeage = packeage;
    }
        
   
     public String getMasterCode() {
        return masterCode;
    }
    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }
        
   
     public String getKind_68code() {
        return kind_68code;
    }
    public void setKind_68code(String kind_68code) {
        this.kind_68code = kind_68code;
    }
        
   
     public String getShortPinyin() {
        return shortPinyin;
    }
    public void setShortPinyin(String shortPinyin) {
        this.shortPinyin = shortPinyin;
    }
        
   
     public String getGoodsName() {
        return goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
        
   
     public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
        
   
     public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
        
   
     public String getProvName() {
        return provName;
    }
    public void setProvName(String provName) {
        this.provName = provName;
    }
        
   
     public String getHosName() {
        return hosName;
    }
    public void setHosName(String hosName) {
        this.hosName = hosName;
    }
        
   
     public String getMfrsName() {
        return mfrsName;
    }
    public void setMfrsName(String mfrsName) {
        this.mfrsName = mfrsName;
    }
        
   
     public String getHisName() {
        return hisName;
    }
    public void setHisName(String hisName) {
        this.hisName = hisName;
    }
        
   
     public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
        
   
     public BigDecimal getTaxRate() {
        return taxRate;
    }
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
        
   
     public BigDecimal getSalePrice() {
        return salePrice;
    }
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
        
   
     public String getProvErpCode() {
        return provErpCode;
    }
    public void setProvErpCode(String provErpCode) {
        this.provErpCode = provErpCode;
    }
        
   
     public String getHosErpCode() {
        return hosErpCode;
    }
    public void setHosErpCode(String hosErpCode) {
        this.hosErpCode = hosErpCode;
    }
        
   
     public BigDecimal getPurPrice() {
        return purPrice;
    }
    public void setPurPrice(BigDecimal purPrice) {
        this.purPrice = purPrice;
    }
        
   
     public String getUniqueCodeStrategy() {
        return uniqueCodeStrategy;
    }
    public void setUniqueCodeStrategy(String uniqueCodeStrategy) {
        this.uniqueCodeStrategy = uniqueCodeStrategy;
    }
        
   
     public String getMfrsGoodsCode() {
        return mfrsGoodsCode;
    }
    public void setMfrsGoodsCode(String mfrsGoodsCode) {
        this.mfrsGoodsCode = mfrsGoodsCode;
    }
        
   
     public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
        
   
     public String getMiCode() {
        return miCode;
    }
    public void setMiCode(String miCode) {
        this.miCode = miCode;
    }
        
   
     public String getPurMode() {
        return purMode;
    }
    public void setPurMode(String purMode) {
        this.purMode = purMode;
    }
        
   
     public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
        
   
     public String getStCondition() {
        return stCondition;
    }
    public void setStCondition(String stCondition) {
        this.stCondition = stCondition;
    }
        
   
     public String getCertificateCode() {
        return certificateCode;
    }
    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode;
    }
        
   

}