/*
 * @(#)BaseServiceImpl.java 1.0 2016年7月17日
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
package org.tievoli.framework.base.impl;

import java.util.List;

import org.tievoli.framework.base.BaseMapper;
import org.tievoli.framework.base.BaseService;

public abstract class BaseServiceImpl<T, E, M extends BaseMapper<T, E>> implements BaseService<T, E, M> {

	public abstract M getMapper();

	@Override
	public int countByExample(E example) {
		return getMapper().countByExample(example);
	}

	@Override
	public int deleteByExample(E example) {
		return getMapper().deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return getMapper().deleteByPrimaryKey(id);
	}

	@Override
	public int insert(T record) {
		return getMapper().insert(record);
	}

	@Override
	public int insertSelective(T record) {
		return getMapper().insertSelective(record);
	}

	@Override
	public List<T> selectByExample(E example) {
		return getMapper().selectByExample(example);
	}

	@Override
	public T selectByPrimaryKey(Integer id) {
		return getMapper().selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(T record, E example) {
		return getMapper().updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(T record, E example) {
		return getMapper().updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return getMapper().updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return getMapper().updateByPrimaryKey(record);
	}
}
