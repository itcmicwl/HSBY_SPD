package gyqx.spdherp.surgeryPkgDept.service;
import gyqx.spdherp.surgeryPkgDept.vo.SurgeryPkgVo;
import gyqx.spdherp.surgeryPkgDept.vo.SurgeryVo;

import java.util.List;
public interface ISurgeryDeptService
{
	SurgeryPkgVo getSurgeryInfoByCode(SurgeryPkgVo qBean) throws Exception;
	List<SurgeryPkgVo> getPrintSurInfo(SurgeryPkgVo qBean) throws Exception;
	SurgeryVo convertOutBill(SurgeryVo surgeryVo) throws  Exception;
	String submitOutBill(SurgeryVo surgeryVo) throws  Exception;

}


