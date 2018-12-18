
package gyqx.spdherp.po;

import java.util.*;
import common.db.IBean;
import common.db.meta.Title;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.*;
/**
 * 不良事件上报附件
*/
@Table(name="adverse_events_file")
public class AdverseEventsFile implements IBean
{
	/**
	 * ID
	*/
	@Id
	@Size(max=36)
	@Column(name="id")
	@Title("ID")
	private String id ;
	/**
	 * 不良事件ID
	*/
	@Size(max=36)
	@Column(name="pid")
	@Title("不良事件ID")
	private String pid ;
	/**
	 * 附件类型
	 * 1：图片2：语音
	*/
	@Size(max=1)
	@Column(name="file_type")
	@Title("附件类型")
	private String fileType ;
	/**
	 * 附件路径
	*/
	@Size(max=128)
	@Column(name="file_path")
	@Title("附件路径")
	private String filePath ;
	/**
	 * 最后更新时间
	*/
	@Column(name="last_update_datetime")
	@Title("最后更新时间")
	private java.util.Date lastUpdateDatetime ;
	/**
	 * 数据版本
	*/
	@Column(name="version")
	@Title("数据版本")
	private int version ;

	public String getId ()
	{
		return id ;
	}
	
	public void setId (String id )
	{
		this.id = id;
	}
	public String getPid ()
	{
		return pid ;
	}
	
	public void setPid (String pid )
	{
		this.pid = pid;
	}
	public String getFileType ()
	{
		return fileType ;
	}
	
	public void setFileType (String fileType )
	{
		this.fileType = fileType;
	}
	public String getFilePath ()
	{
		return filePath ;
	}
	
	public void setFilePath (String filePath )
	{
		this.filePath = filePath;
	}
	public java.util.Date getLastUpdateDatetime ()
	{
		return lastUpdateDatetime ;
	}
	
	public void setLastUpdateDatetime (java.util.Date lastUpdateDatetime )
	{
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	public int getVersion ()
	{
		return version ;
	}
	
	public void setVersion (int version )
	{
		this.version = version;
	}


	@Override
	public String toString() {
		return "AdverseEventsFile [" +" id=" +  id   +", pid=" +  pid   +", fileType=" +  fileType   +", filePath=" +  filePath   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}