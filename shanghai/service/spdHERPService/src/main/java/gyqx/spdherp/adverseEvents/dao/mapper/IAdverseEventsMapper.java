package gyqx.spdherp.adverseEvents.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.AdverseEvents;
 
public interface IAdverseEventsMapper {
	int update(AdverseEvents bean);
	int insert(AdverseEvents bean);
	List<AdverseEvents> list(AdverseEvents queryInfo) ;
}

