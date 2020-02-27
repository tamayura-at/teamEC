package com.internousdev.rose.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.rose.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordCompleteAction extends ActionSupport implements SessionAware{
	private String message;
	private Map<String, Object> session;

	public String execute() throws SQLException {
		String result = ERROR;
		UserInfoDAO userInfoDAO = new UserInfoDAO();

		int res = userInfoDAO.updatePassword(session.get("newPassword").toString(), session.get("loginUserId").toString());

		session.remove("loginUserId");
		session.remove("newPassword");

		if(res > 0) {
			setMessage("パスワードの再設定が完了しました。");
		} else {
			return result;
		}
		result = SUCCESS;
		return result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
