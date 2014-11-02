package com.perushinkov.acad;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.perushinkov.acad.MyPage.*;

public class UserSession {
	private HttpSession session;
	private MyPage lastPage;
	
	public UserSession(HttpSession httpSession) {
		session = httpSession;
		lastPage = null;
	}
	public HttpSession getHttpSession() {
		return session;
	}
	public void respond(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (lastPage == null) {
			lastPage = LOGIN;
			PageConstructor.login(response, "");
			return;
		} 
		switch(lastPage) {
		case LOGIN:
			if (request.getParameter("action") == "login") {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
			} else {
				//refresh
				PageConstructor.login(response, "Welcome, again!");
			}
			break;
		case STUDENT_HOME:
			break;
		case TEACHER_HOME:
			break;
		case TEST_EDIT:
			break;
		case TEST_RESULT_STUDENT:
			break;
		case TEST_RESULT_TEACHER:
			break;
		case TEST_TAKE:
			break;
		case TEST_VIEW:
			break;
		default:
			assert false;
			break;
		}
	}
}
