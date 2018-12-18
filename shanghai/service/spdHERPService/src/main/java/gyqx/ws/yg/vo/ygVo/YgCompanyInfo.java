package gyqx.ws.yg.vo.ygVo;

import gyqx.ws.yg.vo.repVo.YY164_REP_DETAIL;

/**
 *  药企信息
 * @author suCity
 * @time 2018/03/14
 */
public class YgCompanyInfo extends YY164_REP_DETAIL{

	/**
	 * 最后更新时间
	*/
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 数据版本
	*/
	private int version ;
	
	public java.util.Date getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}

	public void setLastUpdateDatetime(java.util.Date lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "ygCompanyInfo [lastUpdateDatetime=" + lastUpdateDatetime + ", version=" + version + ", getQybm()="
				+ getQybm() + ", getQymc()=" + getQymc() + ", getQydz()=" + getQydz() + ", getLxr()=" + getLxr()
				+ ", getLxdh()=" + getLxdh() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
