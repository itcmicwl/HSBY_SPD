
package gyqx.platform.po;

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
 * 机构表
*/
@Table(name="sys_org")
public class SysOrg implements IBean
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
	 * 上级ID
	*/
	@Size(max=36)
	@JoinColumn(name="pid",table="sys_org",referencedColumnName="id")
	@Title("上级ID")
	private String pid ;
	/**
	 * 机构编码
	*/
	@Size(max=300)
	@Column(name="code")
	@Title("机构编码")
	private String code ;
	/**
	 * 自编码
	 * 企业机构的自用编码
	*/
	@Size(max=36)
	@Column(name="self_code")
	@Title("自编码")
	private String selfCode ;
	/**
	 * 所属单位ID
	*/
	@Size(max=36)
	@JoinColumn(name="corp_id",table="bas_company_info",referencedColumnName="id")
	@Title("所属单位ID")
	private String corpId ;
	/**
	 * 单位类型
	 * 
	 * 机构类型
	*/
	@Size(max=36)
	@Column(name="corp_kind")
	@Title("单位类型")
	private String corpKind ;
	/**
	 * 机构名称
	*/
	@Size(max=36)
	@Column(name="ename")
	@Title("机构名称")
	private String ename ;
	/**
	 * 机构别名
	*/
	@Size(max=36)
	@Column(name="cname")
	@Title("机构别名")
	private String cname ;
	/**
	 * 序号
	 * 机构在上级机构中的序号
	*/
	@Column(name="ordinal")
	@Title("序号")
	private int ordinal ;
	/**
	 * 拼音码
	*/
	@Size(max=36)
	@Column(name="short_pinyin")
	@Title("拼音码")
	private String shortPinyin ;
	/**
	 * 地址
	*/
	@Size(max=36)
	@Column(name="address")
	@Title("地址")
	private String address ;
	/**
	 * SCM编号
	*/
	@Size(max=36)
	@Column(name="scm_code")
	@Title("SCM编号")
	private String scmCode ;
	/**
	 * ERP编号
	*/
	@Size(max=36)
	@Column(name="erp_code")
	@Title("ERP编号")
	private String erpCode ;
	/**
	 * 联系方式
	*/
	@Size(max=36)
	@Column(name="contact_way")
	@Title("联系方式")
	private String contactWay ;
	/**
	 * 备注信息
	*/
	@Size(max=128)
	@Column(name="remark")
	@Title("备注信息")
	private String remark ;
	/**
	 * 结点类型
	 * 
	 * [0 非叶结点,1 叶结点]
	*/
	@Size(max=1)
	@Column(name="node_kind")
	@Title("结点类型")
	private String nodeKind ;
	/**
	 * 标志
	*/
	@Size(max=1)
	@Column(name="flag")
	@Title("标志")
	private String flag ;
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
	public String getCode ()
	{
		return code ;
	}
	
	public void setCode (String code )
	{
		this.code = code;
	}
	public String getSelfCode ()
	{
		return selfCode ;
	}
	
	public void setSelfCode (String selfCode )
	{
		this.selfCode = selfCode;
	}
	public String getCorpId ()
	{
		return corpId ;
	}
	
	public void setCorpId (String corpId )
	{
		this.corpId = corpId;
	}
	public String getCorpKind ()
	{
		return corpKind ;
	}
	
	public void setCorpKind (String corpKind )
	{
		this.corpKind = corpKind;
	}
	public String getEname ()
	{
		return ename ;
	}
	
	public void setEname (String ename )
	{
		this.ename = ename;
	}
	public String getCname ()
	{
		return cname ;
	}
	
	public void setCname (String cname )
	{
		this.cname = cname;
	}
	public int getOrdinal ()
	{
		return ordinal ;
	}
	
	public void setOrdinal (int ordinal )
	{
		this.ordinal = ordinal;
	}
	public String getShortPinyin ()
	{
		return shortPinyin ;
	}
	
	public void setShortPinyin (String shortPinyin )
	{
		this.shortPinyin = shortPinyin;
	}
	public String getAddress ()
	{
		return address ;
	}
	
	public void setAddress (String address )
	{
		this.address = address;
	}
	public String getScmCode ()
	{
		return scmCode ;
	}
	
	public void setScmCode (String scmCode )
	{
		this.scmCode = scmCode;
	}
	public String getErpCode ()
	{
		return erpCode ;
	}
	
	public void setErpCode (String erpCode )
	{
		this.erpCode = erpCode;
	}
	public String getContactWay ()
	{
		return contactWay ;
	}
	
	public void setContactWay (String contactWay )
	{
		this.contactWay = contactWay;
	}
	public String getRemark ()
	{
		return remark ;
	}
	
	public void setRemark (String remark )
	{
		this.remark = remark;
	}
	public String getNodeKind ()
	{
		return nodeKind ;
	}
	
	public void setNodeKind (String nodeKind )
	{
		this.nodeKind = nodeKind;
	}
	public String getFlag ()
	{
		return flag ;
	}
	
	public void setFlag (String flag )
	{
		this.flag = flag;
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
		return "SysOrg [" +" id=" +  id   +", pid=" +  pid   +", code=" +  code   +", selfCode=" +  selfCode   +", corpId=" +  corpId   +", corpKind=" +  corpKind   +", ename=" +  ename   +", cname=" +  cname   +", ordinal=" +  ordinal   +", shortPinyin=" +  shortPinyin   +", address=" +  address   +", scmCode=" +  scmCode   +", erpCode=" +  erpCode   +", contactWay=" +  contactWay   +", remark=" +  remark   +", nodeKind=" +  nodeKind   +", flag=" +  flag   +", lastUpdateDatetime=" +  lastUpdateDatetime   +", version=" +  version   
			 + "]";
	}

}