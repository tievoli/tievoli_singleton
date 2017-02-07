/*
 * @(#)BizRuntimeException.java 1.0 2016年7月7日
 *
 * Copyright (c) 2016 Ryan Jiang. All Rights Reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.tievoli.framework.exception;

import org.springframework.validation.BindingResult;

/**
 * 自定义异常类.
 * 
 * @author Ryan Jiang
 * 
 */
public class BizRuntimeException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6261057751754392969L;

	private String code;

	private Object[] arguments;

	private BindingResult bindingResult;

	public BizRuntimeException() {
		super();
	}

	public BizRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BizRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizRuntimeException(String message) {
		super(message);
	}

	public BizRuntimeException(Throwable cause) {
		super(cause);
	}

	public static BizRuntimeException create(BindingResult bindingResult) {
		BizRuntimeException ex = new BizRuntimeException();
		ex.setBindingResult(bindingResult);
		return ex;
	}

	public static BizRuntimeException create(String errorCode) {
		return create(errorCode, null);
	}

	public static BizRuntimeException create(String errorCode, Object[] arguments) {
		BizRuntimeException ex = new BizRuntimeException();
		ex.setCode(errorCode);
		ex.setArguments(arguments);
		return ex;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}

}
