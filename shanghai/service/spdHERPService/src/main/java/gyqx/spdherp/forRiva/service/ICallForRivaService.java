package gyqx.spdherp.forRiva.service;

public interface ICallForRivaService
{

	String callPushOutInfo(String outStockBillId) throws Exception;

	String callPushOutInfo2(String outStockBillId) throws Exception;

	String getStocIdByEname(String ename);
}


