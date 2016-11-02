package no.hib.megagruppe.webpoll.services;

import junit.framework.TestCase.*;
import no.hib.megagruppe.webpoll.data.ResponseRepository;
import no.hib.megagruppe.webpoll.data.SurveyRepository;
import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.fakes.FakeRepositoryFactory;
import no.hib.megagruppe.webpoll.fakes.TestSurveyRepository;
import no.hib.megagruppe.webpoll.models.lecturer.QuestionCreationModel;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SurveyLecturerServiceImplTest {
	
	private SurveyRepository surveyRepository;
	private SurveyEntity survey;
	private SurveyLecturerService service;
	private SurveyCreationModel surveyCreation;
	
	@Before
	public void setup() {
		surveyRepository = new TestSurveyRepository();
		survey = buildSurvey();
		surveyRepository.add(survey);
		service = buildService(surveyRepository);
	}
	
	@Test
	public void getSurveyOverviewGivesCorrectSurvey() {
		
	}
	
	@Test
	public void cloneSurveyMakesNewIdenticalSurvey(){
		
		assertTrue(service.cloneSurvey(0, "Test"));
		assertFalse(surveyRepository.findAll().get(1).getActive());
		assertEquals("Test", surveyRepository.findAll().get(1).getName());
		assertEquals(surveyRepository.findAll().get(0).getOwner(),surveyRepository.findAll().get(1).getOwner());
		
		compairQuestionsFromTwoSurveys(surveyRepository.findAll().get(0),surveyRepository.findAll().get(1));
	}
	
	@Test
	public void commitSurveyCommitsNewSurvey() {
		
		service.commitNewSurvey(buildSurveyCreationModel(survey));
		
		assertFalse(surveyRepository.findAll().get(1).getActive());
		assertEquals("Test", surveyRepository.findAll().get(1).getName());
		assertEquals(surveyRepository.findAll().get(0).getOwner(),surveyRepository.findAll().get(1).getOwner());
		
		compairQuestionsFromTwoSurveys(surveyRepository.findAll().get(0),surveyRepository.findAll().get(1));
		
	}
	
	private static void compairQuestionsFromTwoSurveys(SurveyEntity oldSurvey, SurveyEntity newSurvey) {
		
		List<QuestionEntity> questionsOldSurvey = oldSurvey.getQuestions();
		List<QuestionEntity> questionsNewSurvey = newSurvey.getQuestions();
		for (int i = 0; i < questionsOldSurvey.size(); i++) {
			assertEquals(questionsOldSurvey.get(i).getText(), questionsNewSurvey.get(i).getText());
			assertEquals(questionsOldSurvey.get(i).getType(), questionsNewSurvey.get(i).getType());
			if (questionsOldSurvey.get(i).getType().isMultipleChoice()) {
				for (int j = 0; j < questionsOldSurvey.get(i).getOptions().size(); j++) {
					assertEquals(questionsOldSurvey.get(i).getOptions().get(j).getText(),questionsNewSurvey.get(i).getOptions().get(j).getText());
				}
			}
		}
	}

	private static SurveyLecturerService buildService(SurveyRepository surveyRepository) {
		return new SurveyLecturerServiceImpl(new FakeRepositoryFactory(null, surveyRepository, null));
	}
	
	private static SurveyEntity buildSurvey(){
		SurveyEntity survey;
		
		UserEntity user = new UserEntity();
        user.setEmail("test@testesen.no");
        user.setFirstName("Test");
        user.setLastName("Testesen");
        user.setId(1);

        //////
        OptionEntity optionA = new OptionEntity();
        optionA.setId(1);
        optionA.setText("Ja");

        OptionEntity optionB = new OptionEntity();
        optionB.setId(2);
        optionB.setText("Nei");

        QuestionEntity question1 = new QuestionEntity();
        question1.setId(1);
        question1.setText("Har du noen gang programmert JavaEE?");
        question1.setType(QuestionEntity.QuestionType.MULTIPLE_CHOICE_RADIO);

        List<OptionEntity> options = new ArrayList<>();
        options.add(optionA);
        options.add(optionB);
        question1.setOptions(options);

        optionA.setQuestion(question1);
        optionB.setQuestion(question1);
        //////
        //////
        QuestionEntity question2 = new QuestionEntity();
        question2.setId(2);
        question2.setText("Hva synes du om WebPoll?");
        question2.setType(QuestionEntity.QuestionType.FREE_TEXT);
        //////

        survey = new SurveyEntity();
        survey.setId(1);
        survey.setName("Testundersøkelse");
        survey.setDateCreated(new Timestamp(System.currentTimeMillis() - 3600));
        survey.setDeadline(new Timestamp(System.currentTimeMillis() + 36000));
        survey.setOwner(user);
        survey.setActive(true);
        survey.setCode("abc");

        List<QuestionEntity> questions = new ArrayList<>();
        question1.setSurvey(survey);
        question2.setSurvey(survey);
        questions.add(question1);
        questions.add(question2);

        survey.setQuestions(questions);
        
        return survey;
	}

	private static SurveyCreationModel buildSurveyCreationModel(SurveyEntity survey) {
		SurveyCreationModel surveyCreation = new SurveyCreationModel(survey.getOwner());
		surveyCreation.setName("Test");
		surveyCreation.setOwner(survey.getOwner());
		copyQuestions(survey,surveyCreation);
		
		return surveyCreation;
	}
	
	private static void copyQuestions(SurveyEntity survey, SurveyCreationModel surveyCreation) {
		for (QuestionEntity question : survey.getQuestions()) {
			
			QuestionCreationModel questionCreation = new QuestionCreationModel(question.getType(), question.getText());

			if (question.getType().isMultipleChoice()) {
				List<OptionEntity> optionEntities = question.getOptions();
				String[] options = new String[optionEntities.size()];
				
				for (int i = 0; i < optionEntities.size(); i++) {
					options[i] = optionEntities.get(i).getText();
				}
				
				questionCreation.setOptions(options);
			}
			
			surveyCreation.addQuestionCreationModel(questionCreation);
		}
	}
	
}
