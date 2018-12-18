package gyqx.spdherp.spdOutPtl.vo;

import gyqx.spdherp.po.SpdInventory;
import gyqx.spdherp.po.SpdInventoryList;
import gyqx.spdherp.po.SpdOutPtl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cjzyw on 2018/7/31.
 */
public class SpdInventoryVo extends SpdInventory implements Serializable{
    private List<SpdInventoryList> spdInventoryLists;
    private List<SpdOutPtl> spdOutPtlList;

    public List<SpdInventoryList> getSpdInventoryLists() {
        return spdInventoryLists;
    }

    public void setSpdInventoryLists(List<SpdInventoryList> spdInventoryLists) {
        this.spdInventoryLists = spdInventoryLists;
    }

    public List<SpdOutPtl> getSpdOutPtlList() {
        return spdOutPtlList;
    }

    public void setSpdOutPtlList(List<SpdOutPtl> spdOutPtlList) {
        this.spdOutPtlList = spdOutPtlList;
    }
}
