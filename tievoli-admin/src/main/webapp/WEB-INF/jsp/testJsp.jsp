<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标题</title>
<script type="text/javascript" src="/resources/script/require-jquery-2.1.4.js"></script>
<script type="text/javascript" src="/resources/script/biz.message.js"></script>
<script type="text/javascript" src="/resources/script/yescai.message.js"></script>
<script type="text/javascript" src="/resources/script/jquery-ajax-extend.js"></script>
</head>
<body>

	<h1>测试JSP View</h1>
	<hr/>
	
	<h5>看到这个表示返回的JSP视图成功,ModelAndView 使用默认创建即可,返回的路径设置在/WEB-INF/jsp/**.jsp</h5>

	<h1 id="testJson">点击这里进行JSON测试</h1>
	<h5>
		<div id="json"></div>
	</h5>
	
	<h1>
		<a href="/sample/testFreemarker">点击这里进入到freemarker页面</a>
	</h1>
	
	<h1 id="testExceptionJson">点击这里测试JSON异常处理</h1>
	
	<h1><a href="/sample/testException">点击这里测试基本异常处理</a></h1>
	<script type="text/javascript">
		$(function(){
			
			$("#testJson").on('click',function(){
				$.ajax({
	                url     : "/sample/testJson",
	                type    : "get",
	                dataType: "json",
	                success : function(result) {
	                	
	                	$("#json").html("").html("Success:"+result.success+" , Message: "+result.message);
	                	
	                },exception : function(response) {
	                	alert(response.message);                	
	                }
	            });
			});
			
			
			$("#testExceptionJson").on('click',function(){
				$.ajax({
	                url     : "/sample/testExceptionJson",
	                type    : "get",
	                dataType: "json",
	                success : function(result) {
	                	
	                	alert(result.success + " , " + result.message);
	                	
	                },exception : function(response) {
	                	alert(response.message);                	
	                }
	            });
			});
		});
	
	</script>
</body>
</html>