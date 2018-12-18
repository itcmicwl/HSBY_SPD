package gyqx.spdherp.stocInfoMgr.vo;

import java.util.List;

/**
 * @Author liangwu
 * @Date 18-5-24 下午2:14
 */
public class GoodsShelfInfoVo {
    private String goodsId;
    private List<String> shelfCodeList;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public List<String> getShelfCodeList() {
        return shelfCodeList;
    }

    public void setShelfCodeList(List<String> shelfCodeList) {
        this.shelfCodeList = shelfCodeList;
    }
}
