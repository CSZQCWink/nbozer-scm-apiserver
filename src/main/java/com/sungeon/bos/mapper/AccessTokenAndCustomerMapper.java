package com.sungeon.bos.mapper;

import com.sungeon.bos.entity.nbozer.CustomerFlowInfo;
import com.sungeon.bos.entity.nbozer.MultiInfoItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsPackage: com.sungeon.bos.mapper
 * @ClassName: AccessTokenAndCustomerMapper
 * @Author: 陈苏洲
 * @Description: todo
 * @CreateTime: 2023-11-08 14:39
 * @Version: 1.0
 */
@Mapper
public interface AccessTokenAndCustomerMapper {
	// 获取主表的id
	Long getId();
	// 添加客流数据
	Integer insertCustomer(CustomerFlowInfo customerFlowInfo);

	// 添加多维数据明细
	Integer insertMultiInfo(MultiInfoItem multiInfoItem);

	void callPurchaseItemAm(Long id);
}
