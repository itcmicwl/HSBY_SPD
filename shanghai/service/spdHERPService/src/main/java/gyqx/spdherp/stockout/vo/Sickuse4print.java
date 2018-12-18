package gyqx.spdherp.stockout.vo;

import java.util.Date;
import java.util.List;

public class Sickuse4print {
	private String id;
	private String hosId;       
	private String patientId;      
	private String patientInHosId; 
	private String patientName;    
	private String patientSex;     
	private int patientAge;     
	private String bedNum;         
	private Date oprationDate;   

	private List<SickuseGoods4print> detaill;
	public List<SickuseGoods4print> getDetaill() {
		return detaill;
	}
	public void setDetaill(List<SickuseGoods4print> detaill) {
		this.detaill = detaill;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHosId() {
		return hosId;
	}
	public void setHosId(String hosId) {
		this.hosId = hosId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientInHosId() {
		return patientInHosId;
	}
	public void setPatientInHosId(String patientInHosId) {
		this.patientInHosId = patientInHosId;
	}
	public String getPatientName() {
		return patientName;
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
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public String getBedNum() {
		return bedNum;
	}
	public void setBedNum(String bedNum) {
		this.bedNum = bedNum;
	}
	public Date getOprationDate() {
		return oprationDate;
	}
	public void setOprationDate(Date oprationDate) {
		this.oprationDate = oprationDate;
	}
}
