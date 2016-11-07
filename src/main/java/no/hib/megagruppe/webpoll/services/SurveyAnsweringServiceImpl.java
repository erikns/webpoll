package no.hib.megagruppe.webpoll.services;

import no.hib.megagruppe.webpoll.data.RepositoryFactory;
import no.hib.megagruppe.webpoll.entities.*;
import no.hib.megagruppe.webpoll.models.answering.SurveyAnsweringModel;
import no.hib.megagruppe.webpoll.models.answering.SurveyQuestionModel;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
    	SurveyEntity survey = repositoryFactory.getSurveyRepository().findByCode(code);
    	SurveyAnsweringModel surveyAnsweringModel = new SurveyAnsweringModel(survey);
        return surveyAnsweringModel;
    }

    @Override
    public void commitSurveyAnswering(SurveyAnsweringModel answeringModel) {
    	
    	SurveyEntity survey = repositoryFactory.getSurveyRepository().findByCode(answeringModel.getCode());
    	List<AnswerEntity> answers = new ArrayList<>();
    	SurveyQuestionModel[] surveyQuestions = answeringModel.getQuestions();	
		OptionEntity option;
		String freetext;
		QuestionEntity question;
		
		
    	for (int i = 0; i < surveyQuestions.length; i++) {
    		
    		option = null;
    		freetext = null;
    		question = survey.getQuestions().get(i);
    		
    		if (question.getType().isMultipleChoice()) {
    			for (String surveyAnswer : surveyQuestions[i].getAnswers()) {
    				option = findOption(question, surveyAnswer);
    				if (option != null) {
    					answers.add(new AnswerEntity(question, option));	
    				}
    			}
    		} else {
    			freetext = surveyQuestions[i].getAnswers()[0];		
        		answers.add(new AnswerEntity(question, freetext));
    		}
    	}
    	ResponseEntity response = new ResponseEntity(survey, answers);
    	repositoryFactory.getResponseRepository().add(response);
    }
    
    private OptionEntity findOption(QuestionEntity question, String surveyAnswer) {
    	OptionEntity option = null;
    	
    	try {
    		option = question.getOptions().get(Integer.parseInt(surveyAnswer));
    	} catch(NumberFormatException e) {}
    	
		return option;
    }
}
