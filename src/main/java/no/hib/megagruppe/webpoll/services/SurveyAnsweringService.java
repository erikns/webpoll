package no.hib.megagruppe.webpoll.services;

import no.hib.megagruppe.webpoll.models.AnswerModel;
import no.hib.megagruppe.webpoll.models.QuestionModel;

public interface SurveyAnsweringService {
    boolean isValidSurvey(String code);
    SurveyResponseContext startSurveyAnswering(String code);
    QuestionModel getNextQuestion(SurveyResponseContext context);
    void answerCurrentQuestion(SurveyResponseContext context, AnswerModel answer);
    void commitSurveyAnswering(SurveyResponseContext context);
}
