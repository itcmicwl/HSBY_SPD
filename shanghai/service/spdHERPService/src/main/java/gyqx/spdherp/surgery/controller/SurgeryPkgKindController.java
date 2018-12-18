package gyqx.spdherp.surgery.controller;


import common.exception.ValidateException;
import common.transform.Tx;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.po.SurgeryPkgKind;
import gyqx.spdherp.surgery.service.ISurgeryPkgKindService;
import gyqx.spdherp.surgery.vo.ElTreeVo;
import gyqx.spdherp.surgery.vo.SurgeryPkgKindVo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "surgery/surgeryPkgKind")
public class SurgeryPkgKindController extends BaseController 
{
	@Resource
	private ISurgeryPkgKindService  surgeryPkgKindService;

	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid SurgeryPkgKindVo surgeryPkgKindVo , Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		try{
			SurgeryPkgKind surgeryPkgKind = new SurgeryPkgKind();
			Tx.transform(surgeryPkgKindVo).to(surgeryPkgKind);

			SurgeryPkgKind ret = surgeryPkgKindService.add((SurgeryPkgKind)surgeryPkgKind);
			result.setData(ret);
		} catch (ValidateException ex){
			result.setException(ex);
		}

		return result;
	}


	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid SurgeryPkgKindVo surgeryPkgKindVo , Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);

		try{
			SurgeryPkgKind surgeryPkgKind = new SurgeryPkgKind();
			Tx.transform(surgeryPkgKindVo).to(surgeryPkgKind);

			int ret = surgeryPkgKindService.update(surgeryPkgKind);
			result.setData(ret);
		} catch (ValidateException ex){
			result.setException(ex);
		}
		return result;
	}

    @RequestMapping(value = "delete")
    @ResponseBody
    public AjaxResult delete(@RequestBody @Valid SurgeryPkgKindVo surgeryPkgKindVo , Errors error  )  throws  Exception
    {
        AjaxResult result = new AjaxResult();
        filterErrors(error);

		SurgeryPkgKind surgeryPkgKind = new SurgeryPkgKind();
		Tx.transform(surgeryPkgKindVo).to(surgeryPkgKind);

        int ret = surgeryPkgKindService.delete(surgeryPkgKind);
        result.setData(ret);
        return result;
    }

	/**
	 * 构建手术包类型树结构
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getSurgeryKindByHos")
	@ResponseBody
	public AjaxResult getSurgeryKindByHos(@RequestBody SurgeryPkgKindVo params) throws Exception {
		AjaxResult result = new AjaxResult();

		ElTreeVo tree= surgeryPkgKindService.getSurgeryKindTreeByHos(params);
        result.setData(tree);

		return result;
	}

	/**
	 * 获取手术包分类信息
	 * @param surgeryPkgKind
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getSurKind")
	@ResponseBody
	public AjaxResult getSurKind(@RequestBody @Valid SurgeryPkgKindVo surgeryPkgKind ,Errors error)  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<SurgeryPkgKindVo> list = surgeryPkgKindService.list(surgeryPkgKind);
		if(list != null && list.size() >0) {
			result.setData(list.get(0));
		}
		return result;
	}
}
