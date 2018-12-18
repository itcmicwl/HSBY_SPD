package gyqx.spdherp.sickerMgr.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import common.db.meta.Title;
import gyqx.spdherp.po.SickerInHospitalInfo;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by cjzyw on 2018/6/7.
 */
@JsonIgnoreProperties
public class SickerInHospitalInfoVo extends SickerInHospitalInfo implements Serializable {
    /**
     * 姓名
     */
    @Size(max = 36)
    @Column(name = "patient_name")
    @Title("姓名")
    private String patientName;

    public String getPatientName() {
        if(patientName!=null){
            return patientName.trim();
        }
        return patientName;
    }

    /**
     * 性别
     */
    @Size(max = 36)
    @Column(name = "patient_sex")
    @Title("性别")
    private String patientSex;
    /**
     * 通信地址
     */
    @Size(max = 100)
    @Column(name = "address")
    @Title("通信地址")
    private String address;

    /**
     * 入院开始时间，设置查询范围用于查询
     */
    private java.util.Date sDate;
    /**
     * 入院结束时间，设置查询范围用于查询
     */
    private java.util.Date eDate;

    public Date getsDate() {
        return sDate;
    }

    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }


    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString() + "SickerInHospitalInfoVo{" +
                "patientName='" + patientName + '\'' +
                ", patientSex='" + patientSex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
