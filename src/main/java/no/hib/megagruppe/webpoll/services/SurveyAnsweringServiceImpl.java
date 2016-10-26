package no.hib.megagruppe.webpoll.services;

import no.hib.megagruppe.webpoll.data.RepositoryFactory;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.models.AnswerModel;
import no.hib.megagruppe.webpoll.models.QuestionModel;
import no.hib.megagruppe.webpoll.models.SurveyAnsweringModel;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SurveyAnsweringServiceImpl implements SurveyAnsweringService {
    private final RepositoryFactory repositoryFactory;

    @Inject
    public SurveyAnsweringServiceImpl(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @Override
    public boolean isValidSurvey(String code) {
        SurveyEntity survey = repositoryFactory.getSurveyRepository().findByCode(code);
        return survey != null && survey.getActive();
    }

    @Override
    public SurveyAnsweringModel startSurveyAnswering(String code) {
        return null;
    }

    @Override
    public void commitSurveyAnswering(SurveyAnsweringModel answeringModel) {

    }
}
