package no.hib.megagruppe.webpoll.model;

import no.hib.megagruppe.webpoll.models.answering.SurveyAnsweringModel;
import no.hib.megagruppe.webpoll.models.answering.SurveyQuestionModel;
import no.hib.megagruppe.webpoll.testutil.SurveyAnsweringModelBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SurveyAnsweringModelTest {
	private SurveyAnsweringModel sam;

	@Before
	public void setup() {
        sam = SurveyAnsweringModelBuilder.build();
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
		assertEquals(sam.currentQuestion().getText(), "Spørsmål 1?");
		assertEquals(sam.nextQuestion().getText(), "Spørsmål 2?");
	}
	
	@Test
	public void IteratorWorks(){
		assertEquals(sam.currentQuestion().getText(), "Spørsmål 1?");
		assertEquals(sam.nextQuestion().getText(), "Spørsmål 2?");

		for(SurveyQuestionModel question : sam){
			assertTrue(question.getText().equals("Spørsmål 1?") || question.getText().equals("Spørsmål 2?"));
		}
	}
}






