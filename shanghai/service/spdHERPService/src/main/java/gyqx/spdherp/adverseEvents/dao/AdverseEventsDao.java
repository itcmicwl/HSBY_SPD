package gyqx.spdherp.adverseEvents.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import common.db.SimpleDao;
import common.transform.Tx;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdherp.adverseEvents.dao.mapper.IAdverseEventsMapper;
import gyqx.spdherp.adverseEvents.vo.AdverseEventsVo;
import gyqx.spdherp.po.AdverseEvents;
import gyqx.spdherp.po.AdverseEventsFile;

@Repository
public class AdverseEventsDao {
	@Resource
	private SimpleDao dao;
	@Resource
	private SysConfigUtil configUtil;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IAdverseEventsMapper mapper;

	public AdverseEvents get(String id) {
		return dao.get(AdverseEvents.class, id);
	}

	public AdverseEventsVo insertAndGet(AdverseEventsVo adverseEventsVo) throws Exception {
		List<AdverseEventsFile> adverseEventsFiles = adverseEventsVo.getAdverseEventsFiles();
		
		AdverseEvents adverseEvents = new AdverseEvents();
		Tx.transform(adverseEventsVo).to(adverseEvents);
		
		String id = atomUtil.newValue("adverse_events_id");
		String code = atomUtil.newId("adverse_events_code").get();
		adverseEvents.setId(id);
		adverseEvents.setCode(code);
		adverseEvents = dao.insertAndGet(adverseEvents);
		
		for(AdverseEventsFile a : adverseEventsFiles){
			String fileId = atomUtil.newValue("adverse_events_file_id");
			a.setId(fileId);
			a.setPid(adverseEvents.getId());
			a = dao.insertAndGet(a);
		}
		
		AdverseEventsVo vo = new AdverseEventsVo();
		vo = Tx.transform(adverseEvents).to(vo);
		vo.setAdverseEventsFiles(adverseEventsFiles);
		return vo;
	}

	public AdverseEvents updateAndGet(AdverseEvents adverseEvents) throws Exception {
		return dao.updateAndGet(adverseEvents);
	}

	public int updateFields(AdverseEvents adverseEvents, String fieldNames) throws Exception {
		return dao.updateFields(adverseEvents, fieldNames);
	}

	public int updateNotNullFields(AdverseEvents adverseEvents) throws Exception {
		return dao.updateNotNullFields(adverseEvents);
	}

	public void delete(AdverseEvents adverseEvents) throws Exception {
		dao.delete(adverseEvents);
	}

	public AdverseEvents getByFieldName(String fieldName, Object fieldValue) throws Exception {
		return (AdverseEvents) dao.getByFieldName(AdverseEvents.class, fieldName, fieldValue);
	}

	public List<AdverseEvents> query(String predicate, String orderBy, Object... fieldValue) throws Exception {
		return (List<AdverseEvents>) dao.queryBeanList(AdverseEvents.class, predicate, orderBy, fieldValue);
	}

	public long count(String predicate, Object... fieldValue) throws Exception {
		return dao.count(AdverseEvents.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception {
		return dao.updateVersion(bean);
	}

	public List<AdverseEvents> list(AdverseEvents qbean) throws Exception {

		return mapper.list(qbean);
	}
}
