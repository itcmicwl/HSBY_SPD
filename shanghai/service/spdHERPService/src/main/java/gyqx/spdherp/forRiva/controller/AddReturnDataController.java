package gyqx.spdherp.forRiva.controller;

import common.web.BaseController;
import gyqx.spdherp.forRiva.service.IAddReturnService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping({"/forSickerUse"})
//'http://localhost:8080/spdHERPService/forSickerUse/addSickerUseData/{billId}'
public class AddReturnDataController extends BaseController {
	@Resource
	private IAddReturnService iAddReturnService;

	@RequestMapping(value="/addSickerUseData/{billId}",method = RequestMethod.GET)
	public String addSickerUseData(@PathVariable String billId)  throws  Exception {
		int i = iAddReturnService.addSickerUse(billId);
		if(i==0){
			return "ok";
		}else {
			return "fail";
		}
	}
}

