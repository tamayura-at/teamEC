<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="3; URL='HomeAction'" />
<link rel="stylesheet" type="text/css" href="./css/textStyle.css">
<title>パスワード再設定完了画面</title>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp" />
	</div>

	<div id="main">
		<div id="top">
			<p>パスワード再設定完了画面</p>
		</div>
		<div id="complete">
			<p><s:property value="message" /></p>
		</div>
	</div>

</body>
</html>