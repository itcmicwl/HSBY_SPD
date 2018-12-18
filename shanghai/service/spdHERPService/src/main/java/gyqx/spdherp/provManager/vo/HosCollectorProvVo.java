package gyqx.spdherp.provManager.vo;

import gyqx.spdherp.applyMgr.vo.ProvGoodsKindsVo;
import gyqx.spdherp.po.HosCollectorProv;

import java.io.Serializable;

/**
 * Created by moonb on 2017/10/19.
 */
public class HosCollectorProvVo extends HosCollectorProv implements Serializable {
    private String colName;
    private String provName;
    private String userId;

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj instanceof ProvGoodsKindsVo){
            ProvGoodsKindsVo o = (ProvGoodsKindsVo) obj;
            return o.getProvId().equals(this.getCollectorId());
        }
        return super.equals(obj);
    }
}
