package no.hib.megagruppe.webpoll.models;

import java.sql.Time;
import java.time.Duration;
import java.util.Iterator;
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
public class SurveyAnsweringModel implements Iterable<SurveyQuestionModel>{
	
	private SurveyQuestionModel[] questions;
	private String surveyName;
	private Time surveyDate;
	private Time surveyDeadline;
	private String creator;
	
	private int currentQuestionIndex;
	
	
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
		currentQuestionIndex = 0;
	}

	/**
	 * Checks whether there is a next question in the survey.
	 * @return True if there is a next question in the survey.
	 */
	public boolean hasNextQuestion(){
		return currentQuestionIndex+1 < questions.length;
	}
	
	/**
	 * Checks whether there is a previous question in the survey.
	 * @return True if there is a previous question in the survey.
	 */
	public boolean hasPreviousQuestion(){
		return currentQuestionIndex > 0;
	}
	
	/**
	 * Returns the current question in the survey.
	 * @return The current question in the survey.
	 */
	public SurveyQuestionModel currentQuestion(){
		SurveyQuestionModel qm = questions[currentQuestionIndex];
		return qm;
	}
	
	/**
	 * Moves the questionIndex up by one and returns the new current question.
	 * @return The new current question.
	 */
	public SurveyQuestionModel nextQuestion(){
		currentQuestionIndex++;
		SurveyQuestionModel qm = questions[currentQuestionIndex];
		return qm;
	}
	
	/**
	 * Moves the questionIndex down by one and returns the new current question.
	 * @return The new current question.
	 */
	public SurveyQuestionModel previousQuestion(){
		currentQuestionIndex--;
		SurveyQuestionModel qm = questions[currentQuestionIndex];
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
	 * The questions number. The current index + 1.
	 * @return The question number.
	 */
	public int currentQuestionNumber(){
		return currentQuestionIndex + 1;
	}
	
	@Override
	public Iterator<SurveyQuestionModel> iterator() {
		return new SurveyQuestionModelIterator(questions);
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
