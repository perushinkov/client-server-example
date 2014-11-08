package com.perushinkov.acad;

import java.util.ArrayList;

public class ExamQuestion {
	private String text;

	private ArrayList<String> answers;
	
	private int correctAnswer;
	public ExamQuestion(String text, int correctAnswer, String[] answers) {
		this.text = text;
		this.correctAnswer = correctAnswer;
		
		this.answers = new ArrayList<String>();
		for (String str: answers) {
			this.answers.add(str);
		}
	}
	
	public String getText() {
		return text;
	}
	public ArrayList<String> getAnswers() {
		return answers;
	}
	public int getCorrectAnswer() {
		return correctAnswer;
	}
}