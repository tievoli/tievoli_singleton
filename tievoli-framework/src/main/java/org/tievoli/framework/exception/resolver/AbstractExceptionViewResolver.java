package org.tievoli.framework.exception.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.tievoli.consts.MessageKey;
import org.tievoli.framework.exception.BizRuntimeException;
import org.tievoli.util.AjaxUtils;

/**
 * 统一异常处理类的抽象类.
 * 
 * @author Ryan Jiang
 * 
 */
public abstract class AbstractExceptionViewResolver implements HandlerExceptionResolver, Ordered {

	Logger logger = LoggerFactory.getLogger(getClass());

	private int order = Ordered.LOWEST_PRECEDENCE;

	private View view;

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) {
		logger.error("AbstractExceptionViewResolver exception catch : ", ex);
		ex.printStackTrace();

		String errorCode = getErrorCode(ex);
		Object[] errorArguments = getErrorArguments(ex);

		return doResolveException(request, response, obj, errorCode, errorArguments, ex);
	}

	protected boolean isValidationError(Exception exception) {
		if (!(exception instanceof BizRuntimeException)) {
			return false;
		}
		BizRuntimeException bizException = ((BizRuntimeException) exception);
		return bizException.getBindingResult() != null;
	}

	protected boolean isJSONResponseType(HttpServletRequest request, HandlerMethod handlerMethod) {
		if (handlerMethod == null || handlerMethod.getReturnType() == null || handlerMethod.getReturnType().getParameterType() == null) {
			return false;
		}
		return AjaxUtils.isAjaxRequest(request) || handlerMethod.getReturnType().getParameterType().isAssignableFrom(ResponseEntity.class);
	}

	private String getErrorCode(Exception exception) {
		if (!(exception instanceof BizRuntimeException)) {
			return MessageKey.CMM_ERR_001;
		}
		BizRuntimeException bizException = ((BizRuntimeException) exception);
		if (bizException != null && bizException.getCode() != null) {
			return bizException.getCode();
		} else if (bizException != null && bizException.getBindingResult() != null) {
			return MessageKey.CMM_ERR_001;
		} else {
			return MessageKey.CMM_ERR_001;
		}
	}

	private Object[] getErrorArguments(Exception exception) {
		if (exception instanceof BizRuntimeException)
			return ((BizRuntimeException) exception).getArguments();
		return null;
	}

	public abstract ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object obj, String errorCode, Object[] arguments, Exception exception);

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return this.order;
	}
}
