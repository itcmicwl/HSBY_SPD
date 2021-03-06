package gyqx.ws.yg.hos;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.12
 * 2017-11-28T14:21:57.941+08:00
 * Generated source version: 3.1.12
 * 
 */
@WebService(targetNamespace = "http://ws.framework.shys.wondersgroup.com/", name = "DispatcherService")
@XmlSeeAlso({ObjectFactory.class})
public interface DispatcherService {

    @WebMethod(operationName = "SendRecv")
    @RequestWrapper(localName = "SendRecv", targetNamespace = "http://ws.framework.shys.wondersgroup.com/", className = "gyqx.ws.yg.hos.SendRecv")
    @ResponseWrapper(localName = "SendRecvResponse", targetNamespace = "http://ws.framework.shys.wondersgroup.com/", className = "gyqx.ws.yg.hos.SendRecvResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String sendRecv(
        @WebParam(name = "sUser", targetNamespace = "")
        java.lang.String sUser,
        @WebParam(name = "sPwd", targetNamespace = "")
        java.lang.String sPwd,
        @WebParam(name = "sJgbm", targetNamespace = "")
        java.lang.String sJgbm,
        @WebParam(name = "sVersion", targetNamespace = "")
        java.lang.String sVersion,
        @WebParam(name = "sXxlx", targetNamespace = "")
        java.lang.String sXxlx,
        @WebParam(name = "sSign", targetNamespace = "")
        java.lang.String sSign,
        @WebParam(name = "xmlData", targetNamespace = "")
        java.lang.String xmlData
    );
}
