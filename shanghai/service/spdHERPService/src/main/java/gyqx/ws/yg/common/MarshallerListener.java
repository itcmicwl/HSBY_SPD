package gyqx.ws.yg.common;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import javax.xml.bind.Marshaller;

/**
 * 处理空或空串的情况
 * 
 * @author LIWENKANG
 *
 */
public class MarshallerListener extends Marshaller.Listener {

	public static final String BLANK_CHAR = "";

	@Override
	public void beforeMarshal(Object obj) {
		super.beforeMarshal(obj);
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			try {
				// 字符串类型
				if (f.getType() == String.class && f.get(obj) == null) {
					f.set(obj, BLANK_CHAR);
					// Integer
				}else if(f.getType() == Integer.class && f.get(obj) == null){
					f.set(obj, Integer.valueOf(0));
					// BigDecimal
				}else if(f.getType() == BigDecimal.class && f.get(obj) == null){
					f.set(obj, BigDecimal.valueOf(0));
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
