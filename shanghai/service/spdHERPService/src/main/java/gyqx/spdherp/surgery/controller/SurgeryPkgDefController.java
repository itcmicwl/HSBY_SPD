package gyqx.spdherp.surgery.controller;


import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.po.SurgeryPkgDef;
import gyqx.spdherp.surgery.service.ISurgeryPkgDefService;
import gyqx.spdherp.surgery.vo.ElTreeVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgDefVo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(value = "surgery/surgeryPkgDef")
public class SurgeryPkgDefController extends BaseController 
{
	@Resource
	private ISurgeryPkgDefService  surgeryPkgDefService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid SurgeryPkgDefVo surgeryPkgDefVo ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		try{
			SurgeryPkgDef ret = surgeryPkgDefService.add(surgeryPkgDefVo);
			result.setData(ret);
		} catch (ValidateException ex){
			result.setException(ex);
		}
		return result;
	}

	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid SurgeryPkgDefVo surgeryPkgDefVo ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);

		try{
			int ret = surgeryPkgDefService.update(surgeryPkgDefVo);
			result.setData(ret);
		} catch (ValidateException ex){
			result.setException(ex);
		}
		return result;
	}

	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody @Valid QueryInfo<SurgeryPkgDefVo> queryInfo ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<SurgeryPkgDefVo> ret = surgeryPkgDefService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}

	@RequestMapping(value = "getSurgeryKindDefTreeByHos")
	@ResponseBody
	public AjaxResult getSurgeryKindDefTreeByHos(@RequestBody Map params) throws Exception {
		AjaxResult result = new AjaxResult();
		ElTreeVo root = surgeryPkgDefService.getSurgeryKindDefTreeByHos(params);
		result.setData(root);

		return result;
	}

}
