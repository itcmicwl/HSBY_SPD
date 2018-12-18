package gyqx.ws.yg.common;

/**
 * 返回消息
 * 
 * @author LIWENKANG
 *
 */
public enum ResCode {

	SUCCESS("00000", "处理成功"), 
	NET_FAIL("11001", "通信失败"), 
	SYSTEM_ERROR("11002", "系统处理错误"), 
	USER_ERROR("11003",	"用户名密码校验失败"), 
	UP_CODE_ERROR("11004", "上传的消息类型码有误"), 
	UP_SIGN_ERROR("11005", "上传的签名比对失败"), 
	XML_ERROR("11006", "上传的XML格式有误"),
	OTHER("9999", "其他");
	
	// 成员变量
	private String code;
	private String description;
	
	// 构造方法
	private ResCode(String code, String description) {
		this.code = code;
		this.description = description;
	}

	// get set 方法
	// set 非必需
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 返回描述信息
	 * @param code 消息码
	 * @return
	 */
	public static String getDescription(String code){
		for(ResCode rc : ResCode.values()){
			if(rc.getCode().equals(code)){
				return rc.getDescription();
			}
		}
		return null;
	}
}
