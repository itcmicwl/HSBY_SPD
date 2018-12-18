package gyqx.spdherp.forRiva.service;

import gyqx.spdhdi.orderMgr.vo.DistrBillListVo;
import gyqx.spdherp.forRiva.vo.ForRivaH02;

public interface IPrintForRivaService {
	
	 String receiveJfCode(String goodsId, String hosId)  throws Exception;

	 String printSer(ForRivaH02 forRivaH02) throws Exception;

	 DistrBillListVo getById(String id) throws  Exception;
}
