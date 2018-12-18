
package gyqx.ws.yg.hos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gyqx.ws.yg.hos package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SendRecv_QNAME = new QName("http://ws.framework.shys.wondersgroup.com/", "SendRecv");
    private final static QName _SendRecvResponse_QNAME = new QName("http://ws.framework.shys.wondersgroup.com/", "SendRecvResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gyqx.ws.yg.hos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendRecv }
     * 
     */
    public SendRecv createSendRecv() {
        return new SendRecv();
    }

    /**
     * Create an instance of {@link SendRecvResponse }
     * 
     */
    public SendRecvResponse createSendRecvResponse() {
        return new SendRecvResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendRecv }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.framework.shys.wondersgroup.com/", name = "SendRecv")
    public JAXBElement<SendRecv> createSendRecv(SendRecv value) {
        return new JAXBElement<SendRecv>(_SendRecv_QNAME, SendRecv.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendRecvResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.framework.shys.wondersgroup.com/", name = "SendRecvResponse")
    public JAXBElement<SendRecvResponse> createSendRecvResponse(SendRecvResponse value) {
        return new JAXBElement<SendRecvResponse>(_SendRecvResponse_QNAME, SendRecvResponse.class, null, value);
    }

}
