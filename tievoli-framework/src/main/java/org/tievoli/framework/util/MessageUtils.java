/*
 * @(#)MessageUtils.java 1.0 2015-11-4
 *
 * Copyright (c) 2015 www.yescai.com. All Rights Reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.tievoli.framework.util;

/**
 * 消息工具类.
 * 
 * @author Ryan Jiang
 * 
 */
public abstract class MessageUtils {

	/** The Constant MESSAGE_CATEGORY_USER. */
	private static final String MESSAGE_CATEGORY_USER = "tievoli";

	/**
	 * Gets the message key.
	 * 
	 * @param messageKey
	 *            the message key
	 * @return the message key
	 */
	public static String getMessageKey(String messageKey) {
		return MESSAGE_CATEGORY_USER + "." + messageKey;
	}

	/**
	 * Gets the message arguments.
	 * 
	 * @param argumentList
	 *            the argument list
	 * @return the message arguments
	 */
	public static String[] getMessageArguments(String argumentList) {
		return org.springframework.util.StringUtils.delimitedListToStringArray(argumentList, ";");
	}

}
