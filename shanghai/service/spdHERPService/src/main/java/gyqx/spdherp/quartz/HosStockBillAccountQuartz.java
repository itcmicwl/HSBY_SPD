package gyqx.spdherp.quartz;

import common.utils.groovy.GroovyUtils;
import common.utils.jms.JMSSendUtils;
import common.utils.txp.TxpSqlNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 系统定时任务.
 * 
 * @author ember
 * 
 */
public class HosStockBillAccountQuartz {
	/*@Autowired
    private BuildInOutAccount buildInOutAccount;*/
	@Resource
	private JMSSendUtils jmsUtil;
	@Autowired
	private GroovyUtils customGroovyUtils;
	private Logger logger = LoggerFactory.getLogger(HosStockBillAccountQuartz.class);

	public void billAccountJob() throws Exception{
		logger.info("####################开始执行billAccountJob start##############################");
		System.out.println("*********************开始执行billAccountJob start********************************");

	//	buildInOutAccount.testFun();
		String driverClassName =getValue("jdbc.driverClassName");
		String url = getValue("jdbc.url");
		String username = getValue("jdbc.username");
		String password = getValue("jdbc.password");
		Object[] p=new Object[4];
		p[0]=driverClassName;p[1]=url;p[2]=username;p[3]=password;
		customGroovyUtils.invokeMethod("BuildInOutAccount","testFun",p);
		logger.info("#####################执行billAccountJob 完成 end###############################");
		TxpSqlNotify tsn = new TxpSqlNotify();
		tsn.setCmd("sql.table.changed");
		tsn.setMethod("update");
		tsn.setTableName("in_stock_account");
		tsn.setSql("");
		jmsUtil.send(tsn.getCmd(), tsn,null);
		tsn.setTableName("out_stock_account");
		jmsUtil.send(tsn.getCmd(), tsn,null);
	}

	public static String getValue( String key)
	{
		Properties prop = new Properties();
		String re =null;
		InputStream in = HosStockBillAccountQuartz.class.getClassLoader().getResourceAsStream("properties/dbconfig.properties");
		try {
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");
			BufferedReader bf = new BufferedReader(isr);
			prop.load(bf);
			re = prop.getProperty(key).trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return re;
	}

}
