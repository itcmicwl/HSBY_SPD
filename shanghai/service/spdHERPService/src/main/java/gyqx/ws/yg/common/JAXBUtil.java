package gyqx.ws.yg.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by moonb on 2017/11/10.
 */
public class JAXBUtil {
	private static final String ENCODING = "UTF-8";
	private static final Log logger = LogFactory.getLog(JAXBUtil.class);

	/**
	 * 将xml格式转为对应类型对象
	 * 
	 * @param clazz
	 * @param xml
	 * @return
	 */
	public static <T> T formXML(Class<T> clazz, String xml) {
		JAXBContext jaxbContext = null;
		T object = null;
		if (xml != null && !"".equals(xml)) {
			try {
				jaxbContext = JAXBContext.newInstance(clazz);
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xml.getBytes(ENCODING));
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				JAXBElement<T> jaxbElement = unmarshaller.unmarshal(new StreamSource(byteArrayInputStream), clazz);
				object = (T) jaxbElement.getValue();
			} catch (Exception e) {
				logger.error("error when unmarshalling from a xml string");
			}
		}
		return object;
	}

	/**
	 * 将对象转为xml格式<br/>
	 * 带class
	 * 
	 * @param object
	 * @param c
	 * @return
	 */
	public static <T> String toXML(T object, Class<?> c) {
		String xml = "";
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass(), c);
			Marshaller marshaller = jaxbContext.createMarshaller();
			// 是否格式化生成xml
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// 设置编码方式
			marshaller.setProperty(Marshaller.JAXB_ENCODING, ENCODING);
			// 添加监听器，处理空或空串的情况
			marshaller.setListener(new MarshallerListener());

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			marshaller.marshal(object, byteArrayOutputStream);
			byte[] buf = byteArrayOutputStream.toByteArray();
			xml = new String(buf, 0, buf.length, ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

	/**
	 * 将对象转为xml格式<br/>
	 * 不带class
	 * 
	 * @param object
	 * @return
	 */
	public static <T> String toXML(T object) {
		String xml = "";
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			// 是否格式化生成xml
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// 设置编码方式
			marshaller.setProperty(Marshaller.JAXB_ENCODING, ENCODING);
			// 添加监听器，处理空或空串的情况
			marshaller.setListener(new MarshallerListener());

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			marshaller.marshal(object, byteArrayOutputStream);
			byte[] buf = byteArrayOutputStream.toByteArray();
			xml = new String(buf, 0, buf.length, ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}
}
