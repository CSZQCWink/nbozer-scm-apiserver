package com.sungeon.bos.job;

import com.sungeon.bos.core.constants.Constants;
import com.sungeon.bos.entity.ScheduleJob;
import com.sungeon.bos.service.IBaseService;
import com.sungeon.bos.task.*;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 刘国帅
 * @date 2019-10-9
 *
 * @updateAuthor 陈苏洲
 * @date 2023-09-4
 **/
@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class QuartzJob implements InterruptableJob {

	private boolean interrupted = false;
	@Value("${ScheduleGroup}")
	private String scheduleGroup;
	@Autowired
	protected IBaseService baseService;
	@Autowired
	private CustomerFlowTask customerFlowTask;


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		executeInternal(context);
	}

	public void executeInternal(JobExecutionContext context) {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		log.debug("调度任务 - [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "] 开始");
		if (scheduleJob.getStatus().equals(Constants.BURGEON_YES) && scheduleGroup.equals(scheduleJob.getGroupName())) {
			switch (scheduleJob.getJobName()) {
				// 同步客流信息
				case "syncCustomerFlowInfos":
					customerFlowTask.syncCustomerFlowInfos();
				default:
					break;
			}
		}
		log.debug("调度任务 - [" + scheduleJob.getGroupName() + "." + scheduleJob.getJobName() + "] 结束");
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		interrupted = true;
	}

}
