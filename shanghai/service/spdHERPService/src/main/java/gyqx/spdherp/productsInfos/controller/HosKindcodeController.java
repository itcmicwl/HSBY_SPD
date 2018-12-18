package gyqx.spdherp.productsInfos.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.po.HosKindcode;
import gyqx.spdherp.productsInfos.service.IHosKindcodeService;
import gyqx.spdherp.productsInfos.vo.GoodsKindTreeVo;

@Controller    
@RequestMapping(value = "productsInfos/hosKindcode")
public class HosKindcodeController extends BaseController 
{
	@Resource
	private IHosKindcodeService  hosKindcodeService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid HosKindcode hosKindcode ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosKindcode ret = hosKindcodeService.add(hosKindcode);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "get/{id}",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult get(@PathVariable String id   )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
	
		HosKindcode ret = hosKindcodeService.get(id);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid HosKindcode hosKindcode ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		HosKindcode ret = hosKindcodeService.update(hosKindcode);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid HosKindcode hosKindcode ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<HosKindcode> ret = hosKindcodeService.list(hosKindcode);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult query(@RequestBody @Valid QueryInfo<HosKindcode> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		//List<HosKindcode> ret = hosKindcodeService.query(queryInfo.getPredicate(),queryInfo.getOrderBy(),queryInfo.getQueryObject());
		List<HosKindcode> ret = hosKindcodeService.list(queryInfo.getQueryObject());

		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody @Valid QueryInfo<HosKindcode> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<HosKindcode> ret = hosKindcodeService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value="delete")
	@ResponseBody
	public AjaxResult delete(HttpServletRequest  request,HttpServletResponse response,
			@RequestBody @Valid HosKindcode hkc ,Errors error) throws Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		hosKindcodeService.delete(hkc);
		
		return result;
	}
	
	@RequestMapping(value="list4tree") 
	@ResponseBody	
	public AjaxResult list4tree(@RequestBody @Valid QueryInfo<HosKindcode> queryInfo ,Errors error ) throws Exception{
		List<GoodsKindTreeVo> treeR = new ArrayList();
		GoodsKindTreeVo root  = new GoodsKindTreeVo();
		root.setId(null);
		root.setLabel("商品分类");
		root.setHosId(queryInfo.getQueryObject().getHosId());
		//root.setPId(null);
		root=appColumnNode(root);
		AjaxResult result = new AjaxResult();
		treeR.add(root);
		result.setData(treeR);
		return result;
	}
	
	 private GoodsKindTreeVo appColumnNode(GoodsKindTreeVo parentNode) {
		
			 List<GoodsKindTreeVo> tsonList=new ArrayList();
	    	try {
	    		HosKindcode qbean = new HosKindcode();
	    		qbean.setHosId(parentNode.getHosId());
	    		qbean.setPId(parentNode.getId());
	    		qbean.setHosId(parentNode.getHosId());
	    	
	    		List<HosKindcode> data=   hosKindcodeService.list(qbean);
				if(data.size()>0){
					for (HosKindcode kind : data){
						GoodsKindTreeVo childNode  = new GoodsKindTreeVo();
							childNode.setId(kind.getId());
							childNode.setLabel(kind.getKindName());
							childNode.setCode(kind.getLevelCode());
							childNode.setState(String.valueOf(kind.getState()));
						//	childNode.setPId(kind.getPId());
							childNode.setHosId(kind.getHosId());
							childNode.setInCode(kind.getInCode());
							if(checkIfLeaf(childNode.getId(),childNode.getHosId())&&	StringUtils.isEmpty(kind.getPId())){
								//childNode.setDisabled(true);
								
							}
							else {
								
								childNode =appColumnNode(childNode);
							//	parentNode.setChildVo(childNode);
							}
							tsonList.add(childNode);

				}
				parentNode.setChildren(tsonList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parentNode;
	}

	protected boolean checkIfLeaf(String id ,String hosId) {
		boolean b = true;
	
		HosKindcode qbean = new HosKindcode();
		qbean.setHosId(hosId);
		qbean.setPId(id);
		try {
			List<HosKindcode> data = hosKindcodeService.list(qbean);
			if (data.size() > 0) {
				b = false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}
}
