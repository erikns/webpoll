package no.hib.megagruppe.webpoll.util.sessionmanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;

/**
 * A helper-class for sessions when creating a new survey.
 * @author Magnus
 *
 */
public class CreateSurveySessionManager extends SessionManager{

	private final String SURVEY_MODEL = "surveymodel";
	
	
	
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
	
	/**
	 * Checks if there is an instantiated SurveyCreationModel in this session.
	 * @return True, if there is an instantiated SurveyCreationModel in this session.
	 */
	public boolean hasSurveyModel(){
		HttpSession session = request.getSession();
		SurveyCreationModel surveyModel = (SurveyCreationModel)session.getAttribute(SURVEY_MODEL);
		boolean instantiated = surveyModel != null && surveyModel.isInstantiated();
		
		return instantiated;
	}
	
	
	
	

}
