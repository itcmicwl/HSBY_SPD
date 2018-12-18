package gyqx.ws.yg.vo.reqVo;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 耗材按采购单获取采购明细状态(YY159)
 * Created by moonb on 2017/11/22.
 */
@XmlType(propOrder = { "ddbh", "ddmxbhcxtj"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
public class YY159_REQ_MAIN {
	
	/**
	 * 采购单号
	 */
	@XmlElement(name = "DDBH")
	private String ddbh;
	
	/**
	 * 采购明细单号查询条件
	 */
	@XmlElement(name = "DDMXBHCXTJ")
	private String ddmxbhcxtj="";
	
	public YY159_REQ_MAIN() {
	}

	public YY159_REQ_MAIN(String ddbh) {
		super();
		this.ddbh = ddbh;
	}

	public YY159_REQ_MAIN(String ddbh, String ddmxbhcxtj) {
		super();
		this.ddbh = ddbh;
		this.ddmxbhcxtj = ddmxbhcxtj;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public String getDdmxbhcxtj() {
		return ddmxbhcxtj;
	}

	public void setDdmxbhcxtj(String ddmxbhcxtj) {
		this.ddmxbhcxtj = ddmxbhcxtj;
	}

	
}
