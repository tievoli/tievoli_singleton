package org.tievoli.framework.configuration;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.springframework.stereotype.Component;

/**
 * 属性配置文件工具类.
 * 
 * @author Ryan Jiang
 * 
 */
@Component
public class JProperties {

	/** The configuration. */
	private static Configuration configuration;

	/**
	 * Sets the configuration.
	 * 
	 * @param conf
	 *            the new configuration
	 */
	public void setConfiguration(Configuration conf) {
		configuration = conf;
	}

	/**
	 * Subset.
	 * 
	 * @param prefix
	 *            the prefix
	 * @return the configuration
	 */
	public static Configuration subset(String prefix) {
		return configuration.subset(prefix);
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public static boolean isEmpty() {
		return configuration.isEmpty();
	}

	/**
	 * Contains key.
	 * 
	 * @param key
	 *            the key
	 * @return true, if successful
	 */
	public static boolean containsKey(String key) {
		return configuration.containsKey(key);
	}

	/**
	 * Adds the property.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public static void addProperty(String key, Object value) {
		configuration.addProperty(key, value);
	}

	/**
	 * 设置 property.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public static void setProperty(String key, Object value) {
		configuration.setProperty(key, value);
	}

	/**
	 * Clear property.
	 * 
	 * @param key
	 *            the key
	 */
	public static void clearProperty(String key) {
		configuration.clearProperty(key);
	}

	/**
	 * Clear.
	 */
	public static void clear() {
		configuration.clear();
	}

	/**
	 * Gets the property.
	 * 
	 * @param key
	 *            the key
	 * @return the property
	 */
	public static Object getProperty(String key) {
		return configuration.getProperty(key);
	}

	/**
	 * Gets the keys.
	 * 
	 * @param prefix
	 *            the prefix
	 * @return the keys
	 */
	@SuppressWarnings("unchecked")
	public static Iterator<String> getKeys(String prefix) {
		return configuration.getKeys(prefix);
	}

	/**
	 * Gets the keys.
	 * 
	 * @return the keys
	 */
	@SuppressWarnings("unchecked")
	public static Iterator<String> getKeys() {
		return configuration.getKeys();
	}

	/**
	 * Gets the properties.
	 * 
	 * @param key
	 *            the key
	 * @return the properties
	 */
	public static Properties getProperties(String key) {
		return configuration.getProperties(key);
	}

	/**
	 * Gets the boolean.
	 * 
	 * @param key
	 *            the key
	 * @return the boolean
	 */
	public static boolean getBoolean(String key) {
		return configuration.getBoolean(key);
	}

	/**
	 * Gets the boolean.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the boolean
	 */
	public static boolean getBoolean(String key, boolean defaultValue) {
		return configuration.getBoolean(key, defaultValue);
	}

	/**
	 * Gets the boolean.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the boolean
	 */
	public static Boolean getBoolean(String key, Boolean defaultValue) {
		return configuration.getBoolean(key, defaultValue);
	}

	/**
	 * Gets the byte.
	 * 
	 * @param key
	 *            the key
	 * @return the byte
	 */
	public static byte getByte(String key) {
		return configuration.getByte(key);
	}

	/**
	 * Gets the byte.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the byte
	 */
	public static byte getByte(String key, byte defaultValue) {
		return configuration.getByte(key, defaultValue);
	}

	/**
	 * Gets the byte.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the byte
	 */
	public static Byte getByte(String key, Byte defaultValue) {
		return configuration.getByte(key, defaultValue);
	}

	/**
	 * Gets the double.
	 * 
	 * @param key
	 *            the key
	 * @return the double
	 */
	public static double getDouble(String key) {
		return configuration.getDouble(key);
	}

	/**
	 * Gets the double.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the double
	 */
	public static double getDouble(String key, double defaultValue) {
		return configuration.getDouble(key, defaultValue);
	}

	/**
	 * Gets the double.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the double
	 */
	public static Double getDouble(String key, Double defaultValue) {
		return configuration.getDouble(key, defaultValue);
	}

	/**
	 * Gets the float.
	 * 
	 * @param key
	 *            the key
	 * @return the float
	 */
	public static float getFloat(String key) {
		return configuration.getFloat(key);
	}

	/**
	 * Gets the float.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the float
	 */
	public static float getFloat(String key, float defaultValue) {
		return configuration.getFloat(key, defaultValue);
	}

	/**
	 * Gets the float.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the float
	 */
	public static Float getFloat(String key, Float defaultValue) {
		return configuration.getFloat(key, defaultValue);
	}

	/**
	 * Gets the int.
	 * 
	 * @param key
	 *            the key
	 * @return the int
	 */
	public static int getInt(String key) {
		return configuration.getInt(key);
	}

	/**
	 * Gets the int.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the int
	 */
	public static int getInt(String key, int defaultValue) {
		return configuration.getInt(key, defaultValue);
	}

	/**
	 * Gets the integer.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the integer
	 */
	public static Integer getInteger(String key, Integer defaultValue) {
		return configuration.getInteger(key, defaultValue);
	}

	/**
	 * Gets the long.
	 * 
	 * @param key
	 *            the key
	 * @return the long
	 */
	public static long getLong(String key) {
		return configuration.getLong(key);
	}

	/**
	 * Gets the long.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the long
	 */
	public static long getLong(String key, long defaultValue) {
		return configuration.getLong(key, defaultValue);
	}

	/**
	 * Gets the long.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the long
	 */
	public static Long getLong(String key, Long defaultValue) {
		return configuration.getLong(key, defaultValue);
	}

	/**
	 * Gets the short.
	 * 
	 * @param key
	 *            the key
	 * @return the short
	 */
	public static short getShort(String key) {
		return configuration.getShort(key);
	}

	/**
	 * Gets the short.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the short
	 */
	public static short getShort(String key, short defaultValue) {
		return configuration.getShort(key, defaultValue);
	}

	/**
	 * Gets the short.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the short
	 */
	public static Short getShort(String key, Short defaultValue) {
		return configuration.getShort(key, defaultValue);
	}

	/**
	 * Gets the big decimal.
	 * 
	 * @param key
	 *            the key
	 * @return the big decimal
	 */
	public static BigDecimal getBigDecimal(String key) {
		return configuration.getBigDecimal(key);
	}

	/**
	 * Gets the big decimal.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the big decimal
	 */
	public static BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
		return configuration.getBigDecimal(key, defaultValue);
	}

	/**
	 * Gets the big integer.
	 * 
	 * @param key
	 *            the key
	 * @return the big integer
	 */
	public static BigInteger getBigInteger(String key) {
		return configuration.getBigInteger(key);
	}

	/**
	 * Gets the big integer.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the big integer
	 */
	public static BigInteger getBigInteger(String key, BigInteger defaultValue) {
		return configuration.getBigInteger(key, defaultValue);
	}

	/**
	 * Gets the string.
	 * 
	 * @param key
	 *            the key
	 * @return the string
	 */
	public static String getString(String key) {
		return configuration.getString(key);
	}

	/**
	 * Gets the string.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the string
	 */
	public static String getString(String key, String defaultValue) {
		return configuration.getString(key, defaultValue);
	}

	/**
	 * Gets the string array.
	 * 
	 * @param key
	 *            the key
	 * @return the string array
	 */
	public static String[] getStringArray(String key) {
		return configuration.getStringArray(key);
	}

	/**
	 * Gets the list.
	 * 
	 * @param key
	 *            the key
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> getList(String key) {
		return configuration.getList(key);
	}

	/**
	 * Gets the string list.
	 * 
	 * @param key
	 *            the key
	 * @return the string list
	 */
	public static List<String> getStringList(String key) {
		return Arrays.asList(configuration.getStringArray(key));
	}

	/**
	 * Gets the list.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> getList(String key, List<Object> defaultValue) {
		return configuration.getList(key, defaultValue);
	}
}
