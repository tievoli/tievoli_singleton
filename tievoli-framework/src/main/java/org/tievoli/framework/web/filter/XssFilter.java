/*
 * @(#)XssFilter.java 1.0 2016年7月6日
 *
 * Copyright (c) 2015 www.yescai.com. All Rights Reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.tievoli.framework.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.tievoli.util.StringUtils;

/**
 * XSS过滤器.
 * 
 * @author Ryan Jiang
 * 
 */
public class XssFilter implements Filter {

	/**
	 * 重新HttpServletRequestWrapper的包装类中后去参数信息的方法.
	 * 
	 * @author Ryan Jiang
	 * 
	 */
	static class FilteredRequest extends HttpServletRequestWrapper {

		public FilteredRequest(ServletRequest request) {
			super((HttpServletRequest) request);
		}

		public String getParameter(String paramName) {
			String value = super.getParameter(paramName);
			return transferData(value);
		}

		@SuppressWarnings("unchecked")
		public Map<String, String[]> getParameterMap() {
			Map<String, String[]> parameterMap = super.getParameterMap();
			Set<String> keySet = parameterMap.keySet();
			Iterator<String> itrator = keySet.iterator();

			Map<String, String[]> cleanMap = new HashMap<String, String[]>();

			while (itrator.hasNext()) {
				String key = itrator.next();
				String[] paramValues = parameterMap.get(key);

				if (paramValues == null) {
					cleanMap.put(key, paramValues);
				} else {
					int count = paramValues.length;
					String[] encodedValues = new String[count];

					for (int i = 0; i < count; i++) {
						encodedValues[i] = transferData(paramValues[i]);
					}
					cleanMap.put(key, encodedValues);
				}
			}
			return cleanMap;
		}

		/**
		 * 转换方法,防止脚本攻击.
		 * 
		 * @param data
		 * @return
		 */
		private String transferData(String data) {
			if (data == null) {
				return data;
			}
			data = StringUtils.replace(data, "select", "s elect");
			data = StringUtils.replace(data, "insert", "i nsert");
			data = StringUtils.replace(data, "update", "u pdate");
			data = StringUtils.replace(data, "delete", "d elete");
			data = StringUtils.replace(data, "drop", "d rop");
			data = StringUtils.replace(data, "union", "u nion");
			data = StringUtils.replace(data, "exec", "e xec");

			data = StringUtils.replace(data, "@@variable", "");
			data = StringUtils.replace(data, "@variable", "");
			data = StringUtils.replace(data, "<script", "");
			data = StringUtils.replace(data, "script/>", "");
			data = StringUtils.replace(data, "sysobject", "");
			data = StringUtils.replace(data, "sp_", "");
			data = StringUtils.replace(data, "xp_", "");

			data = StringUtils.replace(data, "script", "");
			data = StringUtils.replace(data, "iframe", "");

			return data;
		}

		public String[] getParameterValues(String paramName) {
			String values[] = super.getParameterValues(paramName);
			if (values == null) {
				return values;
			}
			for (int index = 0; index < values.length; index++) {
				// if(denyString.contains(values[index])) {
				values[index] = transferData(values[index]);

				// }
			}
			return values;
		}

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new FilteredRequest(request), response);
	}

	public void destroy() {
	}

	public void init(FilterConfig filterConfig) {
	}

}
