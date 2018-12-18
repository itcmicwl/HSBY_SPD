package gyqx.spdhdi.myGoods.controller;

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

import gyqx.spdhdi.myGoods.service.IProHosGoodsinfoService;
import gyqx.spdhdi.myGoods.vo.ProHosGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.exception.ValidateException;
import common.transform.Tx;
import common.utils.EntityUtils;
import common.utils.UserOnlineStateUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import common.web.upload.UploadResult;
import gyqx.spdhdi.baseData.vo.BasGoodsInfoVo;
import gyqx.spdhdi.myGoods.service.IFileManagerService;
import gyqx.spdhdi.myGoods.service.IHosGoodsInfoService;
import gyqx.spdhdi.myGoods.service.IProvGoodsHosService;
import gyqx.spdhdi.myGoods.util.ConfigPropertiesUtils;
import gyqx.spdhdi.myGoods.util.ExportExcle;
import gyqx.spdhdi.myGoods.util.FileSave;
import gyqx.spdhdi.myGoods.util.NumberValidationUtils;
import gyqx.spdhdi.myGoods.util.ReadExcle;
import gyqx.spdhdi.myGoods.util.ReturnCodePropertiesUtils;
import gyqx.spdhdi.myGoods.vo.HosGoodsInfoVo;
import gyqx.spdhdi.myGoods.vo.ProvGoods4HosVo;
import gyqx.spdhdi.po.BasMfrsInfo;
import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdhdi.po.ProvGoodsHos;


@Controller
@RequestMapping(value = "myGoods/hosGoods")
public class HosGoodsInfoController extends BaseController {
	List<HosGoodsInfo> errors= new ArrayList<HosGoodsInfo>();

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
	private IProvGoodsHosService provGoodsHosService;
	@Resource
	private IHosGoodsInfoService hosGoodsInfoService;
	@Resource
	private IFileManagerService iFMService ;
	@Autowired
	private IProHosGoodsinfoService iProHosGoodsinfoService;
	@RequestMapping(value = "insert")
	@ResponseBody
	public AjaxResult insert(@RequestBody @Valid HosGoodsInfoVo good, Errors error) throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		 if (simpleDao.checkExists(BasMfrsInfo.class, "id,mfrsName,", good.getMfrsId(),good.getMfrsName()) == 0) {
				ValidateException.Throw("mfrsId","您输入的厂商 ："+ good.getMfrsName()+ "不存在，请重新选择!", good.getMfrsName());
			}
		//good.setHosId("company-009");//默认设置 ，需要从 用户身份中取值
		good.setHosId(userOnline.getCurrent().getCorpId());
		HosGoodsInfo goodInfo = new HosGoodsInfo();
		Tx.transform(good).to(goodInfo);
		goodInfo= hosGoodsInfoService.addHosGood(goodInfo);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid HosGoodsInfoVo good, Errors error) throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		  if (simpleDao.checkExists(BasMfrsInfo.class, "id,mfrsName,", good.getMfrsId(),good.getMfrsName()) == 0) {
				ValidateException.Throw("mfrsId","您输入的厂商 ："+ good.getMfrsName()+ "不存在，请重新选择!", good.getMfrsName());
			}
		
		HosGoodsInfo goodInfo = new HosGoodsInfo();
		Tx.transform(good).to(goodInfo);
		// 只能查询本供应商的商品  
		goodInfo.setHosId(userOnline.getCurrent().getCorpId());//默认设置 ，需要从 用户身份中取值
		goodInfo= hosGoodsInfoService.updateHosGood(goodInfo);
		return result;
	}
	
	
	@RequestMapping(value = "delGoodsInfo")
	@ResponseBody
	public AjaxResult delGoodsInfo(@RequestBody HosGoodsInfoVo good) throws Exception {
		
		AjaxResult result = new AjaxResult();
		
		HosGoodsInfo goodInfo = new HosGoodsInfo();
		Tx.transform(good).to(goodInfo);
		hosGoodsInfoService.deleteHosGood(goodInfo);
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
	
	
	@RequestMapping(value = "getGoodsInfos")
	@ResponseBody
	public AjaxResult getGoodsInfos(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryInfo<HosGoodsInfo> queryInfo)
			throws Exception {
		// 只能查询本供应商的商品  
		queryInfo.getQueryObject().setHosId(userOnline.getCurrent().getCorpId());
		QueryResult<HosGoodsInfoVo> queryResult=   hosGoodsInfoService.listHosGoods(queryInfo);
		AjaxResult result = new AjaxResult();
		result.setData(queryResult);
		return result;
	}
	
	@RequestMapping(value = "getHosGoodsByHos")
	@ResponseBody
	public AjaxResult getHosGoodsByHos(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryInfo<HosGoodsInfoVo> queryInfo)
			throws Exception {
		// 传入医院Id
		//queryInfo.getQueryObject().setHosId(userOnline.getCurrent().getCorpId());
		QueryResult<HosGoodsInfoVo> queryResult=   hosGoodsInfoService.listHosGoods4CodeMgr(queryInfo);
		AjaxResult result = new AjaxResult();
		result.setData(queryResult);
		return result;
	}

	@RequestMapping(value = "getHosGoodsCountByHos")
	@ResponseBody
	public Integer getHosGoodsCountByHos(@RequestBody QueryInfo<HosGoodsInfoVo> queryInfo) {
		return hosGoodsInfoService.listHosGoodsCount4CodeMgr(queryInfo);
	}

	@RequestMapping(value = "goodImprotFromBasGoods")
	@ResponseBody
	public AjaxResult goodImprotFromBasGoods(HttpServletRequest request, HttpServletResponse response, @RequestBody ArrayList<BasGoodsInfoVo> basGoods)
			throws Exception {
	  
		AjaxResult result = new AjaxResult();
	//	hosGoodsInfoService.addHosGoodS(goods);
		List<HosGoodsInfo> hosGoods = new ArrayList<HosGoodsInfo>();
		for(BasGoodsInfoVo basGoodVo:basGoods){
			HosGoodsInfo hosGood = new HosGoodsInfo();
			hosGood = hosGoodsInfoService.convertBasGood2HosGood(basGoodVo, hosGood);
			hosGoods.add(hosGood);
		}
		hosGoodsInfoService.addHosGoodS(hosGoods);
		return result;
	}
	@RequestMapping(value = "goodImprotFromPHGoods")
	@ResponseBody
	public AjaxResult goodImprotFromPHGoods(HttpServletRequest request, HttpServletResponse response, @RequestBody ArrayList<ProvGoods4HosVo> phGoodsVos)
			throws Exception {
	
		AjaxResult result = new AjaxResult();
	//	hosGoodsInfoService.addHosGoodS(goods);
		List<HosGoodsInfo> hosGoods = new ArrayList<HosGoodsInfo>();
		for(ProvGoods4HosVo phGoodVo:phGoodsVos){
			HosGoodsInfo hosGood = new HosGoodsInfo();
			hosGood = hosGoodsInfoService.convertPHGood2HosGood(phGoodVo, hosGood);
			hosGoods.add(hosGood);
		}
		hosGoodsInfoService.addHosGoodS(hosGoods);
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
	
	@RequestMapping(value = "importExcel")
	@ResponseBody
	public AjaxResult importExcel(HttpServletRequest request, HttpServletResponse response,@RequestBody Map map )throws Exception {   
		AjaxResult result = new AjaxResult();
	    	errors.clear();
			if (!StringUtils.isEmpty(map.get("id"))) {
			String fileURL = ConfigPropertiesUtils.getValue("fileUploadURL") + map.get("path") + "/" + map.get("id") + "." + map.get("ext");
			byte[] btImg = FileSave.getImageFromNetByUrl(fileURL);
			String path = request.getSession().getServletContext().getRealPath("/");
			String fileName = map.get("id") + "." + map.get("ext");
			FileSave.writeImageToDisk(btImg, fileName, path);
			String localFilePath = path + fileName;
			ReadExcle read = new ReadExcle();
			List<HosGoodsInfo> pgs = new ArrayList<HosGoodsInfo>();
			List<List<Object>> list = read.read2003Excel(new File(localFilePath));
			for (int i = 1; i < list.size(); i++) {
				List kk = list.get(i);
				HosGoodsInfo pp = new HosGoodsInfo();
				Map mapre = convert2HosGoodsInfo(kk, pp );
			    if(mapre.get("ifErrorCel").toString().equals("true")){
			    	//数字转换有错
			    	errors.add((HosGoodsInfo)mapre.get("pp"));
			    }
			    else{		
				    pgs.add(pp);
				   }
		
			}
	         List<HosGoodsInfo>    xx=	hosGoodsInfoService.addHosGoodS4ImportExcel(pgs);//需要处理mfrsId 及失败返回信息
	         errors.addAll(xx);

			
		}
		result.setData(errors);
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

	
	@RequestMapping(value = "disConnect")
	@ResponseBody
	public AjaxResult disConnect(@RequestBody @Valid HosGoodsInfoVo good, Errors error  )throws Exception {			
		AjaxResult result = new AjaxResult();
		HosGoodsInfo goodInfo = new HosGoodsInfo();
		Tx.transform(good).to(goodInfo);
//		goodInfo.setLbsx(null);
	//	goodInfo = 	hosGoodsInfoService.updateHosGood(goodInfo);
		hosGoodsInfoService.deleteHosGood(goodInfo);
		return result;
	}
	
	/**
	 * 撤销对码
	 * @param good
	 * @param error
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cancelCode")
	@ResponseBody
	public AjaxResult cancelCode(@RequestBody @Valid HosGoodsInfoVo good, Errors error  )throws Exception {			
		AjaxResult result = new AjaxResult();
		HosGoodsInfo goodInfo = new HosGoodsInfo();
		Tx.transform(good).to(goodInfo);
		//清空医院产品供应商id
		goodInfo.setProvId(null);
		goodInfo = 	hosGoodsInfoService.updateHosGood(goodInfo);
		//删除对应对码关系
		ProvGoodsHos provgood = (ProvGoodsHos)simpleDao.getByFieldName(ProvGoodsHos.class, "hisCode", goodInfo.getCode());
		provGoodsHosService.delProvGoodsHosPhysical(provgood);
		return result;
	}
	@GetMapping("getGoodsInfov/{query}")
	@ResponseBody
	public AjaxResult getGoodsInfov(@PathVariable("query")String query) throws Exception{
		AjaxResult result = new AjaxResult();
        ProHosGoodsVo queryBean = new ProHosGoodsVo();
        queryBean.setQuery(query);
        queryBean.setLimit(20);
		List<ProHosGoodsVo> lst = iProHosGoodsinfoService.list(queryBean);
		result.setData(lst);
		return result;
	}
	@PostMapping("changeHosGoods/{newGoodsId}/{oldGoodsId}/{newErpCode}")
	@ResponseBody
	public AjaxResult changeHosGoods(@PathVariable("newGoodsId")String newGoodsId,
									 @PathVariable("oldGoodsId")String oldGoodsId,
									 @PathVariable("newErpCode")String newErpCode) throws Exception{
		AjaxResult result = new AjaxResult();
		Integer res = 0;
		if(StringUtils.hasText(newGoodsId) && StringUtils.hasText(oldGoodsId) && StringUtils.hasText(newErpCode)){
			res = hosGoodsInfoService.updateChangeHosGoods(newGoodsId,oldGoodsId,newErpCode);
		}else{
			throw new Exception("产品信息不全！");
		}
		result.setData(res);
		return result;
	}
}
