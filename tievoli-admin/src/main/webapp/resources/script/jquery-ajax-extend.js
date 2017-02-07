/**
 * 扩展jquery的ajax方法.
 */
(function($){
	/**
	 * 备份JQuery的ajax.
	 */
	if(!$._ajax){
		$._ajax = $.ajax;
	}
	
	/**
	 * 定制自己的ajax.
	 */
	$.ajax = function(options) {
	
		var copyOptions = $.extend(false, {}, options);
	
		copyOptions.success = function(data, status, jqXHR) {
			if(options.success) {
				options.success(data, status, jqXHR);
			}
		};
	
		copyOptions.complete = function(data, status) {
			if(options.complete) {
				options.complete(data, status);
			}
		};
	
		copyOptions.error = function(jqXHR, textStatus, errorThrown) {
			//如果status = 901
			if(jqXHR.status == 901) {
				return;
			}
	
			var validationErrorObj = null;
			try{
				if(jqXHR.responseText != '') {
					validationErrorObj = JSON.parse(jqXHR.responseText);
				}
			}catch(ex){
				var verror = {};
				verror.code = 'sys.err.001';
				verror.message = window.message.getMessage(verror.code) || '系统异常，请刷新重试';
	
				if(options.exception) {
					options.exception(verror);
				}else{
					alert(verror.message);
				}
				return;
			}
			
			if(validationErrorObj != null && validationErrorObj != '' && options.validationException && validationErrorObj.validationError) {
				options.validationException(validationErrorObj.validationError);
				return;
			}
	
			var errorObj = JSON.parse(jqXHR.responseText);
			if(options.exception && errorObj.error) {
				options.exception(errorObj.error);
			}
	
			if(options.error) {
				options.error(jqXHR, textStatus, errorThrown);
			}
		};
	
		$._ajax(copyOptions);
	};
})(jQuery);  