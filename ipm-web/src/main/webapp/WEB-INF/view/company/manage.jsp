<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IPM - Efficient System</title>
</head>
<body>
	<!-- 登录框 -->
	<div>
		<form action="${ctx }/compnaymanage/createuser" method="post">
			<input name="username" placeholder="请输入用户名"/>
			<input name="passowrd" placeholder="请输入密码"/>
			<input name="company" type="hidden" value="${companyId }"/>
			<button>登录</button>
		</form>
	</div>
	
	<!-- 客户登录 -->
	
</body>
</html>