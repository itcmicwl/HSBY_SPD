package gyqx.spdherp.stockout.vo;

import gyqx.spdherp.po.SurgeryPkg;

import java.util.List;

public class SurgeryPkg4UseVo extends SurgeryPkg {
    
    /**
     * 打包人姓名
     */
    private String packerName;
    

    private List<SurgeryPkg4UseListVo> surgeryPkg4UseListVo;


    public List<SurgeryPkg4UseListVo> getSurgeryPkg4UseListVo() {
        return surgeryPkg4UseListVo;
    }

    public void setSurgeryPkg4UseListVo(List<SurgeryPkg4UseListVo> surgeryPkg4UseListVo) {
        this.surgeryPkg4UseListVo = surgeryPkg4UseListVo;
    }
    
    public String getPackerName() {
        return packerName;
    }
    
    public void setPackerName(String packerName) {
        this.packerName = packerName;
    }
}
