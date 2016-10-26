package no.hib.megagruppe.webpoll.models;

import java.util.ArrayList;
import java.util.List;

import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity.QuestionType;

public class SurveyQuestionModel {
	private String text;
	private List<String> options;
	private QuestionType questionType;
	private String[] answers;
	
	
	
	public SurveyQuestionModel(String text, QuestionType questionType, List<OptionEntity> optionEntities){
		this.text = text;
		this.questionType = questionType;
		
		this.options = new ArrayList<String>();
		if(questionType == QuestionType.MULTIPLE_CHOICE){
			for(OptionEntity oe : optionEntities){
				this.options.add(oe.getText());
			}
		}
		
	}

	/**
	 * Method to submit a single answer to an Open- or Mulitple-ChoiceQuestion Used if multiple answers are not allowed
	 * 
	 * @param s
	 */
	public void submitAnswer(String s) {
		//FIXME
	}

	/**
	 * Method to submit more than one answer if the question allows it
	 * 
	 * @param s
	 *            an array of Strings, each representing an answer
	 */
	public void submitAnswer(String[] s) {
		//FIXME
	}

	/**
	 * @return String from enum, representing question type
	 */
	public String getType() {
		return questionType.name();
	}

	public void setType(QuestionType questionType) {
		this.questionType = questionType;
	}

	/**
	 * Method that returns the options from a question, if question 'text' this method will return an empty array
	 * 
	 * @return an array of strings for each option in the question
	 */
	public List<String> getOptions() {
		return options;
	}

	public String getText() {
		return text;
	}

	public String[] getAnswers() {
		return answers;
	}

}
