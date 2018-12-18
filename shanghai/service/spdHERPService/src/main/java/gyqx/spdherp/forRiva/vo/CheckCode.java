package gyqx.spdherp.forRiva.vo;

import gyqx.spdhdi.orderMgr.vo.DistrBillUniqueListVo;

import java.io.Serializable;

/**
 * Created by cjzyw on 2018/6/3.
 */
public class CheckCode extends DistrBillUniqueListVo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6379401495310551560L;
    /**
     * ID
     */
    private String id;
    /**
     * pid
     */
    private String pid;
    /**
     * 配送单行号
     */
    private int distrRowNumber;
    /**
     * 配送商产品ID
     */
    private String provGoodsId;
    private String goodsName;

    private String goodsGg;

    private String expdtEndDate;
    /**
     * 产品批次
     */
    private String batchCode;
    /**
     * 唯一码
     */
    private String uniqueCode;
    /**
     * 数据来源
     */
    private int sourceData;
    /**
     * 数据版本
     */
    private int version;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getPid() {
        return pid;
    }

    @Override
    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public int getDistrRowNumber() {
        return distrRowNumber;
    }

    @Override
    public void setDistrRowNumber(int distrRowNumber) {
        this.distrRowNumber = distrRowNumber;
    }

    @Override
    public String getProvGoodsId() {
        return provGoodsId;
    }

    @Override
    public void setProvGoodsId(String provGoodsId) {
        this.provGoodsId = provGoodsId;
    }

    @Override
    public String getGoodsName() {
        return goodsName;
    }

    @Override
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsGg() {
        return goodsGg;
    }

    public void setGoodsGg(String goodsGg) {
        this.goodsGg = goodsGg;
    }

    public String getExpdtEndDate() {
        return expdtEndDate.substring(0,10);
    }

    public void setExpdtEndDate(String expdtEndDate) {
        this.expdtEndDate = expdtEndDate;
    }

    @Override
    public String getBatchCode() {
        return batchCode;
    }

    @Override
    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    @Override
    public String getUniqueCode() {
        return uniqueCode;
    }

    @Override
    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    @Override
    public int getSourceData() {
        return sourceData;
    }

    @Override
    public void setSourceData(int sourceData) {
        this.sourceData = sourceData;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }
}
