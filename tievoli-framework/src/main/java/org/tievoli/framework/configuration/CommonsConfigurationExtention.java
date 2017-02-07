/**
 * 
 */
package org.tievoli.framework.configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.ReloadingStrategy;
import org.springframework.core.io.Resource;

/**
 * 覆盖apache的文件配置类,对多个文件配置信息进行扩展.
 * 
 * @author Ryan Jiang
 * 
 */
public class CommonsConfigurationExtention implements FileConfiguration {
	FileConfiguration configureation;
	boolean ignoreResourceNotFound = true;

	public boolean isIgnoreResourceNotFound() {
		return this.ignoreResourceNotFound;
	}

	public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound) {
		this.ignoreResourceNotFound = ignoreResourceNotFound;
	}

	public CommonsConfigurationExtention() {
	}

	public CommonsConfigurationExtention(Resource r) {
		try {
			URL configurationFile = r.getURL();
			if (isXml(configurationFile)) {
				parseXmlProperties(configurationFile);
				return;
			}
			parseProperties(configurationFile);
		} catch (IOException e) {
			if (!(this.ignoreResourceNotFound))
				throw new RuntimeException("file not found!", e);
			makeDummyProperties();
		} catch (ConfigurationException e) {
			throw new RuntimeException("file parsing error!", e);
		}
	}

	private void makeDummyProperties() {
		this.configureation = new PropertiesConfiguration();
	}

	private void parseProperties(URL configurationFile) throws ConfigurationException {
		this.configureation = new PropertiesConfiguration(configurationFile);
	}

	private void parseXmlProperties(URL configurationFile) throws ConfigurationException {
		this.configureation = new XMLConfiguration(configurationFile);
	}

	private boolean isXml(URL type) {
		return type.toString().toLowerCase().endsWith("xml");
	}

	public int hashCode() {
		return this.configureation.hashCode();
	}

	public boolean equals(Object obj) {
		return this.configureation.equals(obj);
	}

	public void clear() {
		this.configureation.clear();
	}

	public void save(Writer out) throws ConfigurationException {
		this.configureation.save(out);
	}

	public void load() throws ConfigurationException {
		this.configureation.load();
	}

	public void load(String fileName) throws ConfigurationException {
		this.configureation.load(fileName);
	}

	public String toString() {
		return this.configureation.toString();
	}

	public void load(File file) throws ConfigurationException {
		this.configureation.load(file);
	}

	public void load(URL url) throws ConfigurationException {
		this.configureation.load(url);
	}

	public void load(InputStream in) throws ConfigurationException {
		this.configureation.load(in);
	}

	public void load(InputStream in, String encoding) throws ConfigurationException {
		this.configureation.load(in, encoding);
	}

	public void save(String fileName) throws ConfigurationException {
		this.configureation.save(fileName);
	}

	public void save(URL url) throws ConfigurationException {
		this.configureation.save(url);
	}

	public void save(File file) throws ConfigurationException {
		this.configureation.save(file);
	}

	public void save(OutputStream out) throws ConfigurationException {
		this.configureation.save(out);
	}

	public Configuration subset(String prefix) {
		return this.configureation.subset(prefix);
	}

	public void save(OutputStream out, String encoding) throws ConfigurationException {
		this.configureation.save(out, encoding);
	}

	public String getFileName() {
		return this.configureation.getFileName();
	}

	public void setFileName(String fileName) {
		this.configureation.setFileName(fileName);
	}

	public String getBasePath() {
		return this.configureation.getBasePath();
	}

	public void setBasePath(String basePath) {
		this.configureation.setBasePath(basePath);
	}

	@SuppressWarnings("unchecked")
	public Iterator<String> getKeys(String prefix) {
		return this.configureation.getKeys(prefix);
	}

	public Properties getProperties(String key) {
		return this.configureation.getProperties(key);
	}

	public File getFile() {
		return this.configureation.getFile();
	}

	public void setFile(File file) {
		this.configureation.setFile(file);
	}

	public boolean getBoolean(String key) {
		return this.configureation.getBoolean(key);
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		return this.configureation.getBoolean(key, defaultValue);
	}

	public Boolean getBoolean(String key, Boolean defaultValue) {
		return this.configureation.getBoolean(key, defaultValue);
	}

	public URL getURL() {
		return this.configureation.getURL();
	}

	public void setURL(URL url) {
		this.configureation.setURL(url);
	}

	public byte getByte(String key) {
		return this.configureation.getByte(key);
	}

	public void setAutoSave(boolean autoSave) {
		this.configureation.setAutoSave(autoSave);
	}

	public byte getByte(String key, byte defaultValue) {
		return this.configureation.getByte(key, defaultValue);
	}

	public boolean isAutoSave() {
		return this.configureation.isAutoSave();
	}

	public Byte getByte(String key, Byte defaultValue) {
		return this.configureation.getByte(key, defaultValue);
	}

	public void addProperty(String key, Object value) {
		this.configureation.addProperty(key, value);
	}

	public double getDouble(String key) {
		return this.configureation.getDouble(key);
	}

	public double getDouble(String key, double defaultValue) {
		return this.configureation.getDouble(key, defaultValue);
	}

	public void setProperty(String key, Object value) {
		this.configureation.setProperty(key, value);
	}

	public Double getDouble(String key, Double defaultValue) {
		return this.configureation.getDouble(key, defaultValue);
	}

	public void clearProperty(String key) {
		this.configureation.clearProperty(key);
	}

	public float getFloat(String key) {
		return this.configureation.getFloat(key);
	}

	public ReloadingStrategy getReloadingStrategy() {
		return this.configureation.getReloadingStrategy();
	}

	public void setReloadingStrategy(ReloadingStrategy strategy) {
		this.configureation.setReloadingStrategy(strategy);
	}

	public float getFloat(String key, float defaultValue) {
		return this.configureation.getFloat(key, defaultValue);
	}

	public void reload() {
		this.configureation.reload();
	}

	public Float getFloat(String key, Float defaultValue) {
		return this.configureation.getFloat(key, defaultValue);
	}

	public int getInt(String key) {
		return this.configureation.getInt(key);
	}

	public int getInt(String key, int defaultValue) {
		return this.configureation.getInt(key, defaultValue);
	}

	public Integer getInteger(String key, Integer defaultValue) {
		return this.configureation.getInteger(key, defaultValue);
	}

	public long getLong(String key) {
		return this.configureation.getLong(key);
	}

	public long getLong(String key, long defaultValue) {
		return this.configureation.getLong(key, defaultValue);
	}

	public Long getLong(String key, Long defaultValue) {
		return this.configureation.getLong(key, defaultValue);
	}

	public short getShort(String key) {
		return this.configureation.getShort(key);
	}

	public short getShort(String key, short defaultValue) {
		return this.configureation.getShort(key, defaultValue);
	}

	public Short getShort(String key, Short defaultValue) {
		return this.configureation.getShort(key, defaultValue);
	}

	public BigDecimal getBigDecimal(String key) {
		return this.configureation.getBigDecimal(key);
	}

	public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
		return this.configureation.getBigDecimal(key, defaultValue);
	}

	public BigInteger getBigInteger(String key) {
		return this.configureation.getBigInteger(key);
	}

	public Object getProperty(String key) {
		return this.configureation.getProperty(key);
	}

	public boolean isEmpty() {
		return this.configureation.isEmpty();
	}

	public BigInteger getBigInteger(String key, BigInteger defaultValue) {
		return this.configureation.getBigInteger(key, defaultValue);
	}

	public boolean containsKey(String key) {
		return this.configureation.containsKey(key);
	}

	@SuppressWarnings("unchecked")
	public Iterator<String> getKeys() {
		return this.configureation.getKeys();
	}

	public String getString(String key) {
		return this.configureation.getString(key);
	}

	public String getString(String key, String defaultValue) {
		return this.configureation.getString(key, defaultValue);
	}

	public String[] getStringArray(String key) {
		return this.configureation.getStringArray(key);
	}

	public String getEncoding() {
		return this.configureation.getEncoding();
	}

	public void setEncoding(String encoding) {
		this.configureation.setEncoding(encoding);
	}

	@SuppressWarnings("unchecked")
	public List<Object> getList(String key) {
		return this.configureation.getList(key);
	}

	public void load(Reader in) throws ConfigurationException {
		this.configureation.load(in);
	}

	public void save() throws ConfigurationException {
		this.configureation.save();
	}

	@SuppressWarnings("rawtypes")
	public List getList(String arg0, List arg1) {
		return null;
	}
}
