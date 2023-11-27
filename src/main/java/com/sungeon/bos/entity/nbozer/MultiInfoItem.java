package com.sungeon.bos.entity.nbozer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @BelongsPackage: com.sungeon.bos.entity
 * @ClassName: MultiInfoItem
 * @Author: 陈苏洲
 * @Description: 多维数据明细
 * @CreateTime: 2023-11-16 17:45
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MultiInfoItem {
	private Long id;
	private Long customerFlowInfoId;
	private String name; // 主维度参数名称，AGE: 年龄 GENDER: 性别

	private Long ageUnknown;                         // 年龄: 未知
	private Long ageUnderEighteen;                   // 年龄: 小于18岁
	private Long ageNineteenToTwentyNine;            // 年龄: 19-29
	private Long ageThirtyToThirtyNine;	             // 年龄: 30-39
	private Long ageFortyToFortyNine;	             // 年龄: 40-49
	private Long ageFiftyToFiftyNine;	             // 年龄: 50-59
	private Long ageOverSixty;	                     // 年龄: 60岁以上
	private Float ageUnknownProportion;              // 年龄: 未知 占比
	private Float ageUnderEighteenProportion;	     // 年龄: 小于18岁 占比
	private Float ageNineteenToTwentyNineProportion; // 年龄: 19-29 占比
	private Float ageThirtyToThirtyNineProportion;	 // 年龄: 30-39 占比
	private Float ageFortyToFortyNineProportion;	 // 年龄: 40-49 占比
	private Float ageFiftyToFiftyNineProportion;	 // 年龄: 50-59 占比
	private Float ageOverSixtyProportion;	         // 年龄: 60岁以上 占比
	private Long sexMale;	                         // 性别：男性
	private Long sexFemale;	                         // 性别：女性
	private Long sexUnknown;                         // 性别：未知
	private Float sexMaleProportion;	             // 性别：男性 占比
	private Float sexFemaleProportion;	             // 性别：女性 占比
	private Float sexUnknownProportion;	             // 性别：未知 占比
	private Float proportion;	                     // 主维度占比
	private Long count;	                             // 数量
}
