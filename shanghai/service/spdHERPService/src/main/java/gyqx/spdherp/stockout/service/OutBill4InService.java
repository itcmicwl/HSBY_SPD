package gyqx.spdherp.stockout.service;

import java.util.List;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import gyqx.spdherp.barcodePrintingCenter.vo.DsGoodsVo;
import gyqx.spdherp.stockMgr.vo.InStockVo;
import gyqx.spdherp.stockout.vo.Out4UseVo;
import gyqx.spdherp.stockout.vo.SickerUserVo;
import gyqx.spdherp.stockout.vo.vo4In.Out4Settle;
import gyqx.spdherp.stockout.vo.vo4In.OutBill4InVo;

/**
 * 
 * @author ganwei
 *
 */

public interface OutBill4InService {
  OutBill4InVo getOutBillDetails(String id,String isPacket);
  QueryResult<OutBill4InVo> getBillList4Instock(QueryInfo<OutBill4InVo> queryInfo);
   int updateOutBillStatus(String billId,int status);
   int updateOutBillStatusByInstock(InStockVo instock) throws Exception;
    QueryResult<Out4Settle> outBill4settle(QueryInfo<Out4Settle> queryInfo) throws Exception;
	Out4UseVo outBill4Use(QueryInfo<Out4UseVo> query);

    QueryResult<DsGoodsVo>  getDSGoodsByOutBill(QueryInfo<DsGoodsVo> queryInfo) throws Exception;
}
