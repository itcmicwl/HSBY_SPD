package gyqx.spdherp.basedatamaint.service.impl;

import com.github.pagehelper.PageInfo;
import common.db.SimpleDao;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import common.utils.UtilsContext;
import gyqx.spdherp.basedatamaint.dao.PurContractDao;
import gyqx.spdherp.basedatamaint.service.PurContractService;
import gyqx.spdherp.basedatamaint.vo.PurContractVo;
import gyqx.spdherp.po.PurContract;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Auther Liangwu
 * @Date 17-8-16 下午6:07
 */
@Service
public class PurContractServiceImpl implements PurContractService {
    @Resource
    private PurContractDao purContractDao;

    @Resource
    private SimpleDao simpleDao;

    @Resource
    private UtilsContext utilsContext;

    @Override
    public QueryResult<PurContractVo> listPurContract(String searchText, Date signDateBegin, Date signDateEnd, int pageNum, int pageSize) {
        String hosId = utilsContext.getUserStateUtils().getCurrent().getCorpId();
        if(StringUtils.hasText(searchText)){
            searchText = searchText.replaceAll("%", "\\\\%");
        }
        PageUtils.startPage(pageNum, pageSize);
        return PageUtils.endPage(purContractDao.listPurContract(hosId, searchText, signDateBegin, signDateEnd));
    }

    @Override
    public List<PurContractVo> listProv(String provName) {
        if(StringUtils.hasText(provName)){
            provName.replaceAll("%", "\\\\%");
        }
        PageUtils.startPage(1,10);
        PageInfo<PurContractVo> page = new PageInfo<>(purContractDao.listProv(provName));
        return page.getList();
    }

    @Override
    public String savePurContract(PurContract purContract) throws Exception {
        String hosId = utilsContext.getUserStateUtils().getCurrent().getCorpId();
        String id = utilsContext.getSysAtomUtil().newValue("pur_contract_id");
        purContract.setId(id);
        purContract.setHosId(hosId);
        simpleDao.insert(purContract);
        return id;
    }

    @Override
    public Integer updatePurContract(PurContract purContract) throws Exception {
        simpleDao.updateNotNullFields(purContract);
        return purContract.getVersion()+1;
    }

    @Override
    public String deletePurContract(String id, int version) throws Exception {
        PurContract purContract = new PurContract();
        purContract.setId(id);
        purContract.setVersion(version);
        simpleDao.delete(purContract);
        return "OK";
    }
}
