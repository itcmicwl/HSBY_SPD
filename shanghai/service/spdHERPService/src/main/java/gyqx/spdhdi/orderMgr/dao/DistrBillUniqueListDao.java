package gyqx.spdhdi.orderMgr.dao;

import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdhdi.orderMgr.dao.mapper.IDistrBillUniqueListMapper;
import gyqx.spdhdi.orderMgr.vo.DistrBillUniqueListVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class DistrBillUniqueListDao {
	@Resource
	private SysConfigUtil configUtil;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IDistrBillUniqueListMapper mapper;

	public DistrBillUniqueListVo get(String id) {
		return mapper.get(id);
	}

	public int insertBatch(List<DistrBillUniqueListVo> distrBillUniqueList) throws Exception {
		return mapper.insertBatch(distrBillUniqueList);
	}

	public int update(DistrBillUniqueListVo distrBillUniqueList) throws Exception {
		return mapper.update(distrBillUniqueList);
	}

	public int delById(String id) throws Exception {
		return mapper.delById(id);
	}

	public int delByPid(String pid) throws Exception {
		return mapper.delById(pid);
	}

	public List<DistrBillUniqueListVo> list(DistrBillUniqueListVo qbean) throws Exception {

		return mapper.list(qbean);
	}
	public boolean existUniqueCode(String uniqueCode) throws Exception {
		return mapper.existUniqueCode(uniqueCode) > 0;
	}
}
