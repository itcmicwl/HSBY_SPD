package gyqx.ws.yg.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import common.db.SimpleDao;
import gyqx.ws.yg.dao.mapper.IYgCompanyInfoMapper;
import gyqx.ws.yg.vo.ygVo.YgCompanyInfo;

@Repository
public class YgCompanyInfoDao {
	
	@Resource
	private IYgCompanyInfoMapper mapper;
	
	@Resource
	private SimpleDao simpleDao;
	
	public void saveYgCompanyInfos(List<YgCompanyInfo> cmps) {
		this.mapper.saveYgCompanyInfos(cmps);
	}

	public YgCompanyInfo getYgCompanyInfo(String qybm) {
		return this.mapper.getYgCompanyInfo(qybm);
	}

	public void updateYgCompanyInfo(YgCompanyInfo cmp) {
		String sql = "update yg_company_info set qymc = ? ,lxr = ? ,lxdh = ? ,qydz = ?,last_update_datetime = sysdate() ,version= version+1 where qybm = ?";
		this.simpleDao.executeSql(sql, cmp.getQymc(),cmp.getLxr(),cmp.getLxdh(),cmp.getQydz(),cmp.getQybm());
	}
	public List<YgCompanyInfo> list(YgCompanyInfo bean) throws Exception{
		return mapper.list(bean);
	}

}
