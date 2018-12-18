package gyqx.spdherp.reportAnalysis.vo;

import java.io.Serializable;


/**
 * 查询需要统计的医院配置信息
 * @author Administrator
 *
 */
public class SysConfigVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 配置项名称
	 */
	private String ename;
	
	/**
	 * 配置项值
	 */
	private String evalue;
	
	/**
	 * 医院id
	 */
	private String hosId;
	
	/**
	 * 统计开始日期
	 */
	private String startDate;
	
	/**
	 * 统计借宿日期
	 */
	private String endDate;

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEvalue() {
		return evalue;
	}

	public void setEvalue(String evalue) {
		this.evalue = evalue;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
