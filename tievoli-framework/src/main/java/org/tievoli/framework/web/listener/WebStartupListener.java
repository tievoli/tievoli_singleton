/*
 * @(#)WebStartupListener.java 1.0 2016年7月7日
 *
 * Copyright (c) 2015 www.yescai.com. All Rights Reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.tievoli.framework.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tievoli.framework.util.WebUtils;

public class WebStartupListener implements ServletContextListener {

	private static final Logger log = LoggerFactory.getLogger(WebStartupListener.class);

	public void contextInitialized(ServletContextEvent sce) {
		if (log.isDebugEnabled()) {
			log.debug("**************************************");
			log.debug("Initializing web context.");
			log.debug("**************************************");
		}
		WebUtils.setupWeb();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		if (log.isDebugEnabled()) {
			log.debug("**************************************");
			log.debug("Destroyed web context.");
			log.debug("**************************************");
		}
	}

}
