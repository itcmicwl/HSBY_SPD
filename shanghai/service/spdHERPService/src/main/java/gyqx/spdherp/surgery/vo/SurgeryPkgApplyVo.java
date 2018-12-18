package gyqx.spdherp.surgery.vo;

import lombok.Data;


@Data
public class SurgeryPkgApplyVo extends SurgeryPkgDefVo implements Cloneable {
    private Integer packQty;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
