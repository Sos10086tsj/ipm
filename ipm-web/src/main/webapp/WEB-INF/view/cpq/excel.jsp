<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工厂Excel解析</title>
</head>
<body>
	<div id="js_menu"></div>
	<div>
		<h1>上传工厂excel</h1>
		<div id="btn_excel_upload"></div>
	</div>
	<div>
		<h1>PDF Order 解析结果</h1>
		<p>请仔细审核解析的结果，如果有问题，请即时修改</p>
		<div id="excel_grid"></div>
	</div>
</body>
<%@include file="/WEB-INF/view/base/baselib.jspf" %>
<script type="text/javascript" src="${ctx}/resources/js/cpq/cpq.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/cpq/excel.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/cpq/excelStore.js"></script>
<script>
	var ctx = '${ctx}';
</script>
</html>