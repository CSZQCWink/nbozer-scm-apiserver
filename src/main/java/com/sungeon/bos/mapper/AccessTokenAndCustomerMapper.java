package com.sungeon.bos.mapper;

import com.sungeon.bos.entity.nbozer.CustomerFlowInfo;
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
	Integer insertCustomer(CustomerFlowInfo customerFlowInfo);
}
