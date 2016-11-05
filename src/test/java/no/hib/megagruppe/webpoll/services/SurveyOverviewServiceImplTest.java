package no.hib.megagruppe.webpoll.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import no.hib.megagruppe.webpoll.data.SurveyRepository;
import no.hib.megagruppe.webpoll.data.UserRepository;
import no.hib.megagruppe.webpoll.entities.AnswerEntity;
import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.entities.ResponseEntity;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.fakes.FakeRepositoryFactory;
import no.hib.megagruppe.webpoll.fakes.TestSecurityAdapter;
import no.hib.megagruppe.webpoll.fakes.TestSurveyRepository;
import no.hib.megagruppe.webpoll.fakes.TestUserRepositoryOneUser;
import no.hib.megagruppe.webpoll.models.lecturer.QuestionAnswerOverviewModel;
import no.hib.megagruppe.webpoll.models.lecturer.QuestionCreationModel;
import no.hib.megagruppe.webpoll.models.lecturer.QuestionOverviewModel;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyOverviewModel;

public class SurveyOverviewServiceImplTest {
	
	private SurveyRepository surveyRepository;
	private UserRepository userRepository;
	private SurveyEntity survey;
	private UserEntity user;
	private SurveyOverviewService lecturerService;
	private SurveyCreationService creationService;
	private SurveyCreationModel surveyCreation;
	
	@Before
	public void setup() {
		userRepository = new TestUserRepositoryOneUser();
		user = buildUser();
		userRepository.add(user);
		surveyRepository = new TestSurveyRepository();
		survey = buildSurvey();
		surveyRepository.add(survey);
		creationService = buildCreationService(surveyRepository);
		lecturerService = buildLecturerService(surveyRepository);
	}
	
	@Test
	public void cloneSurveyMakesNewIdenticalSurvey(){
		
		assertTrue(lecturerService.cloneSurvey(0));
		assertFalse(surveyRepository.findAll().get(1).getActive());
		assertEquals(surveyRepository.findAll().get(0).getName() + "_2", surveyRepository.findAll().get(1).getName());
		assertEquals(surveyRepository.findAll().get(0).getOwner(),surveyRepository.findAll().get(1).getOwner());
		
		compareQuestionsFromTwoSurveys(surveyRepository.findAll().get(0),surveyRepository.findAll().get(1));
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
	public void surveyOverviewModelIsSameAsSurveyEntity(){
		surveyCreation = buildSurveyCreationModel(survey);
		creationService.commitSurveyCreation(surveyCreation);
		
		SurveyOverviewModel surveyModel = lecturerService.getSurveyOverviewModel(survey.getId());
		assertEquals(surveyModel.getName(), survey.getName());
		assertEquals(surveyModel.getDateCreated(), survey.getDateCreated());
		assertEquals(surveyModel.getDeadline(), survey.getDeadline());
	}
	
	@Test
	public void surveyOverviewModelHasSameQuestionsAsSurveyEntity(){
		surveyCreation = buildSurveyCreationModel(survey);
		creationService.commitSurveyCreation(surveyCreation);
		buildResponses(survey);
		
		SurveyOverviewModel surveyModel = lecturerService.getSurveyOverviewModel(survey.getId());
		List<QuestionOverviewModel> questionModels = surveyModel.getResultData();
		
		assertTrue(questionModels.size() == 2);
		assertTrue(questionModels.get(0).getQuestionText().equals(survey.getQuestions().get(0).getText()));
		assertTrue(questionModels.get(1).getQuestionText().equals(survey.getQuestions().get(1).getText()));
	}
	
	@Test
	public void questionAnswerOverviewModelDataIsInDescendingOrder(){
		surveyCreation = buildSurveyCreationModel(survey);
		creationService.commitSurveyCreation(surveyCreation);
		buildResponses(survey);
		
		SurveyOverviewModel surveyModel = lecturerService.getSurveyOverviewModel(survey.getId());
		
		List<QuestionOverviewModel> questionModels = surveyModel.getResultData();
		for(QuestionOverviewModel question : questionModels){
			List<QuestionAnswerOverviewModel> answers = question.getAnswers();
			int prevAnswerFrequency = answers.get(0).getFrequency();
			for(int i = 1; i < answers.size(); i++){
				assertTrue(answers.get(i).getFrequency()<=prevAnswerFrequency);
				prevAnswerFrequency = answers.get(i).getFrequency();
			}
		}
		
	}
	
	@Test
	public void questionAnswerOverviewModelHasCorrectData(){
		surveyCreation = buildSurveyCreationModel(survey);
		creationService.commitSurveyCreation(surveyCreation);
		buildResponses(survey);
		
		SurveyOverviewModel surveyModel = lecturerService.getSurveyOverviewModel(survey.getId());
		
		List<QuestionOverviewModel> questionModels = surveyModel.getResultData();
		QuestionOverviewModel question1 = questionModels.get(0);
		QuestionOverviewModel question2 = questionModels.get(1);
		
		assertTrue(question1.getAnswers().get(0).getFrequency() == 2); // To ganger svart det første alternativet.
		assertTrue(question2.getAnswers().get(0).getFrequency() == 9); // 3 ganger 3 skrevet "ja".
		assertTrue(question2.getAnswers().get(1).getFrequency() == 5); // 2 ganger 2 + 1 skrevet "tekst".
		assertTrue(question2.getAnswers().get(2).getFrequency() == 4); // 3 ganger 1 + 1 skrevet "abc2".
		assertTrue(question2.getAnswers().get(5).getPercentage() == 10); // 3 svart av 30 er 10%.
		
	}

	private SurveyOverviewService buildLecturerService(SurveyRepository surveyRepository) {
		return new SurveyOverviewServiceImpl(new FakeRepositoryFactory(null, surveyRepository, null), creationService);
	}
	
	private SurveyCreationService buildCreationService(SurveyRepository surveyRepository) {
		return new SurveyCreationServiceImpl(new FakeRepositoryFactory(userRepository, surveyRepository, null), new TestSecurityAdapter());
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
        survey.setCode("abc");

        List<QuestionEntity> questions = new ArrayList<>();
        question1.setSurvey(survey);
        question2.setSurvey(survey);
        questions.add(question1);
        questions.add(question2);

        survey.setQuestions(questions);
        
        return survey;
	}

	private void buildResponses(SurveyEntity survey){
		List<AnswerEntity> answers1 = new ArrayList<>();
		for(QuestionEntity question : survey.getQuestions()){
			AnswerEntity answer;
			if(question.getType().isMultipleChoice()){
				answer = new AnswerEntity(question,question.getOptions().get(0));
			} else {
				answer = new AnswerEntity(question,"Tekst svar ja mann, ja ja yes! 123 abc2");
			}
			answers1.add(answer);
		}
		ResponseEntity response1 = new ResponseEntity(survey, answers1);
		
		List<AnswerEntity> answers2 = new ArrayList<>();
		for(QuestionEntity question : survey.getQuestions()){
			AnswerEntity answer;
			if(question.getType().isMultipleChoice()){
				answer = new AnswerEntity(question,question.getOptions().get(1));
			} else {
				answer = new AnswerEntity(question,"Tekst svar ja mann ja tekst ja yes! 123 abc2");
			}
			answers2.add(answer);
		}
		ResponseEntity response2 = new ResponseEntity(survey, answers2);
		
		List<AnswerEntity> answers3 = new ArrayList<>();
		for(QuestionEntity question : survey.getQuestions()){
			AnswerEntity answer;
			if(question.getType().isMultipleChoice()){
				answer = new AnswerEntity(question,question.getOptions().get(1));
			} else {
				answer = new AnswerEntity(question,"Tekst svar ja mann ja tekst ja yes! 123 abc2 abc2");
			}
			answers3.add(answer);
		}
		ResponseEntity response3 = new ResponseEntity(survey, answers3);
		
		List<ResponseEntity> responses = new ArrayList<>();
		responses.add(response1);
		responses.add(response2);
		responses.add(response3);
		
		survey.setResponses(responses);
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
