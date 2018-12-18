package gyqx.spdherp.productsInfos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import gyqx.spdherp.po.OperatingPackageKindcode;
import gyqx.spdherp.productsInfos.dao.OperatingPackageKindcodeDao;
import gyqx.spdherp.productsInfos.dao.OperationPackDao;
import gyqx.spdherp.productsInfos.service.OperatingPackageKindcodeService;
import gyqx.spdherp.productsInfos.vo.OperatingPackageInfoVo;

@Service
public class OperatingPackageKindcodeServiceImpl implements OperatingPackageKindcodeService {

	@Resource
	private OperatingPackageKindcodeDao dao;
	@Resource
	private OperationPackDao operationPackDao;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private SimpleDao simpleDao;

	public OperatingPackageKindcode get(String id) {
		return dao.get(id);
	}

	public OperatingPackageKindcode insertKind(OperatingPackageKindcode operatingPackageKindcode) throws Exception {
		String id = atomUtil.newValue("pack-kind-id");
		operatingPackageKindcode.setId(id);
		return dao.insertKind(operatingPackageKindcode);
	}

	public OperatingPackageKindcode update(OperatingPackageKindcode operatingPackageKindcode) throws Exception {
		return dao.updateAndGet(operatingPackageKindcode);
	}

	@Override
	public List<OperatingPackageKindcode> getOperatingPackageKindcodeByPid(String hosId, String pid) throws Exception {
		return dao.getOperatingPackageKindcodeByPid(hosId, pid);
	}

	@Override
	public OperatingPackageKindcode getOperatingPackageKindcodeById(String hosId, String id) throws Exception {
		return dao.getOperatingPackageKindcodeById(hosId, id);
	}

	@Override
	public void delete(OperatingPackageKindcode operatingPackageKindcode) throws Exception {
		simpleDao.executeSql("delete from operating_package_kindcode where id = ?", operatingPackageKindcode.getId());
		// FIXME 删除手术包类型
		OperatingPackageInfoVo vo = new OperatingPackageInfoVo();
		vo.setHosId(operatingPackageKindcode.getHosId());
		vo.setKindCode(operatingPackageKindcode.getId());
		List<OperatingPackageInfoVo> packInfos = operationPackDao.queryPackInfo(vo);
		for (OperatingPackageInfoVo packInfo : packInfos) {
			operationPackDao.deletePackInfo(packInfo); // 同时删除手术包及产品
		}
		
	}

}
