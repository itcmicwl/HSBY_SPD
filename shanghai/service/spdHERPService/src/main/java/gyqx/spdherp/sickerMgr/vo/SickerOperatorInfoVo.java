
package gyqx.spdherp.sickerMgr.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import common.db.IBean;
import common.db.meta.Title;
import gyqx.spdherp.po.SickerOperatorInfo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 手术预约患者信息表
*/
@JsonIgnoreProperties
public class SickerOperatorInfoVo extends SickerOperatorInfo implements Serializable
{
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
}