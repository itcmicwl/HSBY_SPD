package gyqx.spdherp.surgery.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import common.db.meta.Title;
import gyqx.spdherp.po.SurgeryPkgDef;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SurgeryPkgDefVo extends SurgeryPkgDef implements Serializable {
    private String kindName;
    private String kindCode;

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }


    public String getKindCode() {
        return kindCode;
    }

    public void setKindCode(String kindCode) {
        this.kindCode = kindCode;
    }
    private List<SurgeryPkgDefListVo> lstGoods;

    public List<SurgeryPkgDefListVo> getLstGoods() {
        return lstGoods;
    }

    public void setLstGoods(List<SurgeryPkgDefListVo> lstGoods) {
        this.lstGoods = lstGoods;
    }
}
