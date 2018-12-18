package gyqx.spdhdi.myGoods.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.shiro.util.StringUtils;
import org.springframework.stereotype.Service;

import common.base.Hash;
import common.image.ImageUtils;
import common.utils.SysConfigUtil;
import common.web.upload.UploadResult;
import gyqx.spdhdi.myGoods.service.IFileManagerService;


@Service
public class FileManagerService implements IFileManagerService
{
	@Resource
	private SysConfigUtil configUtil;
	
	Pattern p = Pattern.compile(".*(:|\\?|\\*|\\\\|/|<|>|\"|\\|).*");
	public UploadResult uploadFile(File sfile,String folder,String fileName) throws Exception{
		fileName = fileName.trim();
		if(fileName.length()== 0)
			throw new Exception("请选择文件！");
		Matcher mat = p.matcher(fileName);
		if(mat.matches())
			throw new Exception("文件名中不能存在[：? * \\/\"| < >]这些符号！");
		mat = p.matcher(folder);
		if(mat.matches())
			throw new Exception("文件夹名中不能存在[：? * \\/\"| < >]这些符号！");
		String ext = "";
		int dotpos = fileName.lastIndexOf(".");
		if(dotpos >0)
		{
			ext = fileName.substring(dotpos+1);
			ext = ext.toLowerCase();
			if(ext.length()>5)
				ext = "";
		}
		
		String hash = Hash.getFileHash(sfile);
		
		String firstPath = StringUtils.hasText(ext)? ext + "/": "";
		
		String basePath = configUtil.getValue("upload.basepath");
		if(basePath.indexOf(";") >0)
		{
			String[] ss = basePath.split(";");
			if(System.getProperty("os.name").toLowerCase().indexOf("window") >=0 )
			{
				basePath = ss[0];
			}else{
				basePath = ss[1];
			}
		}
		String filePath = basePath + "/"+folder +"/" + firstPath + hash.charAt(0) + "/" +hash.charAt(1) ;
	
		File filedir = new File(filePath);
		if(!filedir.exists())
			filedir.mkdirs();
		
		File file = new File(filePath + "/" + hash + "." + ext);
		if(!file.exists() || file.exists() && ( file.length() != sfile.length() )){
			     //cmfile.transferTo(file); 往服务器写人
			byte[] xxFile = getBytes(sfile.getAbsolutePath());
			writeFileToDisk(xxFile,fileName,file.getAbsolutePath());
		}
	
		
		UploadResult uresult = new UploadResult();
		uresult.setId(hash);
		uresult.setExt(ext);
		uresult.setName(fileName);
		uresult.setPath(folder +"/" + firstPath + hash.charAt(0) + "/" +hash.charAt(1) );

		return uresult;
	}

	private  void writeFileToDisk(byte[] img, String fileName,String savePath){  
        try {  
        	File file = new File(savePath);
           
            FileOutputStream fops = new FileOutputStream(file);  
            fops.write(img);  
            fops.flush();  
            fops.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    /** 
     * 获得指定文件的byte数组 
     */  
    private byte[] getBytes(String filePath){  
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }

}
