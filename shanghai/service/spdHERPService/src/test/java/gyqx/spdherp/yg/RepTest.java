package gyqx.spdherp.yg;

import gyqx.ws.yg.vo.RepHead;
import gyqx.ws.yg.vo.repVo.YY152_REP_DETAIL;
import gyqx.ws.yg.vo.repVo.YY152_REP_MAIN;
import gyqx.ws.yg.vo.repVo.YY152_REP_XML;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration({ "classpath:configs/spring-context.xml"})
public class RepTest {
	public static void main(String[] args) throws Exception {
		
		File file = new File("src/test/java/gyqx/spdherp/yg/rep.xml");
		JAXBContext jaxb = JAXBContext.newInstance(YY152_REP_XML.class);
		Unmarshaller um = jaxb.createUnmarshaller();

		YY152_REP_XML xml = (YY152_REP_XML) um.unmarshal(file);
		RepHead repHead = xml.getRepHead();
		YY152_REP_MAIN main = xml.getMain();
		List<YY152_REP_DETAIL> detail = xml.getDetail();
		System.out.println("消息头: => " + repHead);
		System.out.println("消息主条目: => " + main);
		System.out.println("消息明细条目: => " + detail);
		System.out.println("xml: => " + xml);
	}
}
