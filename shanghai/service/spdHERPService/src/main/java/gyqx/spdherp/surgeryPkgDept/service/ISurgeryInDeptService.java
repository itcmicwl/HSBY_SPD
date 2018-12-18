package gyqx.spdherp.surgeryPkgDept.service;
import gyqx.spdherp.po.InStock;
import gyqx.spdherp.surgeryPkgDept.vo.InDeptSurInfoVo;
import gyqx.spdherp.surgeryPkgDept.vo.InStockVo;
import gyqx.spdherp.surgeryPkgDept.vo.SurgeryPkgVo;
import gyqx.spdherp.surgeryPkgDept.vo.SurgeryVo;

import java.util.List;

public interface ISurgeryInDeptService
{
	InDeptSurInfoVo findAllSurInfo(InDeptSurInfoVo qBean) throws Exception;
	InStock add(InStockVo inStockVo) throws Exception;
}


