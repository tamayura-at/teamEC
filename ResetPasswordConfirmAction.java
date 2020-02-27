package com.internousdev.rose.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.rose.dao.UserInfoDAO;
import com.internousdev.rose.util.CommonUtility;
import com.internousdev.rose.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordConfirmAction extends ActionSupport implements SessionAware{
	private String loginUserId;
	private String loginPassword;
	private String password;
	private String reConfirmationPassword;
	private String errorMessage;
	private String concealPassword;

	private List<String> errorLoginUserIdMessageList;
	private List<String> errorLoginPasswordMessageList;
	private List<String> errorPasswordMessageList;
	private List<String> errorReConfirmationPasswordMessageList;

	private Map<String, Object> session;


	public String execute() throws SQLException  {
		String result = ERROR;

//パスワード一致確認 doPasswordCheck を行う。

		InputChecker inputChecker = new InputChecker();

		setErrorLoginUserIdMessageList(inputChecker.doCheck("ユーザーID", loginUserId, 1, 8, true, false, false, true, false, false));
		setErrorLoginPasswordMessageList(inputChecker.doCheck("現在のパスワード", loginPassword, 1, 16, true, false, false, true, false, false));
		setErrorPasswordMessageList(inputChecker.doCheck("新しいパスワード", password, 1, 16, true, false, false, true, false, false));
		setErrorReConfirmationPasswordMessageList(inputChecker.doCheck("新しいパスワード(再確認）", reConfirmationPassword, 1, 16, true, false, false, true, false, false));

//		resetPasswordConfirm.jsp で値を表示するため
//		ResetPasswordCompleteActionに値を格納するための記述
		session.put("loginUserId", loginUserId);
		session.put("newPassword", password);

//		もし、未入力・文字種・桁数に問題が無ければ【処理】パスワード一致確認を行う
		if(errorLoginUserIdMessageList.size() == 0 && errorLoginPasswordMessageList.size() == 0 && errorPasswordMessageList.size() == 0 && errorReConfirmationPasswordMessageList.size() == 0) {

			String passwordErrorMessage = null;
			passwordErrorMessage = inputChecker.doPasswordCheck(password, reConfirmationPassword);
//			もし、パスワードを確認してerrorMessageが戻ってこなければ、【処理】ユーザー存在確認を行う。
			if(passwordErrorMessage == null) {
				UserInfoDAO userInfoDAO = new UserInfoDAO();

				int res = userInfoDAO.selectPassword(loginUserId, loginPassword);

//				存在する場合は、パスワード再設定確認画面に遷移する。
//				存在しない場合は、自画面に遷移し、メッセージ一覧[ユーザーIDと現在のパスワードがDBに存在しない]を表示する。
				if(res > 0) {
//					パスワードを隠しながら送る作業
					CommonUtility commonUtilityPassword = new CommonUtility();
					concealPassword = commonUtilityPassword.concealPassword(password);
					session.put("concealPassword", concealPassword);

					result = SUCCESS;
				} else {
					setErrorMessage("ユーザーIDまたは現在のパスワードが異なります。");
				}
//			もし、パスワードが間違っていてエラーメッセージが戻ってくれば(一致しない場合)、自画面に遷移し、メッセージ一覧「パスワード間違いを表示する」
			} else {
				setErrorMessage(passwordErrorMessage);
//				passwordErrorMessageの中身はInputCheckerの"新しいパスワードと新しいパスワード(再確認)"が一致しません。
			}
		}
		return result;
	}

	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReConfirmationPassword() {
		return reConfirmationPassword;
	}
	public void setReConfirmationPassword(String reConfirmationPassword) {
		this.reConfirmationPassword = reConfirmationPassword;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getConcealPassword() {
		return concealPassword;
	}
	public void setConcealPassword(String concealPassword) {
		this.concealPassword = concealPassword;
	}


	public List<String> getErrorLoginUserIdMessageList() {
		return errorLoginUserIdMessageList;
	}
	public void setErrorLoginUserIdMessageList(List<String> errorLoginUserIdMessageList) {
		this.errorLoginUserIdMessageList = errorLoginUserIdMessageList;
	}
	public List<String> getErrorLoginPasswordMessageList() {
		return errorLoginPasswordMessageList;
	}
	public void setErrorLoginPasswordMessageList(List<String> errorLoginPasswordMessageList) {
		this.errorLoginPasswordMessageList = errorLoginPasswordMessageList;
	}
	public List<String> getErrorPasswordMessageList() {
		return errorPasswordMessageList;
	}
	public void setErrorPasswordMessageList(List<String> errorPasswordMessageList) {
		this.errorPasswordMessageList = errorPasswordMessageList;
	}
	public List<String> getErrorReConfirmationPasswordMessageList() {
		return errorReConfirmationPasswordMessageList;
	}
	public void setErrorReConfirmationPasswordMessageList(List<String> errorReConfirmationPasswordMessageList) {
		this.errorReConfirmationPasswordMessageList = errorReConfirmationPasswordMessageList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
