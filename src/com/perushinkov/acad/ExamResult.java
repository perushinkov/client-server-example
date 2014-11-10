package com.perushinkov.acad;

import java.util.ArrayList;

public class ExamResult {
	private String user;
	private String test;
	private long timestamp;
	private ArrayList<Integer> answers;
	
	public ExamResult(String user, String test, long timestamp) {
		this.user = user;
		this.test = test;
		this.timestamp = timestamp;
		
		answers = new ArrayList<Integer>();
	}
	
	void addAnswer(Integer selected) {
		answers.add(selected);
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("User: " + user + "\n");
		sb.append("TestId: " + test + "\n");
		sb.append("Milliseconds since 1970: " + timestamp + "\n");
		sb.append("Answers: " + answers.toString() + "\n");
		return sb.toString();
	}
	
	public String getUser() {
		return user;
	}
	public String getTest() {
		return test;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public ArrayList<Integer> getAnswers() {
		return answers;
	}
}
