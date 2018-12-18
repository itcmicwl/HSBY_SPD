package gyqx.spdherp.productsInfos.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import common.db.SimpleDao;
import gyqx.spdherp.po.OperatingPackageKindcode;
import gyqx.spdherp.productsInfos.dao.mapper.OperatingPackageKindcodeMapper;

@Repository
public class OperatingPackageKindcodeDao {
	@Resource
	private SimpleDao simpleDao;
	@Resource
	private OperatingPackageKindcodeMapper mapper;

	public OperatingPackageKindcode get(String id) {
		return simpleDao.get(OperatingPackageKindcode.class, id);
	}

	public OperatingPackageKindcode insertKind(OperatingPackageKindcode operatingPackageKindcode) throws Exception {
		mapper.insert(operatingPackageKindcode);
		return operatingPackageKindcode;
	}

	public OperatingPackageKindcode updateAndGet(OperatingPackageKindcode operatingPackageKindcode) throws Exception {
		return simpleDao.updateAndGet(operatingPackageKindcode);
	}

	public OperatingPackageKindcode getOperatingPackageKindcodeById(String hosId, String id) {
		return mapper.getOperatingPackageKindcodeById(hosId, id);
	}

	public List<OperatingPackageKindcode> getOperatingPackageKindcodeByPid(String hosId, String pid) {
		return mapper.getOperatingPackageKindcodeByPid(hosId, pid);
	}

}
