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
}
