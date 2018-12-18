package gyqx.spdherp.forRiva.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gyqx.spdhdi.orderMgr.vo.DistrBillUniqueListVo;
import java.io.Serializable;
import java.util.List;

/**
 * Created by cjzyw on 2018/6/3.
 */
@JsonIgnoreProperties
public class ForRivaH02 extends DistrBillUniqueListVo implements Serializable{
    private String billId;

    private List<CheckCode> checkCodes;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public List<CheckCode> getCheckCodes() {
        return checkCodes;
    }

    public void setCheckCodes(List<CheckCode> checkCodes) {
        this.checkCodes = checkCodes;
    }
}
