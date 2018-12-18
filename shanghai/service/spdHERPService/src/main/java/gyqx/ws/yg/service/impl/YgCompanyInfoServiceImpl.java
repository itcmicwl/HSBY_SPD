package gyqx.ws.yg.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.github.pagehelper.util.StringUtil;
import common.exception.ExceptionMessage;
import gyqx.ws.yg.dao.YgCompanyInfoDao;
import gyqx.ws.yg.service.IYgCompanyInfoService;
import gyqx.ws.yg.vo.ygVo.YgCompanyInfo;

@Service
public class YgCompanyInfoServiceImpl implements IYgCompanyInfoService{
	private final static Logger log = LoggerFactory.getLogger(YgCompanyInfoServiceImpl.class);

	@Resource
	private YgCompanyInfoDao ygCompanyInfoDao;
	
	@Override
	public void saveYgCompanyInfos(List<YgCompanyInfo> cmps) {
		try {
			this.ygCompanyInfoDao.saveYgCompanyInfos(cmps);
		} catch (Exception e) {
			log.info("#####################批量插入失败: => {}#####################", ExceptionMessage.fromException(e));
		}
	}
	
	@Override
	public YgCompanyInfo getYgCompanyInfo(String qybm){
		 if(StringUtil.isEmpty(qybm))
			 return null;
		return this.ygCompanyInfoDao.getYgCompanyInfo(qybm);
	}

	@Override
	public void updateYgCompanyInfo(YgCompanyInfo cmp) {
		try {
			this.ygCompanyInfoDao.updateYgCompanyInfo(cmp);
		} catch (Exception e) {
			log.info("#####################更新"+cmp.toString()+"失败: => {}#####################", ExceptionMessage.fromException(e));
		}
		
	}

	@Override
	public List<YgCompanyInfo> list(YgCompanyInfo bean) throws Exception {

		return ygCompanyInfoDao.list(bean);
	}
}

