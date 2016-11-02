package no.hib.megagruppe.webpoll.services;

import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;

public interface SurveyCreationService {

	/**
	 * Creates a new SurveyCreationModel and sets the name and owner.
	 * @return The new SurveyCreationModel.
	 */
	SurveyCreationModel instantiateNewSurvey();
	
	/**
	 * Commit (finish) the creation of a new survey.
	 * @param surveyCreationModel The completed SurveyCreationModel, ready to be commited.
	 */
	void commitSurveyCreation(SurveyCreationModel surveyCreationModel);
	
}
