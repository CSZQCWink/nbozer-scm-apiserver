package com.sungeon.bos.dao.impl;

import com.sungeon.bos.dao.AccessTokenAndCustomerDao;
import com.sungeon.bos.entity.nbozer.CustomerFlowInfo;
import com.sungeon.bos.mapper.AccessTokenAndCustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @BelongsPackage: com.sungeon.bos.dao.impl
 * @ClassName: AccessTokenAndCustomerDaoImpl
 * @Author: 陈苏洲
 * @Description: todo
 * @CreateTime: 2023-11-08 14:37
 * @Version: 1.0
 */
@Slf4j
@Repository("AccessTokenAndCustomerDao")
public class AccessTokenAndCustomerDaoImpl implements AccessTokenAndCustomerDao {
	@Autowired
	private AccessTokenAndCustomerMapper accessTokenAndCustomerMapper;
	@Override
	public Integer insertCustomer(CustomerFlowInfo customerFlowInfo) {
		return accessTokenAndCustomerMapper.insertCustomer(customerFlowInfo);
	}
}
