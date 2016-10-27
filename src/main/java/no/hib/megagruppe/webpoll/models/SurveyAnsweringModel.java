package no.hib.megagruppe.webpoll.models;

import java.sql.Date;
import java.time.Duration;
import java.util.List;

import no.hib.megagruppe.webpoll.entities.QuestionEntity;

/**
 * 
 * @author Magnus
 * 
 * This object is used when answering a survey and it represents a survey ready to be answered.
 * 
 * From here you can get all the information you need about the survey, 
 * including the questions, whether there are more questions, 
 * and meta-information like surveyName and dates, etc.
 * 
 * This class will provide you with all the questions in the survey, which
 * can then be used to instantiate the JSPs for answering.
 * 
 * -------------------------
 * 
 * When a student answer a question it is temporarily stored inside SurveyQuestionModel.
 * When a student finishes the survey all the answer-data will be committed to the database.
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
		int i = 0; // XXX Finnes det en finere måte å gjøre dette på?
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
	 * Always call hasNextQuestion() before calling this method.
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
		// FIXME Kan ikke bruke Date for å finne ut sekund of minutt etc! Må endre til Time (DateTime).
		
		return p.toString(); // TODO Change the way the string is represented.
	}
	
	/**
	 * Resets the iterator. The next question will be the first question in the array.
	 * 
	 * This method is useful when you want to iterate through the questions when this survey has been answered.
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
