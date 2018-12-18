package gyqx.spdherp.spdOutPtl.vo;

import gyqx.spdherp.po.OutStockList;

import java.io.Serializable;

/**
 * Created by cjzyw on 2018/6/26.
 */
public class OutStockForSpdPtl extends OutStockList implements Serializable{
    private String erpCode;

    public String getErpCode() {
        return erpCode;
    }

    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }
}
