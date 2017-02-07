(function() {
	var Properties = function() {};

	Properties.prototype = {
		 "ctx" : window.ctx ? window.ctx : ""
		,"baseUrl" : window.baseUrl ? window.baseUrl : "/resources/script"
		,"yescai.data.ajax.use" : true
		,"yescai.data.ajax.indexUrl" : "/index.do"
		,"yescai.data.ajax.errorcode.session_time_out" : "901"

	};

	window.properties = new Properties();
})();