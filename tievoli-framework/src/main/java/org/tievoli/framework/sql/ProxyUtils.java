/*
* @(#)ProxyUtils.java 1.0 2016-1-4
*
* Copyright (c) 2015 www.yescai.com. All Rights Reserved.
* PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package org.tievoli.framework.sql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 代理工具类.
 * 
 * @author Ryan Jiang
 * 
 */
public class ProxyUtils {
	
	/**
	 * New instance.
	 *
	 * @param obj the obj
	 * @param handler the handler
	 * @return the object
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	@SuppressWarnings("rawtypes")
	public static Object newInstance(Object obj, InvocationHandler handler) throws IllegalArgumentException {
		Class[] ifaces = obj.getClass().getInterfaces();
		return newInstance(obj, handler, ifaces);
	}

	/**
	 * New instance.
	 *
	 * @param obj the obj
	 * @param handler the handler
	 * @param ifaces the ifaces
	 * @return the object
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public static Object newInstance(Object obj, InvocationHandler handler, Class<?>[] ifaces)
			throws IllegalArgumentException {
		ClassLoader cl = obj.getClass().getClassLoader();
		Object retObj = Proxy.newProxyInstance(cl, ifaces, handler);
		return retObj;
	}
}
