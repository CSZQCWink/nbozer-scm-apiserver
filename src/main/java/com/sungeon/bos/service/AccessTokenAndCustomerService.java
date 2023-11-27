package com.sungeon.bos.service;

import com.sungeon.bos.entity.nbozer.CustomerFlowInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsPackage: com.sungeon.bos.service
 * @ClassName: AccessTokenAndCustomerMapper
 * @Author: 陈苏洲
 * @Description:
 * @CreateTime: 2023-11-08 13:05
 * @Version: 1.0
 */
@Service
public interface AccessTokenAndCustomerService {
	List<CustomerFlowInfo> getAccessTokenAndCustomer(String startTime) throws Exception;

}
