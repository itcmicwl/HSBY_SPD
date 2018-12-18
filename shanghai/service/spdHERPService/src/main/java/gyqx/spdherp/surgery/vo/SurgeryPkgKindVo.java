package gyqx.spdherp.surgery.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import common.db.meta.Title;
import gyqx.spdherp.po.SurgeryPkgKind;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SurgeryPkgKindVo extends SurgeryPkgKind implements Serializable {
    private String pcode;

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }
}
