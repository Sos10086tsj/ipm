<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
<meta charset="utf-8">
<title>IPM - Efficient System</title>
</head>
<body>
	<!-- 登录框 -->
	<div>
			<input name="js_username_id" name="username" placeholder="请输入用户名"/>
			<input name="js_passowrd_id" name="passowrd" placeholder="请输入密码"/>
			<input name="js_name_id" name="name" placeholder="请输入名字"/>
			<input name="js_company_id" name="companyId" type="hidden" value="${companyId }"/>
			<button onclick="company.submitCreateUser()">登录</button>
	</div>
	
	<!-- 客户登录 -->
	
</body>
<%@include file="/WEB-INF/view/base/baselib.jspf" %>
<script src="${ctx}/resources/js/company/company.js"></script>
<script>
	var ctx = '${pageContext.request.contextPath}';
</script>
</html>