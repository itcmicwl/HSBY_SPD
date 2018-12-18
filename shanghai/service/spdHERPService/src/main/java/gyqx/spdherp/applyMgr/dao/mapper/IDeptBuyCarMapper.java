package gyqx.spdherp.applyMgr.dao.mapper;

import java.util.List;

import gyqx.spdherp.applyMgr.vo.DeptCarGoodsStVo;
import gyqx.spdherp.applyMgr.vo.DeptCarSurGoodsStVo;
import gyqx.spdherp.po.DeptBuyCar;
 
public interface IDeptBuyCarMapper {
	int update(DeptBuyCar bean);
	List<DeptBuyCar> list(DeptBuyCar queryInfo) ;
	
	List<DeptCarGoodsStVo> deptCarGoodsStlist(DeptCarGoodsStVo queryInfo) ;
	List<DeptCarSurGoodsStVo> deptCarGoodsStlist4sur(DeptCarGoodsStVo queryInfo) ;

}

