package no.hib.megagruppe.webpoll.services;

import no.hib.megagruppe.webpoll.models.answering.SurveyAnsweringModel;

/**
 * Service interface to support use cases concerning the answering of surveys by
 * students.
 */
public interface SurveyAnsweringService {
    /**
     * Checks if there is an active survey with the given code
     * @param code The code to check
     * @return True if there is a valid and active survey, false otherwise
     */
    boolean isValidSurvey(String code);

    /**
     * Start a survey answering session.
     * @param code The code for the survey to start answering
     * @return Context representing the survey answering session
     */
    SurveyAnsweringModel startSurveyAnswering(String code);

    /**
     * Commit (finish) the answering of a survey
     * @param answeringModel The survey answering model
     */
    void commitSurveyAnswering(SurveyAnsweringModel answeringModel);
}
