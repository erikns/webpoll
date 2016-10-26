package no.hib.megagruppe.webpoll.models;

import java.sql.Date;
import java.time.Duration;

/**
 * 
 * @author Magnus
 *
 */
public class SurveyAnsweringModel {
	
	private QuestionModel[] questions;
	private String surveyName;
	private Date surveyDate;
	private Date surveyDeadline;
	private String creator;
	
	private int currentQuestionCounter;
	
	
	public SurveyAnsweringModel(){
		currentQuestionCounter = 0;
	}
	
	/**
	 * Checks whether there are more questions left in the survey.
	 * @return True if there are more questions left in the survey.
	 */
	public boolean hasNextQuestion(){
		return currentQuestionCounter < questions.length;
	}
	
	/**
	 * Retrieves the next question. Throws an ArrayIndexOutOfBoundsException if there are no more questions left.
	 * @return The next question.
	 */
	public QuestionModel getNextQuestion(){
		QuestionModel qm = questions[currentQuestionCounter];
		currentQuestionCounter++;
		return qm;
	}
	
	/**
	 * Get the remaining time, formatted nicely.
	 * @return The remaining time.
	 */
	public String getTimeRemaining(){
		
		Duration p = Duration.between(surveyDate.toLocalDate(), surveyDeadline.toLocalDate());
		
		return p.toString(); // TODO
	}
	
	/**
	 * Use this method before iterating the answers.
	 * Resets the currentQuestionCounter.
	 */
	public void reset(){
		currentQuestionCounter = 0;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public Date getSurveyDate() {
		return surveyDate;
	}

	public Date getSurveyDeadline() {
		return surveyDeadline;
	}

	public String getCreator() {
		return creator;
	}
	
}
