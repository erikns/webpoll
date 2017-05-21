package no.hib.megagruppe.webpoll.models.answering;

import java.util.Iterator;

public class SurveyQuestionModelIterator implements Iterator<SurveyQuestionModel>{
	private SurveyQuestionModel[] questions;
	private int arrayLength;
	private int currentIndex;
	
	public SurveyQuestionModelIterator(SurveyQuestionModel[] questions){
		this.questions = questions;
		arrayLength = questions.length;
		currentIndex = 0;
	}
	
	@Override
	public boolean hasNext() {
		return currentIndex < arrayLength;
	}

	@Override
	public SurveyQuestionModel next() {
		SurveyQuestionModel question = questions[currentIndex];
		currentIndex++;
		return question;
	}
}
