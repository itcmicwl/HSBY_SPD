package gyqx.ws.yg.common;

import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

import org.apache.commons.lang3.StringUtils;

import common.base.Hex;
import gyqx.ws.yg.vo.ReqHead;

/**
 * 获取主机信息
 * 
 * @author moonb
 */
public class HostUtils {

	/**
	 * 获取本机mac地址
	 * 
	 * @return
	 */
	public static String getMacAddress() {
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			byte[] mac = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
					continue;
				} else {
					mac = netInterface.getHardwareAddress();
					if (mac != null) {
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < mac.length; i++) {
							sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
						}
						if (sb.length() > 0) {
							return sb.toString();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取本机ip
	 * 
	 * @return
	 */
	public static String getIpAddress() {
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
					continue;
				} else {
					Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
					while (addresses.hasMoreElements()) {
						ip = addresses.nextElement();
						if (ip != null && ip instanceof Inet4Address) {
							return ip.getHostAddress();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取sign
	 * 
	 * @param str
	 * @param encName
	 * @return
	 */
	public static String getMessageDigest(String str, String encName) {
		byte[] digest = null;
		if (StringUtils.isBlank(encName)) {
			encName = "SHA-1";
		}
		try {
			MessageDigest md = MessageDigest.getInstance(encName);
			md.update(str.getBytes("utf-8"));

			digest = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Hex.encodeHexStr(digest);
	}

	/**
	 * 获取请求head
	 * 
	 * @param str 备注信息
	 * @return
	 */
	public static ReqHead getReqHead(String str) {
		ReqHead head = new ReqHead();
		head.setIp(HostUtils.getIpAddress());
		head.setMac(HostUtils.getMacAddress().replaceAll("-", ""));
		if (StringUtils.isNotEmpty(str)) {
			head.setRemark(str);
		}
		return head;
	}

}
