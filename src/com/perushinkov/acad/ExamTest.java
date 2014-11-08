package com.perushinkov.acad;

import java.util.ArrayList;

public class ExamTest {
	private String id;
	private ArrayList<ExamQuestion> questions;

	public ExamTest(String id) {
		this.id = id;
		
		questions = new ArrayList<ExamQuestion>();
	}
	
	public String getId() {
		return id;
	}
	public ArrayList<ExamQuestion> getQuestions() {
		return questions;
	}
	public void addQuestion(String text, int correctAnswer, String[] answers) {
		questions.add(new ExamQuestion(text, correctAnswer, answers));
	}
}
