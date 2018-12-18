package gyqx.spdherp.basedatamaint.dao;

import gyqx.spdherp.basedatamaint.dao.mapper.BarcodeRuleMapper;
import gyqx.spdherp.basedatamaint.vo.BarcodeRuleVo;
import gyqx.spdherp.stockout.vo.IdNameVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-8-11 下午4:27
 */
@Repository
public class BarcodeRuleDao {
    @Resource
    private BarcodeRuleMapper mapper;

    public List<BarcodeRuleVo> listBarcodeRule(String barcode, int barcodeLen) {
        return mapper.listBarcodeRule(barcode, barcodeLen);
    }

    public List<IdNameVo> listMfrs(String mfrsName) {
        return mapper.listMfrs(mfrsName);
    }
}
