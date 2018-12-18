package gyqx.spdherp.barcodePrintingCenter.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdhdi.orderMgr.service.IDistrBillService;
import gyqx.spdhdi.orderMgr.vo.DistrBillListVo;
import gyqx.spdhdi.orderMgr.vo.DistrBillUniqueListVo;
import gyqx.spdhdi.orderMgr.vo.DistrBillVo;

@Controller
@RequestMapping(value = "barcodePrintingCenter/uniqueCode")
public class UniqueCodeController extends BaseController{
	@Resource
	private SimpleDao simpleDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IDistrBillService  distrBillService;
	@RequestMapping(value="create")
	@ResponseBody
	public AjaxResult<DistrBillVo>create(@RequestBody DistrBillVo dis  ,Errors error)  throws  Exception  {
		AjaxResult<DistrBillVo> result = new AjaxResult<DistrBillVo>();
		filterErrors(error);
		DistrBillVo ret = distrBillService.getByBillId(dis.getBillId());
		//ret.getDistrBillList().get(0).getDistrBillUniqueList().
		//找到明细行中 uniuqeKind=0 系统分配唯一码 的明细行
	//	List<DistrBillListVo> sys_wym_mx_list=	ret.getDistrBillList().stream().filter(mxRow-> 0==mxRow.getUniqueKind()).collect(Collectors.toList()); 
			if(ret!=null&&ret.getDistrBillList()!=null){
				for(DistrBillListVo mx:ret.getDistrBillList()){
					if( 0==mx.getUniqueKind()){
						//先查询 distr_bill_unique_list 表中是否已保存了唯一，有则取原有的，没有则生成   。还要判断send_qty 配送数，有几个生成几个唯一码

						DistrBillUniqueListVo temp = new DistrBillUniqueListVo();
						temp.setPid(mx.getId());
						temp.setDistrRowNumber(mx.getRowNumber());
						List<DistrBillUniqueListVo> uniqueList = distrBillService.listDistrUnique(temp);
						if(uniqueList.size()>0){
							mx.setDistrBillUniqueList(uniqueList);
						}
						//List<DistrBillUniqueListVo> distrBillUniqueList_list = new ArrayList();
						else{
							for(int i=0;i< mx.getSendQty().intValue();i++){
								DistrBillUniqueListVo unique = new DistrBillUniqueListVo();
								String wymId =atomUtil.newValue("wym-id");
								unique.setId(wymId);
								unique.setPid(mx.getId());
								unique.setDistrRowNumber(mx.getRowNumber());
								unique.setGoodsName(mx.getHosGoodsName());
								unique.setBatchCode(mx.getBatchCode());
								unique.setProvGoodsId(mx.getProvGoodsId());
								unique.setUniqueCode(wymId);
								uniqueList.add(unique);
								//	int insertDistrUnique(List<DistrBillUniqueListVo> distrBillUniqueList) throws Exception ;


							}
						  //往唯一码表插入数据
							int re =distrBillService.insertDistrUnique(uniqueList);
							mx.setDistrBillUniqueList(uniqueList);
						}
					}
				}
			}
		result.setData(ret);
		return result;
	}

}
