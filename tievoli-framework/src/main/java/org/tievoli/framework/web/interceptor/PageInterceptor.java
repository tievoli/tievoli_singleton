/*
 * @(#)PageInterceptor.java 1.0 2016年7月7日
 *
 * Copyright (c) 2015 www.yescai.com. All Rights Reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.tievoli.framework.web.interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.tievoli.model.Page;

/**
 * 分页拦截器.
 * 
 * @author Ryan Jiang
 * 
 */
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class PageInterceptor implements Interceptor {

	private static final String DIALECT_MYSQL = "MYSQL";
	private static final String DIALECT_ORACLE = "ORACLE";

	private String databaseType;

	public Object intercept(Invocation invocation) throws Throwable {
		final RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
		final StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
		final BoundSql boundSql = delegate.getBoundSql();
		final Object obj = boundSql.getParameterObject();
		String sql = boundSql.getSql();
		if (obj instanceof Page) {
			final Page<?> page = (Page<?>) obj;
			MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
			Connection connection = (Connection) invocation.getArgs()[0];
			setTotalRecord(page, mappedStatement, connection);
			final String pageSql = getPageSql(page, sql);
			ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
			sql = pageSql;
		}

		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	private String getPageSql(Page<?> page, String sql) {
		final StringBuffer sqlBuffer = new StringBuffer(sql);
		if (DIALECT_MYSQL.equalsIgnoreCase(databaseType)) {
			return getMysqlPageSql(page, sqlBuffer);
		} else if (DIALECT_ORACLE.equalsIgnoreCase(databaseType)) {
			return getOraclePageSql(page, sqlBuffer);
		}
		return sqlBuffer.toString();
	}

	private String getOraclePageSql(Page<?> page, StringBuffer sqlBuffer) {
		// 错误判断
		if (page.getPageIndex() > page.getPageTotal()) {
			page.setPageIndex(page.getPageTotal());
		}

		// 计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的
		final int offset = (page.getPageIndex() - 1) * page.getPageSize() + 1;
		sqlBuffer.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(offset + page.getPageSize());
		sqlBuffer.insert(0, "select * from (").append(") where r >= ").append(offset);
		return sqlBuffer.toString();
	}

	private String getMysqlPageSql(Page<?> page, StringBuffer sqlBuffer) {
		sqlBuffer.append(" limit ").append(page.getStartRow()).append(",").append(page.getPageSize());
		return sqlBuffer.toString();
	}

	private void setTotalRecord(Page<?> page, MappedStatement mappedStatement, Connection connection) {
		final BoundSql boundSql = mappedStatement.getBoundSql(page);
		final String sql = boundSql.getSql();
		final String countSql = getCountSql(sql);
		final List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		final BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);
		final ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(countSql);
			parameterHandler.setParameters(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				final int totalRecord = rs.getInt(1);
				page.setRowTotal(totalRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private String getCountSql(String sql) {
		final int index = sql.indexOf("from");
		return "select count(*) " + sql.substring(index);
	}

	public void setProperties(Properties properties) {
		this.databaseType = properties.getProperty("databaseType");
	}

	/**
	 * 利用反射进行操作的一个工具类.
	 * 
	 */
	private static class ReflectUtil {

		public static Object getFieldValue(Object obj, String fieldName) {
			Object result = null;
			final Field field = ReflectUtil.getField(obj, fieldName);
			if (field != null) {
				field.setAccessible(true);
				try {
					result = field.get(obj);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		private static Field getField(Object obj, String fieldName) {
			Field field = null;
			for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
				try {
					field = clazz.getDeclaredField(fieldName);
					break;
				} catch (NoSuchFieldException e) {
					// 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
				}
			}
			return field;
		}

		public static void setFieldValue(Object obj, String fieldName, String fieldValue) {
			final Field field = ReflectUtil.getField(obj, fieldName);
			if (field != null) {
				try {
					field.setAccessible(true);
					field.set(obj, fieldValue);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
