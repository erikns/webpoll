package no.hib.megagruppe.webpoll.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.hib.megagruppe.webpoll.models.SurveyAnsweringModel;
import no.hib.megagruppe.webpoll.models.SurveyQuestionModel;

/**
 * A class for working with survey-answering sessions.
 * In reality it is a wrapper for HttpServletRequest.
 */
public class SurveyAnsweringSessionManager {
	
	private final String SURVEY_ATTRIBUTE_NAME = "survey";
	private final String ERROR_MESSAGE_ATTRIBUTE_NAME = "errormsg";
	
	private HttpServletRequest request;
	
	public SurveyAnsweringSessionManager(HttpServletRequest request){
		this.request = request;
	}
	
	/**
	 * Checks if this session contains a survey. This implies that this session is not null aswell.
	 * @return True if the session contains a survey.
	 */
	public boolean hasSurvey(){
		boolean hasAttribute;
		
		HttpSession session = request.getSession();
		
		if(session != null && session.getAttribute(SURVEY_ATTRIBUTE_NAME) != null){
			hasAttribute = true;
		} else {
			hasAttribute = false;
		}
		
		return hasAttribute;
	}
	
	/**
	 * Gets the SurveyAnsweringModel from this session, if there is any.
	 * If there are no survey stored in this session this returns null.
	 * @return The SurveyAnsweringModel if it exists.
	 */
	public SurveyAnsweringModel getSurveyAnsweringModel(){
		
		HttpSession session = request.getSession();
		SurveyAnsweringModel surveyAnsweringModel = (SurveyAnsweringModel) session.getAttribute(SURVEY_ATTRIBUTE_NAME);
		
		return surveyAnsweringModel;
	}
	
	/**
	 * Stores the SurveyAnsweringModel in this session.
	 * @param surveyAnsweringModel The SurveyAnsweringModel.
	 */
	public void setSurveyAnsweringModel(SurveyAnsweringModel surveyAnsweringModel){
		HttpSession session = request.getSession();
		
		session.setAttribute(SURVEY_ATTRIBUTE_NAME, surveyAnsweringModel);
	}
	
	/**
	 * Stores an error message in this session.
	 * @param errorMessage The error message.
	 */
	public void setErrorMessage(String errorMessage){
		HttpSession session = request.getSession();
		
		session.setAttribute(ERROR_MESSAGE_ATTRIBUTE_NAME, errorMessage);
	}
	
	/**
	 * Checks if this session is not null. Checks if this session exists.
	 * @return True if this session is not null;
	 */
	public boolean isNotNullSession(){
		HttpSession session = request.getSession();
		
		return session != null;
	}
	
	public void submitAnswerInAnsweredQuestion(SurveyQuestionModel answeredQuestion){
		
		if(answeredQuestion.getQuestionType().canHaveMultipleAnswers()){
			String[] answers = request.getParameterValues("answer");
			answeredQuestion.submitAnswer(answers);
			
		} else {
			String answer = request.getParameter("answer");
			answeredQuestion.submitAnswer(answer);
		}
	}
	
	
}
