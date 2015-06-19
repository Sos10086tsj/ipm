<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报表打印</title>
</head>
<body>
	<div id="js_menu">
	</div>
	<div>
		<h1>选择导出条件</h1>
		<div id="js_report_export"></div>
	</div>
</body>
<%@include file="/WEB-INF/view/base/baselib.jspf" %>
<script type="text/javascript" src="${ctx}/resources/js/cpq/cpq.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/cpq/report.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/cpq/reportStore.js"></script>
<script>
	var ctx = '${ctx}';
</script>
</html>