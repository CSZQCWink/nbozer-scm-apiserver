package com.sungeon.bos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @BelongsPackage: com.sungeon.bos.entity
 * @ClassName: CompanyInfo
 * @Author: 陈苏洲
 * @Description: 企业信息
 * @CreateTime: 2023-11-06 11:17
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyInfo {
    // 企业uuid
	private String uuid;
	// 企业id
	private String id;
	// 企业号
	private String code;
	// 企业名称
	private String name;
	// 行业编号
	private String tradeCode;
}
