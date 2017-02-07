/**
 * 
 */
package org.tievoli.framework.sql;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sql日志记录器.
 * 
 * @author Ryan Jiang
 * 
 */
public class SqlLogger {
	
	/** The logger. */
	final Logger logger = LoggerFactory.getLogger(super.getClass());
	
	/** The sql. */
	private String sql;
	
	/** The i. */
	private static AtomicInteger i = new AtomicInteger(0);

	/** The id. */
	private int id = i.incrementAndGet();

	/** The Constant SET_METHODS. */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static final HashSet<String> SET_METHODS = new HashSet();
	
	/** The Constant EXECUTE_METHODS. */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static final HashSet<String> EXECUTE_METHODS = new HashSet();

	/** The column map. */
	@SuppressWarnings("rawtypes")
	private Map columnMap = new HashMap();

	/** The column names. */
	@SuppressWarnings("rawtypes")
	private List columnNames = new ArrayList();

	/** The column values. */
	@SuppressWarnings("rawtypes")
	private List columnValues = new ArrayList();

	/**
	 * Instantiates a new sql logger.
	 *
	 * @param sql the sql
	 */
	public SqlLogger(String sql) {
		this.sql = sql;
	}

	/**
	 * Log.
	 *
	 * @param proxy the proxy
	 * @param method the method
	 * @param args the args
	 */
	public void log(Object proxy, Method method, Object[] args) {
		if (!(this.logger.isDebugEnabled()))
			return;
		if (EXECUTE_METHODS.contains(method.getName())) {
			loggingSql(args);
			clearColumnInfo();
		} else if (SET_METHODS.contains(method.getName())) {
			if ("setNull".equals(method.getName()))
				setColumn(args[0], null);
			else
				setColumn(args[0], args[1]);
		}
	}

	/**
	 * Time log.
	 *
	 * @param method the method
	 * @param l the l
	 */
	public void timeLog(Method method, long l) {
		if (EXECUTE_METHODS.contains(method.getName()))
			this.logger.debug("sqlLog-{}t:{} ms", Integer.valueOf(this.id), Long.valueOf(l));
	}

	/**
	 * Logging sql.
	 *
	 * @param args the args
	 */
	private void loggingSql(Object[] args) {
		if (this.sql == null) {
			this.logger.debug("sqlLog-{}q:\n{}", Integer.valueOf(this.id), args[0]);
			return;
		}
		StringBuilder builder = new StringBuilder();
		if ((this.columnValues != null) && (this.columnValues.size() > 0)) {
			int sidx = 0;
			for (int i = 0; i < this.columnValues.size(); ++i) {
				int idx = this.sql.indexOf(63, sidx);
				if (idx != -1) {
					builder.append(this.sql.substring(sidx, idx));
					if (this.columnValues.get(i) instanceof String)
						builder.append("'").append((String) this.columnValues.get(i)).append("'");
					else if (this.columnValues.get(i) instanceof Integer)
						builder.append(((Integer) this.columnValues.get(i)).intValue());
					else if (this.columnValues.get(i) instanceof Float)
						builder.append(((Float) this.columnValues.get(i)).floatValue());
					else if (this.columnValues.get(i) instanceof Double)
						builder.append(((Double) this.columnValues.get(i)).doubleValue());
					else if (this.columnValues.get(i) instanceof Long)
						builder.append(((Long) this.columnValues.get(i)).longValue());
					else
						builder.append(this.columnValues.get(i));
					sidx = idx + 1;
				}
			}
			builder.append(this.sql.substring(sidx));
		} else {
			builder.append(this.sql);
		}

		this.logger.debug("sqlLog-{}q:\n{}", Integer.valueOf(this.id), builder);
	}

	/**
	 * 设置 column.
	 *
	 * @param key the key
	 * @param value the value
	 */
	@SuppressWarnings("unchecked")
	protected void setColumn(Object key, Object value) {
		this.columnMap.put(key, value);
		this.columnNames.add(key);
		this.columnValues.add(value);
	}

	/**
	 * Gets the type string.
	 *
	 * @return the type string
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected String getTypeString() {
		List typeList = new ArrayList(this.columnValues.size());
		for (int i = 0; i < this.columnValues.size(); ++i) {
			Object value = this.columnValues.get(i);
			if (value == null)
				typeList.add("null");
			else {
				typeList.add(value.getClass().getName());
			}
		}
		return typeList.toString();
	}

	/**
	 * Gets the column string.
	 *
	 * @return the column string
	 */
	protected String getColumnString() {
		return this.columnNames.toString();
	}

	/**
	 * Clear column info.
	 */
	protected void clearColumnInfo() {
		this.columnMap.clear();
		this.columnNames.clear();
		this.columnValues.clear();
	}

	/**
	 * Removes the breaking whitespace.
	 *
	 * @param original the original
	 * @return the string
	 */
	protected String removeBreakingWhitespace(String original) {
		return original.replace('\n', ' ').replace('\r', ' ').replace('\t', ' ');
	}

	static {
		SET_METHODS.add("setString");
		SET_METHODS.add("setInt");
		SET_METHODS.add("setByte");
		SET_METHODS.add("setShort");
		SET_METHODS.add("setLong");
		SET_METHODS.add("setDouble");
		SET_METHODS.add("setFloat");
		SET_METHODS.add("setTimestamp");
		SET_METHODS.add("setDate");
		SET_METHODS.add("setTime");
		SET_METHODS.add("setArray");
		SET_METHODS.add("setBigDecimal");
		SET_METHODS.add("setAsciiStream");
		SET_METHODS.add("setBinaryStream");
		SET_METHODS.add("setBlob");
		SET_METHODS.add("setBoolean");
		SET_METHODS.add("setBytes");
		SET_METHODS.add("setCharacterStream");
		SET_METHODS.add("setClob");
		SET_METHODS.add("setObject");
		SET_METHODS.add("setNull");

		EXECUTE_METHODS.add("execute");
		EXECUTE_METHODS.add("executeUpdate");
		EXECUTE_METHODS.add("executeQuery");
	}
}
