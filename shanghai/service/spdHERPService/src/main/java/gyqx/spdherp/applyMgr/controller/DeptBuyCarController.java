package gyqx.spdherp.applyMgr.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import common.db.SimpleDao;
import gyqx.spdherp.applyMgr.vo.DeptCarSurGoodsStVo;
import gyqx.spdherp.po.SurgeryPkgDefList;
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
import common.utils.UserOnlineStateUtils;
import common.web.AjaxResult;
import common.web.BaseController;
import gyqx.spdherp.applyMgr.service.IDeptBuyCarService;
import gyqx.spdherp.applyMgr.service.IDeptGoodsStVoService;
import gyqx.spdherp.applyMgr.vo.DeptCarGoodsStVo;
import gyqx.spdherp.applyMgr.vo.DeptGoodsStVo;
import gyqx.spdherp.po.DeptBuyCar;

@Controller
@RequestMapping(value = "applyMgr/deptBuyCar")
public class DeptBuyCarController extends BaseController 
{
	@Resource
	private UserOnlineStateUtils userOnline;
	@Resource
	private IDeptBuyCarService  deptBuyCarService;
	@Resource
	private SimpleDao simpleDao;
	@Resource
	private IDeptGoodsStVoService  deptGoodsStVoService;
	@RequestMapping(value = "add")
	@ResponseBody
	public AjaxResult add(@RequestBody @Valid DeptBuyCar deptBuyCar ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		DeptBuyCar ret = deptBuyCarService.add(deptBuyCar);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "get/{id}",method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult get(@PathVariable("id") String id  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		DeptBuyCar ret = deptBuyCarService.get(id);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "update")
	@ResponseBody
	public AjaxResult update(@RequestBody @Valid DeptBuyCar deptBuyCar ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		DeptBuyCar ret = deptBuyCarService.update(deptBuyCar);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxResult list(@RequestBody @Valid DeptBuyCar deptBuyCar ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<DeptBuyCar> ret = deptBuyCarService.list(deptBuyCar);
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "query")
	@ResponseBody
	public AjaxResult query(@RequestBody @Valid QueryInfo<Map<String,String>> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		List<DeptBuyCar> ret = deptBuyCarService.query(queryInfo.getPredicate(),queryInfo.getOrderBy(),queryInfo.getQueryObject());
		result.setData(ret);
		return result;
	}
	@RequestMapping(value = "listByPage")
	@ResponseBody
	public AjaxResult listByPage(@RequestBody @Valid QueryInfo<DeptBuyCar> queryInfo ,Errors error  )  throws  Exception  
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		QueryResult<DeptBuyCar> ret = deptBuyCarService.listByPage(queryInfo);
		result.setData(ret);
		return result;
	}
	/**
	 * 
	 * @param purMode 采购模式 10 实采 20 虚采
	 * @param hosId
	 * @param hGoods
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add2car/{purMode}/{hosId}/{deptId}")
	@ResponseBody
	public AjaxResult add2car(@PathVariable("purMode") String purMode ,@PathVariable("hosId") String hosId ,@PathVariable("deptId") String deptId, @RequestBody ArrayList<DeptGoodsStVo> hGoods)
			throws Exception {
	  
		AjaxResult result = new AjaxResult();
		List<DeptBuyCar> cars = new ArrayList<DeptBuyCar>();
		for(DeptGoodsStVo good:hGoods){
			DeptBuyCar c = new DeptBuyCar();
			c.setGoodsId(good.getGoodsId());
			c.setBuyKind(Integer.parseInt(purMode));
			c.setDeptId(deptId);
			c.setHosId(hosId);
//			if("20".equals(good.getPurMode())||"10".equals(good.getPurMode()))
//			c.setBuyKind(Integer.valueOf(good.getPurMode()));
			c.setBuyKind(10); //华山北院全部采用实际采购模式流程，
			c.setPurMode(Integer.parseInt(good.getPurMode())); // 新增purMode 用于设备科分类审核：10 低值 20 高值 30 办公
			c.setNeedQty(good.getNeedQty().intValue());
			cars.add(c);
		}
		deptBuyCarService.addList(cars);
	//	hosGoodsInfoService.addHosGoodS(hosGoods);
		return result;
	}
	
	/**
	 * 科室产品+库存 列表信息。 供添加到请购车
	 * @param request
	 * @param response
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDeptGoodsStVo")
	@ResponseBody
	public AjaxResult getDeptGoodsStVo(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryInfo<DeptGoodsStVo> queryInfo)
			throws Exception {
		// 传入医院Id
		AjaxResult result = new AjaxResult();
		if(queryInfo.getQueryObject().getPurMode()=="0"){
			queryInfo.getQueryObject().setPurMode(null);
		}
		queryInfo.getQueryObject().setOppertor(userOnline.getCurrent().getUserId());
		QueryResult<DeptGoodsStVo> queryResult=   deptGoodsStVoService.listByPage(queryInfo);
		
		result.setData(queryResult);
		return result;
	}
	
	/**
	 * 科室购物车 商品库存 供生成请购单
	 * @param request
	 * @param response
	 * @param queryInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDeptCarGoodsStVo")
	@ResponseBody
	public AjaxResult getDeptCarGoodsStVo(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryInfo<DeptCarGoodsStVo> queryInfo)
			throws Exception {
		// 传入医院Id
		AjaxResult result = new AjaxResult();
//		if(queryInfo.getQueryObject().getBuyKind().equals("0")){
//			queryInfo.getQueryObject().setBuyKind(null);    
//		}
		QueryResult<DeptCarGoodsStVo> queryResult=   deptBuyCarService.listByPage_deptCarGoodsStlist(queryInfo);
		result.setData(queryResult);
		return result;
	}

	@RequestMapping(value = "add2car4mixSur/{purMode}/{hosId}/{deptId}")
	@ResponseBody
	public AjaxResult add2car4mixSur(@PathVariable("purMode") String purMode ,@PathVariable("hosId") String hosId ,@PathVariable("deptId") String deptId, @RequestBody ArrayList<DeptGoodsStVo> hGoods)
			throws Exception {

		AjaxResult result = new AjaxResult();
		List<DeptBuyCar> cars = new ArrayList<DeptBuyCar>();
		List<DeptGoodsStVo> ssPkgLst= new ArrayList<DeptGoodsStVo>();
		List<DeptGoodsStVo> goodsLst= new ArrayList<DeptGoodsStVo>();
		for(DeptGoodsStVo g: hGoods){
			if(StringUtils.isEmpty(g.getSurId()))
				goodsLst.add(g);
			else
				ssPkgLst.add(g);

		}
		//处理非手术包
		for(DeptGoodsStVo good:goodsLst){
			DeptBuyCar c = new DeptBuyCar();
			c=deptGoodsStVo2DeptBuyCar(good,c,purMode,hosId,deptId);
			cars.add(c);
		}
		//处理手术包
		for(DeptGoodsStVo ssPkg :ssPkgLst){
		           //通过ssPkg.getSurId()获取手术包商品明细列表然后add到cars中
			List<SurgeryPkgDefList> surgeryPkgDefList_lst = new ArrayList<SurgeryPkgDefList>();
			surgeryPkgDefList_lst =simpleDao.queryForList("SELECT * FROM surgery_pkg_def_list where sur_id =?",SurgeryPkgDefList.class,ssPkg.getSurId());
		/*	SurgeryPkgDefList ssGood = new SurgeryPkgDefList();
			ssGood.setId("pkg-goods-1");
			ssGood.setHosId("h0001");
			ssGood.setSurId(ssPkg.getSurId());
			ssGood.setGoodsId("h0001|hosGood-00862");
			ssGood.setQty(new BigDecimal(2));
			surgeryPkgDefList_lst.add(ssGood);
			SurgeryPkgDefList ssGood2 = new SurgeryPkgDefList();
			ssGood2.setId("pkg-goods-1");
			ssGood2.setHosId("h0001");
			ssGood2.setSurId(ssPkg.getSurId());
			ssGood2.setGoodsId("h0001|hosGood-00184");
			ssGood2.setQty(new BigDecimal(1));
			surgeryPkgDefList_lst.add(ssGood2);*/
			for(SurgeryPkgDefList obj : surgeryPkgDefList_lst){
				DeptBuyCar c = new DeptBuyCar();
				c.setBuyKind(20);
				c.setPurMode(40);
				c.setNeedQty(obj.getQty().intValue()*ssPkg.getNeedSurPacketQty().intValue());
				c.setDeptId(deptId);
				c.setHosId(hosId);
				c.setGoodsId(obj.getGoodsId());
				c.setSurId(obj.getSurId());
				cars.add(c);
			}


		}

		//deptBuyCarService.addList(cars);
		deptBuyCarService.addList4mixSur(cars);

		//	hosGoodsInfoService.addHosGoodS(hosGoods);
		return result;
	}
	private  DeptBuyCar deptGoodsStVo2DeptBuyCar(DeptGoodsStVo good,DeptBuyCar c,String purMode,String hosId,String deptId){
		/**
		 * 转换非手术包商品
		 * buyKind： 10 实际采购，20虚拟采购
		 * purMode：10 普通耗材  20 高值耗材 30 办公用品 40手术包10 普通耗材  20 高值耗材 30 办公用品 40手术包
		 */
		c.setGoodsId(good.getGoodsId());
		c.setBuyKind(Integer.parseInt(purMode));
		c.setDeptId(deptId);
		c.setHosId(hosId);
//			if("20".equals(good.getPurMode())||"10".equals(good.getPurMode()))
//			c.setBuyKind(Integer.valueOf(good.getPurMode()));
		 //华山北院全部采用实际采购模式流程， //请购车里的buyKind没有实际意义，生成请购单时会依据
		switch (good.getPurMode()) {
			case "10":    //普耗
				c.setPurMode(10);
				c.setBuyKind(10);
				break;
			case "20":   //高值
				c.setPurMode(20);
				c.setBuyKind(20);
				break;
			case "30":  //办公用品
				c.setPurMode(30);
				c.setBuyKind(10);
				break;
			default:
				break;
		}
		c.setPurMode(Integer.parseInt(good.getPurMode())); // 新增purMode 用于设备科分类审核：10 低值 20 高值 30 办公
		c.setNeedQty(good.getNeedQty().intValue());
		return c;
	}

	/**
	 *  调用手术包数据
	 */
	@RequestMapping(value = "getDeptCarGoodsStVo4sur")
	@ResponseBody
	public AjaxResult getDeptCarGoodsStVo4sur(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryInfo<DeptCarGoodsStVo> queryInfo)
			throws Exception {
		// 传入医院Id
		AjaxResult result = new AjaxResult();
		QueryResult<DeptCarSurGoodsStVo> queryResult=   deptBuyCarService.listByPage_deptCarGoodsStlist4sur(queryInfo);
		result.setData(queryResult);
		return result;
	}

	@RequestMapping(value = "updateSurQty")
	@ResponseBody
	public AjaxResult updateSurQty(@RequestBody @Valid DeptCarSurGoodsStVo surRow ,Errors error  )  throws  Exception
	{
		AjaxResult result = new AjaxResult();
		filterErrors(error);
		String retString=	deptBuyCarService.updateSurQty(surRow);
		result.setData(retString);
		return result;
	}
}
