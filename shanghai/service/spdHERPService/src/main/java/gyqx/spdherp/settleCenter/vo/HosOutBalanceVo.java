package gyqx.spdherp.settleCenter.vo;

import gyqx.spdherp.po.HosOutBalance;

import java.io.Serializable;
import java.util.List;

/**
 * Created by moonb on 2017/9/14.
 */
public class HosOutBalanceVo extends HosOutBalance implements Serializable,Cloneable {
    private static final long serialVersionUID = 1306058422484128024L;
    private String fillterName;
    private List<HosOutBalanceDetailVo> lstDetail;
    private int settleType;//计算类型：入库结算1、出库结算2
    

    public int getSettleType() {
		return settleType;
	}

	public void setSettleType(int settleType) {
		this.settleType = settleType;
	}

	public List<HosOutBalanceDetailVo> getLstDetail() {
        return lstDetail;
    }

    public void setLstDetail(List<HosOutBalanceDetailVo> lstDetail) {
        this.lstDetail = lstDetail;
    }

    public String getFillterName() {
        return fillterName;
    }

    public void setFillterName(String fillterName) {
        this.fillterName = fillterName;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        HosOutBalanceVo o = null;
        o = (HosOutBalanceVo) super.clone();
        return o;
    }
}
