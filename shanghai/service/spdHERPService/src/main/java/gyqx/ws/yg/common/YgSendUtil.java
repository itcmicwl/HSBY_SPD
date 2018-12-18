package gyqx.ws.yg.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gyqx.ws.yg.hos.DispatcherService;
import gyqx.ws.yg.hos.SendRecv_Service;
import gyqx.ws.yg.vo.SMPType;
import gyqx.ws.yg.vo.SmpaaVo;
import org.springframework.stereotype.Component;

/**
 * 上海阳光采购平台发送
 * 
 * @author LIWENKANG
 *
 */
@Component
public class YgSendUtil {
	
	static Logger log = LoggerFactory.getLogger(YgSendUtil.class);

	@Resource
    private SmpaaVo smpaaParam;
	
    private static final QName SERVICE_NAME = new QName("http://ws.framework.shys.wondersgroup.com/", "SendRecv");
    
    /**
     * 发送数据到阳光采购平台
     * @param smpType
     * @param xmlData
     * @return 返回字符串
     * @throws Exception
     */
    public String sendRecv(SMPType smpType, String xmlData) throws Exception {
        SmpaaVo param = buildSmpaaVo(smpType, xmlData);
        URL ygurl = new URL(param.getYgUrl());
        //URL wsdlURL = SendRecv_Service.WSDL_LOCATION;
        SendRecv_Service ss = new SendRecv_Service(ygurl, SERVICE_NAME);
        DispatcherService port = ss.getDispatcher();
        ((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, param.getYgUrl());
        String res = port.sendRecv(param.getUser(), param.getPwd(), param.getJgbm(), param.getVersion(), param.getXxlx(), param.getSign(), param.getXmlData());
        log.info("###################################阳光采购平台返回结果: => {}", res);
        return res;
    }
    
    /**
     * 发送数据到阳光采购平台
     * @param clazz
     * @param smpType
     * @param xmlData
     * @return 返回对象
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public <T> T sendRecv(Class<T> clazz, SMPType smpType, String xmlData) throws Exception {
        //URL wsdlURL = SendRecv_Service.WSDL_LOCATION;
        SmpaaVo param = buildSmpaaVo(smpType, xmlData);
        URL ygurl = new URL(param.getYgUrl());
        SendRecv_Service ss = new SendRecv_Service(ygurl, SERVICE_NAME);
        DispatcherService port = ss.getDispatcher();
        ((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, param.getYgUrl());
        String res = port.sendRecv(param.getUser(), param.getPwd(), param.getJgbm(), param.getVersion(), param.getXxlx(), param.getSign(), param.getXmlData());
        log.info("###################################阳光采购平台返回结果: => {}", res);
        
        // 返回xmlData解析成对象
 		InputStream is = new ByteArrayInputStream(res.getBytes("UTF-8"));
 		JAXBContext jaxb = JAXBContext.newInstance(clazz);
 		Unmarshaller um = jaxb.createUnmarshaller();
 		T repXml = (T) um.unmarshal(is);
 		is.close();
        
        return repXml;
    }
    
    /**
     * 账号信息
     * @param smpType
     * @param xmlData
     * @return
     */
    private  SmpaaVo buildSmpaaVo(SMPType smpType, String xmlData){
        smpaaParam.setXxlx(smpType.getValue());
        smpaaParam.setXmlData(xmlData);
        log.info("###################################阳光采购平台发送的xmlData: => {}", smpaaParam.getXmlData());
        String sign = HostUtils.getMessageDigest(smpaaParam.getXmlData(), "SHA-1");
        smpaaParam.setSign(sign);
        return smpaaParam;
    }

    public <T> T praseXml(Class<T> clazz,String xmlData) throws Exception{
        InputStream is = new ByteArrayInputStream(xmlData.getBytes("UTF-8"));
        JAXBContext jaxb = JAXBContext.newInstance(clazz);
        Unmarshaller um = jaxb.createUnmarshaller();
        T repXml = (T) um.unmarshal(is);
        is.close();
        return repXml;
    }
}
