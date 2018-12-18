package gyqx.spdherp.stockout.vo;

import gyqx.spdherp.po.SickerUse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by moonb on 2018/4/24.
 */
public class SickerUserVo extends SickerUse {
	private Date sDate;
	private Date eDate;
	private String deptName;
    private String fillerName;
    private String patientName;
    private List<SickerUserListVo> lstDetail = new ArrayList<>();
	//消耗的手术包商品列表
	private List<SurgeryPkg4UseVo> pkgLstDetail = new ArrayList<>();

    public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getFillerName() {
        return fillerName;
    }

    public void setFillerName(String fillerName) {
        this.fillerName = fillerName;
    }

    public List<SickerUserListVo> getLstDetail() {
        return lstDetail;
    }

    public void setLstDetail(List<SickerUserListVo> lstDetail) {
        this.lstDetail = lstDetail;
    }

	public List<SurgeryPkg4UseVo> getPkgLstDetail() {
		return pkgLstDetail;
	}

	public void setPkgLstDetail(List<SurgeryPkg4UseVo> pkgLstDetail) {
		this.pkgLstDetail = pkgLstDetail;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
