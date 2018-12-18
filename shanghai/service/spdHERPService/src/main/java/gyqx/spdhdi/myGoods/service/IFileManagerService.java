package gyqx.spdhdi.myGoods.service;

import java.io.File;
import java.io.InputStream;



import common.web.upload.UploadResult;

public interface IFileManagerService 
{
	//UploadResult uploadFile(CommonsMultipartFile file,String fileName) throws Exception;
	UploadResult uploadFile(File sfile,String folder,String fileName) throws Exception;
	
}
