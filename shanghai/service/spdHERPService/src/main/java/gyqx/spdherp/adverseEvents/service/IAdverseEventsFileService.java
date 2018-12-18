package gyqx.spdherp.adverseEvents.service;

import java.util.List;

import gyqx.spdherp.po.AdverseEvents;
import gyqx.spdherp.po.AdverseEventsFile;

public interface IAdverseEventsFileService {
	
	AdverseEventsFile get(String id) throws Exception;

	AdverseEventsFile add(AdverseEventsFile adverseEventsFile) throws Exception;

	AdverseEventsFile update(AdverseEventsFile adverseEventsFile) throws Exception;

	List<AdverseEventsFile> query(String predicate, String orderBy, Object... fieldValue) throws Exception;

	List<AdverseEventsFile> list(AdverseEvents adverseEvents) throws Exception;

}
