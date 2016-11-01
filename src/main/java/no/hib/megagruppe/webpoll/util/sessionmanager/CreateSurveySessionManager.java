package no.hib.megagruppe.webpoll.util.sessionmanager;

import javax.servlet.http.HttpServletRequest;

import no.hib.megagruppe.webpoll.models.lecturer.QuestionCreationModel;
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
	
	

}
