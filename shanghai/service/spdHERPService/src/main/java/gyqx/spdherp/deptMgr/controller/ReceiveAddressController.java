package gyqx.spdherp.deptMgr.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.deptMgr.service.ReceiveAddressService;
import gyqx.spdherp.deptMgr.vo.BaseAreaVo;
import gyqx.spdherp.deptMgr.vo.ReceiveAddressVo;
import gyqx.spdherp.stocInfoMgr.service.StocInfoService;
import gyqx.ws.yg.common.JAXBUtil;
import gyqx.ws.yg.vo.repVo.YY101_REP_XML;

@Controller
@RequestMapping("/deptMgr/receiveAddr")
public class ReceiveAddressController extends BaseController {

	@Resource
	private ReceiveAddressService receiveAddressService;
	@Resource
	private StocInfoService stocInfoService;

	/**
	 * 查询科室收货地址
	 * 
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getReceiveAddressList")
	@ResponseBody
	public AjaxResult<QueryResult<ReceiveAddressVo>> getReceiveAddressList(
			@RequestBody QueryInfo<ReceiveAddressVo> queryInfo) throws Exception {
		AjaxResult<QueryResult<ReceiveAddressVo>> res = new AjaxResult<QueryResult<ReceiveAddressVo>>();
		QueryResult<ReceiveAddressVo> receiveAddressList = receiveAddressService.getReceiveAddressList(queryInfo);
		res.setData(receiveAddressList);
		return res;
	}

	/**
	 * 编辑
	 * 
	 * @param receiveAddressVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult<ReceiveAddressVo> update(@RequestBody @Valid ReceiveAddressVo receiveAddressVo, Errors error)
			throws Exception {
		AjaxResult<ReceiveAddressVo> res = new AjaxResult<>();
		filterErrors(error);
		receiveAddressService.updateReceiveAddress(receiveAddressVo);
		return res;
	}

	/**
	 * 新增
	 * 
	 * @param receiveAddressVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public AjaxResult<String> insert(@RequestBody @Valid ReceiveAddressVo receiveAddressVo, Errors error)
			throws Exception {
		AjaxResult<String> res = new AjaxResult<>();
		filterErrors(error);
		ReceiveAddressVo vo = receiveAddressService.insertReceiveAddress(receiveAddressVo);
		// 默认上传阳光采购平台
		if(vo != null){
			res = this.sendYg(receiveAddressVo, error);
		}
		return res;
	}

	/**
	 * 删除
	 * 
	 * @param stocInfoVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult<ReceiveAddressVo> delete(@RequestBody @Valid ReceiveAddressVo receiveAddressVo, Errors error)
			throws Exception {
		AjaxResult<ReceiveAddressVo> res = new AjaxResult<>();
		filterErrors(error);
		receiveAddressService.deleteReceiveAddress(receiveAddressVo);
		return res;
	}

	/**
	 * 省市区三级联动数据
	 * 
	 * @param baseAreaVo
	 * @return
	 */
	@RequestMapping("/queryBaseArea")
	@ResponseBody
	public AjaxResult<List<BaseAreaVo>> queryBaseArea(@RequestBody BaseAreaVo baseAreaVo) {
		AjaxResult<List<BaseAreaVo>> res = new AjaxResult<>();
		List<BaseAreaVo> areas = receiveAddressService.queryBaseArea(baseAreaVo);
		res.setData(areas);
		return res;
	}

	/**
	 * 设置默认地址
	 * 
	 * @param receiveAddressVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/setDefaultAddr")
	@ResponseBody
	public AjaxResult<ReceiveAddressVo> setDefaultAddr(@RequestBody @Valid ReceiveAddressVo receiveAddressVo,
			Errors error) throws Exception {
		AjaxResult<ReceiveAddressVo> res = new AjaxResult<>();
		filterErrors(error);
		receiveAddressService.setDefaultAddr(receiveAddressVo);
		return res;
	}

	/**
	 * 上传阳光采购平台
	 * 
	 * @param receiveAddressVo
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sendYg")
	@ResponseBody
	public AjaxResult<String> sendYg(@RequestBody @Valid ReceiveAddressVo receiveAddressVo, Errors error)
			throws Exception {
		AjaxResult<String> res = new AjaxResult<>();
		filterErrors(error);
		String sendYg = receiveAddressService.sendYg(receiveAddressVo);
		YY101_REP_XML xml = JAXBUtil.formXML(YY101_REP_XML.class, sendYg);
		String code = xml.getRepHead().getRes();
		if("00000".equals(code)){
			receiveAddressVo.setSendSmp("1");
			receiveAddressService.updateReceiveAddress(receiveAddressVo);
		}
		res.setData(code);
		return res;
	}

}
