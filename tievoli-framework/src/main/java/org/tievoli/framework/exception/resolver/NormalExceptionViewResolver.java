/**
 * 
 */
package org.tievoli.framework.exception.resolver;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.tievoli.consts.Globals;
import org.tievoli.framework.util.AjaxMessageMapRenderer;

/**
 * 普通异常视图解析器.
 * 
 * @author Ryan Jiang
 * 
 */
public class NormalExceptionViewResolver extends AbstractExceptionViewResolver {

	@Override
	public ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object obj, String errorCode, Object[] errorArguments, Exception exception) {

		String viewName = "/common/error";

		ModelAndView mav = new ModelAndView(viewName);

		Map<String, Object> tempMap = AjaxMessageMapRenderer.error(errorCode, errorArguments);
		tempMap.put("message", tempMap.get("message")/*
													 * +"\ncause by:"+
													 * stackTraceString
													 */);

		mav.addObject(Globals.JSON_ERROR_ROOT_ELEMENT_VALUE, tempMap);
		Map<String, Object> errorMessage = AjaxMessageMapRenderer.error(errorCode, errorArguments);

		logger.error(new StringBuffer().append("error_code : {}\n").append("error_message : {}").toString(), new Object[] { errorCode, errorMessage });
		return mav;
	}

}
