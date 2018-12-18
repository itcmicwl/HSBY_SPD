package gyqx.spdherp.barcodePrintingCenter.vo;

import gyqx.spdherp.po.HosPackageBatchDetail;
import gyqx.spdherp.po.HosPackageInfo;

import java.util.ArrayList;
import java.util.List;

public class MPkage extends HosPackageInfo{

    private List<HosPackageBatchDetail> mDetailLst = new ArrayList();


    public List<HosPackageBatchDetail> getmDetailLst() {
        return mDetailLst;
    }

    public void setmDetailLst(List<HosPackageBatchDetail> mDetailLst) {
        this.mDetailLst = mDetailLst;
    }

    @Override
    public String toString() {
        return "MPkage{" +
                "mDetailLst=" + mDetailLst.toString() +
                '}';
    }
}
