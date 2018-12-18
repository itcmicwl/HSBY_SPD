
package gyqx.spdherp.adverseEvents.vo;

import java.util.List;

import gyqx.spdherp.po.AdverseEvents;
import gyqx.spdherp.po.AdverseEventsFile;

/**
 * 不良事件上报
 */
public class AdverseEventsVo extends AdverseEvents {
	private static final long serialVersionUID = 1L;
	
	private List<AdverseEventsFile> adverseEventsFiles;

	public List<AdverseEventsFile> getAdverseEventsFiles() {
		return adverseEventsFiles;
	}

	public void setAdverseEventsFiles(List<AdverseEventsFile> adverseEventsFiles) {
		this.adverseEventsFiles = adverseEventsFiles;
	}
	
}