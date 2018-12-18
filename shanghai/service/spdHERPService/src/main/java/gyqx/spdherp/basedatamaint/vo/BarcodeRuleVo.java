package gyqx.spdherp.basedatamaint.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import common.db.IBean;
import common.db.meta.Title;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * @Auther Liangwu
 * @Date 17-8-11 下午4:50
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class BarcodeRuleVo implements IBean {
    /**
     * ID
     */
    @Id
    @Size(max = 36)
    @Column(name = "id")
    @Title("ID")
    private String id;
    /**
     * 条码样例
     */
    @Size(max = 300)
    @Column(name = "barcode")
    @Title("条码样例")
    private String barcode;
    /**
     * 条码长度
     */
    @Column(name = "barcode_len")
    @Title("条码长度")
    private Integer barcodeLen;
    /**
     * 条码前缀
     */
    @Size(max = 36)
    @Column(name = "barleader")
    @Title("条码前缀")
    private String barleader;
    /**
     * 耗材码位置
     */
    @Column(name = "consumables_position")
    @Title("耗材码位置")
    private Integer consumablesPosition;
    /**
     * 耗材码长度
     */
    @Column(name = "consumables_len")
    @Title("耗材码长度")
    private Integer consumablesLen;
    /**
     * 是否主码
     */
    @Size(max = 1)
    @Column(name = "is_main")
    @Title("是否主码")
    private String isMain;
    /**
     * 有效期位置
     */
    @Column(name = "period_position")
    @Title("有效期位置")
    private Integer periodPosition;
    /**
     * 有效期格式
     */
    @Size(max = 36)
    @Column(name = "period_format")
    @Title("有效期格式")
    private String periodFormat;
    /**
     * 生产批号位置
     */
    @Column(name = "production_position")
    @Title("生产批号位置")
    private Integer productionPosition;
    /**
     * 生产批号长度
     */
    @Column(name = "production_len")
    @Title("生产批号长度")
    private Integer productionLen;
    /**
     * 特征1码位置
     */
    @Column(name = "sign1_position")
    @Title("特征1码位置")
    private Integer sign1Position;
    /**
     * 特征1码
     */
    @Size(max = 36)
    @Column(name = "sign1")
    @Title("特征1码")
    private String sign1;
    /**
     * 特征2码位置
     */
    @Column(name = "sign2_position")
    @Title("特征2码位置")
    private Integer sign2Position;
    /**
     * 特征2码
     */
    @Size(max = 36)
    @Column(name = "sign2")
    @Title("特征2码")
    private String sign2;
    /**
     * 跟踪码位置
     */
    @Column(name = "tracknum_position")
    @Title("跟踪码位置")
    private Integer tracknumPosition;
    /**
     * 跟踪码长度
     */
    @Column(name = "tracknum_len")
    @Title("跟踪码长度")
    private Integer tracknumLen;
    /**
     * 是否有效
     */
    @Size(max=1)
    @Column(name="flag")
    @Title("是否有效")
    private String flag ;
    /**
     * 数据版本
     */
    @Column(name = "version")
    @Title("数据版本")
    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getBarcodeLen() {
        return barcodeLen;
    }

    public void setBarcodeLen(Integer barcodeLen) {
        this.barcodeLen = barcodeLen;
    }

    public String getBarleader() {
        return barleader;
    }

    public void setBarleader(String barleader) {
        this.barleader = barleader;
    }

    public Integer getConsumablesPosition() {
        return consumablesPosition;
    }

    public void setConsumablesPosition(Integer consumablesPosition) {
        this.consumablesPosition = consumablesPosition;
    }

    public Integer getConsumablesLen() {
        return consumablesLen;
    }

    public void setConsumablesLen(Integer consumablesLen) {
        this.consumablesLen = consumablesLen;
    }

    public String getIsMain() {
        return isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
    }

    public Integer getPeriodPosition() {
        return periodPosition;
    }

    public void setPeriodPosition(Integer periodPosition) {
        this.periodPosition = periodPosition;
    }

    public String getPeriodFormat() {
        return periodFormat;
    }

    public void setPeriodFormat(String periodFormat) {
        this.periodFormat = periodFormat;
    }

    public Integer getProductionPosition() {
        return productionPosition;
    }

    public void setProductionPosition(Integer productionPosition) {
        this.productionPosition = productionPosition;
    }

    public Integer getProductionLen() {
        return productionLen;
    }

    public void setProductionLen(Integer productionLen) {
        this.productionLen = productionLen;
    }

    public Integer getSign1Position() {
        return sign1Position;
    }

    public void setSign1Position(Integer sign1Position) {
        this.sign1Position = sign1Position;
    }

    public String getSign1() {
        return sign1;
    }

    public void setSign1(String sign1) {
        this.sign1 = sign1;
    }

    public Integer getSign2Position() {
        return sign2Position;
    }

    public void setSign2Position(Integer sign2Position) {
        this.sign2Position = sign2Position;
    }

    public String getSign2() {
        return sign2;
    }

    public void setSign2(String sign2) {
        this.sign2 = sign2;
    }

    public Integer getTracknumPosition() {
        return tracknumPosition;
    }

    public void setTracknumPosition(Integer tracknumPosition) {
        this.tracknumPosition = tracknumPosition;
    }

    public Integer getTracknumLen() {
        return tracknumLen;
    }

    public void setTracknumLen(Integer tracknumLen) {
        this.tracknumLen = tracknumLen;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "BarcodeRuleVo{" +
                "id='" + id + '\'' +
                ", barcode='" + barcode + '\'' +
                ", barcodeLen=" + barcodeLen +
                ", barleader='" + barleader + '\'' +
                ", consumablesPosition=" + consumablesPosition +
                ", consumablesLen=" + consumablesLen +
                ", isMain='" + isMain + '\'' +
                ", periodPosition=" + periodPosition +
                ", periodFormat='" + periodFormat + '\'' +
                ", productionPosition=" + productionPosition +
                ", productionLen=" + productionLen +
                ", sign1Position=" + sign1Position +
                ", sign1='" + sign1 + '\'' +
                ", sign2Position=" + sign2Position +
                ", sign2='" + sign2 + '\'' +
                ", tracknumPosition=" + tracknumPosition +
                ", tracknumLen=" + tracknumLen +
                ", flag='" + flag + '\'' +
                ", version=" + version +
                '}';
    }
}