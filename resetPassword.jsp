<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/textStyle.css">
<link rel="stylesheet" type="text/css" href="./css/table.css">
<link rel="stylesheet" type="text/css" href="./css/formStyle.css">
<title>パスワード再設定画面</title>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp" />
	</div>


	<div id="main">
		<div id="top">
			<p>パスワード再設定画面</p>
		</div>
		<!--未入力・文字種・桁数に問題があればエラーメッセージを表示する。  -->

		<s:if
			test="errorLoginUserIdMessageList != null && errorLoginUserIdMessageList.size() > 0">
			<div id="error">
				<s:iterator value="errorLoginUserIdMessageList">
					<s:property />
					<br />
				</s:iterator>
			</div>
		</s:if>

		<s:if
			test="errorLoginPasswordMessageList != null && errorLoginPasswordMessageList.size() > 0">
			<div id="error">
				<s:iterator value="errorLoginPasswordMessageList">
					<s:property />
					<br />
				</s:iterator>
			</div>
		</s:if>

		<s:if
			test="errorPasswordMessageList != null && errorPasswordMessageList.size() > 0">
			<div id="error">
				<s:iterator value="errorPasswordMessageList">
					<s:property />
					<br />
				</s:iterator>
			</div>
		</s:if>

		<s:if
			test="errorReConfirmationPasswordMessageList != null && errorReConfirmationPasswordMessageList.size() > 0">
			<div id="error">
				<s:iterator value="errorReConfirmationPasswordMessageList">
					<s:property />
					<br />
				</s:iterator>
			</div>
		</s:if>

		<!--新しいパスワードと再確認が一致しない場合に返ってくるエラーメッセージ  -->
		<s:if test="errorMessage != null">
			<div id="error">
				<s:property value="errorMessage" />
			</div>
		</s:if>

		<!--パスワード再設定のための項目一覧  -->
		<s:form action="ResetPasswordConfirmAction">
			<table class="vertical">

				<!--ユーザーIDでログイン画面から遷移の時 未入力状態  -->
				<s:if test="errorMessage == null && session.loginUserId == null">
					<tr>
						<th><label>ユーザーID</label></th>
						<td><s:textfield  class="textbox" name="loginUserId" value=""
								placeholder="ユーザーID" /></td>
					</tr>
				</s:if>
				<!--ユーザーIDでエラーが発生時の自画面遷移時 入力値を表示  -->
				<s:if test="errorMessage != null && session.loginUserId != null">
					<tr>
						<th><label>ユーザーID</label></th>
						<td><s:textfield  class="textbox" name="loginUserId" placeholder="ユーザーID"
								value='%{session.loginUserId}' /></td>
					</tr>
				</s:if>

				<!--ユーザーIDでパスワード再設定確認画面の戻るボタンを押下時の遷移 入力値を表示  -->
				<s:if test="errorMessage == null && session.loginUserId != null">
					<tr>
						<th><label>ユーザーID</label></th>
						<td><s:textfield class="textbox" name="loginUserId" placeholder="ユーザーID"
								value='%{session.loginUserId}' /></td>
					</tr>
				</s:if>

				<!--パスワード関係は常に未入力状態  -->
				<tr>
					<th><label>現在のパスワード</label></th>
					<td><s:password class="textbox" name="loginPassword" value=""
							placeholder="現在のパスワード" /></td>
				</tr>

				<tr>
					<th><label>新しいパスワード</label></th>
					<td><s:password class="textbox" name="password" value=""
							placeholder="新しいパスワード" /></td>
				</tr>
				<tr>
					<th><label>新しいパスワード（再確認）</label></th>
					<td><s:password class="textbox" name="reConfirmationPassword" value=""
							placeholder="新しいパスワード（再確認）" /></td>
				</tr>
			</table>
			<s:submit value="確認" class="submit_btn" />
		</s:form>
	</div>
</body>
</html>