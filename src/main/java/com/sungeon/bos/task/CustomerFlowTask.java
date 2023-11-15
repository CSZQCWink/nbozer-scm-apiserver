package com.sungeon.bos.task;

import com.sungeon.bos.service.AccessTokenAndCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @BelongsPackage: com.sungeon.bos.task
 * @ClassName: CustomerFlowTask
 * @Author: 陈苏洲
 * @Description: 客户流量任务
 * @CreateTime: 2023-11-08 12:00
 * @Version: 1.0
 */
@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class CustomerFlowTask extends BaseTask{

	@Autowired
	private AccessTokenAndCustomerService accessTokenAndCustomerService;

	public void syncCustomerFlowInfos() {
		Date now = new Date();
		try {
			String startTime = baseService.getThirdTime("NBZR-CUSTOMERFLOW-TIME");

			accessTokenAndCustomerService.getAccessTokenAndCustomer(startTime);

			baseService.updateThirdTime("NBZR-CUSTOMERFLOW-TIME", now);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
