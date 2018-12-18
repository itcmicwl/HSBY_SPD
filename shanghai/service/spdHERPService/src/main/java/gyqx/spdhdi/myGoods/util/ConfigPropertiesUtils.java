package gyqx.spdhdi.myGoods.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigPropertiesUtils {
	 public static String getValue( String key)
	    {
		  Properties prop = new Properties(); 
		  String re =null;
			InputStream in = ReturnCodePropertiesUtils.class.getClassLoader().getResourceAsStream("properties/config.properties");
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
/*
	  public static void main(String[] args)
	    { 
	        String errorCode = ReturnCodePropertiesUtils.getValue("mulDel.errorCode");
	        String message = ReturnCodePropertiesUtils.getValue("mulDel.errorCode.message");
	      
	    } */
}
