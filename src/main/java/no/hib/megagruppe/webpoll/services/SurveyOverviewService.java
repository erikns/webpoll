package no.hib.megagruppe.webpoll.services;

import java.sql.Timestamp;
import java.util.List;

import no.hib.megagruppe.webpoll.models.lecturer.SurveyBasicInfoModel;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyOverviewModel;

/**
 * Interface to support the use cases of the lecturer editing / viewing surveys.
 * Note: this service assumes nothing about security, and expects the checking of
 * user credentials etc to be done before any service method is called.
 */
public interface SurveyOverviewService {
    // NOTES:
    // Create
    // Clone
    // See results
	
	/**
	 * Returns all the surveys belonging to the logged in user.
	 * @return All the lecturers surveys represented as a list of SurveryOverviewModels
	 */
    List<SurveyBasicInfoModel> getSurveyOverviews();
    
    /**
     * Clones the survey with the provided survey ID as a new survey.
     * @param surveyID The ID of the survey to be cloned
     * @return The new survey as a SurveyCreationModel.
     */
    SurveyCreationModel cloneSurvey(Integer surveyID);
    
    /**
     * Gets the overview model for the survey.
     * @param id The survey id.
     * @return The overview model for the survey with given id.
     */
    SurveyOverviewModel getSurveyOverviewModel(Integer id);

    /**
     * Activates the survey and sets the deadline.
     * @param deadline The deadline to be set.
     * @param surveyID The ID of the survey to be activated.
     */
    void activateSurvey(Timestamp deadline, Integer surveyID);
    
    /**
     * Checks if the survey is owned by the logged in user.
     * @return True if the survey is owned by the logged in user, false if not.
     */
    boolean ownedByUser(Integer surveyID);
}
