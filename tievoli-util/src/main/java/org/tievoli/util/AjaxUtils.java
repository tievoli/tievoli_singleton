package org.tievoli.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * Ajax的工具类.
 * 
 * @author Ryan Jiang
 * 
 */
public abstract class AjaxUtils {

	public static final String AJAX_HEADER = "X-Requested-With";

	public static boolean isAjaxRequest(String requestedWith) {
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		if (StringUtils.hasText(request.getHeader(AJAX_HEADER))) {
			return isAjaxRequest(request.getHeader(AJAX_HEADER));
		}
		return false;
	}
}
