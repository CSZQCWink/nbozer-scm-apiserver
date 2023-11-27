package com.sungeon.bos.entity.nbozer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @BelongsPackage: com.sungeon.bos.entity
 * @ClassName: AreaInfo
 * @Author: 陈苏洲
 * @Description: 区域信息
 * @CreateTime: 2023-11-16 14:55
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AreaInfo {
	private String id;
	private String areaCode;
}
