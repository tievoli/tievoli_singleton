/**
 * 
 */
package org.tievoli.framework.sql;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * 数据源添加日志的类.
 * 
 * @author Ryan Jiang
 * 
 */
public class LoggingDataSource implements DataSource {

	private DataSource ds;

	public LoggingDataSource(DataSource ds) {
		this.ds = ds;
	}

	public Connection getConnection() throws SQLException {
		return ConnectionInvocationHandler.newInstance(this.ds.getConnection());
	}

	public Connection getConnection(String username, String password) throws SQLException {
		return ConnectionInvocationHandler.newInstance(this.ds.getConnection(username, password));
	}

	public PrintWriter getLogWriter() throws SQLException {
		return this.ds.getLogWriter();
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		this.ds.setLogWriter(out);
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		this.ds.setLoginTimeout(seconds);
	}

	public int getLoginTimeout() throws SQLException {
		return this.ds.getLoginTimeout();
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		System.out.println("SQL Logging: parentLogger is " + getParentLogger());
		return this.getParentLogger();
	}
}
