(function(){

	var Message = function(options) {
	};

	var M = window.BizMessage.getBizMessage();
	
	Message.prototype = {

		getMessage : function( messageKey, paramList ) {
			var message = M[messageKey];
			if(message == undefined || message == ''){
				return null;
			}
			if(paramList) {
				for(var index in paramList) {
					message = message.replace("{"+index+"}", paramList[index]);
				}
			}

			return message;
		}
	};

	window.message =  new Message();
	
})();