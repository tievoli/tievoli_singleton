/*
 * @(#)CommonInterceptor.java 1.0 2016年7月7日
 *
 * Copyright (c) 2015 www.yescai.com. All Rights Reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.tievoli.framework.web.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 通用拦截器.
 * 
 * @author Ryan Jiang
 * 
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
	
	/** 日志记录器. */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/** 时间容器. */
	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");

	/** {@inheritDoc} */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) throws Exception {
		StringBuilder req = logRequest(request);
		StringBuilder jvm = logJVM();

		req.append(jvm);
		logger.debug(req.toString());

		jvm = null;
		req = null;
	}

	/** {@inheritDoc} */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	/** {@inheritDoc} */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		if (logger.isDebugEnabled()) {
			long beginTime = System.currentTimeMillis();
			startTimeThreadLocal.set(beginTime);
		}
		return super.preHandle(request, response, obj);
	}

	/**
	 * 记录JVM使用情况.
	 * 
	 * @return 返回JVM使用情况
	 */
	private StringBuilder logJVM() {
		StringBuilder sb = new StringBuilder("\n");
		if (logger.isDebugEnabled()) {
			long beginTime = startTimeThreadLocal.get();
			long endTime = System.currentTimeMillis();
			try {

				sb.append("//==================================JVM==================================\n");
				sb.append("开始时间：" + getJVMUseTime(beginTime)).append("\n");
				sb.append("结束时间：" + getJVMUseTime(endTime)).append("\n");
				sb.append("消耗时间：" + getJVMUseTime(endTime - beginTime)).append("\n");
				sb.append("最大内存：" + getMaxMemory()).append("M\n");
				sb.append("已分配内存：" + getTotalMemory()).append("M\n");
				sb.append("已分配中的剩余空间：" + getFreeMemory()).append("M\n");
				sb.append("最大可用内存：" + getMaxCanUseMemory()).append("M\n");
				sb.append("=========================================================================//\n");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return sb;
	}

	/**
	 * 记录请求情况.
	 * 
	 * @param request
	 *            请求对象
	 * @return 返回请求情况
	 */
	private StringBuilder logRequest(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder("\n");
		if (logger.isDebugEnabled()) {
			sb.append("//====================================Request=============================\n");
			sb.append("URI : " + request.getRequestURI()).append("\n");
			sb.append("URL : " + request.getRequestURL()).append("\n");
			sb.append("IP : " + request.getRemoteAddr()).append("\n");
			sb.append("Referer URI : " + request.getHeader("referer")).append("\n");
			sb.append("Method : " + request.getMethod()).append("\n");
			sb.append("User Agent : " + request.getHeader("User-Agent")).append("\n");
			sb.append("Session : " + request.getSession().getId()).append("\n");
			sb.append("Locale : " + request.getLocale().getCountry()).append("\n");
			sb.append("Parameters : \n");

			Enumeration<?> e = request.getParameterNames();

			if (e.hasMoreElements()) {
				String pName = "";
				String pValue = "";
				do {
					pName = (String) e.nextElement();
					pValue = request.getParameter(pName);
					sb.append(pName + " : [" + pValue + "]\n");
				} while (e.hasMoreElements());
			} else {
				sb.append(" is Empty \n");

			}
			sb.append("=========================================================================//\n");
		}

		return sb;
	}

	/**
	 * 获取最大内存.
	 * 
	 * @return the max memory
	 */
	private String getMaxMemory() {
		return String.valueOf(Runtime.getRuntime().maxMemory() / 1024 / 1024);
	}

	/**
	 * 获取已用内存.
	 * 
	 * @return the total memory
	 */
	private String getTotalMemory() {
		return String.valueOf(Runtime.getRuntime().totalMemory() / 1024 / 1024);
	}

	/**
	 * 获取空闲内存.
	 * 
	 * @return the free memory
	 */
	private String getFreeMemory() {
		return String.valueOf(Runtime.getRuntime().freeMemory() / 1024 / 1024);
	}

	/**
	 * 获取最大可用内存.
	 * 
	 * @return the max can use memory
	 */
	private String getMaxCanUseMemory() {
		return String.valueOf((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
	}

	/**
	 * 记录JVM使用时间.
	 * 
	 * @param time
	 *            the time
	 * @return the JVM use time
	 */
	private String getJVMUseTime(long time) {
		SimpleDateFormat format = new SimpleDateFormat(TIME_SSS);
		return format.format(new Date(time));
	}

	/** The Constant TIME_SSS. */
	private static final String TIME_SSS = "HH:mm:ss.SSS";
}
