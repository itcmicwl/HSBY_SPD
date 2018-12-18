package gyqx.spdhdi.myInfos.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import common.db.SimpleDao;
import gyqx.spdhdi.myInfos.dao.mapper.IProvFrwtsVoRMapper;
import gyqx.spdhdi.myInfos.vo.BasMfrsInfoVo;
import gyqx.spdhdi.myInfos.vo.OutdateCertsImgVo;
import gyqx.spdhdi.myInfos.vo.OutdateCertsVo;
import gyqx.spdhdi.myInfos.vo.ProvFrwtsVoR;
import gyqx.spdhdi.myInfos.vo.ProvJxsqsVo;
import gyqx.spdhdi.myInfos.vo.ProvRegistInfoPushVo;
import gyqx.spdhdi.po.HosGoodsInfo;
import gyqx.spdhdi.po.ProvCns;
import gyqx.spdhdi.po.ProvGoodsHos;
import gyqx.spdhdi.po.ProvRegistrationImage;

@Repository
public class ProvFrwtsVoRDao {
	@Resource
	private IProvFrwtsVoRMapper mapper;
	@Resource
	private SimpleDao dao;

	public List<ProvFrwtsVoR> wtsList(ProvRegistInfoPushVo qbean) throws Exception {
		return mapper.wtsList(qbean);
	}

	public List<ProvCns> cnsList(ProvRegistInfoPushVo qbean) throws Exception {
		return mapper.cnsList(qbean);
	}

	public List<ProvJxsqsVo> jxsqsList(ProvRegistInfoPushVo qbean) throws Exception {
		return mapper.jxsqsList(qbean);
	}

	public List<ProvJxsqsVo> certCheckJxsqs(ProvRegistInfoPushVo qbean) throws Exception {
		return mapper.certCheckJxsqs(qbean);
	}

	public List<BasMfrsInfoVo> getBasMfrsList(ProvRegistInfoPushVo qbean) throws Exception {
		return mapper.getBasMfrsList(qbean);
	}

	public List<ProvRegistInfoPushVo> getPushedRegCertsList(ProvRegistInfoPushVo qbean) throws Exception {
		return mapper.getPushedRegCertsList(qbean);
	}

	public List<ProvRegistrationImage> getPushedRegCertsImgList(ProvRegistInfoPushVo qbean) throws Exception {
		return mapper.getPushedRegCertsImgList(qbean);
	}

	public List<OutdateCertsVo> getOutdateCerts(ProvRegistInfoPushVo qbean) throws Exception {
		return mapper.getOutdateCerts(qbean);
	}

	public List<OutdateCertsImgVo> getOutdateCompanyCertsImg(OutdateCertsVo outdateCertsVo) throws Exception {
		return mapper.getOutdateCompanyCertsImg(outdateCertsVo);
	}

	public List<OutdateCertsImgVo> getOutdateMfrsCertsImg(OutdateCertsVo outdateCertsVo) throws Exception {
		return mapper.getOutdateMfrsCertsImg(outdateCertsVo);
	}

	public List<OutdateCertsImgVo> getOutdateRegistCertsImg(OutdateCertsVo outdateCertsVo) throws Exception {
		return mapper.getOutdateRegistCertsImg(outdateCertsVo);
	}

	public List<OutdateCertsImgVo> getOutdateWtsCertsImg(OutdateCertsVo outdateCertsVo) throws Exception {
		return mapper.getOutdateWtsCertsImg(outdateCertsVo);
	}

	public List<OutdateCertsImgVo> getOutdateCnsCertsImg(OutdateCertsVo outdateCertsVo) throws Exception {
		return mapper.getOutdateCnsCertsImg(outdateCertsVo);
	}

	public void insert(ProvGoodsHos good) throws Exception {
		dao.insert(good);
	}

	public void insertAndGet(HosGoodsInfo good) throws Exception {
		dao.insertAndGet(good);
	}
}
