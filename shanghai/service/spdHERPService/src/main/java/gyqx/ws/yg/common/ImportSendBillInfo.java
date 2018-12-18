package gyqx.ws.yg.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moonb on 2018/3/15.
 */
public class ImportSendBillInfo {
    private Integer total;
    private boolean isEmpty = true;
    private Integer cur;
    private String billId;
    private List<String> billLst = new ArrayList<>();
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public Integer getCur() {
        return cur;
    }

    public void setCur(Integer cur) {
        this.cur = cur;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public List<String> getBillLst() {
        return billLst;
    }

    public void setBillLst(List<String> billLst) {
        this.billLst = billLst;
    }

    public void dispose(){
        this.setCur(0);
        this.setTotal(0);
        this.setEmpty(true);
        this.setBillId("");
        this.setBillLst(new ArrayList<>());
    }
}
