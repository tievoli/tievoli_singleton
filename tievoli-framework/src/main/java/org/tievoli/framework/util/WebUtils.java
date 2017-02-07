package org.tievoli.framework.util;


/**
 * web相关的工具类.
 * 
 * @author Ryan Jiang
 * 
 */
public class WebUtils {

	private static final String project_prefix = "tievoli";

	public static void initSystemKey(String systemKey, String defaultvalue) {
		if (System.getProperty(systemKey) == null)
			System.setProperty(systemKey, defaultvalue);
	}

	public static void setupWeb() {
		initSystemKey(project_prefix + ".dtso", "local");
		setupDatasource();
		initSystemKey(project_prefix + ".cache", "local");
		initSystemKey(project_prefix + ".log4j", "local");
		initSystemKey(project_prefix + ".config", "classpath:properties");
		initSystemKey(project_prefix + ".instance", "");

	}

	private static void setupDatasource() {
		String opmode = System.getProperty(project_prefix + ".dtso");
		if ("local".equals(opmode)) {
			initSystemKey(project_prefix + ".datasource", "jdbc");
		} else
			initSystemKey(project_prefix + ".datasource", "jndi");
	}
}
