package no.hib.megagruppe.webpoll.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.models.SurveyAnsweringModel;

public class SurveyAnsweringModelTest {

	private SurveyEntity survey;
	QuestionEntity question1;
	QuestionEntity question2;
	
	SurveyAnsweringModel sam; // sam = SurveyAnsweringModel

	@Before
	public void setup() {
		
		UserEntity user = new UserEntity();
        user.setEmail("test@testesen.no");
        user.setFirstName("Test");
        user.setLastName("Testesen");
        user.setId(1);

		OptionEntity option1 = new OptionEntity();
		option1.setId(1);
		option1.setText("Ja");
		OptionEntity option2 = new OptionEntity();
		option2.setId(2);
		option2.setText("Nei");

		question1 = new QuestionEntity();
		question1.setId(1);
		question1.setText("Har du noen gang programmert JavaEE?");
		question1.setType(QuestionEntity.QuestionType.MULTIPLE_CHOICE_RADIO);

		List<OptionEntity> options = new ArrayList<>();
		options.add(option1);
		options.add(option2);
		question1.setOptions(options);
		option1.setQuestion(question1);
		option2.setQuestion(question1);
		//////
		//////
		question2 = new QuestionEntity();
		question2.setId(2);
		question2.setText("Hva synes du om WebPoll?");
		question2.setType(QuestionEntity.QuestionType.FREE_TEXT);
		//////

		survey = new SurveyEntity();
		survey.setId(1);
		survey.setName("Testundersøkelse");
		survey.setDate(new Time(System.currentTimeMillis() - 3600));
		survey.setDeadline(new Time(System.currentTimeMillis() + 36000));

		List<QuestionEntity> questions = new ArrayList<>();
		question1.setSurvey(survey);
		question2.setSurvey(survey);
		questions.add(question1);
		questions.add(question2);

		survey.setQuestions(questions);
		survey.setOwner(user);
		
		sam = new SurveyAnsweringModel(survey.getQuestions(), survey.getName(), survey.getDate(), survey.getDeadline(), survey.getOwner().toString());
	}
	
	@Test
	public void SAMWithQuestionsHasNextQuestion(){
		assertTrue(sam.hasNextQuestion());
	}
	
	@Test
	public void SAMHasTwoQuestions(){
		assertTrue(sam.hasNextQuestion());
		sam.getNextQuestion();
		assertTrue(sam.hasNextQuestion());
		sam.getNextQuestion();
		assertFalse(sam.hasNextQuestion());
	}
	
	@Test
	public void QuestionsAreInOrder(){
		assertEquals(sam.getNextQuestion().getText(), question1.getText());
		assertEquals(sam.getNextQuestion().getText(), question2.getText());
	}
	
	@Test
	public void ResetResets(){
		assertEquals(sam.getNextQuestion().getText(), question1.getText());
		assertEquals(sam.getNextQuestion().getText(), question2.getText());
		sam.reset();
		assertEquals(sam.getNextQuestion().getText(), question1.getText());
		assertEquals(sam.getNextQuestion().getText(), question2.getText());
	}
	
	@Test
	public void TimeRemainingReturnsFormattedString(){
		assertEquals(sam.getTimeRemaining(), "0d, 0t, 00m, 39s");
	}
}






