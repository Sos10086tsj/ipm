<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单PDF order 解析结果</title>
</head>
<body>
	<h1>PDF Order 解析结果</h1>
	<p>请仔细审核解析的结果，如果有问题，请即时修改</p>
	<div id="pdf_grid"></div>
</body>
<%@include file="/WEB-INF/view/base/baselib.jspf" %>
<script type="text/javascript" src="${ctx}/resources/js/cpq/pdf.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/cpq/pdfStore.js"></script>
</html>