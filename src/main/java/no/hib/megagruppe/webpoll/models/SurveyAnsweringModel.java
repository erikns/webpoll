package no.hib.megagruppe.webpoll.models;

import java.sql.Date;
import java.time.Duration;
import java.util.List;

import no.hib.megagruppe.webpoll.entities.QuestionEntity;

/**
 * 
 * @author Magnus
 *
 */
public class SurveyAnsweringModel {
	
	private SurveyQuestionModel[] questions;
	private String surveyName;
	private Date surveyDate;
	private Date surveyDeadline;
	private String creator;
	
	private int currentQuestionCounter;
	
	
	public SurveyAnsweringModel(List<QuestionEntity> questions, String surveyName, Date surveyDate, Date surveyDeadline, String creator){
		
		this.questions = new SurveyQuestionModel[questions.size()];
		int i = 0;
		for(QuestionEntity qe : questions){
			qe.getType();
			SurveyQuestionModel sqm = new SurveyQuestionModel(qe.getText(), qe.getType(), qe.getOptions());
			this.questions[i] = sqm;
			i++;
		}
		
		this.surveyName = surveyName;
		this.surveyDate = surveyDate;
		this.surveyDeadline = surveyDeadline;
		this.creator = creator;
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
	public SurveyQuestionModel getNextQuestion(){
		SurveyQuestionModel qm = questions[currentQuestionCounter];
		currentQuestionCounter++;
		return qm;
	}
	
	/**
	 * Get the remaining time, formatted nicely.
	 * @return The remaining time.
	 */
	public String getTimeRemaining(){
		
		Duration p = Duration.between(surveyDate.toLocalDate(), surveyDeadline.toLocalDate());
		
		return p.toString(); // TODO Change the way the string is represented.
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
