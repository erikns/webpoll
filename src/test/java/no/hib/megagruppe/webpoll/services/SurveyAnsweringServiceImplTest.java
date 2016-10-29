package no.hib.megagruppe.webpoll.services;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import no.hib.megagruppe.webpoll.data.SurveyRepository;
import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.fakes.FakeRepositoryFactory;
import no.hib.megagruppe.webpoll.fakes.TestSurveyRepository;
import no.hib.megagruppe.webpoll.models.SurveyAnsweringModel;

public class SurveyAnsweringServiceImplTest {

	private SurveyRepository surveyRepository;
	private SurveyEntity survey;
	private SurveyAnsweringService service;
	
	@Before
	public void setup() {
		surveyRepository = new TestSurveyRepository();
		survey = buildSurvey();
		surveyRepository.add(survey);
		service = buildService(surveyRepository);
	}
	
	@Test
	public void isValidSurveyReturnsFalseForNonExistantSurvey() {
		assertFalse(service.isValidSurvey("I do not exit"));
	}

	@Test
	public void isValidSurveyReturnsTrueForActiveSurvey() {
		survey.setActive(true);
		assertTrue(service.isValidSurvey("abc"));
	}

	@Test
	public void isValidSurveyReturnsFalseForInactiveSurvey() {
		survey.setActive(false);
		assertFalse(service.isValidSurvey("abc"));
	}
	
	@Test
	public void startingSurveyGivesCorrectSurveyAnsweringModel() {
		survey.setName("Favorite test");
		survey.setDate(new Timestamp(100000));
		survey.setDeadline(new Timestamp(120000));
		
		SurveyAnsweringModel sam = service.startSurveyAnswering("abc");
		assertEquals(survey.getName(), sam.getSurveyName());
		assertEquals(survey.getDate(), sam.getSurveyDate());
		assertEquals(survey.getDeadline(), sam.getSurveyDeadline());
	}
	
	@Test
	public void commitSurveyDoesNotCrash(){
		SurveyAnsweringModel sam = service.startSurveyAnswering("abc");
		
		service.commitSurveyAnswering(sam);
	}

	private static SurveyAnsweringService buildService(SurveyRepository surveyRepository) {
		return new SurveyAnsweringServiceImpl(new FakeRepositoryFactory(null, surveyRepository));
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
        survey.setDate(new Timestamp(System.currentTimeMillis() - 3600));
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

}
