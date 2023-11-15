package com.sungeon.bos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @BelongsPackage: com.sungeon.bos.entity
 * @ClassName: Token
 * @Author: 陈苏洲
 * @Description: Token
 * @CreateTime: 2023-11-06 11:05
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Token {
	// 认证令牌
	private String accessToken;
	// 账号
	private String account;
	// 令牌过期时间
	private String expiresIn;
	// 用户昵称
	private String nickname;
	// 刷新令牌凭证
	private String refreshToken;
	// 刷新令牌过期时间
	private String refreshTokenExpiresIn;
	// 范围
	private String scope;
	// 令牌类型
	private String tokenType;
	// 用户id
	private String userId;
	// 用户原始id
	private String originalId;
	// 企业信息
	private List<com.sungeon.bos.entity.CompanyInfo> enterprise;
}
