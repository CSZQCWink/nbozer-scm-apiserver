package com.sungeon.bos.controller;

import com.sungeon.bos.core.model.ValueHolder;
import com.sungeon.bos.entity.nbozer.CustomerFlowInfo;
import com.sungeon.bos.service.AccessTokenAndCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @BelongsPackage: com.sungeon.bos.controller
 * @ClassName: AccessTokenController
 * @Author: 陈苏洲
 * @Description: 获取access_token的控制层代码 在获取token的基础上调用客户的接口
 * @CreateTime: 2023-11-06 11:26
 * @Version: 1.0
 */

@RestController
@Slf4j
public class AccessTokenController {

	@Autowired
	private AccessTokenAndCustomerService accessTokenAndCustomerService;

	@GetMapping("/getAccessTokenAndGetDataByModifyTime")
	public ValueHolder<List<CustomerFlowInfo>> getAccessTokenAndGetDataByModifyTime() throws Exception {
		return ValueHolder.ok(accessTokenAndCustomerService.getAccessTokenAndCustomer(null));
	}
}
