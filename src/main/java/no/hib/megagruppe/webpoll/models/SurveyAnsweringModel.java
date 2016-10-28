package no.hib.megagruppe.webpoll.models;

import java.sql.Time;
import java.time.Duration;
import java.util.List;

import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.util.DurationFormatter;

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
	private Time surveyDate;
	private Time surveyDeadline;
	private String creator;
	
	private int currentQuestionCounter;
	
	
	public SurveyAnsweringModel(List<QuestionEntity> questions, String surveyName, Time surveyDate, Time surveyDeadline, String creator){
		
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
	 * This method is for retrieving the answers from the previous question.
	 * 
	 * This method may throw an exception if called from PollQuestion servlet.
	 * @return The previous question, containing the answers.
	 */
	public SurveyQuestionModel getPreviousQuestion(){
		SurveyQuestionModel qm = questions[currentQuestionCounter-1];
		return qm;
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
		long remainingMillis = surveyDeadline.getTime() - surveyDate.getTime();
		Duration timeRemaining = Duration.ofMillis(remainingMillis);
		
		return DurationFormatter.formatDuration(timeRemaining);
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

	public Time getSurveyDate() {
		return surveyDate;
	}

	public Time getSurveyDeadline() {
		return surveyDeadline;
	}

	public String getCreator() {
		return creator;
	}

	public SurveyQuestionModel[] getQuestions() {
		return questions;
	}
	
}
