package no.hib.megagruppe.webpoll.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import no.hib.megagruppe.webpoll.data.SurveyRepository;
import no.hib.megagruppe.webpoll.data.UserRepository;
import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.fakes.FakeRepositoryFactory;
import no.hib.megagruppe.webpoll.fakes.FakeSurveyCodeGenerator;
import no.hib.megagruppe.webpoll.fakes.TestSecurityAdapter;
import no.hib.megagruppe.webpoll.fakes.TestSurveyRepository;
import no.hib.megagruppe.webpoll.fakes.TestUserRepositoryOneUser;
import no.hib.megagruppe.webpoll.models.lecturer.QuestionCreationModel;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;

public class SurveyCreationServiceImplTest {
	
	private SurveyRepository surveyRepository;
	private UserRepository userRepository;
	private SurveyEntity survey;
	private UserEntity user;
	private SurveyCreationService creationService;
	
	@Before
	public void setup() {
		userRepository = new TestUserRepositoryOneUser();
		user = buildUser();
		userRepository.add(user);
		surveyRepository = new TestSurveyRepository();
		survey = buildSurvey();
		surveyRepository.add(survey);
		creationService = buildCreationService(surveyRepository);
	}
	
	@Test
	public void commitSurveyCommitsNewSurvey() {
		
		creationService.commitSurveyCreation(buildSurveyCreationModel(survey));
		
		assertFalse(surveyRepository.findAll().get(1).getActive());
		assertEquals("Test", surveyRepository.findAll().get(1).getName());
		assertEquals(surveyRepository.findAll().get(0).getOwner(), surveyRepository.findAll().get(1).getOwner());
		
		compareQuestionsFromTwoSurveys(surveyRepository.findAll().get(0),surveyRepository.findAll().get(1));
	}
	
	private void compareQuestionsFromTwoSurveys(SurveyEntity oldSurvey, SurveyEntity newSurvey) {
		
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
	
	@Test
	public void surveyCodeIsUnique(){
		SurveyCreationModel s1 = buildSurveyCreationModel(survey);
		SurveyEntity s2 = buildSurvey();
		s2.setCode("fox3");
		surveyRepository.add(s2);
		creationService.commitSurveyCreation(s1);
		List<SurveyEntity> surveys = surveyRepository.findAll();
		assertFalse(surveys.get(0).getCode().equals(surveys.get(2).getCode()));
		assertFalse(surveys.get(1).getCode().equals(surveys.get(2).getCode()));
		assertEquals(surveys.get(2).getCode(), "fox4");
	}
	
	private SurveyCreationService buildCreationService(SurveyRepository surveyRepository) {
		return new SurveyCreationServiceImpl(new FakeRepositoryFactory(userRepository, surveyRepository, null), new TestSecurityAdapter(), new FakeSurveyCodeGenerator());
	}
	
	private UserEntity buildUser(){
		UserEntity user = new UserEntity();
        user.setEmail("test@testesen.no");
        user.setFirstName("Test");
        user.setLastName("Testesen");
        user.setId(0);
        
        return user;
	}
	
	private SurveyEntity buildSurvey(){
		SurveyEntity survey;

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
        survey.setOwner(buildUser());
        survey.setActive(true);
        survey.setCode("fox2");

        List<QuestionEntity> questions = new ArrayList<>();
        question1.setSurvey(survey);
        question2.setSurvey(survey);
        questions.add(question1);
        questions.add(question2);

        survey.setQuestions(questions);
        
        return survey;
	}

	private SurveyCreationModel buildSurveyCreationModel(SurveyEntity survey) {
		SurveyCreationModel surveyCreation = new SurveyCreationModel("Test", survey.getOwner().toString());
		copyQuestions(survey,surveyCreation);
		
		return surveyCreation;
	}
	
	private void copyQuestions(SurveyEntity survey, SurveyCreationModel surveyCreation) {
		for (QuestionEntity question : survey.getQuestions()) {
			
			QuestionCreationModel questionCreation = new QuestionCreationModel(question);

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
