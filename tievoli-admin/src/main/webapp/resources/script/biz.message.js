(function(){
	
	var BizMessage = function(options) {
	};
	
	BizMessage.prototype = {
		getBizMessage: function(){
			var message = {
					"sys.error.001" : "系统错误,请刷新重试"
			};

			return message;
		}
	};
	
	window.BizMessage = new BizMessage();
})();