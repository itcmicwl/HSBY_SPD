package gyqx.spdherp.stockout.vo;

import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-9-1 下午4:50
 */
public class UniqueCodeQueryVo {
    private String stockId;
    private String buyKind;
    private List<String> goodsId;

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public List<String> getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(List<String> goodsId) {
        this.goodsId = goodsId;
    }

    public String getBuyKind() {
        return buyKind;
    }

    public void setBuyKind(String buyKind) {
        this.buyKind = buyKind;
    }
}
