package com.perushinkov.acad;

import java.util.ArrayList;

public class ResultsService {
    private DB db;
    public ResultsService() {
    	db = new DB("C:\\Documents and Settings\\root\\Desktop\\workspace\\client-server-example\\WebContent\\WEB-INF");
    }

    public String getResults(String userName) {
    	ArrayList<ExamUser> users = db.getUsers();
    	ArrayList<ExamResult> foundResults = null;
    	for (ExamUser user: users) {
    		if (user.getUsername().equals(userName)) {
    			foundResults = db.getResults(userName);
    			break;
    		}
    	}
    	StringBuffer sb = new StringBuffer();
    	for (ExamResult res: foundResults) {
    		sb.append(res.toString() + "\n");
    	}
    	return sb.toString();
    }
}