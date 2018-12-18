package gyqx.spdherp.adverseEvents.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.AdverseEvents;
import gyqx.spdherp.po.AdverseEventsFile;
 
public interface IAdverseEventsFileMapper {
	int update(AdverseEventsFile bean);
	int insert(AdverseEventsFile bean);
	List<AdverseEventsFile> list(AdverseEvents adverseEvents) ;
}

