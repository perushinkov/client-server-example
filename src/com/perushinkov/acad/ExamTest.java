package com.perushinkov.acad;

import java.util.ArrayList;

public class ExamTest {
	private String id;
	private ArrayList<ExamQuestion> questions;

	public ExamTest(String id) {
		this.id = id;
		
		questions = new ArrayList<ExamQuestion>();
	}
	
	public ArrayList<ExamQuestion> getQuestions() {
		return questions;
	}
	public void addQuestion(String text, int correctAnswer, String[] answers) {
		questions.add(new ExamQuestion(text, correctAnswer, answers));
	}
	
	private class ExamQuestion {
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
}
