package no.hib.megagruppe.webpoll.services;

import no.hib.megagruppe.webpoll.models.AnswerModel;
import no.hib.megagruppe.webpoll.models.QuestionModel;

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
    SurveyResponseContext startSurveyAnswering(String code);

    /**
     * Get the next question in the survey represented by the response context
     * @param context The response context
     * @return The next question
     */
    QuestionModel getNextQuestion(SurveyResponseContext context);

    /**
     * Answer the current question in the context
     * @param context The response context
     * @param answer The answer
     */
    void answerCurrentQuestion(SurveyResponseContext context, AnswerModel answer);

    /**
     * Commit (finish) the answering of a survey
     * @param context The response context
     */
    void commitSurveyAnswering(SurveyResponseContext context);
}
