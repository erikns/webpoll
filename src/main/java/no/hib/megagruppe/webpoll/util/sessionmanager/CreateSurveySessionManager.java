package no.hib.megagruppe.webpoll.util.sessionmanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;

/**
 * A helper-class for sessions when creating a new survey.
 * @author Magnus
 *
 */
public class CreateSurveySessionManager extends SessionManager{

	
	//SurveyCreationModel surveyModel;
	//QuestionCreationModel questionCreationModel;
	
	private final String SURVEY_MODEL = "surveymodel";
	private final String QUESTION_CREATION_MODEL = "questionmodel";
	
	
	
	public CreateSurveySessionManager(HttpServletRequest request) {
		super(request);
	}
	
	
	/**
	 * Creates and stores a new SurveyModel in the session.
	 * @param owner The owner of the survey.
	 */
	public void instantiateNewSurveyModel(UserEntity owner){
		HttpSession session = request.getSession();
		
		SurveyCreationModel surveyModel = new SurveyCreationModel(owner);
		surveyModel.setName("something unique"); // TODO
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
