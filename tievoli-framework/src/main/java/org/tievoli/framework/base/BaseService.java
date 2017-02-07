/*
 * @(#)BaseService.java 1.0 2016年7月12日
 *
 * Copyright (c) 2016 Ryan Jiang. All Rights Reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.tievoli.framework.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * The Class BaseService.
 */
public interface BaseService<T, E, M extends BaseMapper<T, E>> {
	
	M getMapper();

	int countByExample(E example);

	int deleteByExample(E example);

	int deleteByPrimaryKey(Integer id);

	int insert(T record);

	int insertSelective(T record);

	List<T> selectByExample(E example);

	T selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

	int updateByExample(@Param("record") T record, @Param("example") E example);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);
}
