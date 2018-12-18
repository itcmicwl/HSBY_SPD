package gyqx.spdherp.stockout.dao;

import gyqx.spdherp.stockout.dao.mapper.SurPkgMapper;
import gyqx.spdherp.stockout.vo.EntireDeptBuyVo;
import gyqx.spdherp.stockout.vo.SurPkgVo;
import gyqx.spdherp.surgery.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author liangwu
 * @Date 18-10-8 下午2:17
 */
@Repository
public class SurPkgDao {
    @Autowired
    private SurPkgMapper mapper;

    public List<SurPkgVo> listSurPkg(String stockId, String deptId, String hosId) {
        return mapper.listSurPkg(stockId, deptId, hosId, Constants.SUR_PKG_STATUS_DABAO);
    }

    public int checkUserOpStock(String hosId, String userId, String stockId) {
        return mapper.checkUserOpStock(hosId, userId, stockId);
    }

    public SurPkgVo getSurPkg(String pkgCode, String hosId) {
        return mapper.getSurPkg(pkgCode, hosId, Constants.SUR_PKG_STATUS_DABAO);
    }

    public EntireDeptBuyVo getEntireDeptBuyVo(String applyBillId) {
        return mapper.getEntireDeptBuyVo(applyBillId);
    }

    public int updateTakingStock(List<String> pkgCodeList, String outStockBillId) {
        return mapper.updateTakingStock(pkgCodeList, outStockBillId);
    }

    public int updateSurPkg(List<String> ids) {
        return mapper.updateSurPkg(ids, Constants.SUR_PKG_STATUS_DABAO, Constants.SUR_PKG_STATUS_QGCK);
    }

    public int insertSurLog(List<String> surCodeList, String billId, String userId, String desc) {
        return mapper.insertSurLog(surCodeList, billId, userId, desc);
    }

    public List<SurPkgVo> getSurPkgs(List<String> pkgCodeList, String corpId) {
        return mapper.getSurPkgs(pkgCodeList, corpId, Constants.SUR_PKG_STATUS_DABAO);
    }
}
