package gyqx.spdherp.basedatamaint.dao.mapper;

import gyqx.spdherp.basedatamaint.vo.BarcodeRuleVo;
import gyqx.spdherp.stockout.vo.IdNameVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// TODO: PO更新时，mapper.xml更新对应映射关系
public interface BarcodeRuleMapper {

    List<BarcodeRuleVo> listBarcodeRule(@Param("barcode") String barcode,
                                        @Param("barcodeLen") int barcodeLen);

    List<IdNameVo> listMfrs(@Param("mfrsName") String mfrsName);
}