package no.hib.megagruppe.webpoll.dummy;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import no.hib.megagruppe.webpoll.data.SurveyRepository;
import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.fakes.FakeRepositoryFactory;
import no.hib.megagruppe.webpoll.fakes.TestSurveyRepository;
import no.hib.megagruppe.webpoll.models.SurveyAnsweringModel;
import no.hib.megagruppe.webpoll.services.SurveyAnsweringService;
import no.hib.megagruppe.webpoll.services.SurveyAnsweringServiceImpl;

public class SurveyAnsweringServiceDummyFactory {

	private SurveyAnsweringService service;
	private SurveyRepository surveyRepository;
	private SurveyEntity survey;
	
	private String surveycode = "abc";
	
	public SurveyAnsweringServiceDummyFactory(){
		surveyRepository = new TestSurveyRepository();
		survey = buildSurvey();
		surveyRepository.add(survey);
		service = buildService(surveyRepository);
	}
	
	/**
	 * A simple survey consisting of two questions.
	 * 	- Har du noen gang programmert javaEE? (MULTIPLE_CHOICE_RADIO)
	 * 		- Ja
	 * 		- Nei
	 * 
	 * 	- Hva synes du om WebPoll? (FREE_TEXT)
	 * 
	 * @return A survey consisting of two questions.
	 */
	private SurveyEntity buildSurvey(){
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
        survey.setDate(new Date(System.currentTimeMillis()));
        survey.setDeadline(new Date(System.currentTimeMillis() + 3600000));
        survey.setOwner(user);
        survey.setActive(true);
        survey.setCode(surveycode);

        List<QuestionEntity> questions = new ArrayList<>();
        question1.setSurvey(survey);
        question2.setSurvey(survey);
        questions.add(question1);
        questions.add(question2);

        survey.setQuestions(questions);
        
        return survey;
	}
	
	private SurveyAnsweringService buildService(SurveyRepository surveyRepository) {
		return new SurveyAnsweringServiceImpl(new FakeRepositoryFactory(null, surveyRepository));
	}
	
	/**
	 * Starts the survey which returns a SurveyAnsweringModel-object.
	 * The SAM (SurveyAnsweringModel) can act as an iterator with methods as:
	 * 	+ hasNextQuestion() : boolean
	 * 	+ getNextQuestion() : SurveyQuestionModel
	 * 
	 * To 
	 * 
	 * @return the SurveyAnsweringModel-object.
	 */
	public SurveyAnsweringModel startSurvey(){
		return service.startSurveyAnswering(surveycode);
	}
	
}
