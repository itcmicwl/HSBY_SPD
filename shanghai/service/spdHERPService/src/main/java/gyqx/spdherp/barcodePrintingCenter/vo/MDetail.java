package gyqx.spdherp.barcodePrintingCenter.vo;

public class MDetail {
    private String id;
    private String pc;
    private int qty;
    private String packId;
    private int dRowId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getPackId() {
        return packId;
    }

    public void setPackId(String packId) {
        this.packId = packId;
    }

    public int getdRowId() {
        return dRowId;
    }

    public void setdRowId(int dRowId) {
        this.dRowId = dRowId;
    }

    @Override
    public String toString() {
        return "MDetail{" +
                "id='" + id + '\'' +
                ", pc='" + pc + '\'' +
                ", qty=" + qty +
                ", packId='" + packId + '\'' +
                ", dRowId=" + dRowId +
                '}';
    }
}
