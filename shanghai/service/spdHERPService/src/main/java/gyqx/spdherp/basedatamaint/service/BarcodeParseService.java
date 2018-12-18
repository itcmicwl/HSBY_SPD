package gyqx.spdherp.basedatamaint.service;

import gyqx.spdherp.basedatamaint.vo.BarcodeParseResult;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

/**
 * @Auther Liangwu
 * @Date 17-8-12 下午3:56
 */
public interface BarcodeParseService {
    Set<BarcodeParseResult> parseBarcodeBase(String barcodeStr) throws Exception;

    Set<BarcodeParseResult> parseBarcodeCombine(String barcode, String slaverBarcode, String hosId, String provId) throws Exception;

    Set<BarcodeParseResult> parseBarcodeHERP(String masterBarcode, String slaverBarcode) throws ParseException;

    List<BarcodeParseResult> parseHosPackageInfo(String barcode);
}
