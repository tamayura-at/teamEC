<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/textStyle.css">
<link rel="stylesheet" type="text/css" href="./css/table.css">
<link rel="stylesheet" type="text/css" href="./css/formStyle.css">
<title>パスワード再設定確認画面</title>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp" />
	</div>

	<div id="main">
		<div id="top">
			<p>パスワード再設定確認画面</p>
		</div>
		<form action="ResetPasswordCompleteAction">
			<table class="vertical">
				<tr>
					<th>ユーザーID</th>
					<td><s:property value="loginUserId" />
				</tr>
				<tr>
					<th>パスワード</th>
					<td><s:property value="concealPassword" /></td>
				</tr>
			</table>
			<s:submit value="パスワード再設定" class="submit_btn" />
		</form>
		<form action="ResetPasswordAction">
			<s:submit value="戻る" class="submit_btn" />
			<s:hidden name="backFlg" value="1" />
		</form>
	</div>
</body>
</html>