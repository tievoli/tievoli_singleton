package org.tievoli.framework.util;

import org.springframework.context.support.MessageSourceAccessor;

/**
 * 用于添加静态类.放在App层,用户可根据自己的需求定制.
 * 
 * 
 * @author Ryan Jiang
 * 
 */
public class JBeanRegistry {

	public static final MessageSourceAccessor getMessageSourceAccessor() {
		return (MessageSourceAccessor) SpringContextHolder.getBean(MessageSourceAccessor.class);
	}

}
