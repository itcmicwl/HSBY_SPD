package gyqx.spdhdi.myInfos.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.transform.Tx;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.platform.po.SysOrg;
import gyqx.spdhdi.myInfos.service.IProvHosInfoCodeService;
import gyqx.spdhdi.myInfos.service.IProvHosInfoVoService;
import gyqx.spdhdi.myInfos.service.ISysOrgService;
import gyqx.spdhdi.myInfos.vo.ElTreeVo;
import gyqx.spdhdi.myInfos.vo.ProvHosInfoCodeVo;
import gyqx.spdhdi.myInfos.vo.ProvHosInfoVo;
import gyqx.spdhdi.po.ProvGoodsInfo;
import gyqx.spdhdi.po.ProvHosInfoCode;

@Controller
@RequestMapping(value = "myInfos/provHosInfoCode")
public class ProvHosInfoCodeController extends BaseController 
{
	@Resource
	private IProvHosInfoCodeService  provHosInfoCodeService;
	@Resource
	IProvHosInfoVoService provHosInfoVoService;
	//@Resource
	//SysOrgService sysOrgService;
	  ApplicationContext context = new ClassPathXmlApplicationContext("configs/httpinvoker-client.xml");
	   ISysOrgService remoteOrgservice = (ISysOrgService) context.getBean("sysOrgServiceProxy");  
	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid ProvHosInfoCodeVo provHosInfoCodeVo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		ProvHosInfoCode phc = new ProvHosInfoCode();
		Tx.transform(provHosInfoCodeVo).to(phc);
		ProvHosInfoCode ret = provHosInfoCodeService.add(phc);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid ProvHosInfoCodeVo provHosInfoCodeVo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		ProvHosInfoCode phc = new ProvHosInfoCode();
		Tx.transform(provHosInfoCodeVo).to(phc);
		ProvHosInfoCode ret = provHosInfoCodeService.update(phc);
		result.setData(ret);
		return result;
	}

	

	
	@RequestMapping(value = "getThePHCodeVo")
	@ResponseBody
	public AjaxResult getThePHCodeVO(@RequestBody @Valid QueryInfo<ProvHosInfoCodeVo> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<ProvHosInfoCodeVo> ret = provHosInfoCodeService.list_PH_code_voByPage(queryInfo);
		if(ret.getData().size()>0)
		result.setData(ret.getData().get(0));
		return result;
	}
	
	/**
	 * 构建树，根为我的医院s，孩子节点为各医院的下属机构
	 */
	@RequestMapping(value = "printTree")
	@ResponseBody
	public AjaxResult getHosAndOrgsByProv(HttpServletRequest request,
			@RequestBody QueryInfo<ProvHosInfoVo> queryInfo)
			throws Exception{
		AjaxResult result = new AjaxResult();
		QueryResult<ProvHosInfoVo> lstProvHosInfoVoList = provHosInfoVoService.getProvHosInfoVoList(queryInfo);
		result.setData(lstProvHosInfoVoList);
		ElTreeVo root = new ElTreeVo();
		root.setId("-1");
		root.setLabel("root");
		List<ElTreeVo> rsonList = new ArrayList();	
		for(ProvHosInfoVo phv:lstProvHosInfoVoList.getData()){
			ElTreeVo hos = new ElTreeVo();
			hos.setId(phv.getHosId());
			hos.setCode(phv.getHosId());
			hos.setLabel(phv.getHosName());
			hos.setNodeType("hos");
			//取医院下的机构
			 /* ApplicationContext context = new ClassPathXmlApplicationContext("configs/httpinvoker-client.xml");
			   ISysOrgService remoteOrgservice = (ISysOrgService) context.getBean("sysOrgServiceProxy");  */
				Map map = new HashMap();
				map.put("corpId", phv.getHosId());
				map.put("pid", "");//根机构
			    List<SysOrg> superOrgs = remoteOrgservice.query(map);
		    	List<ElTreeVo> deptList = new ArrayList();	
	        	for(SysOrg o :superOrgs){
	        		ElTreeVo hosDept = new ElTreeVo();
	        		hosDept.setId(o.getId());
	        		hosDept.setCode(o.getCode());
	        		hosDept.setLabel(o.getEname());
	        		hosDept.setNodeType("dept");
	        	
	        		// 判断该dept 有无子 ，由则递归
	        		if(checkIfLeaf(hosDept.getId())){
					}
					else {
						hosDept =appColumnNode(hosDept);
					//	parentNode.setChildVo(childNode);
					}
	        		deptList.add(hosDept);
			    }
	        	hos.setChildren(deptList);
			rsonList.add(hos);
		}
		root.setChildren(rsonList);
		result.setData(root);
		return result;
	}
	
	
	
	
	 private ElTreeVo appColumnNode(ElTreeVo parentNode) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			   map.put("pid", parentNode.getId()); 
	   	    List<ElTreeVo> tsonList=new ArrayList();
	    	
 		List<SysOrg> data=   remoteOrgservice.query(map);
			if(data.size()>0){
				for (SysOrg theOrg : data){
					ElTreeVo childNode  = new ElTreeVo();
					childNode.setId(theOrg.getId());
					childNode.setLabel(theOrg.getCname());
					childNode.setCode(theOrg.getCode());
					childNode.setPid(theOrg.getPid());
					childNode.setNodeType("dept");
					if(checkIfLeaf(childNode.getId())){
					}
					else {
						childNode =appColumnNode(childNode);
					//	parentNode.setChildVo(childNode);
					}
					tsonList.add(childNode);
				}
				parentNode.setChildren(tsonList);
			}
			

	    	return parentNode;    
	    }
	  	protected boolean checkIfLeaf(String  id) throws Exception{
			boolean  b = true;
			Map<String, Object> map = new HashMap<String, Object>();
			   map.put("pid", id); 
				
			List<SysOrg> data=   remoteOrgservice.query(map);
			if(data.size()>0){
				b =false;
			}	
				
			return b;
		}
}
