package com.sungeon.bos.entity.nbozer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @BelongsPackage: com.sungeon.bos.entity
 * @ClassName: MultiInfo
 * @Author: 陈苏洲
 * @Description: 多维度数据
 * @CreateTime: 2023-11-16 17:06
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MultiInfo {
	private Long id;
	private Long customerFlowInfoId;
	private String name; // 主维度参数名称，AGE: 年龄 GENDER: 性别
	private List<MultiInfoItem> item;

}
