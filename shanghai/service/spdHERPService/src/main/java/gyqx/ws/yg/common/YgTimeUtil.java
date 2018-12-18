package gyqx.ws.yg.common;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class YgTimeUtil {
	
	private static final DateTimeFormatter FORMATTER_A = DateTimeFormatter.ofPattern("yyyyMMdd/HHmmss");
	private static final DateTimeFormatter FORMATTER_B = DateTimeFormatter.ofPattern("yyyyMMdd");

	/**
	 * 日期格式A
	 * 
	 * @return
	 */
	public static String getStampA() {
		LocalDateTime now = LocalDateTime.now();
		String format = now.format(FORMATTER_A);
		return format;
	}

	/**
	 * 日期格式B
	 * 
	 * @return
	 */
	public static String getStampB() {
		LocalDateTime now = LocalDateTime.now();
		String format = now.format(FORMATTER_B);
		return format;
	}

	/**
	 * 格式A的字符串转为时间
	 * 
	 * @param str
	 * @return
	 */
	public static Date getDateFromA(String str) {
		LocalDateTime localDateTime = LocalDateTime.parse(str, FORMATTER_A);
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		return Date.from(instant);
	}

	/**
	 * 格式B的字符串转为时间
	 * 
	 * @param str
	 * @return
	 */
	public static Date getDateFromB(String str) {
		LocalDateTime localDateTime = LocalDateTime.parse(str, FORMATTER_B);
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		return Date.from(instant);
	}
}
