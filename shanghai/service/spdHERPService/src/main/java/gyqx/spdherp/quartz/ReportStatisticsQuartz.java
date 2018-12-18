package gyqx.spdherp.quartz;

import gyqx.spdhdi.orderMgr.service.IPurconfirmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import gyqx.spdherp.reportAnalysis.service.IReportStatisticsService;

/**
 * 系统定时任务.
 * 
 * @author ember
 * 
 */
public class ReportStatisticsQuartz {

	@Autowired
	private IReportStatisticsService reportStatisticsService;

	private Logger logger = LoggerFactory.getLogger(ReportStatisticsQuartz.class);

	public void reportStatisticsJob() {
		logger.info("####################开始执行医院高值耗材月消耗统计 start##############################");
		try {
			reportStatisticsService.processStatisticsHosMonthHighValueConsume();
		} catch (Exception e) {
			logger.error("执行定时任务出错", e);
		}
		logger.info("#####################执行医院高值耗材月消耗统计完成 end###############################");
	}

}
