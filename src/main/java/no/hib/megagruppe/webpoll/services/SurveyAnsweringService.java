package no.hib.megagruppe.webpoll.services;

import no.hib.megagruppe.webpoll.models.AnswerModel;
import no.hib.megagruppe.webpoll.models.QuestionModel;

public interface SurveyAnsweringService {
    boolean isValidSurvey(String code);
    SurveyAnsweringContext startSurveyAnswering(String code);
    QuestionModel getNextQuestion(SurveyAnsweringContext context);
    void answerCurrentQuestion(SurveyAnsweringContext context, AnswerModel answer);
    void commitSurveyAnswering(SurveyAnsweringContext context);
}
