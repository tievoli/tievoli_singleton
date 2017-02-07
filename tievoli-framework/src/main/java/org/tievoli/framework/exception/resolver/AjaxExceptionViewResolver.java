/**
 * 
 */
package org.tievoli.framework.exception.resolver;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tievoli.consts.Globals;
import org.tievoli.framework.exception.BizRuntimeException;
import org.tievoli.framework.util.AjaxMessageMapRenderer;

/**
 * Ajax异常视图处理器.
 * 
 * @author Ryan Jiang
 * 
 */
public class AjaxExceptionViewResolver extends AbstractExceptionViewResolver {

	@Override
	public ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object obj, String errorCode, Object[] arguments, Exception exception) {

		if (!isJSONResponseType(request, (HandlerMethod) obj)) {
			return null;
		}
		logger.debug("AjaxExceptionViewResolver!!!");

		response.setStatus(Globals.JSON_ERROR_STATUS_CODE);

		if (isValidationError(exception)) {
			return new ModelAndView(getView(), Globals.JSON_ERROR_VALIDATION_ROOT_ELEMENT_VALUE, AjaxMessageMapRenderer.validationError(((BizRuntimeException) exception).getBindingResult()));
		}

		Map<String, Object> errorMessage = AjaxMessageMapRenderer.error(errorCode, arguments);

		logger.error(new StringBuffer().append("error_code : {}\n").append("error_message : {}").toString(), new Object[] { errorCode, errorMessage });

		return new ModelAndView(getView(), Globals.JSON_ERROR_ROOT_ELEMENT_VALUE, AjaxMessageMapRenderer.error(errorCode, arguments));
	}

}
