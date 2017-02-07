package org.tievoli.framework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.ModelAndView;
import org.tievoli.consts.Globals;
import org.tievoli.consts.MessageKey;
import org.tievoli.framework.exception.BizRuntimeException;

/**
 * 消息工具类.
 * 
 * @author Ryan Jiang
 * 
 */
public abstract class AjaxMessageMapRenderer {

	protected static Logger logger = LoggerFactory.getLogger(AjaxMessageMapRenderer.class);

	public static Map<String, Object> success() {
		return success(MessageKey.CMM_INFO_001);
	}

	public static Map<String, Object> success(String code) {
		return success(code, null);
	}

	public static Map<String, Object> success(String code, Object[] arguments) {
		return RendererHelper.generateSuccess(code, RendererHelper.getMessageByCodeForSuccess(code, arguments));
	}

	public static void success(ModelAndView mav) {
		success(mav, MessageKey.CMM_INFO_001);
	}

	public static void success(ModelAndView mav, String code) {
		success(mav, code, null);
	}

	public static void success(ModelAndView mav, String code, Object[] arguments) {
		mav.addObject(Globals.JSON_RESULT_CODE, code);
		mav.addObject(Globals.JSON_RESULT_MESSAGE, JBeanRegistry.getMessageSourceAccessor().getMessage(code, arguments));
	}

	public static void error(ModelAndView mav) {
		error(mav, MessageKey.CMM_ERR_001);
	}

	public static void error(ModelAndView mav, String code) {
		error(mav, code, null);
	}

	public static void error(ModelAndView mav, String code, Object[] arguments) {
		mav.addObject(Globals.JSON_RESULT_CODE, code);
		mav.addObject(Globals.JSON_RESULT_MESSAGE, JBeanRegistry.getMessageSourceAccessor().getMessage(code, arguments));
	}

	public static Map<String, Object> error() {
		return error(MessageKey.CMM_WARN_001);
	}

	public static Map<String, Object> error(String code) {
		return error(code, null);
	}

	public static Map<String, Object> error(String code, Object[] arguments) {
		return RendererHelper.generateError(code, RendererHelper.getMessageByCode(code, arguments));
	}

	public static void sendMessage(ModelAndView mav, Map<String, Object> resultMap) {
		sendMessage(mav, resultMap, MessageKey.CMM_INFO_001);
	}

	public static void sendMessage(ModelAndView mav, Map<String, Object> resultMap, String successMessageKey) {
		sendMessage(mav, resultMap, successMessageKey, null);
	}

	public static void sendMessage(ModelAndView mav, Map<String, Object> resultMap, String successMessageKey, Object[] successMessageArguments) {
		String resultCode = String.valueOf(resultMap.get(Globals.RESULT_CODE));
		String resultArgs = String.valueOf(resultMap.get(Globals.RESULT_ARGUMENTS));

		if (!Globals.DEFAULT_SUCCESS_CODE.equals(resultCode)) {

			if (Globals.IS_SP_ALERT_MSG) {
				throw BizRuntimeException.create(MessageUtils.getMessageKey(resultCode), MessageUtils.getMessageArguments(resultArgs));
			} else {
				throw BizRuntimeException.create(MessageKey.CMM_ERR_001, new String[] { resultArgs });
			}

		} else {
			AjaxMessageMapRenderer.success(mav, MessageKey.CMM_INFO_001);
		}
	}

	public static List<Map<String, Object>> validationError(BindingResult bindingResult) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Object object : bindingResult.getAllErrors()) {
			if (object instanceof FieldError) {
				FieldError fieldError = (FieldError) object;

				String findingFieldName = null;
				boolean isFindingErrorMessage = false;

				Object[] arguments = fieldError.getArguments();
				for (String errorCode : fieldError.getCodes()) {
					String errorMessage = JBeanRegistry.getMessageSourceAccessor().getMessage(errorCode, arguments);
					findingFieldName = fieldError.getField();

					if (org.springframework.util.StringUtils.hasText(errorMessage)) {
						Map<String, Object> m = new HashMap<String, Object>();

						m.put(Globals.JSON_ERROR_VALIDATION_FIELD, fieldError.getField());
						m.put(Globals.JSON_RESULT_MESSAGE, errorMessage);
						list.add(m);
						isFindingErrorMessage = true;
						break;
					}
				}
				if (!isFindingErrorMessage) {
					Map<String, Object> m = new HashMap<String, Object>();
					m.put(Globals.JSON_ERROR_VALIDATION_FIELD, findingFieldName);
					m.put(Globals.JSON_RESULT_MESSAGE, JBeanRegistry.getMessageSourceAccessor().getMessage(MessageKey.CMM_WARN_001));
					list.add(m);
					isFindingErrorMessage = false;
				}
			}
		}
		return list;
	}

	private static final class RendererHelper {
		private static Map<String, Object> generateError(String code, String message) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(Globals.JSON_RESULT_CODE, code);
			map.put(Globals.JSON_RESULT_MESSAGE, message);
			return map;
		}

		public static String getMessageByCodeForSuccess(String code, Object[] arguments) {
			String message = JBeanRegistry.getMessageSourceAccessor().getMessage(code, arguments);
			if (!org.springframework.util.StringUtils.hasText(message)) {
				message = JBeanRegistry.getMessageSourceAccessor().getMessage(MessageKey.CMM_INFO_001);
			}
			return message;
		}

		private static Map<String, Object> generateSuccess(String code, String message) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(Globals.JSON_RESULT_CODE, code);
			map.put(Globals.JSON_RESULT_MESSAGE, message);
			return map;
		}

		private static String getMessageByCode(String code, Object[] arguments) {
			String message = JBeanRegistry.getMessageSourceAccessor().getMessage(code, arguments);

			if (!org.springframework.util.StringUtils.hasText(message)) {
				// if(Globals.IS_SP_ALERT_MSG) message =
				// JBeanRegistry.getMessageSourceAccessor().getMessage(MessageKey.CMM_WARN_140)
				// +
				// "\n[Error Code : "+code.replaceAll(MessageGroupKey.MESSAGE_CATEGORY_USER+".",
				// "")+"]\n"+(String)arguments[0];
				if (Globals.IS_SP_ALERT_MSG)
					message = (String) arguments[0];
				else
					message = JBeanRegistry.getMessageSourceAccessor().getMessage(MessageKey.CMM_WARN_001);
			}
			return message;
		}
	}
}
