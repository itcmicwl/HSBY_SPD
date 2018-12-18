package gyqx.spdherp.basedatamaint.service;

import common.db.query.QueryResult;
import gyqx.spdherp.basedatamaint.vo.BarcodeRuleVo;
import gyqx.spdherp.stockout.vo.IdNameVo;

import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-8-11 下午4:29
 */
public interface BarcodeRuleService {
    QueryResult<BarcodeRuleVo> listBarcodeRule(String barcode, int barcodeLen, int pageNum, int pageSize);

    String saveBarcodeRule(BarcodeRuleVo barcodeRuleVo) throws Exception;

    Integer updateBarcodeRule(BarcodeRuleVo barcodeRuleVo) throws Exception;

    String removeBarcodeRule(String id, int version) throws Exception;

    List<IdNameVo> listProv(String mfrsName);
}
