/*
* @(#)PreparedStatementInvocationHandler.java 1.0 2016-1-4
*
* Copyright (c) 2015 www.yescai.com. All Rights Reserved.
* PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/

package org.tievoli.framework.sql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * PreparedStatement的处理类.
 * 
 * @author Ryan Jiang
 * 
 */
public class PreparedStatementInvocationHandler implements InvocationHandler {
	
	/** The statement. */
	private Statement statement;
	
	/** The sql logger. */
	private SqlLogger sqlLogger;

	/**
	 * Instantiates a new prepared statement invocation handler.
	 *
	 * @param preparedStatment the prepared statment
	 * @param sql the sql
	 */
	public PreparedStatementInvocationHandler(Statement preparedStatment, String sql) {
		this.statement = preparedStatment;
		this.sqlLogger = new SqlLogger(sql);
	}

	/**
	 * New instance.
	 *
	 * @param preparedStatment the prepared statment
	 * @param args the args
	 * @return the object
	 */
	public static Object newInstance(PreparedStatement preparedStatment, Object[] args) {
		PreparedStatementInvocationHandler handler = new PreparedStatementInvocationHandler(preparedStatment,
				(String) args[0]);
		return ProxyUtils.newInstance(preparedStatment, handler, new Class[] { PreparedStatement.class });
	}

	/**
	 * New instance.
	 *
	 * @param callableStatement the callable statement
	 * @param args the args
	 * @return the object
	 */
	public static Object newInstance(CallableStatement callableStatement, Object[] args) {
		PreparedStatementInvocationHandler handler = new PreparedStatementInvocationHandler(callableStatement,
				(String) args[0]);
		return ProxyUtils.newInstance(callableStatement, handler, new Class[] { CallableStatement.class });
	}

	/**
	 * New instance.
	 *
	 * @param statement the statement
	 * @return the object
	 */
	public static Object newInstance(Statement statement) {
		PreparedStatementInvocationHandler handler = new PreparedStatementInvocationHandler(statement, null);
		Object obj = ProxyUtils.newInstance(statement, handler, new Class[] { Statement.class });
		return obj;
	}

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		this.sqlLogger.log(proxy, method, args);
		long start = System.currentTimeMillis();
		try {
			Object ret = method.invoke(this.statement, args);
			Object localObject1 = ret;

			return localObject1;
		} catch (InvocationTargetException e) {
		} finally {
			this.sqlLogger.timeLog(method, System.currentTimeMillis() - start);
		}
		return null;
	}
}
