package com.sungeon.bos.entity.nbozer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @BelongsPackage: com.sungeon.bos.entity.nbozer
 * @ClassName: CustomerFlowInfo
 * @Author: 陈苏洲
 * @Description: 客流信息
 * @CreateTime: 2023-11-08 13:23
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerFlowInfo {
	// id
	private Long id;
	// 门店Id
	private String storeIdUuid;
	// 门店编号
	private String storeCode;
	// 传区域返回区域名称，传门店返回门店名称，传店内区域返回店内区域名称
	private String name;
	// 传小时按小时粒度返回数据，传天按天粒度返回数据
	private String realTime;
	// 进店客流
	private Long indoorCount;
	// 过店客流（查门店区域不支持）
	private Long outdoorCount;
	// 数据库中的天时间
	private String statDimensionDayTime;
	// 数据库中的小时时间
	private String statDimensionHourTime;
	// 出店客流(查门店区域不支持)
	private Long outsum;
	// 门店区域Code
	private String areaCode;
	// 门店区域名称
	private String areaName;
	// 门店区域Id
	private String storeAreaIdUuid;
	// 试衣人数
	private Long syCount;
	// 多维数据
	private List<MultiInfo> multiInfoList;
	private String multiInfo;
}
