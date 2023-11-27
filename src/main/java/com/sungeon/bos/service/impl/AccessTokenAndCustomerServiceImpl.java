package com.sungeon.bos.service.impl;


import cn.hutool.http.HttpRequest;

import com.alibaba.fastjson.JSON;
import com.sungeon.bos.dao.AccessTokenAndCustomerDao;
import com.sungeon.bos.entity.nbozer.*;
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


		log.info("-----------------------------通过门店id获取区域id------------------------------------");
		for (CustomerFlowInfo customerFlowInfo : customerFlowInfoList) {
			// 获取门店id
			String storeIdUuid = customerFlowInfo.getStoreIdUuid();
			// 请求地址
			String urlGetAreaIdByStoreId = "https://yd.yunding360.com/openapi/storeArea/findAllStoreArea";
			// 将门店id作为请求体
			JSONObject jsonObjectGetAreaIdByStoreId = new JSONObject();
			jsonObjectGetAreaIdByStoreId.put("storeId", storeIdUuid);
			String jsonBodyGetAreaIdByStoreId = jsonObjectGetAreaIdByStoreId.toString();
			// 发送请求
			String strGetAreaIdByStoreId = HttpRequest.post(urlGetAreaIdByStoreId)
					.header("Content-Type", "application/json")
					.header("Authorization", authorization)
					.header("api-version", "v1")
					.body(jsonBodyGetAreaIdByStoreId)
					.execute()
					.body();
			// 将String类型的响应数据转变为JSONObject
			com.alibaba.fastjson.JSONObject jsonObjectGetAreaInfo = JSON.parseObject(strGetAreaIdByStoreId);
			// 通过键获取主要的JSON内容并转变为String类型
			String contentAreaInfo = jsonObjectGetAreaInfo.get("content").toString();
			List<AreaInfo> areaInfos = JSON.parseArray(contentAreaInfo, AreaInfo.class);

			for (AreaInfo areaInfo : areaInfos) {
				if ("SY".equals(areaInfo.getAreaCode())) {
					System.out.println(areaInfo.getId());
					customerFlowInfo.setStoreAreaIdUuid(areaInfo.getId());
				}
			}

			log.info("-----------------------------通过区域id获取试衣数------------------------------------");
			// 区域id
			String storeAreaIdUuid = customerFlowInfo.getStoreAreaIdUuid();
			ArrayList<String> storeAreaIdUuidList = new ArrayList<>();
			storeAreaIdUuidList.add(storeAreaIdUuid);
			// 请求地址
			String urlGetSYCount = "https://yd.yunding360.com/openapi/store/customerFlow/getDataByModifyTime";
			// 请求体
			JSONObject jsonObjectGetSYCount = new JSONObject();
			jsonObjectGetSYCount.put("areaIds", storeAreaIdUuidList);
			jsonObjectGetSYCount.put("startModifyTime", startModifyTime);
			jsonObjectGetSYCount.put("endModifyTime", endModifyTime);
			jsonObjectGetSYCount.put("timeType", "DAY");
			String jsonBodyGetSYCount = jsonObjectGetSYCount.toString();
			// 发送请求 获取响应值
			String strGetSYCount = HttpRequest.post(urlGetSYCount)
					.header("Authorization", authorization)
					.header("api-version", "v2")
					.body(jsonBodyGetSYCount)
					.execute()
					.body();
			// 将String类型的响应数据转变为JSONObject
			com.alibaba.fastjson.JSONObject jsonBojectstrGetSYCount = JSON.parseObject(strGetSYCount);
			// 通过键获取主要的JSON内容并转变为String类型
			String contentSYCount = jsonBojectstrGetSYCount.get("content").toString();
			List<SYCount> syCounts = JSON.parseArray(contentSYCount, SYCount.class);
			for (SYCount syCount : syCounts) {
				customerFlowInfo.setSyCount(syCount.getIndoorCount());
			}
		}

		for (CustomerFlowInfo customerFlowInfo1 : customerFlowInfoList) {
			accessTokenAndCustomerDao.insertCustomer(customerFlowInfo1);
			log.info("主表的数据"+customerFlowInfo1);
		}

		log.info("-----------------------------根据门店id获取客流多维度数据------------------------------------");
		for (CustomerFlowInfo customerFlowInfo2 : customerFlowInfoList) {
			// 获取门店id
			String storeIdUuid = customerFlowInfo2.getStoreIdUuid();
			String urlGetMulti = "https://yd.yunding360.com/openapi/customer_flow/dimensions/multi";
			JSONObject jsonObjectGetMulti = new JSONObject();
			jsonObjectGetMulti.put("storeId", storeIdUuid);
			jsonObjectGetMulti.put("startModifyTime", startModifyTime);
			jsonObjectGetMulti.put("endModifyTime", endModifyTime);
			jsonObjectGetMulti.put("mainDimension", "AGE");
			jsonObjectGetMulti.put("contrastDimension", "GENDER");
			jsonObjectGetMulti.put("queryDimension", "STORE");
			String jsonBodyGetMulti = jsonObjectGetMulti.toString();

			String strGetMulti = HttpRequest.post(urlGetMulti)
					.header("Authorization", authorization)
					.header("api-version", "v1")
					.header("Content-Type", "application/json")
					.body(jsonBodyGetMulti)
					.execute()
					.body();
			// log.info("响应："+strGetMulti);
			// 将String类型的响应数据转变为JSONObject
			com.alibaba.fastjson.JSONObject jsonBojectstrGetMulti = JSON.parseObject(strGetMulti);
			// 通过键获取主要的JSON内容并转变为String类型
			String contentGetMulti = jsonBojectstrGetMulti.get("content").toString();
			List<MultiInfo> multiInfoItemList = JSON.parseArray(contentGetMulti, MultiInfo.class);

			// 遍历multiInfoItemList集合
			for (MultiInfo multiInfo : multiInfoItemList) {
				multiInfo.setCustomerFlowInfoId(customerFlowInfo2.getId());
				// 创建一个
				MultiInfoItem multiInfoItem = new MultiInfoItem();
				multiInfoItem.setCustomerFlowInfoId(multiInfo.getCustomerFlowInfoId());
				multiInfoItem.setName(multiInfo.getName());
				for (MultiInfoItem i : multiInfo.getItem()) {
					multiInfoItem.setAgeUnknown(i.getAgeUnknown());
					multiInfoItem.setAgeUnderEighteen(i.getAgeUnderEighteen());
					multiInfoItem.setAgeNineteenToTwentyNine(i.getAgeNineteenToTwentyNine());
					multiInfoItem.setAgeThirtyToThirtyNine(i.getAgeThirtyToThirtyNine());
					multiInfoItem.setAgeFortyToFortyNine(i.getAgeFortyToFortyNine());
					multiInfoItem.setAgeFiftyToFiftyNine(i.getAgeFiftyToFiftyNine());
					multiInfoItem.setAgeOverSixty(i.getAgeOverSixty());
					multiInfoItem.setAgeUnknownProportion(i.getAgeUnknownProportion());
					multiInfoItem.setAgeUnderEighteenProportion(i.getAgeUnderEighteenProportion());
					multiInfoItem.setAgeNineteenToTwentyNineProportion(i.getAgeNineteenToTwentyNineProportion());
					multiInfoItem.setAgeThirtyToThirtyNineProportion(i.getAgeThirtyToThirtyNineProportion());
					multiInfoItem.setAgeFortyToFortyNineProportion(i.getAgeFortyToFortyNineProportion());
					multiInfoItem.setAgeFiftyToFiftyNineProportion(i.getAgeFiftyToFiftyNineProportion());
					multiInfoItem.setAgeOverSixtyProportion(i.getAgeOverSixtyProportion());
					multiInfoItem.setSexMale(i.getSexMale());
					multiInfoItem.setSexFemale(i.getSexFemale());
					multiInfoItem.setSexUnknown(i.getSexUnknown());
					multiInfoItem.setSexMaleProportion(i.getSexMaleProportion());
					multiInfoItem.setSexFemaleProportion(i.getSexFemaleProportion());
					multiInfoItem.setSexUnknownProportion(i.getSexUnknownProportion());
					multiInfoItem.setProportion(i.getProportion());
					multiInfoItem.setCount(i.getCount());
				}
				log.info("明细数据" + multiInfoItem);
				accessTokenAndCustomerDao.insertMultiInfo(multiInfoItem);
			}
		}
		return customerFlowInfoList;
	}
}
