package gyqx.spdherp.stockMgr.vo;

public class InSUniquecodeQueryVo extends InStockUniqueCodeVo {
    private String hosId;
    private String deptId;

    public String getHosId() {
        return hosId;
    }

    public void setHosId(String hosId) {
        this.hosId = hosId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
