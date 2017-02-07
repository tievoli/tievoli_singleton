/*
 * @(#)UserService.java 1.0 2016年7月17日
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
package org.tievoli.service;

import org.tievoli.framework.base.BaseService;
import org.tievoli.mapper.UserMapper;
import org.tievoli.model.User;
import org.tievoli.model.UserCriteria;

public interface UserService extends BaseService<User, UserCriteria, UserMapper> {

}
