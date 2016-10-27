package no.hib.megagruppe.webpoll.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity.QuestionType;
import no.hib.megagruppe.webpoll.models.SurveyQuestionModel;

public class SurveyQuestionModelTest {

	/*
	 * Is of correct type
	 * Submitting a single answer stores it in an array of length one.
	 * Submitting two answers stores them in an array of lenght two.
	 * Submitting a null-answer creates an empty answer.
	 * Submitting a null-array of answers creates an empty array of answers.
	 * Contains correct options
	 * Options in correct order
	 * 
	 */

	private SurveyQuestionModel question;
	private List<OptionEntity> options;

	@Before
	public void buildOptions() {
		options = new ArrayList<OptionEntity>();
		
		for (int i = 0; i < 10; i++) {
			int j = i * 10;

			OptionEntity option = new OptionEntity();
			option.setId(i);
			String optionText = j + " - " + (j + 9);
			option.setText(optionText);

			options.add(option);
		}
	}

	@After
	public void cleanup() {
		question = null;
		options = null;
	}

	private void buildRadioQuestion() {
		question = new SurveyQuestionModel("Hvor gammel er du?", QuestionType.MULTIPLE_CHOICE_RADIO, options);
	}

	private void buildCheckboxQuestion() {
		question = new SurveyQuestionModel("Hva er dine favorittintervaller?", QuestionType.MULTIPLE_CHOICE_CHECKBOX, options);
	}

	private void buildFreetextQuestion() {
		question = new SurveyQuestionModel("Hva heter du?", QuestionType.FREE_TEXT, null);
	}

	@Test
	public void isOfCorrectType() {
		buildRadioQuestion();
		assertEquals(question.getQuestionType(), QuestionType.MULTIPLE_CHOICE_RADIO);
	}

}
