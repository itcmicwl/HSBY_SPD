package gyqx.spdherp.medicMgr.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.transform.Tx;
import common.utils.EntityUtils;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import common.web.upload.UploadResult;
import gyqx.spdhdi.myGoods.service.IFileManagerService;
import gyqx.spdhdi.myGoods.service.IHosGoodsInfoService;
import gyqx.spdhdi.myGoods.util.ConfigPropertiesUtils;
import gyqx.spdhdi.myGoods.util.ExportExcle;
import gyqx.spdhdi.myGoods.util.NumberValidationUtils;
import gyqx.spdhdi.myGoods.util.ReturnCodePropertiesUtils;
import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdhdi.po.BasMfrsInfo;
import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdherp.medicMgr.service.IMedicHosGoodsInfoService;
import gyqx.spdherp.medicMgr.vo.MedicHosGoodsInfoVo;
import gyqx.spdherp.po.MedicalGoodsInfo;


@Controller
@RequestMapping(value = "medicMgr/medicHosGoods")
public class MedicHosGoodsInfoController extends BaseController {
//	List<HosGoodsInfo> errors= new ArrayList<HosGoodsInfo>();

	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	private final static String date_partterns[] ;
	static{
		
		date_partterns = new String[7];
		date_partterns[0] = "yyyy-MM-dd";
		date_partterns[1] = "yyyy/MM/dd";		
		date_partterns[2] = "yyyy-MM-dd HH:mm:ss";
		date_partterns[3] = "yyyy-MM-dd HH:mm";
		date_partterns[4] = "yyyy/MM/dd HH:mm:ss";
		date_partterns[5] = "yyyy/MM/dd HH:mm";
		date_partterns[6] = "yyyy年MM月dd日";
	}	
	@Resource
	private SimpleDao simpleDao;
	@Resource
	private UserOnlineStateUtils userOnline;
	@Resource
	private IHosGoodsInfoService hosGoodsInfoService;
	@Resource
	private IFileManagerService iFMService ;
	@Resource
	private IMedicHosGoodsInfoService iMedicHosGoodsInfoService;
	@Resource
	private SysAtomUtil atomUtil;
	
	/**
	 * 新增试剂产品信息
	 * @param good
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "insert")
	@ResponseBody
	public AjaxResult insert(@RequestBody @Valid MedicHosGoodsInfoVo good, Errors error) throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		String id = atomUtil.newValue("medical_goods_info_id");
		MedicalGoodsInfo medicalGoodInfo = new MedicalGoodsInfo();
		medicalGoodInfo.setId(id);
		medicalGoodInfo.setHosId(good.getHosId());
		medicalGoodInfo.setGoodsId(good.getId());//由于前端绑定的字段Id，对应的是Hos_goods_info表中对应的id,该id就是 对应medical_goods_info中的goods_ido
		medicalGoodInfo.setTemperatureLower(good.getTemperatureLower());
		medicalGoodInfo.setTemperatureUpper(good.getTemperatureUpper());
		medicalGoodInfo = iMedicHosGoodsInfoService.addMedicHosGood(medicalGoodInfo);
		return result;
	}
	
	/**
	 * 修改试剂产品信息
	 * @param good
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid MedicHosGoodsInfoVo good, Errors error) throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		MedicalGoodsInfo medicalGoodInfo = new MedicalGoodsInfo();
		medicalGoodInfo.setId(good.getMedicHosGoodsId());
		medicalGoodInfo.setHosId(good.getHosId());
		medicalGoodInfo.setGoodsId(good.getGoodsId());
		medicalGoodInfo.setTemperatureLower(good.getTemperatureLower());
		medicalGoodInfo.setTemperatureUpper(good.getTemperatureUpper());
		iMedicHosGoodsInfoService.updateMedicHosGood(medicalGoodInfo);
		return result;
	}
	
	
	@RequestMapping(value = "delMedicGoodsInfo")
	@ResponseBody
	public AjaxResult delMedicGoodsInfo(@RequestBody MedicHosGoodsInfoVo good) throws Exception {
		
		AjaxResult result = new AjaxResult();
		
		MedicalGoodsInfo medicalGoodInfo = new MedicalGoodsInfo();
		medicalGoodInfo.setId(good.getMedicHosGoodsId());
		medicalGoodInfo.setHosId(good.getHosId());
		medicalGoodInfo.setGoodsId(good.getGoodsId());
		medicalGoodInfo.setTemperatureLower(good.getTemperatureLower());
		medicalGoodInfo.setTemperatureUpper(good.getTemperatureUpper());
		iMedicHosGoodsInfoService.deleteMedicHosGood(medicalGoodInfo);
		return result;
	}
	
	@RequestMapping(value = "multDel")
	@ResponseBody
	public AjaxResult multDel(@RequestBody ArrayList<HosGoodsInfoVo> hosGoods) throws Exception {
		AjaxResult result = new AjaxResult();
		List<HosGoodsInfo> goods = new ArrayList<HosGoodsInfo>();
		for(HosGoodsInfoVo pgv:hosGoods ){
			HosGoodsInfo pg = new HosGoodsInfo();
			Tx.transform(pgv).to(pg);
			goods.add(pg);
		}
		hosGoodsInfoService.multDelHosGoods(goods);
		return result;
	}
	
	
	
	/**
	 * 试剂产品信息维护主页面数据
	 * @param request 
	 * @param response
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getMedicHosGoodsByHos")
	@ResponseBody
	public AjaxResult getMedicHosGoodsByHos(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryInfo<MedicHosGoodsInfoVo> queryInfo)
			throws Exception {
		// 传入医院Id
		//queryInfo.getQueryObject().setHosId(userOnline.getCurrent().getCorpId());
		QueryResult<MedicHosGoodsInfoVo> queryResult=   iMedicHosGoodsInfoService.listMedicHosGoods4MedicMgr(queryInfo);
		AjaxResult result = new AjaxResult();
		result.setData(queryResult);
		return result;
	}
	
	
	
	@RequestMapping(value = "exportGoodsInfos")
	@ResponseBody
	public AjaxResult exportGoodsInfos(HttpServletRequest request, HttpServletResponse response, @RequestBody String[] columns )
			throws Exception {   
				AjaxResult result = new AjaxResult();
				QueryInfo<HosGoodsInfo> queryInfo = new QueryInfo();
				queryInfo.setQueryObject(new HosGoodsInfo());
				// 只能查询本医院的商品  
				queryInfo.getQueryObject().setHosId(userOnline.getCurrent().getCorpId());
				QueryResult<HosGoodsInfoVo> queryResult=   hosGoodsInfoService.listHosGoods(queryInfo);
				result.setData(queryResult);
				String docsPath = request.getSession().getServletContext().getRealPath("docs");
				String tempFileName = "export2003_HosGoodsInfo"+UUID.randomUUID().toString()+".xls";
				OutputStream out = new FileOutputStream(docsPath + FILE_SEPARATOR + tempFileName);
		        List<HosGoodsInfoVo> data = queryResult.getData();
		    	ExportExcle<HosGoodsInfoVo> ex = new ExportExcle<HosGoodsInfoVo>();
		    	ex.exportExcel(columns, data, out);
		    	String filePath = docsPath + FILE_SEPARATOR + tempFileName;
				File f = new File(filePath);
				UploadResult uresult =  iFMService.uploadFile(f,"myGoods",tempFileName);
				result.setCode( Integer.parseInt(ReturnCodePropertiesUtils.getValue("myGoods.exportGoodsInfos.exportReCode"))); //998
				String src =ConfigPropertiesUtils.getValue("fileUploadURL4back")+uresult.getPath()+"/"+uresult.getId()+"."+uresult.getExt();
				result.setMsg(src);
		    	
		    //	changeVo(data,columns);
		return result;
	}
	
	
	private void changeVo( List<HosGoodsInfoVo> data,String[] columns ) throws Exception{
		 Field[] fields = HosGoodsInfo.class.getDeclaredFields();
	
		for (int i=0;i<data.size();i++){
				HosGoodsInfoVo p = data.get(i);
				for(int j=0;j<columns.length;j++){
					   for(int k=0;k<fields.length;k++){
						
						    String  theField = fields[k].toString();
							theField =theField.substring(theField.lastIndexOf(".")+1,theField.length());
							String theFieldtTitle=	EntityUtils.getFieldCName(p.getClass(), theField);
							   if(theFieldtTitle.equals(columns[j])){
								   break;
							   }
							   
							
					   }
				}
		}
		
		
	}
	@RequestMapping(value = "getAllColumns")
	@ResponseBody
	public AjaxResult getAllColumns(HttpServletRequest request, HttpServletResponse response)throws Exception {  
		AjaxResult result = new AjaxResult();
		//构造T中的title
		Map<String,String> map =	EntityUtils.getFieldsCName(HosGoodsInfoVo.class);
		result.setData(map.values());
		return result;
	}	
	 private Map convert2HosGoodsInfo(List<Object> kk,HosGoodsInfo pp) throws Exception{
		  boolean ifErrorCel = false;
		  Map map = new HashMap<>();
		  pp.setRemark("");
 	    if(  !StringUtils.isEmpty(kk.get(0)))
			   pp.setGoodsName(kk.get(0).toString());
		    if(  !StringUtils.isEmpty(kk.get(1)))
			   pp.setCode(kk.get(1).toString());
		    if(  !StringUtils.isEmpty(kk.get(2)))
			   pp.setKindCode(kk.get(2).toString());
		    if(  !StringUtils.isEmpty(kk.get(3)))
		    	 pp.setUnit(kk.get(3).toString());
		    if(  !StringUtils.isEmpty(kk.get(4))){
		    	
		    	if(NumberValidationUtils.isDecimal(kk.get(4).toString())){
		    	
		    	    pp.setPackeage(new BigDecimal(kk.get(4).toString()));
		    	}
		    	else{
		    		 ifErrorCel =true;
		    		
		    		 pp.setRemark("包装必须为数字");
		    		
		    	}
		    }
		    if(  !StringUtils.isEmpty(kk.get(5)))   
		    	 pp.setGoodsGg(kk.get(5).toString());
		    if(  !StringUtils.isEmpty(kk.get(6)))
		    	 pp.setMade(kk.get(6).toString());
		    if(  !StringUtils.isEmpty(kk.get(7)))
		    	 pp.setMfrsId(kk.get(7).toString()+"@@4mfrsName");
		    if(  !StringUtils.isEmpty(kk.get(8)))
		    	 pp.setMfrsCode(kk.get(8).toString());
		    if(  !StringUtils.isEmpty(kk.get(9)))
		    	 pp.setLbsx(kk.get(9).toString());
		    if(  !StringUtils.isEmpty(kk.get(10)))
		    	 pp.setHosMfrsName(kk.get(10).toString());
           if(  !StringUtils.isEmpty(kk.get(11))){
		    	
		    	if(NumberValidationUtils.isDecimal(kk.get(11).toString())){
		    	
		    	    pp.setHitPrice(new BigDecimal(kk.get(11).toString()));
		    	}
		    	else{
		    		 ifErrorCel =true;
		    		 pp.setRemark(pp.getRemark()+"/阳光采购价必须为数字");
		    	}
		    }
		  /*  if(  !StringUtils.isEmpty(kk.get(11)))
		    	 pp.setHitPrice(new BigDecimal(kk.get(11).toString()));*/
           
		    if(  !StringUtils.isEmpty(kk.get(12)))
		    	 pp.setMasterCode(kk.get(12).toString());
		    if(  !StringUtils.isEmpty(kk.get(13)))
		    	 pp.setRemark(kk.get(13).toString());
		    
            if(  !StringUtils.isEmpty(kk.get(14))){
		    	
		    	if(NumberValidationUtils.isDecimal(kk.get(14).toString())){
		    	
		    	    pp.setHisPrice(new BigDecimal(kk.get(14).toString()));
		    	}
		    	else{
		    		 ifErrorCel =true;
		    		 pp.setRemark(pp.getRemark()+"/HIS销售价必须为数字");
		    		
		    	}
            }
		  /*  if(  !StringUtils.isEmpty(kk.get(14)))
		    	 pp.setHisPrice(new BigDecimal(kk.get(14).toString()));*/
		    /*if(  !StringUtils.isEmpty(kk.get(15)))
		    	 pp.setPrice(new BigDecimal(kk.get(15).toString()));*/
		    	  if(  !StringUtils.isEmpty(kk.get(15))){
				    	
				    	if(NumberValidationUtils.isDecimal(kk.get(14).toString())){
				    	
				    	    pp.setPrice(new BigDecimal(kk.get(15).toString()));
				    	}
				    	else{
				    		 ifErrorCel =true;
				    		 pp.setRemark(pp.getRemark()+"/单价必须为数字");
				    	}
		    	  }
		    if(  !StringUtils.isEmpty(kk.get(16)))
		    	 pp.setHitCode(kk.get(16).toString());
		    if(  !StringUtils.isEmpty(kk.get(17)))
		    	 pp.setErpCode(kk.get(17).toString());
		    if(  !StringUtils.isEmpty(kk.get(18)))
		    	 pp.setKind68code(kk.get(18).toString());
		    if(  !StringUtils.isEmpty(kk.get(19)))
		    	 pp.setSpdGoodsCode(kk.get(19).toString());
		    if(  !StringUtils.isEmpty(kk.get(20))){
		    	
		    	if(NumberValidationUtils.isDecimal(kk.get(14).toString())){
		    	
		    	    pp.setTaxRate(new BigDecimal(kk.get(20).toString()));
		    	}
		    	else{
		    		 ifErrorCel =true;
		    		 pp.setRemark(pp.getRemark()+"/税率必须为数字");
		    	}
    	  }
		   map.put("ifErrorCel", ifErrorCel);
		   map.put("pp", pp);
 	return map;
 	
 }
	 
	 /**
	  * 根据id查询产品信息
	  * @param good
	  * @return
	  * @throws Exception
	  */
	@RequestMapping("/getHosGoodsInfoById")
	@ResponseBody
	public AjaxResult<HosGoodsInfoVo> getHosGoodsInfoById(@RequestBody HosGoodsInfoVo good) throws Exception {
		AjaxResult<HosGoodsInfoVo> res = new AjaxResult<>();
		HosGoodsInfoVo info = hosGoodsInfoService.getHosGoodsInfoById(good);
		res.setData(info);
		return res;
	}

}

