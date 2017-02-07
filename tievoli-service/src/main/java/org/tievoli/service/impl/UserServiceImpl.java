/*
 * @(#)UserServiceImpl.java 1.0 2016年7月17日
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
package org.tievoli.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tievoli.framework.base.impl.BaseServiceImpl;
import org.tievoli.framework.security.service.ShiroUserService;
import org.tievoli.mapper.UserMapper;
import org.tievoli.model.User;
import org.tievoli.model.UserCriteria;
import org.tievoli.service.UserService;

/**
 * @author Ryan Jiang
 * 
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserCriteria, UserMapper> implements UserService, ShiroUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserMapper getMapper() {
		return userMapper;
	}

	@Override
	public User findUserByUserName(String username) {
		return null;
	}

}
