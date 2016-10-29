package no.hib.megagruppe.webpoll.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity.QuestionType;
import no.hib.megagruppe.webpoll.models.SurveyQuestionModel;

public class SurveyQuestionModelTest {

	/*
	 * Is of correct type.
	 * Submitting a single answer stores it in an array of length one.
	 * Submitting two answers stores them in an array of length two.
	 * Submitting a null-answer creates an empty answer.
	 * Submitting a null-array of answers creates an empty array of answers.
	 * Contains the same options as where put in.
	 * Options in correct order.
	 * 
	 */

	private static SurveyQuestionModel question;
	private static List<OptionEntity> options;

	private void buildRadioQuestion() {
		buildOptions();
		question = new SurveyQuestionModel("Hvor gammel er du?", QuestionType.MULTIPLE_CHOICE_RADIO, options);
	}

	private void buildCheckboxQuestion() {
		buildOptions();
		question = new SurveyQuestionModel("Hva er dine favorittintervaller?", QuestionType.MULTIPLE_CHOICE_CHECKBOX, options);
	}

	private void buildFreetextQuestion() {
		question = new SurveyQuestionModel("Hva heter du?", QuestionType.FREE_TEXT, null);
	}
	
	private static void buildOptions() {
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

	@Test
	public void allQuestionTypesCanBeInstantiated(){
		buildRadioQuestion();
		buildCheckboxQuestion();
		buildFreetextQuestion();
	}
	
	@Test
	public void isOfCorrectType() {
		buildRadioQuestion();
		assertEquals(question.getQuestionType(), QuestionType.MULTIPLE_CHOICE_RADIO);
		buildCheckboxQuestion();
		assertEquals(question.getQuestionType(), QuestionType.MULTIPLE_CHOICE_CHECKBOX);
		buildFreetextQuestion();
		assertEquals(question.getQuestionType(), QuestionType.FREE_TEXT);
		assertFalse(question.getQuestionType().equals(QuestionType.MULTIPLE_CHOICE_CHECKBOX));
	}
	
	@Test
	public void SubmitSingleAnswerStoresAnswerInArrayOfLengthOne(){
		buildRadioQuestion();
		question.submitAnswer(options.get(2).getText());
		assertTrue(question.getAnswers().length == 1);
	}
	
	@Test
	public void SubmitTwoAnswerStoresAnswersInArrayOfLengthTwo(){
		buildCheckboxQuestion();
		String[] answers = {options.get(2).getText(),options.get(3).getText()};
		question.submitAnswer(answers);
		assertTrue(question.getAnswers().length == 2);
	}
	
	@Test
	public void nullSingleAnswerGivesEmptyString(){
		buildFreetextQuestion();
		String answer = null;
		question.submitAnswer(answer);
		assertTrue(question.getAnswers()[0].equals(""));
	}
	
	@Test
	public void nullAnswerArrayGivesEmptyArray(){
		buildCheckboxQuestion();
		String[] answer = null;
		question.submitAnswer(answer);
		for(String a : question.getAnswers()){
			assertEquals(a,"Does not exist, so this should never be called!");
		}
		
	}
	
	@Test
	public void containsAllOptions(){
		buildRadioQuestion();
		
		boolean success = true;
		for(String s : question.getOptions()){
			boolean contains = false;
			
			Iterator<OptionEntity> i = options.iterator();
			OptionEntity current = null;
			while(i.hasNext() && !contains){
				current = i.next();
				if(current.getText().equals(s)){
					contains = true;
				}
			}
			
			success = success && contains;
		}

		for(OptionEntity oe : options){
			String optionText = oe.getText();
			
			boolean contains = false;
			for(String s : question.getOptions()){
				if(s.equals(optionText)){
					contains = true;
				}
			}
			
			success = success && contains;
		}
		assertTrue(success);
	}
	
	@Test
	public void optionsInCorrectOrder(){
		buildRadioQuestion();
		
		for(int i = 0; i < options.size(); i++){
			String s1 = options.get(i).getText();
			String s2 = question.getOptions().get(i);
			assertEquals(s1, s2);
		}
	}
	
	@Test
	public void successfullyStoresAnswers(){
		buildCheckboxQuestion();
		
		String[] answers = {options.get(0).getText(), options.get(4).getText()};
		question.submitAnswer(answers);
		
		for(String answer : question.getAnswers()){
			boolean containsAnswer = false;
			for(String answer2 : answers){
				if(answer.equals(answer2)){
					containsAnswer = true;
				}
			}
			assertTrue(containsAnswer);
		}
		
		
		buildFreetextQuestion();
		question.submitAnswer(options.get(0).getText());
		
		for(String answer : question.getAnswers()){
			assertTrue(answer.equals(options.get(0).getText()));
		}
	}

}
