package no.hib.megagruppe.webpoll.services;

import no.hib.megagruppe.webpoll.data.SurveyRepository;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.models.AnswerModel;
import no.hib.megagruppe.webpoll.models.QuestionModel;

import javax.inject.Inject;

public class SurveyAnsweringServiceImpl implements SurveyAnsweringService {
    private final SurveyRepository surveyRepository;

    @Inject
    public SurveyAnsweringServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public boolean isValidSurvey(String code) {
        SurveyEntity survey = surveyRepository.findByCode(code);
        return survey != null && survey.getActive();
    }

    @Override
    public SurveyResponseContext startSurveyAnswering(String code) {
        return null;
    }

    @Override
    public QuestionModel getNextQuestion(SurveyResponseContext context) {
        return null;
    }

    @Override
    public void answerCurrentQuestion(SurveyResponseContext context, AnswerModel answer) {

    }

    @Override
    public void commitSurveyAnswering(SurveyResponseContext context) {

    }
}
