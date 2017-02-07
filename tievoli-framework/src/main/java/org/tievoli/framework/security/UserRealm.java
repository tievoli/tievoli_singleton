/*
 * @(#)UserRealm.java 1.0 2016年8月1日
 *
 * Copyright (c) 2016 Ryan Jiang. All Rights Reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *                                  _oo0oo_
 *                                 088888880
 *                                88"  .  "88
 *                                 (| -_- |)
 *                                  0\ = /0
 *                               ___/'---'\___
 *                             .' \\\\|     |// '.
 *                            / \\\\|||  :  |||// \\
 *                           /_ ||||| -:- |||||- \\
 *                          |   | \\\\\\  -  /// |   |
 *                          | \_|  ''\---/''  |_/ |
 *                          \  .-\__  '-'  __/-.  /
 *                        ___'. .'  /--.--\  '. .'___
 *                     ."" '<  '.___\_<|>_/___.' >'  "".
 *                    | | : '-  \'.;'\ _ /';.'/ - ' : | |
 *                    \  \ '_.   \_ __\ /__ _/   .-' /  /
 *                ====='-.____'.___ \_____/___.-'____.-'=====
 *                                  '=---='
 *  
 *  
 *              ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                        佛祖保佑  		 iii 		永不bug
 */
package org.tievoli.framework.security;

import java.io.Serializable;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.tievoli.framework.security.service.ShiroUserService;
import org.tievoli.model.User;
import org.tievoli.util.Encodes;

/**
 * The Class UserRealm.
 */
public class UserRealm extends AuthorizingRealm {

	private ShiroUserService shiroUserService;

	/**
	 * 授权操作.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();

		User user = shiroUserService.findUserByUserName(username);

		// 授权信息
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		info.setObjectPermissions(null);
		info.setRoles(null);
		return info;
	}

	/**
	 * 身份验证操作.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = (String) token.getPrincipal();
		User user = shiroUserService.findUserByUserName(userName);

		/*
		 * @param principal the 'primary' principal associated with the
		 * specified realm.
		 * 
		 * @param hashedCredentials the hashed credentials that verify the given
		 * principal.
		 * 
		 * @param credentialsSalt the salt used when hashing the given
		 * hashedCredentials
		 * 
		 * @param realmName the realm from where the principal and credentials
		 * were acquired.
		 */

		byte[] salt = Encodes.decodeHex(user.getPassword().substring(0, 16));
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(new Principal(user), user.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());
		return info;
	}

	public ShiroUserService getShiroUserService() {
		return shiroUserService;
	}

	public void setShiroUserService(ShiroUserService shiroUserService) {
		this.shiroUserService = shiroUserService;
	}

	private static class Principal implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** 用户Id. */
		private Integer userId;

		/** 用户名. */
		private String userName;

		public Principal(User user) {
			this.userId = user.getUserId();
			this.userName = user.getUserName();
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

	}

}
