package com.sungeon.bos.dao;

import com.sungeon.bos.entity.nbozer.CustomerFlowInfo;
import org.springframework.stereotype.Repository;

/**
 * @BelongsPackage: com.sungeon.bos.dao
 * @ClassName: AccessTokenAndCustomerMapper
 * @Author: 陈苏洲
 * @Description: todo
 * @CreateTime: 2023-11-08 14:37
 * @Version: 1.0
 */
@Repository
public interface AccessTokenAndCustomerDao {
	Integer insertCustomer(CustomerFlowInfo customerFlowInfo);
}
