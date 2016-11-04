package no.hib.megagruppe.webpoll.util.sessionmanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.hib.megagruppe.webpoll.entities.QuestionEntity.QuestionType;
import no.hib.megagruppe.webpoll.models.lecturer.QuestionCreationModel;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;

/**
 * A helper-class for sessions when creating a new survey.
 * @author Magnus
 *
 */
public class CreateSurveySessionManager extends SessionManager{

	private final String SURVEY_MODEL = "surveymodel";
	private final String QUESTION_CREATION_MODEL = "questionmodel";
	
	
	
	public CreateSurveySessionManager(HttpServletRequest request) {
		super(request);
	}
	
	
	/**
	 * Sets the SurveyCreationModel for this session.
	 * @param surveyModel The SurveyCreationModel for this session.
	 */
	public void setSurveyCrationModel(SurveyCreationModel surveyModel){
		HttpSession session = request.getSession();
		session.setAttribute(SURVEY_MODEL, surveyModel);
	}
	
	/**
	 * Returns the SurveyModel stored in this session.
	 * @return The SurveyModel stored in this session.
	 */
	public SurveyCreationModel getSurveyModel(){
		HttpSession session = request.getSession();
		
		return (SurveyCreationModel)session.getAttribute(SURVEY_MODEL);
	}
	
	
	
	

}
