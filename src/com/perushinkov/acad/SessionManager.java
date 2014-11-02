package com.perushinkov.acad;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class SessionManager {
	private ArrayList<UserSession> sessions;
	DB dataManager;

	public SessionManager(){
		sessions = new ArrayList<UserSession>();
	}
	
	public UserSession createSession(HttpSession httpSess) {
		UserSession userSess = new UserSession(httpSess);
		sessions.add(userSess);
		return userSess;
	}

	public UserSession getSession(HttpSession httpSess) {
		for (UserSession session: sessions) {
			if (session.getHttpSession().getId() == httpSess.getId()) {
				return session;
			}
		}
		return null;
	}
	
	
	public DB getDataManager() {
		return dataManager;
	}
	public void setDataManager(DB db) {
		dataManager = db;
	}
}
