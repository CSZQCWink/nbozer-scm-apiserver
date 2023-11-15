package com.sungeon.bos.service.impl;


import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.sungeon.bos.dao.AccessTokenAndCustomerDao;
import com.sungeon.bos.entity.nbozer.CustomerFlowInfo;
import com.sungeon.bos.service.AccessTokenAndCustomerService;
import com.sungeon.bos.util.DateConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsPackage: com.sungeon.bos.service.impl
 * @ClassName: AccessTokenAndCustomerServiceImpl
 * @Author: 陈苏洲
 * @Description: TODO
 * @CreateTime: 2023-11-08 13:06
 * @Version: 1.0
 */
@Slf4j
@Service("AccessTokenService")
public class AccessTokenAndCustomerServiceImpl implements AccessTokenAndCustomerService {
	@Autowired
	private AccessTokenAndCustomerDao accessTokenAndCustomerDao;
	@Override
	public List<CustomerFlowInfo> getAccessTokenAndCustomer(String startTime) throws Exception {
		// 请求URL
		String url = "https://yd.yunding360.com/openapi/oauth/token";
		// 请求体JSON格式
		String body = " {\n" +
				"\t\"account\": \"poqbn0nw\",\n" +
				"\t\"enterpriseCode\": \"poqbn0nw\",\n" +
				"\t\"password\": \"tILgFrV4GTHEiu54xVbWGQ==\"\n" +
				"}";
		String str = HttpRequest.post(url)
				.header("Content-Type", "application/json").header("api-version", "v1")
				.body(body)
				.execute()
				.body();


		// 设置安全认证
		String accessToken = str.substring(15, 62);
		// 判断字符串是否以双引号开头，并且以双引号结尾
		String authorization = null;
		if (accessToken.startsWith("\"") && accessToken.endsWith("\"")) {
			// 使用substring方法截取字符串的子串，去除首尾的双引号
			String result = accessToken.substring(1, accessToken.length() - 1);
			authorization = "bearer" + result;
			System.out.println("凭证:" + authorization);
		} else {
			System.out.println("字符串格式不正确！");
		}

		// 设置时间截取时间毫秒级时间戳
		DateConvertUtil dateConvertUtil = new DateConvertUtil();
		String modifiedDate = startTime.replace("/", "-");
		Long startModifyTime = dateConvertUtil.dateToStamp(modifiedDate);
		Long endModifyTime = System.currentTimeMillis();

		// 设置门店编号
		List<String> storeCodeList = new ArrayList<>();
		storeCodeList.add(0, "L0089");
		storeCodeList.add(1, "L0087");
		storeCodeList.add(2, "Z0029");

		String url2 = "https://yd.yunding360.com/openapi/store/customerFlow/getDataByModifyTime";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("storeCodes", storeCodeList);
		jsonObject.put("startModifyTime", startModifyTime);
		jsonObject.put("endModifyTime", endModifyTime);
		jsonObject.put("timeType", "DAY");
		jsonObject.put("page", 1);
		jsonObject.put("size", 20);
		String body2 = jsonObject.toString();

		String str2 = HttpRequest.post(url2)
				.header("Authorization", authorization)
				.header("api-version", "v2")
				.body(body2)
				.execute()
				.body();

		// 将String类型的响应数据转变为JSONObject
		com.alibaba.fastjson.JSONObject jsonObjectCustomerFlowInfo = JSON.parseObject(str2);
		// 通过键获取主要的JSON内容并转变为String类型
		String contentInfo = jsonObjectCustomerFlowInfo.get("content").toString();
		// 将其转变为List集合 集合元素内容为CustomerFlowInfo类型
		List<CustomerFlowInfo> customerFlowInfoList = JSON.parseArray(contentInfo, CustomerFlowInfo.class);
		for (CustomerFlowInfo customerFlowInfo : customerFlowInfoList) {
			accessTokenAndCustomerDao.insertCustomer(customerFlowInfo);
		}
		return customerFlowInfoList;
	}
}
