/**
 * 
 */
package org.tievoli.framework.sql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * SQL的connection处理类.
 * 
 * @author Ryan Jiang
 * 
 */
public class ConnectionInvocationHandler implements InvocationHandler {

	protected Connection connection;

	public ConnectionInvocationHandler(Object connection) {
		this.connection = ((Connection) connection);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object ret = method.invoke(this.connection, args);
		if ("prepareStatement".equals(method.getName()))
			return PreparedStatementInvocationHandler.newInstance((PreparedStatement) ret, args);
		if ("createStatement".equals(method.getName()))
			return PreparedStatementInvocationHandler.newInstance((Statement) ret);
		if ("prepareCall".equals(method.getName())) {
			return PreparedStatementInvocationHandler.newInstance((CallableStatement) ret, args);
		}
		return ret;
	}

	public static Connection newInstance(Connection connection) {
		ConnectionInvocationHandler handler = new ConnectionInvocationHandler(connection);
		return ((Connection) ProxyUtils.newInstance(connection, handler, new Class[] { Connection.class }));
	}
}
