package gyqx.spdherp.basedatamaint.service.impl;

import common.db.SimpleDao;
import common.db.query.QueryResult;
import common.utils.DictionaryUtils;
import common.utils.PageUtils;
import common.utils.UtilsContext;
import gyqx.spdhdi.po.BasMfrsInfo;
import gyqx.spdherp.basedatamaint.dao.BasMfrsInfoDao;
import gyqx.spdherp.basedatamaint.service.BasMfrsInfoService;
import gyqx.spdherp.basedatamaint.vo.BasMfrsInfoVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-8-14 下午3:58
 */
@Service
public class BasMfrsInfoServiceImpl implements BasMfrsInfoService {
    @Resource
    private UtilsContext utilsContext;

    @Resource
    private BasMfrsInfoDao basMfrsInfoDao;

    @Resource
    private SimpleDao simpleDao;

    @Override
    public List<DictionaryUtils.DictionaryValue> listCjlxDict() {
        return utilsContext.getDictUtils().getDictItems("CJLX");
    }

    @Override
    public QueryResult<BasMfrsInfoVo> listBasMfrsInfo(String mfrsName, String mfrsKind, int pageNum, int pageSize) {
        if(StringUtils.hasText(mfrsName)){
            mfrsName = mfrsName.replaceAll("%", "\\\\%");
        }
        PageUtils.startPage(pageNum, pageSize);
        return PageUtils.endPage(basMfrsInfoDao.listBasMfrsInfo(mfrsName, mfrsKind));
    }

    @Override
    public String saveBasMfrsInfo(BasMfrsInfo basMfrsInfo) throws Exception {
        String id = utilsContext.getSysAtomUtil().newValue("mfrs_info_id");
        String uid = utilsContext.getUserStateUtils().getCurrent().getUserId();

        basMfrsInfo.setId(id);
        basMfrsInfo.setUid(uid);
        basMfrsInfo.setLastUpdateDatetime(new Date());
        basMfrsInfo.setVersion(0);

        simpleDao.insert(basMfrsInfo);
        return id;
    }

    @Override
    public Integer updateBasMfrsInfo(BasMfrsInfo basMfrsInfo) throws Exception {
        String uid = utilsContext.getUserStateUtils().getCurrent().getUserId();
        basMfrsInfo.setUid(uid);
        basMfrsInfo.setLastUpdateDatetime(new Date());
        simpleDao.updateNotNullFields(basMfrsInfo);
        return basMfrsInfo.getVersion() + 1;
    }

    @Override
    public String removeBasMfrsInfo(String id, int version) throws Exception {
        BasMfrsInfo basMfrsInfo = new BasMfrsInfo();
        basMfrsInfo.setId(id);
        basMfrsInfo.setVersion(version);
        simpleDao.delete(basMfrsInfo);
        return "OK";
    }
}
