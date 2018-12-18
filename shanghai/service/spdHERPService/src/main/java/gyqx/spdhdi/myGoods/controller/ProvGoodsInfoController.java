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
import common.utils.UserOnlineStateUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import common.web.upload.UploadResult;
import gyqx.spdhdi.baseData.vo.BasGoodsInfoVo;
import gyqx.spdhdi.myGoods.service.IFileManagerService;
import gyqx.spdhdi.myGoods.service.IProvGoodsInfoService;
import gyqx.spdhdi.myGoods.util.ConfigPropertiesUtils;
import gyqx.spdhdi.myGoods.util.ExportExcle;
import gyqx.spdhdi.myGoods.util.FileSave;
import gyqx.spdhdi.myGoods.util.NumberValidationUtils;
import gyqx.spdhdi.myGoods.util.ReadExcle;
import gyqx.spdhdi.myGoods.util.ReturnCodePropertiesUtils;
import gyqx.spdhdi.myGoods.vo.ProvGoodsInfoVo;
import gyqx.spdhdi.po.BasMfrsInfo;
import gyqx.spdhdi.po.ProvGoodsHos;
import gyqx.spdhdi.po.ProvGoodsInfo;


@Controller
@RequestMapping(value = "myGoods/provGoods")
public class ProvGoodsInfoController extends BaseController {
	List<ProvGoodsInfo> errors= new ArrayList<ProvGoodsInfo>();
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
	private IProvGoodsInfoService provGoodsInfoService;
	@Resource
	private IFileManagerService iFMService ;
	@RequestMapping(value = "insert")
	@ResponseBody
	public AjaxResult insert(@RequestBody @Valid ProvGoodsInfoVo good, Errors error) throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		ProvGoodsInfo goodInfo = new ProvGoodsInfo();
		Tx.transform(good).to(goodInfo);
		good.setProvId(userOnline.getCurrent().getCorpId());//默认设置 ，需要从 用户身份中取值"company-009"
		goodInfo= provGoodsInfoService.addProvGood(goodInfo);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid ProvGoodsInfoVo good, Errors error) throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		if (simpleDao.checkExists(BasMfrsInfo.class, "id,mfrsName,", good.getMfrsId(),good.getMfrsName()) == 0) {
			ValidateException.Throw("mfrsId","您输入的厂商 ："+ good.getMfrsName()+ "不存在，请重新选择!", good.getMfrsName());
		}
		ProvGoodsInfo goodInfo = new ProvGoodsInfo();
		Tx.transform(good).to(goodInfo);
		good.setProvId(userOnline.getCurrent().getCorpId());//默认设置 ，需要从 用户身份中取值"company-009"		
		goodInfo= provGoodsInfoService.updateProvGood(goodInfo);
		return result;
	}
	@RequestMapping(value = "delGoodsInfo")
	@ResponseBody
	public AjaxResult delGoodsInfo(@RequestBody @Valid ProvGoodsInfoVo good, Errors error) throws Exception {
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		ProvGoodsInfo goodInfo = new ProvGoodsInfo();
		Tx.transform(good).to(goodInfo);
		provGoodsInfoService.deleteProvGood(goodInfo);
		return result;
	}
	
	@RequestMapping(value = "multDel")
	@ResponseBody
	public AjaxResult multDelGoodsInfo(@RequestBody ArrayList<ProvGoodsInfoVo> provGoods) throws Exception {
		AjaxResult result = new AjaxResult();
	
		List<ProvGoodsInfo> goods = new ArrayList<ProvGoodsInfo>();
		for(ProvGoodsInfoVo pgv:provGoods ){
			ProvGoodsInfo pg = new ProvGoodsInfo();
			Tx.transform(pgv).to(pg);
			goods.add(pg);
		}
		int re =provGoodsInfoService.multDelProvGoods(goods);
	     if(re>0){
	    	 result.setCode( Integer.parseInt(ReturnCodePropertiesUtils.getValue("mulDel.errorCode"))); //999
	    	 String msg= ReturnCodePropertiesUtils.getValue("mulDel.errorCode.message");
	    	 result.setMsg("您选择的"+provGoods.size()+"条记录中有"+re+"条"+msg);
	     }
		return result;
	}
	
	@RequestMapping(value = "getProvGoodsInfoListByProvAndHos_Pages")
	@ResponseBody
	public AjaxResult getProvGoodsInfoListByProvAndHos_Pages(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryInfo<ProvGoodsHos> queryInfo)
			throws Exception {
		
	
		QueryResult<ProvGoodsInfoVo> queryResult=   provGoodsInfoService.getProvGoodsInfoListByProvAndHos_Pages(queryInfo);
		AjaxResult result = new AjaxResult();
		result.setData(queryResult);
		return result;
	}
	
	
	@RequestMapping(value = "getGoodsInfos")
	@ResponseBody
	public AjaxResult getGoodsInfos(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryInfo<ProvGoodsInfo> queryInfo)
			throws Exception {
		// 只能查询本供应商的商品  
		queryInfo.getQueryObject().setProvId(userOnline.getCurrent().getCorpId());
		QueryResult<ProvGoodsInfoVo> queryResult=   provGoodsInfoService.listProvGoods(queryInfo);
		AjaxResult result = new AjaxResult();
		result.setData(queryResult);
		return result;
	}
	
	@RequestMapping(value = "exportGoodsInfos")
	@ResponseBody
	public AjaxResult exportGoodsInfos(HttpServletRequest request, HttpServletResponse response, @RequestBody String[] columns )
			throws Exception {   
		AjaxResult result = new AjaxResult();
		QueryInfo<ProvGoodsInfo> queryInfo = new QueryInfo();
		queryInfo.setQueryObject(new ProvGoodsInfo());

		// 只能查询本供应商的商品  
		queryInfo.getQueryObject().setProvId(userOnline.getCurrent().getCorpId());
		QueryResult<ProvGoodsInfoVo> queryResult=   provGoodsInfoService.listProvGoods(queryInfo);
	
		result.setData(queryResult);
		//String classPath = Thread.currentThread().getContextClassLoader().getParent().getResource("/").toString();
	   //String docsPath = request.getSession().getServletContext().getRealPath("docs");
		String docsPath = request.getSession().getServletContext().getRealPath("docs");
		String tempFileName = "export2003_ProvGoodsInfo"+UUID.randomUUID().toString()+".xls";
				OutputStream out = new FileOutputStream(docsPath + FILE_SEPARATOR + tempFileName);
		        List<ProvGoodsInfoVo> data = queryResult.getData();
		    	ExportExcle<ProvGoodsInfoVo> ex = new ExportExcle<ProvGoodsInfoVo>();
		    	ex.exportExcel(columns, data, out);
		    	//String fileName = tempFileName;
				String filePath = docsPath + FILE_SEPARATOR + tempFileName;
				File f = new File(filePath);
				UploadResult uresult =  iFMService.uploadFile(f,"myGoods",tempFileName);
			   //http://localhost/upload/xls/6/f/6f56b60e7d6909a76b70f73bfa32ac2517cb9bccaa5c2fbbd85645d18c1d7a171a00.xls
				result.setCode( Integer.parseInt(ReturnCodePropertiesUtils.getValue("myGoods.exportGoodsInfos.exportReCode"))); //998
				String src =ConfigPropertiesUtils.getValue("fileUploadURL4back")+uresult.getPath()+"/"+uresult.getId()+"."+uresult.getExt();
				result.setMsg(src);
		    //	changeVo(data,columns);
		return result;
	}
	
	@RequestMapping(value = "importExcel")
	@ResponseBody
	public AjaxResult importExcel(HttpServletRequest request, HttpServletResponse response,@RequestBody Map map )
			throws Exception {   
		AjaxResult result = new AjaxResult();
	 	errors.clear();
		List<ProvGoodsInfo> errors= new ArrayList<ProvGoodsInfo>();
		if (!StringUtils.isEmpty(map.get("id"))) {
			String fileURL = ConfigPropertiesUtils.getValue("fileUploadURL")+map.get("path") + "/" + map.get("id") + "." + map.get("ext");
			byte[] btImg = FileSave.getImageFromNetByUrl(fileURL);
			String path = request.getSession().getServletContext().getRealPath("/");
			String fileName = map.get("id") + "." + map.get("ext");
			FileSave.writeImageToDisk(btImg, fileName, path);
			String localFilePath = path + fileName;
			ReadExcle read = new ReadExcle();
			List<ProvGoodsInfo> pgs = new ArrayList<ProvGoodsInfo>();
			List<List<Object>> list = read.read2003Excel(new File(localFilePath));
			for (int i = 1; i < list.size(); i++) {
				List kk = list.get(i);
				ProvGoodsInfo pp = new ProvGoodsInfo();
				Map mapre = convert2ProvGoodsInfo(kk, pp);
				 if(mapre.get("ifErrorCel").toString().equals("true")){
				    	//数字转换有错
				    	errors.add((ProvGoodsInfo)mapre.get("pp"));
				    }
				    else{		
					    pgs.add(pp);
					   }
			}
				
	
		  List<ProvGoodsInfo>    xx=	provGoodsInfoService.addProvGoodS4ImportExcel(pgs);//需要处理mfrsId 及失败返回信息
		   errors.addAll(xx);

			
		}
		result.setData(errors);

		return result;
	}
	
	private void changeVo( List<ProvGoodsInfoVo> data,String[] columns ) throws Exception{
		 Field[] fields = ProvGoodsInfo.class.getDeclaredFields();
		for (int i=0;i<data.size();i++){
				ProvGoodsInfoVo p = data.get(i);
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
		Map<String,String> map =	EntityUtils.getFieldsCName(ProvGoodsInfoVo.class);
		result.setData(map.values());
		return result;
	}	
	@RequestMapping(value = "goodImprotFromBasGoods")
	@ResponseBody
	public AjaxResult goodImprotFromBasGoods(HttpServletRequest request, HttpServletResponse response, @RequestBody ArrayList<BasGoodsInfoVo> basGoods)
			throws Exception {
		AjaxResult result = new AjaxResult();
	//	hosGoodsInfoService.addHosGoodS(goods);
		List<ProvGoodsInfo> provGoods = new ArrayList<ProvGoodsInfo>();
		for(BasGoodsInfoVo basGoodVo:basGoods){
			ProvGoodsInfo provGood = new ProvGoodsInfo();
			provGood = provGoodsInfoService.convertBasGood2ProvGood(basGoodVo, provGood);
			provGoods.add(provGood);
		}
		provGoodsInfoService.addProvGoodS(provGoods);
		return result;
	}

    private Map convert2ProvGoodsInfo(List<Object> kk,ProvGoodsInfo pp){
    	  boolean ifErrorCel = false;
		  Map map = new HashMap<>();
		  pp.setRemark("");
    	    if(  !StringUtils.isEmpty(kk.get(0)))
			   pp.setGoodsGg(kk.get(0).toString());
           if(  !StringUtils.isEmpty(kk.get(1))){
		    	
		    	if(NumberValidationUtils.isDecimal(kk.get(1).toString())){
		    	
		    	    pp.setHitPrice(new BigDecimal(kk.get(1).toString()));
		    	}
		    	else{
		    		 ifErrorCel =true;
		    		
		    		 pp.setRemark("中标价必须为数字");
		    		
		    	}
		    }
		   /* if(  !StringUtils.isEmpty(kk.get(1)))
			   pp.setHitPrice(new BigDecimal(kk.get(1).toString()));*/
		    if(  !StringUtils.isEmpty(kk.get(2)))
			   pp.setMasterCode(kk.get(2).toString());
		    if(  !StringUtils.isEmpty(kk.get(3)))
		    	 pp.setCode(kk.get(3).toString());
		    if(  !StringUtils.isEmpty(kk.get(4)))
		    	 pp.setKindCode(kk.get(4).toString());
		    if(  !StringUtils.isEmpty(kk.get(5)))   //厂商名称 需要从库中查找其mfrsId
		    	 pp.setMfrsId(kk.get(5).toString()+"@@4mfrsName");
		    if(  !StringUtils.isEmpty(kk.get(6)))
		    	 pp.setRemark(kk.get(6).toString());
            if(  !StringUtils.isEmpty(kk.get(7))){
		    	
		    	if(NumberValidationUtils.isDecimal(kk.get(7).toString())){
		    	
		    	    pp.setPackeage(new BigDecimal(kk.get(7).toString()));
		    	}
		    	else{
		    		 ifErrorCel =true;
		    		
		    		 pp.setRemark("包装必须为数字");
		    		
		    	}
		    }
		   /* if(  !StringUtils.isEmpty(kk.get(7)))
		    	 pp.setPackeage(new BigDecimal(kk.get(7).toString()));*/

		    if(  !StringUtils.isEmpty(kk.get(8)))
		    	 pp.setHitCode(kk.get(8).toString());
		    if(  !StringUtils.isEmpty(kk.get(9)))
		    	 pp.setErpCode(kk.get(9).toString());
		    if(  !StringUtils.isEmpty(kk.get(10)))
		    	 pp.setGoodsName(kk.get(10).toString());
		    if(  !StringUtils.isEmpty(kk.get(11)))
		    	 pp.setKind68code(kk.get(11).toString());
		    if(  !StringUtils.isEmpty(kk.get(12)))
		    	pp.setMade(kk.get(12).toString());
		  /*  if(  !StringUtils.isEmpty(kk.get(14)))
		    	pp.setSpdGoodsCode(kk.get(14).toString());*/
		    	//pp.setFieldCode1(kk.get(21).toString());//将fieldCode1改为了spdGoodsCode
		    if(  !StringUtils.isEmpty(kk.get(13)))
		    	 pp.setLbsx(kk.get(13).toString());
		    if(  !StringUtils.isEmpty(kk.get(14)))
		    	 pp.setUnit(kk.get(14).toString());
		  
		    if(  !StringUtils.isEmpty(kk.get(15)))
		    	 pp.setMfrsCode(kk.get(15).toString());
		    map.put("ifErrorCel", ifErrorCel);
			   map.put("pp", pp);
    	return map;
    	
    }
}
