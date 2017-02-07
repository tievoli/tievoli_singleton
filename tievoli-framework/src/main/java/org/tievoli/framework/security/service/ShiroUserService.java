/*
 * @(#)ShiroUserService.java 1.0 2016年8月2日
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
package org.tievoli.framework.security.service;

import org.tievoli.model.User;

/**
 * @author Ryan Jiang
 * 
 */
public interface ShiroUserService {

	User findUserByUserName(String username);

}
