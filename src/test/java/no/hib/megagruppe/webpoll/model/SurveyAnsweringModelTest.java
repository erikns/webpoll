package no.hib.megagruppe.webpoll.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import no.hib.megagruppe.webpoll.data.InMemorySurveyRepository;
import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.models.answering.SurveyAnsweringModel;
import no.hib.megagruppe.webpoll.models.answering.SurveyQuestionModel;

public class SurveyAnsweringModelTest {

	String question1;
	String question2;
	
	SurveyAnsweringModel sam; // sam = SurveyAnsweringModel

	@Before
	public void setup() {
		
		InMemorySurveyRepository inMemorySurveyRepo = new InMemorySurveyRepository();
		SurveyEntity surveyEntity = inMemorySurveyRepo.findByCode("testabc");
		sam = new SurveyAnsweringModel(surveyEntity);
		
		question1 = inMemorySurveyRepo.getQuestion1Text();
		question2 = inMemorySurveyRepo.getQuestion2Text();
		
	}
	
	@Test
	public void SAMWithQuestionsHasNextQuestion(){
		assertTrue(sam.hasNextQuestion());
	}
	
	@Test
	public void SAMHasTwoQuestions(){
		assertTrue(sam.currentQuestion() != null);
		assertTrue(sam.nextQuestion() != null);
		assertFalse(sam.hasNextQuestion());
	}
	
	@Test
	public void QuestionsAreInOrder(){
		assertEquals(sam.currentQuestion().getText(), question1);
		assertEquals(sam.nextQuestion().getText(), question2);
	}
	
	@Test
	public void IteratorWorks(){
		assertEquals(sam.currentQuestion().getText(), question1);
		assertEquals(sam.nextQuestion().getText(), question2);
		for(SurveyQuestionModel question : sam){
			assertTrue(question.getText().equals(question1) || question.getText().equals(question2));
		}
	}

}






