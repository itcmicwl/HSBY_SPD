package gyqx.spdherp.forRiva.controller;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.forRiva.service.IPrintForRivaService;
import gyqx.spdherp.forRiva.vo.ForRivaH02;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping({"/forRivaInterface/print"})
public class IPrintForRivaController extends BaseController {
	@Resource
	private IPrintForRivaService iPrintForRivaService;

	@RequestMapping(value="print")
	@ResponseBody
	public AjaxResult<String>print(@RequestBody ForRivaH02 as)  throws  Exception {
		AjaxResult<String> result = new AjaxResult<>();
		String sRe = iPrintForRivaService.printSer(as);
		if(sRe.equals("ok")){
			result.setCode(0);
		}else {
			result.setCode(100);
		}
		return result;
	}
}

