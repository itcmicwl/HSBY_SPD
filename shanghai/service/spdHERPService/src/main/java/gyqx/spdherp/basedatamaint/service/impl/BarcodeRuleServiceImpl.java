package gyqx.spdherp.basedatamaint.service.impl;

import com.github.pagehelper.PageInfo;
import common.db.SimpleDao;
import common.db.query.QueryResult;
import common.transform.Tx;
import common.utils.PageUtils;
import common.utils.UtilsContext;
import gyqx.spdhdi.po.BasBarcodeRules;
import gyqx.spdherp.basedatamaint.dao.BarcodeRuleDao;
import gyqx.spdherp.basedatamaint.service.BarcodeRuleService;
import gyqx.spdherp.basedatamaint.vo.BarcodeRuleVo;
import gyqx.spdherp.stockout.vo.IdNameVo;
import org.apache.shiro.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-8-11 下午4:29
 */
@Service
public class BarcodeRuleServiceImpl implements BarcodeRuleService {
    @Resource
    private SimpleDao simpleDao;

    @Resource
    private BarcodeRuleDao barcodeRuleDao;

    @Resource
    private UtilsContext utilsContext;

    @Override
    public QueryResult<BarcodeRuleVo> listBarcodeRule(String barcode, int barcodeLen, int pageNum, int pageSize) {
        if(StringUtils.hasText(barcode)){
            barcode = barcode.replaceAll("%", "\\\\%");
        }
        PageUtils.startPage(pageNum, pageSize);
        return PageUtils.endPage(barcodeRuleDao.listBarcodeRule(barcode, barcodeLen));
    }

    @Override
    public String saveBarcodeRule(BarcodeRuleVo barcodeRuleVo) throws Exception {
        String id = utilsContext.getSysAtomUtil().newValue("basBarcodeRules");
        String uid = utilsContext.getUserStateUtils().getCurrent().getUserId();
        Date now = new Date();

        BasBarcodeRules rule = new BasBarcodeRules();
        Tx.transform(barcodeRuleVo).to(rule);

        rule.setId(id);
        rule.setUid(uid);
        rule.setFillDate(now);
        rule.setLastUpdateDatetime(now);
        rule.setFlag("1");

        simpleDao.insert(rule);
        return id;
    }

    @Override
    public Integer updateBarcodeRule(BarcodeRuleVo barcodeRuleVo) throws Exception {
        String uid = utilsContext.getUserStateUtils().getCurrent().getUserId();
        Date now = new Date();

        BasBarcodeRules rule = new BasBarcodeRules();
        Tx.transform(barcodeRuleVo).to(rule);

        rule.setUid(uid);
        rule.setLastUpdateDatetime(now);
        simpleDao.updateNotNullFields(rule);
        return rule.getVersion() + 1;
    }

    @Override
    public String removeBarcodeRule(String id, int version) throws Exception {
        BasBarcodeRules rule = new BasBarcodeRules();
        rule.setId(id);
        rule.setVersion(version);
        simpleDao.delete(rule);
        return "OK";
    }

    @Override
    public List<IdNameVo> listProv(String mfrsName) {
        if(org.springframework.util.StringUtils.hasText(mfrsName)){
            mfrsName.replaceAll("%", "\\\\%");
        }
        PageUtils.startPage(1,10);
        PageInfo<IdNameVo> page = new PageInfo<>(barcodeRuleDao.listMfrs(mfrsName));
        return page.getList();
    }
}
